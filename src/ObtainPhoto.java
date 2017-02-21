import com.github.sarxos.webcam.Webcam;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ObtainPhoto extends Components
{
    private JFrame frame;
    private String imageTitle;
    public ObtainPhoto(JFrame frame, String imageTitle)
    {
        this.frame = frame;
        this.imageTitle = imageTitle;
        createPhoto();
    }
    private void createPhoto()
    {
        try
        {
            JPanel progressPanel = new JPanel(new GridLayout(2, 1));
            JProgressBar progressBar = createProgressBar(0, 100);
            JLabel progressLabel = createLabel("Opening The Webcam To Create Your User Profile");
            progressPanel.add(progressBar);
            progressPanel.add(progressLabel);
            //Thread aThread = new Thread(() -> SwingUtilities.invokeLater(() -> addComponent(progressPanel)));
            //aThread.start();
            //addComponent(progressPanel);
            /*progressLabel.setText("Selecting The Default Webcam");
            progressBar.setValue(25);
            refreshComponent();*/
            //SwingUtilities.invokeLater(() -> refreshComponent());
            Webcam webcam = Webcam.getDefault();
            /*progressLabel.setText("Opening The Default Webcam");
            progressBar.setValue(50);
            refreshComponent();*/
            //SwingUtilities.invokeLater(() -> refreshComponent());
            webcam.open();
            /*progressLabel.setText("Saving Your User Photo");
            progressBar.setValue(75);
            refreshComponent();*/
            //SwingUtilities.invokeLater(() -> refreshComponent());
            ImageIO.write(webcam.getImage(), "PNG", new File(imageTitle));
            /*progressLabel.setText("Closing Your Webcam");
            progressBar.setValue(100);
            refreshComponent();*/
            //SwingUtilities.invokeLater(() -> refreshComponent());
            webcam.close();
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    /*private void refreshComponent()
    {
        frame.invalidate();
        frame.revalidate();
    }
    private void addComponent(JComponent newComponent)
    {
        frame.add(newComponent);
        frame.invalidate();
        frame.revalidate();
    }*/
}
