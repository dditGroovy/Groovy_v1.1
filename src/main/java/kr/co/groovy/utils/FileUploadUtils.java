package kr.co.groovy.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
public class FileUploadUtils {
    public final String uploadPath;

    public FileUploadUtils(String uploadTest) {
        this.uploadPath = uploadTest;
    }

    //2) 연월일 폴더 생성
    public static String getFolder() {
        //간단날짜형식
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        String str = sdf.format(date);

        return str.replace("-", File.separator);
    }

    //3) 이미지인지 판단
    public static boolean checkImageType(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

        String contentType = getContentTypeFromExtension(extension);

        log.info("contentType={}", contentType);

        return contentType != null && contentType.startsWith("image");
    }

    //** 선생님 방법으로 안돼서 추가
    /* [원래 방식]
    public static boolean checkImageType(File file) {
        String contentType = null;
            try {
                contentType = Files.probeContentType(file.toPath());

                log.info("contentType={}", contentType);

                return contentType.startsWith("image"); //MIME 타입 정보가 image로 시작하는지 true/false로 리턴
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
       }
     */
    private static String getContentTypeFromExtension(String extension) {
        switch (extension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            default:
                return null;
        }
    }


    //파일객체를 던지면 Full경로+파일명 리턴
    public static String getFileUrl(MultipartFile picture) {

        // c:\\img\\개똥이.jpg => 개똥이.jpg
        String uploadFileName = picture.getOriginalFilename();
        uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);

        //------------- 같은날 같은 이미지를 업로드 시 파일 중복 방지 시작 -------------
        UUID uuid = UUID.randomUUID();    //임의의 값을 생성

        //원래의 파일 이름과 구분하기 위해 _를 붙임. asdfasdf_개똥이.jpg
        uploadFileName = uuid.toString() + "_" + uploadFileName;
        //------------- 같은날 같은 이미지를 업로드 시 파일 중복 방지 끝 -------------

        // \\2023\\08\\08\\s_asdfasdf_개똥이.jpg
        String result = "\\" + FileUploadUtils.getFolder() + "\\" + uploadFileName;
        result = result.replace("\\", "/");

        // /2023/08/08/asdfasdf_개똥이.jpg
        return result;
    }
}