import javax.swing.*;
import java.awt.*;
public class BarChart extends JPanel
{
    private String title;
    private String[] labels;
    private Double[] values;
    public BarChart(String title, String[] labels, Double[] values)
    {
        this.title = title;
        this.labels = labels;
        this.values = values;
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Double minimum = 0.0, maximum = 0.0;
        System.out.println("VALUES SIZE: " + values.length);
        for(double aValue : values)
        {
            System.out.println("A VALUE: " + aValue);
            if(minimum > aValue)
                minimum = aValue;
            if(maximum < aValue)
                maximum = aValue;
        }
        int barWidth = getSize().width / values.length;
        Font titleFont = new Font("Segoe UI", Font.BOLD, 30);
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 30);
        FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);
        g.setFont(titleFont);
        g.drawString(title, (getSize().width - titleFontMetrics.stringWidth(title)) / 2, titleFontMetrics.getAscent());
        int top = titleFontMetrics.getHeight();
        int bottom = labelFontMetrics.getHeight();
        double scale = (getSize().height - top - bottom) / (maximum - minimum);
        int stringHeight = getSize().height - labelFontMetrics.getDescent();
        g.setFont(labelFont);
        int counter = 0;
        for(Double aValue : values)
        {
            int valueP = counter * barWidth + 1;
            int valueQ = top;
            int height = (int) (aValue * scale);
            if(aValue >= 0)
                valueQ += (int)((maximum - aValue) * scale);
            else
            {
                valueQ += (int)(maximum * scale);
                height = -height;
            }
            g.setColor(Color.BLUE);
            g.fillRect(valueP, valueQ, barWidth - 2, height);
            g.setColor(Color.BLACK);
            g.drawRect(valueP, valueQ, barWidth - 2, height);
            int labelWidth = labelFontMetrics.stringWidth(labels[counter]);
            int stringWidth = (counter * barWidth) + ((barWidth - labelWidth) / 2);
            g.drawString(labels[counter], stringWidth, stringHeight);
            counter++;
        }
    }
}