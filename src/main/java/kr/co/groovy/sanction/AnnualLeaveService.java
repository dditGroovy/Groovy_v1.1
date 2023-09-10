package kr.co.groovy.sanction;

import kr.co.groovy.enums.Department;
import kr.co.groovy.vo.SanctionFormatVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;
@Slf4j
@Service
public class AnnualLeaveService {
    final
    SanctionMapper mapper;

    public AnnualLeaveService(SanctionMapper mapper) {
        this.mapper = mapper;
    }

    public void approve(Map<String, Object> parameters) {
        String approver = (String) parameters.get("approver");
    }


    public Map<String, Object> viewSanction(Map<String, Object> parameters) throws Exception {
        String etprCode = mapper.getSeq(Department.valueOf((String) parameters.get("etprCode")).label());
        String formatCode = (String) parameters.get("formatCode");
        SanctionFormatVO format = mapper.loadFormat(formatCode);
        parameters.put("etprCode", etprCode);
        parameters.put("format", format.getFormatSj());
        parameters.put("data", parameters);
        return parameters;


    }

}
