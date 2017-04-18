package HTMLPages;

import WeighBridge.Commodity;
import WeighBridge.Consignee;
import WeighBridge.DocketType;
import WeighBridge.Driver;
import HTMLControls.*;
import Models.WeighBridge;
import Utilities.Utilities;
import com.teamdev.jxbrowser.chromium.JSONString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WeighBridgePage
{
    private WeighBridge aWeighBridge;
    public WeighBridgePage()
    {
        aWeighBridge = new WeighBridge();
    }
    public JSONString setWeightBridgeDocketType()
    {
        MetroAccordion setWeighBridgeDocketTypeAccordion = new MetroAccordion();
        MetroLayout setWeighBridgeDocketTypeLayout = new MetroLayout();
        MetroProgressBar aProgressBar = new MetroProgressBar(25, "cyan");
        MetroCommandButton backButton = new MetroCommandButton("Return", "Return To Main Menu", "arrow-left",
        "loadHTML5Edition();", "danger");
        MetroHeading selectWeighBridgeDocketTypeHeading = new MetroHeading("Select Docket Type", "");
        ArrayList<DocketType> availableDocketTypes = aWeighBridge.getDocketTypes();
        List<MetroComponent> availableDocketTypesTiles = new ArrayList<>();
        availableDocketTypes.forEach(x -> availableDocketTypesTiles.add(new MetroTile("selectWeighBridgeConsignee(" + x.getCode() + ");",
        "cyan", x.toString(), "eur", "")));
        setWeighBridgeDocketTypeLayout.addRow(aProgressBar);
        setWeighBridgeDocketTypeLayout.addEmptyRows(2);
        setWeighBridgeDocketTypeLayout.addRow(new ArrayList<>(Arrays.asList(backButton, selectWeighBridgeDocketTypeHeading)),
        new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        setWeighBridgeDocketTypeLayout.addMultipleRows(availableDocketTypesTiles, 3, 0, 3, 1, 2);
        setWeighBridgeDocketTypeAccordion.addFrame("Select Docket Type", setWeighBridgeDocketTypeLayout, "eur");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", setWeighBridgeDocketTypeAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString setConsignee(int docketTypeID)
    {
        aWeighBridge.setSelectedDocketType(aWeighBridge.getDocketType(docketTypeID));
        MetroAccordion setConsigneeAccordion = new MetroAccordion();
        MetroLayout setConsigneeLayout = new MetroLayout();
        MetroProgressBar aProgressBar = new MetroProgressBar(50, "cyan");
        MetroCommandButton backButton = new MetroCommandButton("Return", "Return To Docket Type", "arrow-left",
        "selectWeighBridgeDocketType();", "danger");
        String titleText = "Consignee";
        ArrayList<Consignee> availableConsignees = new ArrayList<>();
        if(aWeighBridge.getSelectedDocketType().getCode() == 1)
        {
            titleText = "Supplier";
            availableConsignees = aWeighBridge.getConsignees("suppliers");
        }
        else if(aWeighBridge.getSelectedDocketType().getCode() == 2)
        {
            titleText = "Customer";
            availableConsignees = aWeighBridge.getConsignees("customers");
        }
        MetroCommandButton addNewConsigneeButton = new MetroCommandButton("Add", "Add New " + titleText, "plus",
        "addNewConsignee();", "success");
        List<MetroComponent> availableConsigneesTiles = new ArrayList<>();
        availableConsignees.forEach(x -> availableConsigneesTiles.add(new MetroTile("selectWeighBridgeDriver(" + x.getCode() + ");", "cyan",
        x.toString(), "user", "")));
        setConsigneeLayout.addRow(aProgressBar);
        setConsigneeLayout.addEmptyRows(2);
        setConsigneeLayout.addRow(new ArrayList<>(Arrays.asList(backButton, addNewConsigneeButton)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        setConsigneeLayout.addMultipleRows(availableConsigneesTiles, 3, 0, 3, 1, 2);
        setConsigneeAccordion.addFrame(titleText, setConsigneeLayout, "user");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", setConsigneeAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString addNewConsignee()
    {
        MetroAccordion addNewConsigneeAccordion = new MetroAccordion();
        MetroLayout addNewConsigneeLayout = new MetroLayout();
        MetroProgressBar addNewConsigneeProgressBar = new MetroProgressBar(50, "cyan");
        String titleText = "Add A Consignee";
        String descriptionText = "Consignee";
        if(aWeighBridge.getSelectedDocketType().getCode() == 1)
        {
            titleText = "Add A Supplier";
            descriptionText = "Supplier";
        }
        else if(aWeighBridge.getSelectedDocketType().getCode() == 2)
        {
            titleText = "Add A Customer";
            descriptionText = "Customer";
        }
        MetroHeading addNewConsigneeHeading = new MetroHeading(titleText, "");
        MetroTextField firstName = new MetroTextField("Please Enter The First Name Of The " + descriptionText, "user",
        "text", "firstName");
        MetroTextField lastName = new MetroTextField("Please Enter The Last Name Of The " + descriptionText, "user", "text",
        "lastName");
        MetroCommandButton saveNewConsigneeButton = new MetroCommandButton("Save", "Save Your " + descriptionText, "checkmark",
        "saveNewConsignee();", "success");
        MetroCommandButton backButton = new MetroCommandButton("Return", "Return To " + descriptionText + "s", "exit",
        "selectWeighBridgeConsignee(" + aWeighBridge.getSelectedDocketType().getCode() + ");", "danger");
        addNewConsigneeLayout.addRow(addNewConsigneeProgressBar);
        addNewConsigneeLayout.addEmptyRows(2);
        addNewConsigneeLayout.addRow(addNewConsigneeHeading);
        addNewConsigneeLayout.addEmptyRows(2);
        addNewConsigneeLayout.addRow(firstName);
        addNewConsigneeLayout.addEmptyRows(2);
        addNewConsigneeLayout.addRow(lastName);
        addNewConsigneeLayout.addEmptyRows(2);
        addNewConsigneeLayout.addRow(new ArrayList<>(Arrays.asList(saveNewConsigneeButton, backButton)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        addNewConsigneeAccordion.addFrame(titleText, addNewConsigneeLayout, "plus");
        addNewConsigneeLayout.addEmptyRows(2);
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", addNewConsigneeAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString saveNewConsignee(String firstName, String lastName)
    {
        HashMap<String, String> parameters = new HashMap<>();
        if(firstName.length() < 2)
        {
            parameters.put("response", "error");
            parameters.put("title", "Invalid First Name");
            parameters.put("content", "A Valid First Name Should Have At Least 2 Characters");
        }
        if(lastName.length() < 2)
        {
            parameters.put("response", "error");
            parameters.put("title", "Invalid Last Name");
            parameters.put("content", "A Valid Last Name Should Have At Least 2 Characters");
        }
        if(parameters.size() == 0)
        {
            String menuTitle = "";
            if (aWeighBridge.getSelectedDocketType().getCode() == 1)
            {
                Consignee aConsignee = aWeighBridge.insertNewConsignee(firstName, lastName, "suppliers");
                menuTitle = "Supplier";
                aWeighBridge.setSelectedConsignee(aConsignee);
            }
            else if (aWeighBridge.getSelectedDocketType().getCode() == 2)
            {
                Consignee aConsignee = aWeighBridge.insertNewConsignee(firstName, lastName, "customers");
                menuTitle = "Customer";
                aWeighBridge.setSelectedConsignee(aConsignee);
            }
            parameters.put("html", createDriversMenu().toString());
            parameters.put("response", "success");
            parameters.put("title", "New " + menuTitle + " Created");
            parameters.put("content", "A New " + menuTitle + " Called " + firstName + " " + lastName + " Has Been Created");
        }
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString setDriver(int consigneeID)
    {
        if(aWeighBridge.getSelectedDocketType().getCode() == 1)
            aWeighBridge.setSelectedConsignee(aWeighBridge.getConsignee(consigneeID, "suppliers"));
        else if(aWeighBridge.getSelectedDocketType().getCode() == 2)
            aWeighBridge.setSelectedConsignee(aWeighBridge.getConsignee(consigneeID, "customers"));
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", createDriversMenu().toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString addNewDriver()
    {
        MetroAccordion addNewDriverAccordion = new MetroAccordion();
        MetroProgressBar addNewDriverProgressBar = new MetroProgressBar(75, "cyan");
        MetroLayout addNewDriverLayout = new MetroLayout();
        MetroHeading addNewDriverHeading = new MetroHeading("Add New Driver", "");
        MetroTextField firstName = new MetroTextField("Please enter the first name of the driver", "user", "text", "firstName");
        MetroTextField lastName = new MetroTextField("Please enter the last name of the driver", "user", "text", "lastName");
        MetroCommandButton saveYourDriverButton = new MetroCommandButton("Save", "Save Your Driver", "checkmark",
        "saveNewDriver();","success");
        MetroCommandButton backButton = new MetroCommandButton("Return", "Return To Drivers", "arrow-left",
        "selectWeighBridgeDriver(" + aWeighBridge.getSelectedConsignee().getCode() + ");", "danger");
        addNewDriverLayout.addRow(addNewDriverProgressBar);
        addNewDriverLayout.addEmptyRows(2);
        addNewDriverLayout.addRow(addNewDriverHeading);
        addNewDriverLayout.addEmptyRows(2);
        addNewDriverLayout.addRow(firstName);
        addNewDriverLayout.addEmptyRows(2);
        addNewDriverLayout.addRow(lastName);
        addNewDriverLayout.addEmptyRows(2);
        addNewDriverLayout.addRow(new ArrayList<>(Arrays.asList(saveYourDriverButton, backButton)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        addNewDriverAccordion.addFrame("Add New Driver", addNewDriverLayout, "user");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", addNewDriverAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString saveNewDriver(String firstName, String lastName)
    {
        HashMap<String, String> parameters = new HashMap<>();
        if(firstName.length() < 2)
        {
            parameters.put("response", "error");
            parameters.put("title", "Invalid First Name");
            parameters.put("content", "A Valid First Name Should Have At Least 2 Characters");
        }
        if(lastName.length() < 2)
        {
            parameters.put("response", "error");
            parameters.put("title", "Invalid Last Name");
            parameters.put("content", "A Valid Last Name Should Have At Least 2 Characters");
        }
        if(parameters.size() == 0)
        {
            Driver aDriver = aWeighBridge.insertNewDriver(firstName, lastName);
            aWeighBridge.setSelectedDriver(aDriver);
            parameters.put("response", "success");
            parameters.put("title", "New Driver");
            parameters.put("content", "The Driver " + firstName + " " + lastName + " Has Been Created Successfully");
            parameters.put("html", createCommoditiesMenu().toString());
        }
        return  Utilities.convertHashMapToJSON(parameters);
    }
    private MetroAccordion createDriversMenu()
    {
        MetroAccordion setDriverAccordion = new MetroAccordion();
        MetroLayout setDriverLayout = new MetroLayout();
        MetroProgressBar setDriverProgressBar = new MetroProgressBar(75, "cyan");
        MetroCommandButton backButton = new MetroCommandButton("Return", "Return To Consignee", "arrow-left",
        "selectWeighBridgeConsignee(" + aWeighBridge.getSelectedDocketType().getCode() + ");", "danger");
        MetroCommandButton addNewDriverButton = new MetroCommandButton("Add", "Add New Driver", "plus",
        "addNewDriver();", "success");
        ArrayList<Driver> availableDrivers = aWeighBridge.getDrivers();
        List<MetroComponent> availableDriversTiles = new ArrayList<>();
        availableDrivers.forEach(x -> availableDriversTiles.add(new MetroTile("selectWeighBridgeCommodity(" + x.getCode() + ");", "cyan",
        x.toString(), "user", "")));
        setDriverLayout.addRow(setDriverProgressBar);
        setDriverLayout.addEmptyRows(2);
        setDriverLayout.addRow(new ArrayList<>(Arrays.asList(backButton, addNewDriverButton)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        setDriverLayout.addMultipleRows(availableDriversTiles, 3, 0, 3, 1, 2);
        setDriverAccordion.addFrame("Select Driver", setDriverLayout, "user");
        return setDriverAccordion;
    }
    public JSONString setCommodity(int driverID)
    {
        aWeighBridge.setSelectedDriver(aWeighBridge.getDriver(driverID));
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", createCommoditiesMenu().toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    private MetroAccordion createCommoditiesMenu()
    {
        MetroAccordion setCommodityAccordion = new MetroAccordion();
        MetroLayout setCommodityLayout = new MetroLayout();
        MetroProgressBar setCommodityProgressBar = new MetroProgressBar(100, "cyan");
        MetroCommandButton backButton = new MetroCommandButton("Return", "Return To Driver", "arrow-left",
        "selectWeighBridgeDriver(" + aWeighBridge.getSelectedConsignee().getCode() + ");", "danger");
        MetroCommandButton addNewCommodityButton = new MetroCommandButton("Add", "Add New Commodity", "plus",
        "addNewCommodity();", "success");
        ArrayList<Commodity> availableCommodities = aWeighBridge.getCommodities();
        List<MetroComponent> availableCommoditiesTiles = new ArrayList<>();
        availableCommodities.forEach(x -> availableCommoditiesTiles.add(new MetroTile("obtainWeighBridgeFirstWeight(" + x.getCode() + ")",
        "cyan", x.toString(),"florist", "")));
        setCommodityLayout.addRow(setCommodityProgressBar);
        setCommodityLayout.addEmptyRows(2);
        setCommodityLayout.addRow(new ArrayList<>(Arrays.asList(backButton, addNewCommodityButton)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        setCommodityLayout.addEmptyRows(2);
        setCommodityLayout.addMultipleRows(availableCommoditiesTiles, 3, 0, 3, 1, 2);
        setCommodityAccordion.addFrame("Select Commodity", setCommodityLayout, "florist");
        return setCommodityAccordion;
    }
    public JSONString addNewCommodity()
    {
        MetroAccordion addNewCommodityAccordion = new MetroAccordion();
        MetroLayout addNewCommodityLayout = new MetroLayout();
        MetroProgressBar addNewCommodityProgressBar = new MetroProgressBar(100, "cyan");
        MetroHeading addCommodityHeading = new MetroHeading("Add New Commodity", "");
        MetroTextField commodity = new MetroTextField("Please enter a value for the new commodity", "user", "text", "commodity");
        MetroCommandButton saveNewCommodityButton = new MetroCommandButton("Save", "Save New Commodity", "checkmark",
        "saveNewCommodity();", "success");
        MetroCommandButton returnButton = new MetroCommandButton("Return", "Return To Commodities", "arrow-left",
        "selectWeighBridgeCommodity(" + aWeighBridge.getSelectedDriver().getCode() + ");", "danger");
        addNewCommodityLayout.addRow(addNewCommodityProgressBar);
        addNewCommodityLayout.addEmptyRows(2);
        addNewCommodityLayout.addRow(addCommodityHeading);
        addNewCommodityLayout.addEmptyRows(2);
        addNewCommodityLayout.addRow(commodity);
        addNewCommodityLayout.addEmptyRows(2);
        addNewCommodityLayout.addRow(new ArrayList<>(Arrays.asList(saveNewCommodityButton, returnButton)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        addNewCommodityAccordion.addFrame("Add New Commodity", addNewCommodityLayout, "plus");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", addNewCommodityAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString saveNewCommodity(String commodityTitle)
    {
        HashMap<String, String> parameters = new HashMap<>();
        if(commodityTitle.length() < 2)
        {
            parameters.put("response", "error");
            parameters.put("title", "Invalid Commodity");
            parameters.put("content", "A Valid Commodity Should Have At Least 2 Letters");
        }
        if(parameters.size() == 0)
        {
            Commodity aCommodity = aWeighBridge.insertNewCommodity(commodityTitle);
            aWeighBridge.setSelectedCommodity(aCommodity);
            return completeFirstWeight(aCommodity.getCode());
        }
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString completeFirstWeight(int commodityID)
    {
        aWeighBridge.setSelectedCommodity(aWeighBridge.getCommodity(commodityID));
        aWeighBridge.insertNewFirstWeight();
        HashMap<String, String> parameters = new HashMap<>();
        HomePage aHomePage = new HomePage();
        parameters.put("html", aHomePage.createMainMenu().toString());
        parameters.put("response", "success");
        parameters.put("title", "First Weight");
        parameters.put("content", "The First Weight Has Been Successfully Obtained");
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString getDocketsAwaitingSecondWeighing()
    {
        MetroAccordion secondWeighingAccordion = new MetroAccordion();
        MetroLayout secondWeighingLayout = new MetroLayout();
        MetroProgressBar secondWeighingProgressBar = new MetroProgressBar(50, "cyan");
        MetroCommandButton backButton = new MetroCommandButton("Return", "Return To Main Menu", "arrow-left",
        "loadHTML5Edition();", "danger");
        MetroHeading secondWeighingHeading = new MetroHeading("Second Weighing", "");
        ArrayList<ArrayList<String>> availableDockets = aWeighBridge.getDocketsAwaitingSecondWeightment();
        List<MetroTile> secondWeighingTiles = new ArrayList<>();
        for(ArrayList<String> anAvailableDocket : availableDockets)
        {
            System.out.println("AN AVAILABLE DOCKET");
            for(String aDocketComponent: anAvailableDocket)
                System.out.println("DOCKET COMPONENT: " + aDocketComponent);
            aWeighBridge.retrieveParametersForSelectedSecondWeight(anAvailableDocket.get(1), anAvailableDocket.get(2), anAvailableDocket.get(5), anAvailableDocket.get(6));
            secondWeighingTiles.add(new MetroTile("", "cyan", aWeighBridge.getCaptionTitle(), "truck", ""));
        }
        secondWeighingLayout.addRow(secondWeighingProgressBar);
        secondWeighingLayout.addEmptyRows(2);
        secondWeighingLayout.addRow(new ArrayList<>(Arrays.asList(backButton, secondWeighingHeading)), new ArrayList<>(Arrays.asList(1, 4, 1, 0, 6, 0)));
        secondWeighingLayout.addEmptyRows(2);
        secondWeighingAccordion.addFrame("Second Weighing", secondWeighingLayout, "truck");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", secondWeighingAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString completeSecondWeight(int seoondWeightID)
    {
        HashMap<String, String> parameters = new HashMap<>();
        return Utilities.convertHashMapToJSON(parameters);
    }
            /*
        firstWeightsAwaitingSecondWeight.forEach(x ->
        {
            aWeighBridge.retrieveParametersForSelectedSecondWeight(x.get(1), x.get(2), x.get(5), x.get(6));
            JButton currentButton = createTile(aWeighBridge.getCaptionTitle(), "", firstWeightsAwaitingSecondWeight.size());
            currentButton.addActionListener((y) ->
            {
                aWeighBridge.insertNewSecondWeight(x.get(0), x.get(3), x.get(4));
                aWeighBridge.generateReport();
                selectDocket();
            });
            secondWeightsPanel.add(currentButton);
        });
        */
}