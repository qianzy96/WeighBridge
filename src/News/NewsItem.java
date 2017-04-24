package News;

import java.util.ArrayList;
import java.util.Arrays;

public class NewsItem
{
    private int code;
    private String title;
    private String publishedDate;
    private String updatedDate;
    private String summary;
    private String link;
    public NewsItem(int code, String title, String publishedDate, String updatedDate, String summary, String link)
    {
        this.code = code;
        this.title = title;
        this.publishedDate = publishedDate;
        this.updatedDate = updatedDate;
        this.summary = summary;
        this.link = link;
    }
    public NewsItem(String title, String publishedDate, String updatedDate, String summary, String link)
    {
        this.title = title;
        this.publishedDate = publishedDate;
        this.updatedDate = updatedDate;
        this.summary = summary;
        this.link = link;
    }
    public String toString()
    {
        return code + " " + title + " " + publishedDate + " " + updatedDate + " " + summary + " " + link;
    }
    public ArrayList<String> toList()
    {
        return new ArrayList<>(Arrays.asList(code + "", title, publishedDate, updatedDate, summary, link));
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
    public String getPublishedDate()
    {
        return publishedDate;
    }
    public void setPublishedDate(String publishedDate)
    {
        this.publishedDate = publishedDate;
    }
    public String getUpdatedDate()
    {
        return updatedDate;
    }
    public void setUpdatedDate(String updatedDate)
    {
        this.updatedDate = updatedDate;
    }
    public String getSummary()
    {
        return summary;
    }
    public void setSummary(String summary)
    {
        this.summary = summary;
    }
    public String getLink()
    {
        return link;
    }
    public void setLink(String link)
    {
        this.link = link;
    }
}