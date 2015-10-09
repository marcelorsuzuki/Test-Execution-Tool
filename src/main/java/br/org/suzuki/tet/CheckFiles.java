package br.org.suzuki.tet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * It used to check if there are differences between two files. 
 * 
 * @author Marcelo Suzuki
 *
 */
public class CheckFiles {
	
	List<Integer> linesDiff;
    private String message;
    

    /**
     * Compare two files and check if the number of lines between files is equal and 
     * if in each line there are some differences between files.  
     *  
     * @param filename1 Name of first file.
     * @param filename2 Name of second file.
     * 
     * @return true  - Files are equals
     * 		   false - There are some differences between files
     * 
     * @throws FileNotFoundException If any file doesn't exist.
     */
    public boolean compare(String filename1, String filename2) throws FileNotFoundException {
    	return compare(filename1, filename2, new ArrayList<String>());
    }

    /**
     * Compare two files and check if the number of lines between files is equal and 
     * if in each line there are some differences between files.  
     *  
     * @param filename1 Name of first file.
     * @param filename2 Name of second file.
     * @param ignoreList List of string. If it appear at the beginner of the line, it's to ignore the line. 
     * 
     * @return true  - Files are equals
     * 		   false - There are some differences between files
     * 
     * @throws FileNotFoundException If any file doesn't exist.
     */
    public boolean compare(String filename1, String filename2, List<String> ignoreList) throws FileNotFoundException{
    	
    	File file1, file2;
    	Scanner scanner1, scanner2;
    	
    	int line = 0;
    	linesDiff = new ArrayList<Integer>();
    	
        file1 = new File(filename1);
        file2 = new File(filename2);
		scanner1 = new Scanner(file1);
		scanner2 = new Scanner(file2);
          

        while(scanner1.hasNext() && scanner2.hasNext()) {
        	
        	line++;
        	String f1 = scanner1.nextLine();
        	String f2 = scanner2.nextLine();
        	
        	boolean ignore = false;
        	
        	for (String s : ignoreList) {
        		ignore = ignore || f1.startsWith(s);
        	}
        	
        	if (!ignore) {
        		
	            if(!f1.equals(f2)) {
	            	linesDiff.add(line);
	            	message = "The files are different.";
	            }
        	}
        }   
        
        if (scanner1.hasNext()) {
        	linesDiff.add(line + 1);
        	message = "Second file is less than first file.";
        }

        if (scanner2.hasNext()) {
        	linesDiff.add(line + 1);
        	message = "First file is less than second file.";
        }
        scanner1.close();
        scanner2.close();
        
        
        //linesDiff list contain lines with differences between files 
        if (linesDiff.size() > 0) {
        	return false;
        }
        
    	message = "The files are equals.";
    	return true;
        
    }
    
    
    //Getters and setters
    public String getMessage() {
		return message;
	}
    
    public List<Integer> getLinesDiff() {
		return linesDiff;
	}
    
}