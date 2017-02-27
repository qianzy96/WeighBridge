package Entities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
public class BatchNumber
{
    private String commodityTitle;
    private Double netWeight;
    private Date date;
    private String batchNumber;
    private String code;
    public BatchNumber(String code, String commodityTitle, Double netWeight, Date date, String batchNumber)
    {
        this.code = code;
        this.commodityTitle = commodityTitle;
        this.netWeight = netWeight;
        this.date = date;
        this.batchNumber = batchNumber;
    }
    public BatchNumber(String commodityTitle, Double netWeight, Date date, String batchNumber)
    {
        this.commodityTitle = commodityTitle;
        this.netWeight = netWeight;
        this.date = date;
        this.batchNumber = batchNumber;
    }
    public BatchNumber(String commodityTitle, Double netWeight, Date date)
    {
        this.commodityTitle = commodityTitle;
        this.netWeight = netWeight;
        this.date = date;
    }
    public String getCode()
    {
        return code;
    }
    public void setCode(String code)
    {
        this.code = code;
    }
    public String getCommodityTitle()
    {
        return commodityTitle;
    }
    public void setCommodityTitle(String commodityTitle)
    {
        this.commodityTitle = commodityTitle;
    }
    public Double getNetWeight()
    {
        return netWeight;
    }
    public void setNetWeight(Double netWeight)
    {
        this.netWeight = netWeight;
    }
    public Date getDate()
    {
        return date;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }
    public String getBatchNumber()
    {
        return batchNumber;
    }
    public void setBatchNumber(String batchNumber)
    {
        this.batchNumber = batchNumber;
    }
    public String toString()
    {
        return "Commodity Title: " + commodityTitle + " Date: " + date.toString() + " Net Weight: " + netWeight +
        " Batch Number: " + batchNumber;
    }
    /*public ArrayList<String> toList()
    {
        return new ArrayList<>(Arrays.asList(code, ))
    }*/
}