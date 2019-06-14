package com.roachfu.tutorial.mail;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author roach
 * @date 2018/10/26
 */
public class JavaMailUtils {

    private static final Logger logger = LoggerFactory.getLogger(JavaMailUtils.class);

    /**
     * 发送文本邮件
     *
     * @param javaMail
     * @return
     */
    public static boolean sendTextMail(JavaMail javaMail) {
        return sendMail(javaMail, false);
    }

    /**
     * 发送HTML邮件
     *
     * @param javaMail
     * @return
     */
    public static boolean sendHtmlMail(JavaMail javaMail) {
        return sendMail(javaMail, true);
    }

    private static boolean sendMail(final JavaMail javaMail, boolean isHtml) {
        boolean flag = true;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", javaMail.getHost());
        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.port", "587")
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(javaMail.getUsername(), javaMail.getPassword());
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(javaMail.getUsername()));
            /* 收件邮箱： 可发送多个用户 */
            if (CollectionUtils.isEmpty(javaMail.getToMails())){
                throw new IllegalArgumentException("收件邮箱不能为空");
            }
            String toMails = String.join(",", javaMail.getToMails());
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMails));

            /* 抄送邮箱: 可抄送多个用户 */
            if (CollectionUtils.isNotEmpty(javaMail.getCcMails())) {
                String cc = String.join(",", javaMail.getCcMails());
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            }

            /* 密送邮箱：可密送多个用户 */
            if (CollectionUtils.isNotEmpty(javaMail.getBccMails())) {
                String bcc = String.join(",", javaMail.getBccMails());
                message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
            }

            // 设置邮箱主题
            message.setSubject(javaMail.getSubject());
            Multipart multipart = new MimeMultipart();
            /* 第一部分： 设置邮件body正文 */
            BodyPart bodyPart = new MimeBodyPart();
            if (isHtml) {
                bodyPart.setContent(javaMail.getContent(), "text/html;charset=UTF-8");
            } else {
                bodyPart.setText(javaMail.getContent());
            }
            multipart.addBodyPart(bodyPart);

            /* 第二部分： 设置邮件附件 */
            if (CollectionUtils.isNotEmpty(javaMail.getAttachments())) {
                for (String attachment : javaMail.getAttachments()) {
                    bodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(attachment);
                    bodyPart.setDataHandler(new DataHandler(source));
                    bodyPart.setFileName(source.getName());
                    multipart.addBodyPart(bodyPart);
                }
            }
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
            flag = false;
            logger.error("邮件发送失败: ", e);
        }
        return flag;
    }

    private static String html() {
        return "<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<title>test</title>" +
                "</head>" +
                "<body>" +
                "<div style='color: red; text-align: center'>" +
                "<h1>Hello World</h1>" +
                "</div>" +
                "</body>" +
                "</html>";
    }

    public static void main(String[] args) {
        JavaMail javamail = new JavaMail();
        javamail.setSubject("test");
        javamail.setContent(html());
        javamail.setToMails(Arrays.asList("fuqiang@imagedt.com", "915231174@qq.com"));
        javamail.setAttachments(Arrays.asList("C:\\Users\\1\\Desktop\\city.txt", "C:\\Users\\1\\Desktop\\something.txt"));
        System.out.println(sendMail(javamail, true));
    }
}
