package kr.co.groovy.alarm;

import kr.co.groovy.vo.AlarmVO;
import org.springframework.stereotype.Service;

@Service
public class AlarmService {
    final AlarmMapper mapper;

    public AlarmService(AlarmMapper mapper) {
        this.mapper = mapper;
    }

    public void insertAlarm(AlarmVO alarmVO) {
        mapper.insertAlarm(alarmVO);
    }

    public void deleteAlarm(AlarmVO alarmVO) {
        mapper.deleteAlarm(alarmVO);
    }
}
