package kr.co.groovy.vacation;

import kr.co.groovy.enums.Department;
import kr.co.groovy.sanction.SanctionMapper;
import kr.co.groovy.vo.VacationUseVO;
import kr.co.groovy.vo.VacationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationService {

    private final VacationMapper vacationMapper;

    public VacationService(VacationMapper vacationMapper, SanctionMapper sanctionMapper) {
        this.vacationMapper = vacationMapper;
    }

    public VacationVO loadVacationCnt(String emplId) {
        return vacationMapper.loadVacationCnt(emplId);
    }

    public List<VacationVO> loadVacationRecord(String emplId) {
        return vacationMapper.loadVacationRecord(emplId);
    }
    public String inputVacation(VacationUseVO vo){
        vo.setYrycUseDtlsSn(vacationMapper.getSeq("인사"));
        vacationMapper.inputVacation(vo);
        return vo.getYrycUseDtlsSn();
    }
}
