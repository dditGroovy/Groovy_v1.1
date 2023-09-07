package kr.co.groovy.vo;
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

}
