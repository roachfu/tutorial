package com.roachfu.tutorial.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author roach
 * @date 2018/10/26
 */

@Data
@Builder
@AllArgsConstructor
public class JavaMail implements Serializable {

    private static final long serialVersionUID = -3555499543948815612L;

    /**
     * 邮件用户, 即发件人的邮箱
     */
    private String username;

    /**
     * 发件人邮箱密码
     */
    private String password;

    /**
     * 所使用的邮箱服务
     */
    private String host;

    /**
     * 邮件接收用户
     */
    private List<String> toMails;

    /**
     * 抄送邮箱用户 (carbon copy)
     */
    private List<String> ccMails;

    /**
     * 密送邮箱用户 blind carbon copy)
     */
    private List<String> bccMails;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 附件地址
     */
    private List<String> attachments;

    public JavaMail() {
        this.username = "fuqiang_it@qq.com";
        this.password = "fuqiang2831786#*";
        this.host = "smtp.qq.com";
    }

    public JavaMail(String username, String password, String host) {
        this.username = username;
        this.password = password;
        this.host = host;
    }
}
