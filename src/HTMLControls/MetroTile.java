package HTMLControls;
import com.hp.gagawa.java.elements.Div;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MetroTile extends MetroComponent
{
    private String onClickEvent;
    private String backgroundColour;
    private String tileLabel;
    private String tileIcon;
    private String tileBadge;
    private String tileHeading;
    public MetroTile(String onClickEvent, String backgroundColour, String tileLabel, String tileIcon, String tileBadge)
    {
        this.onClickEvent = onClickEvent;
        this.backgroundColour = backgroundColour;
        this.tileLabel = tileLabel;
        this.tileIcon = tileIcon;
        this.tileBadge = tileBadge;
        parentElement = createDivElement("tile bg-" + backgroundColour + " fg-white", "tile", "", "", onClickEvent,
        "width:100%;");
        Div tileContentElement = createDivElement("tile-content iconic");
        parentElement.appendChild(tileContentElement);
        tileContentElement.appendChild(createSpanElement("icon mif-" + tileIcon));
        if(tileBadge.length() > 0)
            tileContentElement.appendChild(createSpanElement("tile-badge bg-darkRed", tileBadge));
        tileContentElement.appendChild(createSpanElement("tile-label", tileLabel));
        /*Div tileContentElement = createDivElement("tile-content");
        parentElement.appendChild(tileContentElement);
        MetroLayout tileLayout = new MetroLayout();
        tileLayout.addRow(new ArrayList<>(Collections.singletonList(new MetroIcon(tileIcon, 4))), new ArrayList<>(Arrays.asList(5, 2, 5)));
        tileLayout.addRow(new ArrayList<>(Collections.singletonList(new MetroHeading(tileLabel, ""))), new ArrayList<>(Arrays.asList(0, 12, 0)));
        tileContentElement.appendChild(tileLayout.toHTML());*/
    }
    public MetroTile(String onClickEvent, String backgroundColour, String tileLabel, String tileIcon, String tileBadge, String tileHeading)
    {
        this.onClickEvent = onClickEvent;
        this.backgroundColour = backgroundColour;
        this.tileLabel = tileLabel;
        this.tileIcon = tileIcon;
        this.tileBadge = tileBadge;
        this.tileHeading = tileHeading;
        parentElement = createDivElement("tile bg-" + backgroundColour + " fg-white", "tile", "", "", onClickEvent,
        "width:100%;");
        Div tileContentElement = createDivElement("tile-content");
        parentElement.appendChild(tileContentElement);
        MetroLayout tileLayout = new MetroLayout();
        tileLayout.addRow(new ArrayList<>(Collections.singletonList(new MetroHeading(tileHeading, ""))), new ArrayList<>(Arrays.asList(0, 12, 0)));
        tileLayout.addRow(new ArrayList<>(Collections.singletonList(new MetroIcon(tileIcon, 4))), new ArrayList<>(Arrays.asList(5, 2, 5)));
        tileContentElement.appendChild(tileLayout.toHTML());
    }
}
