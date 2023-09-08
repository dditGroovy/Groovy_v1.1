package kr.co.groovy.admin.generalaffairs;

import kr.co.groovy.vo.NoticeVO;
import kr.co.groovy.vo.VehicleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GeneralAffairsMapper {

    void inputNotice(NoticeVO vo);

    int getNotiSeq();

    void uploadNoticeFile(Map<String, Object> map);

    void deleteNotice(String notiEtprCode);

    List<VehicleVO> getTodayReservedVehicles();

    List<VehicleVO> getAllVehicles();

    int inputVehicle(VehicleVO vo);
}
