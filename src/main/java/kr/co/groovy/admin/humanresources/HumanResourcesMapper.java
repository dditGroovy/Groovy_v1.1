package kr.co.groovy.admin.humanresources;

import kr.co.groovy.vo.CommuteVO;
import kr.co.groovy.vo.ConnectionLogVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HumanResourcesMapper {
    List<ConnectionLogVO> loadConnectionLog(String date);

    List<String> loadDeptList();

    List<CommuteVO> loadAllDclz();

    List<CommuteVO> loadDeptDclz(String deptCode);

    int deptTotalWorkTime(String deptCode);

    int deptAvgWorkTime(String deptCode);
}
