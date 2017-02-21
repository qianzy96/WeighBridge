import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import java.io.File;
import java.util.Properties;

public class Email
{
    private String username;
    private String password;

    public Email(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    public void sendMessage(String recipient, String subject, String description, String filePath, String fileTitle)
    {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties, new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(username, password);
            }
        });
        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(description);
            message.setDescription(description);
            if(filePath.length() > 0 && new File(filePath).exists() && fileTitle.length() > 0)
            {
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                Multipart multiPart = new MimeMultipart();
                DataSource source = new FileDataSource(filePath);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(fileTitle);
                multiPart.addBodyPart(messageBodyPart);
                message.setContent(multiPart);
            }
            Transport.send(message);
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
}
