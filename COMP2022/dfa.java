
public class Dfa{
    public static class State{
        public boolean finalState;
        public String accept;
        public String to;

        public State(boolean state, String accept,String to){
            finalState = state;
            accept = accept;
            to = to;
        } 
    }
    public static void main(String[] args){
        if ( args.length == 1){
            State[] dfa = new State[4];
            State q0 = new State(false, "0","1");
            State q1 = new State(true, "01","12");
            State q2 = new State(true,"10","23");
            State q3 = new State(true,"01","12");
            dfa[0] = q0;
            dfa[1] = q1;
            dfa[2] = q2;
            dfa[3] = q3;

            char input;
            int currentState = 0;
            for (int i = 0; i < args.length; i++ )
            {
                input = args[0].charAt(i);
                for ( int l = 0; l < i; l ++ )
                {
                    System.out.printf(args[l]);
                }
                System.out.print(" ");
                System.out.printf("q%d -- %c --> ", currentState, input);
                for ( int j = 0; j < dfa[currentState].accept.length(); j++ )
                {
                    if ( input == dfa[currentState].accept.charAt(j) )
                    {
                        currentState = dfa[currentState].to.charAt(j);
                        break;
                    }
                }
                System.out.printf("q%d ",currentState);
                for ( int k = i; k < args.length; k++ )
                {
                    System.out.printf(args[k]);
                }
                System.out.printf("\n");
            }

            if ( dfa[currentState].finalState == true)
            {
                System.out.println("Accepted");
            }
            else
            {
                System.out.println("Rejected");
            }
        }
    }
}