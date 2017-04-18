package HTMLPages;
import HTMLControls.*;
import Models.*;
import Users.*;
import Utilities.*;
import Messages.*;
import com.teamdev.jxbrowser.chromium.JSONString;
import java.util.*;
public class MessagePage
{
    private Messages messages;
    private static User user;
    public MessagePage()
    {
        messages = new Messages();
    }
    public static void setUser(User user)
    {
        MessagePage.user = user;
    }
    public JSONString createMessagePage()
    {
        MetroFluentMenu messageFluentMenu = new MetroFluentMenu("messageFluentMenu", "Main Menu", "createMainMenuForMessagePage()",
        new ArrayList<>(Collections.singletonList("Messages")));
        ArrayList<MetroFluentButton> buttons = new ArrayList<>();
        buttons.add(new MetroFluentButton("Create A Message", "plus", "createNewMessage();"));
        buttons.add(new MetroFluentButton("View Received Messages", "mail-read", "viewReceivedMessages();"));
        buttons.add(new MetroFluentButton("View Sent Messages", "mail-read", "viewSentMessages();"));
        MetroFluentMenuPanelGroup aMessagesGroup = new MetroFluentMenuPanelGroup("Messages",  buttons);
        messageFluentMenu.addPanelGroups(new ArrayList<>(Collections.singletonList(aMessagesGroup)));
        MetroLayout messagesLayout = new MetroLayout();
        messagesLayout.addRow(messageFluentMenu);
        messagesLayout.addRow(new MetroUpdatePanel("messagesUpdatePanel", null));
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", messagesLayout.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createMainMenu()
    {
        MetroAccordion mainMenuAccordion = new MetroAccordion();
        MetroLayout mainMenuLayout = new MetroLayout();
        MetroTile mainMenuTile = new MetroTile("getPortalPage();", "cyan", "Main Menu", "menu", "");
        MetroTile logOutTile = new MetroTile("loadHTML5Edition();", "cyan", "Log Out", "exit", "");
        MetroTile exitTile = new MetroTile("exit();", "cyan", "Exit", "exit", "");
        mainMenuLayout.addRow(new ArrayList<>(Arrays.asList(mainMenuTile, logOutTile, exitTile)), new ArrayList<>(Arrays.asList(1, 3, 0, 1, 3, 0, 1, 3, 0)));
        mainMenuAccordion.addFrame("Main Menu", mainMenuLayout, "menu");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", mainMenuAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString viewReceivedMessages()
    {
        ArrayList<ArrayList<String>> receivedMessages = new ArrayList<>();
        ArrayList<Message> allReceivedMessages = messages.getReceivedMessages(user.getCode() + "");
        allReceivedMessages.forEach(x -> receivedMessages.add(new ArrayList<>(Arrays.asList(x.getCode() + "", x.getRecipient().getFirstName() + " " +
        x.getRecipient().getLastName(), x.getSubject(), x.getDescription(), x.getDate().toString()))));
        MetroDataTable receivedMessagesDataTable = new MetroDataTable("receivedMessagesDataTable", messages.getReceivedMessagesHeadings(),
        receivedMessages, new ArrayList<>());
        MetroAccordion receivedMessagesAccordion = new MetroAccordion();
        receivedMessagesAccordion.addFrame("Your Received Messages", receivedMessagesDataTable, "mail-read");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", receivedMessagesAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString viewSentMessages(String confirmationMessage)
    {
        ArrayList<ArrayList<String>> sentMessages = new ArrayList<>();
        MetroLayout sentMessagesLayout = new MetroLayout();
        if(confirmationMessage != null && confirmationMessage.length() > 0)
        {
            sentMessagesLayout.addEmptyRows(2);
            sentMessagesLayout.addRow(new MetroPopover(confirmationMessage, "cyan", "top"), new ArrayList<>(Arrays.asList(2, 8, 2)));
            sentMessagesLayout.addEmptyRows(2);
        }
        ArrayList<Message> allSentMessages = messages.getSentMessages(user.getCode() + "");
        allSentMessages.forEach(x -> sentMessages.add(new ArrayList<>(Arrays.asList(x.getCode() + "", x.getSender().getFirstName() + " " +
        x.getSender().getLastName(), x.getSubject(), x.getDescription(), x.getDate().toString()))));
        MetroDataTable sentMessagesDataTable = new MetroDataTable("sentMessagesDataTable", messages.getSentMessagesHeadings(),
        sentMessages, new ArrayList<>());
        MetroAccordion sentMessagesAccordion = new MetroAccordion();
        sentMessagesAccordion.addFrame("Your Sent Messages", sentMessagesDataTable, "mail-read");
        sentMessagesLayout.addRow(sentMessagesAccordion);
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", sentMessagesLayout.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createNewMessage()
    {
        ArrayList<String> users = new ArrayList<>();
        ArrayList<User> possibleUsers = messages.getPossibleRecipients(user.getCode() + "");
        possibleUsers.forEach(x -> users.add(x.getFirstName() + " " + x.getLastName()));
        MetroLayout messageLayout = new MetroLayout();
        MetroDropDown possibleUser = new MetroDropDown("possibleUser", "Please select a recipient", users);
        MetroTextField subject = new MetroTextField("Please enter the subject of the message", "mail-read", "text", "subject");
        MetroTextField description = new MetroTextField("Please enter the description of the message", "mail-read", "text",
        "description");
        MetroCommandButton sendYourMessageButton = new MetroCommandButton("Send", "Send Your Message", "mail-read",
        "saveNewMessage();", "success");
        messageLayout.addRow(possibleUser);
        messageLayout.addEmptyRows(2);
        messageLayout.addRow(subject);
        messageLayout.addEmptyRows(2);
        messageLayout.addRow(description);
        messageLayout.addEmptyRows(2);
        messageLayout.addRow(sendYourMessageButton, new ArrayList<>(Arrays.asList(4, 4, 4)));
        MetroAccordion createNewMessageAccordion = new MetroAccordion();
        createNewMessageAccordion.addFrame("Send Your Message", messageLayout, "mail-read");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", createNewMessageAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString saveNewMessage(String recipientUserName, String subject, String description)
    {
        HashMap<String, String> parameters = new HashMap<>();
        if(recipientUserName.length() == 0)
        {
            parameters.put("title", "Invalid Recipient");
            parameters.put("response", "error");
            parameters.put("content", "The recipient should contain more than 0 characters");
        }
        if(subject.length() < 2)
        {
            parameters.put("title", "Invalid Subject");
            parameters.put("response", "error");
            parameters.put("content", "The subject should contain at least 2 characters");
        }
        if(description.length() < 2)
        {
            parameters.put("title", "Invalid Description");
            parameters.put("response", "error");
            parameters.put("content", "The description should contain at least 2 characters");
        }
        if(parameters.size() > 0)
            return Utilities.convertHashMapToJSON(parameters);
        messages.sendMessage(user.getCode() + "", recipientUserName, subject, description);
        return viewSentMessages("Your message to " + recipientUserName + " was sent at " + new Date().toString());
    }
}
