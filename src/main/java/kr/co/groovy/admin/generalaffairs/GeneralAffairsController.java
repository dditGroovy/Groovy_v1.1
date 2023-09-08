package kr.co.groovy.admin.generalaffairs;

import kr.co.groovy.common.CommonService;
import kr.co.groovy.vo.NoticeVO;
import kr.co.groovy.vo.UploadFileVO;
import kr.co.groovy.vo.VehicleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/generalAffairs")
public class GeneralAffairsController {
    final
    GeneralAffairsService service;

    final
    CommonService commonService;

    public GeneralAffairsController(GeneralAffairsService service, CommonService commonService) {
        this.service = service;
        this.commonService = commonService;
    }

    @GetMapping("/manageNotice")
    public ModelAndView manageNotice(ModelAndView mav) {
        List<NoticeVO> list = commonService.loadNoticeListForAdmin();
        mav.addObject("notiList", list);
        mav.setViewName("admin/manageNotice");
        return mav;

    }

    @GetMapping("/inputNotice")
    public String inputNoticeForm() {
        return "admin/inputNotice";
    }

    @PostMapping("/inputNotice")
    public String inputNotice(NoticeVO vo, MultipartFile[] notiFiles) {
        service.inputNotice(vo, notiFiles);
        return "redirect:/admin/manageNotice";
    }

    @GetMapping("/noticeDetail")
    public ModelAndView loadNoticeDetail(ModelAndView mav, String notiEtprCode) {
        NoticeVO vo = commonService.loadNoticeDetail(notiEtprCode);
        List<UploadFileVO> list = commonService.loadNotiFiles(notiEtprCode);
        mav.addObject("noticeDetail", vo);
        mav.addObject("notiFiles", list);
        mav.setViewName("admin/adminNoticeDetail");
        return mav;
    }

    @GetMapping("/deleteNotice")
    public String deleteNotice(String notiEtprCode) {
        service.deleteNotice(notiEtprCode);
        return "redirect:/admin/manageNotice";
    }

    @GetMapping("/manageVehicle")
    public ModelAndView loadReservedAndRegisteredVehicle(ModelAndView mav) {
        List<VehicleVO> allVehicles = service.getAllVehicles();
        List<VehicleVO> todayReservedVehicles = getTodayReservedVehicles();
        mav.addObject("allVehicles", allVehicles);
        mav.addObject("todayReservedVehicles", todayReservedVehicles);
        mav.setViewName("admin/manageCarResve");
        return mav;
    }

    @GetMapping("/inputVehicle")
    public ModelAndView inputVehicle(ModelAndView mav) {
        mav.setViewName("admin/insertCar");
        return mav;
    }

    @PostMapping("/inputVehicle")
    public ModelAndView insertVehicle(VehicleVO vehicleVO, ModelAndView mav) {
        log.info("vehicleVO: " + vehicleVO);
        int count = service.inputVehicle(vehicleVO);
        if (count > 0) {
            mav.setViewName("redirect:/generalAffairs/manageVehicle");
        }
        return mav;
    }

    @GetMapping("/loadVehicle")
    public ModelAndView loadVehicle(ModelAndView mav) {
        List<VehicleVO> todayReservedVehicles = getTodayReservedVehicles();
        mav.addObject("todayReservedVehicles", todayReservedVehicles);
        mav.setViewName("admin/listCarResve");
        return mav;
    }

    private List<VehicleVO> getTodayReservedVehicles() {
        List<VehicleVO> todayReservedVehicles = service.getTodayReservedVehicles();
        for (int i = 0; i < todayReservedVehicles.size(); i++) {
            todayReservedVehicles.get(i).setVhcleResveNo(i + 1);
        }
        return todayReservedVehicles;
    }
}