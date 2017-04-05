package HTMLPages;
import Entities.*;
import HTMLControls.*;
import Models.*;
import Utilities.*;
import com.teamdev.jxbrowser.chromium.JSONString;

import javax.swing.*;
import java.util.*;
public class DashboardPage
{
    private Dashboard aDashboard;
    private static User anUser;
    public DashboardPage()
    {
        ;
    }
    public void setUser(User anUser)
    {
        DashboardPage.anUser = anUser;
    }
    public JSONString createDashboardPage()
    {
        this.aDashboard = new Dashboard(anUser.getCode() + "");
        MetroFluentMenu dashboardFluentMenu = new MetroFluentMenu("dashboardFluentMenu", "Main Menu", "createMainMenu();",
        new ArrayList<>(Collections.singletonList("Prices")));
        ArrayList<MetroFluentButton> buttons = new ArrayList<>();
        buttons.add(new MetroFluentButton("BarChart Format", "chart-bars","createCommodityTiles('viewPricesInBarChartFormat');"));
        buttons.add(new MetroFluentButton("LineChart Format", "chart-line","createCommodityTiles('viewPricesInLineChartFormat');"));
        buttons.add(new MetroFluentButton("Tabular Format", "table", "createCommodityTiles('viewPricesInTabularFormat');"));
        MetroFluentMenuPanelGroup aPricesGroup = new MetroFluentMenuPanelGroup("Prices", buttons);
        buttons.clear();
        buttons.add(new MetroFluentButton("Generate PDF File", "file-pdf", "generatePDFFileForCommodityPrices();"));
        buttons.add(new MetroFluentButton("Email PDF File", "mail-read", "emailPDFFileForCommodityPrices();"));
        buttons.add(new MetroFluentButton("Print PDF File", "printer", "printPDFFileForCommodityPrices();"));
        MetroFluentMenuPanelGroup quickActionsGroup = new MetroFluentMenuPanelGroup("Quick Actions", buttons);
        dashboardFluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(aPricesGroup, quickActionsGroup)));
        MetroLayout dashboardLayout = new MetroLayout();
        dashboardLayout.addRow(dashboardFluentMenu);
        MetroAccordion preloaderAccordion = new MetroAccordion();
        MetroPreloader aPreLoader = new MetroPreloader();
        MetroLayout preloaderLayout = new MetroLayout();
        preloaderLayout.addRow(aPreLoader, new ArrayList<>(Arrays.asList(5, 2, 5)));
        preloaderAccordion.addFrame("Retrieving The Latest Commodity Prices", preloaderLayout, "loop2");
        dashboardLayout.addRow(new MetroUpdatePanel("dashboardUpdatePanel", preloaderAccordion));
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", dashboardLayout.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createCommodityTiles(String onClickEvent)
    {
        if(aDashboard.getCommodityPrices().size() == 0)
            aDashboard.addDashboard();
        LinkedHashMap<String, CommodityPrices> commodityPrices  = aDashboard.getCommodityPrices();
        List<MetroComponent> commodityTiles = new ArrayList<>();
        for(String aCommodityTitle: commodityPrices.keySet())
        {
            commodityTiles.add(new MetroTile(onClickEvent + "('" + aCommodityTitle + "');", "cyan", aCommodityTitle,
            "florist", ""));
        }
        MetroLayout commoditiesLayout = new MetroLayout();
        commoditiesLayout.addMultipleRows(commodityTiles, 3, 1, 3, 0, 2);
        MetroAccordion commoditiesAccordion = new MetroAccordion();
        commoditiesAccordion.addFrame("Commodities", commoditiesLayout, "florist");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", commoditiesAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString createMainMenu()
    {
        MetroLayout mainMenuLayout = new MetroLayout();
        MetroTile mainMenuTile = new MetroTile("getPortalPage();", "cyan", "Main Menu", "menu", "");
        MetroTile logOutTile = new MetroTile("loadHTML5Edition();", "cyan", "Log Out", "exit", "");
        MetroTile exitTile = new MetroTile("exit();", "cyan", "Exit", "exit", "");
        mainMenuLayout.addRow(new ArrayList<>(Arrays.asList(mainMenuTile, logOutTile, exitTile)), new ArrayList<>(Arrays.asList(1, 3, 0, 1, 3, 0, 1, 3, 0)));
        MetroAccordion mainMenuAccordion = new MetroAccordion();
        mainMenuAccordion.addFrame("Main Menu", mainMenuLayout, "menu");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", mainMenuAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString viewPricesInTabularFormat(String commodityIdentifier)
    {
        LinkedHashMap<String, CommodityPrices> commodityPrices =  aDashboard.getCommodityPrices();
        CommodityPrices currentCommodityPrices = commodityPrices.get(commodityIdentifier);
        ArrayList<String> currentCommodityPricesHeadings = currentCommodityPrices.getHeadings();
        currentCommodityPricesHeadings.add(0, "Code");
        ArrayList<ArrayList<String>> tableRows = aDashboard.getListOfCommodityPricesForSelectedCommodity(currentCommodityPrices, true);
        MetroDataTable currentCommodityDataTable = new MetroDataTable("currentCommodityDataTable", currentCommodityPrices.getHeadings(), tableRows,
        new ArrayList<>());
        MetroAccordion currentCommodityAccordion = new MetroAccordion();
        currentCommodityAccordion.addFrame("Current Prices For " + currentCommodityPrices.getCommodityTitle(), currentCommodityDataTable, "eur");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", currentCommodityAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString viewPricesInBarChartFormat(String commodityTitle)
    {

        HashMap<String, String> parameters = new HashMap<>();
        MetroAccordion viewPricesInBarChartFormatAccordion = new MetroAccordion();
        MetroLayout barChartLayout = new MetroLayout();
        //barChartLayout.addRow(new MetroCanvas("barChart", "500", "500"));
        LinkedHashMap<String, CommodityPrices> commodityPrices = aDashboard.getCommodityPrices();
        CommodityPrices currentCommodityPrices = commodityPrices.get(commodityTitle);
        ArrayList<String> currentCommodityPricesHeadings = currentCommodityPrices.getHeadings();
        ArrayList<ArrayList<String>> tableRows = aDashboard.getListOfCommodityPricesForSelectedCommodity(currentCommodityPrices, false);
        LinkedHashMap<String, ArrayList<String>> prices = currentCommodityPrices.getPrices();
        List<String> chartTitles = aDashboard.getMonths(currentCommodityPrices);
        List<Double> chartValues = aDashboard.getLastPrices(currentCommodityPrices);
        System.out.println("CHART TITLES");
        for(String aChartTitle : chartTitles)
            System.out.println("A CHART TITLE: " + aChartTitle);
        for(Double aChartValue : chartValues)
            System.out.println("A CHART VALUE: " + aChartValue);
        LinkedHashMap<String, List<Double>> lastPrices = new LinkedHashMap<>();
        lastPrices.put("Spot Prices", chartValues);

        /*ArrayList<String> currentTableTitles = new ArrayList<>();
        ArrayList<ArrayList<Double>> currentTablePrices = new ArrayList<>();
        ArrayList<Double> lastTablePrices = new ArrayList<>();
        for(ArrayList<String> aTableRow : tableRows)
        {
            currentTableTitles.add(aTableRow.get(0));
            lastTablePrices.add(Double.parseDouble(aTableRow.get(1)));
        }
        currentTablePrices.add(lastTablePrices);*/

        //ArrayList<String> currentCommodityMonths = currentCommodityPrices.getMonths();
        //ArrayList<String> currentCommodityLastPrices = currentCommodityPrices.getLastPrices();
        //System.out.println("CURRENT COMMODITY MONTHS");
        //for(int counter = 0; counter < currentCommodityMonths.size(); counter++)
            //System.out.println("CURRENT COMMODITY MONTH: " + currentCommodityMonths.get(counter));
        //for(String aCurrentCommodityMonth : currentCommodityMonths)
            //System.out.println("CURRENT COMMODITY MONTH: " + aCurrentCommodityMonth);
        //System.out.println("CURRENT COMMODITY LAST PRICES");
        //for(int counter = 0; counter < currentCommodityLastPrices.size(); counter++)
            //System.out.println("CURRENT COMMODITY LAST PRICE: " + currentCommodityLastPrices.get(counter));
        //for(String aCurrentCommodityLastPrice : currentCommodityLastPrices)
            //System.out.println("CURRENT COMMODITY LAST PRICE: " + aCurrentCommodityLastPrice);
        MetroChart aChart = new MetroChart("barChart", chartTitles, lastPrices);
        barChartLayout.addRow(aChart);
        viewPricesInBarChartFormatAccordion.addFrame("Bar Chart Format", barChartLayout, "chart-bars");
        parameters.put("html", viewPricesInBarChartFormatAccordion.toString());
        //parameters.put("javascript", aChart.getScriptContent());
        System.out.println("ABOUT TO RETURN HTML AND JAVASCRIPT");
        //return Utilities.convertHashMapToJSON(parameters);
        System.out.println("FORMATTED JSON");
        System.out.println(Utilities.createChart(parameters, new ArrayList<>(Arrays.asList("M", "T", "W", "T", "F", "S", "S")), new LinkedHashMap<>()).getValue());
        return Utilities.createChart(parameters, new ArrayList<>(Arrays.asList("M", "T", "W", "T", "F", "S", "S")), new LinkedHashMap<>());
    }
    public JSONString viewPricesInLineChartFormat(String commodityTitle)
    {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", "");
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString generatePDFFile()
    {
        HashMap<String, String> parameters = new HashMap<>();
        if(aDashboard.getCommodityPrices().size() > 0)
        {
            String fileName = aDashboard.generatePDFFile();
            MetroIFrame generatePDFFileIFrame = new MetroIFrame(fileName);
            MetroAccordion generatePDFFileAccordion = new MetroAccordion();
            generatePDFFileAccordion.addFrame("PDF Report Created For Commodity Prices", generatePDFFileIFrame, "file-pdf");
            parameters.put("html", generatePDFFileAccordion.toString());
        }
        else
        {
            MetroAccordion errorAccordion = new MetroAccordion();
            errorAccordion.addFrame("No Prices Are Currently Available", new MetroHeading("No Prices Are Currently Available", ""), "warning");
            parameters.put("html", errorAccordion.toString());
        }
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString emailPDFFile()
    {
        HashMap<String, String> parameters = new HashMap<>();
        if(aDashboard.getCommodityPrices().size() > 0)
        {
            String fileName = aDashboard.generatePDFFile();
            aDashboard.printPDFFile();
            MetroLayout emailPDFFileLayout = new MetroLayout();
            emailPDFFileLayout.addRow(new MetroProgressBar(50, "cyan"));
            emailPDFFileLayout.addEmptyRows(2);
            emailPDFFileLayout.addRow(new MetroTextField("Please enter the email address of the recipient", "mail-read", "text",
            "emailAddressTextBox"));
            emailPDFFileLayout.addEmptyRows(2);
            emailPDFFileLayout.addRow(new MetroCommandButton("Send", "Send Your Email", "checkmark",
            "emailPDFFileInvokedForCommodityPrices();", "success"), new ArrayList<>(Arrays.asList(4, 4, 4)));
            MetroAccordion emailPDFFileAccordion = new MetroAccordion();
            emailPDFFileAccordion.addFrame("Email PDF Report", emailPDFFileLayout, "mail-read");
            MetroUpdatePanel emailPDFFileUpdatePanel = new MetroUpdatePanel("emailPDFFileUpdatePanel", emailPDFFileAccordion);
            MetroAccordion generatePDFFileAccordion = new MetroAccordion();
            MetroIFrame generatePDFFileIFrame = new MetroIFrame(fileName);
            generatePDFFileAccordion.addFrame("PDF Report Created For Commodity Prices", generatePDFFileIFrame, "file-pdf");
            MetroLayout emailPDFFileMasterLayout = new MetroLayout();
            emailPDFFileMasterLayout.addRow(emailPDFFileUpdatePanel);
            emailPDFFileMasterLayout.addRow(generatePDFFileAccordion);
            parameters.put("html", emailPDFFileMasterLayout.toString());
        }
        else
        {
            MetroAccordion errorAccordion = new MetroAccordion();
            errorAccordion.addFrame("No Prices Are Currently Available", new MetroHeading("No Prices Are Currently Available", ""), "warning");
            parameters.put("html", errorAccordion.toString());
        }
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString emailPDFFileInvoked(String emailAddress)
    {
        boolean emailPDFFile = aDashboard.emailPDFFile(emailAddress);
        MetroLayout emailPDFFileLayout = new MetroLayout();
        emailPDFFileLayout.addRow(new MetroProgressBar(100, "cyan"));
        if(emailPDFFile)
            emailPDFFileLayout.addRow(new MetroHeading("Your commodities report was successfully emailed to " + emailAddress, ""));
        else
            emailPDFFileLayout.addRow(new MetroHeading("Your commodities report was not emailed to " + emailAddress, ""));
        MetroAccordion emailPDFFileAccordion = new MetroAccordion();
        emailPDFFileAccordion.addFrame("Email PDF Report", emailPDFFileLayout, "mail-read");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", emailPDFFileAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString printPDFFile()
    {
        HashMap<String, String> parameters = new HashMap<>();
        if(aDashboard.getCommodityPrices().size() > 0)
        {
            String fileLocation = aDashboard.generatePDFFile();
            MetroAccordion printPDFFileAccordion = new MetroAccordion();
            printPDFFileAccordion.addFrame("Print PDF Report", new MetroHeading("Your commodities report was successfully printed", ""), "printer");
            MetroIFrame generatePDFFileIFrame = new MetroIFrame(fileLocation);
            MetroAccordion generatePDFFileAccordion = new MetroAccordion();
            generatePDFFileAccordion.addFrame("PDF Report Created For Commodity Prices", generatePDFFileIFrame, "file-pdf");
            MetroLayout printPDFFileMasterLayout = new MetroLayout();
            printPDFFileMasterLayout.addRow(printPDFFileAccordion);
            printPDFFileMasterLayout.addRow(generatePDFFileAccordion);
            parameters.put("html", printPDFFileMasterLayout.toString());
        }
        else
        {
            MetroAccordion errorAccordion = new MetroAccordion();
            errorAccordion.addFrame("No Prices Are Currently Available", new MetroHeading("No Prices Are Currently Available", ""), "warning");
            parameters.put("html", errorAccordion.toString());
        }
        return Utilities.convertHashMapToJSON(parameters);
    }
}
