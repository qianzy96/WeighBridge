package HTMLControls;

import com.hp.gagawa.java.elements.Span;

public class MetroIcon extends MetroComponent
{
    private String icon;
    private int size;
    public MetroIcon(String icon, int size)
    {
        this.icon = icon;
        this.size = size;
        parentElement = createDivElement("");
        StringBuilder className = new StringBuilder("mif-" + icon);
        if(size > 0)
            className.append(" mif-" + size + "x");
        Span spanElement = createSpanElement(className.toString());
        parentElement.appendChild(spanElement);
    }
}
