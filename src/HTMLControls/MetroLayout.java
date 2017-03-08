package HTMLControls;
import com.hp.gagawa.java.elements.Div;
import java.util.ArrayList;
public class MetroLayout extends MetroComponent
{
    public MetroLayout()
    {
        parentElement = createDivElement("grid condensed");
    }
    public void addRow(ArrayList<MetroComponent> controls, ArrayList<Integer> positions)
    {
        Div condensedGridTag = createDivElement("grid condensed");
        parentElement.appendChild(condensedGridTag);
        Div rowCellsGridTag = createDivElement("row cells12");
        condensedGridTag.appendChild(rowCellsGridTag);
        for(int counter = 0, index = 0; counter < controls.size() && index < positions.size(); counter++, index = index + 3)
        {
            if(positions.get(index) > 0)
                rowCellsGridTag.appendChild(createDivElement("cell colspan" + positions.get(index)));
            if(positions.get(index + 1) > 0)
                rowCellsGridTag.appendText("<div class=\"cell colspan" + positions.get(index + 1) + "\">" + controls.get(counter).toHTML().write() + "</div>");
            if(positions.get(index + 2) > 0)
                rowCellsGridTag.appendChild(createDivElement("cell colspan" + positions.get(index + 2)));
        }
    }
}
