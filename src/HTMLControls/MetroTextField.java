package HTMLControls;
public class MetroTextField extends MetroComponent
{
    private String placeHolderText;
    private String icon;
    private String textFieldID;
    private String textFieldType;
    public MetroTextField(String placeHolderText, String icon, String textFieldType, String textFieldID)
    {
        if(textFieldType.length() == 0)
            textFieldType = "text";
        this.textFieldID = textFieldID;
        this.placeHolderText = placeHolderText;
        this.icon = icon;
        this.textFieldType = textFieldType;
        parentElement = createDivElement("input-control modern text iconic full-size", "input");
        parentElement.appendChild(createInputElement(textFieldType, textFieldID));
        parentElement.appendChild(createSpanElement("label", placeHolderText));
        parentElement.appendChild(createSpanElement("informer", placeHolderText));
        parentElement.appendChild(createSpanElement("placeholder", placeHolderText));
        parentElement.appendChild(createSpanElement("icon mif-" + icon));
    }
}
