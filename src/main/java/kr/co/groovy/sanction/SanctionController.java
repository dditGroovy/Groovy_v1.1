package kr.co.groovy.sanction;

import kr.co.groovy.common.CommonService;
import kr.co.groovy.enums.ClassOfPosition;
import kr.co.groovy.enums.Department;
import kr.co.groovy.enums.SanctionFormat;
import kr.co.groovy.enums.SanctionProgress;
import kr.co.groovy.vo.EmployeeVO;
import kr.co.groovy.vo.SanctionFormatVO;
import kr.co.groovy.vo.SanctionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

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
    @GetMapping("/inProgress")
    public String getInProgress() {
        return "sanction/inProgressBox";
    }
    @GetMapping("/mySanction")
    public String getMySanction() {
        return "sanction/mySanctionBox";
    }

    // 결재요청 - 결재건수 불러오기
    @GetMapping("/loadInProgress")
    @ResponseBody
    public List<SanctionVO> loadInProgressList(String emplId) {
        List<SanctionVO> list = service.loadInProgressList(emplId);
        for (SanctionVO vo : list) {
            vo.setElctrnSanctnFormatCode(SanctionFormat.valueOf(vo.getElctrnSanctnFormatCode()).label());
            vo.setCommonCodeSanctProgrs(SanctionProgress.valueOf(vo.getCommonCodeSanctProgrs()).label());
        }
        return list;
    }


    // 양식 불러오기
    @GetMapping("/write/{formatSanctnKnd}")
    public ModelAndView writeSanction(@PathVariable("formatSanctnKnd") String formatSanctnKnd, @RequestParam("format") String format, ModelAndView mav) {
        String etprCode = service.getSeq(Department.valueOf(formatSanctnKnd).label());
        SanctionFormatVO vo = service.loadFormat(format);
        mav.addObject("format", vo);
        mav.addObject("etprCode", etprCode);
        mav.setViewName("sanction/write");
        return mav;
    }

    // 결재요청 - 결재건수 불러오기
    @GetMapping("/getStatus")
    @ResponseBody
    public String getStatus(@RequestParam("elctrnSanctnDrftEmplId") String elctrnSanctnDrftEmplId,
                                         @RequestParam("commonCodeSanctProgrs") String commonCodeSanctProgrs) {
        return String.valueOf(service.getStatus(elctrnSanctnDrftEmplId, commonCodeSanctProgrs));
    }

    // 결재선 불러오기
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
