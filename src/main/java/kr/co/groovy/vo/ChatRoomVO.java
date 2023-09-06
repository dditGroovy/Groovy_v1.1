package kr.co.groovy.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ChatRoomVO {
    private int chttRoomNo;
    private String chttRoomNm;
    private String chttRoomTy;
    private int chttRoomNmpr;
    private Date chttRoomCreatDe;

    private String latestChttCn;
    private Date latestInputDate;
    private String chttRoomThumbnail;

}
