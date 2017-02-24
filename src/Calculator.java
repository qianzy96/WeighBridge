import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.ribbon.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Calculator extends Components
{
    private JRibbonFrame frame;
    private JComponent component;
    private HashMap<Ration, Double> rationsWithQuantities;
    private String title;
    private String userID;
    public Calculator(String userID)
    {
        rationsWithQuantities = new HashMap<>();
        title = "";
        this.userID = userID;
        createRibbon();
    }
    private void createRibbon()
    {
        frame = createRibbonFrame("Calculator");
        JRibbonBand rationsBand = createRibbonBand("Rations");
        JCommandButton createNewRationButton = createCommandButton("Create New Ration");
        createNewRationButton.addActionListener(x -> {createCalculatorDialogBox();});
        rationsBand.addCommandButton(createNewRationButton, RibbonElementPriority.TOP);
        JCommandButton viewRationsButton = createCommandButton("View Rations");
        viewRationsButton.addActionListener(x -> generateUsersRationsTable("viewTable"));
        rationsBand.addCommandButton(viewRationsButton, RibbonElementPriority.TOP);
        JCommandButton deleteRationButton = createCommandButton("Delete Ration");
        deleteRationButton.addActionListener(x -> generateUsersRationsTable("deleteTable"));
        rationsBand.addCommandButton(deleteRationButton, RibbonElementPriority.TOP);
        RibbonTask rationsTask = createRibbonTask("Rations", new JRibbonBand[]{rationsBand});
        JRibbonBand commoditiesBand = createRibbonBand("Commodities");
        JCommandButton createNewCommodityButton = createCommandButton("Create New Commodity");
        commoditiesBand.addCommandButton(createNewCommodityButton, RibbonElementPriority.TOP);
        JCommandButton viewCommoditiesButton = createCommandButton("View Commodities");
        viewCommoditiesButton.addActionListener(x -> generateRationsTable("viewTable"));
        commoditiesBand.addCommandButton(viewCommoditiesButton, RibbonElementPriority.TOP);
        JCommandButton deleteCommodityButton = createCommandButton("Delete Commodity");
        deleteCommodityButton.addActionListener(x -> generateRationsTable("deleteTable"));
        commoditiesBand.addCommandButton(deleteCommodityButton, RibbonElementPriority.TOP);
        RibbonTask commoditiesTask = createRibbonTask("Commodities", new JRibbonBand[]{commoditiesBand});
        JRibbonBand quickActionsBand = createRibbonBand("Quick Actions");
        JCommandButton generatePDFFileButton = createCommandButton("Generate PDF File");
        generatePDFFileButton.addActionListener(x -> generatePDFFile());
        quickActionsBand.addCommandButton(generatePDFFileButton, RibbonElementPriority.TOP);
        JCommandButton emailPDFFileButton = createCommandButton("Email PDF File");
        emailPDFFileButton.addActionListener(x -> emailPDFFile());
        quickActionsBand.addCommandButton(emailPDFFileButton, RibbonElementPriority.TOP);
        JCommandButton printPDFFileButton = createCommandButton("Print PDF File");
        printPDFFileButton.addActionListener(x -> printPDFFile());
        quickActionsBand.addCommandButton(printPDFFileButton, RibbonElementPriority.TOP);
        RibbonTask quickActionsTask = createRibbonTask("Quick Actions", new JRibbonBand[]{quickActionsBand});
        frame.getRibbon().addTask(rationsTask);
        frame.getRibbon().addTask(commoditiesTask);
        frame.getRibbon().addTask(quickActionsTask);
        RibbonApplicationMenu anApplicationMenu = new RibbonApplicationMenu();
        for(int counter = 0; counter < 7; counter++)
        {
            RibbonApplicationMenuEntryPrimary anApplicationMenuEntry = createApplicationMenuEntry("Primary", x -> {});
            RibbonApplicationMenuEntrySecondary aSecondaryApplicationMenuEntry = createMinorApplicationMenuEntry("Secondary", x -> {});
            anApplicationMenuEntry.addSecondaryMenuGroup("Group", new RibbonApplicationMenuEntrySecondary[]{aSecondaryApplicationMenuEntry,
            aSecondaryApplicationMenuEntry, aSecondaryApplicationMenuEntry, aSecondaryApplicationMenuEntry, aSecondaryApplicationMenuEntry});
            anApplicationMenu.addMenuEntry(anApplicationMenuEntry);
        }
        anApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Return To Main Menu", x -> {new Portal(userID); frame.dispose();}));
        anApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Log Out", x -> {new WeighBridge(); frame.dispose();}));
        anApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Exit", x -> System.exit(0)));
        frame.getRibbon().setApplicationMenu(anApplicationMenu);
        frame.setVisible(true);
    }
    public void createCalculatorDialogBox()
    {
        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        JPanel textBoxPanel = new JPanel(new GridLayout(3, 1));
        JPanel tilesPanel = new JPanel(new GridLayout(5, 1));
        Database main = new Database();
        ArrayList<Ration> availableFeedCosts = new ArrayList<>();
        ArrayList<ArrayList<String>> availableFeeds = main.getTableRows("rations", new HashMap<>(), new ArrayList<>(), "");
        availableFeeds.forEach(x -> availableFeedCosts.add(new Ration(Integer.parseInt(x.get(0)), x.get(1), x.get(2), x.get(3), x.get(4), x.get(5), x.get(6), x.get(7),
        x.get(8), x.get(9), x.get(10), x.get(11), x.get(12), x.get(13))));
        JLabel titleLabel = createLabel("Please enter the title of the ration");
        JTextField titleTextField = createTextField("");
        JComboBox aDropDownBox = createDropDown(new ArrayList<>());
        availableFeedCosts.forEach(x -> aDropDownBox.addItem(x));
        aDropDownBox.addItemListener((x) ->
        {
            while(textBoxPanel.getComponents().length > 0)
                textBoxPanel.remove(0);
            JLabel aLabel = createLabel("The kg per day fresh weight for " + x.getItem());
            JTextField aTextField = createTextField("");
            textBoxPanel.add(aLabel);
            textBoxPanel.add(aTextField);
            JButton aButton = createTile("Save Feed", "", 8);
            aButton.addActionListener((y) ->
            {
                JButton aTile = createTile(aDropDownBox.getSelectedItem() + " " + aTextField.getText() + " kg", "", 8);
                aDropDownBox.removeItem(x.getItem());
                tilesPanel.add(aTile);
                rationsWithQuantities.put((Ration) x.getItem(), Double.parseDouble(aTextField.getText()));
                frame.invalidate();
                frame.revalidate();
            });
            textBoxPanel.add(aButton);
            frame.invalidate();
            frame.revalidate();
        });
        JButton calculateYourRationTile = createTile("Calculate Your Ration", "", 1);
        calculateYourRationTile.addActionListener(x ->
        {
            if(rationsWithQuantities.size() > 0 && titleTextField.getText().length() > 0)
            {
                title = titleTextField.getText();
                createResultsDialogBox();
            }
        });
        mainPanel.add(titleLabel);
        mainPanel.add(titleTextField);
        mainPanel.add(aDropDownBox);
        mainPanel.add(textBoxPanel);
        mainPanel.add(tilesPanel);
        mainPanel.add(calculateYourRationTile);
        addComponent(mainPanel);
    }
    public void createResultsDialogBox()
    {
        Database main = new Database();
        int usersRationsMaxNumber = main.getMaxValueOfColumn("usersrations", "code");
        main.insertTableRow("usersrations", new ArrayList<>(Arrays.asList((usersRationsMaxNumber + 1) + "", userID, title)));
        for(Map.Entry<Ration, Double> aRation : rationsWithQuantities.entrySet())
        {
            int usersRationsComponentsMaxNumber = main.getMaxValueOfColumn("usersrationscomponents", "code");
            main.insertTableRow("usersrationscomponents", new ArrayList<>(Arrays.asList((usersRationsComponentsMaxNumber + 1) + "",
            (usersRationsMaxNumber + 1) + "", aRation.getKey().getCode() + "", aRation.getValue() + "")));
        }
        RationCalculator aCalculator = new RationCalculator(rationsWithQuantities, title);
        JPanel resultsPanel = new JPanel(new GridLayout(4, 2));
        JButton dryMatterTile = createTile("Dry Matter: " + aCalculator.calculateDryMatter(), "", 7);
        JButton crudeProteinTile = createTile("Crude Protein: " + aCalculator.calculateCrudeProtein(), "", 7);
        JButton ndfTile = createTile("NDF: " + aCalculator.calculateNDF(), "", 7);
        JButton starchAndSugarsTile = createTile("Starch & Sugars: " + aCalculator.calculateStarchAndSugars(), "", 7);
        JButton oilTile = createTile("Oil: " + aCalculator.calculateOil(), "", 7);
        JButton rationCostDryMatterTile = createTile("Dry Matter: £" + aCalculator.calculateRationCostDryMatter(), "", 7);
        JButton rationCostFreshWeightTile = createTile("Fresh Weight: £" + aCalculator.calculateRationCostFreshWeight(), "", 7);
        resultsPanel.add(dryMatterTile);
        resultsPanel.add(crudeProteinTile);
        resultsPanel.add(ndfTile);
        resultsPanel.add(starchAndSugarsTile);
        resultsPanel.add(oilTile);
        resultsPanel.add(rationCostDryMatterTile);
        resultsPanel.add(rationCostFreshWeightTile);
        addComponent(resultsPanel);
    }
    public void generatePDFFile()
    {
        RationCalculator aCalculator = new RationCalculator(rationsWithQuantities, title);
        Report aReport = new Report("rations/1.pdf");
        ArrayList<String> reportContent = new ArrayList<>();
        reportContent.add("Title: " + aCalculator.getTitle());
        reportContent.add("Date: " + new SimpleDateFormat("dd/mm/yyyy hh:mm:ss").format(aCalculator.getDate()));
        Database main = new Database();
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", userID);
        ArrayList<ArrayList<String>> userDetails = main.getTableRows("users", selectedParameters, new ArrayList<>(), "");
        if(userDetails.size() > 0)
        {
            reportContent.add("Printed  By: " + userDetails.get(0).get(3) + " " + userDetails.get(0).get(4));
            reportContent.add("Email Address: " + userDetails.get(0).get(5));
            reportContent.add("Phone Number: " + userDetails.get(0).get(6));
        }
        for(Map.Entry<Ration, Double> aFeedCost : rationsWithQuantities.entrySet())
            reportContent.add(aFeedCost.getKey().getFeed() + " " + aFeedCost.getValue());
        reportContent.add("Total Fresh Intake: " + aCalculator.getTotalFreshIntake());
        reportContent.add("Total Dry Matter Intake: " + aCalculator.getTotalFreshIntake() * (aCalculator.calculateDryMatter() / 100));
        reportContent.addAll(Arrays.asList("Dry Matter: " + aCalculator.calculateDryMatter(), "Crude Protein: " + aCalculator.calculateCrudeProtein(),
        "NDF: " + aCalculator.calculateNDF(), "Starch & Sugars: " + aCalculator.calculateStarchAndSugars(), "Oil: " + aCalculator.calculateOil(), "Dry Matter: £" +
        aCalculator.calculateRationCostDryMatter(), "Fresh Weight: £" + aCalculator.calculateRationCostFreshWeight()));
        aReport.addContent(reportContent);
    }
    public void emailPDFFile()
    {
        if(!new File("rations/1.pdf").exists())
            generatePDFFile();
        Email anEmail = new Email("stephencullinan1991@gmail.com", "TiobraidArann2016");
        Object anEmailAddress = JOptionPane.showInputDialog(null, "Please enter your email address", "Your Email Address",
        JOptionPane.INFORMATION_MESSAGE);
        if(anEmailAddress.toString().length() > 1)
            anEmail.sendMessage(anEmailAddress.toString(), "Ration Report", "Dear Sir/Madam,\n\nPlease find attached the ration report as calculated " +
            "on the application.\n\nYours faithfully,\nStephen Cullinan", "rations/1.pdf", "Ration Report.pdf");
        else
            emailPDFFile();
    }
    public void printPDFFile()
    {
        if(!new File("rations/1.pdf").exists())
            generatePDFFile();
        new Printer("rations/1.pdf");
    }
    public void generateUsersRationsTable(String selectedItemAction)
    {
        Database main = new Database();
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("user", userID);
        ArrayList<ArrayList<String>> usersRationsContents = main.getTableRows("usersrations", selectedParameters,
        new ArrayList<>(Arrays.asList("code", "title")), "");
        ArrayList<String> usersRationsTitles = main.getColumnTitles("usersrations");
        usersRationsTitles.remove(1);
        JTable aTable = addDataToTable(usersRationsTitles, usersRationsContents);
        aTable.getSelectionModel().addListSelectionListener(x ->
        {
            if(selectedItemAction.equals("viewTable"))
            {
                JPanel detailedViewPanel = new JPanel(new GridLayout(1, 1));
                HashMap<String, String> desiredParameters = new HashMap<>();
                desiredParameters.put("usersrations.code", usersRationsContents.get(x.getLastIndex()).get(0));
                ArrayList<ArrayList<String>> tableContents = main.getJoinedTableRows(new ArrayList<>(Arrays.asList("usersrations", "usersrationscomponents", "rations")),
                new ArrayList<>(Arrays.asList("usersrations.code", "usersrationscomponents.userration", "rations.code", "usersrationscomponents.ration")),
                desiredParameters,
                new ArrayList(Arrays.asList("usersrations.title", "rations.feed", "usersrationscomponents.freshweight")),"");
                JTable detailedTable = addDataToTable(new ArrayList<>(Arrays.asList("Title", "Ration", "FreshWeight")), tableContents);
                detailedViewPanel.add(detailedTable);
                addComponent(detailedViewPanel);
            }
            else if(selectedItemAction.equals("deleteTable"))
            {
                int deleteConfirmation = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this row?", "Delete Row",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(deleteConfirmation == JOptionPane.OK_OPTION)
                {
                    //
                    generateUsersRationsTable("deleteTable");
                }
            }
        });
        addComponent(aTable);
    }
    public void generateRationsTable(String selectedItemAction)
    {
        Database main = new Database();
        ArrayList<ArrayList<String>> rationContents = main.getTableRows("rations", new HashMap<>(), new ArrayList<>(), "");
        ArrayList<String> rationTitles = main.getColumnTitles("rations");
        JTable aTable = addDataToTable(rationTitles, rationContents);
        aTable.getSelectionModel().addListSelectionListener(x ->
        {
            if(selectedItemAction.equals("viewTable"))
            {
                JPanel detailedViewPanel = new JPanel(new GridLayout(4, 4));
                for (int counter = 0; counter < rationTitles.size(); counter++)
                {
                    final String rationTitle = rationTitles.get(counter);
                    final String rationDescription = rationContents.get(x.getLastIndex()).get(counter);
                    JButton selectedTile = createTile(rationTitle + ": " + rationDescription, "",4);
                    selectedTile.addActionListener(y -> JOptionPane.showMessageDialog(null, rationDescription, rationTitle,
                    JOptionPane.INFORMATION_MESSAGE));
                    detailedViewPanel.add(selectedTile);
                }
                addComponent(detailedViewPanel);
            }
            else if(selectedItemAction.equals("deleteTable"))
            {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this row?", "Confirm Row Deletion",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.OK_OPTION)
                {
                    HashMap<String, String> selectedParameters = new HashMap<>();
                    selectedParameters.put("code", rationContents.get(x.getLastIndex()).get(0));
                    main.removeTableRow("rations", selectedParameters);
                }
            }
        });
        addComponent(aTable);
    }
    private JTable addDataToTable(ArrayList<String> tableTitles, ArrayList<ArrayList<String>> tableContents)
    {
        JTable aTable = createTable();
        DefaultTableModel model = (DefaultTableModel)aTable.getModel();
        tableTitles.forEach(x -> model.addColumn(x));
        tableContents.forEach(x -> model.addRow(x.toArray()));
        return aTable;
    }
    private void addComponent(JComponent newComponent)
    {
        if(component != null)
            frame.remove(component);
        component = newComponent;
        frame.add(component);
        frame.invalidate();
        frame.revalidate();
    }
    private void refreshComponent()
    {
        frame.invalidate();
        frame.revalidate();
    }
}