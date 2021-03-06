/**
 * Created by Monika on 2017-12-06.
 */
public class HebbianLearning {


    public class HebbRuleNeuralNetwork
    {

        public static final char[] letters = {'A','B','C','D','E','F','G','H','I','J','M','N','O','P','R','S','T','U','W','X'};
        public static final int[][] learning_letters = {
                {0,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1},
                {1,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,1,1,1,0},
                {0,1,1,1,0,1,0,0,0,1,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,1,0,1,1,1,0},
                {1,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,1,1,1,0},
                {1,1,1,1,1,1,0,0,0,0,1,0,0,0,0,1,1,1,1,0,1,0,0,0,0,1,0,0,0,0,1,1,1,1,1},
                {1,1,1,1,1,1,0,0,0,0,1,0,0,0,0,1,1,1,1,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0},
                {0,1,1,1,0,1,0,0,0,1,1,0,0,0,0,1,0,1,1,1,1,0,0,0,1,1,0,0,0,1,0,1,1,1,0},
                {1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1},
                {0,1,1,1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,1,1,1,0},
                {1,1,1,1,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,1,0,0,0,1,0,1,1,1,0},
                {1,0,0,0,1,1,1,0,1,1,1,0,1,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1},
                {1,0,0,0,1,1,0,0,0,1,1,1,0,0,1,1,0,1,0,1,1,0,0,1,1,1,0,0,0,1,1,0,0,0,1},
                {0,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,0,1,1,1,0},
                {1,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,1,1,1,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0},
                {1,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,1,1,1,0,1,0,1,0,0,1,0,0,1,0,1,0,0,0,1},
                {0,1,1,1,0,1,0,0,0,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,0,0,1,0,1,1,1,0},
                {1,1,1,1,1,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0},
                {1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,0,1,1,1,0},
                {1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,1,0,1,1,0,1,0,1,0,1,0,1,0},
                {1,0,0,0,1,1,0,0,0,1,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,1,1,0,0,0,1}
        };
        private int[] inputs;
        private double[] weights;
        private double[] outputs;
        private int maxCells;
        private double epsilon;

        public HebbRuleNeuralNetwork(int cellCount)
        {
            inputs = new int[cellCount];
            weights = new double[cellCount,cellCount];
            outputs = new double[cellCount];
            maxCells = cellCount;
            epsilon = .3;
            resetValues();
        }

        public string printWeights()
        {
            int maxValueSize = 2;

            for (int index = 0; index < maxCells; index++)
            {
                for (int i = 0; i < maxCells; i++)
                {
                    string s = weights[index, i].ToString();
                    if (s.Length > maxValueSize)
                        maxValueSize = s.Length;
                }
            }


            StringBuilder dataBuilder = new StringBuilder();
            dataBuilder.Append("      ");
            for (int i = 0; i < maxCells; i++)
                dataBuilder.Append(makeRightSize(i.ToString(), maxValueSize)).Append("  ");
            dataBuilder.Append("\n");
            for (int index = 0; index < maxCells; index++)
            {
                if (index < 10)
                    dataBuilder.Append("#").Append(index.ToString()).Append("    ");
                else
                    dataBuilder.Append("#").Append(index.ToString()).Append("   ");

                for (int i = 0; i < 16; i++)
                    dataBuilder.Append(makeRightSize(weights[index, i].ToString(), maxValueSize)).Append("  ");

                dataBuilder.Append("\n");
            }

            return dataBuilder.ToString();
        }

        private string makeRightSize(string number, int size)
        {
            StringBuilder sb = new StringBuilder();
            for (int i = number.Length; i < size; i++)
                sb.Append(' ');
            sb.Append(number);
            return sb.ToString();
        }

        public bool isOutputClicked(int i)
        {
            return outputs[i] == 1;
        }

        public double getWeight(int i, int j)
        {
            return weights[i, j];
        }


        public void resetValues()
        {
            for (int i = 0; i < inputs.Length; i++)
                inputs[i] = 0;
            for (int i = 0; i < outputs.Length; i++)
                outputs[i] = 0.0;
            for (int index = 0; index < maxCells; index++)
                for (int i = 0; i < maxCells; i++)
                    weights[index,i] = 0.0;
        }

        public void definePattern(int[] inp)
        {
            for (int i = 0; i < maxCells; i++)
            {
                inputs[i] = inp[i];
                outputs[i] = (double)inp[i];
            }
        }

        public void computeActivations(int adjustWeights)
        {
            for (int i = 0; i < maxCells; i++)
            {
                outputs[i] = 0.0;
                for(int weight = 0; weight<maxCells; weight++)
                    outputs[i] += (weights[i,weight] * (double)inputs[weight]);

                //Click the outputs
                if (outputs[i] >= 0.0)
                    outputs[i] = 1.0;
                else
                    outputs[i] = -1.0;
            }
        }

        public void adjustWeights()
        {
            for (int i = 0; i < maxCells; i++)
                for (int weight = 0; weight < maxCells; weight++)
                    weights[i,weight] += epsilon * (outputs[i] * (double)inputs[weight]);
        }
    }
}
