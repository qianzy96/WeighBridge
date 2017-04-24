package Models;
import Utilities.*;
import javax.swing.*;
import java.util.*;
import News.*;
import Database.*;
public class News
{
    private Boolean fileGenerated;
    public News()
    {
        fileGenerated = false;
    }
    public void retrieveNews()
    {
        try
        {
            String textualContent = new RetrieveContent("http://nogger-noggersblog.blogspot.com/feeds/posts/default").getText();
            ArrayList<String> titles = Utilities.extractTextBetweenTags(textualContent, "<title type='text'>", "</title>");
            ArrayList<String> publishedDates = Utilities.extractTextBetweenTags(textualContent, "<published>", "</published>");
            ArrayList<String> updatedDates = Utilities.extractTextBetweenTags(textualContent, "<updated>", "</updated>");
            ArrayList<String> summaries = Utilities.extractTextBetweenTags(textualContent, "<summary type=\"text\">", "</summary>");
            HashMap<String, String> selectedParameters = new HashMap<>();
            selectedParameters.put("rel", "'alternate'");
            selectedParameters.put("type", "'text/html'");
            ArrayList<String> links =  Utilities.extractAttributeOfTags(textualContent,"<link(.+?)/>", "href", selectedParameters);
            titles.remove(0);
            links.remove(0);
            ArrayList<NewsItem> newsItems = new ArrayList<>();
            for(int counter = 0; counter < summaries.size(); counter++)
                newsItems.add(new NewsItem(titles.get(counter), publishedDates.get(counter), updatedDates.get(counter), summaries.get(counter), links.get(counter)));
            newsItems.forEach(x -> insertNewNewsItem(x));
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    public void insertNewNewsItem(NewsItem aNewsItem)
    {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("link", aNewsItem.getLink());
        Database database = new Database();
        ArrayList<ArrayList<String>> existingNewsItem = database.getTableRows("newsitem", parameters);
        if(existingNewsItem.isEmpty())
        {
            int maximumNewsItem = database.getMaxValueOfColumn("newsitem", "code") + 1;
            aNewsItem.setCode(maximumNewsItem);
            database.insertTableRow("newsitem", aNewsItem.toList());
        }
        else
            aNewsItem.setCode(Integer.parseInt(existingNewsItem.get(0).get(0)));
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", aNewsItem.getCode() + "");
        ArrayList<ArrayList<String>> existingNewsArticle = database.getTableRows("newsarticle", selectedParameters);
        if(existingNewsArticle.isEmpty())
            retrieveDetailedInformationForArticle(aNewsItem.getLink(), aNewsItem.getCode() + "");
    }
    public void retrieveDetailedInformationForArticle(String link, String uniqueIdentifier)
    {
        String webPageContent = new RetrieveContent(link).getText();
        ArrayList<String> formattedTags = Utilities.extractTextBetweenTags(webPageContent, "<div class='post'(.+?)>", "</div>");
        if(formattedTags.size() > 0)
        {
            String formattedContent = formattedTags.get(0);
            formattedContent = formattedContent.replaceAll("<[^>]*>", "");
            if(formattedContent.indexOf(">") > 0)
                formattedContent = formattedContent.substring(formattedContent.indexOf(">") + 1);
            Database database = new Database();
            database.insertTableRow("newsarticle", new ArrayList<>(Arrays.asList(uniqueIdentifier, formattedContent)));
        }
    }
    public ArrayList<NewsItem> retrieveNewsItems()
    {
        ArrayList<NewsItem> newsItems = new ArrayList<>();
        Database database = new Database();
        ArrayList<ArrayList<String>> tableContents = database.getTableRows("newsitem");
        for(ArrayList<String> aTableRow : tableContents)
            newsItems.add(new NewsItem(Integer.parseInt(aTableRow.get(0)), aTableRow.get(1), aTableRow.get(2), aTableRow.get(3), aTableRow.get(4), aTableRow.get(5)));
        return newsItems;
    }
    public Boolean isBookmarked(String articleIdentifier)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", articleIdentifier);
        Database database = new Database();
        ArrayList<ArrayList<String>> tableContents = database.getTableRows("newsitembookmarked", selectedParameters);
        if(tableContents.isEmpty())
            return false;
        else
            return true;
    }
    public Boolean toggleBookmark(String articleIdentifier)
    {
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", articleIdentifier);
        Database database = new Database();
        ArrayList<ArrayList<String>> tableContents = database.getTableRows("newsitembookmarked", selectedParameters);
        if(tableContents.isEmpty())
        {
            database.insertTableRow("newsitembookmarked", new ArrayList<>(Collections.singletonList(articleIdentifier)));
            return true;
        }
        else
        {
            database.removeTableRow("newsitembookmarked", selectedParameters);
            return false;
        }
    }
    public ArrayList<ArrayList<String>> retrieveBookmarkedNewsItemsAsStrings()
    {
        ArrayList<ArrayList<String>> bookmarkedNewsItems = retrieveNewsItemsAsStrings();
        ArrayList<ArrayList<String>> formattedBookmarkedNewsItems = new ArrayList<>();
        for(ArrayList<String> aBookmarkedNewsItem : bookmarkedNewsItems)
        {
            HashMap<String, String> selectedParameters = new HashMap<>();
            selectedParameters.put("code", aBookmarkedNewsItem.get(0));
            System.out.println("CODE: " + aBookmarkedNewsItem.get(0));
            Database database = new Database();
            ArrayList<ArrayList<String>> newsItemBookmarked = database.getTableRows("newsitembookmarked", selectedParameters);
            for(ArrayList<String> aNewsItemBookmark: newsItemBookmarked)
                aNewsItemBookmark.forEach(y -> System.out.println("Y:" + y));
            if(!newsItemBookmarked.isEmpty())
                formattedBookmarkedNewsItems.add(aBookmarkedNewsItem);
        }
        return formattedBookmarkedNewsItems;
    }
    public ArrayList<ArrayList<String>> retrieveNewsItemsAsStrings()
    {
        Database database = new Database();
        return database.getTableRows("newsitem");
    }
    public ArrayList<String> retrieveNewsItemsHeadings()
    {
        return new ArrayList<>(Arrays.asList("Code", "Title", "Published Date", "Updated Date", "Summary", "Link"));
    }
    public NewsItem getNewsItem(String uniqueIdentifier)
    {
        Database database = new Database();
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", uniqueIdentifier);
        ArrayList<ArrayList<String>> selectedRow = database.getTableRows("newsitem", selectedParameters);
        if(selectedRow.size() > 0)
            return new NewsItem(Integer.parseInt(selectedRow.get(0).get(0)), selectedRow.get(0).get(1), selectedRow.get(0).get(2), selectedRow.get(0).get(3),
            selectedRow.get(0).get(4), selectedRow.get(0).get(5));
        return null;
    }
    public NewsArticle getNewsArticle(String articleIdentifier)
    {
        Database database = new Database();
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", articleIdentifier);
        ArrayList<ArrayList<String>> selectedRow = database.getTableRows("newsarticle", selectedParameters);
        if(selectedRow.size() > 0)
            return new NewsArticle(Integer.parseInt(selectedRow.get(0).get(0)), selectedRow.get(0).get(1));
        return null;
    }
    public void deleteSelectedArticle(String articleIdentifier)
    {
        Database database = new Database();
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("code", articleIdentifier);
        database.removeTableRow("newsitem", selectedParameters);
    }
    public void generatePDFFile()
    {
        if(!fileGenerated)
        {
            Report newsReport = new Report("news/News.pdf");
            ArrayList<NewsItem> newsItems = retrieveNewsItems();
            List<String> reportParagraphs = new ArrayList<>();
            newsItems.forEach(x ->
            {
                reportParagraphs.add("Code: " + x.getCode());
                reportParagraphs.add("Title: " + x.getTitle());
                reportParagraphs.add("Published Date: " + x.getPublishedDate());
                reportParagraphs.add("Updated Date: " + x.getUpdatedDate());
                reportParagraphs.add("Link: " + x.getLink());
                reportParagraphs.add("Summary: " + x.getSummary());
            });
            newsReport.addContent(reportParagraphs);
            fileGenerated = true;
        }
    }
    public void printPDFFile()
    {
        generatePDFFile();
        new Printer("news/News.pdf");
    }
    public Boolean emailPDFFile(String recipientEmailAddress)
    {
        Email anEmail = new Email();
        return anEmail.sendMessage(recipientEmailAddress, "News PDF Report", "Dear Sir/Madam, Please find attached the news PDF report. "
        + "If you have any further queries please do not hesitate to contact me at stephencullinan@gmail.com", "news/News.pdf", "News.pdf");
    }
    public String getPDFFileLocation()
    {
        return "file:///" + System.getProperty("user.dir").replace("\\", "/") + "/news/News.pdf";
    }
}
