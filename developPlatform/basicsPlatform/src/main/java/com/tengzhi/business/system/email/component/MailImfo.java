package com.tengzhi.business.system.email.component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 邮件信息对象
 *
 */
public class MailImfo {

	private MimeMessage mimeMessage = null;
	private String saveAttachPath = ""; // 附件下载后的存放目录
	private StringBuffer bodyText = new StringBuffer(); // 存放邮件内容的StringBuffer对象
	private String dateFormat = "yy-MM-dd HH:mm"; // 默认的日前显示格式

	/**
	 * 构造函数,初始化一个MimeMessage对象
	 */
	public MailImfo() {
	}

	public MailImfo(MimeMessage mimeMessage) throws MessagingException {
		this.mimeMessage = mimeMessage;
//		mimeMessage.setContent("HelloWorld","text/html;charset=utf8");
//		System.out.println("创建一个ReceiveEmail对象....");
	}

	public void setMimeMessage(MimeMessage mimeMessage) {
		this.mimeMessage = mimeMessage;
		System.out.println("设置一个MimeMessage对象...");
	}

	/**
	 * * 获得发件人的地址和姓名
	 */
	public String getFrom() throws Exception {
		InternetAddress[] address = (InternetAddress[]) mimeMessage.getFrom();
		String from = address[0].getAddress();
		if (from == null) {
			from = "";
		}
		String personal = address[0].getPersonal();
		if (personal == null) {
			personal = "";
		}
		String fromAddr = null;
		if (personal != null || from != null) {
			fromAddr = personal + "<" + from + ">";
		}
		return fromAddr;
	}

