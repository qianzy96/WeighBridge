package Frames;
import Messages.Message;
import Users.User;
import Models.Messages;
import org.pushingpixels.flamingo.api.common.*;
import org.pushingpixels.flamingo.api.ribbon.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MessageFrame extends Components
{
    private JRibbonFrame frame;
    private JComponent component;
    private String userID;
    private Messages messages;
    public MessageFrame(String userID)
    {
        this.userID = userID;
        this.messages = new Messages();
        addMenu();
    }
    private void addMenu()
    {
        frame = createRibbonFrame("Messages");
        JRibbonBand messages = createRibbonBand("Messages");
        JCommandButton createMessageButton = createCommandButton("Create A Message");
        createMessageButton.addActionListener(x -> createMessage());
        JCommandButton viewReceivedMessagesButton = createCommandButton("View Received Messages");
        viewReceivedMessagesButton.addActionListener(x -> viewReceivedMessages());
        JCommandButton viewSentMessagesButton = createCommandButton("View Sent Messages");
        viewSentMessagesButton.addActionListener(x -> viewSentMessages());
        messages.addCommandButton(createMessageButton, RibbonElementPriority.TOP);
        messages.addCommandButton(viewReceivedMessagesButton, RibbonElementPriority.TOP);
        messages.addCommandButton(viewSentMessagesButton, RibbonElementPriority.TOP);
        frame.getRibbon().addTask(createRibbonTask("Messages", new JRibbonBand[]{messages}));
        RibbonApplicationMenu anApplicationMenu = createApplicationMenu();
        for(int counter = 0; counter < 5; counter++)
        {
            RibbonApplicationMenuEntryPrimary anApplicationMenuEntry = createApplicationMenuEntry("A Menu Item", x -> {});
            RibbonApplicationMenuEntrySecondary[] menuEntries = new RibbonApplicationMenuEntrySecondary[5];
            for(int index = 0; index < 5; index++)
                menuEntries[index] = createMinorApplicationMenuEntry("A Menu Item", x -> {});
            anApplicationMenuEntry.addSecondaryMenuGroup("Menu Items", menuEntries);
            anApplicationMenu.addMenuEntry(anApplicationMenuEntry);
        }
        anApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Log Out", x -> {new WeighBridgeFrame(); frame.dispose();}));
        anApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Exit", x -> System.exit(0)));
        frame.getRibbon().setApplicationMenu(anApplicationMenu);
        frame.setVisible(true);
    }
    private void addComponent(JComponent aComponent)
    {
        if(component != null)
            frame.remove(component);
        component = aComponent;
        frame.add(component, BorderLayout.CENTER);
        frame.invalidate();
        frame.revalidate();
    }
    private void viewReceivedMessages()
    {
        ArrayList<Message> receivedMessages = messages.getReceivedMessages(userID);
        JTable receivedMessagesTable = createTable();
        ArrayList<String> columnTitles = messages.getSentMessagesHeadings();
        DefaultTableModel receivedMessagesTableModel = (DefaultTableModel)receivedMessagesTable.getModel();
        columnTitles.forEach(x -> receivedMessagesTableModel.addColumn(x));
        receivedMessages.forEach(x ->
        {
            String[] selectedReceivedMessage = new String[5];
            selectedReceivedMessage[0] = x.getCode() + "";
            selectedReceivedMessage[1] = x.getSender().getUsername();
            selectedReceivedMessage[2] = x.getSubject();
            selectedReceivedMessage[3] = x.getDescription();
            selectedReceivedMessage[4] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(x.getDate());
            receivedMessagesTableModel.addRow(selectedReceivedMessage);
        });
        addComponent(receivedMessagesTable);
    }
    private void viewSentMessages()
    {
        ArrayList<Message> sentMessages = messages.getSentMessages(userID);
        JTable sentMessagesTable = createTable();
        ArrayList<String> columnTitles = messages.getSentMessagesHeadings();
        DefaultTableModel sentMessagesTableModel = (DefaultTableModel)sentMessagesTable.getModel();
        columnTitles.forEach(x -> sentMessagesTableModel.addColumn(x));
        sentMessages.forEach(x ->
        {
            String[] selectedSentMessage = new String[5];
            selectedSentMessage[0] = x.getCode() + "";
            selectedSentMessage[1] = x.getRecipient().getUsername();
            selectedSentMessage[2] = x.getSubject();
            selectedSentMessage[3] = x.getDescription();
            selectedSentMessage[4] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(x.getDate());
            sentMessagesTableModel.addRow(selectedSentMessage);
        });
        addComponent(sentMessagesTable);
    }
    private void createMessage()
    {
        JPanel createMessagePanel = new JPanel(new GridLayout(7, 1));
        ArrayList<User> users = messages.getPossibleRecipients(userID);
        ArrayList<String> usernames = new ArrayList<>();
        users.forEach(x -> usernames.add(x.getUsername()));
        createMessagePanel.add(createLabel("Your Recipient"));
        JComboBox recipient = createDropDown(usernames);
        createMessagePanel.add(recipient);
        createMessagePanel.add(createLabel("The Subject Of Your Message"));
        JTextField subject = createTextField("");
        createMessagePanel.add(subject);
        createMessagePanel.add(createLabel("The Content Of Your Message"));
        JTextField description = createTextField("");
        createMessagePanel.add(description);
        JButton sendMessageButton = createButton("Send Your Message");
        sendMessageButton.addActionListener(x ->
        {

            messages.sendMessage(userID, recipient.getSelectedItem().toString(), subject.getText(), description.getText());
            viewSentMessages();
        });
        createMessagePanel.add(sendMessageButton);
        addComponent(createMessagePanel);
    }
}