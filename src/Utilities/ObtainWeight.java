package Utilities;

import jssc.SerialPort;
import javax.swing.*;
import Database.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ObtainWeight
{
    private String portName;
    private String baudRate;
    private String dataBits;
    private String stopBits;
    private String parity;
    public ObtainWeight(String portName, String baudRate, String dataBits, String stopBits, String parity)
    {
        this.portName = portName;
        this.baudRate = baudRate;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;
    }
    public ObtainWeight()
    {
        Database database = new Database();
        HashMap<String, String> selectedParameters = new HashMap<>();
        selectedParameters.put("title", "serial.");
        HashMap<String, String> receivedParameters = new HashMap<>();
        ArrayList<ArrayList<String>> tableContents = database.getTableRows("settings", selectedParameters,
        new ArrayList<>(Arrays.asList("title", "value")), "");
        tableContents.forEach(x -> receivedParameters.put(x.get(0), x.get(1)));
        portName = receivedParameters.get("serial.portname");
        baudRate = receivedParameters.get("serial.baudrate");
        dataBits = receivedParameters.get("serial.databits");
        stopBits = receivedParameters.get("serial.stopbits");
        parity = receivedParameters.get("serial.parity");
    }
    public String getPortName()
    {
        return portName;
    }
    public void setPortName(String portName)
    {
        this.portName = portName;
    }
    public String getBaudRate()
    {
        return baudRate;
    }
    public void setBaudRate(String baudRate)
    {
        this.baudRate = baudRate;
    }
    public String getDataBits()
    {
        return dataBits;
    }
    public void setDataBits(String dataBits)
    {
        this.dataBits = dataBits;
    }
    public String getStopBits()
    {
        return stopBits;
    }
    public void setStopBits(String stopBits)
    {
        this.stopBits = stopBits;
    }
    public String getParity()
    {
        return parity;
    }
    public void setParity(String parity)
    {
        this.parity = parity;
    }
    public void getWeightFromSerialPort()
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