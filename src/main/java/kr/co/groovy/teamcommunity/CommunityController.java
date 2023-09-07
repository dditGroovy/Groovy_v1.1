package kr.co.groovy.teamcommunity;

import kr.co.groovy.common.CommonService;
import kr.co.groovy.vo.SntncVO;
import kr.co.groovy.vo.UploadFileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.List;


@RequestMapping("/teamCommunity")
@Slf4j
@Controller
public class CommunityController {
    final CommunityService service;
    final
    CommonService commonService;

    final
    String uploadPath;

    public CommunityController(CommunityService service, CommonService commonService, String uploadPath) {
        this.service = service;
        this.commonService = commonService;
        this.uploadPath = uploadPath;
    }

    @GetMapping("")
    public ModelAndView teamComminity(Principal principal, ModelAndView mav) {
        String recomendEmplId = principal.getName();
        List<SntncVO> sntncList = service.loadPost(recomendEmplId);
        mav.addObject("sntncList", sntncList);
        mav.setViewName("teamcommunity/teamCommunity");
        return mav;
    }

    @PostMapping("/inputPost")
    public String postWrite(String sntncCn, Principal principal, MultipartFile postFile) throws IOException {
        String sntncWritingEmplId = principal.getName();
        SntncVO vo = new SntncVO();
        vo.setSntncWrtingEmplId(sntncWritingEmplId);
        vo.setSntncCn(sntncCn);
        service.inputPost(vo, postFile);
        return "redirect:/teamCommunity";
    }


}
