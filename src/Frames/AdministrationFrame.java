package Frames;
import Entities.BatchNumber;
import Models.Administration;
import Models.WeighBridge;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.JCommandToggleButton;
import org.pushingpixels.flamingo.api.common.StringValuePair;
import org.pushingpixels.flamingo.api.common.icon.EmptyResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.*;
import org.pushingpixels.flamingo.internal.ui.ribbon.JRibbonGallery;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import Utilities.*;
public class AdministrationFrame extends Components
{
    private JComponent component;
    private JRibbonFrame frame;
    private Administration anAdministration;
    public AdministrationFrame()
    {
        anAdministration = new Administration();
        createRibbon();
    }
    private void createRibbon()
    {
        frame = createRibbonFrame("Administration Portal");
        createReportsTaskOnRibbon();
        createSpecifiedTaskOnRibbon();
        createTaskOnRibbon("Driver", "Drivers", "drivers");
        createTaskOnRibbon("Commodity", "Commodities", "commodities");
        createTaskOnRibbon("First Weight", "First Weights", "firstweights");
        createTaskOnRibbon("Second Weight", "Second Weights", "secondweights");
        createTaskOnRibbon("Administrator", "Administrators", "administrators");
        createTaskOnRibbon("Customer", "Customers", "customers");
        createTaskOnRibbon("Docket Type", "Docket Types", "dockettypes");
        createTaskOnRibbon("Supplier", "Suppliers", "suppliers");
        createTaskOnRibbon("Contract", "Contracts", "contracts");
        createTaskOnRibbon("Setting", "Settings", "settings");
        //createTaskOnRibbon("Batch Number", "Batch Numbers", "batchnumbers");
        JRibbonBand configurationBand = createRibbonBand("Configuration");
        JCommandButton extractInformationFromSpreadsheetButton = createCommandButton("Extract Information From Ration Spreadsheet");
        extractInformationFromSpreadsheetButton.addActionListener(x -> anAdministration.extractInformationFromRationSpreadsheet());
        configurationBand.addCommandButton(extractInformationFromSpreadsheetButton, RibbonElementPriority.TOP);
        JCommandButton extractInformationFromRecknerSpreadsheetButton = createCommandButton("Extract Information From Reckner Spreadsheet");
        extractInformationFromRecknerSpreadsheetButton.addActionListener(x -> anAdministration.extractInformationFromRecknerSpreadsheet());
        configurationBand.addCommandButton(extractInformationFromRecknerSpreadsheetButton, RibbonElementPriority.TOP);
        frame.getRibbon().addTask(createRibbonTask("Configuration", new JRibbonBand[]{configurationBand}));
        JRibbonBand batchNumbersBand = createRibbonBand("Batch Numbers");
        JCommandButton addBatchNumbersForCustomersButton = createCommandButton("Add Batch Numbers For Customers");
        addBatchNumbersForCustomersButton.addActionListener(x -> addBatchNumbers("customers", 0));
        batchNumbersBand.addCommandButton(addBatchNumbersForCustomersButton, RibbonElementPriority.TOP);
        JCommandButton addBatchNumbersForSuppliersButton = createCommandButton("Add Batch Numbers For Suppliers");
        addBatchNumbersForSuppliersButton.addActionListener(x -> addBatchNumbers("suppliers", 0));
        batchNumbersBand.addCommandButton(addBatchNumbersForSuppliersButton, RibbonElementPriority.TOP);
        JCommandButton viewBatchNumbersForCustomersButton = createCommandButton("View Batch Numbers For Customers");
        viewBatchNumbersForCustomersButton.addActionListener(x -> viewBatchNumbers("customers"));
        batchNumbersBand.addCommandButton(viewBatchNumbersForCustomersButton, RibbonElementPriority.TOP);
        JCommandButton viewBatchNumbersForSuppliersButton = createCommandButton("View Batch Numbers For Suppliers");
        viewBatchNumbersForSuppliersButton.addActionListener(x -> viewBatchNumbers("suppliers"));
        batchNumbersBand.addCommandButton(viewBatchNumbersForSuppliersButton, RibbonElementPriority.TOP);
        JCommandButton deleteBatchNumberForCustomersButton = createCommandButton("Delete Batch Number For Customer");
        deleteBatchNumberForCustomersButton.addActionListener(x -> deleteABatchNumber("customers"));
        batchNumbersBand.addCommandButton(deleteBatchNumberForCustomersButton, RibbonElementPriority.TOP);
        JCommandButton deleteBatchNumberForSuppliersButton = createCommandButton("Delete Batch Number For Supplier");
        deleteBatchNumberForSuppliersButton.addActionListener(x -> deleteABatchNumber("suppliers"));
        batchNumbersBand.addCommandButton(deleteBatchNumberForSuppliersButton, RibbonElementPriority.TOP);
        frame.getRibbon().addTask(createRibbonTask("Batch Numbers", new JRibbonBand[]{batchNumbersBand}));
        frame.getRibbon().setApplicationMenu(createApplicationMenu());
        frame.setVisible(true);
        //frame.add(ribbon, BorderLayout.NORTH);
    }
    protected RibbonApplicationMenu createApplicationMenu()
    {
        RibbonApplicationMenu aRibbonApplicationMenu = new RibbonApplicationMenu();
        aRibbonApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Log Out", x -> {SwingUtilities.invokeLater(() ->
        {
            new WeighBridge();
            frame.dispose();
        });}));
        aRibbonApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Exit", x -> System.exit(0)));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Drivers", "Driver", "drivers"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Commodities", "Commodity", "commodities"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("First Weights", "First Weight", "firstweights"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Second Weights", "Second Weight", "secondweights"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Administrators", "Administrator", "administrators"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Customers", "Customer", "customers"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Docket Types", "Docket Type", "dockettypes"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Suppliers", "Supplier", "suppliers"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Contracts", "Contract", "contracts"));
        aRibbonApplicationMenu.addMenuEntry(createRibbonMenuEntry("Settings", "Setting", "settings"));
        return aRibbonApplicationMenu;
    }
    private RibbonApplicationMenuEntryPrimary createRibbonMenuEntry(String pluralWord, String singularWord, String tableName)
    {
        RibbonApplicationMenuEntrySecondary viewMenuEntry = createMinorApplicationMenuEntry("View " + pluralWord, (x) -> viewTableContents(tableName));
        RibbonApplicationMenuEntrySecondary addMenuEntry = createMinorApplicationMenuEntry("Add An " + singularWord, (x) -> addRowToTable(tableName));
        RibbonApplicationMenuEntrySecondary deleteMenuEntry = createMinorApplicationMenuEntry("Delete An " + singularWord, (x) -> deleteRowFromTable(tableName));
        RibbonApplicationMenuEntrySecondary updateMenuEntry = createMinorApplicationMenuEntry("Update An " + singularWord, (x) -> updateRowFromTable(tableName));
        RibbonApplicationMenuEntryPrimary aMenuEntry = createApplicationMenuEntry(pluralWord, (x) -> {});
        aMenuEntry.addSecondaryMenuGroup(pluralWord, new RibbonApplicationMenuEntrySecondary[]{viewMenuEntry, addMenuEntry, deleteMenuEntry, updateMenuEntry});
        return aMenuEntry;
    }
    private void createReportsTaskOnRibbon()
    {
        JRibbonBand aBand = createRibbonBand("Reports");
        JRibbonGallery aGallery = new JRibbonGallery();
        ArrayList<StringValuePair<ArrayList<JCommandToggleButton>>> buttons = new ArrayList<>();
        Map<RibbonElementPriority, Integer> buttonPriorities = new HashMap<>();
        buttonPriorities.put(RibbonElementPriority.LOW, 3);
        buttonPriorities.put(RibbonElementPriority.MEDIUM, 3);
        buttonPriorities.put(RibbonElementPriority.TOP, 3);
        ArrayList<JCommandToggleButton> buttonsGroup = new ArrayList<>();
        for(int counter = 0; counter < 5; counter++)
            buttonsGroup.add(new JCommandToggleButton("A Button " + (counter + 1), new EmptyResizableIcon(16)));
        buttons.add(new StringValuePair<>("Group 1", buttonsGroup));
        buttons.add(new StringValuePair<>("Group 2", buttonsGroup));
        //aBand.addRibbonGallery("Start Date", buttons, buttonPriorities, 3, RibbonElementPriority.TOP);
    }
    private void createSpecifiedTaskOnRibbon()
    {
        JRibbonBand aBand = createRibbonBand("Joined Tables");
        JCommandButton viewSecondWeightsCustomersButton = createCommandButton("View Customers Completed Weighings");
        viewSecondWeightsCustomersButton.addActionListener(x -> createTableWithJoins("secondWeightsCustomers"));
        aBand.addCommandButton(viewSecondWeightsCustomersButton, RibbonElementPriority.TOP);
        JCommandButton viewSecondWeightsSuppliersButton = createCommandButton("View Suppliers Completed Weighings");
        viewSecondWeightsSuppliersButton.addActionListener(x -> createTableWithJoins("secondWeightsSuppliers"));
        aBand.addCommandButton(viewSecondWeightsSuppliersButton, RibbonElementPriority.TOP);
        JCommandButton viewFirstWeightsCustomersButton = createCommandButton("View Customers First Weighings");
        viewFirstWeightsCustomersButton.addActionListener(x -> createTableWithJoins("firstWeightsCustomers"));
        aBand.addCommandButton(viewFirstWeightsCustomersButton, RibbonElementPriority.TOP);
        JCommandButton viewFirstWeightsSuppliersButton = createCommandButton("View Suppliers First Weighings");
        viewFirstWeightsSuppliersButton.addActionListener(x -> createTableWithJoins("firstWeightsSuppliers"));
        aBand.addCommandButton(viewFirstWeightsSuppliersButton, RibbonElementPriority.TOP);
        JCommandButton viewCustomerContractsButton = createCommandButton("View Customer Contracts");
        viewCustomerContractsButton.addActionListener(x -> createTableWithJoins("contractsCustomers"));
        JCommandButton viewSupplierContractsButton = createCommandButton("View Supplier Contracts");
        viewSupplierContractsButton.addActionListener(x -> createTableWithJoins("contractsSuppliers"));
        aBand.addCommandButton(viewCustomerContractsButton, RibbonElementPriority.TOP);
        aBand.addCommandButton(viewSupplierContractsButton, RibbonElementPriority.TOP);
        RibbonTask aTask = createRibbonTask("Joined Tables", new JRibbonBand[]{aBand});
        frame.getRibbon().addTask(aTask);
    }
    private void createTaskOnRibbon(String singularWord, String pluralWord, String tableName)
    {
        JRibbonBand aBand = createRibbonBand(pluralWord);
        JCommandButton viewButton = createCommandButton("View " + pluralWord);
        viewButton.addActionListener(x -> viewTableContents(tableName));
        JCommandButton addButton = createCommandButton("Add An " + singularWord);
        addButton.addActionListener(x -> addRowToTable(tableName));
        JCommandButton deleteButton = createCommandButton("Delete An " + singularWord);
        deleteButton.addActionListener(x -> deleteRowFromTable(tableName));
        JCommandButton updateButton = createCommandButton("Update An " + singularWord);
        updateButton.addActionListener(x -> updateRowFromTable(tableName));
        aBand.addCommandButton(viewButton, RibbonElementPriority.TOP);
        aBand.addCommandButton(addButton, RibbonElementPriority.TOP);
        aBand.addCommandButton(deleteButton, RibbonElementPriority.TOP);
        aBand.addCommandButton(updateButton, RibbonElementPriority.TOP);
        RibbonTask aTask = createRibbonTask(pluralWord, new JRibbonBand[]{aBand});
        frame.getRibbon().addTask(aTask);
    }
    private void createTableWithJoins(String tableType)
    {
        ArrayList<ArrayList<String>> rowContents = new ArrayList<>();
        ArrayList<String> columnTitles = new ArrayList<>();
        if(tableType.equals("secondWeightsCustomers"))
        {
            rowContents = anAdministration.getSecondWeightsCustomersInformation();
            columnTitles = anAdministration.getSecondWeightsColumnTitles();
        }
        else if(tableType.equals("secondWeightsSuppliers"))
        {
            rowContents = anAdministration.getSecondWeightsSuppliersInformation();
            columnTitles = anAdministration.getSecondWeightsColumnTitles();
        }
        else if(tableType.equals("firstWeightsCustomers"))
        {
            rowContents = anAdministration.getFirstWeightsCustomersInformation();
            columnTitles = anAdministration.getFirstWeightsColumnTitles();
        }
        else if(tableType.equals("firstWeightsSuppliers"))
        {
            rowContents = anAdministration.getFirstWeightsSuppliersInformation();
            columnTitles = anAdministration.getFirstWeightsColumnTitles();
        }
        else if(tableType.equals("contractsCustomers"))
        {
            rowContents = anAdministration.getContractsCustomersInformation();
            columnTitles = anAdministration.getContractsColumnTitles();
        }
        else if(tableType.equals("contractsSuppliers"))
        {
            rowContents = anAdministration.getContractsSuppliersInformation();
            columnTitles = anAdministration.getContractsColumnTitles();
        }
        JTable aTable = createTable();
        DefaultTableModel aModel = (DefaultTableModel)aTable.getModel();
        columnTitles.forEach(x -> aModel.addColumn(x));
        rowContents.forEach(x -> aModel.addRow(x.toArray()));
        addComponent(aTable);
    }
    private JTable createTable(String tableName)
    {
        ArrayList<String> columnTitles = anAdministration.getTableTitles(tableName);
        ArrayList<ArrayList<String>> rowContents = anAdministration.getTableContents(tableName);
        JTable aTable = createTable();
        DefaultTableModel aModel = (DefaultTableModel)aTable.getModel();
        columnTitles.forEach(x -> aModel.addColumn(x));
        rowContents.forEach(x -> aModel.addRow(x.toArray()));
        addComponent(aTable);
        return aTable;
    }
    public void viewTableContents(String tableName)
    {
        createTable(tableName);
    }
    public void updateRowFromTable(String tableName)
    {
        JTable aTable = createTable(tableName);
        aTable.getSelectionModel().addListSelectionListener(x ->
        {
            JPanel aPanel = new JPanel(new GridLayout(0, 1));
            ArrayList<String> columnTitles = anAdministration.getTableTitles(tableName);
            ArrayList<ArrayList<String>> selectedRowContents = anAdministration.getSelectedRow(tableName,
            aTable.getModel().getValueAt(aTable.getSelectedRow(), 0).toString());
            ArrayList<JTextField> textFields = new ArrayList<>();
            for(int counter = 0; counter < columnTitles.size(); counter++)
            {
                aPanel.add(createLabel("Please enter a value for " + columnTitles.get(counter)));
                JTextField aTextField = createTextField(selectedRowContents.get(0).get(counter));
                if(counter == 0)
                    aTextField.setEnabled(false);
                textFields.add(aTextField);
                aPanel.add(aTextField);
            }
            JButton aButton = createButton("Update Selected Row");
            aButton.addActionListener(y ->
            {
                HashMap<String, String> updatedParameters = new HashMap<>();
                columnTitles.forEach(z ->
                {
                    int currentPosition = columnTitles.indexOf(z);
                    if(currentPosition > 0)
                        updatedParameters.put(z, textFields.get(currentPosition).getText());
                });
                anAdministration.updateSelectedRow(tableName, updatedParameters, textFields.get(0).getText());
                updateRowFromTable(tableName);
            });
            aPanel.add(aButton);
            addComponent(aPanel);
        });
    }
    public void deleteRowFromTable(String tableName)
    {
        JTable aTable = createTable(tableName);
        aTable.getSelectionModel().addListSelectionListener(x ->
        {
            int userResponse = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this row?", "Confirm Deletion",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(userResponse == JOptionPane.OK_OPTION)
            {
                anAdministration.deleteSelectedRow(tableName, aTable.getModel().getValueAt(aTable.getSelectedRow(), 0).toString());
                deleteRowFromTable(tableName);
            }
        });
    }
    public void addRowToTable(String tableName)
    {
        ArrayList<String> columnTitles = anAdministration.getTableTitles(tableName);
        ArrayList<JTextField> textFields = new ArrayList<>();
        JPanel aPanel = new JPanel(new GridLayout(0, 1));
        columnTitles.forEach(x ->
        {
            JTextField aTextField = createTextField("");
            textFields.add(aTextField);
            aPanel.add(createLabel("Please enter a valid value for " + x));
            aPanel.add(aTextField);
        });
        JButton aButton = createButton("Add New Row");
        aButton.addActionListener(x ->
        {
            ArrayList<String> textEntered = new ArrayList<>();
            textFields.forEach(y -> textEntered.add(y.getText()));
            anAdministration.insertNewRow(tableName, textEntered);
            createTable(tableName);
        });
        aPanel.add(aButton);
        addComponent(aPanel);
    }
    public void editABatchNumber(String consigneeType)
    {
        JTable batchNumbersTable = generateTableForBatchNumbers(consigneeType);

    }
    public void deleteABatchNumber(String consigneeType)
    {
        JTable batchNumbersTable = generateTableForBatchNumbers(consigneeType);
        batchNumbersTable.getSelectionModel().addListSelectionListener(x ->
        {
            int userResponse = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this batch number?", "Batch Number",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(userResponse == JOptionPane.OK_OPTION)
            {
                anAdministration.deleteSelectedRow("batchnumbers",
                batchNumbersTable.getModel().getValueAt(batchNumbersTable.getSelectedRow(), 0).toString());
                deleteABatchNumber(consigneeType);
            }
        });
    }
    public void viewBatchNumbers(String consigneeType)
    {
        addComponent(generateTableForBatchNumbers(consigneeType));
    }
    private JTable generateTableForBatchNumbers(String consigneeType)
    {
        ArrayList<BatchNumber> availableLoads = anAdministration.getLoadsWithBatchNumbers(consigneeType);
        ArrayList<String> columnTitles = new ArrayList<>(Arrays.asList("Code", "Date", "Commodity", "Net Weight", "Batch Number"));
        ArrayList<ArrayList<String>> rowValues = new ArrayList<>();
        availableLoads.forEach(x -> rowValues.add(new ArrayList<>(Arrays.asList(x.getCode() + "", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(
        x.getSecondWeight().getDate()), x.getSecondWeight().getFirstWeight().getCommodity().getTitle(), x.getSecondWeight().getNetWeight() + "", x.getBatchNumber()))));
        JTable aTable = createTable();
        DefaultTableModel aModel = (DefaultTableModel)aTable.getModel();
        columnTitles.forEach(x -> aModel.addColumn(x));
        rowValues.forEach(x -> aModel.addRow(x.toArray()));
        return aTable;
    }
    public void addBatchNumbers(String consigneeType, int currentPosition)
    {
        ArrayList<BatchNumber> availableLoads = anAdministration.getLoadsWithNoBatchNumbers(consigneeType);
        if(availableLoads.size() > currentPosition)
        {
            JPanel aPanel = createPanelForObtainingBatchNumbers(availableLoads.get(currentPosition), currentPosition, availableLoads.size());
            JTextField batchNumberTextField = createTextField("");
            aPanel.add(batchNumberTextField);
            JButton submitButton = createButton("Save Your Batch Number");
            submitButton.addActionListener(x ->
            {
                availableLoads.get(currentPosition).setBatchNumber(batchNumberTextField.getText());
                anAdministration.insertNewBatchNumber(availableLoads.get(currentPosition));
                addBatchNumbers(consigneeType, currentPosition + 1);
            });
            aPanel.add(submitButton);
            addComponent(aPanel);
        }
        else
        {
            JPanel aPanel = new JPanel(new GridLayout(2, 1));
            aPanel.add(createProgressBar(0, availableLoads.size(), availableLoads.size()));
            aPanel.add(createLabel("All Batch Numbers Have Been Inputted Successfully"));
            addComponent(aPanel);
        }
    }
    private JPanel createPanelForObtainingBatchNumbers(BatchNumber currentBatchNumber, int currentPosition, int totalPositions)
    {
        JPanel aPanel = new JPanel(new GridLayout(10, 1));
        aPanel.add(createProgressBar(0, totalPositions, currentPosition));
        aPanel.add(createLabel("Commodity Title"));
        aPanel.add(createDisabledTextField(currentBatchNumber.getSecondWeight().getFirstWeight().getCommodity().getTitle()));
        aPanel.add(createLabel("Date"));
        aPanel.add(createDisabledTextField(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(currentBatchNumber.getSecondWeight().getDate())));
        aPanel.add(createLabel("Net Weight"));
        aPanel.add(createDisabledTextField(currentBatchNumber.getSecondWeight().getNetWeight() + ""));
        aPanel.add(createLabel("Batch Number"));
        return aPanel;
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
}
