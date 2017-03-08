package HTMLControls;
public class MetroPreloader extends MetroComponent
{
    public MetroPreloader()
    {
        parentElement = createDivElement("", "preloader");
        parentElement.setAttribute("data-type", "cycle");
        parentElement.setAttribute("data-style", "color");
    }
}