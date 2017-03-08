package HTMLControls;
import com.hp.gagawa.java.elements.Div;
public class MetroTileWithControl extends MetroComponent
{
    private String onClickEvent;
    private MetroComponent aControl;
    private String tileLabel;
    public MetroTileWithControl(String onClickEvent, MetroComponent aControl, String tileLabel)
    {
        this.onClickEvent = onClickEvent;
        this.aControl = aControl;
        this.tileLabel = tileLabel;
        parentElement = createDivElement("tile bg-white fg-white", "tile", "", "", onClickEvent, "width:100%;");
        Div tileContentTag = createDivElement("tile-content");
        parentElement.appendChild(tileContentTag);
        if(aControl != null)
            tileContentTag.appendChild(aControl.toHTML());
        if(tileLabel.length() > 0)
            parentElement.appendChild(createDivElement("tile-label", "", tileLabel));
    }
}