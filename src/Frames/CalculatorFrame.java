package Frames;
import Entities.Ration;
import Entities.RationCalculator;
import Models.Calculator;
import Models.Portal;
import Models.WeighBridge;
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
public class CalculatorFrame extends Components
{
    private JRibbonFrame frame;
    private JComponent component;
    private Calculator aCalculator;
    private HashMap<Ration, Double> rationsWithQuantities;
    private String title;
    private String rationType;
    private String userID;
    public CalculatorFrame(String userID)
    {
        rationsWithQuantities = new HashMap<>();
        title = "";
        this.userID = userID;
        this.rationType = "";
        aCalculator = new Calculator();
        createRibbon();
    }
    private void createRibbon()
    {
        frame = createRibbonFrame("Calculator");
        JRibbonBand rationsBand = createRibbonBand("Rations");
        JCommandButton createNewRationButton = createCommandButton("Create New Ration");
        createNewRationButton.addActionListener(x -> createCalculatorDialogBoxStepOne());
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
        generatePDFFileButton.addActionListener(x -> aCalculator.generatePDFFile(rationsWithQuantities, title, userID));
        quickActionsBand.addCommandButton(generatePDFFileButton, RibbonElementPriority.TOP);
        JCommandButton emailPDFFileButton = createCommandButton("Email PDF File");
        emailPDFFileButton.addActionListener(x -> emailPDFFile());
        quickActionsBand.addCommandButton(emailPDFFileButton, RibbonElementPriority.TOP);
        JCommandButton printPDFFileButton = createCommandButton("Print PDF File");
        printPDFFileButton.addActionListener(x -> aCalculator.printPDFFile(rationsWithQuantities, userID, title));
        quickActionsBand.addCommandButton(printPDFFileButton, RibbonElementPriority.TOP);
        RibbonTask quickActionsTask = createRibbonTask("Quick Actions", new JRibbonBand[]{quickActionsBand});
        JRibbonBand recknerBand = createRibbonBand("Reckner Formulations");
        JCommandButton calculateARecknerFormulationButton = createCommandButton("Calculate A Reckner Formulation");
        calculateARecknerFormulationButton.addActionListener(x -> {});
        recknerBand.addCommandButton(calculateARecknerFormulationButton, RibbonElementPriority.TOP);
        JCommandButton viewReckerRations = createCommandButton("View Recker Rations");
        viewReckerRations.addActionListener(x -> {});
        recknerBand.addCommandButton(viewReckerRations, RibbonElementPriority.TOP);
        JCommandButton deleteARecknerRation = createCommandButton("Delete A Reckner Ration");
        deleteARecknerRation.addActionListener(x -> {});
        recknerBand.addCommandButton(deleteARecknerRation, RibbonElementPriority.TOP);
        JRibbonBand recknerCommoditiesBand = createRibbonBand("Reckner Commodities");
        JCommandButton viewRecknerCommodities = createCommandButton("View Reckner Commodities");
        viewRecknerCommodities.addActionListener(x -> {});
        recknerCommoditiesBand.addCommandButton(viewRecknerCommodities, RibbonElementPriority.TOP);
        JCommandButton deleteARecknerCommodity = createCommandButton("Delete A Reckner Commodity");
        deleteARecknerCommodity.addActionListener(x -> {});
        recknerCommoditiesBand.addCommandButton(deleteARecknerCommodity, RibbonElementPriority.TOP);
        RibbonTask recknerTask = createRibbonTask("Reckner Formulations", new JRibbonBand[]{recknerBand});
        RibbonTask reckerCommoditiesTask = createRibbonTask("Reckner Commodities", new JRibbonBand[]{recknerCommoditiesBand});
        frame.getRibbon().addTask(rationsTask);
        frame.getRibbon().addTask(commoditiesTask);
        frame.getRibbon().addTask(quickActionsTask);
        frame.getRibbon().addTask(reckerCommoditiesTask);
        frame.getRibbon().addTask(recknerTask);
        RibbonApplicationMenu anApplicationMenu = new RibbonApplicationMenu();
        for(int counter = 0; counter < 7; counter++)
        {
            RibbonApplicationMenuEntryPrimary anApplicationMenuEntry = createApplicationMenuEntry("Primary", x -> {});
            RibbonApplicationMenuEntrySecondary aSecondaryApplicationMenuEntry = createMinorApplicationMenuEntry("Secondary", x -> {});
            anApplicationMenuEntry.addSecondaryMenuGroup("Group", new RibbonApplicationMenuEntrySecondary[]{aSecondaryApplicationMenuEntry,
                    aSecondaryApplicationMenuEntry, aSecondaryApplicationMenuEntry, aSecondaryApplicationMenuEntry, aSecondaryApplicationMenuEntry});
            anApplicationMenu.addMenuEntry(anApplicationMenuEntry);
        }
        anApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Return To Main Menu", x -> {new PortalFrame(userID); frame.dispose();}));
        anApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Log Out", x -> {new WeighBridgeFrame(); frame.dispose();}));
        anApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Exit", x -> System.exit(0)));
        frame.getRibbon().setApplicationMenu(anApplicationMenu);
        frame.setVisible(true);
    }
    public void createRecknerDialogBoxStepOne()
    {
        JPanel mainPanel = new JPanel(new GridLayout(5, 1));
        JProgressBar progressBar = createProgressBar(0, 100, 50);
        mainPanel.add(progressBar);
        JTextField titleTextField = createTextField("");
        mainPanel.add(createLabel("Please enter the title of the ration"));
        mainPanel.add(titleTextField);
        JButton saveCalculationButton = createButton("Proceed To Step 2");
        saveCalculationButton.addActionListener(x ->
        {
            if(titleTextField.getText().length() > 1)
            {
                title = titleTextField.getText();
                createRecknerDialogBoxStepTwo();
            }
        });
        addComponent(mainPanel);
    }
    public void createRecknerDialogBoxStepTwo()
    {
        JPanel mainPanel = new JPanel(new GridLayout(5, 1));
        JProgressBar progressBar = createProgressBar(0, 100, 100);

    }
    public void createCalculatorDialogBoxStepOne()
    {
        JPanel mainPanel = new JPanel(new GridLayout(5, 1));
        JProgressBar progressBar = createProgressBar(0, 100);
        progressBar.setValue(50);
        mainPanel.add(progressBar);
        JTextField titleTextField = createTextField("");
        mainPanel.add(createLabel("Please enter the title of the ration"));
        mainPanel.add(titleTextField);
        JComboBox aDropDownBox = createDropDown(aCalculator.getAvailableRationTypes());
        mainPanel.add(aDropDownBox);
        JButton saveCalculationButton = createButton("Proceed To Step 2");
        saveCalculationButton.addActionListener(x ->
        {
            if(titleTextField.getText().length() > 1)
            {
                title = titleTextField.getText();
                rationType = aDropDownBox.getSelectedItem().toString();
                createCalculatorDialogBoxStepTwo();
            }
        });
        mainPanel.add(saveCalculationButton);
        addComponent(mainPanel);
    }
    public void createCalculatorDialogBoxStepTwo()
    {
        JPanel mainPanel = new JPanel(new GridLayout(5, 1));
        JPanel tilesPanel = new JPanel(new GridLayout(5, 1));
        JPanel textBoxPanel = new JPanel(new GridLayout(3, 1));
        JProgressBar progressBar = createProgressBar(0, 100);
        progressBar.setValue(100);
        ArrayList<Ration> availableRations = aCalculator.getAvailableRations();
        JComboBox aDropDownBox = createDropDown(new ArrayList<>());
        availableRations.forEach(x -> aDropDownBox.addItem(x));
        aDropDownBox.addItemListener(x ->
        {
            while(textBoxPanel.getComponents().length > 0)
                textBoxPanel.remove(0);
            JTextField aTextField = createTextField("");
            textBoxPanel.add(createLabel("The kg per day fresh weight for " + x.getItem()));
            textBoxPanel.add(aTextField);
            JButton aButton = createTile("Save Feed", "", 8);
            aButton.addActionListener(y ->
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
            if(rationsWithQuantities.size() > 0)
                createResultsDialogBox();
        });
        mainPanel.add(progressBar);
        mainPanel.add(aDropDownBox);
        mainPanel.add(textBoxPanel);
        mainPanel.add(tilesPanel);
        mainPanel.add(calculateYourRationTile);
        addComponent(mainPanel);
    }
    public void createResultsDialogBox()
    {
        aCalculator.insertNewRationCalculation(userID, title, rationsWithQuantities);
        ArrayList<String> calculatorResults = aCalculator.getCalculationResults(rationsWithQuantities, title);
        JPanel resultsPanel = new JPanel(new GridLayout(4, 2));
        calculatorResults.forEach(x -> resultsPanel.add(createTile(x, "", 7)));
        addComponent(resultsPanel);
    }
    public void emailPDFFile()
    {
        Object anEmailAddress = JOptionPane.showInputDialog(null, "Please enter your email address", "Your Email Address",
        JOptionPane.INFORMATION_MESSAGE);
        if(anEmailAddress.toString().length() > 1)
            aCalculator.emailPDFFile(rationsWithQuantities, title, userID, anEmailAddress.toString());
        else
            emailPDFFile();
    }
    public void generateUsersRationsTable(String selectedItemAction)
    {
        ArrayList<ArrayList<String>> usersRationsContents = aCalculator.getUsersRations(userID);
        ArrayList<String> usersRationsTitles = aCalculator.getUsersRationsTitles();
        JTable aTable = addDataToTable(usersRationsTitles, usersRationsContents);
        aTable.getSelectionModel().addListSelectionListener(x ->
        {
            if(selectedItemAction.equals("viewTable"))
            {
                JPanel detailedViewPanel = new JPanel(new GridLayout(1, 1));
                JTable detailedTable = addDataToTable(new ArrayList<>(Arrays.asList("Title", "Ration", "FreshWeight")),
                aCalculator.getUsersRationsComponents(usersRationsContents.get(x.getLastIndex()).get(0)));
                detailedViewPanel.add(detailedTable);
                addComponent(detailedViewPanel);
            }
            else if(selectedItemAction.equals("deleteTable"))
            {
                int deleteConfirmation = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this row?", "Delete Row",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(deleteConfirmation == JOptionPane.OK_OPTION)
                {
                    aCalculator.removeUserRation(usersRationsContents.get(x.getLastIndex()).get(0));
                    generateUsersRationsTable("deleteTable");
                }
            }
        });
        addComponent(aTable);
    }
    public void generateRationsTable(String selectedItemAction)
    {
        ArrayList<ArrayList<String>> rationContents = aCalculator.getTableContents("rations");
        ArrayList<String> rationTitles = aCalculator.getTableColumnTitles("rations");
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
                    aCalculator.removeRation(rationContents.get(x.getLastIndex()).get(0));
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
}