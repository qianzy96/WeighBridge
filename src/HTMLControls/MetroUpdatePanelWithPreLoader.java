package HTMLControls;
import com.hp.gagawa.java.elements.*;
import java.util.*;
public class MetroUpdatePanelWithPreLoader extends MetroComponent
{
    public MetroUpdatePanelWithPreLoader(String id, MetroComponent existingComponent)
    {
        this.parentElement = createDivElement("", "", "", id);
        MetroAccordion preloaderAccordion = new MetroAccordion();
        MetroLayout preloaderLayout = new MetroLayout();
        preloaderLayout.addRow(new MetroPreloader(), new ArrayList<>(Arrays.asList(5, 2, 5)));
        preloaderAccordion.addFrame("Processing Your Request", preloaderLayout, "loop2");
        Div preloaderDiv = createDivElement("", "", "", id + "_Loader", "", "display:none;");
        preloaderDiv.appendChild(preloaderAccordion.toHTML());
        Div panelDiv = createDivElement("", "", "", id + "_Content");
        if(existingComponent != null)
            panelDiv.appendChild(existingComponent.toHTML());
        parentElement.appendChild(preloaderDiv);
        parentElement.appendChild(panelDiv);
    }
}
