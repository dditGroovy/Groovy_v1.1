package kr.co.groovy.alarm;

import kr.co.groovy.vo.AlarmVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlarmMapper {
    void insertAlarm(AlarmVO alarmVO);

    void deleteAlarm(AlarmVO alarmVO);
}
