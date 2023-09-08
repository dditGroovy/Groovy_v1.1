package kr.co.groovy.sanction;

import kr.co.groovy.vo.SanctionFormatVO;
import kr.co.groovy.vo.SanctionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

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

    List<SanctionVO> loadInProgressList(String emplId) {
        return mapper.loadInProgressList(emplId);
    }
}
