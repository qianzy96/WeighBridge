package Utilities;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObtainPhoto extends SwingWorker<Boolean, HashMap<Integer, String>>
{
    private JLabel obtainPhotoLabel;
    private JProgressBar obtainPhotoProgressBar;
    private String imageTitle;
    public ObtainPhoto(JLabel obtainPhotoLabel, JProgressBar obtainPhotoProgressBar, String imageTitle)
    {
        this.obtainPhotoLabel = obtainPhotoLabel;
        this.obtainPhotoProgressBar = obtainPhotoProgressBar;
        this.imageTitle = imageTitle;
    }
    protected Boolean doInBackground()
    {
        try
        {
            HashMap<Integer, String> parameters = new HashMap<>();
            parameters.put(25, "Selecting The Default WebCam");
            publish(parameters);
            Webcam webcam = Webcam.getDefault();
            parameters.clear();
            parameters.put(50, "Opening The Default Webcam");
            publish(parameters);
            webcam.open();
            parameters.clear();
            parameters.put(75, "Saving Your Entities.User Photo");
            publish(parameters);
            ImageIO.write(webcam.getImage(), "PNG", new File(imageTitle));
            parameters.clear();
            parameters.put(100, "Closing The Default Webcam");
            publish(parameters);
            webcam.close();
            return true;
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
            return false;
        }
    }
    protected void process(List<HashMap<Integer, String>> information)
    {
        for(HashMap<Integer, String> anInformation : information)
        {
            for (Map.Entry<Integer, String> anInformationRow : anInformation.entrySet())
            {
                obtainPhotoProgressBar.setValue(anInformationRow.getKey());
                obtainPhotoLabel.setText(anInformationRow.getValue());
            }
        }
    }
}
/*import com.github.sarxos.webcam.Webcam;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Utilities.Utilities.ObtainPhoto extends Frames.Components
{
    private JFrame frame;
    private String imageTitle;
    public Utilities.Utilities.ObtainPhoto(JFrame frame, String imageTitle)
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
            JLabel progressLabel = createLabel("Opening The Webcam To Create Your Entities.User Profile");
            progressPanel.add(progressBar);
            progressPanel.add(progressLabel);
            //Thread aThread = new Thread(() -> SwingUtilities.invokeLater(() -> addComponent(progressPanel)));
            //aThread.start();
            //addComponent(progressPanel);
            //progressLabel.setText("Selecting The Default Webcam");
            //progressBar.setValue(25);
            //refreshComponent();
            //SwingUtilities.invokeLater(() -> refreshComponent());
            Webcam webcam = Webcam.getDefault();
            //progressLabel.setText("Opening The Default Webcam");
            //progressBar.setValue(50);
            //refreshComponent();
            //SwingUtilities.invokeLater(() -> refreshComponent());
            webcam.open();
            //progressLabel.setText("Saving Your Entities.User Photo");
            //progressBar.setValue(75);
            //refreshComponent();
            //SwingUtilities.invokeLater(() -> refreshComponent());
            ImageIO.write(webcam.getImage(), "PNG", new File(imageTitle));
            //progressLabel.setText("Closing Your Webcam");
            //progressBar.setValue(100);
            //refreshComponent();
            //SwingUtilities.invokeLater(() -> refreshComponent());
            webcam.close();
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    //private void refreshComponent()
    //{
    //    frame.invalidate();
    //    frame.revalidate();
    //}
    //private void addComponent(JComponent newComponent)
    //{
    //    frame.add(newComponent);
    //    frame.invalidate();
    //    frame.revalidate();
    //}
}
*/