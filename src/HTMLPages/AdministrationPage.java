package HTMLPages;
import HTMLControls.*;
import Models.Administration;
import Utilities.*;
import WeighBridge.*;
import com.teamdev.jxbrowser.chromium.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class AdministrationPage
{
    private Administration administration;
    public AdministrationPage()
    {
        administration = new Administration();
    }
    public JSONString createAdministrationPage()
    {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", createControlForAdministrationPage().toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public MetroLayout createControlForAdministrationPage()
    {
        MetroLayout administrationPageLayout = new MetroLayout();
        //MetroFluentMenu administrationFluentMenu = new MetroFluentMenu("administrationFluentMenu", "Main Menu", "getAdministrationMainMenu();",
        //new ArrayList<>(Arrays.asList("Drivers & Commodities", "Weights", "Users", "Consignees", "Docket Types & Contracts", "Settings & Batch Numbers")));
        MetroFluentMenu administrationFluentMenu = new MetroFluentMenu("administrationFluentMenu", "Main Menu", "getAdministrationMainMenu();",
        new ArrayList<>(Collections.singletonList("Drivers & Commodities")));
        MetroFluentMenuPanelGroup driversMenuGroup = createMenuGroup("drivers", "Drivers", "Driver");
        MetroFluentMenuPanelGroup commoditiesMenuGroup = createMenuGroup("commodities", "Commodities", "Commodity");
        administrationFluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(driversMenuGroup, commoditiesMenuGroup)));
        /*administrationFluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(firstWeightsMenuGroup, secondWeightsMenuGroup)));
        administrationFluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(administratorsMenuGroup, usersMenuGroup)));
        administrationFluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(customersMenuGroup, suppliersMenuGroup)));
        administrationFluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(docketTypesMenuGroup, contractsMenuGroup)));
        administrationFluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(settingsMenuGroup, batchNumbersMenuGroup)));*/
        MetroUpdatePanel administrationFluentMenuUpdatePanel = new MetroUpdatePanel("administrationFluentMenuUpdatePanel", administrationFluentMenu);
        administrationPageLayout.addRow(administrationFluentMenuUpdatePanel);
        administrationPageLayout.addRow(new MetroUpdatePanel("administrationPanel", null));
        return administrationPageLayout;
    }
    public JSONString createFluentMenu(int selectedInteger)
    {
        /*ArrayList<MetroFluentButton> fluentMenuButtons = new ArrayList<>();
        fluentMenuButtons.add(new MetroFluentButton("Mail Settings", "cog", ""));
        MetroFluentMenuPanelGroup settingsMenuGroup = new MetroFluentMenuPanelGroup("Settings", fluentMenuButtons);
        fluentMenuButtons.clear();*/
        MetroFluentMenu fluentMenu = null;
        if(selectedInteger == 0)
        {
            ArrayList<MetroFluentButton> fluentMenuButtons = new ArrayList<>();
            fluentMenuButtons.add(new MetroFluentButton("Mail Settings", "cog", "createEmailSettings();"));
            fluentMenuButtons.add(new MetroFluentButton("Serial Port Settings", "cog", ""));
            MetroFluentMenuPanelGroup settingsMenuGroup = new MetroFluentMenuPanelGroup("Settings", fluentMenuButtons);
            fluentMenu = new MetroFluentMenu("administrationFluentMenu", "Main Menu", "getAdministrationMainMenu();",
            new ArrayList<>(Collections.singletonList("Settings")));
            fluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(settingsMenuGroup)));
        }
        else if(selectedInteger == 1)
        {
            MetroFluentMenuPanelGroup customersMenuGroup = createMenuGroup("customers", "Customers", "Customer");
            MetroFluentMenuPanelGroup suppliersMenuGroup = createMenuGroup("suppliers", "Suppliers", "Supplier");
            fluentMenu = new MetroFluentMenu("administrationFluentMenu", "Main Menu", "getAdministrationMainMenu();",
            new ArrayList<>(Collections.singletonList("Customers & Suppliers")));
            fluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(customersMenuGroup, suppliersMenuGroup)));
        }
        else if(selectedInteger == 2)
        {
            MetroFluentMenuPanelGroup firstWeightsMenuGroup = createMenuGroup("firstweights", "First Weights", "First Weight");
            MetroFluentMenuPanelGroup secondWeightsMenuGroup = createMenuGroup("secondweights", "Second Weights", "Second Weight");
            fluentMenu = new MetroFluentMenu("administrationFluentMenu", "Main Menu", "getAdministrationMainMenu();",
            new ArrayList<>(Collections.singletonList("First & Second Weights")));
            fluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(firstWeightsMenuGroup, secondWeightsMenuGroup)));
        }
        else if(selectedInteger == 3)
        {
            MetroFluentMenuPanelGroup administratorsMenuGroup = createMenuGroup("administrators", "Administrators", "Administrator");
            MetroFluentMenuPanelGroup usersMenuGroup = createMenuGroup("users", "Users", "User");
            fluentMenu = new MetroFluentMenu("administrationFluentMenu", "Main Menu", "getAdministrationMainMenu();",
            new ArrayList<>(Collections.singletonList("Administrators & Users")));
            fluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(administratorsMenuGroup, usersMenuGroup)));
        }
        else if(selectedInteger == 4)
        {
            MetroFluentMenuPanelGroup customersMenuGroup = createMenuGroup("customers", "Customers", "Customer");
            MetroFluentMenuPanelGroup suppliersMenuGroup = createMenuGroup("suppliers", "Suppliers", "Supplier");
            fluentMenu = new MetroFluentMenu("administrationFluentMenu", "Main Menu", "getAdministrationMainMenu();",
            new ArrayList<>(Collections.singletonList("Customers & Suppliers")));
            fluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(customersMenuGroup, suppliersMenuGroup)));
        }
        else if(selectedInteger == 5)
        {
            MetroFluentMenuPanelGroup contractsMenuGroup = createMenuGroup("contracts", "Contracts", "Contract");
            MetroFluentMenuPanelGroup docketTypesMenuGroup = createMenuGroup("dockettypes", "Docket Types", "Docket Type");
            fluentMenu = new MetroFluentMenu("administrationFluentMenu", "Main Menu", "getAdministrationMainMenu();",
            new ArrayList<>(Collections.singletonList("Contracts & Docket Types")));
            fluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(contractsMenuGroup, docketTypesMenuGroup)));
        }
        else if(selectedInteger == 6)
        {
            MetroFluentMenuPanelGroup batchNumbersMenuGroup = createMenuGroup("batchnumbers", "Batch Numbers", "Batch Number");
            MetroFluentMenuPanelGroup settingsMenuGroup = createMenuGroup("settings", "Settings", "Setting");
            fluentMenu = new MetroFluentMenu("administrationFluentMenu", "Main Menu", "getAdministrationMainMenu();",
            new ArrayList<>(Collections.singletonList("Batch Numbers & Settings")));
            fluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(batchNumbersMenuGroup, settingsMenuGroup)));
        }
        else if(selectedInteger == 7)
        {
            ArrayList<MetroFluentButton> fluentMenuButtons = new ArrayList<>();
            fluentMenuButtons.add(new MetroFluentButton("Add A Contract", "plus", "addContract();"));
            fluentMenuButtons.add(new MetroFluentButton("View Contracts", "database", "viewContracts();"));
            fluentMenuButtons.add(new MetroFluentButton("Delete A Contract", "bin", "deleteContracts();"));
            fluentMenuButtons.add(new MetroFluentButton("Edit A Contract", "pencil", "editContracts();"));
            fluentMenuButtons.add(new MetroFluentButton("Generate PDF File For Contract", "file-pdf", "pdfForSelectedContract();"));
            MetroFluentMenuPanelGroup contractsMenuGroup = new MetroFluentMenuPanelGroup("Contracts", fluentMenuButtons);
            fluentMenu = new MetroFluentMenu("administrationFluentMenu", "Main Menu", "getAdministrationMainMenu();",
            new ArrayList<>(Collections.singletonList("Contracts")));
            fluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(contractsMenuGroup)));
        }
        MetroLayout fluentMenuLayout = new MetroLayout();
        if(fluentMenu != null)
            fluentMenuLayout.addRow(fluentMenu);
        fluentMenuLayout.addRow(new MetroUpdatePanel("administrationPanel", null));
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", fluentMenuLayout.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createEmailSettings(String messageContent)
    {
        Email anEmail = administration.getEmailSettings();
        MetroLayout emailSettingsLayout = new MetroLayout();
        if(messageContent.length() > 0)
        {
            emailSettingsLayout.addEmptyRows(2);
            emailSettingsLayout.addRow(new MetroPopover(messageContent, "cyan", "top"), new ArrayList<>(Arrays.asList(2, 8, 2)));
            emailSettingsLayout.addEmptyRows(2);
        }
        MetroTextField usernameTextField = new MetroTextField("Please enter the username", "user", "text", "mailUsername",
        anEmail.getUsername());
        MetroPanel mailUsernamePanel = new MetroPanel("Please enter the username", "", "user", usernameTextField);
        emailSettingsLayout.addRow(mailUsernamePanel);
        emailSettingsLayout.addEmptyRows(2);
        MetroTextField passwordTextField = new MetroTextField("Please enter the password", "lock", "password", "mailPassword",
        anEmail.getPassword());
        MetroPanel mailPasswordPanel = new MetroPanel("Please enter the password", "", "lock", passwordTextField);
        emailSettingsLayout.addRow(mailPasswordPanel);
        emailSettingsLayout.addEmptyRows(2);
        MetroTextField smtpAuthTextField = new MetroTextField("Please enter the smtp auth", "mail-read", "text", "mailSmtpAuth",
        anEmail.getSmtpAuth());
        MetroPanel smtpAuthPanel = new MetroPanel("Please enter the smtp auth", "", "mail-read", smtpAuthTextField);
        emailSettingsLayout.addRow(smtpAuthPanel);
        emailSettingsLayout.addEmptyRows(2);
        MetroTextField smtpStartTlsEnableTextField = new MetroTextField("Please enter the smtp start tls enable", "mail-read", "text",
        "mailSmtpStartTlsEnable", anEmail.getSmtpStartTlsEnable());
        MetroPanel smtpStartTlsEnablePanel = new MetroPanel("Please enter the smtp start tls enable", "", "mail-read", smtpStartTlsEnableTextField);
        emailSettingsLayout.addRow(smtpStartTlsEnablePanel);
        emailSettingsLayout.addEmptyRows(2);
        MetroTextField smtpHostTextField = new MetroTextField("Please enter the smtp host", "mail-read", "text",
        "mailSmtpHost", anEmail.getSmtpHost());
        MetroPanel smtpHostPanel = new MetroPanel("Please enter the smtp host", "", "mail-read", smtpHostTextField);
        emailSettingsLayout.addRow(smtpHostPanel);
        emailSettingsLayout.addEmptyRows(2);
        MetroTextField smtpPortTextField = new MetroTextField("Please enter the smtp port", "mail-read", "text", "mailSmtpPort",
        "587");
        MetroPanel smtpPortPanel = new MetroPanel("Please enter the smtp port", "", "mail-read", smtpPortTextField);
        emailSettingsLayout.addRow(smtpPortPanel);
        emailSettingsLayout.addEmptyRows(2);
        emailSettingsLayout.addRow(new MetroCommandButton("Update", "Update Your Email Settings", "checkmark",
        "saveEmailSettings();", "success"), new ArrayList<>(Arrays.asList(4, 4, 4)));
        MetroAccordion emailSettingsAccordion = new MetroAccordion();
        emailSettingsAccordion.addFrame("Email Settings", emailSettingsLayout, "cog");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", emailSettingsAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createSerialPortSettings(String messageContent)
    {
        ObtainWeight serialPortSettings = administration.getSerialPortSettings();
        MetroLayout serialPortSettingsLayout = new MetroLayout();
        if(messageContent.length() > 0)
        {
            serialPortSettingsLayout.addEmptyRows(2);
            serialPortSettingsLayout.addRow(new MetroPopover(messageContent, "cyan", "top"));
            serialPortSettingsLayout.addEmptyRows(2);
        }
        MetroTextField portNameTextField = new MetroTextField("Please enter the port name", "list-numbered", "text", "portName",
        serialPortSettings.getPortName());
        MetroPanel portNamePanel = new MetroPanel("Please enter the port name", "", "list-numbered", portNameTextField);
        serialPortSettingsLayout.addRow(portNamePanel);
        serialPortSettingsLayout.addEmptyRows(2);
        MetroTextField baudRateTextField = new MetroTextField("Please enter the baud rate", "list-numbered", "text", "baudRate",
        serialPortSettings.getBaudRate());
        MetroPanel baudRatePanel = new MetroPanel("Please enter the baud rate", "", "list-numbered", baudRateTextField);
        serialPortSettingsLayout.addRow(baudRatePanel);
        serialPortSettingsLayout.addEmptyRows(2);
        MetroTextField dataBitsTextField = new MetroTextField("Please enter the number of data bits", "list-numbered", "text",
        "dataBits", serialPortSettings.getDataBits());
        MetroPanel dataBitsPanel = new MetroPanel("Please enter the number of data bits","", "list-numbered", dataBitsTextField);
        serialPortSettingsLayout.addRow(dataBitsPanel);
        serialPortSettingsLayout.addEmptyRows(2);
        MetroTextField stopBitsTextField = new MetroTextField("Please enter the number of stop bits", "list-numbered", "text",
        "stopBits", serialPortSettings.getStopBits());
        MetroPanel stopBitsPanel = new MetroPanel("Please enter the number of stop bits", "", "list-numbered", stopBitsTextField);
        serialPortSettingsLayout.addRow(stopBitsPanel);
        serialPortSettingsLayout.addEmptyRows(2);
        MetroTextField parityTextField = new MetroTextField("Please enter a value for the parity", "list-numbered", "text",
        "parity", serialPortSettings.getParity());
        MetroPanel parityPanel = new MetroPanel("Please enter a value for the parity", "", "list-numbered", parityTextField);
        serialPortSettingsLayout.addRow(parityPanel);
        serialPortSettingsLayout.addEmptyRows(2);
        MetroCommandButton updateSerialPortSettingsCommandButton = new MetroCommandButton("Save", "Save Your Serial Port Settings",
        "checkmark", "saveSerialPortSettings();", "success");
        serialPortSettingsLayout.addRow(updateSerialPortSettingsCommandButton, new ArrayList<>(Arrays.asList(4, 4, 4)));
        MetroAccordion serialPortSettingsAccordion = new MetroAccordion();
        serialPortSettingsAccordion.addFrame("Serial Port Settings", serialPortSettingsLayout, "cog");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", serialPortSettingsAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString saveSerialPortSettings(String portName, String baudRate, String dataBits, String stopBits, String parity)
    {
        administration.saveSerialPortSettings(portName, baudRate, dataBits, stopBits, parity);
        return createSerialPortSettings("Your serial port settings were updated at " +
        new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
    }
    public JSONString saveEmailSettings(String username, String password, String smtpAuth, String smtpStartTlsEnable, String smtpHost, String smtpPort)
    {
        administration.saveEmailSettings(username, password, smtpAuth, smtpStartTlsEnable, smtpHost, smtpPort);
        return createEmailSettings("Your email settings were updated at " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
    }
    private MetroFluentMenuPanelGroup createMenuGroup(String tableName, String pluralWord, String singularWord)
    {
        String aWord = "A ";
        if(singularWord.startsWith("A") || singularWord.startsWith("E") || singularWord.startsWith("I") || singularWord.startsWith("O") || singularWord.startsWith("U"))
            aWord = "An ";
        ArrayList<MetroFluentButton> fluentMenuButtoms = new ArrayList<>();
        fluentMenuButtoms.add(new MetroFluentButton("View " + pluralWord, "database", "getTableContents('" + tableName + "');"));
        fluentMenuButtoms.add(new MetroFluentButton("Add " + aWord + singularWord, "plus", "addNewRowToTable('" + tableName + "');"));
        fluentMenuButtoms.add(new MetroFluentButton("Delete " + aWord + singularWord, "bin", "deleteExistingRow('" + tableName + "');"));
        fluentMenuButtoms.add(new MetroFluentButton("Update " + aWord + singularWord, "pencil", "updateRows('" + tableName + "');"));
        return new MetroFluentMenuPanelGroup(pluralWord, fluentMenuButtoms);
    }
    public JSONString createMainMenu()
    {
        MetroAccordion mainMenuAccordion = new MetroAccordion();
        MetroLayout customAdministrationFluentMenuLayout = new MetroLayout();
        List<MetroComponent> customAdministrationFluentMenuTiles = new ArrayList<>();
        customAdministrationFluentMenuTiles.add(new MetroTile("createFluentMenu(7);", "cyan", "Contracts", "eur", ""));
        customAdministrationFluentMenuLayout.addMultipleRows(customAdministrationFluentMenuTiles, 3, 1, 3, 0,
        2);
        mainMenuAccordion.addFrame("Custom Administration Menu", customAdministrationFluentMenuLayout, "menu");
        MetroLayout administrationFluentMenuLayout = new MetroLayout();
        List<MetroComponent> administrationFluentMenuTiles = new ArrayList<>();
        administrationFluentMenuTiles.add(new MetroTile("createFluentMenu(0);", "cyan", "Settings", "cog", ""));
        administrationFluentMenuTiles.add(new MetroTile("createFluentMenu(1);", "cyan", "Drivers & Commodities","user",
        ""));
        administrationFluentMenuTiles.add(new MetroTile("createFluentMenu(2);", "cyan", "First Weights & Second Weights", "truck",
        ""));
        administrationFluentMenuTiles.add(new MetroTile("createFluentMenu(3);", "cyan", "Users & Administrators", "user",
        ""));
        administrationFluentMenuTiles.add(new MetroTile("createFluentMenu(4);", "cyan", "Customers & Suppliers", "user",
        ""));
        administrationFluentMenuTiles.add(new MetroTile("createFluentMenu(5);", "cyan", "Docket Types & Contracts", "eur",
        ""));
        administrationFluentMenuTiles.add(new MetroTile("createFluentMenu(6);", "cyan", "Settings & Batch Numbers", "cog",
        ""));
        administrationFluentMenuLayout.addMultipleRows(administrationFluentMenuTiles, 3, 1, 3, 0, 2);
        mainMenuAccordion.addFrame("Administration Menu", administrationFluentMenuLayout, "menu");
        MetroLayout mainMenuLayout = new MetroLayout();
        List<MetroComponent> administrationMenuTiles = new ArrayList<>();
        administrationMenuTiles.add(new MetroTile("loadHTML5Edition();", "cyan", "Log Out", "exit", ""));
        //MetroTile mainMenuTile = new MetroTile("getHomePage();", "cyan", "Main Menu", "menu", "");
        administrationMenuTiles.add(new MetroTile("exit();", "cyan", "Exit", "exit", ""));
        //mainMenuLayout.addEmptyRows(2);
        //mainMenuLayout.addRow(new ArrayList<>(Arrays.asList(logOutTile, mainMenuTile, exitTile)), new ArrayList<>(Arrays.asList(0, 3, 1, 0, 3, 1, 0, 3, 1)));
        mainMenuLayout.addMultipleRows(administrationMenuTiles, 3, 1, 3, 0, 2);
        mainMenuAccordion.addFrame("Main Menu", mainMenuLayout, "menu");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", mainMenuAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createTable(String tableName)
    {
        Administration anAdministration = new Administration();
        MetroDataTable administrationTable = new MetroDataTable("administrationTable", anAdministration.getTableTitles(tableName),
        anAdministration.getTableContents(tableName), new ArrayList<>());
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", administrationTable.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createNewRow(String tableName)
    {
        Administration anAdministration = new Administration();
        ArrayList<String> tableTitles = anAdministration.getTableTitles(tableName);
        MetroAccordion createNewRowAccordion = new MetroAccordion();
        MetroLayout createNewRowLayout = new MetroLayout();
        tableTitles.forEach(x ->
        {
            MetroTextField aTextField = new MetroTextField("Please enter a value for " + x, "pencil", "text", x);
            createNewRowLayout.addRow(aTextField);
            createNewRowLayout.addEmptyRows(2);
        });
        JSONString formattedTableTitle = Utilities.convertListToJavaScriptArray(tableTitles);
        MetroCommandButton saveNewRowButton = new MetroCommandButton("Save", "Save New Row", "plus",
        "saveNewRow('" + tableName + "', " + formattedTableTitle.getValue() + ");", "success");
        MetroCommandButton cancelNewRowButton = new MetroCommandButton("Cancel", "Return To Table", "exit",
        "getTableContents('" + tableName + "');", "danger");
        createNewRowLayout.addRow(new ArrayList<>(Arrays.asList(saveNewRowButton, cancelNewRowButton)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        createNewRowAccordion.addFrame("Add New Row", createNewRowLayout, "plus");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", createNewRowAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createTableForUpdatingRows(String tableName)
    {
        Administration anAdministration = new Administration();
        ArrayList<ArrayList<String>> tableContents = anAdministration.getTableContents(tableName);
        ArrayList<String> tableClickEvents = new ArrayList<>();
        tableContents.forEach(x -> tableClickEvents.add("updateExistingRow('" + tableName + "', '" + x.get(0) + "');"));
        MetroDataTable administrationTable = new MetroDataTable("administrationTable", anAdministration.getTableTitles(tableName), tableContents, tableClickEvents);
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", administrationTable.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString updateExistingRow(String tableName, String uniqueIdentifier)
    {
        Administration anAdministration = new Administration();
        ArrayList<String> tableTitles = anAdministration.getTableTitles(tableName);
        ArrayList<ArrayList<String>> selectedRow = anAdministration.getSelectedRow(tableName, uniqueIdentifier);
        MetroAccordion updateExistingRowAccordion = new MetroAccordion();
        MetroLayout updateExistingRowLayout = new MetroLayout();
        if(selectedRow.size() > 0)
        {
            for(int counter = 0; counter < tableTitles.size(); counter++)
            {
                MetroTextField aTextField = new MetroTextField("Please enter an updated value for " + tableTitles.get(counter), "pencil",
                "text", tableTitles.get(counter), selectedRow.get(0).get(counter));
                updateExistingRowLayout.addRow(aTextField);
                updateExistingRowLayout.addEmptyRows(2);
            }
        }
        JSONString formattedTableTitle = Utilities.convertListToJavaScriptArray(tableTitles);
        MetroCommandButton updateExistingRowButton = new MetroCommandButton("Update", "Update Existing Row", "pencil",
        "updateExistingRowConfirmation('" + tableName + "', " + formattedTableTitle + ", '" + uniqueIdentifier + "');", "success");
        MetroCommandButton cancelExistingRowButton = new MetroCommandButton("Cancel", "Return To Table", "exit",
        "updateRows('" + tableName + "');", "danger");
        updateExistingRowLayout.addRow(new ArrayList<>(Arrays.asList(updateExistingRowButton, cancelExistingRowButton)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        updateExistingRowAccordion.addFrame("Update Existing Row", updateExistingRowLayout, "pencil");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", updateExistingRowAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString saveNewRow(String tableName, JSArray tableValues)
    {
        System.out.println("SAVE NEW ROW METHOD CALLED");
        System.out.println("TABLE NAME: " + tableName);
        ArrayList<String> newRowValues = new ArrayList<>();
        for(int counter = 0; counter < tableValues.length(); counter++)
            newRowValues.add(tableValues.get(counter).getStringValue());
        for(int counter = 0; counter < tableValues.length(); counter++)
            System.out.println("TABLE CELL: " + tableValues.get(counter).getStringValue());
        ArrayList<String> newRowOfValues = new ArrayList<>();
        administration.insertNewRow(tableName, newRowOfValues);
        return createTable(tableName);
    }
    public JSONString updateExistingRow(String tableName, String[] tableValues, String uniqueIdentifier)
    {
        System.out.println("UPDATE EXISTING ROWS METHOD CALLED");
        HashMap<String, String> parameters = new HashMap<>();
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createTableForDeletions(String tableName)
    {
        Administration anAdministration = new Administration();
        ArrayList<ArrayList<String>> tableContents = anAdministration.getTableContents(tableName);
        ArrayList<String> tableClickEvents = new ArrayList<>();
        tableContents.forEach(x -> tableClickEvents.add("deleteSelectedRow('" + tableName + "', '" + x.get(0) + "');"));
        MetroDataTable administrationTable = new MetroDataTable("administrationTable", anAdministration.getTableTitles(tableName),
        anAdministration.getTableContents(tableName), tableClickEvents);
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", administrationTable.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString deleteSelectedRow(String tableName, String uniqueIdentifier)
    {
        Administration anAdministration = new Administration();
        ArrayList<String> tableTitles = anAdministration.getTableTitles(tableName);
        ArrayList<ArrayList<String>> tableContents = anAdministration.getSelectedRow(tableName, uniqueIdentifier);
        MetroDataTable administrationTable = new MetroDataTable("administrationTable", anAdministration.getTableTitles(tableName),
        anAdministration.getSelectedRow(tableName, uniqueIdentifier), new ArrayList<>());
        MetroCommandButton deleteSelectedRowConfirmationButton = new MetroCommandButton("Delete", "Delete Selected Row", "checkmark",
        "", "success");
        MetroCommandButton deleteSelectedRowCancellationButton = new MetroCommandButton("Cancel", "Return To Table", "bin",
        "deleteExistingRow('" + tableName + "');", "danger");
        MetroAccordion deleteSelectedRowAccordion = new MetroAccordion();
        MetroLayout deleteSelectedRowLayout = new MetroLayout();
        deleteSelectedRowLayout.addEmptyRows(2);
        deleteSelectedRowLayout.addRow(new MetroHeading("Delete Selected Row", ""));
        deleteSelectedRowLayout.addEmptyRows(2);
        deleteSelectedRowLayout.addRow(administrationTable);
        deleteSelectedRowLayout.addEmptyRows(2);
        deleteSelectedRowLayout.addRow(new ArrayList<>(Arrays.asList(deleteSelectedRowConfirmationButton, deleteSelectedRowCancellationButton)),
        new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        deleteSelectedRowAccordion.addFrame("Delete Selected Row", deleteSelectedRowLayout, "bin");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", deleteSelectedRowAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString deleteSelectedRowConfirmation(String tableName, String uniqueIdentifier)
    {
        Administration anAdministration = new Administration();
        anAdministration.deleteSelectedRow(tableName, uniqueIdentifier);
        return createTableForDeletions(tableName);
    }
    public JSONString viewContracts()
    {
        Administration anAdministration = new Administration();
        ArrayList<String> contractsTableTitles = anAdministration.getContractsTitles();
        ArrayList<ArrayList<String>> contractsTableValues = anAdministration.getContractsValues();
        MetroDataTable contractsDataTable = new MetroDataTable("contractsDataTable", contractsTableTitles, contractsTableValues, new ArrayList<>());
        MetroAccordion contractsAccordion = new MetroAccordion();
        contractsAccordion.addFrame("Contracts", contractsDataTable, "eur");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", contractsAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString deleteContracts()
    {
        Administration anAdministration = new Administration();
        ArrayList<String> contractsTableTitles = anAdministration.getContractsTitles();
        ArrayList<ArrayList<String>> contractsTableValues = anAdministration.getContractsValues();
        ArrayList<String> contractsTableClickEvents = new ArrayList<>();
        contractsTableValues.forEach(x -> contractsTableClickEvents.add("deleteSelectedContract('" + x.get(0) + "');"));
        MetroDataTable contractsDataTable = new MetroDataTable("contractsDataTable", contractsTableTitles, contractsTableValues, contractsTableClickEvents);
        MetroAccordion contractsAccordion = new MetroAccordion();
        contractsAccordion.addFrame("Contracts", contractsDataTable, "bin");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", contractsAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString pdfForSelectedContract()
    {
        Administration anAdministration = new Administration();
        ArrayList<String> contractsTableTitles = anAdministration.getContractsTitles();
        ArrayList<ArrayList<String>> contractsTableValues = anAdministration.getContractsValues();
        ArrayList<String> contractsTableClickEvents = new ArrayList<>();
        contractsTableValues.forEach(x -> contractsTableClickEvents.add("generatePDFForSelectedContract('" + x.get(0) + "');"));
        MetroDataTable contractsDataTable = new MetroDataTable("contractsDataTable", contractsTableTitles, contractsTableValues, contractsTableClickEvents);
        MetroAccordion contractsAccordion = new MetroAccordion();
        contractsAccordion.addFrame("Contracts", contractsDataTable, "file-pdf");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", contractsAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString generatePDFForSelectedContract(String contractID)
    {
        Administration anAdministration = new Administration();
        String fileLocation = anAdministration.generateContractReport(contractID);
        MetroAccordion generatePDFForSelectedContractAccordion = new MetroAccordion();
        MetroIFrame anIFrame = new MetroIFrame(fileLocation);
        generatePDFForSelectedContractAccordion.addFrame("PDF Report For Contract " + contractID, anIFrame, "file-pdf");
        MetroLayout generatePDFForSelectedContractLayout = new MetroLayout();
        MetroTile emailTile = new MetroTile("emailPDFForSelectedContract('" + contractID + "')", "cyan", "Email PDF Report",
        "mail-read", "");
        MetroTile printTile = new MetroTile("", "cyan", "Print PDF Report", "printer", "");
        generatePDFForSelectedContractLayout.addRow(new ArrayList<>(Arrays.asList(emailTile, printTile)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        MetroUpdatePanel generatePDFForSelectedContractUpdatePanel = new MetroUpdatePanel("generatePDFForSelectedContractUpdatePanel",
        generatePDFForSelectedContractLayout);
        MetroLayout generatePDFForSelectedContractMasterLayout = new MetroLayout();
        generatePDFForSelectedContractMasterLayout.addRow(generatePDFForSelectedContractUpdatePanel);
        generatePDFForSelectedContractMasterLayout.addRow(generatePDFForSelectedContractAccordion);
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", generatePDFForSelectedContractMasterLayout.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString emailPDFForSelectedContract(String contractID)
    {
        MetroTextField emailAddress = new MetroTextField("Please enter the email address of the recipient", "mail-read", "text",
        "emailAddress");
        MetroPanel emailAddressPanel = new MetroPanel("Please enter the email address of the recipient", "", "mail-read", emailAddress);
        MetroCommandButton emailAddressButton = new MetroCommandButton("Send", "Send Your Email", "mail-read",
       "emailPDFConfirmation('" + contractID + "');","success");
        MetroLayout emailAddressLayout = new MetroLayout();
        emailAddressLayout.addRow(emailAddressPanel);
        emailAddressLayout.addEmptyRows(2);
        emailAddressLayout.addRow(emailAddressButton, new ArrayList<>(Arrays.asList(4, 4, 4)));
        emailAddressLayout.addEmptyRows(2);
        MetroAccordion emailAddressAccordion = new MetroAccordion();
        emailAddressAccordion.addFrame("Email Address Of The Recipient", emailAddressLayout, "mail-read");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", emailAddressAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString emailPDFConfirmation(String contractID, String emailAddress)
    {
        Administration anAdministration = new Administration();
        MetroAccordion emailPDFConfirmationAccordion = new MetroAccordion();
        Boolean emailStatus = anAdministration.emailContractReport(emailAddress, contractID);
        if(emailStatus)
            emailPDFConfirmationAccordion.addFrame("Email Successfully Sent", new MetroHeading("Your email was successfully sent", ""),
            "mail-read");
        else
            emailPDFConfirmationAccordion.addFrame("Email Was Not Successfully Sent", new MetroHeading("Your email was not successfully sent", ""),
            "warning");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", emailPDFConfirmationAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString deleteSelectedContract(String contractID)
    {
        Administration anAdministration = new Administration();
        ArrayList<String> contractsTableTitles = anAdministration.getContractsTitles();
        ArrayList<String> selectedContract = anAdministration.getContractAsList(contractID);
        ArrayList<ArrayList<String>> contractsTableValues = new ArrayList<>();
        contractsTableValues.add(selectedContract);
        MetroDataTable contractsDataTable = new MetroDataTable("contractsDataTable", contractsTableTitles, contractsTableValues, new ArrayList<>());
        MetroLayout contractsLayout = new MetroLayout();
        contractsLayout.addEmptyRows(2);
        contractsLayout.addRow(new MetroHeading("Are you sure you wish to delete the selected contract?", ""));
        contractsLayout.addEmptyRows(2);
        contractsLayout.addRow(contractsDataTable);
        contractsLayout.addEmptyRows(2);
        MetroCommandButton deleteContractConfirmationButton = new MetroCommandButton("Delete", "Delete The Selected Contract", "bin",
        "deleteSelectedContractConfirmation('" + contractID + "');", "success");
        MetroCommandButton deleteContractDeletionButton = new MetroCommandButton("Cancel", "Return To Contracts", "exit",
        "deleteContracts();", "danger");
        contractsLayout.addRow(new ArrayList<>(Arrays.asList(deleteContractConfirmationButton, deleteContractDeletionButton)),
        new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        contractsLayout.addEmptyRows(2);
        MetroAccordion contractsAccordion = new MetroAccordion();
        contractsAccordion.addFrame("Contracts", contractsLayout, "bin");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", contractsAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString deleteSelectedContractConfirmation(String contractID)
    {
        Administration anAdministration = new Administration();
        anAdministration.deleteSelectedRow("contracts", contractID);
        return deleteContracts();
    }
    public JSONString addContract()
    {
        Administration anAdministration = new Administration();
        MetroDropDown commoditiesDropDown = new MetroDropDown("commoditiesDropDown", "Please select a commodity", anAdministration.getCommoditiesValues());
        MetroTextField price = new MetroTextField("Please enter the price of the contract", "eur", "text", "price");
        MetroTextField total = new MetroTextField("Please enter the total tonnage of the contract", "eur", "text", "total");
        MetroDropDown docketTypesDropDown = new MetroDropDown("docketTypesDropDown", "Please select a docket type",
        anAdministration.getDocketTypesValues());
        DocketType existingDocketType = new DocketType(1, "Purchase");
        MetroDropDown consigneesDropDown = new MetroDropDown("consigneesDropDown", "Please select a consignee",
        anAdministration.getConsigneesValues(existingDocketType));
        MetroTextField startDate = new MetroTextField("Please enter the start date of the contract", "calendar", "text",
        "startDate");
        MetroTextField endDate = new MetroTextField("Please enter the end date of the contract", "calendar", "text",
        "endDate");
        MetroCommandButton addContractCommandButton = new MetroCommandButton("Add", "Add Your Contract", "plus",
        "saveNewContract();", "success");
        MetroLayout addContractLayout = new MetroLayout();
        addContractLayout.addEmptyRows(2);
        addContractLayout.addRow(commoditiesDropDown);
        addContractLayout.addEmptyRows(2);
        addContractLayout.addRow(price);
        addContractLayout.addEmptyRows(2);
        addContractLayout.addRow(total);
        addContractLayout.addEmptyRows(2);
        addContractLayout.addRow(docketTypesDropDown);
        addContractLayout.addEmptyRows(2);
        addContractLayout.addRow(consigneesDropDown);
        addContractLayout.addEmptyRows(2);
        addContractLayout.addRow(startDate);
        addContractLayout.addEmptyRows(2);
        addContractLayout.addRow(endDate);
        addContractLayout.addEmptyRows(2);
        addContractLayout.addRow(addContractCommandButton, new ArrayList<>(Arrays.asList(4, 4, 4)));
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", addContractLayout.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString saveNewContract(String commodityTitle, String price, String total, String docketType, String consignee, String startDate, String endDate)
    {
        Administration anAdministration = new Administration();
        anAdministration.insertNewContract(commodityTitle, price, total, docketType, consignee, startDate, endDate);
        return viewContracts();
    }
    public JSONString editContracts()
    {
        Administration anAdministration = new Administration();
        ArrayList<String> contractsTableTitles = anAdministration.getContractsTitles();
        ArrayList<ArrayList<String>> contractsTableValues = anAdministration.getContractsValues();
        ArrayList<String> contractsTableClickEvents = new ArrayList<>();
        contractsTableValues.forEach(x -> contractsTableClickEvents.add("editSelectedContract('" + x.get(0) + "');"));
        MetroDataTable contractsDataTable = new MetroDataTable("contractsDataTable", contractsTableTitles, contractsTableValues, contractsTableClickEvents);
        MetroAccordion contractsAccordion = new MetroAccordion();
        contractsAccordion.addFrame("Contracts", contractsDataTable, "pencil");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", contractsAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString editSelectedContract(String contractID)
    {
        Administration anAdministration = new Administration();
        Contract aContract = anAdministration.getContract(contractID);
        MetroDropDown selectedCommodity = new MetroDropDown("selectedCommodity", "Please select a commodity", anAdministration.getCommoditiesValues());
        MetroTextField price = new MetroTextField("Please enter a value for the price of the commodity", "eur", "text",
        "price", aContract.getPrice() + "");
        MetroTextField total = new MetroTextField("Please enter a value for the total of the commodity", "eur", "text",
        "total", aContract.getTotal() + "");
        MetroDropDown selectedDocketType = new MetroDropDown("selectedDocketType", "Please select a docket type", anAdministration.getDocketTypesValues());
        MetroDropDown selectedConsignee = new MetroDropDown("selectedConsignee", "Please select a consignee",
        anAdministration.getConsigneesValues(aContract.getDocketType()));
        MetroTextField startDate = new MetroTextField("Please enter a value for the start date of the contract", "calendar", "text",
        "startDate", new SimpleDateFormat("yyyy-MM-dd").format(aContract.getStartDate()));
        MetroTextField endDate = new MetroTextField("Please enter a value for the end date of the contract", "calendar", "text",
        "endDate", new SimpleDateFormat("yyyy-MM-dd").format(aContract.getEndDate()));
        MetroCommandButton updateSelectedContract = new MetroCommandButton("Update", "Update Selected Contract", "checkmark",
        "", "success");
        MetroCommandButton cancelSelectedContract = new MetroCommandButton("Cancel", "Return To Contracts", "exit",
        "editContracts();", "danger");
        MetroLayout contractLayout = new MetroLayout();
        contractLayout.addRow(selectedCommodity);
        contractLayout.addEmptyRows(2);
        contractLayout.addRow(price);
        contractLayout.addEmptyRows(2);
        contractLayout.addRow(total);
        contractLayout.addEmptyRows(2);
        contractLayout.addRow(selectedDocketType);
        contractLayout.addEmptyRows(2);
        contractLayout.addRow(selectedConsignee);
        contractLayout.addEmptyRows(2);
        contractLayout.addRow(startDate);
        contractLayout.addEmptyRows(2);
        contractLayout.addRow(endDate);
        contractLayout.addEmptyRows(2);
        contractLayout.addRow(new ArrayList<>(Arrays.asList(updateSelectedContract, cancelSelectedContract)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        MetroAccordion editSelectedContractAccordion = new MetroAccordion();
        editSelectedContractAccordion.addFrame("Edit Selected Contract", contractLayout, "pencil");
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("html", editSelectedContractAccordion.toString());
        return Utilities.convertHashMapToJSON(selectedParameters);
    }
}