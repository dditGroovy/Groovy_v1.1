package kr.co.groovy.facility;

import kr.co.groovy.vo.FacilityVO;
import kr.co.groovy.vo.VehicleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FacilityMapper {
    public List<VehicleVO> getVehicles();

    public List<VehicleVO> getReservedVehicleByVhcleNo(String vhcleNo);

    public List<VehicleVO> getReservedVehicleByEmplId(String vhcleResveEmplId);

    public int inputVehicleReservation(VehicleVO vehicleVO);

    public int deleteReservedByVhcleResveNo(int vhcleResveNo);

//    public List<FacilityVO> getMeetingRooms();

    public List<FacilityVO> getRooms(String commonCodeFcltyKind);

    public List<FacilityVO> getReservedRoomsByFcltyKind(String commonCodeFcltyKind);

    public List<FacilityVO> getReservedRoomByFcltyResveEmplId(Map<String, String> map);

    public int inputRestReservation(FacilityVO facilityVO);

    public int deleteReservedByFcltyResveSn(int fcltyResveSn);

    public FacilityVO getFixturesByFcltyKind(String commonCodeFcltyKind);


}
