import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class SecondWeight
{
    private int code;
    private double weight;
    private Date date;
    private FirstWeight firstWeight;
    public SecondWeight(int code, double weight, Date date, FirstWeight firstWeight)
    {
        this.code = code;
        this.weight = weight;
        this.date = date;
        this.firstWeight = firstWeight;
    }
    public SecondWeight(double weight, Date date, FirstWeight firstWeight)
    {
        this.weight = weight;
        this.date = date;
        this.firstWeight = firstWeight;
    }
    public int getCode()
    {
        return code;
    }
    public void setCode(int code)
    {
        this.code = code;
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
    public FirstWeight getFirstWeight()
    {
        return firstWeight;
    }
    public void setFirstWeight(FirstWeight firstWeight)
    {
        this.firstWeight = firstWeight;
    }
    public String toString()
    {
        return weight + " " + date.toString() + " " + firstWeight.toString();
    }
    public ArrayList<String> toList()
    {
        return new ArrayList<>(Arrays.asList(code + "", weight + "", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date), firstWeight.getCode() + ""));
    }
}