/**
 * Created by Monika on 2017-10-14.
 */
public class Driver {

    public static void main(String[] args) {
        int[][][] data = Perceptron.andData;
        double[] weights = Perceptron.initial_weights;
        Driver driver = new Driver();
        Perceptron perceptron = new Perceptron();
        int epochNumber = 0;
        boolean errorFlag = true;
        double error = 0;
        double[] adjustedWeights = null;

        int iteration  = 1000;
        for(int j=0;j<iteration;j++)
        {
            driver.printHeading(epochNumber++);
            errorFlag=false;
            error=0;
            for( int i=0;i<data.length;i++){
                double weightedSum = perceptron.calculateWeightedSum(data[i][0],weights);
                int result = perceptron.applyActivationFunction(weightedSum);
                error = data[i][1][0] - result;
              //  if(error != 0)
                  //  errorFlag = true;
                adjustedWeights = perceptron.adjustedWeights(data[i][0],weights,error);
                driver.printVector(data[i],weights,result,error,weightedSum,adjustedWeights);
                weights = adjustedWeights;
            }
        }

    }


    public void printHeading(int epochNumber){ //wydrukuj nagłówek
        System.out.println("\n Epoch # "+epochNumber);
        //System.out.println("  w1  |  w2  |  x1  |  x2 |  target result | result | error | weighted sum | adjusted w1 | adjusted w2");
        System.out.println("    w1    |     w2     |  result |  error | weighted sum | adjusted w1 | adjusted w2");
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }
    public void printVector(int[][] data, double[] weights, int result, double error, double weightedSum, double[] adjustedWeights){

        //System.out.println(" "+String.format("%.2f",weights[0])+" | "+String.format("%.2f",weights[1])+" | "+data[0][0]+" | "+data[0][1]+" | "+data[1][0]+" | "+data[1][1]+" | "+result+" | "+error+" | "+String.format("%.2f",weightedSum)+" | "+String.format("%.2f",adjustedWeights[0])+" | "+String.format("%.2f",adjustedWeights[1]) );
        System.out.println(" "+String.format("%.5f",weights[0])+" |   "+String.format("%.5f",weights[1])+"  |    "+result+"    |   "+error+"  |    "+String.format("%.2f",weightedSum)+"      |     "+String.format("%.2f",adjustedWeights[0])+"    |   "+String.format("%.2f",adjustedWeights[1]) );

    }


}
