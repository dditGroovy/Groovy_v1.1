package kr.co.groovy.vacation;

import kr.co.groovy.vo.VacationVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationService {

    private final VacationMapper vacationMapper;

    public VacationService(VacationMapper vacationMapper) {
        this.vacationMapper = vacationMapper;
    }

    public VacationVO loadVacationCnt(String emplId) {
        return vacationMapper.loadVacationCnt(emplId);
    }

    public List<VacationVO> loadVacationRecord(String emplId) {
        return vacationMapper.loadVacationRecord(emplId);
    }
}
