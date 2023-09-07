package kr.co.groovy.job;

import kr.co.groovy.vo.JobVO;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    final JobMapper mapper;

    public JobService(JobMapper mapper) {
        this.mapper = mapper;
    }

    String getLeader(String emplId) {
        return mapper.getLeader(emplId);
    }

    int insertDiary(JobVO jobVO) {
        return mapper.insertDiary(jobVO);
    }
}
