package HTMLControls;

import com.hp.gagawa.java.elements.H1;

public class MetroHeading extends MetroComponent
{
    private String title;
    private String subTitle;
    public MetroHeading(String title, String subTitle)
    {
        this.title = title;
        this.subTitle = subTitle;
        parentElement = createDivElement("");
        H1 headingElement = createHeadingElement(title, "center");
        parentElement.appendChild(headingElement);
        headingElement.appendChild(createSmallElement(subTitle));
    }
}
