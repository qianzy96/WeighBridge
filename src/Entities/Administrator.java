package Entities;

import java.util.ArrayList;
import java.util.Arrays;
public class Administrator
{
    private int code;
    private String username;
    private String password;
    public Administrator(int code, String username, String password)
    {
        this.code = code;
        this.username = username;
        this.password = password;
    }
    public Administrator(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    public int getCode()
    {
        return code;
    }
    public void setCode(int code)
    {
        this.code = code;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String toString()
    {
        return username + " " + password;
    }
    public ArrayList<String> toList()
    {
        return new ArrayList<String>(Arrays.asList(code + "", username, password));
    }
}