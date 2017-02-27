package Models;
import Database.Database;
import Entities.Ration;
import Entities.RationCalculator;
import Frames.Components;
import Utilities.Email;
import Utilities.Printer;
import Utilities.Report;
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
    private Database main;
    public Calculator()
    {
        main = new Database();
    }
    public ArrayList<Ration> getAvailableRations()
    {
        ArrayList<Ration> availableRations = new ArrayList<>();
        ArrayList<ArrayList<String>> contents = main.getTableRows("rations", new HashMap<>(), new ArrayList<>(), "");
        contents.forEach(x -> availableRations.add(new Ration(Integer.parseInt(x.get(0)), x.get(1), x.get(2), x.get(3), x.get(4), x.get(5), x.get(6), x.get(7), x.get(8),
        x.get(9), x.get(10), x.get(11), x.get(12), x.get(13))));
        return availableRations;
    }
    public ArrayList<String> getAvailableRationTypes()
    {
        return new ArrayList<>(Arrays.asList("Growing Cattle", "Finishing Cattle", "Dry Suckler Cow", "Lactating Suckler Cow"));
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
    public void generatePDFFile(HashMap<Ration, Double> rationsWithQuantities, String title, String userID)
    {
        RationCalculator aCalculator = new RationCalculator(rationsWithQuantities, title);
        int userRationID = getUserRationID(userID, title);
        Report aReport = new Report("rations/" + userRationID + ".pdf");
        ArrayList<String> reportContent = new ArrayList<>();
        reportContent.add("Title: " + aCalculator.getTitle());
        reportContent.add("Date: " + new SimpleDateFormat("dd/mm/yyyy hh:mm:ss").format(aCalculator.getDate()));
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
        reportContent.addAll(getCalculationResults(rationsWithQuantities, title));
        aReport.addContent(reportContent);
    }
    public void emailPDFFile(HashMap<Ration, Double> rationsWithQuantities, String title, String userID, String emailAddress)
    {
        int userRationID = getUserRationID(userID, title);
        if(!new File("rations/" + userRationID + ".pdf").exists())
            generatePDFFile(rationsWithQuantities, title, userID);
        Email anEmail = new Email("stephencullinan1991@gmail.com", "TiobraidArann2016");
        anEmail.sendMessage(emailAddress, "Ration Report", "Dear Sir/Madam,\n\nPlease find attached the ration report as calculated " +
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
    public ArrayList<String> getTableColumnTitles(String tableName)
    {
        return main.getColumnTitles(tableName);
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