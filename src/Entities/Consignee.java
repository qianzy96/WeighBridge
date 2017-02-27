package Entities;

import java.util.ArrayList;
import java.util.Arrays;

public class Consignee
{
    private int code;
    private String firstName;
    private String lastName;
    public Consignee(int code, String firstName, String lastName)
    {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Consignee(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public int getCode()
    {
        return code;
    }
    public void setCode(int code)
    {
        this.code = code;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public String toString()
    {
        return firstName + " " + lastName;
    }
    public ArrayList<String> toList()
    {
        return new ArrayList<>(Arrays.asList(code + "", firstName, lastName));
    }
}
