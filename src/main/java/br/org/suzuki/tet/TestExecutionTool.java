package br.org.suzuki.tet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestExecutionTool {
	
	public static void main(String[] args) {
		
		ExecuteScript script = new ExecuteScript();
		
		try {
			script.readFile(args[0]);
		}
		catch (FileNotFoundException e) {
			System.out.println("The file " + args[0] + " was not found.");
		}
		
		for (String cmd : script.getExecList()) {
			Process p;
			try {
				long start = System.currentTimeMillis();
				p = Runtime.getRuntime().exec(cmd);
				p.waitFor();
				float elapsed = ((float)(System.currentTimeMillis() - start)) / 1000.0f;
				System.out.println("Elapsed time: " + elapsed + "s");
			} 
			catch (IOException e) {
				e.printStackTrace();
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		List<String> diffList = new ArrayList<String>();
		CheckFiles checkFiles = new CheckFiles();
		for (int i = 0; i < script.getExampleList().size(); i++) {
			String f1 = script.getExampleList().get(i);
			String f2 = script.getResultList().get(i);
			try {
				if (!checkFiles.compare(f1, f2, script.getIgnoreList())) {
					diffList.add("File " + f1 + " and file " + f2 + " are different.");
				}
			}
			catch (FileNotFoundException e) {
				diffList.add("File " + f1 + " or file " + f2 + " was not found.");
			}
		}
		
		if (diffList.size() == 0) {
			System.out.println("All files are equals.");
		}
		else {
			for (String s : diffList) {
				System.out.println(s);
			}
		}
		
	}

}
