package WeighBridge;

import java.util.ArrayList;
import java.util.Arrays;
public class Commodity
{
    private int code;
    private String title;
    public Commodity(int code, String title)
    {
        this.code = code;
        this.title = title;
    }
    public Commodity(String title)
    {
        this.title = title;
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
    public String toString()
    {
        return title;
    }
    public ArrayList<String> toList()
    {
        return new ArrayList<>(Arrays.asList(code + "", title));
    }
}
