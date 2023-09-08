package kr.co.groovy.schedule;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import freemarker.core.ParseException;
import kr.co.groovy.vo.ScheduleVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/full-calendar") // 나중에 매핑 바꾸기
public class ScheduleController {
	
	final
	ScheduleService scheduleService;
	
	public ScheduleController(ScheduleService scheduleService) {
		this.scheduleService = scheduleService; 
	}
	
	@RequestMapping("/main")
	public String scheduleMain() {
		return "schedule/calendar";
	}
	
	@GetMapping("calendar-admin-update")
	@ResponseBody
	public List<Map<String, Object>> findAllSchedule() throws Exception {
		
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		
		HashMap<String, Object> hash = new HashMap<>();
		
		List<ScheduleVO> list = scheduleService.getSchedule();
		
		for(ScheduleVO schedule : list) {
			hash.put("id", schedule.getSchdulSn());
			hash.put("title", schedule.getSchdulNm());
			hash.put("start", schedule.getSchdulBeginDate());
			hash.put("end", schedule.getSchdulClosDate());
			
			jsonObj = new JSONObject(hash);
			jsonArr.add(jsonObj);
			
		}
			log.info("jsonArrCheck:{}",jsonArr);
			return jsonArr;
		}

	}

