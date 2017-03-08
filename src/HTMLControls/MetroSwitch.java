package HTMLControls;

import com.hp.gagawa.java.elements.Label;

public class MetroSwitch extends MetroComponent
{
    private Boolean switched;
    public MetroSwitch(Boolean switched)
    {
        this.switched = switched;
        parentElement = createDivElement("");
        Label switchElement = createLabelElement("switch");
        if(switched)
            switchElement.appendText("<input type=\"checkbox\" checked><span class=\"check\"></span>");
        else
            switchElement.appendText("<input type=\"checkbox\"><span class=\"check\"></span>");
    }
}