package kr.co.groovy.utils;

import kr.co.groovy.vo.UploadFileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    UploadFileVO downloadFile (int uploadFileSn);
}
