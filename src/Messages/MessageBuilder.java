package Messages;
import Users.User;

import java.util.Date;
public class MessageBuilder
{
    private int code;
    private User sender;
    private User recipient;
    private String subject;
    private String description;
    private Date date;
    public MessageBuilder setCode(int code)
    {
        this.code = code;
        return this;
    }
    public MessageBuilder setSender(User sender)
    {
        this.sender = sender;
        return this;
    }
    public MessageBuilder setRecipient(User recipient)
    {
        this.recipient = recipient;
        return this;
    }
    public MessageBuilder setSubject(String subject)
    {
        this.subject = subject;
        return this;
    }
    public MessageBuilder setDescription(String description)
    {
        this.description = description;
        return this;
    }
    public MessageBuilder setDate(Date date)
    {
        this.date = date;
        return this;
    }
    public Message createMessage()
    {
        return new Message(code, sender, recipient, subject, description, date);
    }
}