package com.tengzhi.business.system.email.component;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.system.email.vo.FileEmail;
import com.tengzhi.business.system.email.vo.PicVo;

/**
 * @author lqy 邮件发送组件包
 */
@Component
public class EmailComPonent {
	@Autowired
	static JavaMailSenderImpl jms = new JavaMailSenderImpl();
	String sender;

	/**
	 * 发送纯文本的方式
	 * 
	 * @param sender   发送方
	 * @param receiver 接收方
	 * @param title    发送的标题
	 * @param text     发送的内容
	 * @return
	 */
	public Result sendPlainText(String sender, String receiver, String title, String text) {
		SimpleMailMessage mainMessage = new SimpleMailMessage();
		mainMessage.setFrom(sender);
		mainMessage.setTo(receiver);
		mainMessage.setSubject(title);
		mainMessage.setText(text);
		try {
			jms.send(mainMessage);
		} catch (Exception e) {
			return Result.error("发送失败");
		}
		return Result.resultOk();
	}

	/**
	 * 发送html的方式
	 * 
	 * @param sender   发送方
	 * @param receiver 接收方
	 * @param title    标题
	 * @param content  内容
	 * @return
	 * @throws MessagingException
	 */
	public Result send(String sender, String receiver, String title, String content) throws MessagingException {
		MimeMessage message = jms.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(sender);
		helper.setTo(receiver);
		helper.setSubject(title);
		helper.setText(content, true);
		try {
			jms.send(message);
		} catch (Exception e) {
			return Result.error("发送失败");
		}
		return Result.resultOk();
	}

	/**
	 * @param sender   发送方
	 * @param receiver 接收方
	 * @param title    标题
	 * @param content  内容
	 * @param filePath 附件路径
	 * @return
	 * @throws MessagingException
	 */
	public Result send(String sender, String receiver, String title, String content, String filePath) throws MessagingException {
		MimeMessage message = jms.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(sender);
		helper.setTo(receiver);
		helper.setSubject(title);
		helper.setText(content, true);
		try {
			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = file.getFilename();
			helper.addAttachment(fileName, file);
			jms.send(message);
		} catch (Exception e) {
			return Result.error("发送失败");
		}
		return Result.resultOk();
	}

	/**
	 * 含有图片的邮件
	 * 
	 * @param sender      发送方
	 * @param receiver接收方
	 * @param title       标题
	 * @param content     内容
	 * @param picPath     图片地址
	 * @param picId       唯一id
	 * @return
	 * @throws MessagingException
	 */
	public Result send(String sender, String receiver, String title, String content, String picPath, String picId) throws MessagingException {
		MimeMessage message = jms.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(sender);
		helper.setTo(receiver);
		helper.setSubject(title);
		helper.setText(content, true);
		try {
			FileSystemResource file = new FileSystemResource(new File(picPath));
			helper.addInline(picId, file);
			jms.send(message);
		} catch (Exception e) {
			return Result.error("发送失败");
		}
		return Result.resultOk();
	}

	/**
	 * 发送图片和附件
	 * 
	 * @param sender      发送方
	 * @param receiver 接收方
	 * @param title       标题
	 * @param content     内容
	 * @param list        图片list
	 * @param fileEmail   附件list
	 * @return
	 * @throws MessagingException
	 */
	public Result send(String sender, String receiver, String title, String content, List<PicVo> list, List<FileEmail> fileEmail) throws MessagingException {
		MimeMessage message = jms.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(sender);
		helper.setTo(receiver);
		helper.setSubject(title);
		helper.setText(content, true);
		try {
			list.stream().forEach(e -> {
				FileSystemResource file = new FileSystemResource(new File(e.getPicpath()));
				try {
					helper.addInline(e.getPicid(), file);
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}
			});
			fileEmail.stream().forEach(e -> {
				FileSystemResource file = new FileSystemResource(new File(e.getFilePath()));
				String fileName = file.getFilename();
				try {
					helper.addAttachment(fileName, file);
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}
			});
			jms.send(message);
		} catch (Exception e) {
			return Result.error("发送失败");
		}
		return Result.resultOk();
	}

	/**
	 * 含有图片的邮件
	 * 
	 * @param sender      发送方
	 * @param receiver接收方
	 * @param title       标题
	 * @param content     内容
	 * @param list        图片
	 * @return
	 * @throws MessagingException
	 */
	public Result send(String sender, String receiver, String title, String content, List<PicVo> list) throws MessagingException {
		MimeMessage message = jms.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(sender);
		helper.setTo(receiver);
		helper.setSubject(title);
		helper.setText(content, true);
		try {
			list.stream().forEach(e -> {
				FileSystemResource file = new FileSystemResource(new File(e.getPicpath()));
				try {
					helper.addInline(e.getPicid(), file);
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}
			});
			jms.send(message);
		} catch (Exception e) {
			return Result.error("发送失败");
		}
		return Result.resultOk();
	}

	public void init(String host, int port, String sender, String password) throws MessagingException {
		jms.setHost(host);
		jms.setPort(port);
		jms.setUsername(sender);
		this.sender = sender;
		jms.setPassword(password);
		jms.setDefaultEncoding("Utf-8");
		Properties p = new Properties();
		p.setProperty("mail.smtp.auth", "true");
		jms.setJavaMailProperties(p);
	}

}
