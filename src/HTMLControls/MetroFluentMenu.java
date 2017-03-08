package HTMLControls;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Li;
import com.hp.gagawa.java.elements.Ul;

import java.util.ArrayList;
public class MetroFluentMenu extends MetroComponent
{
    private ArrayList<String> titles;
    private String title;
    private Div tabsContent;
    public MetroFluentMenu(String title, ArrayList<String> titles)
    {
        this.title = title;
        this.titles = titles;
        parentElement = createDivElement("fluent-menu", "fluentmenu");
        Ul tabsHolder = createUlElement("tabs-holder");
        parentElement.appendChild(tabsHolder);
        Li specialListItem = createLiElement("special");
        tabsHolder.appendChild(specialListItem);
        specialListItem.appendChild(createAElement("#", title));
        int counter = 0;
        for(String aTitle : titles)
        {
            Li activeListItem = createLiElement("active");
            tabsHolder.appendChild(activeListItem);
            activeListItem.appendChild(createAElement("#tabs_" + counter++, aTitle));
        }
        tabsContent = createDivElement("tabs-content");
        parentElement.appendChild(tabsContent);
    }
    public void addPanel(MetroFluentMenuPanel aPanel)
    {
        tabsContent.appendChild(aPanel.toHTML());
    }
}