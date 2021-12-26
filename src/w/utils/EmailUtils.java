package w.utils;

import w.bean.User;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailUtils {
    public static void sendMessage(User user) {
        //1. 创建Properties对象.        ->      设置连接邮箱服务器的信息.
        Properties props = new Properties();
        //1.1 设置发送邮件的协议.        smtp -> simple mail transport protocol
        props.setProperty("mail.transport.protocol","smtp");
        //1.2 设置邮件服务器的地址.     126邮箱 -> smtp.126.com
        props.setProperty("mail.smtp.host","smtp.qq.com");
        //1.3 设置是否需要权限校验.     需要.
        props.setProperty("mail.smtp.auth","true");
        //2.1 创建Session对象            ->      获取连接邮箱服务器的session对象.
        Session session = Session.getDefaultInstance(props);
        //2.2 设置发送邮件时,打印详细信息
        session.setDebug(true);
        try {
        	System.out.println("trying");
            //3. 创建邮件
            Message message = createMessage(session,user);
            System.out.println(message);
            //4. 获取发送邮件的对象.
            Transport transport = session.getTransport();
            System.out.println(transport);
            //5. 设置邮件用户名和密码(授权码)
            transport.connect("1007091117@qq.com","oiaqbsagtzcabcge");
            //6. 发送邮件
            transport.sendMessage(message,message.getAllRecipients());
            //7. 释放资源
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("发送邮件失败!!");
        }
    }


    public static Message createMessage(Session session,User user) throws Exception {
    	System.out.println(1);
    	System.out.println(session.getProperties());
        //3. 创建MimeMessage邮件对象.   ->      邮件
        Message message = new MimeMessage(session);
        System.out.println(2);
        //4. 设置邮件的发件人.
        message.setFrom(new InternetAddress("1007091117@qq.com","www","UTF-8"));
        System.out.println(3);
        //5. 设置邮件的收件人.
//        message.setRecipients(Message.RecipientType.TO,user.getEmail());
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(user.getEmail()));
        System.out.println(4);
        //6. 设置邮件的主题.
        message.setSubject("发货通知邮件");
        System.out.println(5);
//        http://localhost:8080/xiaomi/userservlet?method=active&email=user.getEmail()&code=user.getCode()
        String url = "http://127.0.0.1:8080/orderservlet?method=updateStatus&email=" + Base64Utils.encode(user.getEmail());
        System.out.println(url);
        //7. 设置邮件的内容.
        message.setContent(user.getUsername() + ",您好<br /> 你的商品已发货！","text/html;charset=UTF-8");
        System.out.println(6);
        //8. 设置邮件的发送时间.
        message.setSentDate(new Date());
        System.out.println(7);
        //9. 保存邮件.
        message.saveChanges();
        System.out.println(8);
        //10. 返回邮件
        return message;
    }


}
