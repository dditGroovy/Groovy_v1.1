package kr.co.groovy.common;

import kr.co.groovy.employee.EmployeeService;
import kr.co.groovy.enums.ClassOfPosition;
import kr.co.groovy.enums.Department;
import kr.co.groovy.vo.AlarmVO;
import kr.co.groovy.vo.EmployeeVO;
import kr.co.groovy.vo.NoticeVO;
import kr.co.groovy.vo.UploadFileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/common")
@Controller
public class CommonController {
    final
    CommonService service;
    final
    String uploadPath;
    final
    EmployeeService employeeService;


    public CommonController(CommonService service, String uploadPath, EmployeeService employeeService) {
        this.service = service;
        this.uploadPath = uploadPath;
        this.employeeService = employeeService;
    }

    @GetMapping("/loadNoticeList")
    public ModelAndView loadNoticeList(ModelAndView mav) {
        List<NoticeVO> list = service.loadNoticeList();
        mav.addObject("noticeList", list);
        mav.setViewName("common/companyNotice");
        return mav;
    }

    @GetMapping("/findNotice")
    public ModelAndView findNotice(ModelAndView mav, @RequestParam(value = "keyword") String keyword, @RequestParam(value = "sortBy") String sortBy) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("keyword", keyword);
        paramMap.put("sortBy", sortBy);
        List<NoticeVO> list = service.findNotice(paramMap);
        mav.addObject("noticeList", list);
        mav.setViewName("common/companyNotice");
        return mav;
    }

    @GetMapping("/noticeDetail")
    public ModelAndView loadNoticeDetail(ModelAndView mav, String notiEtprCode) {
        service.modifyNoticeView(notiEtprCode);
        NoticeVO vo = service.loadNoticeDetail(notiEtprCode);
        List<UploadFileVO> list = service.loadNotiFiles(notiEtprCode);
        mav.addObject("noticeDetail", vo);
        mav.addObject("notiFiles", list);
        mav.setViewName("common/companyNoticeDetail");
        return mav;
    }

    // 조직도 불러오기
    @GetMapping("/loadOrgChart")
    public ModelAndView loadOrgChart(ModelAndView mav, String depCode) {
        List<String> departmentCodes = Arrays.asList("DEPT010", "DEPT011", "DEPT012", "DEPT013", "DEPT014", "DEPT015");
        for (String deptCode : departmentCodes) {
            List<EmployeeVO> deptEmployees = service.loadOrgChart(deptCode);
            for (EmployeeVO vo : deptEmployees) {
                vo.setCommonCodeDept(Department.valueOf(vo.getCommonCodeDept()).label());
                vo.setCommonCodeClsf(ClassOfPosition.valueOf(vo.getCommonCodeClsf()).label());
            }
            mav.addObject(deptCode + "List", deptEmployees);
        }
        mav.setViewName("common/orgChart");
        return mav;
    }

    // 동호회
    @GetMapping("/club")
    public String club() {
        return "common/club";
    }

    @GetMapping("/fileDownload")
    public void fileDownload(int uploadFileSn, HttpServletResponse resp) throws Exception {
        try {
            UploadFileVO vo = service.downloadNotiFile(uploadFileSn);
            String originalName = new String(vo.getUploadFileOrginlNm().getBytes("utf-8"), "iso-8859-1");
            String filePath = uploadPath + "/notice";
            String fileName = vo.getUploadFileStreNm();

            File file = new File(filePath, fileName);
            if (!file.isFile()) {
                log.info("파일 없음");
                return;
            }

            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + originalName + "\"");
            resp.setHeader("Content-Transfer-Encoding", "binary");
            resp.setContentLength((int) file.length());

            FileInputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = resp.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            log.info("파일 다운로드 실패");
        }
    }

    @PostMapping("/insertAlarm")
    @ResponseBody
    public void insertAlarm(AlarmVO alarmVO) {
        List<EmployeeVO> emplList = employeeService.loadEmpList();
        for (EmployeeVO employeeVO : emplList) {
            alarmVO.setNtcnEmplId(employeeVO.getEmplId());
            service.insertAlarm(alarmVO);
        }
    }
}

