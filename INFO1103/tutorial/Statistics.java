import java.util.*;
import java.lang.*;

public class Test{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter data set:");
        ArrayList<Double> dataSet = new ArrayList<Double>();
        while( keyboard.hasNextDouble() ){
            double input = keyboard.nextDouble();
            dataSet.add(input);
        }
        System.out.println();
        if(dataSet.size() == 0){
            System.out.println("No data!");
            return;
        }else{
            double ave = mean(dataSet);
            double vari = variance(dataSet, ave);
            double standardD = sd(vari);
            System.out.print("Mean = ");
            System.out.printf("%.4f", ave);
            System.out.println();
            System.out.print("Variance = ");
            System.out.printf("%.4f", vari );
            System.out.println();
            System.out.print("Standard deviation = ");
            System.out.printf("%.4f", standardD);
            System.out.println();
        }
    }
    public static double mean(ArrayList<Double> data){
        double sum = 0;
        double mean;
        for(int i = 0; i < data.size(); i ++){
            sum = data.get(i) + sum;
        }
        mean = sum / data.size();
        return mean;
    }
    public static double variance(ArrayList<Double> data, double mean){
        double variance;
        double toThePower;
        double sumOfToThePower = 0;
        for(int i = 0; i < data.size(); i++){
            toThePower = Math.pow(data.get(i) - mean, 2);
            sumOfToThePower = sumOfToThePower + toThePower;
        }
        variance = sumOfToThePower / data.size();
        return variance;
    }
    public static double sd(double variance){
        double sd;
        sd = Math.sqrt(variance);
        return sd;
    }
}