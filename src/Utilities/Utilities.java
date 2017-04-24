package Utilities;

import com.teamdev.jxbrowser.chromium.JSONString;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities
{
    public static boolean isWholeNumber(String text)
    {
        try
        {
            Integer wholeNumber = Integer.parseInt(text);
            return true;
        }
        catch(Exception error)
        {
            return false;
        }
    }
    public static boolean isDecimal(String text)
    {
        try
        {
            Double decimal = Double.parseDouble(text);
            return true;
        }
        catch(Exception error)
        {
            return false;
        }
    }
    public static boolean isDate(String text, String pattern)
    {
        try
        {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
            Date date = dateFormatter.parse(text);
            return true;
        }
        catch(Exception error)
        {
            return false;
        }
    }
    public static String formatDate(String text, String patternOfText)
    {
        try
        {
            Date aDate = new SimpleDateFormat(patternOfText).parse(text);
            return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(aDate);
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
            return "";
        }
    }
    public static Date createDate(String text, String patternOfText)
    {
        try
        {
            Date aDate = new SimpleDateFormat(patternOfText).parse(text);
            return aDate;
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
            return new Date();
        }
    }
    public static ArrayList<String> extractTextBetweenTags(String text, String openingPattern, String closingPattern)
    {
        Pattern aPattern = Pattern.compile(openingPattern + ".*?" + closingPattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher aMatcher = aPattern.matcher(text);
        ArrayList<String> validMatches = new ArrayList<>();
        while(aMatcher.find())
        {
            String textualContent = aMatcher.group();
            textualContent = textualContent.substring(textualContent.indexOf(openingPattern) + openingPattern.length());
            if(openingPattern.contains(".*?") && textualContent.contains(">"))
                textualContent = textualContent.substring(textualContent.indexOf(">") + 1);
            textualContent = textualContent.substring(0, textualContent.indexOf(closingPattern));
            validMatches.add(textualContent.trim());
        }
        return validMatches;
    }
    public static ArrayList<String> extractAttributeOfTags(String text, String pattern, String attribute, HashMap<String, String> selectedAttributes)
    {
        Pattern aPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher aMatcher = aPattern.matcher(text);
        ArrayList<String> attributes = new ArrayList<>();
        while(aMatcher.find())
        {
            String currentTag = aMatcher.group();
            Boolean invalidEntryLocated = false;
            for(Map.Entry<String, String> aSelectedAttribute : selectedAttributes.entrySet())
                if(!currentTag.contains(aSelectedAttribute.getKey()) || !currentTag.contains(aSelectedAttribute.getValue()))
                    invalidEntryLocated = true;
            if(currentTag.contains(attribute) && !invalidEntryLocated)
            {
                currentTag = currentTag.substring(currentTag.indexOf(attribute) + attribute.length() + 2);
                if(currentTag.indexOf("\"") > -1)
                    currentTag = currentTag.substring(0, currentTag.indexOf("\""));
                else if(currentTag.indexOf("\'") > -1)
                    currentTag = currentTag.substring(0, currentTag.indexOf("\'"));
                attributes.add(currentTag);
            }
        }
        return attributes;
    }
    public static String convertCharArrayToString(char[] inputCharacters)
    {
        String output = "";
        for(char anInputCharacter : inputCharacters)
            output += anInputCharacter;
        return output;
    }
    public static JSONString convertHashMapToJSON(HashMap<String, String> aHashMap)
    {
        StringBuilder formattedOutput = new StringBuilder("{");
        for(Map.Entry<String, String> anEntry : aHashMap.entrySet())
        {
            formattedOutput.append("\"" + anEntry.getKey() + "\"");
            formattedOutput.append(" : ");
            formattedOutput.append("\"" + anEntry.getValue().replace("\"", "\\\"") + "\", ");
        }
        return new JSONString(formattedOutput.substring(0, formattedOutput.length() - 2) + "}");
    }
    public static JSONString convertListToJavaScriptArray(List<String> aList)
    {
        StringBuilder formattedOutput = new StringBuilder("[");
        aList.forEach(x -> formattedOutput.append("\'" + x + "\', "));
        return new JSONString(formattedOutput.substring(0, formattedOutput.length() - 2) + "]");
    }
    public static JSONString createChart(HashMap<String, String> aHashMap, List<String> labels, LinkedHashMap<String, List<Double>> data)
    {
        StringBuilder formattedOutput = new StringBuilder("{");
        for(Map.Entry<String, String> anEntry : aHashMap.entrySet())
        {
            formattedOutput.append("\"" + anEntry.getKey() + "\"");
            formattedOutput.append(" : ");
            formattedOutput.append("\"" + anEntry.getValue().replace("\"", "\\\"") + "\", ");
        }
        formattedOutput.append("\"chart\"");
        formattedOutput.append(" : {");
        formattedOutput.append("\"labels\" : " + convertListToJSONArray(labels).getValue() + ", ");
        formattedOutput.append("\"data\" : [");
        for(Map.Entry<String, List<Double>> aDataItem : data.entrySet())
        {
            formattedOutput.append("{");
            formattedOutput.append("\"label\": \"" + aDataItem.getKey() + "\",");
            formattedOutput.append("\"data\": [");
            for(Double aDoubleValue : aDataItem.getValue())
                formattedOutput.append(aDoubleValue + ", ");
            if(aDataItem.getValue().size() > 0)
                formattedOutput = new StringBuilder(formattedOutput.substring(0, formattedOutput.length() - 2));
            formattedOutput.append("]");
            formattedOutput.append("},");
        }
        return new JSONString(formattedOutput.toString().substring(0, formattedOutput.length() - 1) + "]}}");
    }
    public static JSONString convertListToJSONArray(List<String> aList)
    {
        StringBuilder formattedOutput = new StringBuilder("[");
        aList.forEach(x -> formattedOutput.append("\"" + x + "\", "));
        return new JSONString(formattedOutput.substring(0, formattedOutput.length() - 2) + "]");
    }
}
