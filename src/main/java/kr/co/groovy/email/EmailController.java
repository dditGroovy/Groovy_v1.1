package kr.co.groovy.email;

import kr.co.groovy.vo.EmailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
@Controller
@RequestMapping("/email")
public class EmailController {
    @GetMapping("/allMails")
    public ModelAndView getAllMails(ModelAndView mav, String userId, String password) {
        List<EmailVO> list = new ArrayList<>();
        userId = "groovytest@daum.net";
        password = "groovy402dditfinal";

        Properties properties = new Properties();
        properties.put("mail.store.protocol", "pop3");
        properties.put("mail.pop3.ssl.enable", "true");
        Session session = Session.getDefaultInstance(properties, null);

        try {
            Store store = session.getStore("pop3");
            store.connect("pop.daum.net", userId, password);

            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            Message[] messages = folder.getMessages();
            for (Message mail : messages) {

                EmailVO emailVO = new EmailVO();
                String content = "";
                if (mail.getContent().toString().contains("Multipart")) {
                    Multipart mp = (Multipart) mail.getContent();
                    for (int i = 0; i < mp.getCount(); i++) {
                        MimeBodyPart bp = (MimeBodyPart) mp.getBodyPart(i);
                        if (bp.getContentType().contains("text/html")) {
                            content = bp.getContent().toString();
                        }
                    }
                } else {
                    content = mail.getContent().toString();
                }
                emailVO.setEmailDsptchEmplId(InternetAddress.toUnicodeString(mail.getFrom()));
                emailVO.setEmailRecptnEmplId(userId);
                emailVO.setEmailCn(content);
                emailVO.setEmailSj(mail.getSubject());
                emailVO.setEmailTrnsmisDt(mail.getReceivedDate());
                log.info("emailVO: " + emailVO);
                list.add(emailVO);
            }
            mav.addObject("list", list);
            mav.setViewName("email/allMail");
            folder.close();
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return mav;
    }
}
