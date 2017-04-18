package Messages;
import Users.User;

import java.text.SimpleDateFormat;
import java.util.*;
public class Message
{
    private int code;
    private User sender;
    private User recipient;
    private String subject;
    private String description;
    private Date date;
    public Message(int code, User sender, User recipient, String subject, String description, Date date)
    {
        this.code = code;
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.description = description;
        this.date = date;
    }
    public ArrayList<String> toList()
    {
        return new ArrayList<String>(Arrays.asList(code + "", sender.getCode() + "", recipient.getCode() + "", subject, description,
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)));
    }
    public int getCode()
    {
        return code;
    }
    public void setCode(int code)
    {
        this.code = code;
    }
    public User getSender()
    {
        return sender;
    }
    public void setSender(User sender)
    {
        this.sender = sender;
    }
    public User getRecipient()
    {
        return recipient;
    }
    public void setRecipient(User recipient)
    {
        this.recipient = recipient;
    }
    public String getSubject()
    {
        return subject;
    }
    public void setSubject(String subject)
    {
        this.subject = subject;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public Date getDate()
    {
        return date;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }
}
