package kr.co.groovy.sanction;

import kr.co.groovy.vo.SanctionFormatVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

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

    int getStatus(String elctrnSanctnDrftEmplId, String commonCodeSanctProgrs){
        return mapper.getStatus(elctrnSanctnDrftEmplId, commonCodeSanctProgrs);
    }

}
