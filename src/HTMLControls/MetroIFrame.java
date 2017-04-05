package HTMLControls;
import com.hp.gagawa.java.elements.*;
public class MetroIFrame extends MetroComponent
{
    private String url;
    public MetroIFrame(String url)
    {
        this.url = url;
        parentElement = createDivElement("");
        Iframe iframeElement = createIFrameElement(url);
        parentElement.appendChild(iframeElement);
    }
}
