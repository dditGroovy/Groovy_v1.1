package kr.co.groovy.admin.humanresources;

import kr.co.groovy.vo.ConnectionLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/humanResources")
public class HumanResourcesController {
    final
    HumanResourcesService service;

    public HumanResourcesController(HumanResourcesService service) {
        this.service = service;
    }

    @GetMapping("/loadLog")
    ModelAndView loadConnectionLog(ModelAndView mav, String today) {
        List<ConnectionLogVO> list = service.loadConnectionLog(today);
        mav.addObject("logList", list);
        mav.setViewName("admin/connectionLog");
        return mav;
    }
}
