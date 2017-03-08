package Entities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
public class BatchNumber
{
    private int code;
    private String batchNumber;
    private SecondWeight secondWeight;
    public BatchNumber(int code, String batchNumber, SecondWeight secondWeight)
    {
        this.code = code;
        this.batchNumber = batchNumber;
        this.secondWeight = secondWeight;
    }
    public BatchNumber(String batchNumber, SecondWeight secondWeight)
    {
        this.batchNumber = batchNumber;
        this.secondWeight = secondWeight;
    }
    public BatchNumber(SecondWeight secondWeight)
    {
        this.secondWeight = secondWeight;
    }
    public int getCode()
    {
        return code;
    }
    public void setCode(int code)
    {
        this.code = code;
    }
    public String getBatchNumber()
    {
        return batchNumber;
    }
    public void setBatchNumber(String batchNumber)
    {
        this.batchNumber = batchNumber;
    }
    public SecondWeight getSecondWeight()
    {
        return secondWeight;
    }
    public void setSecondWeight(SecondWeight secondWeight)
    {
        this.secondWeight = secondWeight;
    }
    public String toString()
    {
        return "Code: " + code + " Batch Number: " + batchNumber;
    }
    public ArrayList<String> toList()
    {
        return new ArrayList<>(Arrays.asList(code + "", secondWeight.getCode() + "", batchNumber));
    }
}