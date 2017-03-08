package HTMLControls;

import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Tbody;
import com.hp.gagawa.java.elements.Thead;
import com.hp.gagawa.java.elements.Tr;

import java.util.ArrayList;

public class MetroDataTable extends MetroComponent
{
    private String id;
    private ArrayList<String> tableRowTitles;
    private ArrayList<ArrayList<String>> tableRowContents;
    public MetroDataTable(String id, ArrayList<String> tableRowTitles, ArrayList<ArrayList<String>> tableRowContents)
    {
        this.id = id;
        this.tableRowTitles = tableRowTitles;
        this.tableRowContents = tableRowContents;
        Table aTable = createTableElement("dataTable striped border bordered", "datatable", id, true);
        parentElement = createDivElement("");
        parentElement.appendChild(aTable);
        if(tableRowTitles.size() > 0)
        {
            Thead aTableHead = createTableHeadElement();
            aTable.appendChild(aTableHead);
            Tr headingTableRow = createTableRowElement();
            aTableHead.appendChild(headingTableRow);
            for(String aTableRowTitle : tableRowTitles)
                headingTableRow.appendChild(createTableHeadCellElement(aTableRowTitle));
        }
        if(tableRowContents.size() > 0)
        {
            Tbody aTableBody = createTableBodyElement();
            aTable.appendChild(aTableBody);
            for(ArrayList<String> aTableRow: tableRowContents)
            {
                if(aTableRow.size() > 0)
                {
                    Tr aTableRowTag = createTableRowElement();
                    aTableBody.appendChild(aTableRowTag);
                    for(String aTableCell: aTableRow)
                        aTableRowTag.appendChild(createTableRowCellElement(aTableCell));
                }
            }
        }
    }
}