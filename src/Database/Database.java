package Database;

import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import Utilities.*;
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
    public ArrayList<ArrayList<String>> getJoinedTableRows(ArrayList<String> tableTitles, ArrayList<String> joinConditions)
    {
        return getJoinedTableRows(tableTitles, joinConditions, new HashMap<>(), new ArrayList<>(), new ArrayList<>(), true);
    }
    public ArrayList<ArrayList<String>> getJoinedTableRows(ArrayList<String> tableTitles, ArrayList<String> joinConditions,
                                                           HashMap<String, String> selectedParameters)
    {
        return getJoinedTableRows(tableTitles, joinConditions, selectedParameters, new ArrayList<>(), new ArrayList<>(), true);
    }
    public ArrayList<ArrayList<String>> getJoinedTableRows(ArrayList<String> tableTitles, ArrayList<String> joinConditions,
                                                           HashMap<String, String> selectedParameters, ArrayList<String> columnTitles)
    {
        return getJoinedTableRows(tableTitles, joinConditions, selectedParameters, columnTitles, new ArrayList<>(), true);
    }
    public ArrayList<ArrayList<String>> getJoinedTableRows(ArrayList<String> tableTitles, ArrayList<String> joinConditions,
                                                           HashMap<String, String> selectedParameters, ArrayList<String> columnTitles,
                                                           String orderByParameter)
    {
        return getJoinedTableRows(tableTitles, joinConditions, selectedParameters, columnTitles,
                new ArrayList<>(Collections.singletonList(orderByParameter)), true);
    }
    public ArrayList<ArrayList<String>> getJoinedTableRows(ArrayList<String> tableTitles, ArrayList<String> joinConditions,
                                                           HashMap<String, String> selectedParameters, ArrayList<String> columnTitles,
                                                           ArrayList<String> orderByParameters)
    {
        return getJoinedTableRows(tableTitles, joinConditions, selectedParameters, columnTitles, orderByParameters, true);
    }
    public ArrayList<ArrayList<String>> getJoinedTableRows(ArrayList<String> tableTitles, ArrayList<String> joinConditions,
                                                           HashMap<String, String> selectedParameters, ArrayList<String> columnTitles,
                                                           ArrayList<String> orderByParameters, Boolean orOperator)
    {
        try
        {
            ArrayList<ArrayList<String>> retrievedRows = new ArrayList<>();
            try (Connection connection = DriverManager.getConnection(databaseUrl, user, password);)
            {
                StringBuilder sqlStatement = new StringBuilder("select ");
                if(columnTitles.isEmpty())
                    sqlStatement.append("*");
                else
                {
                    columnTitles.forEach(x -> sqlStatement.append(x).append(", "));
                    sqlStatement.setLength(sqlStatement.length() - 2);
                }
                if(tableTitles.size() >= 1)
                    sqlStatement.append(" from ").append(tableTitles.get(0));
                for(int counter = 0; counter < (tableTitles.size() - 1); counter++)
                {
                    sqlStatement.append(" join ").append(tableTitles.get(counter + 1)).append(" on ");
                    sqlStatement.append(joinConditions.get(counter * 2)).append(" = ").append(joinConditions.get((counter * 2) + 1));
                }
                try (PreparedStatement preparedStatement = addWhereAndOrderByClause(selectedParameters, orderByParameters, connection,
                        sqlStatement, new ArrayList<>(), orOperator))
                {
                    if(preparedStatement != null)
                    {
                        retrievedRows = retrieveResults(preparedStatement);
                        preparedStatement.close();
                    }
                    connection.close();
                }
            }
            return retrievedRows;
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, error);
            return new ArrayList<>();
        }
    }
    public ArrayList<ArrayList<String>> getTableRows(String tableName)
    {
        return getTableRows(tableName, new HashMap<>(), new ArrayList<>(), new ArrayList<>(), true);
    }
    public ArrayList<ArrayList<String>> getTableRows(String tableName, HashMap<String, String> selectedParameters)
    {
        return getTableRows(tableName, selectedParameters, new ArrayList<>(), new ArrayList<>(), true);
    }
    public ArrayList<ArrayList<String>> getTableRows(String tableName, HashMap<String, String> selectedParameters, ArrayList<String> columnTitles)
    {
        return getTableRows(tableName, selectedParameters, columnTitles, new ArrayList<>(), true);
    }
    public ArrayList<ArrayList<String>> getTableRows(String tableName, HashMap<String, String> selectedParameters, ArrayList<String> columnTitles,
                                                     String orderByColumn)
    {
        return getTableRows(tableName, selectedParameters, columnTitles, new ArrayList<>(Collections.singletonList(orderByColumn)), true);
    }
    public ArrayList<ArrayList<String>> getTableRows(String tableName, HashMap<String, String> selectedParameters, ArrayList<String> columnTitles,
                                                     ArrayList<String> orderByColumns)
    {
        return getTableRows(tableName, selectedParameters, columnTitles, orderByColumns, true);
    }
    public ArrayList<ArrayList<String>> getTableRows(String tableName, HashMap<String, String> selectedParameters, ArrayList<String> columnTitles,
                                                     ArrayList<String> orderByColumns, Boolean orOperator)
    {
        try
        {
            ArrayList<ArrayList<String>> retrievedRows = new ArrayList<>();
            try (Connection connection = DriverManager.getConnection(databaseUrl, user, password))
            {
                StringBuilder sqlStatement = new StringBuilder("select ");
                if(columnTitles.isEmpty())
                    sqlStatement.append("*");
                else
                {
                    columnTitles.forEach(x -> sqlStatement.append(x).append(", "));
                    sqlStatement.setLength(sqlStatement.length() - 2);
                }
                sqlStatement.append(" from ").append(tableName);
                try (PreparedStatement preparedStatement = addWhereAndOrderByClause(selectedParameters, orderByColumns, connection, sqlStatement,
                        new ArrayList<>(), orOperator))
                {
                    if(preparedStatement != null)
                    {
                        retrievedRows = retrieveResults(preparedStatement);
                        preparedStatement.close();
                    }
                    connection.close();
                }
            }
            return retrievedRows;
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, error);
            return new ArrayList<>();
        }
    }
    public int getMaxValueOfColumn(String tableName, String columnName, HashMap<String, String> selectedParameters)
    {
        return performArithmeticFunctionOnSelectedColumn(tableName, columnName, "max", selectedParameters);
    }
    public int getMaxValueOfColumn(String tableName, String columnName)
    {
        return performArithmeticFunctionOnSelectedColumn(tableName, columnName, "max", new HashMap<>());
    }
    public int getSumValueOfColumn(String tableName, String columnName, HashMap<String, String> selectedParameters)
    {
        return performArithmeticFunctionOnSelectedColumn(tableName, columnName, "sum", selectedParameters);
    }
    public int getSumValueOfColumn(String tableName, String columnName)
    {
        return performArithmeticFunctionOnSelectedColumn(tableName, columnName, "sum", new HashMap<>());
    }
    //NOT WORKING FOR DERBBY
    public ArrayList<String> getColumnTitles(String tableName)
    {
        try
        {
            ArrayList<String> columnTitles = new ArrayList<>();
            try (Connection connection = DriverManager.getConnection(databaseUrl, user, password))
            {
                StringBuilder sqlStatement = new StringBuilder();
                if(databaseUrl.contains("jdbc:mysql:"))
                {
                    sqlStatement.append("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE '");
                    sqlStatement.append(tableName).append("' AND TABLE_SCHEMA LIKE '").append(databaseTitle).append("'");
                }
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement.toString()))
                {
                    ArrayList<ArrayList<String>> retrievedColumnTitles = retrieveResults(preparedStatement);
                    retrievedColumnTitles.forEach(x -> columnTitles.add(x.get(0)));
                }
            }
            return columnTitles;
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, error);
            return new ArrayList<>();
        }
    }
    public void insertTableRow(String tableName, ArrayList<String> tableRowValues)
    {
        try (Connection connection = DriverManager.getConnection(databaseUrl, user, password))
        {
            StringBuilder sqlStatement = new StringBuilder("insert into " + tableName + " values (");
            tableRowValues.forEach(x -> sqlStatement.append("?, "));
            sqlStatement.setLength(sqlStatement.length() - 2);
            sqlStatement.append(")");
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement.toString()))
            {
                for(int counter = 1; counter <= tableRowValues.size(); counter++)
                {
                    if(isWholeNumber(tableRowValues.get(counter - 1)))
                        preparedStatement.setInt(counter, Integer.parseInt(tableRowValues.get(counter - 1)));
                    else if(isDecimal(tableRowValues.get(counter - 1)))
                        preparedStatement.setDouble(counter, Double.parseDouble(tableRowValues.get(counter - 1)));
                    else
                        preparedStatement.setString(counter, tableRowValues.get(counter - 1));
                }
                preparedStatement.executeUpdate();
            }
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    public void removeTableRow(String tableName)
    {
        removeTableRow(tableName, new HashMap<>(), true);
    }
    public void removeTableRow(String tableName, HashMap<String, String> selectedParameters)
    {
        removeTableRow(tableName, selectedParameters, true);
    }
    public void removeTableRow(String tableName, HashMap<String, String> selectedParameters, Boolean orOperator)
    {
        try (Connection connection = DriverManager.getConnection(databaseUrl, user, password))
        {
            StringBuilder sqlStatement = new StringBuilder("delete from " + tableName);
            try (PreparedStatement preparedStatement = addWhereAndOrderByClause(selectedParameters, new ArrayList<>(), connection,
                    sqlStatement, new ArrayList<>(), orOperator))
            {
                preparedStatement.executeUpdate();
            }
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    public void updateTableRow(String tableName, HashMap<String, String> updatedParameters)
    {
        updateTableRow(tableName, updatedParameters, new HashMap<>());
    }
    public void updateTableRow(String tableName, HashMap<String, String> updatedParameters, HashMap<String, String> selectedParameters)
    {
        updateTableRow(tableName, updatedParameters, selectedParameters, true);
    }
    public void updateTableRow(String tableName, HashMap<String, String> updatedParameters, HashMap<String, String> selectedParameters,
                               Boolean orOperator)
    {
        try (Connection connection = DriverManager.getConnection(databaseUrl, user, password))
        {
            StringBuilder sqlStatement = new StringBuilder("update " + tableName + " set ");
            ArrayList<String> updatedValues = new ArrayList<>();
            updatedParameters.forEach((x, y) ->
            {
                /*if(isDecimal(y) || isWholeNumber(y) || isDate(y))
                    sqlStatement.append(x).append(" = ").append(y).append(", ");
                else
                    sqlStatement.append(x).append(" = ").append(y).append(", ");*/
                sqlStatement.append(x).append(" = ?, ");
                updatedValues.add(y);

            });
            sqlStatement.setLength(sqlStatement.length() - 2);
            try (PreparedStatement preparedStatement = addWhereAndOrderByClause(selectedParameters, new ArrayList<>(), connection, sqlStatement,
                    updatedValues, orOperator))
            {
                if(preparedStatement != null)
                {
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }
                connection.close();
            }
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    private int performArithmeticFunctionOnSelectedColumn(String tableName, String columnName, String arithmeticOperation,
                                                             HashMap<String, String> selectedParameters)
    {
        try
        {
            int maxValue = 0;
            try (Connection connection = DriverManager.getConnection(databaseUrl, user, password);)
            {
                StringBuilder sqlStatement = new StringBuilder("select ");
                sqlStatement.append(arithmeticOperation).append("(").append(columnName).append(") from ").append(tableName);
                PreparedStatement preparedStatement = addWhereAndOrderByClause(selectedParameters, new ArrayList<>(), connection,
                        sqlStatement, new ArrayList<>(), true);
                ArrayList<ArrayList<String>> possibleMaximumValue = retrieveResults(preparedStatement);
                if(possibleMaximumValue.size() > 0)
                {
                    String currentValue = possibleMaximumValue.get(0).get(0);
                    if(isWholeNumber(currentValue))
                        maxValue = Integer.parseInt(currentValue);
                    //else if(isDecimal(currentValue))
                        //maxValue = Double.parseDouble(currentValue);
                }
            }
            return maxValue;
        }
        catch(NumberFormatException | SQLException error)
        {
            JOptionPane.showMessageDialog(null, error);
            return 0;
        }
    }
    private PreparedStatement addWhereAndOrderByClause(HashMap<String, String> selectedParameters, ArrayList<String> orderByClause,
                                                       Connection connection, StringBuilder sqlStatement, ArrayList<String> updatedValues,
                                                       Boolean orOperator)
    {
        if(selectedParameters.size() > 0)
        {
            sqlStatement.append(" where ");
            selectedParameters.forEach((x, y) ->
            {
                String operator = "or";
                if(!orOperator)
                    operator = "and";
                if(isNumeric(y))
                    sqlStatement.append(x).append(" = ? ").append(operator).append(" ");
                else
                    sqlStatement.append(x).append(" like ? ").append(operator).append(" ");
            });
            sqlStatement.setLength(sqlStatement.length() - 4);
        }
        for(int counter = 0; counter < orderByClause.size(); counter++)
            if(orderByClause.get(counter).trim().length() == 0)
                orderByClause.remove(counter);
        if(orderByClause.size() > 0)
        {
            sqlStatement.append(" order by ");
            orderByClause.forEach(x -> sqlStatement.append(x).append(" asc, "));
            sqlStatement.setLength(sqlStatement.length() - 2);
        }
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement.toString());
            for(int counter = 1, index = 0; index < updatedValues.size(); index++, counter++)
                setValue(preparedStatement, updatedValues.get(index), counter, false);
            Object[] selectedValues = selectedParameters.values().toArray();
            for(int counter = updatedValues.size() + 1, index = 0; index < selectedParameters.size(); counter++, index++)
            {
                setValue(preparedStatement, selectedValues[index].toString(), counter, true);
            }
            return preparedStatement;
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, error);
            return null;
        }
    }
    private ArrayList<ArrayList<String>> retrieveResults(PreparedStatement currentStatement)
    {
        ArrayList<ArrayList<String>> retrievedRows = new ArrayList<>();
        try (ResultSet selectedRows = currentStatement.executeQuery())
        {
            Integer numberOfColumns = selectedRows.getMetaData().getColumnCount();
            while (selectedRows.next())
            {
                ArrayList<String> aRetrievedRow = new ArrayList<>();
                for (Integer k = 1; k <= numberOfColumns; k++)
                {
                    if(selectedRows.getString(k) != null)
                        aRetrievedRow.add(selectedRows.getString(k));
                }
                if(aRetrievedRow.size() > 0)
                    retrievedRows.add(aRetrievedRow);
            }
            return retrievedRows;
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, error);
            return new ArrayList<>();
        }
    }
    private void setValue(PreparedStatement preparedStatement, String currentValue, int position, boolean includePercentageSymbol)
    {
        try
        {
            if(isWholeNumber(currentValue))
                preparedStatement.setInt(position, Integer.parseInt(currentValue));
            else if(isDecimal(currentValue))
                preparedStatement.setDouble(position, Double.parseDouble(currentValue));
            else
            {
                if(includePercentageSymbol && currentValue.length() > 0)
                    preparedStatement.setString(position, "%" + currentValue + "%");
                else
                    preparedStatement.setString(position, currentValue);
            }
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    private Boolean isNumeric(String text)
    {
        if(isDecimal(text) || isDate(text))
            return true;
        return false;
    }
    private Boolean isDecimal(String text)
    {
        try
        {
            Double.parseDouble(text);
            return true;
        }
        catch(NumberFormatException error)
        {
            return false;
        }
    }
    private Boolean isWholeNumber(String text)
    {
        try
        {
            Integer.parseInt(text);
            return true;
        }
        catch(NumberFormatException error)
        {
            return false;
        }
    }
    private Boolean isDate(String text)
    {
        try
        {
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(text);
            return true;
        }
        catch(ParseException error)
        {
            return false;
        }
    }
}
