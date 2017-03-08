package HTMLControls;
public class MetroProgressBar extends MetroComponent
{
    private int value;
    private String colour;
    public MetroProgressBar(int value, String colour)
    {
        this.value = value;
        this.colour = colour;
        parentElement = createDivElement("progress large", "progress", "", "", "", Integer.toString(value),
        "ribbed-" + colour);
    }
}
