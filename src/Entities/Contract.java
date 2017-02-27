package Entities;

import Entities.Commodity;
import Entities.Consignee;

import java.util.ArrayList;
import java.util.Arrays;

public class Contract
{
    private int code;
    private Commodity commodity;
    private double total;
    private double price;
    private DocketType docketType;
    private Consignee consignee;
    public Contract(int code, Commodity commodity, double total, double price, DocketType docketType, Consignee consignee)
    {
        this.code = code;
        this.commodity = commodity;
        this.total = total;
        this.price = price;
        this.docketType = docketType;
        this.consignee = consignee;
    }
    public Contract(Commodity commodity, double total, double price, DocketType docketType, Consignee consignee)
    {
        this.commodity = commodity;
        this.total = total;
        this.price = price;
        this.docketType = docketType;
        this.consignee = consignee;
    }
    public int getCode()
    {
        return code;
    }
    public void setCode(int code)
    {
        this.code = code;
    }
    public Commodity getCommodity()
    {
        return commodity;
    }
    public void setCommodity(Commodity commodity)
    {
        this.commodity = commodity;
    }
    public double getTotal()
    {
        return total;
    }
    public void setTotal(double total)
    {
        this.total = total;
    }
    public double getPrice()
    {
        return price;
    }
    public void setPrice(double price)
    {
        this.price = price;
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
        return commodity.getTitle() + " " + total + " " + price + " " + docketType.getDocketType() + " " + consignee.getFirstName() + " " + consignee.getLastName();
    }
    public ArrayList<String> toList()
    {
        return new ArrayList<>(Arrays.asList(code + "", commodity.getCode() + "", total + "", price + "", docketType.getCode() + "", consignee.getCode() + ""));
    }
}
