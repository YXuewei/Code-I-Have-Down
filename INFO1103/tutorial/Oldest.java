import java.util.*;
import java.lang.*;

public class Oldest{
    public static void main(String[] args){
        Pet p = new Pet("Steven",2,"Husky",true);
        Pet p2 = new Pet("Steven",1,"Husky",false);
        Pet p3 = new Pet("Matt",4,"Golden Retriver",true);
        Pet p4 = new Pet("Coke",1,"Alaska",true);
        ArrayList<Pet> array = new ArrayList<Pet>();
        array.add(p);
        array.add(p2);
        array.add(p3);
        array.add(p4);
        int oldest = 0;
        for(int i = 1; i < 4; i++){
            if( array.get(i - 1).getAge() > array.get(i).getAge()){
                continue;
            }else{
                oldest = i;
            }
        }
        System.out.println(array.get(oldest).getName() + "\n" + 
                          array.get(oldest).getAge() + "\n" + 
                          array.get(oldest).getSpecies() + "\n" +
                          array.get(oldest).getHouseTrained());
    }
}