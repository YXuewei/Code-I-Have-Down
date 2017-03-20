import java.util.*;
import java.lang.*;

public class Pet{
   String name;
   ArrayList<String> nickNames;
   int age;
   String species;
   String houseTrianed;
   public Pet(String n,ArrayList<String> nickN,int old,String type,String trained){
    name = n;
    nickNames = nickN;
    age = old;
    species = type;
    houseTrianed = trained;     
   }
   public Pet(String n,int a, String s){
       name = n;
       age = a;
       species = s;
   }
   public Pet(String n,int a,String s,String t){
        name = n;
        age = a;
        species = s;
        houseTrianed = t;       
   }
   public Pet(String n){
       name = n;
   }
   public void setName(String n){
       this.name = n;
       return;
   }
   public void setAge(int a){
       this.age = a;
       return;
   }
   public ArrayList<String> setNickNames(String[] n){
        int length = n.length;
        ArrayList<String> nickNames = new ArrayList<String>();
        for(int i = 0; i < length;i++){
            nickNames.add(n[i]);
       }
        return nickNames;
   }
   public void setSpecies(String s){
       this.species = s;
       return;
   }
   public void setHouseTrained(String trianed){
       this.houseTrianed = trianed;
       return;
   }
   public String getName(){
       return this.name;
   }
   public int getAge(){
       return this.age;
   }
   public ArrayList<String> getNickNames(){
       return this.nickNames;
   }
   public String getSpecies(){
       return this.species;
   }
   public String getHouseTrained(){
       return this.houseTrianed;
   }
   public boolean equalc Pet pet){
       if(this.getName().equals( pet.getName() ) 
            && this.getAge() == pet.getAge()
            && this.getSpecies() == pet.getSpecies() ){
                return true;
       }else{
           return false;
       }
   }
   public void addNickName(String n){
       int length = this.nickNames.size();
       for(int i = 0;i < length; i++){
           if(this.nickNames.get(i).equals(n) ){
               return;
           }
       }
       this.nickNames.add(n);
       return;
   }
   public boolean hasNickname(String n){
       int length = this.nickNames.size();
       for(int i = 0; i < length; i++){
           if(this.nickNames.get(i).equals(n)){
               return true;
           }
       }
       return false;
   }
}