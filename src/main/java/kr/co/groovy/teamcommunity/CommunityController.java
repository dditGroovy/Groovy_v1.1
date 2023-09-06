package kr.co.groovy.teamcommunity;

import kr.co.groovy.vo.SntncVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;


@RequestMapping("/teamCommunity")
@Slf4j
@Controller
public class CommunityController {
    final CommunityService service;

    public CommunityController(CommunityService service) {this.service = service;}

    @GetMapping("")
    public String teamComminity(Principal principal, Model model){
        String recomendEmplId = principal.getName();
        model.addAttribute("emplId",recomendEmplId);
        return "teamcommunity/teamCommunity";
    }
    @PostMapping("/inputPost")
    public String postWrite(String sntncCn, Principal principal, MultipartFile postFile) throws IOException {
        String sntncWritingEmplId = principal.getName();
        SntncVO vo = new SntncVO();
        vo.setSntncWritingEmplId(sntncWritingEmplId);
        vo.setSntncCn(sntncCn);
        service.inputPost(vo, postFile);
        return "redirect:/teamcommunity";
    }



}
