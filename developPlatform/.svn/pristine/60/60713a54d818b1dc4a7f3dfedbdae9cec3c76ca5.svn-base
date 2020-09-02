package com.tengzhi.business.system.email.component;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tengzhi.base.jpa.result.Result;

@RestController
public class TestController {
	@Autowired
	private EmailComPonent emailComPonent;

	@GetMapping("/send")
	public Result send() throws MessagingException {
		emailComPonent.init("smtp.qq.com", 25, "994437958@qq.com", "pqxxbduvyrrdbdgc");
		String sender = "994437958@qq.com"; // 这个是发送人的邮箱
		String receiver = "2565321906@qq.com"; // 这个是接受人的邮箱
		String title = "翔翔科技"; // 标题
		String text = "【翔翔科技】王翔天下无敌。";
		Result result = emailComPonent.sendPlainText(sender, receiver, title, text);
		return result;
	}

	@GetMapping("/htmlEmail")
	public Result sendHtml() throws MessagingException {
		String sender = "994437958@qq.com"; // 这个是发送人的邮箱
		String receiver = "2565321906@qq.com"; // 这个是接受人的邮箱
		String title = "翔翔科技"; // 标题
		String content = "<html>\n" + "<body>\n" + "<h2>【翔翔科技】王翔天下无敌!!!</h2>\n" + "</body>\n" + "</html>";
		Result result = emailComPonent.send(sender, receiver, title, content);
		return result;
	}

	/**
	 * 含有附件的邮件发送
	 * 
	 * @return
	 * @throws MessagingException
	 */
	@RequestMapping("/attachment")
	public Result sendAttachment() throws MessagingException {
		String sender = "994437958@qq.com"; // 这个是发送人的邮箱
		String receiver = "2565321906@qq.com"; // 这个是接受人的邮箱
		String title = "翔翔科技"; // 标题
		String filePath = "C:\\Users\\99443\\Desktop\\平台开发.txt";
		String content = "【翔翔科技】王翔天下无敌";
		Result rest = emailComPonent.send(sender, receiver, title, content, filePath);
		return rest;
	}

	/**
	 * 含有附件的邮件发送
	 * 
	 * @return
	 * @throws MessagingException
	 */
	@RequestMapping("/sendPicture")
	public Result sendPicture() throws MessagingException {
		String sender = "994437958@qq.com"; // 这个是发送人的邮箱
		String receiver = "2565321906@qq.com"; // 这个是接受人的邮箱
		String picPath = "C:\\Users\\99443\\Desktop\\测试.jpg";
		String picId = "PIC001";
		String title = "翔翔科技"; // 标题
		String content = "<html><body>【翔翔科技】王翔天下无敌!!!\n" + "<img src='cid:" + picId + "'></img></body></html>";
		Result result = emailComPonent.send(sender, receiver, title, content, picPath, picId);
		return result;
	}
}
