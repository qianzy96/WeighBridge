import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.Paragraph;

import javax.swing.*;
import java.util.List;

public class Report
{
    private Document aReport;
    public Report(String fileLocation)
    {
        try
        {
            PdfDocument aPdfDocument = new PdfDocument(new PdfWriter(fileLocation));
            aReport = new Document(aPdfDocument);
            aReport.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD));
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    public void addContent(List<String> paragraphs)
    {
        paragraphs.forEach(x -> aReport.add(new Paragraph(x)));
        aReport.close();
    }
    public Document getReport()
    {
        return aReport;
    }
}
