package kr.co.groovy.admin.humanresources;

import kr.co.groovy.vo.ConnectionLogVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HumanResourcesMapper {
    List<ConnectionLogVO> loadConnectionLog(String date);
}
