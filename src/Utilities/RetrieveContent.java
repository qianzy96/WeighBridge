package Utilities;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class RetrieveContent
{
    private String url;
    private StringBuilder webPageContent;
    public RetrieveContent(String url)
    {
        this.url = url;
        this.webPageContent = new StringBuilder();
        getContent();
    }
    private void getContent()
    {
        try
        {
            String line = "";
            InputStream aStream = new URL(url).openStream();
            InputStreamReader aStreamReader = new InputStreamReader(aStream);
            BufferedReader aBufferedReader = new BufferedReader(aStreamReader);
            while ((line = aBufferedReader.readLine()) != null)
                webPageContent.append(line);
            aBufferedReader.close();
            aStreamReader.close();
            aStream.close();
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    public String getText()
    {
        return webPageContent.toString();
    }
}
