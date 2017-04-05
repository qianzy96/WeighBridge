package HTMLControls;
import com.hp.gagawa.java.elements.*;
public class MetroTextualPanel extends MetroComponent
{
    private String title;
    private String textualContent;
    public MetroTextualPanel(String title, String textualContent)
    {
        parentElement = createDivElement("example");
        parentElement.setAttribute("data-text", title);
        if(title.length() > 0)
            parentElement.appendChild(new MetroHeading(title, "").toHTML());
        parentElement.appendText(textualContent);
    }
}
