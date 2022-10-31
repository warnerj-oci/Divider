package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;

public class Divider extends JPanel implements ActionListener {

  private static final String DIVIDEND_STRING = "X";
  private static final String DIVISOR_STRING = "Y";
  private static final String QUOTIENT_STRING = "Result";
  private static final String HEADER_STRING = "Enter X and Y values then press the calculate button";
  private static final String OPERATOR_STRING = "OPERATOR";

  private final JFormattedTextField dividendEntry;
  private final JFormattedTextField divisorEntry;
  private final JTextArea quotientArea;
  private final JComboBox<String> operatorChoice;

  public Divider() {
    super (new BorderLayout());

    //Format for input
    NumberFormat numberFormat = NumberFormat.getNumberInstance();


    //Labels for fields
    JLabel dividendLabel = new JLabel(DIVIDEND_STRING);
    JLabel divisorLabel = new JLabel(DIVISOR_STRING);
    JLabel quotientLabel = new JLabel(QUOTIENT_STRING);
    JLabel headerLabel = new JLabel(HEADER_STRING);
    JLabel operatorLabel = new JLabel(OPERATOR_STRING);

    //Combo box for operator
    String[] operatorChoices = {"+", "-", "*", "/"};
    operatorChoice = new JComboBox<>(operatorChoices);
    operatorChoice.setSelectedIndex(0);
    operatorChoice.addActionListener(e -> { });

    //Fields for entry
    dividendEntry = new JFormattedTextField(numberFormat);
    dividendEntry.setColumns(10);
    dividendEntry.addActionListener(this);

    divisorEntry = new JFormattedTextField(numberFormat);
    divisorEntry.setColumns(10);
    divisorEntry.addActionListener(this);

    //Field for the answer
    quotientArea = new JTextArea();
    quotientArea.setColumns(10);
    quotientArea.setEditable(false);

    //Button for the action
    JButton divideButton = new JButton("CALCULATE");
    divideButton.addActionListener(this);

    //Associate labels with fields
    dividendLabel.setLabelFor(dividendEntry);
    divisorLabel.setLabelFor(divisorEntry);
    quotientLabel.setLabelFor(quotientArea);
    operatorLabel.setLabelFor(operatorChoice);

    // Add panel for labels
    JPanel labelPanel = new JPanel(new GridLayout(0,1));
    labelPanel.add(dividendLabel);
    labelPanel.add(divisorLabel);
    labelPanel.add(quotientLabel);

    //Add panel for fields
    JPanel fieldPanel = new JPanel(new GridLayout(0,1));
    fieldPanel.add(dividendEntry);
    fieldPanel.add(operatorChoice);
    fieldPanel.add(divisorEntry);
    fieldPanel.add(quotientArea);

    // Add panel for button
    JPanel buttonPanel = new JPanel(new GridLayout(1,1));
    buttonPanel.add(divideButton);

    // Add panel for header
    JPanel headerPanel = new JPanel(new GridLayout(1,1));
    headerPanel.add(headerLabel);

    setBorder(BorderFactory.createEmptyBorder(30, 80, 80, 80));
    add(headerPanel, BorderLayout.NORTH);
    add(labelPanel, BorderLayout.CENTER);
    add(fieldPanel, BorderLayout.LINE_END);
    add(buttonPanel, BorderLayout.SOUTH);
  }

  public void actionPerformed(ActionEvent evt) {
    quotientArea.setText("");

    //Grab the values input in the fields, make sure there is a value
    String dividend = dividendEntry.getText().isEmpty() ? "0" : dividendEntry.getText();
    String divisor = divisorEntry.getText().isEmpty() ? "0" : divisorEntry.getText();
    String operator = (String)operatorChoice.getSelectedItem();

    //Turn the input into numbers
    double numerator = Double.parseDouble(dividend);
    double denominator = Double.parseDouble(divisor);

    // Do the math and set the answer to the quotient field
    quotientArea.append(String.valueOf(calc(numerator, denominator, operator)));
    quotientArea.selectAll();

  }

  private double calc(double x, double y, String operator) {
    double answer = 0;
    //Check for the dreaded divide by zero condition
    if (operator.equals("DIVIDE") && y == 0) {
      JOptionPane.showMessageDialog(null, "You can't divide by zero, you silly goose!");
      return answer;
    }
    try {
      Thread.sleep(10*1000);
    } catch (InterruptedException ex) {
      //OH NO! Anyway...
    }
    switch(operator) {
      case "+":
        answer = x+y;
        break;
      case "-":
        answer = x-y;
        break;
      case "*":
        answer = x*y;
        break;
      case "/":
        answer = x/y;
        break;
      default:
        answer = -999;
        JOptionPane.showMessageDialog(null, "Why is there no operator here?!");
    }
    return answer;
  }


  private static void createAndShowDivider() {

    JFrame frame = new JFrame("GreatDivider");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.add(new Divider());

    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {

    javax.swing.SwingUtilities.invokeLater(Divider::createAndShowDivider);

  }
}
