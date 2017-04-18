package Frames;
import WeighBridge.*;
import Models.*;
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
        JRibbonBand batchNumbersBand = createRibbonBand("Batch Numbers And Weights");
        JCommandButton addBatchNumbersButton = createCommandButton("Add Batch Numbers");
        addBatchNumbersButton.addActionListener(x -> addBatchNumbers());
        batchNumbersBand.addCommandButton(addBatchNumbersButton, RibbonElementPriority.TOP);
        JCommandButton viewBatchNumbersButton = createCommandButton("View Batch Numbers");
        viewBatchNumbersButton.addActionListener(x -> viewBatchNumbers());
        batchNumbersBand.addCommandButton(viewBatchNumbersButton, RibbonElementPriority.TOP);
        JCommandButton deleteBatchNumberButton = createCommandButton("Delete Batch Number");
        deleteBatchNumberButton.addActionListener(x -> deleteBatchNumbers());
        batchNumbersBand.addCommandButton(deleteBatchNumberButton, RibbonElementPriority.TOP);
        JCommandButton editBatchNumberButton = createCommandButton("Edit Batch Number");
        editBatchNumberButton.addActionListener(x -> editBatchNumbers());
        batchNumbersBand.addCommandButton(editBatchNumberButton, RibbonElementPriority.TOP);
        JCommandButton viewFirstWeightsButton = createCommandButton("View First Weights");
        viewFirstWeightsButton.addActionListener(x -> viewFirstWeights());
        batchNumbersBand.addCommandButton(viewFirstWeightsButton, RibbonElementPriority.TOP);
        JCommandButton deleteFirstWeightsButton = createCommandButton("Delete First Weights");
        deleteFirstWeightsButton.addActionListener(x -> deleteFirstWeights());
        batchNumbersBand.addCommandButton(deleteFirstWeightsButton, RibbonElementPriority.TOP);
        JCommandButton editFirstWeightsButton = createCommandButton("Edit First Weights");
        editFirstWeightsButton.addActionListener(x -> editFirstWeights());
        batchNumbersBand.addCommandButton(editFirstWeightsButton, RibbonElementPriority.TOP);
        JCommandButton viewSecondWeightsButton = createCommandButton("View Second Weights");
        viewSecondWeightsButton.addActionListener(x -> viewSecondWeights());
        batchNumbersBand.addCommandButton(viewSecondWeightsButton, RibbonElementPriority.TOP);
        JCommandButton deleteSecondWeightsButton = createCommandButton("Delete Second Weights");
        deleteSecondWeightsButton.addActionListener(x -> deleteSecondWeights());
        batchNumbersBand.addCommandButton(deleteSecondWeightsButton, RibbonElementPriority.TOP);
        JCommandButton editSecondWeightsButton = createCommandButton("Edit Second Weights");
        editSecondWeightsButton.addActionListener(x -> editSecondWeights());
        batchNumbersBand.addCommandButton(editSecondWeightsButton, RibbonElementPriority.TOP);
        JCommandButton generatePDFFileButton = createCommandButton("Generate PDF File");
        generatePDFFileButton.addActionListener(x -> generatePDFFileForSecondWeights());
        batchNumbersBand.addCommandButton(generatePDFFileButton, RibbonElementPriority.TOP);
        JCommandButton addContractButton = createCommandButton("Add A Contract");
        addContractButton.addActionListener(x -> addContract());
        batchNumbersBand.addCommandButton(addContractButton, RibbonElementPriority.TOP);
        JCommandButton viewContractsButton = createCommandButton("View Contracts");
        viewContractsButton.addActionListener(x -> viewContracts());
        batchNumbersBand.addCommandButton(viewContractsButton, RibbonElementPriority.TOP);
        JCommandButton deleteContractsButton = createCommandButton("Delete Contracts");
        deleteContractsButton.addActionListener(x -> deleteContracts());
        batchNumbersBand.addCommandButton(deleteContractsButton, RibbonElementPriority.TOP);
        JCommandButton editContractsButton = createCommandButton("Edit Contracts");
        editContractsButton.addActionListener(x -> editContract());
        batchNumbersBand.addCommandButton(editContractsButton, RibbonElementPriority.TOP);
        JCommandButton generatePDFFileContractButton = createCommandButton("Generate PDF File For Contract");
        generatePDFFileContractButton.addActionListener(x -> generatePDFFileForContracts());
        batchNumbersBand.addCommandButton(generatePDFFileContractButton, RibbonElementPriority.TOP);
        JCommandButton viewLoadsBasedOnContractButton = createCommandButton("View Loads Based On Contract");
        viewLoadsBasedOnContractButton.addActionListener(x -> viewLoadsBasedOnContract());
        batchNumbersBand.addCommandButton(viewLoadsBasedOnContractButton, RibbonElementPriority.TOP);
        /*JCommandButton addBatchNumbersForCustomersButton = createCommandButton("Add Batch Numbers For Customers");
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
        batchNumbersBand.addCommandButton(deleteBatchNumberForSuppliersButton, RibbonElementPriority.TOP);*/
        frame.getRibbon().addTask(createRibbonTask("Batch Numbers And Weights", new JRibbonBand[]{batchNumbersBand}));
        JRibbonBand settingsBand = createRibbonBand("Settings");
        JCommandButton fontSettingsButton = createCommandButton("Font Settings");
        fontSettingsButton.addActionListener(x -> editFontSettings());
        settingsBand.addCommandButton(fontSettingsButton, RibbonElementPriority.TOP);
        JCommandButton calculatorSettingsButton = createCommandButton("Calculator Settings");
        calculatorSettingsButton.addActionListener(x -> {});
        settingsBand.addCommandButton(calculatorSettingsButton, RibbonElementPriority.TOP);
        JCommandButton emailSettingsButton = createCommandButton("Email Settings");
        emailSettingsButton.addActionListener(x -> {});
        settingsBand.addCommandButton(emailSettingsButton, RibbonElementPriority.TOP);
        JCommandButton serialPortSettingsButton = createCommandButton("Serial Port Settings");
        serialPortSettingsButton.addActionListener(x -> {});
        settingsBand.addCommandButton(serialPortSettingsButton, RibbonElementPriority.TOP);
        frame.getRibbon().addTask(createRibbonTask("Settings", new JRibbonBand[]{settingsBand}));
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
        /*JCommandButton addContractButton = createCommandButton("Add A Contract");
        addContractButton.addActionListener(x -> addNewContract());
        aBand.addCommandButton(addContractButton, RibbonElementPriority.TOP);
        JCommandButton viewExistingContractsButton = createCommandButton("View Existing Contracts");
        viewExistingContractsButton.addActionListener(x -> viewExistingContracts());
        aBand.addCommandButton(viewExistingContractsButton, RibbonElementPriority.TOP);*/
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
    public void editFontSettings()
    {
        HashMap<String, String> existingFontSettings = anAdministration.getFontSettings();
        JPanel viewFontSettingsPanel = new JPanel(new GridLayout(5, 1));
        viewFontSettingsPanel.add(createLabel("Please enter the font size"));
        JTextField fontSizeTextField = createTextField(existingFontSettings.get("Font Size"));
        viewFontSettingsPanel.add(fontSizeTextField);
        viewFontSettingsPanel.add(createLabel("Please enter the font face"));
        JTextField fontFaceTextField = createTextField(existingFontSettings.get("Font Face"));
        viewFontSettingsPanel.add(fontFaceTextField);
        JButton fontSettingsSaveButton = createButton("Save Font Settings");
        fontSettingsSaveButton.addActionListener(x ->
        {
            if(fontSizeTextField.getText().length() > 0 && fontFaceTextField.getText().length() > 0)
                anAdministration.saveFontSettings(fontSizeTextField.getText(), fontFaceTextField.getText());
        });
        viewFontSettingsPanel.add(fontSettingsSaveButton);
        addComponent(viewFontSettingsPanel);
    }
    public void editCalculatorSettings()
    {
        JPanel viewCalculatorSettingsPanel = new JPanel(new GridLayout(5, 1));
        viewCalculatorSettingsPanel.add(createLabel("Please enter the number of places"));
        JTextField numberOfPlacesTextField = createTextField("");
        viewCalculatorSettingsPanel.add(numberOfPlacesTextField);
        viewCalculatorSettingsPanel.add(createLabel("Please enter the currency symbol"));
        JTextField currencySymbolTextField = createTextField("");
        viewCalculatorSettingsPanel.add(currencySymbolTextField);
        JButton calculatorSettingsSaveButton = createButton("Save Calculator Settings");
        calculatorSettingsSaveButton.addActionListener(x ->
        {
            if(numberOfPlacesTextField.getText().length() > 0 && currencySymbolTextField.getText().length() > 0)
                anAdministration.saveCalculatorSettings(numberOfPlacesTextField.getText(), currencySymbolTextField.getText());
        });
        viewCalculatorSettingsPanel.add(calculatorSettingsSaveButton);
        addComponent(viewCalculatorSettingsPanel);
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
    public void viewContractsBasedOnCommodity()
    {

    }
    public void viewContracts()
    {
        JTable contractsTable = createTable();
        DefaultTableModel contractsTableModel = (DefaultTableModel)contractsTable.getModel();
        anAdministration.getContractsTitles().forEach(x -> contractsTableModel.addColumn(x));
        ArrayList<ArrayList<String>> formattedContractsNumbers = anAdministration.getContractsValues();
        formattedContractsNumbers.forEach(x -> contractsTableModel.addRow(x.toArray()));
        addComponent(contractsTable);
    }
    public void deleteContracts()
    {
        JTable contractsTable = createTable();
        DefaultTableModel contractsTableModel = (DefaultTableModel)contractsTable.getModel();
        anAdministration.getContractsTitles().forEach(x -> contractsTableModel.addColumn(x));
        ArrayList<ArrayList<String>> formattedContractsNumbers = anAdministration.getContractsValues();
        formattedContractsNumbers.forEach(x -> contractsTableModel.addRow(x.toArray()));
        contractsTable.getSelectionModel().addListSelectionListener(x ->
        {
            int userResponse = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this row?", "Confirm Deletion", JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
            if(userResponse == JOptionPane.OK_OPTION)
            {
                anAdministration.deleteSelectedRow("contracts", contractsTable.getModel().getValueAt(contractsTable.getSelectedRow(), 0).toString());
                deleteContracts();
            }
        });
        addComponent(contractsTable);
    }
    public void editContract()
    {
        JTable contractsTable = createTable();
        DefaultTableModel contractsTableModel = (DefaultTableModel)contractsTable.getModel();
        anAdministration.getContractsTitles().forEach(x -> contractsTableModel.addColumn(x));
        ArrayList<ArrayList<String>> formattedContractsNumbers = anAdministration.getContractsValues();
        formattedContractsNumbers.forEach(x -> contractsTableModel.addRow(x.toArray()));
        contractsTable.getSelectionModel().addListSelectionListener(x ->
        {
            Contract currentContract = anAdministration.getContract(contractsTableModel.getValueAt(contractsTable.getSelectedRow(), 0).toString());
            JPanel editContractPanel = new JPanel(new GridLayout(9, 2));
            editContractPanel.add(createLabel("Contract ID"));
            JTextField contractID = createDisabledTextField(currentContract.getCode() + "");
            editContractPanel.add(contractID);
            editContractPanel.add(createLabel("Commodity"));
            JComboBox commodities = createDropDown(anAdministration.getCommoditiesValues(), currentContract.getCommodity().getTitle());
            editContractPanel.add(commodities);
            editContractPanel.add(createLabel("Price"));
            JTextField price = createTextField(currentContract.getPrice() + "");
            editContractPanel.add(price);
            editContractPanel.add(createLabel("Total"));
            JTextField total = createTextField(currentContract.getTotal() + "");
            editContractPanel.add(total);
            editContractPanel.add(createLabel("Docket Type"));
            JComboBox docketTypes = createDropDown(anAdministration.getDocketTypesValues(), currentContract.getDocketType().getDocketType());
            editContractPanel.add(docketTypes);
            editContractPanel.add(createLabel("Consignee"));
            final JComboBox consignees = createDropDown(anAdministration.getConsigneesValues(currentContract.getDocketType()));
            editContractPanel.add(consignees);
            docketTypes.addActionListener(y ->
            {
                ArrayList<String> consigneeValues = anAdministration.getConsigneesValues(anAdministration.getDocketTypeByValue(docketTypes.getSelectedItem().toString()));
                consignees.removeAllItems();
                consigneeValues.forEach(z -> consignees.addItem(z));
            });
            editContractPanel.add(createLabel("Start Date"));
            JTextField startDate = createTextField(new SimpleDateFormat("yyyy-MM-dd").format(currentContract.getStartDate()));
            editContractPanel.add(startDate);
            editContractPanel.add(createLabel("End Date"));
            JTextField endDate = createTextField(new SimpleDateFormat("yyyy-MM-dd").format(currentContract.getEndDate()));
            editContractPanel.add(endDate);
            editContractPanel.add(createLabel("Edit Contract"));
            JButton editContractButton = createButton("Edit Contract");
            editContractButton.addActionListener(y ->
            {
                anAdministration.updateContract(contractID.getText(), commodities.getSelectedItem().toString(), price.getText(), total.getText(),
                docketTypes.getSelectedItem().toString(), consignees.getSelectedItem().toString(), startDate.getText(), endDate.getText());
                editContract();
            });
            editContractPanel.add(editContractButton);
            addComponent(editContractPanel);
        });
        addComponent(contractsTable);
    }
    public void addContract()
    {
        JPanel addContractPanel = new JPanel(new GridLayout(9, 2));
        addContractPanel.add(createLabel("Contract ID"));
        JTextField contractID = createDisabledTextField("");
        addContractPanel.add(contractID);
        addContractPanel.add(createLabel("Commodity"));
        JComboBox commodities = createDropDown(anAdministration.getCommoditiesValues());
        addContractPanel.add(commodities);
        addContractPanel.add(createLabel("Price"));
        JTextField price = createTextField("");
        addContractPanel.add(price);
        addContractPanel.add(createLabel("Total"));
        JTextField total = createTextField("");
        addContractPanel.add(total);
        addContractPanel.add(createLabel("Docket Type"));
        JComboBox docketTypes = createDropDown(anAdministration.getDocketTypesValues());
        addContractPanel.add(docketTypes);
        addContractPanel.add(createLabel("Consignee"));
        final JComboBox consignees = createDropDown(new ArrayList<>());
        addContractPanel.add(consignees);
        docketTypes.addActionListener(y ->
        {
            ArrayList<String> consigneeValues = anAdministration.getConsigneesValues(anAdministration.getDocketTypeByValue(docketTypes.getSelectedItem().toString()));
            consignees.removeAllItems();
            consigneeValues.forEach(z -> consignees.addItem(z));
        });
        addContractPanel.add(createLabel("Start Date"));
        JTextField startDate = createTextField("");
        addContractPanel.add(startDate);
        addContractPanel.add(createLabel("End Date"));
        JTextField endDate = createTextField("");
        addContractPanel.add(endDate);
        addContractPanel.add(createLabel("Add Contract"));
        JButton addContractButton = createButton("Add Contract");
        addContractButton.addActionListener(x ->
        {
            anAdministration.insertNewContract(commodities.getSelectedItem().toString(), price.getText(), total.getText(), docketTypes.getSelectedItem().toString(),
            consignees.getSelectedItem().toString(), startDate.getText(), endDate.getText());
            viewContracts();
        });
        addContractPanel.add(addContractButton);
        addComponent(addContractPanel);
    }
    public void viewLoadsBasedOnContract()
    {
        JTable contractsTable = createTable();
        DefaultTableModel contractsTableModel = (DefaultTableModel)contractsTable.getModel();
        anAdministration.getContractsTitles().forEach(x -> contractsTableModel.addColumn(x));
        ArrayList<ArrayList<String>> formattedContractsNumbers = anAdministration.getContractsValues();
        formattedContractsNumbers.forEach(x -> contractsTableModel.addRow(x.toArray()));
        contractsTable.getSelectionModel().addListSelectionListener(x ->
        {
            JTable selectedContractTable = createTable();
            DefaultTableModel selectedContractTableModel = (DefaultTableModel)selectedContractTable.getModel();
            anAdministration.getLoadsBasedOnSelectedContractTitles().forEach(y -> selectedContractTableModel.addColumn(y));
            ArrayList<ArrayList<String>> formattedSelectedContractNumbers = anAdministration.getLoadsBasedOnSelectedContract(
            contractsTableModel.getValueAt(contractsTable.getSelectedRow(), 0).toString());
            formattedSelectedContractNumbers.forEach(y -> selectedContractTableModel.addRow(y.toArray()));
            addComponent(selectedContractTable);
        });
        addComponent(contractsTable);
    }
    public void viewSecondWeights()
    {
        JTable secondWeightsTable = createTable();
        DefaultTableModel secondWeightsTableModel = (DefaultTableModel)secondWeightsTable.getModel();
        anAdministration.getSecondWeightsTitles().forEach(x -> secondWeightsTableModel.addColumn(x));
        ArrayList<ArrayList<String>> formattedSecondWeightsNumbers = anAdministration.getSecondWeightsValues();
        formattedSecondWeightsNumbers.forEach(x -> secondWeightsTableModel.addRow(x.toArray()));
        addComponent(secondWeightsTable);
    }
    public void deleteSecondWeights()
    {
        JTable secondWeightsTable = createTable();
        DefaultTableModel secondWeightsTableModel = (DefaultTableModel)secondWeightsTable.getModel();
        anAdministration.getSecondWeightsTitles().forEach(x -> secondWeightsTableModel.addColumn(x));
        ArrayList<ArrayList<String>> formattedSecondWeightsNumbers = anAdministration.getSecondWeightsValues();
        formattedSecondWeightsNumbers.forEach(x -> secondWeightsTableModel.addRow(x.toArray()));
        secondWeightsTable.getSelectionModel().addListSelectionListener(x ->
        {
            int userResponse = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this row?", "Confirm Deletion",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(userResponse == JOptionPane.OK_OPTION)
            {
                anAdministration.deleteSelectedRow("secondweights", secondWeightsTable.getModel().getValueAt(secondWeightsTable.getSelectedRow(), 0).toString());
                deleteSecondWeights();
            }
        });
        addComponent(secondWeightsTable);
    }
    public void viewFirstWeights()
    {
        JTable firstWeightsTable = createTable();
        DefaultTableModel firstWeightsTableModel = (DefaultTableModel)firstWeightsTable.getModel();
        anAdministration.getFirstWeightsTitles().forEach(x -> firstWeightsTableModel.addColumn(x));
        ArrayList<ArrayList<String>> formattedFirstWeightsNumbers = anAdministration.getFirstWeightsValues();
        formattedFirstWeightsNumbers.forEach(x -> firstWeightsTableModel.addRow(x.toArray()));
        addComponent(firstWeightsTable);
    }
    public void deleteFirstWeights()
    {
        JTable firstWeightsTable = createTable();
        DefaultTableModel firstWeightsTableModel = (DefaultTableModel)firstWeightsTable.getModel();
        anAdministration.getFirstWeightsTitles().forEach(x -> firstWeightsTableModel.addColumn(x));
        ArrayList<ArrayList<String>> formattedFirstWeightsNumbers = anAdministration.getFirstWeightsValues();
        formattedFirstWeightsNumbers.forEach(x -> firstWeightsTableModel.addRow(x.toArray()));
        firstWeightsTable.getSelectionModel().addListSelectionListener(x ->
        {
            int userResponse = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this row?", "Confirm Deletion", JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
            if(userResponse == JOptionPane.OK_OPTION)
            {
                anAdministration.deleteSelectedRow("firstweights", firstWeightsTable.getModel().getValueAt(firstWeightsTable.getSelectedRow(), 0).toString());
                deleteFirstWeights();
            }
        });
        addComponent(firstWeightsTable);
    }
    public void generatePDFFileForContracts()
    {
        JTable contractsTable = createTable();
        DefaultTableModel contractsTableModel = (DefaultTableModel)contractsTable.getModel();
        anAdministration.getContractsTitles().forEach(x -> contractsTableModel.addColumn(x));
        ArrayList<ArrayList<String>> formattedContractsNumbers = anAdministration.getContractsValues();
        formattedContractsNumbers.forEach(x -> contractsTableModel.addRow(x.toArray()));
        contractsTable.getSelectionModel().addListSelectionListener(x ->
        {
            Contract aContract = anAdministration.getContract(contractsTable.getModel().getValueAt(contractsTable.getSelectedRow(), 0).toString());
            JPanel contractActionsPanel = new JPanel(new GridLayout(1, 3));
            JButton generatePDFButton = createButton("Generate PDF");
            generatePDFButton.addActionListener(y ->
            {
                anAdministration.generateContractReport(aContract.getCode() + "");
                generatePDFFileForContracts();
            });
            contractActionsPanel.add(generatePDFButton);
            JButton printPDFButton = createButton("Print PDF");
            printPDFButton.addActionListener(y ->
            {
                anAdministration.printContractReport(aContract.getCode() + "");
                generatePDFFileForContracts();
            });
            contractActionsPanel.add(printPDFButton);
            JButton emailPDFButton = createButton("Email PDF");
            emailPDFButton.addActionListener(y ->
            {
                JPanel emailPDFFilePanel = new JPanel(new GridLayout(3, 1));
                emailPDFFilePanel.add(createLabel("Please enter your email address"));
                JTextField emailTextField = createTextField("");
                emailPDFFilePanel.add(emailTextField);
                JButton emailSubmitButton = createButton("Send Your Email");
                emailSubmitButton.addActionListener(z ->
                {
                    if(emailTextField.getText().length() > 0)
                    {
                        anAdministration.emailContractReport(emailTextField.getText(), aContract.getCode() + "");
                        generatePDFFileForContracts();
                    }
                });
            });
            contractActionsPanel.add(emailPDFButton);
            addComponent(contractActionsPanel);
        });
        addComponent(contractsTable);
    }
    public void generatePDFFileForSecondWeights()
    {
        JTable secondWeightsTable = createTable();
        DefaultTableModel secondWeightsTableModel = (DefaultTableModel)secondWeightsTable.getModel();
        anAdministration.getSecondWeightsTitles().forEach(x -> secondWeightsTableModel.addColumn(x));
        ArrayList<ArrayList<String>> formattedSecondWeightsNumbers = anAdministration.getSecondWeightsValues();
        formattedSecondWeightsNumbers.forEach(x -> secondWeightsTableModel.addRow(x.toArray()));
        secondWeightsTable.getSelectionModel().addListSelectionListener(x ->
        {
            SecondWeight aSecondWeight = anAdministration.getSecondWeight(secondWeightsTable.getModel().getValueAt(secondWeightsTable.getSelectedRow(), 0).toString());
            JPanel secondWeightActions = new JPanel(new GridLayout(1, 3));
            JButton generatePDFButton = createButton("Generate PDF");
            generatePDFButton.addActionListener(y ->
            {
                anAdministration.generateReport(aSecondWeight.getCode() + "");
                generatePDFFileForSecondWeights();
            });
            secondWeightActions.add(generatePDFButton);
            JButton printPDFButton = createButton("Print PDF");
            printPDFButton.addActionListener(y ->
            {
                anAdministration.printReport(aSecondWeight.getCode()+ "");
                generatePDFFileForSecondWeights();
            });
            secondWeightActions.add(printPDFButton);
            JButton emailPDFButton = createButton("Email PDF");
            emailPDFButton.addActionListener(y ->
            {
                JPanel emailPDFFilePanel = new JPanel(new GridLayout(3, 1));
                emailPDFFilePanel.add(createLabel("Please enter your email address"));
                JTextField emailPDFFileTextField = createTextField("");
                emailPDFFilePanel.add(emailPDFFileTextField);
                JButton emailPDFFileSubmitButton = createButton("Email Your PDF File");
                emailPDFFileSubmitButton.addActionListener(z ->
                {
                    if(emailPDFFileTextField.getText().length() > 0)
                    {
                        anAdministration.emailReport(emailPDFFileTextField.getText(), aSecondWeight.getCode() + "");
                        generatePDFFileForSecondWeights();
                    }
                });
                emailPDFFilePanel.add(emailPDFFileSubmitButton);
                addComponent(emailPDFFilePanel);
            });
            secondWeightActions.add(emailPDFButton);
            addComponent(secondWeightActions);
        });
        addComponent(secondWeightsTable);
    }
    public void editSecondWeights()
    {
        JTable secondWeightsTable = createTable();
        DefaultTableModel secondWeightsTableModel = (DefaultTableModel)secondWeightsTable.getModel();
        anAdministration.getSecondWeightsTitles().forEach(x -> secondWeightsTableModel.addColumn(x));
        ArrayList<ArrayList<String>> formattedSecondWeightsNumbers = anAdministration.getSecondWeightsValues();
        formattedSecondWeightsNumbers.forEach(x -> secondWeightsTableModel.addRow(x.toArray()));
        secondWeightsTable.getSelectionModel().addListSelectionListener(x ->
        {
            SecondWeight aSecondWeight = anAdministration.getSecondWeight(secondWeightsTable.getModel().getValueAt(secondWeightsTable.getSelectedRow(), 0).toString());
            JPanel editSecondWeightPanel = new JPanel(new GridLayout(11, 2));
            editSecondWeightPanel.add(createLabel("First Weight ID"));
            JTextField firstWeightCode = createDisabledTextField(aSecondWeight.getFirstWeight().getCode() + "");
            editSecondWeightPanel.add(firstWeightCode);
            editSecondWeightPanel.add(createLabel("Second Weight ID"));
            JTextField secondWeightCode = createDisabledTextField(aSecondWeight.getCode() + "");
            editSecondWeightPanel.add(secondWeightCode);
            editSecondWeightPanel.add(createLabel("Driver"));
            JComboBox drivers = createDropDown(anAdministration.getDriversValues(), aSecondWeight.getFirstWeight().getDriver().getFirstName() + " " +
            aSecondWeight.getFirstWeight().getDriver().getLastName());
            editSecondWeightPanel.add(drivers);
            editSecondWeightPanel.add(createLabel("Commodity"));
            JComboBox commodities = createDropDown(anAdministration.getCommoditiesValues(), aSecondWeight.getFirstWeight().getCommodity().getTitle());
            editSecondWeightPanel.add(commodities);
            editSecondWeightPanel.add(createLabel("First Weight"));
            JTextField firstWeight = createTextField(aSecondWeight.getFirstWeight().getWeight() + "");
            editSecondWeightPanel.add(firstWeight);
            editSecondWeightPanel.add(createLabel("First Weight Date"));
            JTextField firstWeightDate = createTextField(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(aSecondWeight.getFirstWeight().getDate()));
            editSecondWeightPanel.add(firstWeightDate);
            editSecondWeightPanel.add(createLabel("Second Weight"));
            JTextField secondWeight = createTextField(aSecondWeight.getWeight() + "");
            editSecondWeightPanel.add(secondWeight);
            editSecondWeightPanel.add(createLabel("Second Weight Date"));
            JTextField secondWeightDate = createTextField(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(aSecondWeight.getDate()));
            editSecondWeightPanel.add(secondWeightDate);
            editSecondWeightPanel.add(createLabel("Docket Type"));
            JComboBox docketTypes = createDropDown(anAdministration.getDocketTypesValues(), aSecondWeight.getFirstWeight().getDocketType().getDocketType());
            editSecondWeightPanel.add(docketTypes);
            DocketType aDocketType = anAdministration.getDocketTypeByValue(aSecondWeight.getFirstWeight().getDocketType().getDocketType());
            editSecondWeightPanel.add(createLabel("Consignee"));
            final JComboBox consignees = createDropDown(anAdministration.getConsigneesValues(aDocketType), aSecondWeight.getFirstWeight().getConsignee().getFirstName() +
            " " + aSecondWeight.getFirstWeight().getConsignee().getLastName());
            editSecondWeightPanel.add(consignees);
            docketTypes.addActionListener(y ->
            {
                ArrayList<String> consigneeValues = anAdministration.getConsigneesValues(anAdministration.getDocketTypeByValue(docketTypes.getSelectedItem().toString()));
                consignees.removeAllItems();
                consigneeValues.forEach(z -> consignees.addItem(z));
            });
            JButton editSecondWeightButton = createButton("Save Your Changes");
            editSecondWeightButton.addActionListener(y ->
            {
                anAdministration.updateSecondWeight(secondWeightCode.getText(), secondWeight.getText(), secondWeightDate.getText(), firstWeightCode.getText(),
                drivers.getSelectedItem().toString(), commodities.getSelectedItem().toString(), firstWeight.getText(), firstWeightDate.getText(),
                docketTypes.getSelectedItem().toString(), consignees.getSelectedItem().toString());
                editSecondWeights();
            });
            editSecondWeightPanel.add(createLabel("Save Your Changes"));
            editSecondWeightPanel.add(editSecondWeightButton);
            addComponent(editSecondWeightPanel);
        });
        addComponent(secondWeightsTable);
    }
    public void editFirstWeights()
    {
        JTable firstWeightsTable = createTable();
        DefaultTableModel firstWeightsTableModel = (DefaultTableModel)firstWeightsTable.getModel();
        anAdministration.getFirstWeightsTitles().forEach(x -> firstWeightsTableModel.addColumn(x));
        ArrayList<ArrayList<String>> formattedFirstWeightsNumbers = anAdministration.getFirstWeightsValues();
        formattedFirstWeightsNumbers.forEach(x -> firstWeightsTableModel.addRow(x.toArray()));
        firstWeightsTable.getSelectionModel().addListSelectionListener(x ->
        {
            JPanel editFirstWeightPanel = new JPanel(new GridLayout(8, 2));
            editFirstWeightPanel.add(createLabel("First Weight ID"));
            JTextField code = createDisabledTextField(firstWeightsTable.getModel().getValueAt(firstWeightsTable.getSelectedRow(), 0).toString());
            editFirstWeightPanel.add(code);
            editFirstWeightPanel.add(createLabel("Driver"));
            String selectedDriver = firstWeightsTable.getModel().getValueAt(firstWeightsTable.getSelectedRow(), 3).toString();
            JComboBox drivers = createDropDown(anAdministration.getDriversValues(), selectedDriver);
            editFirstWeightPanel.add(drivers);
            editFirstWeightPanel.add(createLabel("Commodity"));
            String selectedCommodity = firstWeightsTable.getModel().getValueAt(firstWeightsTable.getSelectedRow(), 4).toString();
            JComboBox commodities = createDropDown(anAdministration.getCommoditiesValues(), selectedCommodity);
            editFirstWeightPanel.add(commodities);
            editFirstWeightPanel.add(createLabel("First Weight"));
            JTextField firstWeight = createTextField(firstWeightsTable.getModel().getValueAt(firstWeightsTable.getSelectedRow(), 2).toString());
            editFirstWeightPanel.add(firstWeight);
            editFirstWeightPanel.add(createLabel("Date"));
            JTextField date = createTextField(firstWeightsTable.getModel().getValueAt(firstWeightsTable.getSelectedRow(), 1).toString());
            editFirstWeightPanel.add(date);
            String selectedDocketType = firstWeightsTable.getModel().getValueAt(firstWeightsTable.getSelectedRow(), 5).toString();
            editFirstWeightPanel.add(createLabel("Docket Type"));
            JComboBox docketTypes = createDropDown(anAdministration.getDocketTypesValues(), selectedDocketType);
            editFirstWeightPanel.add(docketTypes);
            DocketType aDocketType = anAdministration.getDocketTypeByValue(selectedDocketType);
            editFirstWeightPanel.add(createLabel("Consignee"));
            String selectedConsignee = firstWeightsTable.getModel().getValueAt(firstWeightsTable.getSelectedRow(), 6).toString();
            final JComboBox consignees = createDropDown(anAdministration.getConsigneesValues(aDocketType), selectedConsignee);
            editFirstWeightPanel.add(consignees);
            docketTypes.addActionListener(y ->
            {
                ArrayList<String> consigneeValues = anAdministration.getConsigneesValues(anAdministration.getDocketTypeByValue(docketTypes.getSelectedItem().toString()));
                consignees.removeAllItems();
                consigneeValues.forEach(z -> consignees.addItem(z));
            });
            JButton editFirstWeightButton = createButton("Save Your Changes");
            editFirstWeightButton.addActionListener(y ->
            {
                anAdministration.updateFirstWeight(code.getText(), drivers.getSelectedItem().toString(), commodities.getSelectedItem().toString(), firstWeight.getText(),
                date.getText(), docketTypes.getSelectedItem().toString(), consignees.getSelectedItem().toString());
                editFirstWeights();
            });
            editFirstWeightPanel.add(createLabel("Save Your Changes"));
            editFirstWeightPanel.add(editFirstWeightButton);
            addComponent(editFirstWeightPanel);
        });
        addComponent(firstWeightsTable);
    }
    public void viewBatchNumbers()
    {
        JTable batchNumbersTable = createTable();
        DefaultTableModel batchNumbersTableModel = (DefaultTableModel)batchNumbersTable.getModel();
        anAdministration.getBatchNumberTitles().forEach(x -> batchNumbersTableModel.addColumn(x));
        ArrayList<ArrayList<String>> formattedBatchNumbers = anAdministration.getBatchNumbers();
        formattedBatchNumbers.forEach(x -> batchNumbersTableModel.addRow(x.toArray()));
        addComponent(batchNumbersTable);
    }
    public void deleteBatchNumbers()
    {
        JTable batchNumbersTable = createTable();
        DefaultTableModel batchNumbersTableModel = (DefaultTableModel)batchNumbersTable.getModel();
        anAdministration.getBatchNumberTitles().forEach(x -> batchNumbersTableModel.addColumn(x));
        ArrayList<ArrayList<String>> formattedBatchNumbers = anAdministration.getBatchNumbers();
        formattedBatchNumbers.forEach(x -> batchNumbersTableModel.addRow(x.toArray()));
        batchNumbersTable.getSelectionModel().addListSelectionListener(x ->
        {
            int userResponse = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this row?", "Confirm Deletion", JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
            if(userResponse == JOptionPane.OK_OPTION)
            {
                anAdministration.deleteSelectedRow("batchnumbers", batchNumbersTable.getModel().getValueAt(batchNumbersTable.getSelectedRow(), 0).toString());
                deleteBatchNumbers();
            }
        });
        addComponent(batchNumbersTable);
    }
    public void editBatchNumbers()
    {
        JTable batchNumbersTable = createTable();
        DefaultTableModel batchNumbersTableModel = (DefaultTableModel)batchNumbersTable.getModel();
        anAdministration.getBatchNumberTitles().forEach(x -> batchNumbersTableModel.addColumn(x));
        ArrayList<ArrayList<String>> formattedBatchNumbers = anAdministration.getBatchNumbers();
        formattedBatchNumbers.forEach(x -> batchNumbersTableModel.addRow(x.toArray()));
        batchNumbersTable.getSelectionModel().addListSelectionListener(x ->
        {
            JPanel editBatchNumberPanel = new JPanel(new GridLayout(3, 1));
            editBatchNumberPanel.add(createLabel("Please enter your updated batch number"));
            JTextField currentBatchNumber = createTextField(batchNumbersTable.getModel().getValueAt(batchNumbersTable.getSelectedRow(), 6).toString());
            editBatchNumberPanel.add(currentBatchNumber);
            JButton updateBatchNumberButton = createButton("Update Batch Number");
            editBatchNumberPanel.add(updateBatchNumberButton);
            updateBatchNumberButton.addActionListener(y ->
            {
                BatchNumber updatedBatchNumber = new BatchNumber(
                Integer.parseInt(batchNumbersTable.getModel().getValueAt(batchNumbersTable.getSelectedRow(), 0).toString()),
                currentBatchNumber.getText(), null);
                anAdministration.updateExistingBatchNumber(updatedBatchNumber);
                editBatchNumbers();
            });
            addComponent(editBatchNumberPanel);
        });
        addComponent(batchNumbersTable);
    }
    public void addBatchNumbers()
    {
        ArrayList<SecondWeight> secondWeights = anAdministration.getLoadsWithNoBatchNumbers();
        addBatchNumberForSelectedLoad(secondWeights, 0);
    }
    public void addBatchNumberForSelectedLoad(ArrayList<SecondWeight> secondWeights, int secondWeightPosition)
    {
        if(secondWeights.size() == 0)
            viewBatchNumbers();
        else
        {
            SecondWeight currentSecondWeight = secondWeights.get(secondWeightPosition);
            JPanel addBatchNumberPanel = new JPanel(new GridLayout(12, 1));
            addBatchNumberPanel.add(createProgressBar(0, secondWeights.size(), secondWeightPosition));
            addBatchNumberPanel.add(createLabel("Date"));
            addBatchNumberPanel.add(createTextField(secondWeights.get(secondWeightPosition).getDate().toString()));
            addBatchNumberPanel.add(createLabel("Net Weight"));
            addBatchNumberPanel.add(createTextField(secondWeights.get(secondWeightPosition).getNetWeight() + ""));
            addBatchNumberPanel.add(createLabel("Consignee"));
            addBatchNumberPanel.add(createTextField(secondWeights.get(secondWeightPosition).getFirstWeight().getConsignee().getFirstName() + " " +
            secondWeights.get(secondWeightPosition).getFirstWeight().getConsignee().getLastName()));
            addBatchNumberPanel.add(createLabel("Commodity Title"));
            addBatchNumberPanel.add(createTextField(secondWeights.get(secondWeightPosition).getFirstWeight().getCommodity().getTitle()));
            addBatchNumberPanel.add(createLabel("Your Batch Number"));
            JTextField batchNumberTextField = createTextField("");
            addBatchNumberPanel.add(batchNumberTextField);
            JButton saveBatchNumber = createButton("Save Batch Number");
            saveBatchNumber.addActionListener(x ->
            {
                if (batchNumberTextField.getText().length() > 0)
                {
                    int currentSecondWeightPosition = secondWeightPosition;
                    anAdministration.insertNewBatchNumber(new BatchNumber(batchNumberTextField.getText(), secondWeights.get(currentSecondWeightPosition)));
                    currentSecondWeightPosition++;
                    if (currentSecondWeightPosition == secondWeights.size())
                        viewBatchNumbers();
                    else
                        addBatchNumberForSelectedLoad(secondWeights, currentSecondWeightPosition);
                }
            });
            addBatchNumberPanel.add(saveBatchNumber);
            addComponent(addBatchNumberPanel);
        }
    }
    /*public void viewSecondWeights()
    {
        ArrayList<ArrayList<String>>
    }*/
    /*public void editABatchNumber(String consigneeType)
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
    }*/
    private void addComponent(JComponent aComponent)
    {
        if(component != null)
            frame.remove(component);
        component = aComponent;
        frame.add(component, BorderLayout.CENTER);
        frame.invalidate();
        frame.revalidate();
    }
    private void refreshFrame()
    {
        frame.invalidate();
        frame.revalidate();
    }
    /*private void viewExistingContracts()
    {
        HashSet<String> availableCommodities = anAdministration.getCommoditiesFromContracts();
        JPanel aPanel = new JPanel(new GridLayout(availableCommodities.size() / 3, 3));
        for(String aCommodity: availableCommodities)
        {
            JButton aCommodityButton = createButton(aCommodity);
            aCommodityButton.addActionListener(x ->
            {
                ArrayList<ArrayList<String>> contractsForSpecifiedCommodity = anAdministration.getContractsForSpecifiedCommodity(aCommodity);
                ArrayList<String> contractsForSpecifiedCommodityHeading = anAdministration.getContractsForSpecifiedCommodityHeading();
                JTable commoditiesTable = createTable();
                DefaultTableModel commoditiesTableModel = (DefaultTableModel)commoditiesTable.getModel();
                contractsForSpecifiedCommodityHeading.forEach(y -> commoditiesTableModel.addColumn(y));
                contractsForSpecifiedCommodity.forEach(y -> commoditiesTableModel.addRow(y.toArray()));
                addComponent(commoditiesTable);
            });
            aPanel.add(aCommodityButton);
        }
        JButton viewAllCommoditiesButton = createButton("View All Commodities");
        viewAllCommoditiesButton.addActionListener(x ->
        {
            ArrayList<ArrayList<String>> commodities = anAdministration.getTableContents("commodities");
            JPanel allCommoditiesPanel = new JPanel(new GridLayout(commodities.size(), 1));
            for(ArrayList<String> aCommodity : commodities)
            {
                System.out.println("COMMODITY TITLE: " + aCommodity.get(1));
                ArrayList<ArrayList<String>> contractsForSpecifiedCommodity = anAdministration.getContractsForSpecifiedCommodity(aCommodity.get(1));
                if(contractsForSpecifiedCommodity.size() > 1)
                {
                    ArrayList<String> contractsForSpecifiedCommodityHeading = anAdministration.getContractsForSpecifiedCommodityHeading();
                    JTable commoditiesTable = createTable();
                    DefaultTableModel commoditiesTableModel = (DefaultTableModel)commoditiesTable.getModel();
                    contractsForSpecifiedCommodityHeading.forEach(y -> commoditiesTableModel.addColumn(y));
                    contractsForSpecifiedCommodity.forEach(y -> commoditiesTableModel.addRow(y.toArray()));
                    allCommoditiesPanel.add(createLabel(aCommodity.get(1)));
                    allCommoditiesPanel.add(commoditiesTable);
                }
            }
            addComponent(allCommoditiesPanel);
        });
        aPanel.add(viewAllCommoditiesButton);
        addComponent(aPanel);
    }
    private void addNewContract()
    {
        JPanel aPanel = new JPanel(new GridLayout(9, 2));
        aPanel.add(createLabel("Commodity Title"));
        ArrayList<String> possibleCommodityTypes = new ArrayList<>();
        ArrayList<ArrayList<String>> tableContents = anAdministration.getTableContents("commodities");
        tableContents.forEach(x -> possibleCommodityTypes.add(x.get(1)));
        JComboBox commodity = createDropDown(possibleCommodityTypes);
        aPanel.add(commodity);
        aPanel.add(createLabel("Price"));
        JTextField contractPrice = createTextField("");
        aPanel.add(contractPrice);
        aPanel.add(createLabel("Contract Total"));
        JTextField contractTotal = createTextField("");
        aPanel.add(contractTotal);
        aPanel.add(createLabel("Docket Type"));
        ArrayList<String> possibleDocketTypes = new ArrayList<>();
        tableContents =  anAdministration.getTableContents("dockettypes");
        tableContents.forEach(x -> possibleDocketTypes.add(x.get(1)));
        JComboBox docketType = createDropDown(possibleDocketTypes);
        aPanel.add(docketType);
        JComboBox consignee = createDropDown(new ArrayList());
        docketType.addActionListener(x ->
        {
            if(docketType.getSelectedItem().toString().equals("Purchase"))
            {
                System.out.println("Purchase");
                ArrayList<ArrayList<String>> suppliers = anAdministration.getTableContents("suppliers");
                ArrayList<String> suppliersNames = new ArrayList<>();
                suppliers.forEach(y -> suppliersNames.add(y.get(1) + " " + y.get(2)));
                suppliersNames.forEach(z -> consignee.addItem(z));
            }
            else if(docketType.getSelectedItem().toString().equals("Sale"))
            {
                System.out.println("Sale");
                ArrayList<ArrayList<String>> customers = anAdministration.getTableContents("customers");
                ArrayList<String> customersNames = new ArrayList<>();
                customers.forEach(y -> customersNames.add(y.get(1) + " " + y.get(2)));
                customersNames.forEach(z -> consignee.addItem(z));
            }
            refreshFrame();
        });
        aPanel.add(createLabel("Consignee"));
        aPanel.add(consignee);
        aPanel.add(createLabel("Start Date"));
        JTextField startDate = createTextField("");
        aPanel.add(startDate);
        aPanel.add(createLabel("End Date"));
        JTextField endDate = createTextField("");
        aPanel.add(endDate);
        JButton submitButton = createButton("Add Your Contract");
        submitButton.addActionListener(x ->
        {
            anAdministration.insertNewContract(commodity.getSelectedItem().toString(), Double.parseDouble(contractTotal.getText()),
            Double.parseDouble(contractPrice.getText()), docketType.getSelectedItem().toString(), consignee.getSelectedItem().toString(), startDate.getText(),
            endDate.getText());
            if(docketType.getSelectedItem().toString().equals("Purchase"))
                createTableWithJoins("contractsCustomers");
            else if(docketType.getSelectedItem().toString().equals("Sale"))
                createTableWithJoins("contractsSuppliers");
        });
        aPanel.add(submitButton);
        addComponent(aPanel);
    }*/
}