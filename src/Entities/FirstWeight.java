package Entities;

import Entities.Commodity;
import Entities.Consignee;
import Entities.DocketType;
import Entities.Driver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
public class FirstWeight
{
    private int code;
    private Driver driver;
    private Commodity commodity;
    private double weight;
    private Date date;
    private DocketType docketType;
    private Consignee consignee;
    public FirstWeight(int code, Driver driver, Commodity commodity, double weight, Date date, DocketType docketType, Consignee customer)
    {
        this.code = code;
        this.driver = driver;
        this.commodity = commodity;
        this.weight = weight;
        this.date = date;
        this.docketType = docketType;
        this.consignee = customer;
    }
    public FirstWeight(Driver driver, Commodity commodity, double weight, Date date, DocketType docketType, Consignee customer)
    {
        this.driver = driver;
        this.commodity = commodity;
        this.weight = weight;
        this.date = date;
        this.docketType = docketType;
        this.consignee = customer;
    }
    public int getCode()
    {
        return code;
    }
    public void setCode(int code)
    {
        this.code = code;
    }
    public Driver getDriver()
    {
        return driver;
    }
    public void setDriver(Driver driver)
    {
        this.driver = driver;
    }
    public Commodity getCommodity()
    {
        return commodity;
    }
    public void setCommodity(Commodity commodity)
    {
        this.commodity = commodity;
    }
    public double getWeight()
    {
        return weight;
    }
    public void setWeight(double weight)
    {
        this.weight = weight;
    }
    public Date getDate()
    {
        return date;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }
    public DocketType getDocketType()
    {
        return docketType;
    }
    public void setDocketType(DocketType docketType)
    {
        this.docketType = docketType;
    }
    public Consignee getConsignee()
    {
        return consignee;
    }
    public void setConsignee(Consignee consignee)
    {
        this.consignee = consignee;
    }
    public String toString()
    {
        return driver.toString() + " " + commodity.getTitle() + " " + weight + " " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date) + " " +
        docketType.getDocketType() + " " + consignee.toString();
    }
    public ArrayList<String> toList()
    {
        return new ArrayList<>(Arrays.asList(code + "", driver.getCode() + "", commodity.getCode() + "", weight + "",
        new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date), docketType.getCode() + "", consignee.getCode() + ""));
    }
}