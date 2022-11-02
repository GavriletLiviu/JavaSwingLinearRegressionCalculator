package linearregressioncalculator;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static linearregressioncalculator.LinearRegressionCalculator.regCalc;

public class GraphicalInterface extends JFrame implements ActionListener{
    JPanel calculations = new JPanel();
    JTextArea data = new JTextArea();
    JScrollPane dataSets = new JScrollPane(data);
    JLabel lblXI = new JLabel("Xi:", SwingConstants.CENTER);
    JLabel lblYI = new JLabel("Yi:", SwingConstants.CENTER);
    JButton reset = new JButton("Reset");
    JTextField txtXI = new JTextField();
    JTextField txtYI = new JTextField();
    JButton addDataSets = new JButton("ADD");
    JButton importDataSets = new JButton("Import");
    JButton exportDataSets = new JButton("Export");
    JButton computeDataSets = new JButton("Compute Data");
    JTextField txtB0 = new JTextField();
    JLabel lblB0 = new JLabel("b0", SwingConstants.CENTER);
    JLabel lblEqB0 = new JLabel("Ymean - (b1 * Xmean)", SwingConstants.CENTER);
    JTextField txtB1 = new JTextField();
    JLabel lblB1 = new JLabel("b1", SwingConstants.CENTER);
    JLabel lblEqB1 = new JLabel("(SUM[(Xi-Xmean)*(Yi-Ymean)])/(SUM(Xi-Xmean)^2)]", SwingConstants.CENTER);
    JTextField txtSSE = new JTextField();
    JLabel lblSSE = new JLabel("SSE-variance due to residual", SwingConstants.CENTER);
    JLabel lblEqSSE = new JLabel("SUM[(Yi-estimatedYi)^2]", SwingConstants.CENTER);
    JTextField txtSSR = new JTextField();
    JLabel lblSSR = new JLabel("SSR-variance due to regression", SwingConstants.CENTER);
    JLabel lblEqSSR = new JLabel("SUM[(estimatedYi-Ymean)^2]", SwingConstants.CENTER);
    JTextField txtSST = new JTextField();
    JLabel lblSST = new JLabel("SST-total sum of squares", SwingConstants.CENTER);
    JLabel lblEqSST = new JLabel("SSR + SST", SwingConstants.CENTER);
    JTextField txtRXY = new JTextField();
    JLabel lblRXY = new JLabel("Rxy-coefficient of corelation", SwingConstants.CENTER);
    JLabel lblEqRXY = new JLabel("Rsquared^1/2", SwingConstants.CENTER);
    JTextField txtRSQ = new JTextField();
    JLabel lblRSQ = new JLabel("R Squared-coefficient of determination", SwingConstants.CENTER);
    JLabel lblEqRSQ = new JLabel("SSR / SST", SwingConstants.CENTER);
    JTextField txtMSE = new JTextField();
    JLabel lblMSE = new JLabel("MSE-mean square error", SwingConstants.CENTER);
    JLabel lblEqMSE = new JLabel("SSE / (n-2)", SwingConstants.CENTER);
    JTextField txtS = new JTextField();
    JLabel lblS = new JLabel("Standard Error", SwingConstants.CENTER);
    JLabel lblEqS = new JLabel("MSE^1/2", SwingConstants.CENTER);
    JTextField txtSofB0 = new JTextField();
    JLabel lblSofB0 = new JLabel("standard error for intercept", SwingConstants.CENTER);
    JLabel lblEqSofB0 = new JLabel("{MSE*[(1/n)+(Xmean^2)/(SUM(Xi-Xmean)^2)]}^1/2", SwingConstants.CENTER);
    JTextField txtSofB1 = new JTextField();
    JLabel lblSofB1 = new JLabel("standard error for slope", SwingConstants.CENTER);
    JLabel lblEqSofB1 = new JLabel("{MSE*[1/(SUM(Xi-Xmean)^2)]}^1/2", SwingConstants.CENTER);
    JTextField txtTofB0 = new JTextField();
    JLabel lblTofB0 = new JLabel("t-score for intercept", SwingConstants.CENTER);
    JLabel lblEqTofB0 = new JLabel("|b0/Sb0|", SwingConstants.CENTER);
    JTextField txtTofB1 = new JTextField();
    JLabel lblTofB1 = new JLabel("t-score for slope", SwingConstants.CENTER);
    JLabel lblEqTofB1 = new JLabel("|b1/Sb1|", SwingConstants.CENTER);
    JTextField txtMSR = new JTextField();
    JLabel lblMSR = new JLabel("MSR-mean square due to regression", SwingConstants.CENTER);
    JLabel lblEqMSR = new JLabel("SSR/1", SwingConstants.CENTER);
    JTextField txtFScore = new JTextField();
    JLabel lblFScore = new JLabel("f-score", SwingConstants.CENTER);
    JLabel lblEqFScore = new JLabel("(n-2)*[(R^2)/(1-R^2)]", SwingConstants.CENTER);
    
