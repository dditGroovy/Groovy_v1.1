package kr.co.groovy.memo;

import java.security.Principal;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.groovy.vo.MemoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/memo")
public class MemoController {
	
	final
	MemoService memoService;
	
	String emplId;
	
	public MemoController(MemoService memoService) {
		this.memoService = memoService;
	}
	
	@GetMapping("/memoMain")
	public String memoMain(Model model, Principal principal) {
		emplId = principal.getName();
		List<MemoVO> list = memoService.getMemo(emplId);
		model.addAttribute("memoList", list);
	
		return "memo/memo";
	}
	
	
	@PostMapping("/memoMain")
	public String insertMemo(@RequestBody MemoVO memoVO, Principal principal) {
		emplId = principal.getName();
		memoVO.setMemoEmplId(emplId);
		
		memoService.inputMemo(memoVO);
		
		return "success";
	}
	

}
