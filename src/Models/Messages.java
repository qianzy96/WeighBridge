package Models;
import Database.*;
import Users.User;
import Messages.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class Messages
{
    private Database database;
    public Messages()
    {
        database = new Database();
    }
    public ArrayList<User> getPossibleRecipients(String userID)
    {
        ArrayList<User> possibleRecipients = new ArrayList<>();
        ArrayList<ArrayList<String>> possibleUsers = database.getTableRows("users", new HashMap<>(), new ArrayList<>(), "");
        possibleUsers.forEach(x ->
        {
            if(!x.get(0).equals(userID))
                possibleRecipients.add(new User(Integer.parseInt(x.get(0)), x.get(1), x.get(2), x.get(3), x.get(4), x.get(5), x.get(6)));
        });
        return possibleRecipients;
    }
    public ArrayList<String> getSentMessagesHeadings()
    {
        return new ArrayList<>(Arrays.asList("Message ID", "Recipient", "Subject", "Description", "Date"));
    }
    public ArrayList<String> getReceivedMessagesHeadings()
    {
        return new ArrayList<>(Arrays.asList("Message ID", "Sender", "Subject", "Description", "Date"));
    }
    public ArrayList<Message> getReceivedMessages(String userID)
    {
        ArrayList<Message> receivedMessages = new ArrayList<>();
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("recipient", userID);
        User recipient = convertUserIDToUser(userID);
        ArrayList<ArrayList<String>> receivedMessagesFromDatabase = database.getTableRows("messages", selectedParameters, new ArrayList<>(),
        "");
        receivedMessagesFromDatabase.forEach(x ->
        {
            User sender = convertUserIDToUser(x.get(1));
            MessageBuilder newMessage = new MessageBuilder();
            newMessage.setCode(Integer.parseInt(x.get(0)));
            newMessage.setSender(sender);
            newMessage.setRecipient(recipient);
            newMessage.setSubject(x.get(3));
            newMessage.setDescription(x.get(4));
            try
            {
                newMessage.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(x.get(5)));
            }
            catch(Exception error)
            {
                JOptionPane.showMessageDialog(null, error);
            }
            receivedMessages.add(newMessage.createMessage());
        });
        return receivedMessages;
    }
    public ArrayList<Message> getSentMessages(String userID)
    {
        ArrayList<Message> sentMessages = new ArrayList<>();
        HashMap<String, String> selectedParameters = new HashMap();
        selectedParameters.put("sender", userID);
        User sender = convertUserIDToUser(userID);
        ArrayList<ArrayList<String>> sentMessagesFromDatabase = database.getTableRows("messages", selectedParameters, new ArrayList<>(), "");
        sentMessagesFromDatabase.forEach(x ->
        {
            User recipient = convertUserIDToUser(x.get(2));
            MessageBuilder newMessage = new MessageBuilder();
            newMessage.setCode(Integer.parseInt(x.get(0)));
            newMessage.setSender(sender);
            newMessage.setRecipient(recipient);
            newMessage.setSubject(x.get(3));
            newMessage.setDescription(x.get(4));
            try
            {
                newMessage.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(x.get(5)));
            }
            catch(Exception error)
            {
                JOptionPane.showMessageDialog(null, error);
            }
            sentMessages.add(newMessage.createMessage());
        });
        return sentMessages;
    }
    public void sendMessage(String userID, String recipientUserName, String subject, String description)
    {
        User sender = convertUserIDToUser(userID);
        User recipient = convertUserNameToUser(recipientUserName);
        MessageBuilder newMessage = new MessageBuilder();
        newMessage.setCode(database.getMaxValueOfColumn("messages", "code") + 1);
        newMessage.setSender(sender);
        newMessage.setRecipient(recipient);
        newMessage.setSubject(subject);
        newMessage.setDescription(description);
        newMessage.setDate(new Date());
        Message aMessage = newMessage.createMessage();
        database.insertTableRow("messages", aMessage.toList());
    }
    private User convertUserIDToUser(String userID)
    {
        return convertUserAttributeToUser("code", userID);
    }
    private User convertUserNameToUser(String username)
    {
        return convertUserAttributeToUser("username", username);
    }
    private User convertUserAttributeToUser(String userAttributeTitle, String userAttributeValue)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put(userAttributeTitle, userAttributeValue);
        ArrayList<ArrayList<String>> tableContents = database.getTableRows("users", selectedParameters, new ArrayList<>(), "");
        if(tableContents.size() > 0)
            return new User(Integer.parseInt(tableContents.get(0).get(0)), tableContents.get(0).get(1), tableContents.get(0).get(2), tableContents.get(0).get(3),
                    tableContents.get(0).get(4), tableContents.get(0).get(5), tableContents.get(0).get(6));
        return new User();
    }
}
