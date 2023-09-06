package kr.co.groovy.admin.humanresources;

import kr.co.groovy.vo.ConnectionLogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Slf4j
@Service
public class HumanResourcesService {
    final
    HumanResourcesMapper mapper;

    public HumanResourcesService(HumanResourcesMapper mapper) {
        this.mapper = mapper;
    }

    List<ConnectionLogVO> loadConnectionLog(String today){
        today = String.valueOf(LocalDate.now());
        log.info(today);
        return mapper.loadConnectionLog(today);
    }
}
