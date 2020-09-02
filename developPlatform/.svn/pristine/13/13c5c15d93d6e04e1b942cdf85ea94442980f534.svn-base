package com.tengzhi.business.system.email.component;


	import java.util.Properties;

	import javax.mail.Folder;
	import javax.mail.Message;
	import javax.mail.MessagingException;
	import javax.mail.NoSuchProviderException;
	import javax.mail.Session;
	import javax.mail.Store;

	public class MailTest
	{

	    /**
	     * @param args
	     */
	    public static void main(String[] args)
	    {
	        try
	        {
	            String host = "pop.qq.com";
	            String username = "994437958@qq.com";
	            String password = "pqxxbduvyrrdbdgc";
	            Properties p = new Properties();
	            p.setProperty("mail.pop3.host", "pop.qq.com"); // 按需要更改
	            p.setProperty("mail.pop3.port", "995");
	            // SSL安全连接参数
	            p.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	            p.setProperty("mail.pop3.socketFactory.fallback", "true");
	            p.setProperty("mail.pop3.socketFactory.port", "995");
	            Session session = Session.getDefaultInstance(p, null);
	            Store store = session.getStore("pop3");
	            store.connect(host, username, password);
	            Folder folder = store.getFolder("INBOX");
	            folder.open(Folder.READ_ONLY);
	            Message[] message = folder.getMessages();
	            System.out.println("邮件数量:　" + message.length);
	            new GetMailInfoThread(message).start();
	        }
	        catch (NoSuchProviderException e)
	        {
	            e.printStackTrace();
	        }
	        catch (MessagingException e)
	        {
	            e.printStackTrace();
	        }
	    }

	}
