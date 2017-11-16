/**
 * Created by Monika on 2017-10-15.
 */
public class Neuron {
    private double threshold = 0.5 - Math.random();
    private double[] weights = {0.8 - Math.random(), 0.8 - Math.random()};
    private double output = 0;
    private double error = 0;

    public void applyActivationFunction(double weightedSum) {output=1.0 / (1+Math.exp(-1.0 * weightedSum));}
    public  double derivative(){return output * 1.0 - output;}
    //gettery and settery

    public double getThreshold() {return threshold;}
    public void setThreshold(double threshold) {this.threshold = threshold;}
    public double[] getWeights() {return weights;}
    public double getOutput() {return output;}
    public void setOutput(double output) {this.output = output;}
    public double getError() {return error;}
    public void setError(double error) {this.error = error;}

}
