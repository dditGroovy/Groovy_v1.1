package kr.co.groovy.facility;

import kr.co.groovy.enums.Facility;
import kr.co.groovy.enums.Hipass;
import kr.co.groovy.vo.FacilityVO;
import kr.co.groovy.vo.VehicleVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FacilityService {
    private final FacilityMapper mapper;

    public List<VehicleVO> getVehicles() {
        List<VehicleVO> vehicles = mapper.getVehicles();
        for (VehicleVO vehicleVO : vehicles) {
            vehicleVO.setCommonCodeHipassAsnAt(Hipass.valueOf(vehicleVO.getCommonCodeHipassAsnAt()).getLabel());
        }
        return vehicles;
    }

    public List<VehicleVO> getReservedVehicle(String vhcleNo) {
        return mapper.getReservedVehicleByVhcleNo(vhcleNo);
    }

    public List<VehicleVO> getReservedVehicleByEmplId(String vhcleResveEmplId) {
        return mapper.getReservedVehicleByEmplId(vhcleResveEmplId);
    }

    public int inputVehicleReservation(VehicleVO vehicleVO) {
        return mapper.inputVehicleReservation(vehicleVO);
    }

    public int deleteReservedByVhcleResveNo(int vhcleResveNo) {
        return mapper.deleteReservedByVhcleResveNo(vhcleResveNo);
    }

//    public List<FacilityVO> getMeetingRooms() {
//        List<FacilityVO> meetingRooms = mapper.getMeetingRooms();
//        for (FacilityVO meetingRoom : meetingRooms) {
//            meetingRoom.setCommonCodeFcltyKind(Facility.valueOf(meetingRoom.getCommonCodeFcltyKind()).getLabel());
//        }
//        return meetingRooms;
//    }

    public List<FacilityVO> getRestRooms() {
        List<FacilityVO> restRooms = mapper.getRestRooms();
        changeCommonCodeToEnum(restRooms);
        return restRooms;
    }

    public List<FacilityVO> getReservedRestRoomsByFcltyKind(String commonCodeFcltyKind) {
        List<FacilityVO> reservedRestRoomsByFcltyKind = mapper.getReservedRestRoomsByFcltyKind(commonCodeFcltyKind);
        changeCommonCodeToEnum(reservedRestRoomsByFcltyKind);
        return reservedRestRoomsByFcltyKind;
    }

    public List<FacilityVO> getReservedRestRoomByFcltyResveEmplId(String fcltyResveEmplId) {
        List<FacilityVO> reservedRestRoomByFcltyResveEmplId = mapper.getReservedRestRoomByFcltyResveEmplId(fcltyResveEmplId);
        changeCommonCodeToEnum(reservedRestRoomByFcltyResveEmplId);
        return reservedRestRoomByFcltyResveEmplId;
    }

    public int inputRestReservation(FacilityVO facilityVO) {
        return mapper.inputRestReservation(facilityVO);
    }

    public int deleteReservedByFcltyResveSn(int fcltyResveSn) {
        return mapper.deleteReservedByFcltyResveSn(fcltyResveSn);
    }

    private static void changeCommonCodeToEnum(List<FacilityVO> list) {
        for (FacilityVO facilityVO : list) {
            facilityVO.setCommonCodeFcltyKind(Facility.valueOf(facilityVO.getCommonCodeFcltyKind()).getLabel());
        }
    }


}
