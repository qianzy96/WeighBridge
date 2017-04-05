package HTMLControls;
import com.hp.gagawa.java.elements.Div;

import java.util.*;
public class MetroChart extends MetroComponent
{
    private String chartID;
    private List<String> chartLabels;
    private LinkedHashMap<String, List<Double>> chartData;
    private String scriptContent;
    public MetroChart(String chartID, List<String> chartLabels, LinkedHashMap<String, List<Double>> chartData)
    {
        this.chartID = chartID;
        this.chartLabels = chartLabels;
        this.chartData = chartData;
        this.scriptContent = "";
        createControl();
    }
    public String getScriptContent()
    {
        return scriptContent;
    }
    private void createControl()
    {
        parentElement = createDivElement("");
        Div canvasTag = createCanvasTag(chartID, "100%", "100%");
        parentElement.appendChild(canvasTag);
        scriptContent = "function createBarChart()" +
        "{" +
                "alert('CREATE BAR CHART FUNCTION CALLED');" +
                "var barChartContext = document.getElementById('" + chartID + "').getContext('2d');" +
                "alert('BAR CHART OBJECT: ' + barChartContext);" +
                "var myChart = new Chart(barChartContext," +
                "{" +
                    "type: 'line'," +
                    "data:" +
                    "{" +
                        //"labels: ['M', 'T', 'W', 'T', 'F', 'S', 'S']," +
                        "labels: [@Labels]," +
                        "datasets: [@DataSets]" +
                    "}" +
                "});" +
                "alert('BAR CHART: ' + myChart);" +
        "}";
        String labelsText = "";
        for(String aChartLabel : chartLabels)
            labelsText += "'" + aChartLabel.replace("\'", "\\\'") + "', ";
        if(labelsText.length() > 0)
            labelsText = labelsText.substring(0, labelsText.length() - 2);
        String dataText = "";
        for(Map.Entry<String, List<Double>> aSetOfDataPoints : chartData.entrySet())
        {
            dataText += "{";
            dataText += "label: '" + aSetOfDataPoints.getKey().replace("\'", "\\\'") + "', ";
            dataText += "data: [";
            for(Double aDataPoint : aSetOfDataPoints.getValue())
                dataText += aDataPoint + ", ";
            if(aSetOfDataPoints.getValue().size() > 0)
                dataText = dataText.substring(0, dataText.length() - 2) + "]";
            dataText += "}, ";
        }
        if(dataText.length() > 0)
            dataText = dataText.substring(0, dataText.length() - 2);
        scriptContent = scriptContent.replace("@Labels", labelsText);
        scriptContent = scriptContent.replace("@DataSets", dataText);
        System.out.println("SCRIPT CONTENT: " + scriptContent);
        //Script aScriptTag = createScript(scriptContent);
        //parentElement.appendChild(aScriptTag);
    }
}
