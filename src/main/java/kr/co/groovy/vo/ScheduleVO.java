package kr.co.groovy.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScheduleVO {
	private int schdulSn;
	private String schdulNm;
	private Date schdulBeginDate;
	private Date schdulClosDate;
}
