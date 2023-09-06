package kr.co.groovy.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SntncVO {
    private String sntncEtprCode;
    private String sntncWritingEmplId;
    private String sntncSj;
    private String sntncCn;
    private Date sntncWritingDate;
    private String commonCodeSntncCategory;
}
