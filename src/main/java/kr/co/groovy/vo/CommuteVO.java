package kr.co.groovy.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class CommuteVO {
    private String dclzWorkDe;
    private String dclzEmplId;
    private String dclzAttendTm;
    private String dclzLvffcTm;
    private int dclzDailWorkTime;
    private int dclzWikWorkTime;
    private String commonCodeLaborSttus;
    private String emplNm;
    private String deptNm;
    private String clsfNm;
    private int defaulWorkDate; // 소정 근무일 수
    private int realWikWorkDate; // 실제 근무일 수
    private String defaulWorkTime; // 소정 근무시간
    private String realWorkTime; // 실제 근무시간
    private String overWorkTime; // 총 연장 근무시간
    private String totalWorkTime; // 총 근무시간
    private String avgWorkTime; // 평균 근무시간

}
