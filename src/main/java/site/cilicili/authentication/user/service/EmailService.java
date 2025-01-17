package site.cilicili.authentication.user.service;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.authentication.user.service
 * Date:2024/2/28 22:05
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public interface EmailService {

    /**
     * 发送文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送HTML邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendHtmlMail(String to, String subject, String content);

    /**
     * 发送带附件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param filePath 附件
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);
}