    public GraphicalInterface(){
        setSize(1024,1024);
        setTitle("Linear Regression Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1));
        calculations.setLayout(new GridLayout(18,3));
        calculations.add(lblXI);
        calculations.add(lblYI);
        calculations.add(reset);
        calculations.add(txtXI);
        calculations.add(txtYI);
        calculations.add(addDataSets);
        calculations.add(importDataSets);
        calculations.add(exportDataSets);
        calculations.add(computeDataSets);
        calculations.add(txtB0);
        txtB0.setEditable(false);
        calculations.add(lblB0);
        calculations.add(lblEqB0);
        calculations.add(txtB1);
        txtB1.setEditable(false);
        calculations.add(lblB1);
        calculations.add(lblEqB1);
        calculations.add(txtSSE);
        txtSSE.setEditable(false);
        calculations.add(lblSSE);
        calculations.add(lblEqSSE);
        calculations.add(txtSSR);
        txtSSR.setEditable(false);
        calculations.add(lblSSR);
        calculations.add(lblEqSSR);
        calculations.add(txtSST);
        txtSST.setEditable(false);
        calculations.add(lblSST);
        calculations.add(lblEqSST);
        calculations.add(txtRXY);
        txtRXY.setEditable(false);
        calculations.add(lblRXY);
        calculations.add(lblEqRXY);
        calculations.add(txtRSQ);
        txtRSQ.setEditable(false);
        calculations.add(lblRSQ);
        calculations.add(lblEqRSQ);
        calculations.add(txtMSE);
        txtMSE.setEditable(false);
        calculations.add(lblMSE);
        calculations.add(lblEqMSE);
        calculations.add(txtS);
        txtS.setEditable(false);
        calculations.add(lblS);
        calculations.add(lblEqS);
        calculations.add(txtSofB0);
        txtSofB0.setEditable(false);
        calculations.add(lblSofB0);
        calculations.add(lblEqSofB0);
        calculations.add(txtSofB1);
        txtSofB1.setEditable(false);
        calculations.add(lblSofB1);
        calculations.add(lblEqSofB1);
        calculations.add(txtTofB0);
        txtTofB0.setEditable(false);
        calculations.add(lblTofB0);
        calculations.add(lblEqTofB0);
        calculations.add(txtTofB1);
        txtTofB1.setEditable(false);
        calculations.add(lblTofB1);
        calculations.add(lblEqTofB1);
        calculations.add(txtMSR);
        txtMSR.setEditable(false);
        calculations.add(lblMSR);
        calculations.add(lblEqMSR);
        calculations.add(txtFScore);
        txtFScore.setEditable(false);
        calculations.add(lblFScore);
        calculations.add(lblEqFScore);
        add(calculations);
        add(dataSets);
        data.setEditable(false);
        importDataSets.addActionListener(this);
        addDataSets.addActionListener(this);
        exportDataSets.addActionListener(this);
        computeDataSets.addActionListener(this);
        importDataSets.setMnemonic(KeyEvent.VK_I);
        addDataSets.setMnemonic(KeyEvent.VK_A);
        exportDataSets.setMnemonic(KeyEvent.VK_E);
        computeDataSets.setMnemonic(KeyEvent.VK_C);
        reset.setMnemonic(KeyEvent.VK_R);
        reset.addActionListener(this);
        txtXI.addKeyListener(new TextKeyListener());
        txtYI.addKeyListener(new TextKeyListener());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Import")){
            importDataSet();
        }else if(e.getActionCommand().equals("ADD")){
            if(txtXI.getText().isEmpty()){
                return;
            }else if(txtYI.getText().isEmpty()){
                return;
            }else{
            addDataSet();
            txtXI.setText("");
            txtYI.setText("");
            }
        }else if(e.getActionCommand().equals("Compute Data")){
            computeData();
        }else if(e.getActionCommand().equals("Reset")){
            resetData();
            resetText();
            resetDataSetsTextArea();
        }else if(e.getActionCommand().equals("Export")){
            exportResults();
        }
    }
    class TextKeyListener extends KeyAdapter{
            @Override
            public void keyTyped(KeyEvent e){
                //ASCI code for 0 to 9 is 48 to 57
                int asciiCode = e.getKeyChar();
                if (asciiCode==46){
                }else if (asciiCode<48 || asciiCode>57){
                    e.consume();
                    //text.setText(text.getText());
                }
            }
        }
    private void exportResults(){
        JFileChooser chooser = new JFileChooser("./");
        int returned = chooser.showSaveDialog(this);
        if (returned == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            file = new File(file.toString() + ".txt");
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.append("The computed value for the punctual estimation of intercept (b0) = " + regCalc.getB0());
                writer.newLine();
                writer.append("The computed value for the punctual estimation of the slope/regression coefficient (b1) = " + regCalc.getB1());
                writer.newLine();
                writer.append("The computed value for the variance due to residual = " + regCalc.getSSE());
                writer.newLine();
                writer.append("The computed value for the variance due to regression = " + regCalc.getSSR());
                writer.newLine();
                writer.append("The computed value for the total sum of squares = " + regCalc.getSST());
                writer.newLine();
                writer.append("The computed value for the coefficient of corelation (Rxy) = " + regCalc.getRXY());
                writer.newLine();
                writer.append("The computed value for the coefficient of determination (R squared) = " + regCalc.getRSQ());
                writer.newLine();
                writer.append("The computed value for the mean square error (MSE) = " + regCalc.getMSE());
                writer.newLine();
                writer.append("The computed value for standard error = " + regCalc.getS());
                writer.newLine();
                writer.append("The computed value for the standard error for the intercept = " + regCalc.getSofB0());
                writer.newLine();
                writer.append("The computed value for the standard error for the slope = " + regCalc.getSofB1());
                writer.newLine();
                writer.append("The computed value for the t-score for the intercept = " + regCalc.getTofB0());
                writer.newLine();
                writer.append("The computed value for the t-score for the slope = " + regCalc.getTofB1());
                writer.newLine();
                writer.append("The computed value for the mean square due to regression = " + regCalc.getMSR());
                writer.newLine();
                writer.append("The computed value for the f-score = " + regCalc.getFScore());
                writer.newLine();
                writer.append("This computation have been done on the following dataset : ");
                writer.newLine();
                int counter = 1;
                for (int i=0; i<regCalc.numberOfSets(); i++){
                    writer.append("X[" + counter + "] = " + regCalc.getXI(i) + " Y[" + counter + "] = " + regCalc.getYI(i));
                    writer.newLine();
                    counter++;
                }
                writer.close();
            }catch(IOException e){
                System.out.println("Could not export file!");
            }
        }
    }
    private void resetData(){
        regCalc = new RegressionCalculus();
    }
    private void resetText(){
        txtB0.setText("");
        txtB1.setText("");
        txtSSE.setText("");
        txtSSR.setText("");
        txtSST.setText("");
        txtRSQ.setText("");
        txtRXY.setText("");
        txtMSE.setText("");
        txtS.setText("");
        txtSofB0.setText("");
        txtSofB1.setText("");
        txtTofB0.setText("");
        txtTofB1.setText("");
        txtMSR.setText("");
        txtFScore.setText("");
        lblXI.setText("Xi:");
        lblYI.setText("Yi:");
        data.setText("");
    }
    private void resetDataSetsTextArea(){
        data.setText("");
    }
    private void addDataSet(){
        double x, y;
        x = Double.parseDouble(txtXI.getText());
        y = Double.parseDouble(txtYI.getText());
        DataSet data = new DataSet(x,y);
        regCalc.addDataSet(data);
        String line = ("X[" + (regCalc.numberOfSets()) + "] = " + x + " Y[" + (regCalc.numberOfSets()) + "] = " + y);
        this.data.append(line+"\n");
        increaseLabelIndex();
    }
    private void computeData(){
        regCalc.calculus();
        txtB0.setText(String.valueOf(regCalc.getB0()));
        txtB1.setText(String.valueOf(regCalc.getB1()));
        txtSSE.setText(String.valueOf(regCalc.getSSE()));
        txtSSR.setText(String.valueOf(regCalc.getSSR()));
        txtSST.setText(String.valueOf(regCalc.getSST()));
        txtRSQ.setText(String.valueOf(regCalc.getRSQ()));
        txtRXY.setText(String.valueOf(regCalc.getRXY()));
        txtMSE.setText(String.valueOf(regCalc.getMSE()));
        txtS.setText(String.valueOf(regCalc.getS()));
        txtSofB0.setText(String.valueOf(regCalc.getSofB0()));
        txtSofB1.setText(String.valueOf(regCalc.getSofB1()));
        txtTofB0.setText(String.valueOf(regCalc.getTofB0()));
        txtTofB1.setText(String.valueOf(regCalc.getTofB1()));
        txtMSR.setText(String.valueOf(regCalc.getMSR()));
        txtFScore.setText(String.valueOf(regCalc.getFScore()));
    }
    private void importDataSet(){
        JFileChooser chooser = new JFileChooser("./");
        int returned = chooser.showOpenDialog((this));
        if (returned == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while((line=br.readLine())!=null){
                    String[] tempData = line.split(",");
                    double x = Double.parseDouble(tempData[0]);
                    double y = Double.parseDouble(tempData[1]);
                    DataSet dataSet = new DataSet(x, y);
                    regCalc.addDataSet(dataSet);
                }
                br.close();
                
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            resetDataSetsTextArea();
            for(int i=0; i<regCalc.numberOfSets(); i++){
                String line = ("X[" + (i+1) + "] = " + regCalc.getXI(i) + " Y[" + (i+1) + "] = " + regCalc.getYI(i));
                data.append(line+"\n");
            }
            increaseLabelIndex();
        }
    }
    private void increaseLabelIndex(){
        int tempCount = regCalc.numberOfSets()+1;
        lblXI.setText("X" + tempCount + ":");
        lblYI.setText("Y" + tempCount + ":");
    }
}
            
    

