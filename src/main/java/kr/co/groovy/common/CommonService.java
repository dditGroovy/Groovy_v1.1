package kr.co.groovy.common;

import kr.co.groovy.enums.NoticeKind;
import kr.co.groovy.vo.EmployeeVO;
import kr.co.groovy.vo.NoticeVO;
import kr.co.groovy.vo.UploadFileVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonService {
    final
    CommonMapper mapper;

    public CommonService(CommonMapper mapper) {
        this.mapper = mapper;
    }

    public List<NoticeVO> loadNoticeList() {
        return mapper.loadNoticeList();
    }

    public List<NoticeVO> loadNoticeListForAdmin() {
        List<NoticeVO> list = mapper.loadNoticeList();
        for (NoticeVO noticeVO : list) {
            String iconFileName = noticeVO.getNotiCtgryIconFileStreNm();
            String categoryLabel = NoticeKind.getCategoryLabel(iconFileName);
            noticeVO.setNotiCtgryIconFileStreNm(categoryLabel);
        }
        return list;
    }

    public List<NoticeVO> findNotice(Map<String, Object> paramMap) {
        return mapper.findNotice(paramMap);
    }

    public NoticeVO loadNoticeDetail(String notiSeq) {
        return mapper.loadNoticeDetail(notiSeq);
    }

    public UploadFileVO downloadNotiFile(int uploadFileSn) {
        return mapper.downloadNotiFile(uploadFileSn);
    }

    public List<UploadFileVO> loadNotiFiles(String notiEtprCode) {
        return mapper.loadNotiFiles(notiEtprCode);
    }

    public void modifyNoticeView(String notiEtprCode) {
        mapper.modifyNoticeView(notiEtprCode);
    }

    public List<EmployeeVO> loadOrgChart(String depCode) {
        return mapper.loadOrgChart(depCode);
    }

}
