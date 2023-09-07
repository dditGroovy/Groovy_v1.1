package kr.co.groovy.job;

import kr.co.groovy.vo.JobVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobMapper {
    String getLeader(String emplId);

    int insertDiary(JobVO jobVO);
}
