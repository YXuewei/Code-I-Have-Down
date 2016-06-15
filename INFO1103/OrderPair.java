public class OrderPair{
    private int[] numberArray;
    private int length;
    
    
    public OrderPair(int a, int b){
        this.numberArray = new int[2];
        this.length = 2;
        if(a > b){
            this.numberArray[0] = b;
            this.numberArray[1] = a;
        }else{
            this.numberArray[0] = a;
            this.numberArray[1] = b;
        }
    }
    
    public void set(int index, int a){
        if(index > 1){
            return;
        }else{
            this.numberArray[index] = a;
            return;
        }
    }
    
    public void set(int a){
        this.numberArray[0] = a;
        this.numberArray[1] = a;
        return;
    }
    
    public int getElement(int index){
        if(index > 1){
            return -1;
        }else{
            return this.numberArray[index];
        }
    }
    
    public int getIndex(int element){
        if(this.numberArray[0] == element){
            return 0;
        }else if(this.numberArray[1] == element){
            return 1;
        }else{
            return -1;
        }
    }
}