	/**
	 * * 获得邮件的收件人，抄送，和密送的地址和姓名，根据所传递的参数的不同 * "to"----收件人 "cc"---抄送人地址 "bcc"---密送人地址
	 */
	public String getMailAddress(String type) throws Exception {
		String mailAddr = "";
		String addType = type.toUpperCase();

		InternetAddress[] address = null;
		if ("TO".equals(addType) || "CC".equals(addType) || "BCC".equals(addType)) {

			if ("TO".equals(addType)) {
				address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.TO);
			} else if ("CC".equals(addType)) {
				address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.CC);
			} else {
				address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.BCC);
			}

			if (address != null) {
				for (int i = 0; i < address.length; i++) {
					String emailAddr = address[i].getAddress();
					if (emailAddr == null) {
						emailAddr = "";
					} else {
						emailAddr = MimeUtility.decodeText(emailAddr);
					}
					String personal = address[i].getPersonal();
					if (personal == null) {
						personal = "";
					} else {
						personal = MimeUtility.decodeText(personal);
					}
					String compositeto = personal + "<" + emailAddr + ">";
					mailAddr += "," + compositeto;
				}
				if (mailAddr.length() < 1) {
					return "";
				}
				mailAddr = mailAddr.substring(1);
			}
		} else {
			throw new Exception("错误的电子邮件类型!");
		}
		return mailAddr;
	}

	/**
	 * * 获得邮件主题
	 *
	 * @throws UnsupportedEncodingException
	 */

	public String getSubject() throws MessagingException, UnsupportedEncodingException {
		String subject = mimeMessage.getHeader("subject")[0];
		System.out.println("==1"+subject);
		subject = subject.replaceAll("gb2312", "gbk").replaceAll("GB2312", "gbk");// 繁体中文
		System.out.println("==2"+subject);

		int num = countStr(subject, "?gbk");
		if (num > 1) {
			subject = decodeMailString(subject);
		} else {
			subject = MimeUtility.decodeText(subject);
		}
		return subject;
	}

	public static String getSubChinese(String str) {
		String rtnStr = "";
		try {
			rtnStr = new String(str.getBytes(StandardCharsets.ISO_8859_1));

			if (rtnStr.contains("=")) {
				rtnStr = MimeUtility.decodeText(str);
			}

		} catch (UnsupportedEncodingException e) {
			rtnStr = str;
			e.printStackTrace();
		}
		System.out.println(rtnStr);
		return rtnStr;
	}


	/**
	 * @param str     原字符串
	 * @param sToFind 需要查找的字符串
	 * @return 返回在原字符串中sToFind出现的次数
	 */
	private int countStr(String str, String sToFind) {
		int num = 0;
		while (str.contains(sToFind)) {
			str = str.substring(str.indexOf(sToFind) + sToFind.length());
			num++;
		}
		return num;
	}

	public String decodeMailString(String str) {
		try {
			if (str == null) {
				return null;
			}
			StringBuilder sb = new StringBuilder();
			String[] aStr;
			while (true) {
				int pos = str.indexOf("=?");
				if (pos > -1) {
					str = MimeUtility.decodeText(str);
					aStr = str.split("=\\?", 2);
					sb.append(aStr[0]);
					if (aStr.length > 1) {
						str = "=?" + aStr[1];
					} else {
						return sb.toString();
					}
				} else {
					return str;
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * * 获得邮件发送日期
	 */
	public Date getSentDate() throws Exception {
		Date sentDate = mimeMessage.getSentDate();
        return sentDate;
	}

	/**
	 * * 获得邮件正文内容
	 */
	public String getBodyText() {
		return bodyText.toString();
	}

	/**
	 * * 解析邮件，把得到的邮件内容保存到一个StringBuffer对象中，解析邮件 * 主要是根据MimeType类型的不同执行不同的操作，一步一步的解析
	 */

	public void getMailContent(Part part) throws Exception {
		String contentType = part.getContentType();
		// 获得邮件的MimeType类型
		int nameIndex = contentType.indexOf("name");
		boolean conName = false;
		if (nameIndex != -1) {
			conName = true;
		}
		if (part.isMimeType("text/plain") && conName == false) {
			// text/plain 类型
			bodyText.append((String) part.getContent());
		} else if (part.isMimeType("text/html") && conName == false) {
			// text/html 类型
			bodyText.append((String) part.getContent());
		} else if (part.isMimeType("multipart/*")) {
			// multipart/*
			Multipart multipart = (Multipart) part.getContent();
			int counts = multipart.getCount();
			for (int i = 0; i < counts; i++) {
				getMailContent(multipart.getBodyPart(i));
			}
		} else if (part.isMimeType("message/rfc822")) {
			getMailContent((Part) part.getContent());
		} else {

		}
	}

	/**
	 * * 判断此邮件是否需要回执，如果需要回执返回"true",否则返回"false"
	 */
	public boolean getReplySign() throws MessagingException {

		boolean replySign = false;

		String[] needReply = mimeMessage.getHeader("Disposition-Notification-To");

		if (needReply != null) {
			replySign = true;
		}
		return replySign;
	}

	/**
	 * 获得此邮件的Message-ID
	 */
	public String getMessageId() throws MessagingException {
		String messageID = mimeMessage.getMessageID();
		System.out.println("邮件ID: " + messageID);
		return messageID;
	}

	/**
	 * 判断此邮件是否已读，如果未读返回false,反之返回true
	 */
	public boolean isNew() throws MessagingException {
		boolean isNew = false;
		Flags flags = ((Message) mimeMessage).getFlags();
		Flags.Flag[] flag = flags.getSystemFlags();
		for (int i = 0; i < flag.length; i++) {
			if (flag[i] == Flags.Flag.SEEN) {
				isNew = true;
			}
		}
		return isNew;
	}

	/**
	 * 判断此邮件是否包含附件
	 */
	public boolean isContainAttach(Part part) throws Exception {
		boolean attachFlag = false;
		if (part.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) part.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				BodyPart mPart = mp.getBodyPart(i);
				String disposition = mPart.getDisposition();
				if ((disposition != null) && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE)))) {
					attachFlag = true;
				} else if (mPart.isMimeType("multipart/*")) {
					attachFlag = isContainAttach(mPart);
				} else {
					String conType = mPart.getContentType();

					if (conType.toLowerCase().indexOf("application") != -1) {
						attachFlag = true;
					}
					if (conType.toLowerCase().indexOf("name") != -1) {
						attachFlag = true;
					}
				}
			}
		} else if (part.isMimeType("message/rfc822")) {
			attachFlag = isContainAttach((Part) part.getContent());
		}
		return attachFlag;
	}

	/**
	 * * 保存附件
	 */

	public void saveAttachMent(Part part) throws Exception {
		String fileName = "";
		if (part.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) part.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				BodyPart mPart = mp.getBodyPart(i);
				String disposition = mPart.getDisposition();
				if ((disposition != null) && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE)))) {
					fileName = mPart.getFileName();
					if (fileName.toLowerCase().indexOf("gb2312") != -1) {
						fileName = MimeUtility.decodeText(fileName);
					}
					saveFile(fileName, mPart.getInputStream());
				} else if (mPart.isMimeType("multipart/*")) {
					saveAttachMent(mPart);
				} else {
					fileName = mPart.getFileName();
					if ((fileName != null) && (fileName.toLowerCase().indexOf("GB2312") != -1)) {
						fileName = MimeUtility.decodeText(fileName);
						saveFile(fileName, mPart.getInputStream());
					}
				}
			}
		} else if (part.isMimeType("message/rfc822")) {
			saveAttachMent((Part) part.getContent());
		}
	}

	/**
	 * 设置附件存放路径
	 */
	public void setAttachPath(String attachPath) {
		this.saveAttachPath = attachPath;
	}

	/**
	 * * 设置日期显示格式
	 */
	public void setDateFormat(String format) throws Exception {
		this.dateFormat = format;
	}

	/**
	 * * 获得附件存放路径
	 */
	public String getAttachPath() {
		return saveAttachPath;
	}

	/**
	 * * 真正的保存附件到指定目录里
	 */
	public void saveFile(String fileName, InputStream in) throws Exception {
		String osName = System.getProperty("os.name");
		String storeDir = getAttachPath();
		String separator = "";
		if (osName == null) {
			osName = "";
		}
		if (osName.toLowerCase().indexOf("win") != -1) {
			separator = "\\";
			if (storeDir == null || "".equals(storeDir)) {
				storeDir = "c:\\tmp";
			}
		} else {
			separator = "/";
			storeDir = "/tmp";
		}
		File storeFile = new File(storeDir + separator + fileName);
		System.out.println("附件的保存地址:　" + storeFile.toString());
		// for(int i=0;storefile.exists();i++){
		// storefile = new File(storedir+separator+fileName+i);
		// }
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;

		try {
			bos = new BufferedOutputStream(new FileOutputStream(storeFile));
			bis = new BufferedInputStream(in);
			int c;
			while ((c = bis.read()) != -1) {
				bos.write(c);
				bos.flush();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception("文件保存失败!");
		} finally {
			bos.close();
			bis.close();
		}
	}

	/**
	 * @param part 解析内容
	 * @throws Exception
	 */
	void rePart(Part part) throws MessagingException, IOException {
		if (part.getDisposition() != null) {

			String strFileNmae = MimeUtility.decodeText(part.getFileName()); // MimeUtility.decodeText解决附件名乱码问题
			System.out.println("发现附件: " + MimeUtility.decodeText(part.getFileName()));
			System.out.println("内容类型: " + MimeUtility.decodeText(part.getContentType()));
			System.out.println("附件内容:" + part.getContent());
			InputStream in = part.getInputStream();// 打开附件的输入流
			// 读取附件字节并存储到文件中
			java.io.FileOutputStream out = new FileOutputStream(strFileNmae);
			int data;
			while ((data = in.read()) != -1) {
				out.write(data);
			}
			in.close();
			out.close();
		} else {
			if (part.getContentType().startsWith("text/plain")) {
				System.out.println("文本内容：" + part.getContent());
			} else {
				// System.out.println("HTML内容：" + part.getContent());
			}
		}
	}

	/**
	 * @param multipart // 接卸包裹（含所有邮件内容(包裹+正文+附件)）
	 * @throws Exception
	 */
	void reMultipart(Multipart multipart) throws Exception {
		// System.out.println("邮件共有" + multipart.getCount() + "部分组成");
		// 依次处理各个部分
		for (int j = 0, n = multipart.getCount(); j < n; j++) {
			// System.out.println("处理第" + j + "部分");
			Part part = multipart.getBodyPart(j);// 解包, 取出 MultiPart的各个部分, 每部分可能是邮件内容,
			// 也可能是另一个小包裹(MultipPart)
			// 判断此包裹内容是不是一个小包裹, 一般这一部分是 正文 Content-Type: multipart/alternative
			if (part.getContent() instanceof Multipart) {
				Multipart p = (Multipart) part.getContent();// 转成小包裹
				// 递归迭代
				reMultipart(p);
			} else {
				rePart(part);
			}
		}
	}

}
