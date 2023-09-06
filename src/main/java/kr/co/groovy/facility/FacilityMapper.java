package kr.co.groovy.facility;

import kr.co.groovy.vo.FacilityVO;
import kr.co.groovy.vo.VehicleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FacilityMapper {
    public List<VehicleVO> getVehicles();

    public List<VehicleVO> getReservedVehicleByVhcleNo(String vhcleNo);

    public List<VehicleVO> getReservedVehicleByEmplId(String vhcleResveEmplId);

    public int inputVehicleReservation(VehicleVO vehicleVO);

    public int deleteReservedByVhcleResveNo(int vhcleResveNo);

    public List<FacilityVO> getMeetingRooms();

    public List<FacilityVO> getRestRooms();
}
