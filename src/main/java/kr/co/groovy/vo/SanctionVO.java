package kr.co.groovy.vo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class SanctionVO {
    private String elctrnSanctnEtprCode;
    private int elctrnSanctnFormatCode;
    private String elctrnSanctnSj;
    private String elctrnSanctnDc;
    private String elctrnSanctnDrftEmplId;
    private Date elctrnSanctnRecomDate;
    private String commonCodeSanctProgrs;

}
