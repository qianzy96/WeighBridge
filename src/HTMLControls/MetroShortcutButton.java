package HTMLControls;

import com.hp.gagawa.java.elements.Button;

public class MetroShortcutButton extends MetroComponent
{
    public MetroShortcutButton(String title, String icon, String badgeContent)
    {
        parentElement = createDivElement("");
        Button aButtonElement = createButtonElement("shortcut-button bg-cyan bg-active-darkBlue fg-white");
        parentElement.appendChild(aButtonElement);
        aButtonElement.appendChild(createSpanElement("icon mif-" + icon));
        aButtonElement.appendChild(createSpanElement("title", title));
        if(badgeContent.length() > 0)
            aButtonElement.appendChild(createSpanElement("badge", badgeContent));
    }
}
