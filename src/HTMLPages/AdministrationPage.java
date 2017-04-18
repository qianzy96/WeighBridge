package HTMLPages;
import HTMLControls.*;
import Models.Administration;
import Utilities.*;
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
}