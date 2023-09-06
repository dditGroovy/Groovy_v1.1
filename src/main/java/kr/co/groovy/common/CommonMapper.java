package kr.co.groovy.common;

import kr.co.groovy.vo.EmployeeVO;
import kr.co.groovy.vo.NoticeVO;
import kr.co.groovy.vo.UploadFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonMapper {
    List<NoticeVO> loadNoticeList();

    List<NoticeVO> findNotice(Map<String, Object> paramMap);

    NoticeVO loadNoticeDetail(String notiSeq);

    List<UploadFileVO> loadNotiFiles(String notiEtprCode);

    UploadFileVO downloadNotiFile(int uploadFileSn);

    void modifyNoticeView(String notiEtprCode);

    List<EmployeeVO> loadOrgChart (String depCode);
}
