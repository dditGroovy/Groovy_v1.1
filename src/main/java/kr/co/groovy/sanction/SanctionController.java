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
import org.springframework.ui.Model;
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

    @GetMapping("/showForm")
    public String showForm(Model model) {
        // HTML 템플릿 파일 로드
//        String template = formService.loadFormTemplate();
//
//        // 템플릿을 모델에 추가
//        model.addAttribute("template", template);

        return "form";
    }


    @PostMapping("/submitForm")
    public String submitForm(@RequestParam Map<String, String> formData, Model model) {
//        // 사용자가 입력한 데이터를 템플릿에 적용
//        String template = formService.loadFormTemplate();
//        for (Map.Entry<String, String> entry : formData.entrySet()) {
//            template = template.replace("{{" + entry.getKey() + "}}", entry.getValue());
//        }
//
//        // 채워진 양식을 모델에 추가
//        model.addAttribute("filledTemplate", template);

        return "form_result";
    }




    @PostMapping("/approve")
    @ResponseBody
    public Map<String, Object> approve(@RequestBody Map<String, Object> request, ModelAndView mav) {
        return service.approve(request);
    }


    @GetMapping("/loadSanction")
    public Map<?, ?> loadSanction(@RequestParam String sanctionNo) {
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
    @GetMapping("/write")
    public String writeSanction(
                                @RequestParam("format") String format,
                                @RequestParam("etprCode") String etprCode, Model model) {
//        String etprCode = service.getSeq(Department.valueOf(formatSanctnKnd).label());
        SanctionFormatVO vo = service.loadFormat(format);
        String template = vo.getFormatCn();
        model.addAttribute("template", template);
        model.addAttribute("etprCode", etprCode);
        return "sanction/template/write";
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
