package Models;
import Entities.BatchNumber;
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
    public ArrayList<BatchNumber> getLoadsWithNoBatchNumbers(String consigneeType)
    {
        ArrayList<String> tableTitles = new ArrayList<>(Arrays.asList("commodities", "secondweights", "firstweights", consigneeType, "dockettypes"));
        ArrayList<String> joinConditions = new ArrayList<>(Arrays.asList("secondweights.firstweight", "firstweights.code", "firstweights.commodity", "commodities.code",
        "firstweights.consignee", consigneeType + ".code", "firstweights.dockettype", "dockettypes.code"));
        ArrayList<String> desiredColumns = new ArrayList<>(Arrays.asList("secondweights.code", "secondweights.date", "commodities.title", "secondweights.weight",
        "firstweights.weight", consigneeType + ".firstname", consigneeType + ".lastname"));
        HashMap<String, String> selectedValues = new HashMap<>();
        if(consigneeType.equals("suppliers"))
            selectedValues.put("dockettypes.code", "2");
        else if(consigneeType.equals("customers"))
            selectedValues.put("dockettypes.code", "1");
        ArrayList<ArrayList<String>> allLoads = main.getJoinedTableRows(tableTitles, joinConditions, selectedValues, desiredColumns, "");
        for(ArrayList<String> aLoad : allLoads)
            for(String aLoadComponent: aLoad)
                System.out.println(aLoadComponent);
        ArrayList<ArrayList<String>> batchNumbers = main.getTableRows("batchnumbers", new HashMap<>(), new ArrayList<>(Arrays.asList("secondweight")),
        "");
        HashSet<String> batchedLoads = new HashSet<>();
        batchNumbers.forEach(x -> batchedLoads.add(x.get(0)));
        ArrayList<BatchNumber> loadsWithoutBatchNumbers = new ArrayList<>();
        for(ArrayList<String> aLoad : allLoads)
        {
            if(!batchedLoads.contains(aLoad.get(0)))
                loadsWithoutBatchNumbers.add(new BatchNumber(aLoad.get(2), Double.parseDouble(aLoad.get(3)) - Double.parseDouble(aLoad.get(4)),
                Utilities.createDate(aLoad.get(1), "dddd/MM/yyyy hh:mm:ss")));
        }
        return loadsWithoutBatchNumbers;
    }
    public void extractInformationFromSpreadSheet()
    {
        try
        {
            FileInputStream aFile = new FileInputStream(new File("Beef-Entities.Ration-Models.Calculator-041115.xlsx"));
            XSSFWorkbook aWorkbook = new XSSFWorkbook(aFile);
            Iterator<Row> availableRows = aWorkbook.getSheetAt(1).iterator();
            ArrayList<ArrayList<String>> rowContents = new ArrayList<>();
            int counter = 1;
            while(availableRows.hasNext())
            {
                Row currentRow = availableRows.next();
                if(counter >= 4 && counter <= 78)
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
