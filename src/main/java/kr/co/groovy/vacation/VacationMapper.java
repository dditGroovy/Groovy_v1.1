package kr.co.groovy.vacation;

import kr.co.groovy.vo.VacationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VacationMapper {

    VacationVO loadVacationCnt(String emplId);

    List<VacationVO> loadVacationRecord(String emplId);
}
