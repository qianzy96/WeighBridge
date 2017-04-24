package HTMLPages;
import Frames.BrowserFrame;
import HTMLControls.*;
import Utilities.*;
import Models.*;
import News.*;
import com.teamdev.jxbrowser.chromium.*;
import java.util.*;
public class NewsPage
{
    private News news;
    public NewsPage()
    {
        news = new News();
    }
    public JSONString createNewsPage()
    {
        MetroFluentMenu newsPageFluentMenu = new MetroFluentMenu("newsPageFluentMenu", "Main Menu", "getMainMenuForNewsPage();",
        new ArrayList<>(Collections.singletonList("News")));
        ArrayList<MetroFluentButton> buttons = new ArrayList<>();
        buttons.add(new MetroFluentButton("View Articles As Tiles", "table", "getArticlesAsTiles();"));
        buttons.add(new MetroFluentButton("View Articles As Table", "table", "getArticlesAsTable();"));
        buttons.add(new MetroFluentButton("Download Latest Articles", "download", "retrieveLatestArticles();"));
        buttons.add(new MetroFluentButton("Delete An Article", "bin", "deleteAnArticle();"));
        buttons.add(new MetroFluentButton("View Bookmarks", "bookmarks", "getBookmarkedArticles();"));
        MetroFluentMenuPanelGroup newsPageTabGroup = new MetroFluentMenuPanelGroup("News", buttons);
        buttons.clear();
        buttons.add(new MetroFluentButton("Generate PDF File", "file-pdf", "generateNewsPDFFile();"));
        buttons.add(new MetroFluentButton("Email PDF File", "mail-read", "emailNewsPDFFile();"));
        buttons.add(new MetroFluentButton("Print PDF File", "printer", "printNewsPDFFile();"));
        MetroFluentMenuPanelGroup quickActionsPageTabGroup = new MetroFluentMenuPanelGroup("Quick Actions", buttons);
        newsPageFluentMenu.addPanelGroups(new ArrayList<>(Arrays.asList(newsPageTabGroup, quickActionsPageTabGroup)));
        MetroLayout newsPageLayout = new MetroLayout();
        newsPageLayout.addRow(newsPageFluentMenu);
        newsPageLayout.addRow(new MetroUpdatePanel("newsPageUpdatePanel", null));
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", newsPageLayout.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString getMainMenuForNewsPage()
    {
        MetroLayout mainMenuLayout = new MetroLayout();
        MetroTile mainMenuTile = new MetroTile("getPortalPage();", "cyan", "Main Menu", "menu", "");
        MetroTile logOutTile = new MetroTile("loadHTML5Edition();", "cyan", "Log Out", "exit", "");
        MetroTile exitTile = new MetroTile("exit();", "cyan", "Exit", "exit", "");
        mainMenuLayout.addRow(new ArrayList<>(Arrays.asList(mainMenuTile, logOutTile, exitTile)), new ArrayList<>(Arrays.asList(1, 3, 0, 1, 3, 0, 1, 3, 0)));
        MetroAccordion mainMenuAccordion = new MetroAccordion();
        mainMenuAccordion.addFrame("Main Menu", mainMenuLayout, "menu");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", mainMenuAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString getArticlesAsTable()
    {
        ArrayList<ArrayList<String>> availableArticles = news.retrieveNewsItemsAsStrings();
        for(ArrayList<String> anArticle : availableArticles)
            anArticle.remove(anArticle.size() - 1);
        ArrayList<String> availableArticlesTitles = news.retrieveNewsItemsHeadings();
        availableArticlesTitles.remove(availableArticlesTitles.size() - 1);
        ArrayList<String> availableArticlesClickEvents = new ArrayList<>();
        availableArticles.forEach(x -> availableArticlesClickEvents.add("getDetailedDescriptionForArticle('" + x.get(0) + "');"));
        MetroDataTable availableArticlesDataTable = new MetroDataTable("availableArticlesDataTable", availableArticlesTitles, availableArticles,
        availableArticlesClickEvents);
        MetroAccordion availableArticlesAccordion = new MetroAccordion();
        availableArticlesAccordion.addFrame("Available Articles", availableArticlesDataTable, "table");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", availableArticlesAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString deleteAnArticle()
    {
        ArrayList<ArrayList<String>> availableArticles = news.retrieveNewsItemsAsStrings();
        for(ArrayList<String> anArticle : availableArticles)
            anArticle.remove(anArticle.size() - 1);
        ArrayList<String> availableArticlesTitles = news.retrieveNewsItemsHeadings();
        availableArticlesTitles.remove(availableArticlesTitles.size() - 1);
        ArrayList<String> availableArticlesClickEvents = new ArrayList<>();
        availableArticles.forEach(x -> availableArticlesClickEvents.add("deleteSelectedArticle('" + x.get(0) + "');"));
        MetroDataTable availableArticlesDataTable = new MetroDataTable("availableArticlesDataTable", availableArticlesTitles, availableArticles,
        availableArticlesClickEvents);
        MetroAccordion availableArticlesAccordion = new MetroAccordion();
        availableArticlesAccordion.addFrame("Available Articles For Deletion", availableArticlesDataTable, "bin");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", availableArticlesAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString deleteSelectedArticle(String articleIdentifier)
    {
        ArrayList<ArrayList<String>> deleteSelectedArticles = new ArrayList<>();
        NewsItem currentNewsItem = news.getNewsItem(articleIdentifier);
        deleteSelectedArticles.add(currentNewsItem.toList());
        deleteSelectedArticles.forEach(x -> x.remove(x.size() - 1));
        ArrayList<String> deleteSelectedTitles = news.retrieveNewsItemsHeadings();
        deleteSelectedTitles.remove(deleteSelectedTitles.size() - 1);
        MetroDataTable deleteSelectedTable = new MetroDataTable("deleteSelectedTable", deleteSelectedTitles, deleteSelectedArticles, new ArrayList<>());
        MetroHeading deleteSelectedArticleHeading = new MetroHeading("Are you sure you wish to delete this article?", "");
        MetroCommandButton deleteCommandButton = new MetroCommandButton("Delete", "Delete Selected Article", "checkmark",
        "deleteSelectedArticleConfirmation('" + articleIdentifier + "');", "success");
        MetroCommandButton cancelCommandButton = new MetroCommandButton("Cancel", "Return To Articles", "exit",
        "deleteAnArticle();", "danger");
        MetroLayout deleteSelectedArticleLayout = new MetroLayout();
        deleteSelectedArticleLayout.addRow(deleteSelectedArticleHeading);
        deleteSelectedArticleLayout.addEmptyRows(2);
        deleteSelectedArticleLayout.addRow(deleteSelectedTable);
        deleteSelectedArticleLayout.addEmptyRows(2);
        deleteSelectedArticleLayout.addRow(new ArrayList<>(Arrays.asList(deleteCommandButton, cancelCommandButton)), new ArrayList<>(Arrays.asList(1, 4, 1, 1, 4, 1)));
        MetroAccordion deleteSelectedArticleAccordion = new MetroAccordion();
        deleteSelectedArticleAccordion.addFrame("Delete Selected Article", deleteSelectedArticleLayout, "bin");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", deleteSelectedArticleAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString deleteSelectedArticleConfirmation(String articleIdentifier)
    {
        news.deleteSelectedArticle(articleIdentifier);
        return deleteAnArticle();
    }
    public JSONString getArticlesAsTiles()
    {
        ArrayList<MetroComponent> availableArticlesTiles = new ArrayList<>();
        ArrayList<ArrayList<String>> availableArticles = news.retrieveNewsItemsAsStrings();
        for(ArrayList<String> anAvailableArticle : availableArticles)
            availableArticlesTiles.add(new MetroTile("getDetailedDescriptionForArticle('" + anAvailableArticle.get(0) + "');", "cyan",
            anAvailableArticle.get(1), "feed3", ""));
        MetroLayout availableArticlesLayout = new MetroLayout();
        availableArticlesLayout.addMultipleRows(availableArticlesTiles, 3, 1, 3, 0, 2);
        MetroAccordion availableArticlesAccordion = new MetroAccordion();
        availableArticlesAccordion.addFrame("Available Articles", availableArticlesLayout, "table");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", availableArticlesAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString retrieveLatestArticles()
    {
        MetroLayout retrieveLatestArticlesLayout = new MetroLayout();
        retrieveLatestArticlesLayout.addRow(new MetroPreloader(), new ArrayList<>(Arrays.asList(5, 2, 5)));
        MetroAccordion retrieveLatestArticlesAccordion = new MetroAccordion();
        retrieveLatestArticlesAccordion.addFrame("Retrieving The Latest Articles", retrieveLatestArticlesLayout, "loop2");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", retrieveLatestArticlesAccordion.toString());
        Thread aThread = new Thread(() -> downloadLatestArticles());
        aThread.start();
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString getDetailedDescriptionForArticle(String articleNumber)
    {
        NewsItem aNewsItem = news.getNewsItem(articleNumber);
        HashMap<String, String> parameters = new HashMap<>();
        if(aNewsItem != null)
        {
            MetroPanel uniqueIdentifierPanel = new MetroPanel("Unique Identifier", "", "feed3",
            new MetroPopover(aNewsItem.getCode() + "", "cyan", "top"));
            MetroPanel publishedDatePanel = new MetroPanel("Published Date", "", "feed3",
            new MetroPopover(aNewsItem.getPublishedDate(), "cyan", "top"));
            MetroPanel updatedDatePanel = new MetroPanel("Updated Date", "", "feed3",
            new MetroPopover(aNewsItem.getUpdatedDate(), "cyan", "top"));
            MetroTextualPanel summaryPanel = new MetroTextualPanel("Summary", aNewsItem.getSummary());
            MetroLayout detailedDescriptionForArticleLayout = new MetroLayout();
            detailedDescriptionForArticleLayout.addRow(new ArrayList<>(Arrays.asList(uniqueIdentifierPanel, publishedDatePanel, updatedDatePanel)),
            new ArrayList<>(Arrays.asList(1, 3, 0, 1, 3, 0, 1, 3, 0)));
            MetroTile bookmarkTile;
            if(news.isBookmarked(aNewsItem.getCode() + ""))
                bookmarkTile = new MetroTile("bookmarkSelectedArticle('" + aNewsItem.getCode() + "');", "cyan", "Remove Bookmark",
                "bin", "");
            else
                bookmarkTile = new MetroTile("bookmarkSelectedArticle('" + aNewsItem.getCode() + "');", "cyan", "Add Bookmark",
                "bookmark", "");
            MetroUpdatePanel bookmarkUpdatePanel = new MetroUpdatePanel("bookmarkUpdatePanel", bookmarkTile);
            detailedDescriptionForArticleLayout.addRow(bookmarkUpdatePanel, new ArrayList<>(Arrays.asList(5, 3, 4)));
            detailedDescriptionForArticleLayout.addRow(summaryPanel);
            NewsArticle aNewsArticle = news.getNewsArticle(articleNumber);
            if(aNewsArticle != null)
            {
                MetroTextualPanel descriptionPanel = new MetroTextualPanel("Description", aNewsArticle.getText());
                detailedDescriptionForArticleLayout.addRow(descriptionPanel);
            }
            MetroAccordion selectedArticleAccordion = new MetroAccordion();
            selectedArticleAccordion.addFrame(aNewsItem.getTitle(), detailedDescriptionForArticleLayout, "feed3");
            parameters.put("html", selectedArticleAccordion.toString());
        }
        return Utilities.convertHashMapToJSON(parameters);
    }
    public void downloadLatestArticles()
    {
        news.retrieveNews();
        BrowserFrame.getBrowser().executeJavaScript("getArticlesAsTable();");
    }
    public JSONString generateNewsPDFFile()
    {
        news.generatePDFFile();
        MetroAccordion pdfFileAccordion = new MetroAccordion();
        pdfFileAccordion.addFrame("PDF Report Of Latest News", new MetroIFrame(news.getPDFFileLocation()), "file-pdf");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", pdfFileAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString printNewsPDFFile()
    {
        news.printPDFFile();
        MetroAccordion pdfFileAccordion = new MetroAccordion();
        MetroLayout pdfFileLayout = new MetroLayout();
        MetroPanel pdfFilePrintedPanel = new MetroPanel("Printed Successfully", "", "printer",
        new MetroHeading("Printed Successfully", ""));
        pdfFileLayout.addRow(pdfFilePrintedPanel);
        pdfFileLayout.addEmptyRows(2);
        pdfFileLayout.addRow(new MetroIFrame(news.getPDFFileLocation()));
        pdfFileAccordion.addFrame("Printed PDF Report", pdfFileLayout, "printer");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", pdfFileAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString emailNewsPDFFile()
    {
        news.generatePDFFile();
        MetroAccordion pdfFileAccordion = new MetroAccordion();
        MetroLayout pdfFileEmailAddressLayout = new MetroLayout();
        pdfFileEmailAddressLayout.addRow(new MetroTextField("Please enter the email address of the recipient", "mail-read", "text",
        "emailAddress"));
        pdfFileEmailAddressLayout.addEmptyRows(2);
        pdfFileEmailAddressLayout.addRow(new MetroCommandButton("Send", "Send Your Email", "checkmark",
        "emailNewsPDFFileConfirmation();","success"), new ArrayList<>(Arrays.asList(4, 4, 4)));
        pdfFileAccordion.addFrame("Email PDF Report", new MetroUpdatePanel("emailAddressUpdatePanel", pdfFileEmailAddressLayout), "mail-read");
        pdfFileAccordion.addFrame("PDF Report Confirmation", new MetroIFrame(news.getPDFFileLocation()), "file-pdf");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", pdfFileAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString emailNewsPDFFileConfirmation(String emailAddress)
    {
        HashMap<String, String> parameters = new HashMap<>();
        Boolean emailSuccessfullySent = news.emailPDFFile(emailAddress);
        if(emailSuccessfullySent)
            parameters.put("html", new MetroPanel("Email Successfully Sent", "", "mail-read", new MetroHeading("Your Email Was Successfully Sent",
            "")).toString());
        else
            parameters.put("html", new MetroPanel("Email Was Not Successfully Sent", "", "warning", new MetroHeading(
            "Your Email Was Not Successfully Sent", "")).toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString getBookmarkedArticles()
    {
        MetroAccordion bookmarksAccordion = new MetroAccordion();
        ArrayList<String> bookmarkArticlesOnClickEvents = new ArrayList<>();
        ArrayList<String> bookmarkArticlesTitles = news.retrieveNewsItemsHeadings();
        bookmarkArticlesTitles.remove(bookmarkArticlesTitles.size() - 1);
        ArrayList<ArrayList<String>> bookmarkArticlesContents = news.retrieveBookmarkedNewsItemsAsStrings();
        bookmarkArticlesContents.forEach(x -> x.remove(x.size() - 1));
        bookmarkArticlesContents.forEach(x -> bookmarkArticlesOnClickEvents.add("getDetailedDescriptionForArticle('" + x.get(0) + "');"));
        MetroDataTable bookmarkedArticlesTable = new MetroDataTable("bookmarkedArticlesTable", bookmarkArticlesTitles, bookmarkArticlesContents,
        bookmarkArticlesOnClickEvents);
        bookmarksAccordion.addFrame("Bookmarks", bookmarkedArticlesTable, "bookmark");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", bookmarksAccordion.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
    public JSONString bookmarkSelectedArticle(String articleIdentifier)
    {
        MetroTile aTile;
        if(news.toggleBookmark(articleIdentifier))
            aTile = new MetroTile("bookmarkSelectedArticle('" + articleIdentifier + "');", "cyan", "Remove Bookmark", "bin",
                    "");
        else
            aTile = new MetroTile("bookmarkSelectedArticle('" + articleIdentifier + "');", "cyan", "Add Bookmark",
                    "bookmark", "");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("html", aTile.toString());
        return Utilities.convertHashMapToJSON(parameters);
    }
}