package HTMLControls;
import com.hp.gagawa.java.elements.*;
import java.util.ArrayList;
public class MetroBreadCrumbs extends MetroComponent
{
    private ArrayList<String> breadCrumbsItems;
    public MetroBreadCrumbs(ArrayList<String> breadCrumbsItems)
    {
        this.breadCrumbsItems = new ArrayList<>();
        parentElement = createDivElement("");
        Ul breadCrumbs = createUlElement("breadcrumbs");
        parentElement.appendChild(breadCrumbs);
        Li breadCrumbsListItem = createLiElement("");
        breadCrumbs.appendChild(breadCrumbsListItem);
        A breadCrumbsLink = createAElement("#");
        breadCrumbsListItem.appendChild(breadCrumbsLink);
        breadCrumbsLink.appendChild(createSpanElement("icon mif-home"));
        for(String aBreadCrumbItem : breadCrumbsItems)
        {
            Li aListItem = createLiElement("");
            breadCrumbs.appendChild(aListItem);
            aListItem.appendChild(createAElement("#", aBreadCrumbItem));
        }
    }
}
