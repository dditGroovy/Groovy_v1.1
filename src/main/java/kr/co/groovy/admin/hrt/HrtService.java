package kr.co.groovy.admin.hrt;

import kr.co.groovy.vo.CommuteVO;
import kr.co.groovy.vo.ConnectionLogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class HrtService {

    final HrtMapper mapper;

    public HrtService(HrtMapper mapper) {
        this.mapper = mapper;
    }



}
