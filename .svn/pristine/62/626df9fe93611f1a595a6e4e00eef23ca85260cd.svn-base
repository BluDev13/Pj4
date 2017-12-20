package manager;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    private static final String username = "prj.shopteam.1@gmail.com";
    private static final String password = "trinh123";
    private static Session session;

    public static void sendRequest(String email, String user, String name, String url) {
        Properties props = new Properties();
        String strMessage;
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("prj.shopteam.1@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Recovery Password");
            strMessage = "Dear " + user;
            strMessage += "<br /> <br />You requested password recovery from FashionShop.Com website<br />";
            strMessage += "To complete the request, please click on the link below!";
            strMessage += "<br /><br /><a href=" + url + ">" + url + "</a>";
            strMessage += "<br /><br />Thank You !";
            message.setContent(strMessage, "text/html");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendPassword(String email, String user, String newPass) {
        Properties props = new Properties();
        String strMessage;
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("prj.shopteam.1@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("New Password");
            strMessage = "Dear " + user;
            strMessage += "<br /> <br />You requested password recovery from FashionShop.Com website";
            strMessage += "<br /><br /><b>New Account Information : </b>" ;
            strMessage += "<br />Username : " + user;
            strMessage += "<br />Password : " + newPass;
            strMessage += "<br /><br />Please login and change your password.";
            strMessage += "<br />Thank You !";
            message.setContent(strMessage, "text/html");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String randomString() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_+-";
        int length = 10;
        Random rand = new Random();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < length; i++) {
            buf.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return buf.toString();
    }
}
