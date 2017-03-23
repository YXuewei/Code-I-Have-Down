public class EnglishCalculator{
    public static double calculate(String s){
        String[] splited = s.split(" ");
        int len = splited.length;
        double a, b;
        if ( len != 4){
             a = Double.parseDouble( splited[0] );
             b = Double.parseDouble( splited[2] );
        }else {
             a = Double.parseDouble( splited[0] );
             b = Double.parseDouble( splited[3] );
        }
        double result = 0;
        
        if ( len == 3){
            switch (splited[1]) {
                case "times":
                    result =  a * b;
                    break;
                case "plus":
                    result =  a + b;
                    break;
                case "minus":
                    result =  a - b;
                    break;
                case "divided":
                    result =  a / b;
                    break;
            }
        }else{
            result = a / b;
        }

        return result;
    }
}