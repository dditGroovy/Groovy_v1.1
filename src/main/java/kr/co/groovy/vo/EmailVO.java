package kr.co.groovy.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class EmailVO {
    private String emailEtprCode;
    private String emailSj;
    private String emailCn;
    private Date emailTrnsmisDt;
    private int emailParntsEmailNo;
    private String emailDsptchEmplId;
    private String commonCodeMailRedngAt;
    private String commonCodeMailImprtncAt;
    private String commonCodeMailDeleteAt;
    private String commonCodeMailTmprStreAt;
    private String emailRecptnEmplId;
    private String commonCodeMailRecpntKind;

}
