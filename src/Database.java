import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.out;

public class Database
{
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    private String databaseUrl = "jdbc:mysql://localhost/weighbridge";
    private String user = "root";
    private String password = "Stiofan10";
    private String databaseTitle = "weighbridge";
    public Database()
    {
        try
        {
            Class.forName(jdbcDriver);
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    public ArrayList<ArrayList<String>> getJoinedTableRows(ArrayList<String> tableTitles, ArrayList<String> joinConditions,
                                                           HashMap<String, String> selectedParameters, ArrayList<String> columnTitles, String columnTitleForSorting)
    {
        try
        {
            Connection connection = DriverManager.getConnection(databaseUrl, user, password);
            Statement currentStatement = connection.createStatement();
            StringBuilder sqlStatement = new StringBuilder("select ");
            if(columnTitles.size() == 0)
                sqlStatement.append("*");
            else
            {
                columnTitles.forEach(x -> sqlStatement.append(x + ", "));
                sqlStatement.setLength(sqlStatement.length() - 2);
            }
            if(tableTitles.size() >= 1)
                sqlStatement.append(" from " + tableTitles.get(0));
            for(int counter = 0; counter < (tableTitles.size() - 1); counter++)
            {
                sqlStatement.append(" join " + tableTitles.get(counter + 1) + " on " + joinConditions.get(counter * 2) + " = " +
                joinConditions.get((counter * 2) + 1));
            }
            addParametersToSQLStatement(selectedParameters, sqlStatement);
            if(columnTitleForSorting.length() > 0)
                sqlStatement.append(" order by " + columnTitleForSorting + " asc");
            System.out.println("SQL STATEMENT: " + sqlStatement.toString());
            ArrayList<ArrayList<String>> retrievedRows = retrieveResults(sqlStatement.toString(), connection.createStatement());
            currentStatement.close();
            connection.close();
            return retrievedRows;
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
            return new ArrayList<>();
        }
    }
    public ArrayList<ArrayList<String>> getTableRows(String tableName, HashMap<String, String> selectedParameters, ArrayList<String> columnTitles,
                                                     String columnTitleForSorting)
    {
        try
        {
            Connection connection = DriverManager.getConnection(databaseUrl, user, password);
            Statement currentStatement = connection.createStatement();
            StringBuilder sqlStatement = new StringBuilder("select ");
            if(columnTitles.size() == 0)
                sqlStatement.append("*");
            else
            {
                columnTitles.forEach(x -> sqlStatement.append(x + ", "));
                sqlStatement.setLength(sqlStatement.length() - 2);
            }
            sqlStatement.append(" from " + tableName);
            addParametersToSQLStatement(selectedParameters, sqlStatement);
            if(columnTitleForSorting.length() > 0)
                sqlStatement.append(" order by " + columnTitleForSorting + " asc");
            ArrayList<ArrayList<String>> retrievedRows = retrieveResults(sqlStatement.toString(), connection.createStatement());
            currentStatement.close();
            connection.close();
            return retrievedRows;
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
            return new ArrayList<>();
        }
    }
    public int getMaxValueOfColumn(String tableName, String columnName)
    {
        try
        {
            int maxValue = 0;
            Connection connection = DriverManager.getConnection(databaseUrl, user, password);
            Statement currentStatement = connection.createStatement();
            ResultSet selectedRows = currentStatement.executeQuery("select max(" + columnName + ") from " + tableName);
            while(selectedRows.next())
                maxValue = selectedRows.getInt(1);
            selectedRows.close();
            currentStatement.close();
            connection.close();
            return maxValue;
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
            return -1;
        }
    }
    public ArrayList<String> getColumnTitles(String tableName)
    {
        try
        {
            ArrayList<String> columnTitles = new ArrayList<>();
            Connection connection = DriverManager.getConnection(databaseUrl, user, password);
            Statement currentStatement = connection.createStatement();
            ResultSet selectedRows = currentStatement.executeQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE '" + tableName + "' AND " +
            "TABLE_SCHEMA LIKE '" + databaseTitle + "';");
            Integer numberOfColumns = selectedRows.getMetaData().getColumnCount();
            while(selectedRows.next())
            {
                for(int counter = 1; counter <= numberOfColumns; counter++)
                    columnTitles.add(selectedRows.getString(counter));
            }
            selectedRows.close();
            currentStatement.close();
            connection.close();
            return columnTitles;
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
            return new ArrayList<>();
        }
    }
    public void insertTableRow(String tableName, ArrayList<String> tableRowValues)
    {
        try
        {
            Connection connection = DriverManager.getConnection(databaseUrl, user, password);
            StringBuilder sqlStatement = new StringBuilder("insert into " + tableName + " values (");
            tableRowValues.forEach(x -> sqlStatement.append("?, "));
            sqlStatement.setLength(sqlStatement.length() - 2);
            sqlStatement.append(")");
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement.toString());
            Integer counter = 0;
            for(counter = 1; counter <= tableRowValues.size(); counter++)
            {
                if(Utilities.isWholeNumber(tableRowValues.get(counter - 1)))
                    preparedStatement.setInt(counter, Integer.parseInt(tableRowValues.get(counter - 1)));
                else if(Utilities.isDecimal(tableRowValues.get(counter - 1)))
                    preparedStatement.setDouble(counter, Double.parseDouble(tableRowValues.get(counter - 1)));
                else
                    preparedStatement.setString(counter, tableRowValues.get(counter - 1));
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    public void removeTableRow(String tableName, HashMap<String, String> selectedParameters)
    {
        try
        {
            Connection connection = DriverManager.getConnection(databaseUrl, user, password);
            StringBuilder sqlStatement = new StringBuilder("delete from " + tableName);
            addParametersToSQLStatement(selectedParameters, sqlStatement);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error, "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void updateTableRow(String tableName, HashMap<String, String> updatedParameters, HashMap<String, String> selectedParameters)
    {
        try
        {
            Connection connection = DriverManager.getConnection(databaseUrl, user, password);
            StringBuilder sqlStatement = new StringBuilder("update " + tableName + " set ");
            updatedParameters.forEach((x, y) ->
            {
                if(Utilities.isDecimal(y) || Utilities.isWholeNumber(y) || Utilities.isDate(y, "yyyy/MM/dd"))
                    sqlStatement.append(x + " = " + y + ", ");
                else
                    sqlStatement.append(x + " = '" + y + "', ");
            });

            sqlStatement.setLength(sqlStatement.length() - 2);
            addParametersToSQLStatement(selectedParameters, sqlStatement);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    private void addParametersToSQLStatement(HashMap<String, String> selectedParameters, StringBuilder sqlStatement)
    {
        if(selectedParameters.size() > 0)
        {
            sqlStatement.append(" where ");
            selectedParameters.forEach((x, y) ->
            {
                if(Utilities.isDecimal(y) || Utilities.isWholeNumber(y) || Utilities.isDate(y, "yyyyMMdd"))
                    sqlStatement.append(x + " = " + y + " or ");
                else
                    sqlStatement.append(x + " like '%" + y + "%' or ");
            });
            sqlStatement.setLength(sqlStatement.length() - 4);
        }
    }
    private ArrayList<ArrayList<String>> retrieveResults(String sqlStatement, Statement currentStatement)
    {
        try
        {
            ResultSet selectedRows = currentStatement.executeQuery(sqlStatement);
            ArrayList<ArrayList<String>> retrievedRows = new ArrayList<>();
            Integer numberOfColumns = selectedRows.getMetaData().getColumnCount();
            while (selectedRows.next())
            {
                ArrayList<String> aRetrievedRow = new ArrayList<>();
                for (Integer k = 1; k <= numberOfColumns; k++)
                    aRetrievedRow.add(selectedRows.getString(k));
                retrievedRows.add(aRetrievedRow);
            }
            selectedRows.close();
            return retrievedRows;
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
            return new ArrayList<>();
        }
    }
}
