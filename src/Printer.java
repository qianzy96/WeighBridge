import javax.print.*;
import javax.swing.*;
import java.io.FileInputStream;

public class Printer
{
    private String fileName;
    public Printer(String fileName)
    {
        this.fileName = fileName;
        printDocument();
    }
    private void printDocument()
    {
        try
        {
            FileInputStream anInputStream = new FileInputStream(this.fileName);
            Doc pdfDocument = new SimpleDoc(anInputStream, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
            PrintServiceLookup.lookupDefaultPrintService().createPrintJob().print(pdfDocument, null);
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
}
