package kr.co.groovy.sanction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/sanction")
public class SanctionController {
    final
    SanctionService service;

    public SanctionController(SanctionService service) {
        this.service = service;
    }

    @GetMapping("/sanctionBox")
    public String getSanctionBox(){
        return "sanction/sanctionBox";
    }

    @GetMapping("/write")
    public String writeSanction(){
        return "sanction/write";
    }
}
