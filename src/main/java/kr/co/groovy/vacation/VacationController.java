package kr.co.groovy.vacation;

import kr.co.groovy.vo.VacationUseVO;
import kr.co.groovy.vo.VacationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/vacation")
public class VacationController {

    private final VacationService service;

    public VacationController(VacationService vacationService) {
        this.service = vacationService;
    }

    @GetMapping("/vacation")
    public String vacation(Model model, Principal principal) {
        String emplId = principal.getName();
        VacationVO vacationVO = service.loadVacationCnt(emplId);
        if (vacationVO != null) {
            int usedVacationCnt = vacationVO.getYrycUseCo();
            int nowVacationCnt = vacationVO.getYrycNowCo();
            int totalVacationCnt = usedVacationCnt + nowVacationCnt;
            model.addAttribute("usedVacationCnt", usedVacationCnt);
            model.addAttribute("nowVacationCnt", nowVacationCnt);
            model.addAttribute("totalVacationCnt", totalVacationCnt);
        }
        return "employee/myVacation";
    }

    @GetMapping("/salary")
    public String salary() {
        return "employee/mySalary";
    }
    @GetMapping("/request")
    public String requestVacation() {
        return "employee/vacation/request";
    }


    @GetMapping("/record")
    public String vacationRecord(Model model, Principal principal) {
        String emplId = principal.getName();
        List<VacationVO> vacationRecord = service.loadVacationRecord(emplId);
        model.addAttribute("vacationRecord", vacationRecord);
        return "employee/vacationRecord";
    }
    @PostMapping(value = "/inputVacation", produces = "application/text; charset=utf8")
    @ResponseBody
    public String inputVacation(VacationUseVO vo){
        return service.inputVacation(vo);
    }

}
