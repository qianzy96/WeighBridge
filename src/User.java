import java.util.ArrayList;
import java.util.Arrays;

public class User
{
    private int code;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    public User(int code, String username, String password, String firstName, String lastName, String emailAddress, String phoneNumber)
    {
        this.code = code;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
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
    public String getEmailAddress()
    {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    public String toString()
    {
        return "Code: " + code + " Username: " + username + " Password: " + password + " FirstName: " + firstName + " LastName: " + lastName + " Email: " + emailAddress
        + " Phone Number: " + phoneNumber;
    }
    public ArrayList<String> toList()
    {
        return new ArrayList<>(Arrays.asList(code + "", username, password, firstName, lastName, emailAddress, phoneNumber));
    }
}