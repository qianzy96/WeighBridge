package Models;

import WeighBridge.*;
import Frames.Components;
import Utilities.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import Database.Database;

public class WeighBridge extends Components
{
    private Database main;
    private DocketType selectedDocketType;
    private Consignee selectedConsignee;
    private Driver selectedDriver;
    private Commodity selectedCommodity;
    private FirstWeight selectedFirstWeight;
    private SecondWeight selectedSecondWeight;
    public WeighBridge()
    {
        main = new Database();
    }
    public ArrayList<DocketType> getDocketTypes()
    {
        ArrayList<ArrayList<String>> docketTypes = main.getTableRows("dockettypes", new HashMap<>(), new ArrayList<>(), "dockettype");
        ArrayList<DocketType> docketTypesObjects = new ArrayList<>();
        docketTypes.forEach(x -> docketTypesObjects.add(new DocketType(Integer.parseInt(x.get(0)), x.get(1))));
        return docketTypesObjects;
    }
    public DocketType getDocketType(int docketTypeID)
    {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("code", Integer.toString(docketTypeID));
        ArrayList<ArrayList<String>> docketTypes = main.getTableRows("dockettypes", parameters, new ArrayList<>(), "dockettype");
        return new DocketType(Integer.parseInt(docketTypes.get(0).get(0)), docketTypes.get(0).get(1));
    }
    public ArrayList<Consignee> getConsignees(String tableName)
    {
        ArrayList<Consignee> consigneesObjects = new ArrayList<>();
        ArrayList<ArrayList<String>> consignees = main.getTableRows(tableName, new HashMap<>(), new ArrayList<>(), "lastname");
        consignees.forEach(x -> consigneesObjects.add(new Consignee(Integer.parseInt(x.get(0)), x.get(1), x.get(2))));
        return consigneesObjects;
    }
    public Consignee getConsignee(int consigneeID, String tableName)
    {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("code", Integer.toString(consigneeID));
        ArrayList<ArrayList<String>> consigneeTypes = main.getTableRows(tableName, parameters, new ArrayList<>(), "lastname");
        return new Consignee(Integer.parseInt(consigneeTypes.get(0).get(0)), consigneeTypes.get(0).get(1), consigneeTypes.get(0).get(2));
    }
    public ArrayList<Commodity> getCommodities()
    {
        ArrayList<Commodity> commoditiesObjects = new ArrayList<>();
        ArrayList<ArrayList<String>> commodities = main.getTableRows("commodities", new HashMap<>(), new ArrayList<>(), "title");
        commodities.forEach(x -> commoditiesObjects.add(new Commodity(Integer.parseInt(x.get(0)), x.get(1))));
        return commoditiesObjects;
    }
    public Commodity getCommodity(int commodityID)
    {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("code", Integer.toString(commodityID));
        ArrayList<ArrayList<String>> commodity = main.getTableRows("commodities", parameters, new ArrayList<>(), "title");
        return new Commodity(Integer.parseInt(commodity.get(0).get(0)), commodity.get(0).get(1));
    }
    public Driver getDriver(int driverID)
    {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("code", Integer.toString(driverID));
        ArrayList<ArrayList<String>> driverTypes = main.getTableRows("drivers", new HashMap<>(), new ArrayList<>(), "lastname");
        return new Driver(Integer.parseInt(driverTypes.get(0).get(0)), driverTypes.get(0).get(1), driverTypes.get(0).get(2));
    }
    public ArrayList<Driver> getDrivers()
    {
        ArrayList<Driver> driverObjects = new ArrayList<>();
        ArrayList<ArrayList<String>> drivers = main.getTableRows("drivers", new HashMap<>(), new ArrayList<>(), "lastname");
        drivers.forEach(x -> driverObjects.add(new Driver(Integer.parseInt(x.get(0)), x.get(1), x.get(2))));
        return driverObjects;
    }
    public Driver insertNewDriver(String firstName, String lastName)
    {
        int maximumValue = main.getMaxValueOfColumn("drivers", "code");
        Driver aDriver = new Driver(maximumValue + 1, firstName, lastName);
        main.insertTableRow("drivers", aDriver.toList());
        selectedDriver = aDriver;
        return selectedDriver;
    }
    public Commodity insertNewCommodity(String commodityTitle)
    {
        int maximumValue = main.getMaxValueOfColumn("commodities", "code");
        Commodity aCommodity = new Commodity(maximumValue + 1, commodityTitle);
        main.insertTableRow("commodities", aCommodity.toList());
        selectedCommodity = aCommodity;
        return selectedCommodity;
    }
    public Consignee insertNewConsignee(String firstName, String lastName, String consigneeTable)
    {
        int maximumValue = main.getMaxValueOfColumn(consigneeTable, "code");
        Consignee aConsignee = new Consignee(maximumValue + 1, firstName, lastName);
        main.insertTableRow(consigneeTable, aConsignee.toList());
        selectedConsignee = aConsignee;
        return selectedConsignee;
    }
    public void insertNewFirstWeight()
    {
        int maximumValue = main.getMaxValueOfColumn("firstweights", "code");
        selectedFirstWeight = new FirstWeight(maximumValue + 1, selectedDriver, selectedCommodity, 45000, new Date(), selectedDocketType, selectedConsignee);
        main.insertTableRow("firstweights", selectedFirstWeight.toList());
    }
    public void insertNewSecondWeight(String firstWeightIdentifer, String firstWeightValue, String firstWeightDate)
    {
        selectedFirstWeight = new FirstWeight(Integer.parseInt(firstWeightIdentifer), selectedDriver, selectedCommodity, Double.parseDouble(firstWeightValue),
        Utilities.createDate(firstWeightDate, "yyyy-MM-dd HH:mm:ss"), selectedDocketType, selectedConsignee);
        int maximumValue = main.getMaxValueOfColumn("secondweights", "code");
        selectedSecondWeight = new SecondWeight(maximumValue + 1, 15000, new Date(), selectedFirstWeight);
        main.insertTableRow("secondweights", selectedSecondWeight.toList());
    }
    public ArrayList<ArrayList<String>> getDocketsAwaitingSecondWeightment()
    {
        ArrayList<ArrayList<String>> firstWeights = main.getTableRows("firstweights", new HashMap<>(), new ArrayList<>(), "");
        ArrayList<ArrayList<String>> secondWeights = main.getTableRows("secondweights", new HashMap<>(), new ArrayList<>(), "");
        ArrayList<ArrayList<String>> firstWeightsAwaitingSecondWeight = new ArrayList<>();
        for(int counter = 0; counter < firstWeights.size(); counter++)
        {
            Boolean weightLocated = false;
            for(int index = 0; index < secondWeights.size() && !weightLocated; index++)
                if(secondWeights.get(index).get(3).equals(firstWeights.get(counter).get(0)))
                    weightLocated = true;
            if(!weightLocated)
                firstWeightsAwaitingSecondWeight.add(firstWeights.get(counter));
        }
        return firstWeightsAwaitingSecondWeight;
    }
    public void retrieveParametersForSelectedSecondWeight(String driverIdentifier, String commodityIdentifier, String docketTypeIdentifier, String consigneeIdentifier)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", driverIdentifier);
        ArrayList<ArrayList<String>> driverDetails = main.getTableRows("drivers", selectedParameters, new ArrayList<>(), "");
        driverDetails.forEach(y -> selectedDriver = new Driver(Integer.parseInt(y.get(0)), y.get(1), y.get(2)));
        selectedParameters.put("code", commodityIdentifier);
        ArrayList<ArrayList<String>> commodityDetails = main.getTableRows("commodities", selectedParameters, new ArrayList<>(), "");
        commodityDetails.forEach(y -> selectedCommodity = new Commodity(Integer.parseInt(y.get(0)), y.get(1)));
        selectedParameters.put("code", docketTypeIdentifier);
        ArrayList<ArrayList<String>> docketTypeDetails = main.getTableRows("dockettypes", selectedParameters, new ArrayList<>(), "");
        docketTypeDetails.forEach(y -> selectedDocketType = new DocketType(Integer.parseInt(y.get(0)), y.get(1)));
        selectedParameters.put("code", consigneeIdentifier);
        if(selectedDocketType.getCode() == 1)
        {
            ArrayList<ArrayList<String>> supplierDetails = main.getTableRows("suppliers", selectedParameters, new ArrayList<>(), "");
            supplierDetails.forEach(y -> selectedConsignee = new Consignee(Integer.parseInt(y.get(0)), y.get(1), y.get(2)));
        }
        else if(selectedDocketType.getCode() == 2)
        {
            ArrayList<ArrayList<String>> customerDetails = main.getTableRows("customers", selectedParameters, new ArrayList<>(), "");
            customerDetails.forEach(y -> selectedConsignee = new Consignee(Integer.parseInt(y.get(0)), y.get(1), y.get(2)));
        }
    }
    public void generateReport()
    {
        Report aReport = new Report("dockets/" + selectedSecondWeight.getCode() + ".pdf");
        ArrayList<String> reportContent = new ArrayList<>(Arrays.asList("Weight Type: " + selectedDocketType.getDocketType(),
        "Consignee: " + selectedConsignee.toString(), "Driver: " + selectedDriver.toString(), "Commodity: " + selectedCommodity.toString(),
        "First Weight Sequence Number: " + selectedFirstWeight.getCode(), "First Weight Actual Weight: " + selectedFirstWeight.getWeight(),
        "First Weight Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(selectedFirstWeight.getDate()),
        "Second Weight Sequence Number: " + selectedSecondWeight.getCode(), "Second Weight Actual Weight: " + selectedSecondWeight.getWeight(),
        "Second Weight Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(selectedSecondWeight.getDate())));
        if(selectedDocketType.getCode() == 1)
            reportContent.add("Total Weight: " + (selectedFirstWeight.getWeight() - selectedSecondWeight.getWeight()));
        else if(selectedDocketType.getCode() == 2)
            reportContent.add("Total Weight: " + (selectedSecondWeight.getWeight() - selectedFirstWeight.getWeight()));
        aReport.addContent(reportContent);
    }
    public void printReport()
    {
        new Printer("dockets/" + selectedSecondWeight.getCode() + ".pdf");
    }
    public void emailReport(String emailAddress)
    {
        Email anEmail = new Email();
        anEmail.sendMessage(emailAddress, "Weight Docket " + selectedSecondWeight.getCode(),
        "Dear Sir/Madam\n\nPlease find attached the weight docket for " + selectedSecondWeight.getCode() + ".\n\nYours sincerely,\nS Cullinan",
         "dockets/" + selectedSecondWeight.getCode() + ".pdf", selectedSecondWeight.getCode() + ".pdf");
    }
    public String getCaptionTitle()
    {
        return selectedDriver.toString() + " " + selectedCommodity.toString();
    }
    public DocketType getSelectedDocketType()
    {
        return selectedDocketType;
    }
    public void setSelectedDocketType(DocketType selectedDocketType)
    {
        this.selectedDocketType = selectedDocketType;
    }
    public Consignee getSelectedConsignee()
    {
        return selectedConsignee;
    }
    public void setSelectedConsignee(Consignee selectedConsignee)
    {
        this.selectedConsignee = selectedConsignee;
    }
    public Driver getSelectedDriver()
    {
        return selectedDriver;
    }
    public void setSelectedDriver(Driver selectedDriver)
    {
        this.selectedDriver = selectedDriver;
    }
    public Commodity getSelectedCommodity()
    {
        return selectedCommodity;
    }
    public void setSelectedCommodity(Commodity selectedCommodity)
    {
        this.selectedCommodity = selectedCommodity;
    }
    public FirstWeight getSelectedFirstWeight()
    {
        return selectedFirstWeight;
    }
    public void setSelectedFirstWeight(FirstWeight selectedFirstWeight)
    {
        this.selectedFirstWeight = selectedFirstWeight;
    }
    public SecondWeight getSelectedSecondWeight()
    {
        return selectedSecondWeight;
    }
    public void setSelectedSecondWeight(SecondWeight selectedSecondWeight)
    {
        this.selectedSecondWeight = selectedSecondWeight;
    }
}
