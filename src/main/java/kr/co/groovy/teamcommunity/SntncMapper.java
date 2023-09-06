package kr.co.groovy.teamcommunity;

import kr.co.groovy.vo.SntncVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface SntncMapper {
    void inputPost(SntncVO vo);

    int getSeq();
    void uploadPostFile(Map<String, Object> map);
}
