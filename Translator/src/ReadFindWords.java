import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFindWords {

	public static int findTranslateWords() {
		
		int numberOfWordsReplaced = 0;
		try {
			HashMap<String, String> findEngWordsToFrenchWordMap = new HashMap<String, String>();
			BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\Resources\\find_words.txt"));
			BufferedReader bufferedReaderForCsvFile = null;
			
	        String curLine;
	        while ((curLine = bufferedReader.readLine()) != null){
	            
	            bufferedReaderForCsvFile = new BufferedReader(new FileReader(".\\Resources\\french_dictionary.csv"));
	            String csvLine;
	            while ((csvLine = bufferedReaderForCsvFile.readLine()) != null) {
	        		String[] engAndFrenchWord = csvLine.split(",");
	        		
	        		if(curLine.equalsIgnoreCase(engAndFrenchWord[0])) {
	        			findEngWordsToFrenchWordMap.put(curLine.toLowerCase(), engAndFrenchWord[1].toLowerCase());
	        			break;
	        		}
	            }
	            
	        }
	        bufferedReader.close();
	        if(bufferedReaderForCsvFile != null) {
	        	bufferedReaderForCsvFile.close();
	        }
			
			
		System.out.println(findEngWordsToFrenchWordMap);
		
		
		numberOfWordsReplaced = replaceEngwordsWithFreshWords(findEngWordsToFrenchWordMap);
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return numberOfWordsReplaced;
	}

	private static int replaceEngwordsWithFreshWords(HashMap<String, String> findEngWordsToFrenchWordMap) {
		int numberOfWordsReplaced = 0;
		try {
			
			FileWriter writer = new FileWriter(".\\Resources\\output.t8.shakespeare.txt");
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\Resources\\t8.shakespeare.txt"));
			
			
			Pattern p = Pattern.compile("([a-zA-Z]+)");
			
			String curLine;
	        while ((curLine = bufferedReader.readLine()) != null){
	        	String outputLine = new String(curLine);
	        	
	        	String[] wordsInCurrentLine = curLine.split(" ");
	        	
	        	for(String word : wordsInCurrentLine) {
	        		Matcher m = p.matcher(word);
	        		
	        		if(m.find()) {
	        			String validWord = m.group(0);
	        			
	        			if(findEngWordsToFrenchWordMap.containsKey(validWord.toLowerCase())) {
	        				numberOfWordsReplaced++;
	        				outputLine = outputLine.replaceAll(validWord, findEngWordsToFrenchWordMap.get(validWord.toLowerCase()));
	        			}
	        		}
	        	}
	        	
	        	outputLine = outputLine + "\r\n";
	        	
	             writer.write(outputLine);


	            
	        }
			
	        writer.close();
	        bufferedReader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return numberOfWordsReplaced;
		
	}
	
	
}
