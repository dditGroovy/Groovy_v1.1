package kr.co.groovy.teamcommunity;

import kr.co.groovy.vo.SntncVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommunityMapper {
    void inputPost(SntncVO vo);

    int getSeq();
    void uploadPostFile(Map<String, Object> map);

    List<SntncVO> findPost(Map<String, String> map);
    String findRecomend(String sntncEtprCode, String recomendEmplId);
}
