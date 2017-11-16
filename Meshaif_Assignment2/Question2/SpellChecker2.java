
package spellchecker2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class SpellChecker2 {

   /**
    * This method takes in a filename and spells check it	
    * @param filename an input file stream (i.e. bonk.txt)	
    * @return ArrayList<String> list of words not found in dictionary 
    */
    public ArrayList<String> spell(String filename) {
	// method body
        System.out.println("Reading input file: " + filename);
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            BufferedReader reader1 = new BufferedReader(new FileReader("dictionary.txt"));
            
            String line;
            String spellCheckFile = "";
            String dictionaryfile = ""; 
     
            while ((line = reader.readLine()) != null)
            {
              spellCheckFile = spellCheckFile +  line + "\n";
            }
            while ((line = reader1.readLine()) != null)
            {
              dictionaryfile = dictionaryfile + line + "\n";
            }
            //System.out.println(dictionaryfile);
            String [] dictonary =  dictionaryfile.split("\\R+");
            String [] spelCheckWords = spellCheckFile.replaceAll("[^a-zA-Z]+", " ").split(" "); 
            ArrayList<String> badWords = new ArrayList<>();
            int index = 0;
            while(index < spelCheckWords.length){
                if(!inDictionary(spelCheckWords[index], dictonary)){
                    badWords.add(spelCheckWords[index]);
                    index++;
                }
                else{
                    index++;
                }
            }       
            reader.close();
            return badWords;
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }


   /**
    * This method takes the list of words not found in the dictionary	
    * and prints a list of non-duplicate words
    * @param ArrayList<String> List of words not found in dictionary	
    */
    public void printNonDuplicates(ArrayList<String> array ) {
	// method body
        ArrayList<String> newArray = new ArrayList<>();
        int index = 0;
        while(index < array.size()){
            if(!newArray.contains(array.get(index))){
                System.out.println(array.get(index));
                newArray.add(array.get(index));
                index++;
            }
            else
                index++;
        }
        
    }
   
   /**
    * This method returns true if a word in input file stream	
    * (i.e. bonk.txt) is in the dictionary, false otherwise.	
    * @param word a string that contains a word to be checked	
    * @return the boolean status of the word (i.e. true or false)	
    */
    public boolean inDictionary(String word, String[] dictionary) {
	// method body
        int index = 0;
        while(index < dictionary.length){
            if(word.equalsIgnoreCase(dictionary[index])){
                return true;
            }
            index++;
        }
        return false;
    }

    public static void main(String[] args) {
        // Prompt the user to enter a file to be spell checked
        String filename = null;
        if(args.length > 0){
          filename = args[0];
        }
        SpellChecker2 sc = new SpellChecker2();
        ArrayList<String> badWords = sc.spell("bonk.txt");
        sc.printNonDuplicates(badWords);
    }
}