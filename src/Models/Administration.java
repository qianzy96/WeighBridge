package Models;
import WeighBridge.*;
import Database.Database;
import Utilities.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Administration
{
    private Database main;
    public Administration()
    {
        main = new Database();
    }
    public ArrayList<String> getTableTitles(String tableName)
    {
        return main.getColumnTitles(tableName);
    }
    public ArrayList<ArrayList<String>> getTableContents(String tableName)
    {
        return main.getTableRows(tableName, new HashMap<>(), new ArrayList<>(), "");
    }
    public ArrayList<ArrayList<String>> getSelectedRow(String tableName, String uniqueIdentifier)
    {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("code", uniqueIdentifier);
        return main.getTableRows(tableName, parameters, new ArrayList<>(), "");
    }
    public void updateSelectedRow(String tableName, HashMap<String, String> updatedParameters, String uniqueIdentifier)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", uniqueIdentifier);
        main.updateTableRow(tableName, updatedParameters, selectedParameters);
    }
    public void deleteSelectedRow(String tableName, String uniqueIdentifier)
    {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("code", uniqueIdentifier);
        main.removeTableRow(tableName, parameters);
    }
    public void insertNewRow(String tableName, ArrayList<String> parameters)
    {
        main.insertTableRow(tableName, parameters);
    }
    public Email getEmailSettings()
    {
        HashMap<String, String> emailSettings = new HashMap<>();
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("title", "mail.");
        ArrayList<ArrayList<String>> tableContents = main.getTableRows("settings", selectedParameters,
        new ArrayList<>(Arrays.asList("title", "value")), "");
        for(ArrayList<String> aTableRow : tableContents)
            emailSettings.put(aTableRow.get(0), aTableRow.get(1));
        Email anEmail = new Email(emailSettings.get("mail.username"), emailSettings.get("mail.password"), emailSettings.get("mail.smtp.auth"),
        emailSettings.get("mail.smtp.starttls.enable"), emailSettings.get("mail.smtp.host"), emailSettings.get("mail.smtp.port"));
        return anEmail;
    }
    public HashMap<String, String> getFontSettings()
    {
        HashMap<String, String> formattedFontSettings = new HashMap<>();
        HashMap<String, String> fontSettings = new HashMap<>();
        fontSettings.put("title", "Font Size");
        fontSettings.put("title", "Font Face");
        ArrayList<ArrayList<String>> tableContents = main.getTableRows("settings", fontSettings);
        for(ArrayList<String> aTableContent: tableContents)
            formattedFontSettings.put(aTableContent.get(1), aTableContent.get(2));
        return formattedFontSettings;
    }
    public HashMap<String, String> getCalculatorSettings()
    {
        HashMap<String, String> formattedCalculatorSettings = new HashMap<>();
        HashMap<String, String> calculatorSettings = new HashMap<>();
        calculatorSettings.put("title", "Number Of Places");
        calculatorSettings.put("title", "Currency Symbol");
        ArrayList<ArrayList<String>> tableContents = main.getTableRows("settings", calculatorSettings);
        for(ArrayList<String> aTableContent: tableContents)
            formattedCalculatorSettings.put(aTableContent.get(1), aTableContent.get(2));
        return formattedCalculatorSettings;
    }
    public void saveFontSettings(String fontSize, String fontFace)
    {
        updateSettingInDatabase(1, fontSize);
        updateSettingInDatabase(2, fontFace);
    }
    public void saveCalculatorSettings(String numberOfPlaces, String currencySymbol)
    {
        updateSettingInDatabase(3, numberOfPlaces);
        updateSettingInDatabase(4, currencySymbol);
    }
    public void saveEmailSettings(String username, String password, String smtpAuth, String smtpStartTlsEnable, String smtpHost, String smtpPort)
    {
        updateSettingInDatabase(5, username);
        updateSettingInDatabase(6, password);
        updateSettingInDatabase(7, smtpAuth);
        updateSettingInDatabase(8, smtpStartTlsEnable);
        updateSettingInDatabase(9, smtpHost);
        updateSettingInDatabase(10, smtpPort);
    }
    public void saveSerialPortSettings(String portName, String baudRate, String dataBits, String stopBits, String parity)
    {
        updateSettingInDatabase(11, portName);
        updateSettingInDatabase(12, baudRate);
        updateSettingInDatabase(13, dataBits);
        updateSettingInDatabase(14, stopBits);
        updateSettingInDatabase(15, parity);
    }
    public ObtainWeight getSerialPortSettings()
    {
        return new ObtainWeight();
    }
    private void updateSettingInDatabase(int uniqueIdentifier, String updatedValue)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", uniqueIdentifier + "");
        HashMap<String, String> updatedParameters = new HashMap<>();
        updatedParameters.put("value", updatedValue);
        main.updateTableRow("settings", updatedParameters, selectedParameters);
    }
    public ArrayList<ArrayList<String>> getSecondWeightsCustomersInformation()
    {
        ArrayList<String> tableTitles = new ArrayList<>(Arrays.asList("firstweights", "drivers", "commodities", "dockettypes", "customers", "secondweights"));
        ArrayList<String> joinConditions = new ArrayList<>(Arrays.asList("firstweights.driver", "drivers.code", "firstweights.commodity", "commodities.code",
        "firstweights.dockettype", "dockettypes.code", "firstweights.consignee", "customers.code", "firstweights.code", "secondweights.firstweight"));
        ArrayList<String> desiredColumns = new ArrayList<>(Arrays.asList("firstweights.code", "firstweights.weight", "firstweights.date", "drivers.firstname",
        "drivers.lastname", "commodities.title", "dockettypes.dockettype", "customers.firstname", "customers.lastname", "secondweights.code", "secondweights.weight",
        "secondweights.date"));
        HashMap<String, String> selectedValues = new HashMap<>();
        selectedValues.put("dockettypes.code", "2");
        return main.getJoinedTableRows(tableTitles, joinConditions, selectedValues, desiredColumns, "");
    }
    public ArrayList<ArrayList<String>> getSecondWeightsSuppliersInformation()
    {
        ArrayList<String> tableTitles = new ArrayList<>(Arrays.asList("firstweights", "drivers", "commodities", "dockettypes", "suppliers", "secondweights"));
        ArrayList<String> joinConditions = new ArrayList<>(Arrays.asList("firstweights.driver", "drivers.code", "firstweights.commodity", "commodities.code",
        "firstweights.dockettype", "dockettypes.code", "firstweights.consignee", "suppliers.code", "firstweights.code", "secondweights.firstweight"));
        ArrayList<String> desiredColumns = new ArrayList<>(Arrays.asList("firstweights.code", "firstweights.weight", "firstweights.date", "drivers.firstname",
        "drivers.lastname", "commodities.title", "dockettypes.dockettype", "suppliers.firstname", "suppliers.lastname", "secondweights.code", "secondweights.weight",
        "secondweights.date"));
        HashMap<String, String> selectedValues = new HashMap<>();
        selectedValues.put("dockettypes.code", "1");
        return main.getJoinedTableRows(tableTitles, joinConditions, selectedValues, desiredColumns, "");
    }
    public ArrayList<String> getSecondWeightsColumnTitles()
    {
        return new ArrayList<>(Arrays.asList("First Weight Code", "First Weight", "Date", "First Name", "Last Name", "Title", "DocketType",
        "First Name", "Last Name", "Second Weight Code", "Second Weight", "Second Weight Date"));
    }
    public ArrayList<ArrayList<String>> getFirstWeightsCustomersInformation()
    {
        ArrayList<String> tableTitles = new ArrayList<>(Arrays.asList("firstweights", "drivers", "commodities", "dockettypes", "customers"));
        ArrayList<String> joinConditions = new ArrayList<>(Arrays.asList("firstweights.driver", "drivers.code", "firstweights.commodity", "commodities.code",
        "firstweights.dockettype", "dockettypes.code", "firstweights.consignee", "customers.code"));
        ArrayList<String> desiredColumns = new ArrayList<>(Arrays.asList("firstweights.code", "firstweights.weight", "firstweights.date", "drivers.firstname",
        "drivers.lastname", "commodities.title", "dockettypes.dockettype", "customers.firstname", "customers.lastname"));
        HashMap<String, String> selectedValues = new HashMap<>();
        selectedValues.put("dockettypes.code", "2");
        return main.getJoinedTableRows(tableTitles, joinConditions, selectedValues, desiredColumns, "");
    }
    public ArrayList<ArrayList<String>> getFirstWeightsSuppliersInformation()
    {
        ArrayList<String> tableTitles = new ArrayList<>(Arrays.asList("firstweights", "drivers", "commodities", "dockettypes", "suppliers"));
        ArrayList<String> joinConditions = new ArrayList<>(Arrays.asList("firstweights.driver", "drivers.code", "firstweights.commodity", "commodities.code",
        "firstweights.dockettype", "dockettypes.code", "firstweights.consignee", "suppliers.code"));
        ArrayList<String> desiredColumns = new ArrayList<>(Arrays.asList("firstweights.code", "firstweights.weight", "firstweights.date", "drivers.firstname",
        "drivers.lastname", "commodities.title", "dockettypes.dockettype", "suppliers.firstname", "suppliers.lastname"));
        HashMap<String, String> selectedValues = new HashMap<>();
        selectedValues.put("dockettypes.code", "1");
        return main.getJoinedTableRows(tableTitles, joinConditions, selectedValues, desiredColumns, "");
    }
    public ArrayList<String> getFirstWeightsColumnTitles()
    {
        return new ArrayList<>(Arrays.asList("First Weight Code", "First Weight", "Date", "First Name", "Last Name", "Title", "DocketType", "First Name", "Last Name"));
    }
    public void insertNewContract(String commodityTitle, Double total, Double price, String docketType, String consignee, String startDate, String endDate)
    {
        String commodityCode = "";
        String docketTypeCode = "";
        String consigneeCode = "";
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("title", commodityTitle);
        ArrayList<ArrayList<String>> selectedTableContents = main.getTableRows("commodities", selectedParameters, new ArrayList<>(), "");
        if(selectedTableContents.size() > 0)
            commodityCode = selectedTableContents.get(0).get(0);
        selectedParameters.clear();
        selectedParameters.put("dockettype", docketType);
        selectedTableContents = main.getTableRows("dockettypes", selectedParameters, new ArrayList<>(), "");
        if(selectedTableContents.size() > 0)
            docketTypeCode = selectedTableContents.get(0).get(0);
        selectedParameters.clear();
        String[] consigneeNames = consignee.split(" ");
        selectedParameters.put("firstname", consigneeNames[0]);
        selectedParameters.put("lastname", consigneeNames[1]);
        if(docketTypeCode.equals("1"))
        {
            ArrayList<ArrayList<String>> tableContents = main.getTableRows("suppliers", selectedParameters, new ArrayList<>(), "");
            if(tableContents.size() > 0)
                consigneeCode = tableContents.get(0).get(0);
        }
        else if(docketTypeCode.equals("2"))
        {
            ArrayList<ArrayList<String>> tableContents = main.getTableRows("customers", selectedParameters, new ArrayList<>(), "");
            if(tableContents.size() > 0)
                consigneeCode = tableContents.get(0).get(0);
        }
        int maxValue = main.getMaxValueOfColumn("contracts", "code") + 1;
        main.insertTableRow("contracts", new ArrayList<>(Arrays.asList(maxValue + "", commodityCode, total + "", price + "", docketTypeCode, consigneeCode, startDate,
        endDate)));
    }
    public ArrayList<ArrayList<String>> getContractsCustomersInformation()
    {
        ArrayList<String> tableTitles = new ArrayList<>(Arrays.asList("contracts", "commodities", "dockettypes", "customers"));
        ArrayList<String> joinConditions = new ArrayList<>(Arrays.asList("contracts.commodity", "commodities.code", "contracts.dockettype", "dockettypes.code",
        "contracts.consignee", "customers.code"));
        ArrayList<String> desiredColumns = new ArrayList<>(Arrays.asList("commodities.title", "contracts.startdate", "contracts.enddate", "contracts.total",
        "contracts.price", "dockettypes.dockettype", "customers.firstname", "customers.lastname"));
        HashMap<String, String> selectedValues = new HashMap<>();
        selectedValues.put("dockettypes.code", "2");
        return main.getJoinedTableRows(tableTitles, joinConditions, selectedValues, desiredColumns, "");
    }
    public ArrayList<ArrayList<String>> getContractsSuppliersInformation()
    {
        ArrayList<String> tableTitles = new ArrayList<>(Arrays.asList("contracts", "commodities", "dockettypes", "suppliers"));
        ArrayList<String> joinConditions = new ArrayList<>(Arrays.asList("contracts.commodity", "commodities.code", "contracts.dockettype", "dockettypes.code",
        "contracts.consignee", "suppliers.code"));
        ArrayList<String> desiredColumns = new ArrayList<>(Arrays.asList("commodities.title", "contracts.startdate", "contracts.enddate", "contracts.total",
        "contracts.price", "dockettypes.dockettype", "suppliers.firstname", "suppliers.lastname"));
        HashMap<String, String> selectedValues = new HashMap<>();
        selectedValues.put("dockettypes.code", "1");
        return main.getJoinedTableRows(tableTitles, joinConditions, selectedValues, desiredColumns, "");
    }
    public ArrayList<String> getContractsColumnTitles()
    {
        return new ArrayList<>(Arrays.asList("Commodity", "Total Quantity", "Total Price", "Docket Type", "First Name", "Last Name"));
    }
    /*public HashSet<String> getCommoditiesFromContracts()
    {
        HashSet<String> commodities = new HashSet<>();
        ArrayList<String> tableTitles = new ArrayList<>(Arrays.asList("contracts", "commodities"));
        ArrayList<String> joinConditions = new ArrayList<>(Arrays.asList("contracts.commodity", "commodities.code"));
        ArrayList<String> desiredColumns = new ArrayList<>(Arrays.asList("commodities.code", "commodities.title"));
        ArrayList<ArrayList<String>> tableContents = main.getJoinedTableRows(tableTitles, joinConditions, new HashMap<>(), desiredColumns, "");
        tableContents.forEach(x -> commodities.add(x.get(1)));
        return commodities;
    }
    public ArrayList<ArrayList<String>> getContractsForSpecifiedCommodity(String commodityTitle)
    {
        ArrayList<ArrayList<String>> specifiedContracts = new ArrayList<>();
        ArrayList<ArrayList<String>> secondWeightsForCustomers = getSecondWeightsCustomersInformation();
        ArrayList<ArrayList<String>> secondWeightsForSuppliers = getSecondWeightsSuppliersInformation();
        ArrayList<ArrayList<String>> contractsForCustomers = getContractsCustomersInformation();
        ArrayList<ArrayList<String>> contractsForSuppliers = getContractsSuppliersInformation();
        for(ArrayList<String> aSecondWeight: secondWeightsForCustomers)
        {
            System.out.println("A SECOND WEIGHT");
            for (String aSecondWeightComponent : aSecondWeight)
                System.out.println("Second Weight Component: " + aSecondWeightComponent);
        }
        for(ArrayList<String> aContract: contractsForCustomers)
            for(String aContractComponent: aContract)
                System.out.println("Contract Component: " + aContractComponent);
        contractsForCustomers.removeIf(x -> !x.get(0).equals(commodityTitle));
        int totalQuantityForAllLoads = 0;
        int totalPriceForAllLoads = 0;
        for(ArrayList<String> aContract: contractsForCustomers)
        {
            for(ArrayList<String> aSecondWeight : secondWeightsForCustomers)
            {
                try
                {
                    Date secondWeightDate = new SimpleDateFormat("yyyy-MM-dd").parse(aSecondWeight.get(11));
                    Date contractStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(aContract.get(1));
                    Date contractEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(aContract.get(2));
                    if(secondWeightDate.compareTo(contractStartDate) >= 0 && secondWeightDate.compareTo(contractEndDate) <= 0)
                    {
                        if(aSecondWeight.get(5).equals(aContract.get(0)))
                        {
                            int totalPrice = ((Integer.parseInt(aSecondWeight.get(1)) - Integer.parseInt(aSecondWeight.get(10))) / 1000) *
                            Integer.parseInt(aContract.get(3));
                            specifiedContracts.add(new ArrayList<>(Arrays.asList(aSecondWeight.get(11), (Integer.parseInt(aSecondWeight.get(1)) -
                            Integer.parseInt(aSecondWeight.get(10))) + "", aSecondWeight.get(3) + " " + aSecondWeight.get(4), aContract.get(3), totalPrice + "")));
                            totalQuantityForAllLoads += Integer.parseInt(aSecondWeight.get(1)) - Integer.parseInt(aSecondWeight.get(10));
                            totalPriceForAllLoads += totalPrice;
                        }
                    }
                }
                catch(ParseException error)
                {
                    JOptionPane.showMessageDialog(null, error);
                }
            }
        }
        specifiedContracts.add(new ArrayList<>(Arrays.asList("Total", totalQuantityForAllLoads + "", "", "", totalPriceForAllLoads + "")));
        return specifiedContracts;
    }*/

    public ArrayList<String> getContractsForSpecifiedCommodityHeading()
    {
        return new ArrayList<>(Arrays.asList("Date", "Second Weight", "Consignee", "Price", "Total"));
    }
    public ArrayList<String> getBatchNumberTitles()
    {
        return new ArrayList<>(Arrays.asList("Code", "Date", "Docket Type", "Consignee", "Net Weight", "Commodity", "Batch Number"));
    }
    public ArrayList<ArrayList<String>> getBatchNumbers()
    {
        ArrayList<ArrayList<String>> batchNumbers = new ArrayList<>();
        ArrayList<BatchNumber> loadsWithBatchNumbers = getLoadsWithBatchNumbers();
        loadsWithBatchNumbers.forEach(x ->
        {
            ArrayList<String> aBatchNumber = new ArrayList<>();
            aBatchNumber.add(x.getCode() + "");
            aBatchNumber.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(x.getSecondWeight().getDate()));
            aBatchNumber.add(x.getSecondWeight().getFirstWeight().getDocketType().getDocketType());
            aBatchNumber.add(x.getSecondWeight().getFirstWeight().getConsignee().getFirstName() + " " +
            x.getSecondWeight().getFirstWeight().getConsignee().getLastName());
            aBatchNumber.add(x.getSecondWeight().getNetWeight() + "");
            aBatchNumber.add(x.getSecondWeight().getFirstWeight().getCommodity().getTitle());
            aBatchNumber.add(x.getBatchNumber());
            batchNumbers.add(aBatchNumber);
        });
        return batchNumbers;
    }
    public ArrayList<BatchNumber> getLoadsWithBatchNumbers()
    {
        ArrayList<BatchNumber> loadsWithBatchNumbers = new ArrayList<>();
        ArrayList<ArrayList<String>> batchNumbers = main.getTableRows("batchnumbers", new HashMap<>(), new ArrayList<>(), "");
        for(ArrayList<String> aBatchNumber : batchNumbers)
        {
            SecondWeight aSecondWeight = getSecondWeight(aBatchNumber.get(1));
            loadsWithBatchNumbers.add(new BatchNumber(Integer.parseInt(aBatchNumber.get(0)), aBatchNumber.get(2), aSecondWeight));
        }
        return loadsWithBatchNumbers;
    }
    public void insertNewBatchNumber(BatchNumber aBatchNumber)
    {
        int maximumValue = main.getMaxValueOfColumn("batchnumbers", "code") + 1;
        main.insertTableRow("batchnumbers", new ArrayList<>(Arrays.asList(maximumValue + "", aBatchNumber.getSecondWeight().getCode() + "",
        aBatchNumber.getBatchNumber())));
    }
    public void updateExistingBatchNumber(BatchNumber aBatchNumber)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", aBatchNumber.getCode() + "");
        HashMap<String, String> updatedParameters = new HashMap<>();
        updatedParameters.put("batchnumber", aBatchNumber.getBatchNumber());
        main.updateTableRow("batchnumbers", updatedParameters, selectedParameters);
    }
    public ArrayList<SecondWeight> getLoadsWithNoBatchNumbers()
    {
        ArrayList<SecondWeight> secondWeights = getSecondWeights();
        ArrayList<BatchNumber> batchNumbers = getLoadsWithBatchNumbers();
        for(int counter = 0; counter < batchNumbers.size(); counter++)
        {
            for(int index = 0; index < secondWeights.size(); index++)
            {
                if(batchNumbers.get(counter).getSecondWeight().getCode() == secondWeights.get(index).getCode())
                {
                    secondWeights.remove(index);
                    System.out.println("SECOND WEIGHT REMOVED");
                }
            }
        }
        return secondWeights;
    }
    public ArrayList<String> getLoadsBasedOnSelectedContractTitles()
    {
        return new ArrayList<>(Arrays.asList("Code", "Commodity", "Docket Type", "Consignee", "Net Weight", "Price", "Total"));
    }
    public ArrayList<ArrayList<String>> getLoadsBasedOnSelectedContract(String contractIdentifier)
    {
        ArrayList<ArrayList<String>> selectedLoads = new ArrayList<>();
        Contract selectedContract = getContract(contractIdentifier);
        ArrayList<SecondWeight> secondWeights = getSecondWeights();
        double quantity = 0;
        for(int counter = 0; counter < secondWeights.size(); counter++)
        {
            if(selectedContract.getCommodity().getTitle().equals(secondWeights.get(counter).getFirstWeight().getCommodity().getTitle()))
            {
                if(selectedContract.getStartDate().before(secondWeights.get(counter).getDate()) &&
                   selectedContract.getEndDate().after(secondWeights.get(counter).getDate()))
                {
                    ArrayList<String> aSelectedLoad = new ArrayList<>();
                    aSelectedLoad.add(selectedContract.getCode() + "");
                    aSelectedLoad.add(selectedContract.getCommodity().getTitle());
                    aSelectedLoad.add(selectedContract.getDocketType().getDocketType());
                    aSelectedLoad.add(selectedContract.getConsignee().getFirstName() + " " + selectedContract.getConsignee().getLastName());
                    aSelectedLoad.add(secondWeights.get(counter).getNetWeight() + "");
                    aSelectedLoad.add(selectedContract.getPrice() + "");
                    aSelectedLoad.add((secondWeights.get(counter).getNetWeight() / 1000) * selectedContract.getPrice() + "");
                    selectedLoads.add(aSelectedLoad);
                }
            }
        }
        return selectedLoads;
        //return new ArrayList<>();
    }
    public void updateSecondWeight(String secondWeightCode, String secondWeightValue, String secondWeightDate, String firstWeightCode, String driverTitle,
                                   String commodityTitle, String firstWeightValue, String firstWeightDate, String docketTypeTitle, String consigneeTitle)
    {
        updateFirstWeight(firstWeightCode, driverTitle, commodityTitle, firstWeightValue, firstWeightDate, docketTypeTitle, consigneeTitle);
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", secondWeightCode);
        HashMap<String, String> updatedParameters = new HashMap<>();
        updatedParameters.put("weight", secondWeightValue);
        updatedParameters.put("date", secondWeightDate);
        main.updateTableRow("secondweights", updatedParameters, selectedParameters);
    }
    public void updateFirstWeight(String code, String driverTitle, String commodityTitle, String firstWeightValue, String firstWeightDate, String docketTypeTitle,
                                  String consigneeTitle)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", code);
        Driver aDriver = getDriverByValue(driverTitle);
        Commodity aCommodity = getCommodityByValue(commodityTitle);
        DocketType aDocketType = getDocketTypeByValue(docketTypeTitle);
        Consignee aConsignee = getConsigneeByValue(consigneeTitle, aDocketType);
        HashMap<String, String> updatedParameters = new HashMap<>();
        updatedParameters.put("driver", aDriver.getCode() + "");
        updatedParameters.put("commodity", aCommodity.getCode() + "");
        updatedParameters.put("weight", firstWeightValue);
        updatedParameters.put("date", firstWeightDate);
        updatedParameters.put("dockettype", aDocketType.getCode() + "");
        updatedParameters.put("consignee", aConsignee.getCode() + "");
        main.updateTableRow("firstweights", updatedParameters, selectedParameters);
    }
    public void updateContract(String code, String commodityTitle, String price, String total, String docketTypeTitle, String consigneeTitle, String startDate,
                               String endDate)
    {
        Commodity aCommodity = getCommodityByValue(commodityTitle);
        DocketType aDocketType = getDocketTypeByValue(docketTypeTitle);
        Consignee aConsignee = getConsigneeByValue(consigneeTitle, aDocketType);
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", code);
        HashMap<String, String> updatedParameters = new HashMap<>();
        updatedParameters.put("commodity", aCommodity.getCode() + "");
        updatedParameters.put("price", price);
        updatedParameters.put("total", total);
        updatedParameters.put("dockettype", aDocketType.getCode() + "");
        updatedParameters.put("consignee", aConsignee.getCode() + "");
        updatedParameters.put("startdate", startDate);
        updatedParameters.put("enddate", endDate);
        main.updateTableRow("contracts", updatedParameters, selectedParameters);
    }
    public void insertNewContract(String commodityTitle, String price, String total, String docketTypeTitle, String consigneeTitle, String startDate, String endDate)
    {
        int contractID = main.getMaxValueOfColumn("contracts", "code") + 1;
        Commodity aCommodity = getCommodityByValue(commodityTitle);
        DocketType aDocketType = getDocketTypeByValue(docketTypeTitle);
        Consignee aConsignee = getConsigneeByValue(consigneeTitle, aDocketType);
        main.insertTableRow("contracts", new ArrayList<>(Arrays.asList(contractID + "", aCommodity.getCode() + "", price, total, aDocketType.getCode() + "",
        aConsignee.getCode() + "", startDate, endDate)));
    }
    public ArrayList<String> getFirstWeightsTitles()
    {
        return new ArrayList<>(Arrays.asList("Code", "Date", "First Weight", "Driver", "Commodity", "Docket Type", "Consignee"));
    }
    public ArrayList<ArrayList<String>> getFirstWeightsValues()
    {
        ArrayList<ArrayList<String>> firstWeightsValues = new ArrayList<>();
        ArrayList<FirstWeight> firstWeights = getFirstWeights();
        firstWeights.forEach(x ->
        {
            ArrayList<String> aFirstWeight = new ArrayList<>();
            aFirstWeight.add(x.getCode() + "");
            aFirstWeight.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(x.getDate()));
            aFirstWeight.add(x.getWeight() + "");
            aFirstWeight.add(x.getDriver().getFirstName() + " " + x.getDriver().getLastName());
            aFirstWeight.add(x.getCommodity().getTitle());
            aFirstWeight.add(x.getDocketType().getDocketType());
            aFirstWeight.add(x.getConsignee().getFirstName() + " " + x.getConsignee().getLastName());
            firstWeightsValues.add(aFirstWeight);
        });
        return firstWeightsValues;
    }
    public ArrayList<String> getSecondWeightsTitles()
    {
        return new ArrayList<>(Arrays.asList("Code", "Date", "First Weight", "Second Weight", "Net Weight", "Commodity", "Docket Type", "Consignee"));
    }
    public ArrayList<ArrayList<String>> getSecondWeightsValues()
    {
        ArrayList<ArrayList<String>> secondWeightsValues = new ArrayList<>();
        ArrayList<SecondWeight> secondWeights = getSecondWeights();
        secondWeights.forEach(x ->
        {
            ArrayList<String> aSecondWeight = new ArrayList<>();
            aSecondWeight.add(x.getCode() + "");
            aSecondWeight.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(x.getDate()));
            aSecondWeight.add(x.getFirstWeight().getWeight() + "");
            aSecondWeight.add(x.getWeight() + "");
            aSecondWeight.add(x.getNetWeight() + "");
            aSecondWeight.add(x.getFirstWeight().getCommodity().getTitle());
            aSecondWeight.add(x.getFirstWeight().getDocketType().getDocketType());
            aSecondWeight.add(x.getFirstWeight().getConsignee().getFirstName() + " " + x.getFirstWeight().getConsignee().getLastName());
            secondWeightsValues.add(aSecondWeight);
        });
        return secondWeightsValues;
    }
    public ArrayList<String> getContractsTitles()
    {
        return new ArrayList<>(Arrays.asList("Code", "Commodity", "Price", "Total", "Docket Type", "Consignee", "Start Date", "End Date"));
    }
    public ArrayList<ArrayList<String>> getContractsValues()
    {
        ArrayList<ArrayList<String>> contractsValues = new ArrayList<>();
        ArrayList<Contract> contracts = getContracts();
        contracts.forEach(x ->
        {
            ArrayList<String> aContract = new ArrayList<>();
            aContract.add(x.getCode() + "");
            aContract.add(x.getCommodity().getTitle());
            aContract.add(x.getPrice() + "");
            aContract.add(x.getTotal() + "");
            aContract.add(x.getDocketType().getDocketType() + "");
            aContract.add(x.getConsignee().getFirstName() + " " + x.getConsignee().getLastName());
            aContract.add(x.getStartDate().toString());
            aContract.add(x.getEndDate().toString());
            contractsValues.add(aContract);
        });
        return contractsValues;
    }
    public ArrayList<Contract> getContracts()
    {
        ArrayList<Contract> contracts = new ArrayList<>();
        ArrayList<ArrayList<String>> contractsFromDatabase = main.getTableRows("contracts", new HashMap<>(), new ArrayList<>(), "");
        contractsFromDatabase.forEach(x ->
        {
            try
            {
                Commodity aCommodity = getCommodity(x.get(1));
                DocketType aDocketType = getDocketType(x.get(4));
                Consignee aConsignee = getConsignee(x.get(5), aDocketType);
                contracts.add(new Contract(Integer.parseInt(x.get(0)), aCommodity, Double.parseDouble(x.get(2)), Double.parseDouble(x.get(3)), aDocketType,
                aConsignee, new SimpleDateFormat("yyyy-MM-dd").parse(x.get(6)), new SimpleDateFormat("yyyy-MM-dd").parse(x.get(7))));
            }
            catch(Exception error)
            {
                JOptionPane.showMessageDialog(null, error);
            }
        });
        return contracts;
    }
    public ArrayList<String> getContractAsList(String contractIdentifier)
    {
        Contract currentContract = getContract(contractIdentifier);
        ArrayList<String> currentContractAsList = new ArrayList<>();
        currentContractAsList.add(currentContract.getCode() + "");
        currentContractAsList.add(currentContract.getCommodity().getTitle());
        currentContractAsList.add(currentContract.getPrice() + "");
        currentContractAsList.add(currentContract.getTotal() + "");
        currentContractAsList.add(currentContract.getDocketType().getDocketType());
        currentContractAsList.add(currentContract.getConsignee().getFirstName() + " " + currentContract.getConsignee().getLastName());
        currentContractAsList.add(currentContract.getStartDate().toString());
        currentContractAsList.add(currentContract.getEndDate().toString());
        return currentContractAsList;
    }
    public Contract getContract(String contractIdentifier)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", contractIdentifier);
        ArrayList<ArrayList<String>> contractFromDatabase = main.getTableRows("contracts", selectedParameters);
        if(contractFromDatabase.size() > 0)
        {
            try
            {
                Commodity aCommodity = getCommodity(contractFromDatabase.get(0).get(1));
                DocketType aDocketType = getDocketType(contractFromDatabase.get(0).get(4));
                Consignee aConsignee = getConsignee(contractFromDatabase.get(0).get(5), aDocketType);
                return new Contract(Integer.parseInt(contractFromDatabase.get(0).get(0)), aCommodity, Double.parseDouble(contractFromDatabase.get(0).get(2)),
                Double.parseDouble(contractFromDatabase.get(0).get(3)), aDocketType, aConsignee,
                new SimpleDateFormat("yyyy-MM-dd").parse(contractFromDatabase.get(0).get(6)),
                new SimpleDateFormat("yyyy-MM-dd").parse(contractFromDatabase.get(0).get(7)));
            }
            catch(Exception error)
            {
                JOptionPane.showMessageDialog(null, error);
            }
        }
        return null;
    }
    public ArrayList<SecondWeight> getSecondWeights()
    {
        ArrayList<SecondWeight> secondWeights = new ArrayList<>();
        ArrayList<ArrayList<String>> secondWeightsFromDatabase = main.getTableRows("secondweights", new HashMap<>(), new ArrayList<>(), "");
        secondWeightsFromDatabase.forEach(x ->
        {
            try
            {
                FirstWeight aFirstWeight = getFirstWeight(x.get(3));
                secondWeights.add(new SecondWeight(Integer.parseInt(x.get(0)), Double.parseDouble(x.get(1)),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(x.get(2)), aFirstWeight));
            }
            catch(Exception error)
            {
                JOptionPane.showMessageDialog(null, error);
            }
        });
        return secondWeights;
    }
    public ArrayList<FirstWeight> getFirstWeights()
    {
        ArrayList<FirstWeight> firstWeights = new ArrayList<>();
        ArrayList<ArrayList<String>> firstWeightsFromDatabase = main.getTableRows("firstweights", new HashMap<>(), new ArrayList<>(), "");
        firstWeightsFromDatabase.forEach(x ->
        {
            Driver aDriver = getDriver(x.get(1));
            Commodity aCommodity = getCommodity(x.get(2));
            DocketType aDocketType = getDocketType(x.get(5));
            Consignee aConsignee = getConsignee(x.get(6), aDocketType);
            try
            {
                firstWeights.add(new FirstWeight(Integer.parseInt(x.get(0)), aDriver, aCommodity, Double.parseDouble(x.get(3)),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(x.get(4)), aDocketType, aConsignee));
            }
            catch(Exception error)
            {
                JOptionPane.showMessageDialog(null, error);
            }
        });
        return firstWeights;
    }
    public SecondWeight getSecondWeight(String secondWeightIdentifier)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", secondWeightIdentifier);
        ArrayList<ArrayList<String>> selectedSecondWeight = main.getTableRows("secondweights", selectedParameters, new ArrayList<>(), "");
        if(selectedSecondWeight.size() > 0)
        {
            try
            {
                FirstWeight aFirstWeight = getFirstWeight(selectedSecondWeight.get(0).get(3));
                return new SecondWeight(Integer.parseInt(selectedSecondWeight.get(0).get(0)), Double.parseDouble(selectedSecondWeight.get(0).get(1)),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(selectedSecondWeight.get(0).get(2)), aFirstWeight);
            }
            catch(Exception error)
            {
                JOptionPane.showMessageDialog(null, error);
            }
        }
        return null;
    }
    private FirstWeight getFirstWeight(String firstWeightIdentifier)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", firstWeightIdentifier);
        ArrayList<ArrayList<String>> selectedFirstWeight = main.getTableRows("firstweights", selectedParameters, new ArrayList<>(), "");
        if(selectedFirstWeight.size() > 0)
        {
            Driver aDriver = getDriver(selectedFirstWeight.get(0).get(1));
            Commodity aCommodity = getCommodity(selectedFirstWeight.get(0).get(2));
            DocketType aDocketType = getDocketType(selectedFirstWeight.get(0).get(5));
            Consignee aConsignee = getConsignee(selectedFirstWeight.get(0).get(6), aDocketType);
            try
            {
                return new FirstWeight(Integer.parseInt(selectedFirstWeight.get(0).get(0)), aDriver, aCommodity, Double.parseDouble(selectedFirstWeight.get(0).get(3)),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(selectedFirstWeight.get(0).get(4)), aDocketType, aConsignee);
            }
            catch(Exception error)
            {
                JOptionPane.showMessageDialog(null, error);
            }
        }
        return null;
    }
    private Consignee getConsignee(String consigneeIdentifier, DocketType docketType)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", consigneeIdentifier);
        if(docketType.getDocketType().equals("Purchase"))
        {
            ArrayList<ArrayList<String>> selectedConsignee = main.getTableRows("suppliers", selectedParameters, new ArrayList<>(), "");
            if(selectedConsignee.size() > 0)
                return new Consignee(Integer.parseInt(selectedConsignee.get(0).get(0)), selectedConsignee.get(0).get(1), selectedConsignee.get(0).get(2));
        }
        else if(docketType.getDocketType().equals("Sale"))
        {
            ArrayList<ArrayList<String>> selectedConsignee = main.getTableRows("customers", selectedParameters, new ArrayList<>(), "");
            if(selectedConsignee.size() > 0)
                return new Consignee(Integer.parseInt(selectedConsignee.get(0).get(0)), selectedConsignee.get(0).get(1), selectedConsignee.get(0).get(2));
        }
        return null;
    }
    public Consignee getConsigneeByValue(String consigneeValue, DocketType docketType)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        String[] consigneeComponents = consigneeValue.split(" ");
        if(consigneeComponents.length == 2)
        {
            selectedParameters.put("firstname", consigneeComponents[0]);
            selectedParameters.put("lastname", consigneeComponents[1]);
            if(docketType.getDocketType().equals("Purchase"))
            {
                ArrayList<ArrayList<String>> selectedConsignee = main.getTableRows("suppliers", selectedParameters, new ArrayList<>(), new ArrayList<>(),
                false);
                if(selectedConsignee.size() > 0)
                    return new Consignee(Integer.parseInt(selectedConsignee.get(0).get(0)), selectedConsignee.get(0).get(1), selectedConsignee.get(0).get(2));
            }
            if(docketType.getDocketType().equals("Sale"))
            {
                ArrayList<ArrayList<String>> selectedConsignee = main.getTableRows("customers", selectedParameters, new ArrayList<>(), new ArrayList<>(), false);
                if(selectedConsignee.size() > 0)
                    return new Consignee(Integer.parseInt(selectedConsignee.get(0).get(0)), selectedConsignee.get(0).get(1), selectedConsignee.get(0).get(2));
            }
        }
        return null;
    }
    public Driver getDriverByValue(String driverValue)
    {
        String[] driverComponents = driverValue.split(" ");
        if(driverComponents.length == 2)
        {
            HashMap<String, String> selectedParameters = new HashMap<>();
            selectedParameters.put("firstname", driverComponents[0]);
            selectedParameters.put("lastname", driverComponents[1]);
            ArrayList<ArrayList<String>> selectedDriver = main.getTableRows("drivers", selectedParameters, new ArrayList<>(), new ArrayList<>(), false);
            if(selectedDriver.size() > 0)
                return new Driver(Integer.parseInt(selectedDriver.get(0).get(0)), selectedDriver.get(0).get(1), selectedDriver.get(0).get(2));
        }
        return null;
    }
    public Commodity getCommodityByValue(String commodityValue)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("title", commodityValue);
        ArrayList<ArrayList<String>> selectedCommodity = main.getTableRows("commodities", selectedParameters);
        if(selectedCommodity.size() > 0)
            return new Commodity(Integer.parseInt(selectedCommodity.get(0).get(0)), selectedCommodity.get(0).get(1));
        return null;
    }
    public DocketType getDocketTypeByValue(String docketTypeValue)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("dockettype", docketTypeValue);
        ArrayList<ArrayList<String>> selectedDocketType = main.getTableRows("dockettypes", selectedParameters, new ArrayList<>(), "");
        if(selectedDocketType.size() > 0)
            return new DocketType(Integer.parseInt(selectedDocketType.get(0).get(0)), selectedDocketType.get(0).get(1));
        return null;
    }
    private DocketType getDocketType(String docketTypeIdentifier)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", docketTypeIdentifier);
        ArrayList<ArrayList<String>> selectedDocketType = main.getTableRows("dockettypes", selectedParameters, new ArrayList<>(), "");
        if(selectedDocketType.size() > 0)
            return new DocketType(Integer.parseInt(selectedDocketType.get(0).get(0)), selectedDocketType.get(0).get(1));
        return null;
    }
    private Driver getDriver(String driverIdentifier)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", driverIdentifier);
        ArrayList<ArrayList<String>> selectedDriver = main.getTableRows("drivers", selectedParameters, new ArrayList<>(), "");
        if(selectedDriver.size() > 0)
            return new Driver(Integer.parseInt(selectedDriver.get(0).get(0)), selectedDriver.get(0).get(1), selectedDriver.get(0).get(2));
        return null;
    }
    private Commodity getCommodity(String commodityIdentifier)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", commodityIdentifier);
        ArrayList<ArrayList<String>> selectedCommodity = main.getTableRows("commodities", selectedParameters, new ArrayList<>(), "");
        if(selectedCommodity.size() > 0)
            return new Commodity(Integer.parseInt(selectedCommodity.get(0).get(0)), selectedCommodity.get(0).get(1));
        return null;
    }
    public ArrayList<Commodity> getCommodities()
    {
        ArrayList<Commodity> selectedCommodities = new ArrayList<>();
        ArrayList<ArrayList<String>> commodities = main.getTableRows("commodities", new HashMap<>(), new ArrayList<>(), "");
        for(ArrayList<String> aCommodity: commodities)
            selectedCommodities.add(new Commodity(Integer.parseInt(aCommodity.get(0)), aCommodity.get(1)));
        return selectedCommodities;
    }
    public ArrayList<String> getCommoditiesValues()
    {
        ArrayList<String> commoditiesValues = new ArrayList<>();
        ArrayList<Commodity> selectedCommodities = getCommodities();
        selectedCommodities.forEach(x -> commoditiesValues.add(x.getTitle()));
        return commoditiesValues;
    }
    public ArrayList<Driver> getDrivers()
    {
        ArrayList<Driver> selectedDrivers = new ArrayList<>();
        ArrayList<ArrayList<String>> drivers = main.getTableRows("drivers", new HashMap<>(), new ArrayList<>(), "");
        for(ArrayList<String> aDriver: drivers)
            selectedDrivers.add(new Driver(Integer.parseInt(aDriver.get(0)), aDriver.get(1), aDriver.get(2)));
        return selectedDrivers;
    }
    public ArrayList<String> getDriversValues()
    {
        ArrayList<String> driversValues = new ArrayList<>();
        ArrayList<Driver> selectedDrivers = getDrivers();
        selectedDrivers.forEach(x -> driversValues.add(x.getFirstName() + " " + x.getLastName()));
        return driversValues;
    }
    public ArrayList<DocketType> getDocketTypes()
    {
        ArrayList<DocketType> selectedDocketTypes = new ArrayList<>();
        ArrayList<ArrayList<String>> docketTypes = main.getTableRows("dockettypes", new HashMap<>(), new ArrayList<>(), "");
        for(ArrayList<String> aDocketType: docketTypes)
            selectedDocketTypes.add(new DocketType(Integer.parseInt(aDocketType.get(0)), aDocketType.get(1)));
        return selectedDocketTypes;
    }
    public ArrayList<String> getDocketTypesValues()
    {
        ArrayList<String> docketTypesValues = new ArrayList<>();
        ArrayList<DocketType> selectedDocketTypes = getDocketTypes();
        selectedDocketTypes.forEach(x -> docketTypesValues.add(x.getDocketType()));
        return docketTypesValues;
    }
    public ArrayList<Consignee> getConsignees(DocketType aDocketType)
    {
        ArrayList<Consignee> selectedConsignees = new ArrayList<>();
        ArrayList<ArrayList<String>> consignees = new ArrayList<>();
        if(aDocketType.getDocketType().equals("Sale"))
            consignees = main.getTableRows("customers", new HashMap<>(), new ArrayList<>(), "");
        else if(aDocketType.getDocketType().equals("Purchase"))
            consignees = main.getTableRows("suppliers", new HashMap<>(), new ArrayList<>(), "");
        for(ArrayList<String> aConsignee: consignees)
            selectedConsignees.add(new Consignee(Integer.parseInt(aConsignee.get(0)), aConsignee.get(1), aConsignee.get(2)));
        return selectedConsignees;
    }
    public ArrayList<String> getConsigneesValues(DocketType aDocketType)
    {
        ArrayList<String> consigneesValues = new ArrayList<>();
        ArrayList<Consignee> selectedConsignees = getConsignees(aDocketType);
        selectedConsignees.forEach(x -> consigneesValues.add(x.getFirstName() + " " + x.getLastName()));
        return consigneesValues;
    }
    public String generateContractReport(String contractIdentifier)
    {
        Contract aContract = getContract(contractIdentifier);
        Report aReport = new Report("contracts/" + aContract.getCode() + ".pdf");
        ArrayList<String> reportContent = new ArrayList<>(Arrays.asList("Contract Type: " + aContract.getDocketType().getDocketType(),
        "Consignee: " + aContract.getConsignee().getFirstName() + " " + aContract.getConsignee().getLastName(),
        "Commodity: " + aContract.getCommodity().getTitle(), "Price: " + aContract.getPrice(),
        "Total: " + aContract.getTotal(),
        "Start Date: " + new SimpleDateFormat("yyyy-MM-dd").format(aContract.getStartDate()),
        "End Date: " + new SimpleDateFormat("yyyy-MM-dd").format(aContract.getEndDate())));
        aReport.addContent(reportContent);
        return "file:///"  + System.getProperty("user.dir").replace("\\", "/") + "/contracts/" + contractIdentifier + ".pdf";
    }
    public void printContractReport(String contractIdentifier)
    {
        if(!new File("contracts/" + contractIdentifier + ".pdf").exists())
            generateContractReport(contractIdentifier);
        new Printer("contracts/" + contractIdentifier + ".pdf");
    }
    public Boolean emailContractReport(String emailAddress, String contractIdentifier)
    {
        if(!new File("contracts/" + contractIdentifier + ".pdf").exists())
            generateContractReport(contractIdentifier);
        Email anEmail = new Email();
        return anEmail.sendMessage(emailAddress, "Contract " + contractIdentifier,
        "Dear Sir/Madam\n\nPlease find attached the contract for " + contractIdentifier + ".\n\nYours sincerely,\nS Cullinan",
        "contracts/" + contractIdentifier + ".pdf", contractIdentifier + ".pdf");
    }
    public void generateReport(String secondWeightIdentifier)
    {
        SecondWeight aSecondWeight = getSecondWeight(secondWeightIdentifier);
        Report aReport = new Report("dockets/" + aSecondWeight.getCode() + ".pdf");
        ArrayList<String> reportContent = new ArrayList<>(Arrays.asList("Weight Type: " + aSecondWeight.getFirstWeight().getDocketType().getDocketType(),
        "Consignee: " + aSecondWeight.getFirstWeight().getConsignee().getFirstName() + " " + aSecondWeight.getFirstWeight().getConsignee().getLastName(),
        "Driver: " + aSecondWeight.getFirstWeight().getDriver().getFirstName() + " " + aSecondWeight.getFirstWeight().getDriver().getLastName(),
        "Commodity: " + aSecondWeight.getFirstWeight().getCommodity().getTitle(),
        "First Weight Sequence Number: " + aSecondWeight.getFirstWeight().getCode(),
        "First Weight Actual Weight: " + aSecondWeight.getFirstWeight().getWeight(),
        "First Weight Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(aSecondWeight.getFirstWeight().getDate()),
        "Second Weight Sequence Number: " + aSecondWeight.getCode(),
        "Second Weight Actual Weight: " + aSecondWeight.getWeight(),
        "Second Weight Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(aSecondWeight.getDate())));
        if(aSecondWeight.getFirstWeight().getDocketType().getCode() == 1)
            reportContent.add("Total Weight: " + (aSecondWeight.getFirstWeight().getWeight() - aSecondWeight.getWeight()));
        else if(aSecondWeight.getFirstWeight().getDocketType().getCode() == 2)
            reportContent.add("Total Weight: " + (aSecondWeight.getWeight() - aSecondWeight.getFirstWeight().getWeight()));
        aReport.addContent(reportContent);
    }
    public void printReport(String secondWeightIdentifier)
    {
        if(!new File("dockets/" + secondWeightIdentifier + ".pdf").exists())
            generateReport(secondWeightIdentifier);
        new Printer("dockets/" + secondWeightIdentifier + ".pdf");
    }
    public void emailReport(String emailAddress, String secondWeightIdentifier)
    {
        if(!new File("dockets/" + secondWeightIdentifier + ".pdf").exists())
            generateReport(secondWeightIdentifier);
        Email anEmail = new Email();
        anEmail.sendMessage(emailAddress, "Weight Docket " + secondWeightIdentifier,
        "Dear Sir/Madam\n\nPlease find attached the weight docket for " + secondWeightIdentifier + ".\n\nYours sincerely,\nS Cullinan",
        "dockets/" + secondWeightIdentifier + ".pdf", secondWeightIdentifier + ".pdf");
    }
    /*public ArrayList<BatchNumber> getLoadsWithBatchNumbers(String consigneeType)
    {
        return getLoads(consigneeType, true, false);
    }
    public void insertNewBatchNumber(BatchNumber aBatchNumber)
    {
        int maximumValue = main.getMaxValueOfColumn("batchnumbers", "code");
        aBatchNumber.setCode(maximumValue + 1);
        main.insertTableRow("batchnumbers", aBatchNumber.toList());
    }
    public ArrayList<BatchNumber> getLoadsWithNoBatchNumbers(String consigneeType)
    {
        return getLoads(consigneeType, false, true);
    }
    private ArrayList<BatchNumber> getLoads(String consigneeType, Boolean includeLoadsWithBatchNumbers, Boolean includeLoadsWithNoBatchNumbers)
    {
        ArrayList<String> tableTitles = new ArrayList<>(Arrays.asList("secondweights", "firstweights", "commodities", consigneeType, "dockettypes", "drivers"));
        ArrayList<String> joinConditions = new ArrayList<>(Arrays.asList("secondweights.firstweight", "firstweights.code", "firstweights.commodity", "commodities.code",
        "firstweights.consignee", consigneeType + ".code", "firstweights.dockettype", "dockettypes.code", "firstweights.driver", "drivers.code"));
        HashMap<String, String> selectedValues = new HashMap<>();
        if (consigneeType.equals("suppliers"))
            selectedValues.put("dockettypes.code", "2");
        else if (consigneeType.equals("customers"))
            selectedValues.put("dockettypes.code", "1");
        ArrayList<ArrayList<String>> allLoads = main.getJoinedTableRows(tableTitles, joinConditions, selectedValues, new ArrayList<>(), "");
        for (ArrayList<String> aLoad : allLoads)
            for (String aLoadComponent : aLoad)
                System.out.println(aLoadComponent);
        ArrayList<ArrayList<String>> batchNumbers = main.getTableRows("batchnumbers", new HashMap<>(), new ArrayList<>(Arrays.asList("secondweight")),
                "");
        HashSet<String> batchedLoads = new HashSet<>();
        batchNumbers.forEach(x -> batchedLoads.add(x.get(0)));
        ArrayList<BatchNumber> selectedLoads = new ArrayList<>();
        for (ArrayList<String> aLoad : allLoads) {
            if (!batchedLoads.contains(aLoad.get(0)) && includeLoadsWithNoBatchNumbers) {
                FirstWeight aFirstWeight = new FirstWeight(Integer.parseInt(aLoad.get(3)), new Driver(Integer.parseInt(aLoad.get(13)), aLoad.get(14), aLoad.get(15)),
                        new Commodity(Integer.parseInt(aLoad.get(11)), aLoad.get(12)), Double.parseDouble(aLoad.get(7)),
                        Utilities.createDate(aLoad.get(8), "yyyy-MM-dd HH:mm:ss"), new DocketType(Integer.parseInt(aLoad.get(16)), aLoad.get(17)),
                        new Consignee(Integer.parseInt(aLoad.get(18)), aLoad.get(19), aLoad.get(20)));
                SecondWeight aSecondWeight = new SecondWeight(Integer.parseInt(aLoad.get(0)), Double.parseDouble(aLoad.get(1)),
                        Utilities.createDate(aLoad.get(2), "yyyy-MM-dd HH:mm:ss"), aFirstWeight);
                selectedLoads.add(new BatchNumber(aSecondWeight));
            } else if (batchedLoads.contains(aLoad.get(0)) && includeLoadsWithBatchNumbers) {
                FirstWeight aFirstWeight = new FirstWeight(Integer.parseInt(aLoad.get(3)), new Driver(Integer.parseInt(aLoad.get(13)), aLoad.get(14), aLoad.get(15)),
                        new Commodity(Integer.parseInt(aLoad.get(11)), aLoad.get(12)), Double.parseDouble(aLoad.get(7)),
                        Utilities.createDate(aLoad.get(8), "yyyy-MM-dd HH:mm:ss"), new DocketType(Integer.parseInt(aLoad.get(16)), aLoad.get(17)),
                        new Consignee(Integer.parseInt(aLoad.get(18)), aLoad.get(19), aLoad.get(20)));
                SecondWeight aSecondWeight = new SecondWeight(Integer.parseInt(aLoad.get(0)), Double.parseDouble(aLoad.get(1)),
                        Utilities.createDate(aLoad.get(2), "yyyy-MM-dd HH:mm:ss"), aFirstWeight);
                selectedLoads.add(new BatchNumber(aSecondWeight));
            }
        }
        return selectedLoads;
    }*/
    public void extractInformationFromRecknerSpreadsheet()
    {
        extractInformationFromSpreadSheet("Ration_Reckner_Version_9.xlsx", 2);
    }
    public void extractInformationFromRationSpreadsheet()
    {
        extractInformationFromSpreadSheet("Beef-Ration-Calculator-041115.xlsx", 1);
    }
    public void extractInformationFromSpreadSheet(String fileName, int sheetNumber)
    {
        try
        {
            FileInputStream aFile = new FileInputStream(new File(fileName));
            XSSFWorkbook aWorkbook = new XSSFWorkbook(aFile);
            Iterator<Row> availableRows = aWorkbook.getSheetAt(sheetNumber).iterator();
            ArrayList<ArrayList<String>> rowContents = new ArrayList<>();
            int counter = 1;
            while(availableRows.hasNext())
            {
                Row currentRow = availableRows.next();
                if((counter >= 4 && counter <= 78 && fileName.equals("Beef-Ration-Calculator-041115.xlsx")) ||
                (counter >= 5 && counter <= 155 && fileName.equals("Ration_Reckner_Version_9.xlsx")))
                {
                    ArrayList<String> aRow = new ArrayList<>(Arrays.asList((counter - 3) + ""));
                    Iterator<Cell> availableCells = currentRow.iterator();
                    while (availableCells.hasNext())
                    {
                        Cell currentCell = availableCells.next();
                        aRow.add(currentCell.toString());
                    }
                    rowContents.add(aRow);
                }
                counter++;
            }
            aWorkbook.close();
            aFile.close();
            for(ArrayList<String> aRow : rowContents)
                for(String aCell : aRow)
                    System.out.println(aCell);
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
}
