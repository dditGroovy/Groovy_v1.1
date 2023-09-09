package kr.co.groovy.admin.hrt;

import kr.co.groovy.vo.CommuteVO;
import kr.co.groovy.vo.ConnectionLogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class HrtService {

    final
    HrtMapper mapper;

    public HrtService(HrtMapper mapper) {
        this.mapper = mapper;
    }

    List<ConnectionLogVO> loadConnectionLog(String today){
        today = String.valueOf(LocalDate.now());
        log.info(today);
        return mapper.loadConnectionLog(today);
    }

    List<Map<String, Object>> loadAllDclz() {
        List<CommuteVO> list = mapper.loadAllDclz();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for(CommuteVO vo : list) {
            vo.setDefaulWorkDate(5);
            vo.setDefaulWorkTime("40:00");
            int defaulWorkTime = 40 * 60;
            int realWikWorkDate = vo.getRealWikWorkDate();
            int dclzWikWorkTime = vo.getDclzWikWorkTime();
            if(realWikWorkDate != 0 && dclzWikWorkTime != 0) {
                vo.setTotalWorkTime(convertTime(dclzWikWorkTime));
                if (dclzWikWorkTime > defaulWorkTime) {
                    int overWorkTime = dclzWikWorkTime - defaulWorkTime;
                    vo.setOverWorkTime(convertTime(overWorkTime));
                    vo.setRealWorkTime("40:00");
                } else {
                    vo.setOverWorkTime("0");
                    vo.setRealWorkTime(convertTime(dclzWikWorkTime));
                }
                int avgWorkTime = dclzWikWorkTime / realWikWorkDate;
                vo.setAvgWorkTime(convertTime(avgWorkTime));
            } else {
                vo.setRealWorkTime("0");
                vo.setAvgWorkTime("0");
                vo.setOverWorkTime("0");
                vo.setTotalWorkTime("0");
            }
            Map<String, Object> map = convertToMap(vo);
            mapList.add(map);
        }
        return mapList;
    }

    Map<String, Object> convertToMap(CommuteVO vo) {
       Map<String, Object> map = new HashMap<>();
       map.put("emplId", vo.getDclzEmplId());
       map.put("emplNm", vo.getEmplNm());
       map.put("deptNm", vo.getDeptNm());
       map.put("clsfNm", vo.getClsfNm());
       map.put("defaulWorkDate", vo.getDefaulWorkDate());
       map.put("realWikWorkDate", vo.getRealWikWorkDate());
       map.put("defaulWorkTime", vo.getDefaulWorkTime());
       map.put("realWorkTime", vo.getRealWorkTime());
       map.put("overWorkTime", vo.getOverWorkTime());
       map.put("totalWorkTime", vo.getTotalWorkTime());
       map.put("avgWorkTime", vo.getAvgWorkTime());
       return map;
    }

    String convertTime(int time) {
        String hours = String.format("%02d", (time / 60));
        String minutes = String.format("%02d", (time % 60));
        String converted = hours + ":" + minutes;
        return converted;
    }

    List<String> loadDeptList() {
        return mapper.loadDeptList();
    }

    int deptTotalWorkTime(String deptCode) {
        return mapper.deptTotalWorkTime(deptCode);
    }

    int deptAvgWorkTime(String deptCode) {
        return mapper.deptAvgWorkTime(deptCode);
    }

}
