package HTMLControls;
public class MetroPopover extends MetroComponent
{
    private String backgroundColour;
    private String markerPosition;
    private String popoverText;
    public MetroPopover(String popoverText, String backgroundColour, String markerPosition)
    {
        this.popoverText = popoverText;
        this.backgroundColour = backgroundColour;
        this.markerPosition = markerPosition;
        parentElement = createDivElement("popover marker-on-" + markerPosition + " bg-" + backgroundColour);
        parentElement.appendChild(createDivElement("fg-white", "", popoverText));
    }
}
