package HTMLControls;
import com.hp.gagawa.java.elements.Div;
public class MetroPresenter extends MetroComponent
{
    private Div scene;
    public MetroPresenter()
    {
        parentElement = createDivElement("presenter", "presenter");
        parentElement.setAttribute("data-height", "220");
        parentElement.setAttribute("data-easing", "swing");
        scene = createDivElement("scene");
        parentElement.appendChild(scene);
    }
    public void addSlide(String backgroundColour, String heading, String firstContent, String secondContent)
    {
        Div act = createDivElement("act bg-" + backgroundColour + " fg-white");
        scene.appendChild(act);
        act.appendChild(createHeadingElement(heading, "actor", "10,250"));
        act.appendChild(createParagraphElement(firstContent, "actor", "70, 250"));
        act.appendChild(createParagraphElement(secondContent, "actor", "130, 250"));
    }
}
