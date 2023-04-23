import java.io.FileWriter;
import java.io.IOException;

public class Translator {

	public static void main(String[] args) {
		
		
		long begin = System.currentTimeMillis();
	      //Starting the watch
		int numberOfWordsReplaced = ReadFindWords.findTranslateWords();
	      //End time
	      long end = System.currentTimeMillis();      
	     
	      long time = end-begin;
	      System.out.println();
	      System.out.println("Elapsed Time: "+time +" milli seconds");
		
		System.out.println("Total no of words translated from english to french : " + numberOfWordsReplaced);
		try {
			FileWriter writer = new FileWriter(".\\Resources\\output.performance.txt");
			writer.write("Elapsed Time: "+time +" milli seconds");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileWriter writer = new FileWriter(".\\Resources\\output.frequency.csv");
			writer.write("Total no of words translated from english to french : " + numberOfWordsReplaced);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
