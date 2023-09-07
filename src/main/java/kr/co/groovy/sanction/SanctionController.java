package kr.co.groovy.sanction;

import kr.co.groovy.common.CommonService;
import kr.co.groovy.enums.ClassOfPosition;
import kr.co.groovy.enums.Department;
import kr.co.groovy.vo.EmployeeVO;
import kr.co.groovy.vo.SanctionFormatVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Slf4j
@Controller
@RequestMapping("/sanction")
public class SanctionController {
    final
    SanctionService service;
    final
    CommonService commonService;

    public SanctionController(SanctionService service, CommonService commonService) {
        this.service = service;
        this.commonService = commonService;
    }

    @GetMapping("/sanctionBox")
    public String getSanctionBox() {
        return "sanction/sanctionBox";
    }

    @GetMapping("/write")
    public ModelAndView writeSanction(@RequestParam("format") String format, ModelAndView mav) {
        SanctionFormatVO vo = service.loadFormat(format);
        mav.addObject("format", vo);
        mav.setViewName("sanction/write");
        log.info(format);
        log.info(vo + "");
        return mav;
    }

    @GetMapping("/loadOrgChart")
    @ResponseBody
    public List<EmployeeVO> loadOrgChart(ModelAndView mav) {
        List<String> departmentCodes = Arrays.asList("DEPT010", "DEPT011", "DEPT012", "DEPT013", "DEPT014", "DEPT015");
        List<EmployeeVO> allEmployees = new ArrayList<>();

        for (String deptCode : departmentCodes) {
            List<EmployeeVO> deptEmployees = commonService.loadOrgChart(deptCode);
            for (EmployeeVO vo : deptEmployees) {
                vo.setCommonCodeDept(Department.valueOf(vo.getCommonCodeDept()).label());
                vo.setCommonCodeClsf(ClassOfPosition.valueOf(vo.getCommonCodeClsf()).label());
            }
            allEmployees.addAll(deptEmployees);
        }
        return allEmployees;
    }
}
