package kr.co.groovy.sanction;

import kr.co.groovy.utils.ParamMap;
import kr.co.groovy.vacation.VacationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.security.Principal;
import java.util.HashMap;
=======
>>>>>>> origin/main
import java.util.Map;

@Slf4j
@Service
public class AnnualLeaveService {
    final
    VacationMapper mapper;

    public AnnualLeaveService(VacationMapper mapper) {
        this.mapper = mapper;
    }

    public void afterApprove(Map<String, Object> paramMap) {
        log.info("오남?");
        ParamMap map = ParamMap.init();
        map.put("approveId", paramMap.get("approveId"));
        map.put("elctrnSanctnEtprCode", paramMap.get("elctrnSanctnEtprCode"));
        mapper.modifySanctionCode(map);
    }


<<<<<<< HEAD
    public Map<String, Object> viewSanction(Map<String, Object> parameters) throws Exception {
        log.info("왔남");
        String etprCode = mapper.getSeq(Department.valueOf((String) parameters.get("etprCode")).label());
        String formatCode = (String) parameters.get("formatCode");
        SanctionFormatVO format = mapper.loadFormat(formatCode);
        parameters.put("etprCode", etprCode);
        parameters.put("format", format.getFormatSj());
        parameters.put("data", parameters);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("etprCode", etprCode);
        responseData.put("format", format.getFormatSj());
        responseData.put("data", parameters);

        return responseData;


    }

=======
>>>>>>> origin/main
}
