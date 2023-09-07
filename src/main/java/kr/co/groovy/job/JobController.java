package kr.co.groovy.job;

import kr.co.groovy.vo.JobVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/job")
public class JobController {
    final JobService service;

    public JobController(JobService service) {
        this.service = service;
    }

    @GetMapping("/jobDiary")
    public String jobDiary() {
        //불러오기...
        return "employee/jobDiary";
    }

    @GetMapping("/write")
    public String jobDiaryWrite() {
        return "employee/jobDiaryWrite";
    }

    @PostMapping("/insertJob")
    public String insertJob(@ModelAttribute JobVO jobVO, Principal principal, Model model) {
        String emplId = principal.getName();
        jobVO.setJobDiaryWrtingEmplId(emplId);
        String recptnEmplId = service.getLeader(emplId);
        jobVO.setJobDiaryRecptnEmplId(recptnEmplId);

        return "job/jobDiary";
    }
}
