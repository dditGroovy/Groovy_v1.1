package kr.co.groovy.admin.gat;

import kr.co.groovy.vo.NoticeVO;
import kr.co.groovy.vo.VehicleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GatMapper {

    void inputNotice(NoticeVO vo);

    int getNotiSeq();

    void uploadNoticeFile(Map<String, Object> map);

    void deleteNotice(String notiEtprCode);

    List<VehicleVO> getTodayReservedVehicles();

    List<VehicleVO> getAllVehicles();

    int inputVehicle(VehicleVO vo);
}
