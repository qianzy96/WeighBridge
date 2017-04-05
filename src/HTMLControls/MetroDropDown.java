package HTMLControls;

import com.hp.gagawa.java.elements.Select;

import java.util.ArrayList;

public class MetroDropDown extends MetroComponent
{
    private String id;
    private String placeHolder;
    private ArrayList<String> values;
    public MetroDropDown(String id, String placeHolder, ArrayList<String> values)
    {
        this.id = id;
        this.placeHolder = placeHolder;
        this.values = values;
        parentElement = createDivElement("input-control full-size", "select");
        parentElement.setAttribute("data-placeholder", placeHolder);
        parentElement.setAttribute("data-allow-clear", "true");
        parentElement.setAttribute("data-template-result", "formatValue");
        parentElement.setId(id);
        Select aSelectElement = createSelectElement("full-size");
        parentElement.appendChild(aSelectElement);
        aSelectElement.appendChild(createOptionElement());
        values.forEach(x -> aSelectElement.appendChild(createOptionElement(x, x)));
    }
}
