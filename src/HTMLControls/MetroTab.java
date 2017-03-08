package HTMLControls;

import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Li;
import com.hp.gagawa.java.elements.Ul;

import java.util.ArrayList;

public class MetroTab extends MetroComponent
{
    private String id;
    private ArrayList<String> tabTitles;
    private int counter;
    private Div framesTag;
    public MetroTab(String id, ArrayList<String> tabTitles)
    {
        this.id = id;
        this.tabTitles = tabTitles;
        this.counter = 0;
        parentElement = createDivElement("tabcontrol", "tabcontrol");
        Ul tabs = createUlElement("tabs");
        parentElement.appendChild(tabs);
        for(String aTabTitle : tabTitles)
        {
            Li aListItem = createLiElement("");
            tabs.appendChild(aListItem);
            aListItem.appendChild(createAElement("#frame_" + id + counter++, aTabTitle));
        }
        counter = 0;
        framesTag = createDivElement("frames bg-white");
        parentElement.appendChild(framesTag);
    }
    public void addTab(MetroComponent aMetroComponent)
    {
        Div aFrameTag = createDivElement("frame bg-white", "", "", "frame_" + id + counter++);
        framesTag.appendChild(aFrameTag);
        if(aMetroComponent != null)
            aFrameTag.appendChild(aMetroComponent.toHTML());
    }
}
