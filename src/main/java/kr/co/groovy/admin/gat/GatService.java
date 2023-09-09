package kr.co.groovy.admin.gat;

import kr.co.groovy.enums.Hipass;
import kr.co.groovy.vo.NoticeVO;
import kr.co.groovy.vo.VehicleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class GatService {
    final
    GatMapper mapper;
    final
    String uploadPath;

    public GatService(GatMapper mapper, String uploadPath) {
        this.mapper = mapper;
        this.uploadPath = uploadPath;
    }

    public void inputNotice(NoticeVO vo, MultipartFile[] notiFiles) {
        int notiSeq = mapper.getNotiSeq();
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
        Date currentDate = new java.util.Date();
        String formattedDate = sdf.format(currentDate);

        String notiEtprCode = "NOTI-" + notiSeq + "-" + formattedDate;
        vo.setNotiEtprCode(notiEtprCode);
        mapper.inputNotice(vo);

        try {
            String path = uploadPath + "/notice";
            log.info("notice path: " + path);
            File uploadDir = new File(path);
            if (!uploadDir.exists()) {
                if (uploadDir.mkdirs()) {
                    log.info("폴더 생성 성공");
                } else {
                    log.info("폴더 생성 실패");
                }
            }
            for (MultipartFile notiFile : notiFiles) {
                String originalFileName = notiFile.getOriginalFilename();
                String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
                String newFileName = UUID.randomUUID() + "." + extension;

                File saveFile = new File(path, newFileName);
                notiFile.transferTo(saveFile);

                long fileSize = notiFile.getSize();
                HashMap<String, Object> map = new HashMap<>();
                map.put("notiEtprCode", notiEtprCode);
                map.put("originalFileName", originalFileName);
                map.put("newFileName", newFileName);
                map.put("fileSize", fileSize);
                mapper.uploadNoticeFile(map);
                log.info("공지 파일 등록 성공");
            }

        } catch (Exception e) {
            log.info("공지 파일 등록 실패");
        }
    }

    public void deleteNotice(String notiEtprCode) {
        mapper.deleteNotice(notiEtprCode);
    }

    public List<VehicleVO> getTodayReservedVehicles() {
        List<VehicleVO> todayReservedVehicles = mapper.getTodayReservedVehicles();
        setCommonCodeToHipass(todayReservedVehicles);
        return todayReservedVehicles;
    }

    public List<VehicleVO> getAllVehicles() {
        List<VehicleVO> allVehicles = mapper.getAllVehicles();
        setCommonCodeToHipass(allVehicles);
        return allVehicles;
    }

    public int inputVehicle(VehicleVO vo) {
        return mapper.inputVehicle(vo);
    }

    private static void setCommonCodeToHipass(List<VehicleVO> list) {
        for (VehicleVO vehicleVO : list) {
            vehicleVO.setCommonCodeHipassAsnAt(Hipass.valueOf(vehicleVO.getCommonCodeHipassAsnAt()).getLabel());
        }
    }

}