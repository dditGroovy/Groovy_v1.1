package kr.co.groovy.utils;

import kr.co.groovy.vo.UploadFileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@Controller("/file")
public class FileUtil {

    final
    String uploadPath;
    final
    FileService service;

    public FileUtil(String uploadPath, FileService service) {
        this.uploadPath = uploadPath;
        this.service = service;
    }

    @GetMapping("/download")
    public void fileDownload(int uploadFileSn, HttpServletResponse resp, String dir) throws Exception {
        try {
            UploadFileVO vo = service.downloadFile(uploadFileSn);
            String originalName = new String(vo.getUploadFileOrginlNm().getBytes("utf-8"), "iso-8859-1");
            String filePath = uploadPath + "/" + dir;
            String fileName = vo.getUploadFileStreNm();

            File file = new File(filePath, fileName);
            if (!file.isFile()) {
                log.info("파일 없음");
                return;
            }

            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + originalName + "\"");
            resp.setHeader("Content-Transfer-Encoding", "binary");
            resp.setContentLength((int) file.length());

            FileInputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = resp.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            log.info("파일 다운로드 실패");
        }
    }

}
