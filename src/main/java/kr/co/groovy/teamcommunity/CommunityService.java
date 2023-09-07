package kr.co.groovy.teamcommunity;

import kr.co.groovy.vo.SntncVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CommunityService {
    final CommunityMapper mapper;
    final String uploadPath;

    public CommunityService(CommunityMapper mapper, String uploadPath) {
        this.mapper = mapper;
        this.uploadPath = uploadPath;
    }
    public void inputPost(SntncVO vo, MultipartFile postFile) throws IOException {
        /* sntncEtprCode */
        int postSeq = mapper.getSeq();
        /*'SNTNC-'||SNTNC_SEQ.nextval||'-'||TO_CHAR(sysdate,'yyyyMMdd')*/
        // 현재 날짜 구하기
        Date now = new Date();
        // 날짜 포맷팅
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String nowDate = format.format(now);

        String sntncEtprCode = "SNTNC-" + postSeq + "-" + nowDate;
        vo.setSntncEtprCode(sntncEtprCode);
        log.info(vo.getSntncCn(), vo.getSntncWrtingEmplId(), vo.getSntncEtprCode());
        mapper.inputPost(vo);
        boolean hasFile = postFile.isEmpty();
        log.info(postFile + "");
        if(postFile!= null && postFile.getSize() != 0 && !postFile.getName().equals("")){
            String path = uploadPath + "/teamCommunity";
            log.debug("path: " + path);
            File uploadDir = new File(path);
            if (!uploadDir.exists()) {
                if (uploadDir.mkdirs()) {
                    log.info("폴더 생성 성공");
                } else {
                    log.info("폴더 생성 실패");
                }
            }

            String originalFileName = postFile.getOriginalFilename();
            String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            String newFileName = UUID.randomUUID() + "." + extension;

            File saveFile = new File(path, newFileName);
            postFile.transferTo(saveFile);

            long fileSize = postFile.getSize();
            HashMap<String, Object> map = new HashMap<>();
            map.put("sntncEtprCode", sntncEtprCode);
            map.put("originalFileName", originalFileName);
            map.put("newFileName", newFileName);
            map.put("fileSize", fileSize);
            log.info(String.valueOf(map));

            mapper.uploadPostFile(map);
        }


    }

    public List<SntncVO> loadPost(String emplId){
        return mapper.loadPost(emplId);
    };
    public String findRecomend(String sntncEtprCode, String recomendEmplId) {
        return mapper.findRecomend(sntncEtprCode, recomendEmplId);
    }
}
