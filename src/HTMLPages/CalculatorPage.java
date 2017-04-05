package HTMLPages;

import Entities.*;
import HTMLControls.*;
import Models.Calculator;
import Utilities.Utilities;
import com.teamdev.jxbrowser.chromium.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalculatorPage
{
    private Calculator aCalculator;
    private static User user;
    private HashMap<Ration, Double> rationQuantities;
    private String title;
    public CalculatorPage()
    {
        this.aCalculator = new Calculator();
        this.rationQuantities = new HashMap<>();
        this.title = "";
    }
    public void setUser(User user)
    {
        CalculatorPage.user = user;
    }
    public JSONString createCalculatorPage()
    {
        MetroFluentMenu calculatorFluentMenu = new MetroFluentMenu("calculatorFluentMenu", "Main Menu", "getMainMenu();",
        new ArrayList<>(Collections.singletonList("Calculate Rations")));
        ArrayList<MetroFluentButton> buttons = new ArrayList<>();
        buttons.add(new MetroFluentButton("Create A New Ration", "calculator2", "createRationStepOne();"));
        buttons.add(new MetroFluentButton("View Rations", "database", "getUserRations(false);"));
        buttons.add(new MetroFluentButton("Delete A Ration", "bin", "getUserRations(true);"));
        MetroFluentMenuPanelGroup aRationsGroup = new MetroFluentMenuPanelGroup("Rations", buttons);
        buttons.clear();
        buttons.add(new MetroFluentButton("Create A New Commodity", "plus", "addACommodityMenu();"));
        buttons.add(new MetroFluentButton("View Commodities", "database", "getCommodities(false);"));
        buttons.add(new MetroFluentButton("Delete A Commodity", "bin", "getCommodities(true);"));
        MetroFluentMenuPanelGroup aCommoditiesGroup = new MetroFluentMenuPanelGroup("Commodities", buttons);
        buttons.clear();
        buttons.add(new MetroFluentButton("Generate PDF File", "file-pdf", "generatePDFFile();"));
        buttons.add(new MetroFluentButton("Email PDF File", "mail-read", "emailPDFFile();"));
        buttons.add(new MetroFluentButton("Print PDF File", "printer", "printPDFFile();"));
        MetroFluentMenuPanelGroup aQuickActionsGroup = new MetroFluentMenuPanelGroup("Quick Actions", buttons);
        buttons.clear();
        buttons.add(new MetroFluentButton("Settings", "cog", "createSettingsMenu();"));
        MetroFluentMenuPanelGroup aSettingsGroup = new MetroFluentMenuPanelGroup("Settings", buttons);
        calculatorFluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(aRationsGroup, aCommoditiesGroup, aQuickActionsGroup, aSettingsGroup)));
        MetroLayout calculatorLayout = new MetroLayout();
        calculatorLayout.addRow(calculatorFluentMenu);
        calculatorLayout.addRow(new MetroUpdatePanel("calculatorUpdatePanel", null));
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", calculatorLayout.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createRationStepOne()
    {
        MetroAccordion createRationAccordion = new MetroAccordion();
        MetroLayout createRationLayout = new MetroLayout();
        MetroProgressBar progressBar = new MetroProgressBar(50, "cyan");
        MetroTextField rationTitleTextField = new MetroTextField("Please enter the title of the ration", "pencil", "text",
                                              "rationTitleTextField");
        MetroDropDown rationTypeDropDown = new MetroDropDown("rationTypeDropDown", "Select A Ration Type", aCalculator.getAvailableRationTypes());
        MetroCommandButton rationTitleSaveButton = new MetroCommandButton("Save", "Proceed To Step 2", "checkmark",
        "createRationStepTwo();", "success");
        createRationLayout.addRow(progressBar);
        createRationLayout.addEmptyRows(2);
        createRationLayout.addRow(rationTitleTextField);
        createRationLayout.addEmptyRows(2);
        createRationLayout.addRow(rationTypeDropDown);
        createRationLayout.addEmptyRows(2);
        createRationLayout.addRow(rationTitleSaveButton, new ArrayList<>(Arrays.asList(4, 4, 4)));
        createRationAccordion.addFrame("Ration Calculator", createRationLayout, "calculator2");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", createRationAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createRationStepTwo(String rationTitle, String rationType)
    {
        System.out.println("Ration Title: " + rationTitle);
        System.out.println("Ration Type: " + rationType);
        HashMap<String, String> parameters = new HashMap<>();
        title = rationTitle;
        if(rationTitle.length() < 2 || rationType.length() < 2)
        {
            parameters.put("response", "error");
            parameters.put("title", "Invalid Length");
            parameters.put("content", "The Ration Title And The Ration Type Should Have At Least 2 Characters");
        }
        if(parameters.size() == 0)
        {
            MetroAccordion createRationAccordion = new MetroAccordion();
            MetroLayout createRationLayout = new MetroLayout();
            MetroProgressBar progressBar = new MetroProgressBar(100, "cyan");
            MetroDropDown commoditiesDropDown = new MetroDropDown("commoditiesDropDown", "Add A Commodity", aCalculator.getAvailableRationsAsStrings());
            MetroTextField commodityQuantityTextBox = new MetroTextField("Please enter the desired quantity of the commodity", "calculator2",
            "text", "commodityQuantityTextBox");
            MetroCommandButton addComponentToRationButton = new MetroCommandButton("Add", "Add Component", "plus",
            "addCommodityTitleAndQuantityToRation();", "success");
            MetroCommandButton createRationButton = new MetroCommandButton("Save", "Save Your Ration", "checkmark",
            "calculateRation();","success");
            createRationLayout.addRow(progressBar);
            createRationLayout.addEmptyRows(2);
            createRationLayout.addRow(commoditiesDropDown);
            createRationLayout.addEmptyRows(2);
            createRationLayout.addRow(commodityQuantityTextBox);
            createRationLayout.addEmptyRows(2);
            createRationLayout.addRow(addComponentToRationButton, new ArrayList<>(Arrays.asList(4, 4, 4)));
            createRationLayout.addEmptyRows(2);
            createRationLayout.addRow(new MetroUpdatePanel("addRationComponentUpdatePanel", null));
            createRationLayout.addEmptyRows(2);
            createRationLayout.addRow(createRationButton, new ArrayList<>(Arrays.asList(4, 4, 4)));
            createRationAccordion.addFrame("Ration Calculator", createRationLayout, "calculator2");
            parameters.put("html", createRationAccordion.toString());
        }
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString getMainMenu()
    {
        MetroLayout mainMenuLayout = new MetroLayout();
        ArrayList<MetroComponent> mainMenuTiles = new ArrayList<>();
        mainMenuTiles.add(new MetroTile("getPortalPage();","cyan", "Main Menu", "menu", ""));
        mainMenuTiles.add(new MetroTile("loadHTML5Edition();","cyan", "Log Out", "exit", ""));
        mainMenuTiles.add(new MetroTile("exit();","cyan", "Exit", "exit", ""));
        mainMenuLayout.addRow(mainMenuTiles, new ArrayList<>(Arrays.asList(1, 3, 0, 1, 3, 0, 1, 3, 0)));
        MetroAccordion mainMenuAccordion = new MetroAccordion();
        mainMenuAccordion.addFrame("Main Menu", mainMenuLayout, "menu");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", mainMenuAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString addCommodityToRation(String commodityTitle)
    {
        MetroLayout commodityLayout = new MetroLayout();
        MetroTextField commodityTextField = new MetroTextField("Please enter a value for the kg per day fresh weight for " + commodityTitle,
        "list-numbered", "text", "commodityTextField");
        MetroCommandButton saveCommodityButton = new MetroCommandButton("Save", "Save Your Commodity", "checkmark",
        "", "success");
        commodityLayout.addRow(commodityTextField);
        commodityLayout.addEmptyRows(2);
        commodityLayout.addRow(saveCommodityButton, new ArrayList<>(Arrays.asList(4, 4, 4)));
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", commodityLayout.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString addCommodityWithQuantityToRation(String commodityTitle, String quantity)
    {
        HashMap<String, String> parameters = new HashMap<>();
        if(commodityTitle.length() < 2)
        {
            parameters.put("response", "error");
            parameters.put("title", "Invalid Length");
            parameters.put("content", "The Title Of The Commodity Should Have At Least 2 Characters");
        }
        try
        {
            Double currentQuantity = Double.parseDouble(quantity);
            if(currentQuantity <= 0)
            {
                parameters.put("response", "error");
                parameters.put("title", "Invalid Quantity");
                parameters.put("content", "The Quantity Of The Commodity Should Be Greater Than 0");
            }
        }
        catch(Exception error)
        {
            parameters.put("response", "error");
            parameters.put("title", "Invalid Double");
            parameters.put("content", "The Quantity Of The Commodity Should Be A Valid Double");
        }
        if(parameters.size() == 0)
        {
            Ration aRation = aCalculator.getRationFromSpecifiedTitle(commodityTitle);
            if (aRation != null)
                rationQuantities.put(aRation, Double.parseDouble(quantity));
            ArrayList<ArrayList<String>> rationTitlesAndQuantities = new ArrayList<>();
            for (Map.Entry<Ration, Double> aRationQuantity : rationQuantities.entrySet())
                rationTitlesAndQuantities.add(new ArrayList<>(Arrays.asList(aRationQuantity.getKey().getFeed(), aRationQuantity.getValue().toString())));
            MetroDataTable addedCommoditiesDataTable = new MetroDataTable("addedCommoditiesDataTable", new ArrayList<>(Arrays.asList("Titles", "Quantities")),
                                                       rationTitlesAndQuantities, new ArrayList<>());
            MetroAccordion addedCommoditiesAccordion = new MetroAccordion();
            addedCommoditiesAccordion.addFrame("Commodities With Quantities", addedCommoditiesDataTable, "calculator2");
            parameters.put("html", addedCommoditiesAccordion.toString());
        }
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString calculateRation()
    {
        aCalculator.insertNewRationCalculation(user.getCode() + "", title, rationQuantities);
        LinkedHashMap<String, String> rationResults = aCalculator.getCalculationResultsWithTitles(rationQuantities, "");
        MetroLayout aLayout = new MetroLayout();
        List<MetroComponent> componentTiles = new ArrayList<>();
        for(Map.Entry<String, String> aRationResult : rationResults.entrySet())
        {
            MetroTile currentTile = new MetroTile("", "cyan", aRationResult.getKey(), "calculator2", "",
            aRationResult.getValue());
            componentTiles.add(currentTile);
        }
        aLayout.addMultipleRows(componentTiles, 3, 1, 3, 0, 2);
        MetroAccordion calculateRationAccordion = new MetroAccordion();
        calculateRationAccordion.addFrame("Calculate Ration", aLayout, "calculator2");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", calculateRationAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString getCommodities(boolean deleteCommodities)
    {
        ArrayList<Ration> commodities = aCalculator.getAvailableRations();
        ArrayList<String> rationsColumnTitles = aCalculator.getTableColumnTitles("rations");
        ArrayList<ArrayList<String>> commoditiesAsStrings = new ArrayList<>();
        ArrayList<String> tableClickEvents = new ArrayList<>();
        for(Ration aCommodity : commodities)
            commoditiesAsStrings.add(aCommodity.toList());
        if(deleteCommodities)
            for(Ration aCommodity: commodities)
                tableClickEvents.add("deleteConfirmationOfCommodity('" + aCommodity.getFeed() + "');");
        else
            for(Ration aCommodity: commodities)
                tableClickEvents.add("getDetailedViewOfCommodity('" + aCommodity.getFeed() + "');");
        MetroDataTable commoditiesTable = new MetroDataTable("commoditiesTable", rationsColumnTitles, commoditiesAsStrings, tableClickEvents);
        MetroAccordion commoditiesAccordion = new MetroAccordion();
        commoditiesAccordion.addFrame("Available Commodities", commoditiesTable, "florist");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", commoditiesAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString deleteConfirmationOfCommodity(String commodityTitle)
    {
        Ration aRation = aCalculator.getRationFromSpecifiedTitle(commodityTitle);
        LinkedHashMap<String, String> rationComponents = aRation.toMap();
        ArrayList<String> rationTitles = new ArrayList<>();
        ArrayList<ArrayList<String>> rationValues = new ArrayList<>();
        ArrayList<String> selectedRationValues = new ArrayList<>();
        rationComponents.keySet().forEach(x -> rationTitles.add(x));
        rationComponents.values().forEach(x -> selectedRationValues.add(x));
        rationValues.add(selectedRationValues);
        MetroDataTable selectedCommodityForDeletionTable = new MetroDataTable("selectedCommodityForDeletionTable", rationTitles, rationValues, new ArrayList<>());
        MetroLayout selectedCommodityForDeletionLayout = new MetroLayout();
        selectedCommodityForDeletionLayout.addRow(new MetroHeading("Are you sure you want to delete this row?", ""));
        selectedCommodityForDeletionLayout.addEmptyRows(2);
        selectedCommodityForDeletionLayout.addRow(selectedCommodityForDeletionTable);
        selectedCommodityForDeletionLayout.addEmptyRows(2);
        selectedCommodityForDeletionLayout.addRow(new MetroCommandButton("Delete", "Delete This Row", "bin",
        "deleteCommodity('" + aRation.getCode() + "');", "danger"), new ArrayList<>(Arrays.asList(4, 4, 4)));
        MetroAccordion selectedCommodityForDeletionAccordion = new MetroAccordion();
        selectedCommodityForDeletionAccordion.addFrame("Confirm Deletion Of Row", selectedCommodityForDeletionLayout, "bin");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", selectedCommodityForDeletionAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString deleteCommodity(String commodityCode)
    {
        aCalculator.removeRation(commodityCode);
        return getCommodities(true);
    }
    public JSONString getDetailedViewOfCommodity(String commodityTitle)
    {
        Ration aRation = aCalculator.getRationFromSpecifiedTitle(commodityTitle);
        LinkedHashMap<String, String> rationComponents = aRation.toMap();
        MetroAccordion detailedViewCommodityAccordion = new MetroAccordion();
        for(Map.Entry<String, String> aRationComponent : rationComponents.entrySet())
        {
            MetroTextualPanel aTextualPanel = new MetroTextualPanel("", aRationComponent.getValue());
            detailedViewCommodityAccordion.addFrame("Value For " + aRationComponent.getKey(), aTextualPanel, "florist");
        }
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", detailedViewCommodityAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString getRations(boolean deleteRations)
    {
        ArrayList<ArrayList<String>> usersRations = aCalculator.getUsersRations(user.getCode() + "");
        ArrayList<String> userRationsTitles = aCalculator.getUsersRationsTitles();
        ArrayList<String> tableClickEvents = new ArrayList<>();
        if(deleteRations)
            for(ArrayList<String> anUserRation : usersRations)
                tableClickEvents.add("deleteConfirmationOfRation('" + anUserRation.get(0) + "');");
        else
            for(ArrayList<String> anUserRation : usersRations)
                tableClickEvents.add("getDetailedViewOfRation('" + anUserRation.get(0) + "');");
        MetroDataTable userCreatedRations = new MetroDataTable("userCreatedRations", userRationsTitles, usersRations, tableClickEvents);
        MetroAccordion getRationAccordion = new MetroAccordion();
        getRationAccordion.addFrame("User Created Rations", userCreatedRations, "calculator2");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", getRationAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString getDetailedViewOfRation(String userRationsCode)
    {
        ArrayList<ArrayList<String>> usersRationComponents = aCalculator.getUsersRationsComponents(userRationsCode);
        ArrayList<String> usersRationsTitles = aCalculator.getDetailedUsersRationsTitles();
        MetroDataTable detailedViewRationTable = new MetroDataTable("detailedViewRationTable", usersRationsTitles, usersRationComponents, new ArrayList<>());
        MetroAccordion getDetailedViewOfRationAccordion = new MetroAccordion();
        getDetailedViewOfRationAccordion.addFrame("Detailed View Of Ration", detailedViewRationTable, "calculator2");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", getDetailedViewOfRationAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString deleteConfirmationOfRation(String userRationsCode)
    {
        ArrayList<ArrayList<String>> usersRationTitles = aCalculator.getRationDetails(userRationsCode);
        for(ArrayList<String> anUserRationTitle : usersRationTitles)
            anUserRationTitle.remove(1);
        ArrayList<String> usersRationHeadings = aCalculator.getUsersRationsTitles();
        MetroLayout userCreatedRationsLayout = new MetroLayout();
        MetroDataTable deletedViewRationTable = new MetroDataTable("deletedViewRationTable", usersRationHeadings, usersRationTitles, new ArrayList<>());
        MetroCommandButton deleteUserCreatedRationCommandButton = new MetroCommandButton("Delete", "Delete This Ration", "bin",
        "deleteRation('" + userRationsCode + "');", "danger");
        userCreatedRationsLayout.addRow(deletedViewRationTable);
        userCreatedRationsLayout.addRow(deleteUserCreatedRationCommandButton, new ArrayList<>(Arrays.asList(4, 4, 4)));
        MetroAccordion deleteRationConfirmationAccordion = new MetroAccordion();
        deleteRationConfirmationAccordion.addFrame("Delete Ration Confirmation", userCreatedRationsLayout, "warning");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", deleteRationConfirmationAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString deleteRation(String userRationCode)
    {
        aCalculator.removeUserRation(userRationCode);
        return getRations(true);
    }
    public JSONString generatePDFFile()
    {
        HashMap<String, String> parameters = new HashMap<>();
        if(rationQuantities.size() > 0)
        {
            String fileLocation = aCalculator.generatePDFFile(rationQuantities, title, user.getCode() + "");
            MetroAccordion pdfFileAccordion = new MetroAccordion();
            MetroIFrame pdfForIFrame = new MetroIFrame(fileLocation);
            pdfFileAccordion.addFrame("PDF Report For " + title, pdfForIFrame, "file-pdf");
            parameters.put("html", pdfFileAccordion.toString());
        }
        else
        {
            MetroAccordion anErrorAccordion = new MetroAccordion();
            anErrorAccordion.addFrame("No Ration Has Been Created", new MetroHeading("No Ration Has Been Created", ""), "warning");
            parameters.put("html", anErrorAccordion.toString());
        }
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString emailPDFFile()
    {
        HashMap<String, String> parameters = new HashMap<>();
        if(rationQuantities.size() > 0)
        {
            String fileLocation = aCalculator.generatePDFFile(rationQuantities, title, user.getCode() + "");
            MetroAccordion pdfFileAccordion = new MetroAccordion();
            MetroIFrame pdfForIFrame = new MetroIFrame(fileLocation);
            pdfFileAccordion.addFrame("PDF Report For " + title, pdfForIFrame, "file-pdf");
            MetroAccordion emailPdfFileAccordion = new MetroAccordion();
            MetroLayout emailPDFFileLayout = new MetroLayout();
            MetroProgressBar emailPdfFileProgressBar = new MetroProgressBar(50, "cyan");
            emailPDFFileLayout.addRow(emailPdfFileProgressBar);
            MetroHeading emailPdfFileHeading = new MetroHeading("Recipient Email Address", "Step 1");
            emailPDFFileLayout.addRow(emailPdfFileHeading);
            MetroTextField emailAddressTextBox = new MetroTextField("Please enter the email address of the recipient", "mail-read",
            "text","emailAddressTextBox");
            emailPDFFileLayout.addRow(emailAddressTextBox);
            MetroCommandButton submitEmailAddressCommandButton = new MetroCommandButton("Send", "Send To Your Email Address",
            "checkmark","emailPDFFileInvoked();", "success");
            emailPDFFileLayout.addRow(submitEmailAddressCommandButton, new ArrayList<>(Arrays.asList(4, 4, 4)));
            MetroUpdatePanelWithPreLoader emailPDFFileUpdatePanel = new MetroUpdatePanelWithPreLoader("emailPDFFileUpdatePanel", emailPDFFileLayout);
            emailPdfFileAccordion.addFrame("Emailing The PDF Report", emailPDFFileUpdatePanel, "mail-read");
            MetroLayout emailPDFFileMasterLayout = new MetroLayout();
            emailPDFFileMasterLayout.addRow(emailPdfFileAccordion);
            emailPDFFileMasterLayout.addRow(pdfFileAccordion);
            parameters.put("html", emailPDFFileMasterLayout.toString());
        }
        else
        {
            MetroAccordion anErrorAccordion = new MetroAccordion();
            anErrorAccordion.addFrame("No Ration Has Been Created", new MetroHeading("No Ration Has Been Created", ""), "warning");
            parameters.put("html", anErrorAccordion.toString());
        }
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString printPDFFile()
    {
        HashMap<String, String> parameters = new HashMap<>();
        if(rationQuantities.size() > 0)
        {
            String fileLocation = aCalculator.generatePDFFile(rationQuantities, title, user.getCode() + "");
            aCalculator.printPDFFile(rationQuantities, user.getCode() + "", title);
            MetroAccordion pdfFileAccordion = new MetroAccordion();
            MetroIFrame pdfForIFrame = new MetroIFrame(fileLocation);
            pdfFileAccordion.addFrame("PDF Report For " + title, pdfForIFrame, "file-pdf");
            MetroAccordion printPDFFileAccordion = new MetroAccordion();
            printPDFFileAccordion.addFrame("Printing The PDF Report", new MetroHeading("The PDF report has been successfully printed", ""), "printer");
            MetroLayout printPDFFileLayout = new MetroLayout();
            printPDFFileLayout.addRow(printPDFFileAccordion);
            printPDFFileLayout.addRow(pdfFileAccordion);
            parameters.put("html", printPDFFileLayout.toString());
        }
        else
        {
            MetroAccordion anErrorAccordion = new MetroAccordion();
            anErrorAccordion.addFrame("No Ration Has Been Created", new MetroHeading("No Ration Has Been Created", ""), "warning");
            parameters.put("html", anErrorAccordion.toString());
        }
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString emailPDFFileInvoked(String emailAddress)
    {
        String headingTitle = "";
        if(aCalculator.emailPDFFile(rationQuantities, title, user.getCode() + "", emailAddress))
            headingTitle = "The ration report was successfully emailed to " + emailAddress;
        else
            headingTitle = "The ration report was not emailed to " + emailAddress;
        MetroLayout emailPDFFileLayout = new MetroLayout();
        emailPDFFileLayout.addRow(new MetroProgressBar(100, "cyan"));
        emailPDFFileLayout.addRow(new MetroHeading(headingTitle, ""));
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", emailPDFFileLayout.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createSettingsMenu(String messageContent)
    {
        String numberOfPlaces = "";
        String currencySymbol = "";
        ArrayList<String> existingSettings = aCalculator.getCalculatorSettings();
        if(existingSettings.size() == 2)
        {
            numberOfPlaces = existingSettings.get(0);
            currencySymbol = existingSettings.get(1);
        }
        MetroLayout settingsMenuLayout = new MetroLayout();
        if(messageContent.length() > 0)
        {
            settingsMenuLayout.addEmptyRows(2);
            settingsMenuLayout.addRow(new MetroPopover(messageContent, "cyan", "top"), new ArrayList<>(Arrays.asList(2, 8, 2)));
            settingsMenuLayout.addEmptyRows(2);
        }
        MetroLayout numberOfPlacesLayout = new MetroLayout();
        numberOfPlacesLayout.addEmptyRows(2);
        MetroTextField numberOfPlacesTextBox = new MetroTextField("Please enter the number of places","list-numbered", "text",
        "numberOfPlacesTextBox", numberOfPlaces);
        numberOfPlacesLayout.addRow(numberOfPlacesTextBox);
        numberOfPlacesLayout.addEmptyRows(2);
        MetroPanel numberOfPlacesPanel = new MetroPanel("Please enter the number of places", "", "list-numbered", numberOfPlacesLayout);
        settingsMenuLayout.addRow(numberOfPlacesPanel);
        MetroLayout currencySymbolLayout = new MetroLayout();
        currencySymbolLayout.addEmptyRows(2);
        MetroTextField currencySymbolTextBox = new MetroTextField("Please enter the currency symbol", "eur", "text",
        "currencySymbolTextBox", currencySymbol);
        currencySymbolLayout.addRow(currencySymbolTextBox);
        currencySymbolLayout.addEmptyRows(2);
        MetroPanel currencySymbolPanel = new MetroPanel("Please enter the currency symbol", "", "eur", currencySymbolLayout);
        settingsMenuLayout.addRow(currencySymbolPanel);
        settingsMenuLayout.addRow(new MetroCommandButton("Save", "Save Your Settings", "checkmark",
        "saveSettingsMenu();", "success"), new ArrayList<>(Arrays.asList(4, 4, 4)));
        MetroAccordion settingsMenuAccordion = new MetroAccordion();
        settingsMenuAccordion.addFrame("Calculator Settings", settingsMenuLayout, "cog");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", settingsMenuAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString saveSettingsMenu(String numberOfPlaces, String currencySymbol)
    {
        aCalculator.updateCalculatorSettings(numberOfPlaces, currencySymbol);
        return createSettingsMenu("The calculator settings were updated at " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
    }
    public JSONString addACommodityMenu()
    {
        MetroLayout createCommodityLayout = new MetroLayout();
        ArrayList<String> tableColumnTitles = aCalculator.getTableColumnTitles("rations");
        tableColumnTitles.remove(0);
        for(int counter = 0; counter < tableColumnTitles.size(); counter++)
        {
            createCommodityLayout.addEmptyRows(1);
            createCommodityLayout.addRow(new MetroTextField("Please enter a value for " + tableColumnTitles.get(counter), "pencil",
            "text", "createCommodityTextField_" + counter));
            createCommodityLayout.addEmptyRows(1);
        }
        createCommodityLayout.addRow(new MetroCommandButton("Save", "Save Your Commodity", "checkmark",
        "saveNewCommodity('createCommodityTextField', " + tableColumnTitles.size() + ");", "success"),
        new ArrayList<>(Arrays.asList(4, 4, 4)));
        MetroAccordion createCommodityAccordion = new MetroAccordion();
        createCommodityAccordion.addFrame("Add New Commodity", createCommodityLayout, "plus");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", createCommodityAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString saveNewCommodity(JSArray anArray)
    {
        ArrayList<String> newParameters = new ArrayList<>();
        for(int counter = 0; counter < anArray.length(); counter++)
            newParameters.add(anArray.get(counter).getStringValue());
        aCalculator.insertNewCommodity(newParameters);
        return getCommodities(false);
    }
}