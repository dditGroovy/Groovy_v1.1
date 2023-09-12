package kr.co.groovy.sanction;

import kr.co.groovy.enums.SanctionFormat;
import kr.co.groovy.enums.SanctionProgress;
import kr.co.groovy.utils.ParamMap;
import kr.co.groovy.vo.ReferenceVO;
import kr.co.groovy.vo.SanctionFormatVO;
import kr.co.groovy.vo.SanctionLineVO;
import kr.co.groovy.vo.SanctionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

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


<<<<<<< HEAD
    /*  리플랙션  */
    public void approve(@RequestBody Map<String, Object> request, Model model) {
=======
    public void approve(@RequestBody Map<String, Object> request) {
>>>>>>> origin/main
        try {
            String approvalType = (String) request.get("approvalType");
            String methodName = (String) request.get("methodName");
            Map<String, Object> parameters = (Map<String, Object>) request.get("parameters");
            Class<?> serviceType = Class.forName(approvalType);
            Object serviceInstance = context.getBean(serviceType);
            Method method = serviceType.getDeclaredMethod(methodName, Map.class);
<<<<<<< HEAD
            Object result = method.invoke(serviceInstance, parameters);

            // 결과 데이터를 모델에 추가
            model.addAttribute("result", result);
        } catch (Exception e) {
            e.printStackTrace();
            // 실패 시 에러 처리 로직을 이곳에 추가하세요.
=======
            method.invoke(serviceInstance, parameters);
        } catch (Exception e) {
            e.printStackTrace();
>>>>>>> origin/main
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

    void inputSanction(ParamMap requestData) {
        log.info(String.valueOf(requestData));
        SanctionVO vo = new SanctionVO();
        String etprCode = requestData.getString("etprCode");
        String formatCode = requestData.getString("formatCode");
        String writer = requestData.getString("writer");
        String title = requestData.getString("title");
        String content = requestData.getString("content");

        vo.setElctrnSanctnEtprCode(etprCode);
        vo.setElctrnSanctnFormatCode(formatCode);
        vo.setElctrnSanctnSj(title);
        vo.setElctrnSanctnDc(content);
        vo.setElctrnSanctnDrftEmplId(writer);
        vo.setCommonCodeSanctProgrs("SANCTN010");
        mapper.inputSanction(vo);

        List<String> approverList = requestData.get("approver", List.class);
        log.info(approverList + "");

        if (approverList != null) {
            for (int i = 0; i < approverList.size(); i++) {
                SanctionLineVO lineVO = createSanctionLine(etprCode, approverList.get(i), i, approverList);
                mapper.inputLine(lineVO);
            }
        }

        ReferenceVO referenceVO = new ReferenceVO();
        referenceVO.setElctrnSanctnEtprCode(etprCode);

        List<String> referrerList = requestData.get("referrer", List.class);
        if (referrerList != null) {
            for (String referrer : referrerList) {
                referenceVO.setSanctnRefrnEmplId(referrer);
                mapper.inputRefrn(referenceVO);
            }
        }
    }

    private SanctionLineVO createSanctionLine(String etprCode, String approver, int index, List<String> approverList) {
        SanctionLineVO lineVO = new SanctionLineVO();
        lineVO.setElctrnSanctnEtprCode(etprCode);
        lineVO.setElctrnSanctnemplId(approver);
        lineVO.setSanctnLineOrdr(String.valueOf(index + 1));
        lineVO.setCommonCodeSanctProgrs(index == 0 ? "SANCTN012" : "SANCTN013");
        lineVO.setElctrnSanctnFinalAt(index == approverList.size() - 1 ? "Y" : "N");
        return lineVO;
    }
}
