package kr.co.groovy.admin.hrt;

import com.google.gson.Gson;
import kr.co.groovy.vo.ConnectionLogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/hrt")
public class HrtController {
    final HrtService service;

    public HrtController(HrtService service) {
        this.service = service;
    }

    // 연결 로그 로드
    @GetMapping("/loadLog")
    ModelAndView loadConnectionLog(ModelAndView mav, String today) {
        List<ConnectionLogVO> list = service.loadConnectionLog(today);
        mav.addObject("logList", list);
        mav.setViewName("admin/hrt/employee/connectionLog");
        return mav;
    }

    // 부서별 근무 관리 페이지
    @GetMapping("/manageDclz")
    public String manageDclz(Model model) {
        List<String> deptList = service.loadDeptList();
        List<Integer> deptTotalWorkTime = new ArrayList<>();
        List<Integer> deptAvgWorkTime = new ArrayList<>();

        for (String deptCode : deptList) {
            int totalTime = service.deptTotalWorkTime(deptCode);
            int avgTime = service.deptAvgWorkTime(deptCode);
            deptTotalWorkTime.add(totalTime);
            deptAvgWorkTime.add(avgTime);
        }

        List<Map<String, Object>> list = service.loadAllDclz();
        Gson gson = new Gson();
        String allDclzList = gson.toJson(list);

        model.addAttribute("deptTotalWorkTime", deptTotalWorkTime);
        model.addAttribute("deptAvgWorkTime", deptAvgWorkTime);
        model.addAttribute("allDclzList", allDclzList);

        return "admin/hrt/attendance/all";
    }

    @GetMapping("/manageDclz/{deptCode}")
    public String manageDclzDept(Model model, @PathVariable String deptCode) {
        log.info("deptCode : {}", deptCode);
        List<Map<String, Object>> list = service.loadDeptDclz(deptCode);
        log.info("list : {}", list);
        Gson gson = new Gson();
        String deptDclzList = gson.toJson(list);
        model.addAttribute("deptDclzList", deptDclzList);
        return "admin/hrt/attendance/dept";
    }

}
