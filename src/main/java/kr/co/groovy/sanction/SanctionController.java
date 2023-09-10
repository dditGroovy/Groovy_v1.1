package kr.co.groovy.sanction;

import kr.co.groovy.common.CommonService;
import kr.co.groovy.enums.ClassOfPosition;
import kr.co.groovy.enums.Department;
import kr.co.groovy.utils.BeanInvoker;
import kr.co.groovy.utils.ParamMap;
import kr.co.groovy.vo.EmployeeVO;
import kr.co.groovy.vo.SanctionFormatVO;
import kr.co.groovy.vo.SanctionLineVO;
import kr.co.groovy.vo.SanctionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/sanction")
public class SanctionController {
    final
    SanctionService service;
    final
    CommonService commonService;


    public SanctionController(SanctionService service, CommonService commonService, BeanInvoker invoker, WebApplicationContext context) {
        this.service = service;
        this.commonService = commonService;
    }

    @GetMapping("/template")
    public String getTemplate() {
        return "sanction/template/write";

    }

    @PostMapping("/approve")
    @ResponseBody
    public Map<String, Object> approve(@RequestBody Map<String, Object> request, ModelAndView mav) {
        return service.approve(request);
    }


    @GetMapping("/loadSanction")
    public Map<?, ?> loadSanction(@RequestParam String sanctionNo) {
        // no를 통해 데이터베이스에서 결재 정보를 리플랙션으로 클래스 정보를 받아와서 자동으로 vo의 필드값과 매칭하여 파람맵으로 만들어 맵형태의
        // 객체로 반환하여 jsp로 보낸다. ${}를 통해 jsp에서 결재 정보를 출력한다.
        ParamMap map = ParamMap.init();
        return map;
    }

    @GetMapping("/approve/{SANCTN015}")
    public void approve(Map<String, Object> parameters, @PathVariable String SANCTN015) {
        // 결재 승인 로직 추가
        // 파라미터에서 결재자 정보 추출 및 승인 처리
        String approver = (String) parameters.get("approver");

        // 최종 승인 시 추가 로직
        if (isFinalApproval(parameters)) {
        }
    }

    @GetMapping("/reject/{SANCTN014}")
    public void reject(Map<String, Object> parameters, @PathVariable String SANCTN014) {
        // 결재 반려/거설 로직 추가
        // 파라미터에서 결재자 정보 추출 및 반려 처리
        String approver = (String) parameters.get("approver");

        // 순서 확인 로직 추가
        if (isFinalApproval(parameters)) {
        }
    }

    private boolean isFinalApproval(Map<String, Object> parameters) {
        return false;
    }


    @GetMapping("/box")
    public String getSanctionBox() {
        return "sanction/box";
    }

    @GetMapping("/document")
    public String getInProgress() {
        return "sanction/document";
    }

    // insert - 전자결재
    @PostMapping("/inputSanction")
    @ResponseBody
    public void inputSanction(@RequestBody Map<String, Object> requestData) {
        service.inputSanction(requestData);
    }


    @GetMapping("/loadRequest")
    @ResponseBody
    public List<SanctionVO> loadRequest(String emplId) {
        return service.loadRequest(emplId);
    }

    @GetMapping("/loadAwaiting")
    @ResponseBody
    public List<SanctionLineVO> loadAwaiting(String progrsCode, String emplId) {
        return service.loadAwaiting(progrsCode, emplId);
    }


    // 양식 불러오기
    @GetMapping("/write/{formatSanctnKnd}")
    public ModelAndView writeSanction(@PathVariable("formatSanctnKnd") String formatSanctnKnd, @RequestParam("format") String format, ModelAndView mav) {
        String etprCode = service.getSeq(Department.valueOf(formatSanctnKnd).label());
        SanctionFormatVO vo = service.loadFormat(format);
        mav.addObject("format", vo);
        mav.addObject("etprCode", etprCode);
        mav.setViewName("sanction/template/write");
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
