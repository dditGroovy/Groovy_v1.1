package kr.co.groovy.sanction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;
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
    final
    BeanInvoker invoker;
    final WebApplicationContext context;

    public SanctionController(SanctionService service, CommonService commonService, BeanInvoker invoker, WebApplicationContext context) {
        this.service = service;
        this.commonService = commonService;
        this.invoker = invoker;
        this.context = context;
    }

    @PostMapping("/startApproval")
    public String startApproval(@RequestParam("approvalType") String approvalType,
                                @RequestParam("beanName") String beanName,
                                @RequestParam("methodName") String methodName,
                                @RequestParam Map<String, Object> parameters) {
        try {
            /*
            // 데이터베이스에서 JSON 데이터를 읽어옴
            String json = "";

            ParamMap paramMap = ParamMap.fromJson(json);
            String approvalType = paramMap.getString("approvalType");
            String parametersJson = paramMap.getString("parameters");

            // parametersJson을 다시 Map<String, Object> 형태로 파싱
            Map<String, Object> parameters = new Gson().fromJson(parametersJson, new TypeToken<Map<String, Object>>() {
            }.getType());

            // 결재 서비스 클래스 이름과 메서드명 추출
            String[] approvalTypeInfo = approvalType.split("\\.");
            String serviceClassName = approvalTypeInfo[0];
            String methodName = approvalTypeInfo[1];

            // 서비스 클래스 리플렉션으로 로드
            Class<?> serviceType = Class.forName("com.example.approval.service." + serviceClassName);
            Object serviceInstance = context.getBean(serviceType);

            // 메서드를 동적으로 호출
            Method method = serviceType.getDeclaredMethod(methodName, Map.class);
            method.invoke(serviceInstance, parameters);

            */
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 처리 로직 추가
            return "";
        }
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
