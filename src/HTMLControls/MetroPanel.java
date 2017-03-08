package HTMLControls;

import com.hp.gagawa.java.elements.Div;

public class MetroPanel extends MetroComponent
{
    private String title;
    private String content;
    private String icon;
    public MetroPanel(String title, String content, String icon)
    {
        this.title = title;
        this.content = content;
        this.icon = icon;
        parentElement = createDivElement("panel");
        Div headingPanelElement = createDivElement("heading");
        parentElement.appendChild(headingPanelElement);
        headingPanelElement.appendChild(createSpanElement("icon mif-" + icon));
        headingPanelElement.appendChild(createSpanElement("title", title));
        Div contentElement = createDivElement("content", "", content);
        parentElement.appendChild(contentElement);
    }
}
