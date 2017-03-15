package HTMLControls;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Li;
import com.hp.gagawa.java.elements.Ul;

import java.util.ArrayList;
import java.util.List;

public class MetroFluentMenu extends MetroComponent
{
    private ArrayList<String> titles;
    private int currentTabPosition;
    private String title;
    private String titleOnClickEvent;
    private String id;
    private Div tabsContent;
    public MetroFluentMenu(String id, String title, String titleOnClickEvent, ArrayList<String> titles)
    {
        this.id = id;
        this.title = title;
        this.titleOnClickEvent = titleOnClickEvent;
        this.titles = titles;
        this.currentTabPosition = 0;
        parentElement = createDivElement("fluent-menu", "fluentmenu");
        Ul tabsHolder = createUlElement("tabs-holder");
        parentElement.appendChild(tabsHolder);
        Li specialListItem = createLiElement("special", titleOnClickEvent);
        tabsHolder.appendChild(specialListItem);
        specialListItem.appendChild(createAElement("#", title));
        for(String aTitle : titles)
        {
            Li activeListItem = createLiElement("active");
            tabsHolder.appendChild(activeListItem);
            activeListItem.appendChild(createAElement("#" + id + "_" + currentTabPosition, aTitle, "",
            "displayPanel('" + id + "_content', " + currentTabPosition++ + ");"));
        }
        currentTabPosition = 0;
        tabsContent = createDivElement("tabs-content", "", "", id + "_content");
        parentElement.appendChild(tabsContent);
    }
    public void addPanelGroups(List<MetroFluentMenuPanelGroup> groups)
    {
        System.out.println("CURRENT TAB POSITION: " + currentTabPosition);
        StringBuilder controlText = new StringBuilder("<div class=\"tab-panel\" id=\"" + id + "_" + currentTabPosition++ + "\">");
        for(MetroFluentMenuPanelGroup aMenuPanelGroup : groups)
            controlText.append(aMenuPanelGroup.toString());
        tabsContent.appendText(controlText.toString());
    }
}