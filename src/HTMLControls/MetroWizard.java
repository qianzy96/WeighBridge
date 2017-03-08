package HTMLControls;

import com.hp.gagawa.java.elements.Div;

public class MetroWizard extends MetroComponent
{
    public MetroWizard()
    {
        parentElement = createDivElement("wizard2", "wizard2");
    }
    public void addStep(MetroComponent aControl)
    {
        Div stepElement = createDivElement("step");
        parentElement.appendChild(stepElement);
        Div stepContentElement = createDivElement("step-content");
        stepElement.appendChild(stepContentElement);
        if(aControl != null)
            stepContentElement.appendChild(aControl.toHTML());
    }
}
