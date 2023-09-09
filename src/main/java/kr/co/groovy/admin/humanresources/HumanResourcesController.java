package kr.co.groovy.admin.humanresources;

import com.google.gson.Gson;
import kr.co.groovy.vo.ConnectionLogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/humanResources")
public class HumanResourcesController {
    final HumanResourcesService service;

    public HumanResourcesController(HumanResourcesService service) {
        this.service = service;
    }

    // 연결 로그 로드
    @GetMapping("/loadLog")
    ModelAndView loadConnectionLog(ModelAndView mav, String today) {
        List<ConnectionLogVO> list = service.loadConnectionLog(today);
        mav.addObject("logList", list);
        mav.setViewName("admin/connectionLog");
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

        return "admin/hrt/manageDclzAll";
    }

    @GetMapping("/manageDclz/HRT")
    public String manageDclzHRT(Model model) {
        List<Map<String, Object>> list = service.loadDeptDclz("DEPT010");
        Gson gson = new Gson();
        String deptDclzList = gson.toJson(list);
        model.addAttribute("deptDclzList", deptDclzList);
        return "admin/hrt/manageDclzHRT";
    }

    @GetMapping("/manageDclz/AT")
    public String manageDclzAT(Model model) {
        List<Map<String, Object>> list = service.loadDeptDclz("DEPT011");
        Gson gson = new Gson();
        String deptDclzList = gson.toJson(list);
        model.addAttribute("deptDclzList", deptDclzList);
        return "admin/hrt/manageDclzAT";
    }

    @GetMapping("/manageDclz/ST")
    public String manageDclzST(Model model) {
        List<Map<String, Object>> list = service.loadDeptDclz("DEPT012");
        Gson gson = new Gson();
        String deptDclzList = gson.toJson(list);
        model.addAttribute("deptDclzList", deptDclzList);
        return "admin/hrt/manageDclzST";
    }

    @GetMapping("/manageDclz/PRT")
    public String manageDclzPRT(Model model) {
        List<Map<String, Object>> list = service.loadDeptDclz("DEPT013");
        Gson gson = new Gson();
        String deptDclzList = gson.toJson(list);
        model.addAttribute("deptDclzList", deptDclzList);
        return "admin/hrt/manageDclzPRT";
    }

    @GetMapping("/manageDclz/GAT")
    public String manageDclzGAT(Model model) {
        List<Map<String, Object>> list = service.loadDeptDclz("DEPT014");
        Gson gson = new Gson();
        String deptDclzList = gson.toJson(list);
        model.addAttribute("deptDclzList", deptDclzList);
        return "admin/hrt/manageDclzGAT";
    }
}
