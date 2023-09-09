package kr.co.groovy.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class SanctionLineVO {
    private String elctrnSanctnEtprCode;
    private String elctrnSanctnemplId;
    private String sanctnLineOrdr;
    private String sanctnLineReturnResn;
    private Date sanctnLineDate;
    private String commonCodeSanctProgrs;
    private String elctrnSanctnFinalAt;


    // 결재 문서함 출력용
    private String emplNm;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date elctrnSanctnRecomDate;
    private String elctrnSanctnSj;


}
