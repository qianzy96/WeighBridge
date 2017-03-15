package HTMLControls;
public class MetroUpdatePanel extends MetroComponent
{
    private String id;
    public MetroUpdatePanel(String id, MetroComponent aMetroControl)
    {
        this.id = id;
        parentElement = createDivElement("", "", "", id);
        if(aMetroControl != null)
            parentElement.appendChild(aMetroControl.toHTML());
    }
}
