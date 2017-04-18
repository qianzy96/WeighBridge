package WeighBridge;

import java.util.ArrayList;
import java.util.Arrays;

public class DocketType
{
    private int code;
    private String docketType;
    public DocketType(int code, String docketType)
    {
        this.code = code;
        this.docketType = docketType;
    }
    public DocketType(String docketType)
    {
        this.docketType = docketType;
    }
    public int getCode()
    {
        return code;
    }
    public void setCode(int code)
    {
        this.code = code;
    }
    public String getDocketType()
    {
        return docketType;
    }
    public void setDocketType(String docketType)
    {
        this.docketType = docketType;
    }
    public String toString()
    {
        return docketType;
    }
    public ArrayList<String> toList()
    {
        return new ArrayList<>(Arrays.asList(code + "", docketType));
    }
}