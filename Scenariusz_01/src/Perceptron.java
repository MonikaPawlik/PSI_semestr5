/**
 * Created by Monika on 2017-10-14.
 */
public class Perceptron {
    public static final int [][][] andData = {{{0,0},{0}},
                                              {{0,1},{0}},
                                              {{1,0},{0}},
                                              {{1,1},{1}}};
    public static final double learning_rate = 0.8;
    public static final double[] initial_weights = { 0.5 - Math.random(),0.5 -  Math.random()};
    public double calculateWeightedSum( int[] data, double[] weights)
    {
        double weightedSum =0;
        for(int x=0;x<data.length; x++) {
            weightedSum +=data[x]*weights[x];
        }
        return weightedSum;
    }
    public int applyActivationFunction(double weightedSum) //zastosować aktywację funkcji
    {
        int result=0;
        if(weightedSum > 1)
            result = 1;
        return result;
    }
    public double[] adjustedWeights(int[] data, double[] weights, double error) //dostosuj wagi  uczenie sieci
    {
        double[] adjustedWeights = new double[weights.length];
        for(int x=0;x<weights.length;x++){
            adjustedWeights[x]= learning_rate * error * data[x] + weights[x];
        }
        return adjustedWeights;
    }


}
