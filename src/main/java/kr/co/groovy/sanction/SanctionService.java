package kr.co.groovy.sanction;

import kr.co.groovy.enums.Department;
import kr.co.groovy.enums.SanctionFormat;
import kr.co.groovy.enums.SanctionProgress;
import kr.co.groovy.utils.ParamMap;
import kr.co.groovy.vo.ReferenceVO;
import kr.co.groovy.vo.SanctionFormatVO;
import kr.co.groovy.vo.SanctionLineVO;
import kr.co.groovy.vo.SanctionVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SanctionService {
    final SanctionMapper mapper;
    final WebApplicationContext context;


    public SanctionService(SanctionMapper mapper, WebApplicationContext context) {
        this.mapper = mapper;
        this.context = context;
    }


    /*  리플랙션  */
    public void approve(@RequestBody Map<String, Object> request, Model model) {
        try {
            String approvalType = (String) request.get("approvalType");
            String methodName = (String) request.get("methodName");
            Map<String, Object> parameters = (Map<String, Object>) request.get("parameters");
            // 동적으로 클래스 로드
            Class<?> serviceType = Class.forName(approvalType);
            Object serviceInstance = context.getBean(serviceType);

            // 메서드를 동적으로 호출
            Method method = serviceType.getDeclaredMethod(methodName, Map.class);
            Object result = method.invoke(serviceInstance, parameters);

            // 결과 데이터를 모델에 추가
            model.addAttribute("result", result);
        } catch (Exception e) {
            e.printStackTrace();
            // 실패 시 에러 처리 로직을 이곳에 추가하세요.
        }

    }

    SanctionFormatVO loadFormat(String format) {
        return mapper.loadFormat(format);
    }

    String getSeq(String formatSanctnKnd) {
        return mapper.getSeq(formatSanctnKnd);
    }


    int getStatus(String elctrnSanctnDrftEmplId, String commonCodeSanctProgrs) {
        return mapper.getStatus(elctrnSanctnDrftEmplId, commonCodeSanctProgrs);
    }

    List<SanctionLineVO> loadAwaiting(String progrsCode, String emplId) {
        List<SanctionLineVO> list = mapper.loadAwaiting(progrsCode, emplId);
        for (SanctionLineVO vo : list) {
            vo.setCommonCodeSanctProgrs(SanctionProgress.valueOf(vo.getCommonCodeSanctProgrs()).label());
        }
        return list;
    }

    List<SanctionVO> loadRequest(String emplId) {
        List<SanctionVO> list = mapper.loadRequest(emplId);
        for (SanctionVO vo : list) {
            vo.setElctrnSanctnFormatCode(SanctionFormat.valueOf(vo.getElctrnSanctnFormatCode()).label());
            vo.setCommonCodeSanctProgrs(SanctionProgress.valueOf(vo.getCommonCodeSanctProgrs()).label());
        }

        return list;
    }

    void inputSanction(Map<String, Object> requestData) {
        SanctionVO vo = new SanctionVO();
        String etprCode = (String) requestData.get("etprCode");
        String formatCode = (String) requestData.get("formatCode");
        String writer = (String) requestData.get("writer");
        String title = (String) requestData.get("title");
        String content = (String) requestData.get("content");

        vo.setElctrnSanctnEtprCode(etprCode);
        vo.setElctrnSanctnFormatCode(formatCode);
        vo.setElctrnSanctnSj(title);
        vo.setElctrnSanctnDc(content);
        vo.setElctrnSanctnDrftEmplId(writer);
        vo.setCommonCodeSanctProgrs("SANCTN010");
        mapper.inputSanction(vo);

        List<String> approverList = (List<String>) requestData.get("approver");
        log.info(approverList + "");

        for (int i = 0; i < approverList.size(); i++) {
            SanctionLineVO lineVO = new SanctionLineVO();
            lineVO.setElctrnSanctnEtprCode(etprCode);
            lineVO.setElctrnSanctnemplId(approverList.get(i));
            lineVO.setSanctnLineOrdr(String.valueOf(i + 1));
            if (i == 0) {
                lineVO.setCommonCodeSanctProgrs("SANCTN012");
            } else {
                lineVO.setCommonCodeSanctProgrs("SANCTN013");
            }
            if (i == approverList.size() - 1) {
                lineVO.setElctrnSanctnFinalAt("Y");
            } else {
                lineVO.setElctrnSanctnFinalAt("N");
            }
            mapper.inputLine(lineVO);
        }

        ReferenceVO referenceVO = new ReferenceVO();
        referenceVO.setElctrnSanctnEtprCode(etprCode);

        List<String> referrerList = (List<String>) requestData.get("referrer");
        for (String referrer : referrerList) {
            referenceVO.setSanctnRefrnEmplId(referrer);
            mapper.inputRefrn(referenceVO);
        }
    }
}
