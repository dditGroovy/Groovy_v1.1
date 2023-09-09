package kr.co.groovy.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class AlarmVO {
    private int ntcnSn;
    private String ntcnEmplId;
    private String ntcnCn;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date ntcnDate;
    private String commonCodeNtcnKind;
}
