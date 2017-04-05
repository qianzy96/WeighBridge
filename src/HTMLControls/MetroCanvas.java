package HTMLControls;
public class MetroCanvas extends MetroComponent
{
    public MetroCanvas(String id, String width, String height)
    {
        parentElement = createDivElement("");
        parentElement.appendChild(createCanvasTag(id, width, height));
    }
}
