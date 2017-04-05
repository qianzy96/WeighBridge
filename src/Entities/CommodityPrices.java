package Entities;
import java.util.*;
public class CommodityPrices
{
    private String commodityTitle;
    private String date;
    private ArrayList<String> headings;
    private LinkedHashMap<String, ArrayList<String>> prices;
    public CommodityPrices(String commodityTitle, String date)
    {
        this.commodityTitle = commodityTitle;
        this.date = date;
        this.headings = new ArrayList<>();
        this.prices = new LinkedHashMap<>();
    }
    public void addHeading(String aHeading)
    {
        headings.add(aHeading);
    }
    public void addPrices(String month, ArrayList<String> specifiedPrices)
    {
        prices.put(month, specifiedPrices);
    }
    public String toString()
    {
        StringBuilder output = new StringBuilder();
        output.append("Title: " + commodityTitle + "\n");
        output.append("Date: " + date + "\n");
        headings.forEach(x -> output.append("A Heading: " + x + "\n"));
        prices.forEach((y, z) -> {output.append("A Price : " + y); z.forEach(a -> output.append("A Price: " + a));});
        return output.toString();
    }
    public String getCommodityTitle()
    {
        return commodityTitle;
    }
    public void setCommodityTitle(String commodityTitle)
    {
        this.commodityTitle = commodityTitle;
    }
    public String getDate()
    {
        return date;
    }
    public void setDate(String date)
    {
        this.date = date;
    }
    public ArrayList<String> getHeadings()
    {
        return headings;
    }
    public void setHeadings(ArrayList<String> headings)
    {
        this.headings = headings;
    }
    public LinkedHashMap<String, ArrayList<String>> getPrices()
    {
        return prices;
    }
    public void setPrices(LinkedHashMap<String, ArrayList<String>> prices)
    {
        this.prices = prices;
    }
    public ArrayList<String> getMonths()
    {
        return prices.get("Month");
    }
    public ArrayList<String> getLastPrices()
    {
        return prices.get("Last");
    }
}
