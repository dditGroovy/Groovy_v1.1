package kr.co.groovy.vacation;

import kr.co.groovy.vo.VacationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/vacation")
public class VacationController {

    private final VacationService vacationService;

    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping("/vacation")
    public String vacation(Model model, Principal principal) {
        String emplId = principal.getName();
        VacationVO vacationVO = vacationService.loadVacationCnt(emplId);
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
        List<VacationVO> vacationRecord = vacationService.loadVacationRecord(emplId);
        model.addAttribute("vacationRecord", vacationRecord);
        return "employee/vacationRecord";
    }

}
