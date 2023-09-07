package kr.co.groovy.utils;

import kr.co.groovy.vo.UploadFileVO;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    final
    FileMapper mapper;

    public FileService(FileMapper mapper) {
        this.mapper = mapper;
    }
    UploadFileVO downloadFile(int uploadFileSn){
        return mapper.downloadFile(uploadFileSn);
    }
}
