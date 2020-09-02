package com.tengzhi.business.system.email.component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.system.email.dao.InboxDao;

/**
 * 用于获取邮箱中邮件信息的线程
 * 
 * @author lupf
 *
 */
public class GetMailInfoThread extends Thread {
	private InboxDao dao;
	SessionUser securityUser = null;
	Message[] message = null;
	MailImfo re = null;

	public GetMailInfoThread(Message[] message, InboxDao dao, SessionUser securityUser) {
		this.message = message;
		this.securityUser = securityUser;
		this.dao = dao;
	}

	public GetMailInfoThread(Message[] message) {
		this.message = message;
	}

	@Override
	public void run() {
		super.run();
		if (null != message) {
			for (int i = 0; i < message.length; i++) {
				try {
					re = new MailImfo((MimeMessage) message[i]);
					System.err.println(re.getSubject());
//					Inbox ibox = dao.findByEmailId(re.getMessageId());
//					if (ibox != null) {
//						continue;
//					}
//					Inbox inbox = new Inbox();
//					inbox.setTitle(re.getSubject());// 标题
//					inbox.setSender(re.getFrom());// 发件人
//					inbox.setCc(re.getMailAddress("cc"));// 抄送
//					inbox.setSendTime(re.getSentDate());// 发送时间
//					inbox.setEmailId(re.getMessageId());// 邮件id
//					inbox.setContent(re.getBodyText());// 正文
//					inbox.setContainsAttachment(re.isContainAttach((Part) message[i]));// 是否包含附件
//					inbox.setHaveRead(re.isNew());// 是否已读
//					inbox.setUserId(securityUser.getUserId());// 所属用户
//					inbox.setId(UUIDUtil.uuid());
//					dao.save(inbox);
//					Object o = message[i].getContent();
//					if (o instanceof Multipart) {
//						Multipart multipart = (Multipart) o;
//						re.reMultipart(multipart);
//					} else if (o instanceof Part) {
//						Part part = (Part) o;
//						re.rePart(part);
//					}
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.err.println("end==========================================================================");
		}
	}
}
