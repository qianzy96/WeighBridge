import jssc.SerialPort;
import javax.swing.*;

public class ObtainWeight
{
    private String portName;
    public ObtainWeight(String portName)
    {
        this.portName = portName;
        getWeightFromSerialPort();
    }
    private void getWeightFromSerialPort()
    {
        SerialPort aSerialPort = new SerialPort(portName);
        try
        {
            aSerialPort.openPort();
            aSerialPort.setParams(9600, 8, 1, 0);
            aSerialPort.setEventsMask(SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR);
            aSerialPort.addEventListener(x ->
            {
                if(x.isRXCHAR() && x.getEventValue() == 10)
                {
                    try
                    {
                        byte[] weight = aSerialPort.readBytes(10);
                    }
                    catch(Exception error)
                    {
                        JOptionPane.showMessageDialog(null, error);
                    }
                }
            });
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
}