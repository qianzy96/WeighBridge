package HTMLControls;
import com.hp.gagawa.java.elements.Button;
public class MetroCommandButton extends MetroComponent
{
    private String buttonTitle;
    private String buttonSubTitle;
    private String buttonIcon;
    private String buttonState;
    private String onClickEvent;
    public MetroCommandButton(String buttonTitle, String buttonSubTitle, String buttonIcon, String onClickEvent, String buttonState)
    {
        this.buttonTitle = buttonTitle;
        this.buttonSubTitle = buttonSubTitle;
        this.buttonIcon = buttonIcon;
        this.onClickEvent = onClickEvent;
        this.buttonState = buttonState;
        parentElement = createDivElement("");
        Button aButtonTag = createButtonElement("command-button " + buttonState, onClickEvent, buttonTitle, "width:100%;");
        parentElement.appendChild(aButtonTag);
        aButtonTag.appendChild(createSpanElement("icon mif-" + buttonIcon));
        aButtonTag.appendChild(createSmallElement(buttonSubTitle));
    }
}
