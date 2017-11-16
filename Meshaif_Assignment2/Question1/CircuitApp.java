package javaapplication2;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class CircuitApp {
    
    private static String good_string="";
    private static String bad_string="";
    public static void main(String[] argc) throws FileNotFoundException, IOException{
        String filename="data.txt"; //as given in requirments
               try{
                    System.out.println("reading file: "+ filename);
               byte[] buff = new byte[600];
               FileInputStream in_stream = new FileInputStream(filename);
                while((in_stream.read(buff)) != -1) {
            }
                String[] str=new String(buff).split("\n");
                int temp=str.length-1;
               for(int a=0;a<temp;a++)
               {
                  cal_res(str[a]); 
               }//end for
               in_stream.close();//close file
               writeGoodIntoFile(good_string);
               System.out.println("The following are bad/poor design:");
               System.out.println(bad_string);
               }//end try
               catch(FileNotFoundException ex){ //excpection of file
                   System.out.println("system is unable to open file named '" + filename + "'"); 
               }//end catch1
               catch(IOException ex) { //IOexception
                    System.out.println("there is error in reading file named '" + filename + "'");  
               }//end catch2
               
    }//end main
//other functions
    private static void cal_res(String buff){
        String [] res_arr=buff.split("\\s+"); //alpha112
        float []f=new float[6];
        for(int a=0;a<6;a++)
        {
          f[a] = Float.parseFloat(res_arr[a]);
        }//end for
        float res=((f[0]+f[1])*(f[3]*f[5]))/((f[2]+f[3])*(f[0]*f[4]));
        if(res == 7.5){
            good_string=good_string+buff+"\n";
        }//end else
        else{
            bad_string=bad_string+buff+"\n";
        }//end else
    }//end func
    
    private static void writeGoodIntoFile(String buff) throws IOException{
        System.out.println("Saving good design into file named good.txt");
        String filename = "good.txt";
        try{
            FileWriter fw= new FileWriter(filename);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.append(buff);
            bw.close();
            
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '"+ filename + "'");
        }
    }//end function
    
}//end main