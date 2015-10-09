package br.org.suzuki.tet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Read a script file and execute each command line wrote in the file.
 * 
 * @author Marcelo Suzuki
 *
 */
public class ExecuteScript {
	
	private List<String> commentList;
	private List<String> execList;
	private List<String> exampleList;
	private List<String> resultList;
	private List<String> invalidList;
	private List<String> ignoreList;
	
	
	/**
	 * Read the script file and extract comments, exec commands, files to compare and invalid commands
	 * 
	 * @param filename Name of the script file
	 * 
	 * @throws FileNotFoundException If the file doesn't exist.
	 */
	public void readFile(String filename) throws FileNotFoundException {

		commentList = new ArrayList<String>();
		execList = new ArrayList<String>();
		exampleList = new ArrayList<String>();
		resultList = new ArrayList<String>();
		invalidList = new ArrayList<String>();
		ignoreList = new ArrayList<String>();
				
		
		File file = new File(filename);
		Scanner scanner = new Scanner(file);
		
		while (scanner.hasNext()) {
			
			String line = scanner.nextLine();
			
			//Comments
			if (line.startsWith("//")) {
				commentList.add(line.substring("//".length()).trim());
			}
			//Exec commands
			else if (line.startsWith("exec:")) {
				execList.add(line.substring("exec:".length()).trim());
			}
			//Files to compare
			else if (line.startsWith("compare:")) {
				String[] tmp = line.substring("compare:".length()).split(",");
				exampleList.add(tmp[0].trim());
				resultList.add(tmp[1].trim());
			}
			//Ignore commands
			else if (line.startsWith("ignore:")) {
				ignoreList.add(line.substring("ignore:".length()));
			}
			//Invalid commands
			else if (!line.trim().equals("")) {
				invalidList.add(line);
			}
		}
		
		scanner.close();
		
		
	}

	public List<String> getCommentList() {
		return commentList;
	}

	public List<String> getExecList() {
		return execList;
	}

	public List<String> getExampleList() {
		return exampleList;
	}

	public List<String> getResultList() {
		return resultList;
	}
	
	public List<String> getInvalidList() {
		return invalidList;
	}
	
	public List<String> getIgnoreList() {
		return ignoreList;
	}
	

}
