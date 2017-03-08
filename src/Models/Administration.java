package Models;
import Entities.*;
import Frames.Components;
import Database.Database;
import Utilities.Utilities;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
public class Administration extends Components
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
    public ArrayList<ArrayList<String>> getContractsCustomersInformation()
    {
        ArrayList<String> tableTitles = new ArrayList<>(Arrays.asList("contracts", "commodities", "dockettypes", "customers"));
        ArrayList<String> joinConditions = new ArrayList<>(Arrays.asList("contracts.commodity", "commodities.code", "contracts.dockettype", "dockettypes.code",
        "contracts.consignee", "customers.code"));
        ArrayList<String> desiredColumns = new ArrayList<>(Arrays.asList("commodities.title", "contracts.total", "contracts.price", "dockettypes.dockettype",
        "customers.firstname", "customers.lastname"));
        HashMap<String, String> selectedValues = new HashMap<>();
        selectedValues.put("dockettypes.code", "2");
        return main.getJoinedTableRows(tableTitles, joinConditions, selectedValues, desiredColumns, "");
    }
    public ArrayList<ArrayList<String>> getContractsSuppliersInformation()
    {
        ArrayList<String> tableTitles = new ArrayList<>(Arrays.asList("contracts", "commodities", "dockettypes", "suppliers"));
        ArrayList<String> joinConditions = new ArrayList<>(Arrays.asList("contracts.commodity", "commodities.code", "contracts.dockettype", "dockettypes.code",
        "contracts.consignee", "suppliers.code"));
        ArrayList<String> desiredColumns = new ArrayList<>(Arrays.asList("commodities.title", "contracts.total", "contracts.price", "dockettypes.dockettype",
        "suppliers.firstname", "suppliers.lastname"));
        HashMap<String, String> selectedValues = new HashMap<>();
        selectedValues.put("dockettypes.code", "2");
        return main.getJoinedTableRows(tableTitles, joinConditions, selectedValues, desiredColumns, "");
    }
    public ArrayList<String> getContractsColumnTitles()
    {
        return new ArrayList<>(Arrays.asList("Commodity", "Total Quantity", "Total Price", "Docket Type", "First Name", "Last Name"));
    }
    public ArrayList<BatchNumber> getLoadsWithBatchNumbers(String consigneeType)
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
        if(consigneeType.equals("suppliers"))
            selectedValues.put("dockettypes.code", "2");
        else if(consigneeType.equals("customers"))
            selectedValues.put("dockettypes.code", "1");
        ArrayList<ArrayList<String>> allLoads = main.getJoinedTableRows(tableTitles, joinConditions, selectedValues, new ArrayList<>(), "");
        for(ArrayList<String> aLoad : allLoads)
            for(String aLoadComponent: aLoad)
                System.out.println(aLoadComponent);
        ArrayList<ArrayList<String>> batchNumbers = main.getTableRows("batchnumbers", new HashMap<>(), new ArrayList<>(Arrays.asList("secondweight")),
                "");
        HashSet<String> batchedLoads = new HashSet<>();
        batchNumbers.forEach(x -> batchedLoads.add(x.get(0)));
        ArrayList<BatchNumber> selectedLoads = new ArrayList<>();
        for(ArrayList<String> aLoad : allLoads)
        {
            if(!batchedLoads.contains(aLoad.get(0)) && includeLoadsWithNoBatchNumbers)
            {
                FirstWeight aFirstWeight = new FirstWeight(Integer.parseInt(aLoad.get(3)), new Driver(Integer.parseInt(aLoad.get(13)), aLoad.get(14), aLoad.get(15)),
                new Commodity(Integer.parseInt(aLoad.get(11)), aLoad.get(12)), Double.parseDouble(aLoad.get(7)),
                Utilities.createDate(aLoad.get(8), "yyyy/MM/dd HH:mm:ss"), new DocketType(Integer.parseInt(aLoad.get(16)), aLoad.get(17)),
                new Consignee(Integer.parseInt(aLoad.get(18)), aLoad.get(19), aLoad.get(20)));
                SecondWeight aSecondWeight = new SecondWeight(Integer.parseInt(aLoad.get(0)), Double.parseDouble(aLoad.get(1)),
                Utilities.createDate(aLoad.get(2), "yyyy/MM/dd HH:mm:ss"), aFirstWeight);
                selectedLoads.add(new BatchNumber(aSecondWeight));
            }
            else if(batchedLoads.contains(aLoad.get(0)) && includeLoadsWithBatchNumbers)
            {
                FirstWeight aFirstWeight = new FirstWeight(Integer.parseInt(aLoad.get(3)), new Driver(Integer.parseInt(aLoad.get(13)), aLoad.get(14), aLoad.get(15)),
                new Commodity(Integer.parseInt(aLoad.get(11)), aLoad.get(12)), Double.parseDouble(aLoad.get(7)),
                Utilities.createDate(aLoad.get(8), "yyyy/MM/dd HH:mm:ss"), new DocketType(Integer.parseInt(aLoad.get(16)), aLoad.get(17)),
                new Consignee(Integer.parseInt(aLoad.get(18)), aLoad.get(19), aLoad.get(20)));
                SecondWeight aSecondWeight = new SecondWeight(Integer.parseInt(aLoad.get(0)), Double.parseDouble(aLoad.get(1)),
                Utilities.createDate(aLoad.get(2), "yyyy/MM/dd HH:mm:ss"), aFirstWeight);
                selectedLoads.add(new BatchNumber(aSecondWeight));
            }
        }
        return selectedLoads;
    }
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
