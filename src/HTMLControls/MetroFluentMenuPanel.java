package HTMLControls;
import java.util.ArrayList;
public class MetroFluentMenuPanel extends MetroComponent
{
    private ArrayList<MetroFluentMenuPanelGroup> menuPanelGroups;
    private String id;
    public MetroFluentMenuPanel(String id, ArrayList<MetroFluentMenuPanelGroup> menuPanelGroups)
    {
        this.id = id;
        this.menuPanelGroups = menuPanelGroups;
        //parentElement = createDivElement("tab-panel", "", "", id);
        parentElement = createDivElement("");
        StringBuilder controlText = new StringBuilder("<div class=\"tab-panel\" id=\"" + id + "\">");
        for(MetroFluentMenuPanelGroup aMenuPanelGroup : menuPanelGroups)
            //parentElement.appendChild(aMenuPanelGroup.toHTML());
            controlText.append(aMenuPanelGroup.toHTML().write());
        controlText.append("</div>");
        parentElement.appendText(controlText.toString());
    }
}
