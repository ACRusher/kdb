package com.kdb.service;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by zhouxiliang on 2016/1/26.
 */
public class MailService {

    public  static boolean testSendMail(String[]to,String title,String content) throws MessagingException {
        JavaMailSenderImpl javaMail  =  new JavaMailSenderImpl();
        javaMail.setHost("smtp.163.com");
        javaMail.setPassword("job_zhou2214");
        javaMail.setUsername("zhouxiliang1988");
        Properties prop  =  new  Properties();
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.timeout", "25000");
        javaMail.setJavaMailProperties(prop);

        MimeMessage message  =  javaMail.createMimeMessage();
        String code = "UTF-8";

        MimeMessageHelper messageHelp  =  new  MimeMessageHelper(message, true, code);
        messageHelp.setFrom("zhouxiliang1988@163.com");
        messageHelp.setTo(to);
        messageHelp.setSubject(title);
        messageHelp.setText(content, true);
        javaMail.send(message);
        return true;
    }

    public static void main(String[] args) throws MessagingException {
        String content = "<h2>this is h2</h2><br/> <table><th><td>a</td><td>b</td></th><tr><td>http://www.baidu.com</td><td>test</td></tr></table>";
        String[] to={"zhouxiliang1988@gmail.com"};
        String title="title";

        testSendMail(to, title, content);
    }
}
