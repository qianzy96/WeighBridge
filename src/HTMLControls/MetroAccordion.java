package HTMLControls;
import com.hp.gagawa.java.elements.Div;
public class MetroAccordion extends MetroComponent
{
    public MetroAccordion()
    {
        parentElement = createDivElement("accordion large-heading", "accordion");
    }
    public void addFrame(String title, MetroComponent component, String icon)
    {
        Div frameElement = createDivElement("frame active");
        parentElement.appendChild(frameElement);
        Div frameHeading = createDivElement("heading", "", title);
        frameElement.appendChild(frameHeading);
        frameHeading.appendChild(createSpanElement("mif-" + icon + " icon"));
        Div frameContent = createDivElement("content");
        frameElement.appendChild(frameContent);
        if(component != null)
            frameContent.appendChild(component.toHTML());
    }
}
