package kr.co.groovy.facility;

import kr.co.groovy.enums.Facility;
import kr.co.groovy.vo.FacilityVO;
import kr.co.groovy.vo.VehicleVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/facility")
@RequiredArgsConstructor
public class FacilityController {
    private final FacilityService service;

    @GetMapping("/vehicle")
    public ModelAndView getVehicles(ModelAndView mav) {
        List<VehicleVO> vehicles = service.getVehicles();
        mav.addObject("vehicles", vehicles);
        mav.setViewName("facility/carResve");
        return mav;
    }

    @GetMapping("/vehicle/reservedVehicles/{vhcleNo}")
    @ResponseBody
    public List<VehicleVO> getReservedVehicle(@PathVariable String vhcleNo) {
        return service.getReservedVehicle(vhcleNo);
    }

    @GetMapping("/vehicle/myReservations")
    @ResponseBody
    public List<VehicleVO> getReservedVehicleByEmplId(Principal vhcleResveEmplId) {
        return service.getReservedVehicleByEmplId(vhcleResveEmplId.getName());
    }

    @PostMapping("/vehicle")
    @ResponseBody
    public String inputReservation(Principal vhcleResveEmplId, @RequestBody VehicleVO vehicleVO) {
        if (vehicleVO.getVhcleNo() == null || vehicleVO.getVhcleNo() == "") {
            return "vhcleNo is null";
        } else if (vehicleVO.getVhcleResveBeginTime() == null) {
            return "beginTime is null";
        } else if (vehicleVO.getVhcleResveEndTime() == null) {
            return "endTime is null";
        }

        if (vehicleVO.getVhcleResveBeginTime().equals(vehicleVO.getVhcleResveEndTime())) {
            return "same time";
        } else if (vehicleVO.getVhcleResveBeginTime().after(vehicleVO.getVhcleResveEndTime())) {
            return "end early than begin";
        } else {
            vehicleVO.setVhcleResveEmplId(vhcleResveEmplId.getName());
            int count = service.inputReservation(vehicleVO);
            return String.valueOf(count);
        }
    }

    @DeleteMapping("/vehicle/{vhcleResveNo}")
    @ResponseBody
    public String deleteReservedByVhcleResveNo(@PathVariable int vhcleResveNo) {
        int count = service.deleteReservedByVhcleResveNo(vhcleResveNo);
        return String.valueOf(count);
    }

//    @GetMapping("/meeting")
//    public String getMeetingRooms(Model model) {
//        List<FacilityVO> meetingRooms = service.getMeetingRooms();
//
//        log.info("meetingRooms: " + meetingRooms);
//        model.addAttribute("meetingRooms", meetingRooms);
//        return "facility/meetingResve";
//    }

    @GetMapping("/rest")
    public String getRestRooms(Model model) {
        List<FacilityVO> restRooms = service.getRestRooms();
        List<FacilityVO> bedList = new ArrayList<>();
        List<FacilityVO> sofaList = new ArrayList<>();
        for (int i = 0; i < restRooms.size(); i++) {
            if (restRooms.get(i).getCommonCodeFcltyKind().startsWith("R00")) {
                bedList.add(restRooms.get(i));
                model.addAttribute("bedList", bedList);
            }
            if (restRooms.get(i).getCommonCodeFcltyKind().startsWith("R01")) {
                sofaList.add(restRooms.get(i));
                model.addAttribute("sofaList", sofaList);
            }
        }
        return "facility/restResve";
    }

    @GetMapping("/rest/reservedRestRoom/{seatNo}")
    @ResponseBody
    public List<FacilityVO> getReservedRestRoomsByFcltyKind(@PathVariable String seatNo) {
        String commonCodeFcltyKind = Facility.getValueByLabel(seatNo);
        return service.getReservedRestRoomsByFcltyKind(commonCodeFcltyKind);
    }

    @GetMapping("/rest/myReservations")
    @ResponseBody
    public List<FacilityVO> getReservedRestRoomByFcltyResveEmplId(Principal fcltyResveEmplId) {
        return service.getReservedRestRoomByFcltyResveEmplId(fcltyResveEmplId.getName());
    }
}
