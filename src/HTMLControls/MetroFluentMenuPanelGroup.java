package HTMLControls;
import com.hp.gagawa.java.elements.Div;
import java.util.ArrayList;
public class MetroFluentMenuPanelGroup extends MetroComponent
{
    private String panelCaption;
    private ArrayList<MetroFluentButton> panelButtons;
    public MetroFluentMenuPanelGroup(String panelCaption, ArrayList<MetroFluentButton> panelButtons)
    {
        this.panelCaption = panelCaption;
        this.panelButtons = panelButtons;
        parentElement = createDivElement("tab-panel-group");
        StringBuilder controlText = new StringBuilder("<div class=\"tab-group-content\">");
        for(MetroFluentButton aPanelButton : panelButtons)
            controlText.append(aPanelButton.toHTML().write());
        controlText.append("</div>");
        parentElement.appendText(controlText.toString());
        Div tabGroupCaption = createDivElement("tab-group-caption", "", panelCaption);
        parentElement.appendChild(tabGroupCaption);
    }
}
