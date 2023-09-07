package kr.co.groovy.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;


@Getter
@Setter
@ToString
public class SntncVO {
    private String sntncEtprCode;
    private String sntncWritingEmplId;
    private String sntncSj;
    private String sntncCn;
    private String emplNm;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date sntncWritingDate;
    private String commonCodeSntncCategory;
    private int recomendCnt;
}
