package Models;
import Database.Database;
import RationCalculator.Ration;
import RationCalculator.RationCalculator;
import Frames.Components;
import Reckner.RecknerCommodity;
import Reckner.RecknerCommodityBuilder;
import Utilities.Email;
import Utilities.Printer;
import Utilities.Report;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Calculator extends Components
{
    private Database main;
    private String currencySymbol;
    public Calculator()
    {
        main = new Database();
        initialiseVariables();
    }
    private void initialiseVariables()
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("title", "Currency Symbol");
        ArrayList<ArrayList<String>> tableContents = main.getTableRows("settings", selectedParameters, new ArrayList<>(Collections.singletonList("value")),
        "");
        if(tableContents.size() > 0)
            currencySymbol = tableContents.get(0).get(0);
    }
    public ArrayList<Ration> getAvailableRations()
    {
        ArrayList<Ration> availableRations = new ArrayList<>();
        ArrayList<ArrayList<String>> contents = main.getTableRows("rations", new HashMap<>(), new ArrayList<>(), "");
        contents.forEach(x -> availableRations.add(new Ration(Integer.parseInt(x.get(0)), x.get(1), x.get(2), x.get(3), x.get(4), x.get(5), x.get(6), x.get(7), x.get(8),
        x.get(9), x.get(10), x.get(11), x.get(12), x.get(13))));
        return availableRations;
    }
    public ArrayList<String> getAvailableRationsAsStrings()
    {
        ArrayList<Ration> availableRations = getAvailableRations();
        ArrayList<String> rations = new ArrayList<>();
        availableRations.forEach(x -> rations.add(x.getFeed()));
        return rations;
    }
    public ArrayList<RecknerCommodity> getAvailableRecknerCommodities()
    {
        ArrayList<RecknerCommodity> availableCommodities = new ArrayList<>();
        ArrayList<ArrayList<String>> contents = main.getTableRows("recknercommodities", new HashMap<>(), new ArrayList<>(), "");
        contents.forEach(x -> availableCommodities.add(new RecknerCommodityBuilder().setCode(Integer.parseInt(x.get(0))).setCommodityTitle(x.get(1)).setCommodityPrice(Double.parseDouble(x.get(2))).setDm(Double.parseDouble(x.get(3))).setAsh(Double.parseDouble(x.get(4))).setCrudeProtein(Double.parseDouble(x.get(5))).setCrudeFibre(Double.parseDouble(x.get(6))).setOil(Double.parseDouble(x.get(7))).setNdf(Double.parseDouble(x.get(8))).setAdf(Double.parseDouble(x.get(9))).setEffectiveNdf(Double.parseDouble(x.get(10))).setOmd(Double.parseDouble(x.get(11))).setPdia(Double.parseDouble(x.get(12))).setPdin(Double.parseDouble(x.get(13))).setPdie(Double.parseDouble(x.get(14))).setStarch(Double.parseDouble(x.get(15))).setSugar(Double.parseDouble(x.get(16))).setUfl(Double.parseDouble(x.get(17))).setUfv(Double.parseDouble(x.get(18))).setLysdi(Double.parseDouble(x.get(19))).setMethdi(Double.parseDouble(x.get(20))).setCa(Double.parseDouble(x.get(21))).setP(Double.parseDouble(x.get(22))).setMg(Double.parseDouble(x.get(23))).setNa(Double.parseDouble(x.get(24))).setCu(Double.parseDouble(x.get(25))).setZn(Double.parseDouble(x.get(26))).setMn(Double.parseDouble(x.get(27))).setCo(Double.parseDouble(x.get(28))).setSe(Double.parseDouble(x.get(29))).setI(Double.parseDouble(x.get(30))).setVitaminA(Double.parseDouble(x.get(31))).setVitaminD(Double.parseDouble(x.get(32))).setVitaminE(Double.parseDouble(x.get(33))).setPal(Double.parseDouble(x.get(34))).setMe(Double.parseDouble(x.get(35))).createRecknerCommodity()));
        return availableCommodities;
    }
    public ArrayList<String> getAvailableRationTypes()
    {
        ArrayList<String> availableRationTypes = new ArrayList<>(Arrays.asList("Growing Cattle", "Finishing Cattle", "Dry Suckler Cow", "Lactating Suckler Cow"));
        Collections.sort(availableRationTypes);
        return availableRationTypes;
    }
    public void insertNewRationCalculation(String userID, String title, HashMap<Ration, Double> rationsWithQuantities)
    {
        int usersRationsMaxNumber = main.getMaxValueOfColumn("usersrations", "code");
        main.insertTableRow("usersrations", new ArrayList<>(Arrays.asList((usersRationsMaxNumber + 1)+ "", userID, title)));
        for(Map.Entry<Ration, Double> aRation : rationsWithQuantities.entrySet())
        {
            int usersRationsComponentsMaxNumber = main.getMaxValueOfColumn("usersrationscomponents", "code");
            main.insertTableRow("usersrationscomponents", new ArrayList<>(Arrays.asList((usersRationsComponentsMaxNumber + 1) + "",
            (usersRationsMaxNumber + 1) + "", aRation.getKey().getCode() + "", aRation.getValue() + "")));
        }
    }
    public Ration getRationFromSpecifiedTitle(String commodityTitle)
    {
        Database database = new Database();
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("feed", commodityTitle);
        ArrayList<ArrayList<String>> rationsTableContents = database.getTableRows("rations", selectedParameters, new ArrayList<>(), "");
        if(rationsTableContents.size() > 0)
        {
            List<String> selectedTableContents = rationsTableContents.get(0);
            return new Ration(Integer.parseInt(selectedTableContents.get(0)), selectedTableContents.get(1), selectedTableContents.get(2),
            selectedTableContents.get(3), selectedTableContents.get(4), selectedTableContents.get(5), selectedTableContents.get(6), selectedTableContents.get(7),
            selectedTableContents.get(8), selectedTableContents.get(9), selectedTableContents.get(10), selectedTableContents.get(11), selectedTableContents.get(12),
            selectedTableContents.get(13));
        }
        return null;
    }
    public ArrayList<String> getCalculationResults(HashMap<Ration, Double> rationsWithQuantities, String title)
    {
        ArrayList<String> calculationResults = new ArrayList<>();
        RationCalculator aCalculator = new RationCalculator(rationsWithQuantities, title);
        calculationResults.add("Dry Matter: " + aCalculator.calculateDryMatter());
        calculationResults.add("Crude Protein: " + aCalculator.calculateCrudeProtein());
        calculationResults.add("NDF: " + aCalculator.calculateNDF());
        calculationResults.add("Starch & Sugars: " + aCalculator.calculateNDF());
        calculationResults.add("Oil: " + aCalculator.calculateOil());
        calculationResults.add("Dry Matter: £" + aCalculator.calculateRationCostDryMatter());
        calculationResults.add("Fresh Weight: £" + aCalculator.calculateRationCostFreshWeight());
        return calculationResults;
    }
    public LinkedHashMap<String, String> getCalculationResultsWithTitles(HashMap<Ration, Double> rationsWithQuantities, String title)
    {
        LinkedHashMap<String, String> calculationResults = new LinkedHashMap<>();
        RationCalculator aCalculator = new RationCalculator(rationsWithQuantities, title);
        calculationResults.put("Dry Matter", aCalculator.calculateDryMatter().toString());
        calculationResults.put("Crude Protein", aCalculator.calculateCrudeProtein().toString());
        calculationResults.put("NDF", aCalculator.calculateNDF().toString());
        calculationResults.put("Starch & Sugars", aCalculator.calculateStarchAndSugars().toString());
        calculationResults.put("Oil", aCalculator.calculateOil().toString());
        calculationResults.put("Dry Matter", "£" + aCalculator.calculateRationCostDryMatter());
        calculationResults.put("Fresh Weight", "£" + aCalculator.calculateRationCostFreshWeight());
        return calculationResults;
    }
    public String generatePDFFile(HashMap<Ration, Double> rationsWithQuantities, String title, String userID)
    {
        RationCalculator aCalculator = new RationCalculator(rationsWithQuantities, title);
        int userRationID = getUserRationID(userID, title);
        if(!new File("rations/" + userRationID + ".pdf").exists())
        {
            Report aReport = new Report("rations/" + userRationID + ".pdf");
            ArrayList<String> reportContent = new ArrayList<>();
            reportContent.add("Title: " + aCalculator.getTitle());
            reportContent.add("Date: " + new SimpleDateFormat("dd/mm/yyyy hh:mm:ss").format(aCalculator.getDate()));
            HashMap<String, String> selectedParameters = new HashMap<>();
            selectedParameters.put("code", userID);
            ArrayList<ArrayList<String>> userDetails = main.getTableRows("users", selectedParameters, new ArrayList<>(), "");
            if (userDetails.size() > 0) {
                reportContent.add("Printed  By: " + userDetails.get(0).get(3) + " " + userDetails.get(0).get(4));
                reportContent.add("Email Address: " + userDetails.get(0).get(5));
                reportContent.add("Phone Number: " + userDetails.get(0).get(6));
            }
            for (Map.Entry<Ration, Double> aFeedCost : rationsWithQuantities.entrySet())
                reportContent.add(aFeedCost.getKey().getFeed() + " " + aFeedCost.getValue());
            reportContent.add("Total Fresh Intake: " + aCalculator.getTotalFreshIntake());
            reportContent.add("Total Dry Matter Intake: " + aCalculator.getTotalFreshIntake() * (aCalculator.calculateDryMatter() / 100));
            reportContent.addAll(getCalculationResults(rationsWithQuantities, title));
            aReport.addContent(reportContent);
        }
        return "file:///"  + System.getProperty("user.dir").replace("\\", "/") + "/rations/" + userRationID + ".pdf";
    }
    public boolean emailPDFFile(HashMap<Ration, Double> rationsWithQuantities, String title, String userID, String emailAddress)
    {
        int userRationID = getUserRationID(userID, title);
        if(!new File("rations/" + userRationID + ".pdf").exists())
            generatePDFFile(rationsWithQuantities, title, userID);
        //Email anEmail = new Email("stephencullinan1991@gmail.com", "TiobraidArann2016");
        Email anEmail = new Email();
        return anEmail.sendMessage(emailAddress, "Ration Report", "Dear Sir/Madam,\n\nPlease find attached the ration report as calculated " +
        "on the application.\n\nYours faithfully,\nStephen Cullinan", "rations/" + userRationID + ".pdf", "Ration Report.pdf");
    }
    public void printPDFFile(HashMap<Ration, Double> rationsWithQuantities, String userID, String title)
    {
        int userRationID = getUserRationID(userID, title);
        if(!new File("rations/" + userRationID  + ".pdf").exists())
            generatePDFFile(rationsWithQuantities, userID, title);
        new Printer("rations/" + userRationID + ".pdf");
    }
    public ArrayList<ArrayList<String>> getUsersRations(String userID)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("user", userID);
        return main.getTableRows("usersrations", selectedParameters, new ArrayList<>(Arrays.asList("code", "title")), "");
    }
    public ArrayList<String> getUsersRationsTitles()
    {
        ArrayList<String> usersRationsTitles = main.getColumnTitles("usersrations");
        usersRationsTitles.remove(1);
        return usersRationsTitles;
    }
    public ArrayList<ArrayList<String>> getUsersRationsComponents(String usersRationsCode)
    {
        HashMap<String, String> desiredParameters = new HashMap<>();
        desiredParameters.put("usersrations.code", usersRationsCode);
        return main.getJoinedTableRows(new ArrayList<>(Arrays.asList("usersrations", "usersrationscomponents", "rations")),
        new ArrayList<>(Arrays.asList("usersrations.code", "usersrationscomponents.userration", "rations.code", "usersrationscomponents.ration")),
        desiredParameters, new ArrayList<>(Arrays.asList("usersrations.title", "rations.feed", "usersrationscomponents.freshweight")), "");
    }
    public ArrayList<String> getDetailedUsersRationsTitles()
    {
        return new ArrayList<>(Arrays.asList("Title", "Feed", "Fresh Weight"));
    }
    public void removeUserRation(String rationCode)
    {
        HashMap<String, String> currentParameters = new HashMap<>();
        currentParameters.put("userration", rationCode);
        main.removeTableRow("usersrationscomponents", currentParameters);
        currentParameters = new HashMap<>();
        currentParameters.put("code", rationCode);
        main.removeTableRow("usersrations", currentParameters);
    }
    public void removeRation(String rationCode)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", rationCode);
        main.removeTableRow("rations", selectedParameters);
    }
    public ArrayList<ArrayList<String>> getTableContents(String tableName)
    {
        return main.getTableRows(tableName, new HashMap<>(), new ArrayList<>(), "");
    }
    public ArrayList<ArrayList<String>> getRationDetails(String userRationIdentifier)
    {
        HashMap<String, String> desiredParameters = new HashMap<>();
        desiredParameters.put("code", userRationIdentifier);
        return main.getTableRows("usersrations", desiredParameters, new ArrayList<>(), "");
    }
    public ArrayList<String> getTableColumnTitles(String tableName)
    {
        return main.getColumnTitles(tableName);
    }

    public void updateCalculatorSettings(String numberOfPlaces, String currencySymbol)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("title", "Number Of Places");
        ArrayList<ArrayList<String>> existingNumberOfPlacesSetting = main.getTableRows("settings", selectedParameters,
        new ArrayList<>(Collections.singletonList("title")),"");
        if(existingNumberOfPlacesSetting.size() > 0)
        {
            HashMap<String, String> updatedParameters = new HashMap<>();
            updatedParameters.put("value", numberOfPlaces);
            main.updateTableRow("settings", updatedParameters, selectedParameters);
        }
        else
        {
            int maximumValue = main.getMaxValueOfColumn("settings", "code");
            main.insertTableRow("settings", new ArrayList<>(Arrays.asList(++maximumValue + "", "Number Of Places", numberOfPlaces)));
        }
        selectedParameters.put("title", "Currency Symbol");
        ArrayList<ArrayList<String>> existingCurrencySymbolSetting = main.getTableRows("settings", selectedParameters,
        new ArrayList<>(Collections.singletonList("title")), "");
        if(existingCurrencySymbolSetting.size() > 0)
        {
            HashMap<String, String> updatedParameters = new HashMap<>();
            updatedParameters.put("value", currencySymbol);
            main.updateTableRow("settings", updatedParameters, selectedParameters);
        }
        else
        {
            int maximumValue = main.getMaxValueOfColumn("settings", "code");
            main.insertTableRow("settings", new ArrayList<>(Arrays.asList(++maximumValue + "", "Currency Symbol", currencySymbol)));
        }
    }
    public ArrayList<String> getCalculatorSettings()
    {
        ArrayList<String> calculatorSettings = new ArrayList<>();
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("title", "Number Of Places");
        ArrayList<ArrayList<String>> numberOfPlaces = main.getTableRows("settings", selectedParameters, new ArrayList<>(Collections.singletonList("value")),
        "");
        if(numberOfPlaces.size() > 0)
            calculatorSettings.add(numberOfPlaces.get(0).get(0));
        selectedParameters.put("title", "Currency Symbol");
        ArrayList<ArrayList<String>> currencySymbol = main.getTableRows("settings", selectedParameters, new ArrayList<>(Collections.singletonList("value")),
        "");
        if(currencySymbol.size() > 0)
            calculatorSettings.add(currencySymbol.get(0).get(0));
        return calculatorSettings;
    }
    public void insertNewCommodity(ArrayList<String> newCommodity)
    {
        int maximumNumber = main.getMaxValueOfColumn("rations", "code");
        newCommodity.add(0, ++maximumNumber + "");
        main.insertTableRow("rations", newCommodity);
    }
    private int getUserRationID(String userID, String title)
    {
        HashMap<String, String> desiredParameters = new HashMap<>();
        desiredParameters.put("user", userID);
        desiredParameters.put("title", title);
        ArrayList<ArrayList<String>> rationNumber = main.getTableRows("usersrations", desiredParameters, new ArrayList<>(Arrays.asList("code")),
        "");
        if(rationNumber.size() > 0)
            return Integer.parseInt(rationNumber.get(0).get(0));
        return 0;
    }
}