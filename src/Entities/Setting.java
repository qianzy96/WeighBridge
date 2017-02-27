package Entities;

import java.util.ArrayList;
import java.util.Arrays;

public class Setting
{
    private int code;
    private String title;
    private String value;
    public Setting(int code, String title, String value)
    {
        this.code = code;
        this.title = title;
        this.value = value;
    }
    public Setting(String title, String value)
    {
        this.title = title;
        this.value = value;
    }
    public int getCode()
    {
        return code;
    }
    public void setCode(int code)
    {
        this.code = code;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getValue()
    {
        return value;
    }
    public void setValue(String value)
    {
        this.value = value;
    }
    public String toString()
    {
        return title + " " + value;
    }
    public ArrayList<String> toList()
    {
        return new ArrayList<>(Arrays.asList(code + "", title, value));
    }
}
