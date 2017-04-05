package Utilities;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import java.io.File;
import java.util.*;
import Database.Database;
public class Email
{
    private String username;
    private String password;
    private String smtpAuth;
    private String smtpStartTlsEnable;
    private String smtpHost;
    private String smtpPort;
    private Properties properties;
    public Email(String username, String password, String smtpAuth, String smtpStartTlsEnable, String smtpHost, String smtpPort)
    {
        this.username = username;
        this.password = password;
        this.smtpAuth = smtpAuth;
        this.smtpStartTlsEnable = smtpStartTlsEnable;
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }
    public Email(String username, String password, Properties properties)
    {
        this.username = username;
        this.password = password;
        this.properties = properties;
    }
    public Email()
    {
        System.out.println("EMAIL DEFAULT CONSTRUCTOR CALLED");
        properties = new Properties();
        Database aDatabase = new Database();
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("title", "mail.");
        ArrayList<ArrayList<String>> tableContents = aDatabase.getTableRows("settings", selectedParameters,
        new ArrayList<>(Arrays.asList("title", "value")), "");
        if(tableContents.size() > 0)
            System.out.println("RECEIVED TABLE CONTENTS");
        for(ArrayList<String> aSelectedRow: tableContents)
            for(String aSelectedRowCell: aSelectedRow)
                System.out.println("SELECTED ROW CELL: " + aSelectedRowCell);
        HashMap<String, String> retrievedValues = new HashMap<>();
        for(ArrayList<String> aSelectedRow : tableContents)
            retrievedValues.put(aSelectedRow.get(0), aSelectedRow.get(1));
        username = retrievedValues.get("mail.username");
        retrievedValues.remove("mail.username");
        password = retrievedValues.get("mail.password");
        retrievedValues.remove("mail.password");
        for(Map.Entry<String, String> aRetrievedValue : retrievedValues.entrySet())
            properties.put(aRetrievedValue.getKey(), aRetrievedValue.getValue());
        System.out.println("USERNAME: " + username);
        System.out.println("PASSWORD: " + password);
        for(Map.Entry<Object, Object> aProperty : properties.entrySet())
        {
            System.out.println("PROPERTY KEY: " + aProperty.getKey());
            System.out.println("PROPERTY VALUE: " + aProperty.getValue());
        }
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getSmtpAuth()
    {
        return smtpAuth;
    }
    public void setSmtpAuth(String smtpAuth)
    {
        this.smtpAuth = smtpAuth;
    }
    public String getSmtpStartTlsEnable()
    {
        return smtpStartTlsEnable;
    }
    public void setSmtpStartTlsEnable(String smtpStartTlsEnable)
    {
        this.smtpStartTlsEnable = smtpStartTlsEnable;
    }
    public String getSmtpHost()
    {
        return smtpHost;
    }
    public void setSmtpHost(String smtpHost)
    {
        this.smtpHost = smtpHost;
    }
    public String getSmtpPort()
    {
        return smtpPort;
    }
    public void setSmtpPort(String smtpPort)
    {
        this.smtpPort = smtpPort;
    }
    public Properties getProperties()
    {
        return properties;
    }
    public void setProperties(Properties properties)
    {
        this.properties = properties;
    }
    public boolean sendMessage(String recipient, String subject, String description, String filePath, String fileTitle)
    {
        boolean messageSent = true;
        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822");
        /*Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");*/
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
            messageSent = false;
        }
        return messageSent;
    }
}
