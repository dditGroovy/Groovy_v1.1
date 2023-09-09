package kr.co.groovy.sanction;

import kr.co.groovy.enums.SanctionFormat;
import kr.co.groovy.enums.SanctionProgress;
import kr.co.groovy.vo.ReferenceVO;
import kr.co.groovy.vo.SanctionFormatVO;
import kr.co.groovy.vo.SanctionLineVO;
import kr.co.groovy.vo.SanctionVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SanctionService {
    final
    SanctionMapper mapper;

    public SanctionService(SanctionMapper mapper) {
        this.mapper = mapper;
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
    List<SanctionLineVO> loadAwaiting(String progrsCode,  String emplId){
        List<SanctionLineVO> list = mapper.loadAwaiting(progrsCode,emplId);
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
        vo.setCommonCodeSanctProgrs("SANCTN014");
        mapper.inputSanction(vo);

        List<String> approverList = (List<String>) requestData.get("approver");
        log.info(approverList + "");

        for (int i = 0; i < approverList.size(); i++) {
            SanctionLineVO lineVO = new SanctionLineVO();
            lineVO.setElctrnSanctnEtprCode(etprCode);
            lineVO.setElctrnSanctnemplId(approverList.get(i));
            lineVO.setSanctnLineOrdr(String.valueOf(i + 1));
            if (i == 0) {
                lineVO.setCommonCodeSanctProgrs("SANCTN015");
            } else {
                lineVO.setCommonCodeSanctProgrs("SANCTN016");
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
