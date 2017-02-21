import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
public class Calculator extends Components
{
    private JFrame frame;
    private JComponent component;
    private HashMap<FeedCosts, Double> feedCostsWithQuantities;
    public Calculator()
    {
        feedCostsWithQuantities = new HashMap<>();
    }
    public void createCalculatorDialogBox()
    {
        frame = createFrame("Calculator");
        JPanel mainPanel = new JPanel(new GridLayout(3, 0));
        JPanel textBoxPanel = new JPanel(new GridLayout(3, 0));
        JPanel tilesPanel = new JPanel(new GridLayout(5, 0));
        Database main = new Database();
        ArrayList<FeedCosts> availableFeedCosts = new ArrayList<>();
        ArrayList<String> availableFeedTitles = new ArrayList<>();
        ArrayList<ArrayList<String>> availableFeeds = main.getTableRows("feedcosts", new HashMap<>(), new ArrayList<>(), "");
        availableFeeds.forEach(x -> availableFeedCosts.add(new FeedCosts(Integer.parseInt(x.get(0)), x.get(1), x.get(2), x.get(3), x.get(4), x.get(5), x.get(6), x.get(7),
        x.get(8), x.get(9), x.get(10), x.get(11), x.get(12), x.get(13))));
        availableFeeds.forEach(x -> availableFeedTitles.add(x.get(0)));
        JComboBox aDropDownBox = createDropDown(new ArrayList<>());
        availableFeedCosts.forEach(x -> aDropDownBox.addItem(x));
        aDropDownBox.addItemListener((x) ->
        {
            while(textBoxPanel.getComponents().length > 0)
                textBoxPanel.remove(0);
            JLabel aLabel = createLabel("The kg per day fresh weight for " + x.getItem());
            JTextField aTextField = createTextField("");
            textBoxPanel.add(aLabel);
            textBoxPanel.add(aTextField);
            JButton aButton = createTile("Save Feed", "", 8);
            aButton.addActionListener((y) ->
            {
                JButton aTile = createTile(aDropDownBox.getSelectedItem() + " " + aTextField.getText() + " kg", "", 8);
                aDropDownBox.removeItem(x.getItem());
                tilesPanel.add(aTile);
                feedCostsWithQuantities.put((FeedCosts) x.getItem(), Double.parseDouble(aTextField.getText()));
                frame.invalidate();
                frame.revalidate();
            });
            textBoxPanel.add(aButton);
            frame.invalidate();
            frame.revalidate();
        });
        JButton calculateYourRationTile = createTile("Calculate Your Ration", "", 1);
        calculateYourRationTile.addActionListener(x ->
        {
            if(feedCostsWithQuantities.size() > 0)
                createResultsDialogBox();
        });
        mainPanel.add(aDropDownBox);
        mainPanel.add(textBoxPanel);
        mainPanel.add(tilesPanel);
        mainPanel.add(calculateYourRationTile);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public void createResultsDialogBox()
    {
        FeedCostsCalculator aCalculator = new FeedCostsCalculator();
        aCalculator.setFeedCosts(feedCostsWithQuantities);
        JPanel resultsPanel = new JPanel(new GridLayout(3, 2));
        JButton dryMatterTile = createTile("Dry Matter: " + aCalculator.calculateDryMatter(), "", 5);
        JButton crudeProteinTile = createTile("Crude Protein: " + aCalculator.calculateCrudeProtein(), "", 5);
        JButton ndfTile = createTile("NDF: " + aCalculator.calculateNDF(), "", 5);
        JButton starchAndSugarsTile = createTile("Starch & Sugars: " + aCalculator.calculateStarchAndSugars(), "", 5);
        JButton oilTile = createTile("Oil: " + aCalculator.calculateOil(), "", 5);
        resultsPanel.add(dryMatterTile);
        resultsPanel.add(crudeProteinTile);
        resultsPanel.add(ndfTile);
        resultsPanel.add(starchAndSugarsTile);
        resultsPanel.add(oilTile);
        addComponent(resultsPanel);
    }
    private void addComponent(JComponent newComponent)
    {
        if(component != null)
            frame.remove(component);
        component = newComponent;
        frame.add(component);
        frame.invalidate();
        frame.revalidate();
    }
    private void refreshComponent()
    {
        frame.invalidate();
        frame.revalidate();
    }
}