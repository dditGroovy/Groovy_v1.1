package kr.co.groovy.admin.humanresources;

import kr.co.groovy.vo.CommuteVO;
import kr.co.groovy.vo.ConnectionLogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/humanResources")
public class HumanResourcesController {
    final
    HumanResourcesService service;

    public HumanResourcesController(HumanResourcesService service) {
        this.service = service;
    }

    @GetMapping("/loadLog")
    ModelAndView loadConnectionLog(ModelAndView mav, String today) {
        List<ConnectionLogVO> list = service.loadConnectionLog(today);
        mav.addObject("logList", list);
        mav.setViewName("admin/connectionLog");
        return mav;
    }

    @GetMapping("/manageDclz")
    public String manageDclz(Model model) {
        List<String> deptList = service.loadDeptList();
        List<Integer> deptTotalWorkTime = new ArrayList<>();
        List<Integer> deptAvgWorkTime = new ArrayList<>();
        for(String deptCode : deptList) {
            int totalTime = service.deptTotalWorkTime(deptCode);
            int avgTime = service.deptAvgWorkTime(deptCode);
            deptTotalWorkTime.add(totalTime);
            deptAvgWorkTime.add(avgTime);
        }
        model.addAttribute("deptTotalWorkTime", deptTotalWorkTime);
        model.addAttribute("deptAvgWorkTime", deptAvgWorkTime);
        List<CommuteVO> allDclzList = service.loadAllDclz();
        return "admin/manageDclzAll";
    }

}
