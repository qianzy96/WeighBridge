package HTMLControls;
import com.hp.gagawa.java.elements.Div;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MetroLayout extends MetroComponent
{
    public MetroLayout()
    {
        parentElement = createDivElement("grid condensed");
    }
    public void addEmptyRows(int numberOfEmptyRows)
    {
        for(int counter = 0; counter < numberOfEmptyRows; counter++)
        {
            Div rowCellsGridTag = createDivElement("row cells12");
            parentElement.appendChild(rowCellsGridTag);
            rowCellsGridTag.appendChild(createDivElement("cell colspan12"));
        }
    }
    public void addRow(ArrayList<MetroComponent> controls, ArrayList<Integer> positions)
    {
        //Div condensedGridTag = createDivElement("grid condensed");
        //parentElement.appendChild(condensedGridTag);
        Div rowCellsGridTag = createDivElement("row cells12");

        parentElement.appendChild(rowCellsGridTag);

        //condensedGridTag.appendChild(rowCellsGridTag);
        for(int counter = 0, index = 0; counter < controls.size() && index < positions.size(); counter++, index = index + 3)
        {
            if(positions.get(index) > 0)
                rowCellsGridTag.appendChild(createDivElement("cell colspan" + positions.get(index)));
            if(positions.get(index + 1) > 0)
                rowCellsGridTag.appendText("<div class=\"cell colspan" + positions.get(index + 1) + "\">" + controls.get(counter).toString() + "</div>");
            if(positions.get(index + 2) > 0)
                rowCellsGridTag.appendChild(createDivElement("cell colspan" + positions.get(index + 2)));
        }
    }
    public void addRow(MetroComponent aControl)
    {
        addRow(new ArrayList<>(Collections.singletonList(aControl)), new ArrayList<>(Arrays.asList(0, 12, 0)));
    }
    public void addRow(MetroComponent aControl, ArrayList<Integer> positions)
    {
        addRow(new ArrayList<>(Collections.singletonList(aControl)), positions);
    }
    public void addMultipleRows(List<MetroComponent> controls, int numberOfControlsPerRow, int leftWidth, int centerWidth, int rightWidth, int numberOfEmptyRows)
    {
        Div rowCellsGridTag = createDivElement("row cells12");
        for(int counter = 0; counter < controls.size(); counter++)
        {
            if(counter % numberOfControlsPerRow == 0)
            {
                if(numberOfEmptyRows > 0)
                    addEmptyRows(numberOfEmptyRows);
                rowCellsGridTag = createDivElement("row cells12");
                parentElement.appendChild(rowCellsGridTag);
            }
            if(leftWidth > 0)
                rowCellsGridTag.appendChild(createDivElement("cell colspan" + leftWidth));
            if(centerWidth > 0)
                rowCellsGridTag.appendText("<div class=\"cell colspan" + centerWidth + "\">" + controls.get(counter).toString() + "</div>");
            if(rightWidth > 0)
                rowCellsGridTag.appendChild(createDivElement("cell colspan" + rightWidth));
        }
    }
}
