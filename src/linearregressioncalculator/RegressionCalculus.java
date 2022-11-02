package linearregressioncalculator;

import java.util.ArrayList;

public class RegressionCalculus {
    
    private ArrayList<DataSet> regressionData;
    private double xMean=0;
    private double yMean=0;
    private double XiMXmean=0;
    private double YiMYmean=0;
    private double XiMXmeanSQ=0;
    private double YiMYmeanSQ=0;
    private double PRODXiMXmeanYiMYmean=0;
    private double b0;
    private double b1;
    private double SSE=0;
    private double SSR=0;
    private double SST=0;
    private double RSQ=0;
    private double RXY=0;
    private double MSE=0;
    private double S=0;
    private double SofB0=0;
    private double SofB1=0;
    private double TofB0=0;
    private double TofB1=0;
    private double MSR=0;
    private double FScore=0;
    
    public RegressionCalculus(){
        this.regressionData = new ArrayList<DataSet>();
    }
    public void addDataSet (DataSet dataSet){
        this.regressionData.add(dataSet);
    }
    public double getB0(){
        return this.b0;
    }
    public double getB1(){
        return this.b1;
    }
    public double getSSE(){
        return this.SSE;
    }
    public double getSSR(){
        return this.SSR;
    }
    public double getSST(){
        return this.SST;
    }
    public double getRSQ(){
        return this.RSQ;
    }
    public double getRXY(){
        return this.RXY;
    }
    public double getMSE(){
        return this.MSE;
    }
    public double getS(){
        return this.S;
    }
    public double getSofB0(){
        return this.SofB0;
    }
    public double getSofB1(){
        return this.SofB1;
    }
    public double getTofB0(){
        return this.TofB0;
    }
    public double getTofB1(){
        return this.TofB1;
    }
    public double getMSR(){
        return this.MSR;
    }
    public double getFScore(){
        return this.FScore;
    }
    public int numberOfSets(){
        return this.regressionData.size();
    }
    public double getXI(int index){
        return regressionData.get(index).getX();
    }
    public double getYI(int index){
        return regressionData.get(index).getY();
    }
    public void calculus(){
        xMean();
        yMean();
        XiMXmean();
        YiMYmean();
        computePRODXiMXmeanYiMYmean();
        b1 = PRODXiMXmeanYiMYmean/XiMXmeanSQ;
        b0 = yMean - (b1*xMean);
        computeSSE();
        computeSSR();
        computeSST();
        computeRSQ();
        RXY = Math.sqrt(RSQ);
        computeMSE();
        S = Math.sqrt(MSE);
        SofB0 = Math.sqrt(computeSofB0SQ());
        SofB1 = Math.sqrt(computeSofB1SQ());
        computeTofB0();
        computeTofB1();
        MSR = SSR;
        computeFScore();
    }
    private double xSum(){
        double xSum=0;
        for (int i=0; i<regressionData.size(); i++){
            xSum += regressionData.get(i).getX();
        }
        return xSum;
    }
    private void xMean(){
        xMean = xSum() / regressionData.size();
    }
    private double ySum(){
        double ySum=0;
        for (int i=0; i<regressionData.size(); i++){
            ySum += regressionData.get(i).getY();  
        }
        return ySum;
    }
    private void yMean(){
        yMean= ySum() / regressionData.size();
    }
    private void XiMXmean(){
        for (int i=0; i<regressionData.size(); i++){
            XiMXmean += regressionData.get(i).getX() - xMean;
            XiMXmeanSQ += (regressionData.get(i).getX() - xMean)*(regressionData.get(i).getX() - xMean);
        }
    }
    private void YiMYmean(){
        for (int i=0; i<regressionData.size(); i++){
            YiMYmean += regressionData.get(i).getY() - yMean;
            YiMYmeanSQ += (regressionData.get(i).getY() - yMean)*(regressionData.get(i).getY() - yMean);
        }
    }
    private void computePRODXiMXmeanYiMYmean(){
        for (int i=0; i<regressionData.size(); i++){
            PRODXiMXmeanYiMYmean += (regressionData.get(i).getX() - xMean)*(regressionData.get(i).getY() - yMean);
        }
    }
    private void computeSSE(){
        for (int i=0; i<regressionData.size(); i++){
            SSE += (regressionData.get(i).getY() - (b0 + (b1*regressionData.get(i).getX())))*(regressionData.get(i).getY() - (b0 + (b1*regressionData.get(i).getX())));
        }
    }
    private void computeSSR(){
        for (int i=0; i<regressionData.size(); i++){
            SSR += ((b0 + (b1*regressionData.get(i).getX()))-yMean)*((b0 + (b1*regressionData.get(i).getX()))-yMean);
        }
    }
    private void computeSST(){
        SST = SSE + SSR;
    }
    private void computeRSQ(){
        RSQ = SSR / SST;
    }
    private void computeMSE(){
        MSE = SSE / (regressionData.size()-2);
    }
    private double computeSofB0SQ(){
        double SofB0SQ;
        SofB0SQ = MSE * ((1.0/regressionData.size())+(xMean*xMean/XiMXmeanSQ));
        return SofB0SQ;
    }
    private double computeSofB1SQ(){
        double SofB1SQ = MSE * (1/XiMXmeanSQ);
        return SofB1SQ;
    }
    private void computeTofB0(){
        TofB0 = Math.abs(b0/SofB0);
    }
    private void computeTofB1(){
        TofB1 = Math.abs(b1/SofB1);
    }
    private void computeFScore(){
        FScore = MSR/MSE;
    }   
}
