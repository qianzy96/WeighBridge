package HTMLControls;
import com.hp.gagawa.java.elements.*;
public class MetroSideBar extends MetroComponent
{
    private String colour;
    private Ul sideBarComponent;
    public MetroSideBar(String colour)
    {
        this.colour = colour;
        parentElement = createDivElement("");
        sideBarComponent = createUlElement("sidebar " + colour, "width:100%;");
        parentElement.appendChild(sideBarComponent);
    }
    public void addItem(String title, String subTitle, String icon)
    {
        Li aListTag = createLiElement("");
        sideBarComponent.appendChild(aListTag);
        A aHrefTag = createAElement("#", "");
        aListTag.appendChild(aHrefTag);
        aHrefTag.appendChild(createSpanElement("mif-" + icon + " icon"));
        aHrefTag.appendChild(createSpanElement("title", title));
        aHrefTag.appendChild(createSpanElement("counter", subTitle));
    }
}
