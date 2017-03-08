package HTMLControls;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import java.util.ArrayList;
public class MetroListView extends MetroComponent
{
    public MetroListView()
    {
        parentElement = createDivElement("listview-outlook", "listview");
    }
    public void addItem(String itemTitle, ArrayList<ArrayList<String>> itemContents)
    {
        Div listGroup = createDivElement("list-group");
        parentElement.appendChild(listGroup);
        listGroup.appendChild(createSpanElement("list-group-toggle", itemTitle));
        Div listGroupContent = createDivElement("list-group-content");
        listGroup.appendChild(listGroupContent);
        for(ArrayList<String> anItem : itemContents)
        {
            if(anItem.size() > 0)
            {
                A listItem = createAElement("#", "", "list marked");
                listGroupContent.appendChild(listItem);
                Div listContent = createDivElement("list-content");
                listItem.appendChild(listContent);
                listContent.appendChild(createSpanElement("list-title", anItem.get(0)));
                if(anItem.size() > 1)
                    listContent.appendChild(createSpanElement("list-subtitle", anItem.get(1)));
                if(anItem.size() > 2)
                    listContent.appendChild(createSpanElement("list-remark", anItem.get(2)));
            }
        }
    }
}