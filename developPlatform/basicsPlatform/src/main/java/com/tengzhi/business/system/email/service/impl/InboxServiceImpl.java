package com.tengzhi.business.system.email.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.transaction.Transactional;

import com.tengzhi.base.security.common.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.business.system.email.component.GetMailInfoThread;
import com.tengzhi.business.system.email.dao.EmailConfigDao;
import com.tengzhi.business.system.email.dao.InboxDao;
import com.tengzhi.business.system.email.model.EmailConfig;
import com.tengzhi.business.system.email.model.Inbox;
import com.tengzhi.business.system.email.service.InboxService;

@Service
@Transactional
public class InboxServiceImpl implements InboxService {
	@Autowired
	private InboxDao dao;
	@Autowired
	private EmailConfigDao emailConfigDao;

	@Override
	public BasePage<Inbox> findAll(BaseDto baseDto) throws IOException {
		SessionUser securityUser = SessionUser.SessionUser();
		Map<String, String> map = baseDto.getParamsMap();
		return dao.QueryPageList(baseDto, Specifications.where((c) -> {
			c.eq("userId", securityUser.getUserId());
		}));
	}

	@Override
	public Inbox findById(String Id) {
		return dao.findById(Id).orElse(null);
	}

	@Override
	public Inbox save(Inbox inbox) throws Exception {
		SecurityUser securityUser= SessionUser.SessionUser();
		inbox.setCreationTime(new Date());
		inbox.setUserId(securityUser.getUserId());
		inbox.setCreatorId(securityUser.getUserId());
		inbox.setId(UUIDUtil.uuid());
		return dao.save(inbox);
	}

	@Override
	public void update(Inbox inbox) {
		inbox.setModifyTime(new Date());
		dao.dynamicSave(inbox, dao.findById(inbox.getId()).orElse(null));
	}

	@Override
	public void deleteById(String Id) {
		dao.deleteById(Id);
	}

	@Override
	public Result synchronous(String email) {
		EmailConfig config = emailConfigDao.findByUsername(email);
		try {
			Properties p = new Properties();
 			p.setProperty("mail.pop3.host", config.getHost()); // 按需要更改
			p.setProperty("mail.pop3.port", config.getPort());
			p.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");//指定支持SMTP协议的Transport具体类，允许由第三方提供
			p.put("mail.imap.class", "com.sun.mail.imap.IMAPStore"); 
			// SSL安全连接参数
			p.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			p.setProperty("mail.pop3.socketFactory.fallback", "true");
			p.setProperty("mail.pop3.socketFactory.port",config.getPort());
			Session session = Session.getDefaultInstance(p, null);
			Store store = session.getStore("pop3");
			store.connect(config.getHost(), config.getUsername(), config.getPassword());
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			Message[] message = folder.getMessages();
			SessionUser sessionUser= SessionUser.SessionUser();
			new GetMailInfoThread(message, dao,sessionUser).start();
		} catch (NoSuchProviderException e) {
			return Result.error("同步异常");
		} catch (MessagingException e) {
			return Result.error("同步异常");
		}
		return Result.resultOk("同步成功");
	}

}
