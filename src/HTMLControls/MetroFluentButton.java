package HTMLControls;

public class MetroFluentButton extends MetroComponent
{
    private String onClickEvent;
    private String buttonIcon;
    private String buttonText;
    public MetroFluentButton(String buttonText, String buttonIcon, String onClickEvent)
    {
        this.onClickEvent = onClickEvent;
        this.buttonIcon = buttonIcon;
        this.buttonText = buttonText;
        //parentElement = createDivElement("");
        //Button fluentBigButton = createButtonElement("fluent-big-button", onClickEvent, buttonText, "");
        //parentElement.appendChild(fluentBigButton);
        parentElement = createDivElement("fluent-big-button", "", buttonText, "", onClickEvent);
        parentElement.setTitle("button");
        //fluentBigButton.appendChild(createSpanElement("icon mif-" + buttonIcon));
        parentElement.appendChild(createSpanElement("icon mif-" + buttonIcon));
    }
}
