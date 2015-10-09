package br.org.suzuki.tet;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import br.org.suzuki.tet.ExecuteScript;
import static org.junit.Assert.*;

public class ExecuteScriptTest {

	private String path;
	
	@Before
	public void init() {
		path = ClassLoader.getSystemResource(".").getPath();
	}
	
	@Test
	public void testReadComment() {
		
		try {
			ExecuteScript script = new ExecuteScript();
			script.readFile(path + "correct.script");
			
			assertEquals(2, script.getCommentList().size());
			assertEquals("This line is just a comment and will be ignored. Blank lines will be ignored, too.", script.getCommentList().get(0));
			assertEquals("Ignore the lines that start with the strings below.", script.getCommentList().get(1));
		}
		catch (FileNotFoundException e) {
			assertFalse(e instanceof FileNotFoundException);
		}
	}

	@Test
	public void testReadExec() {
		
		try {
			ExecuteScript script = new ExecuteScript();
			script.readFile(path + "correct.script");
			assertEquals(5, script.getExecList().size());
			assertEquals("echo \"This string is equal in f1.txt and f2.txt\" > f1.txt", script.getExecList().get(0));
			assertEquals("echo \"This string is equal in f1.txt and f2.txt\" > f2.txt", script.getExecList().get(1));
			assertEquals("echo \"This string is different in f4.txt\" > f3.txt", script.getExecList().get(2));
			assertEquals("echo \"This string is different in f3.txt\" > f4.txt", script.getExecList().get(3));
			assertEquals("echo \"     *Ignore in f5.txt\" > f5.txt", script.getExecList().get(4));
		}
		catch (FileNotFoundException e) {
			assertFalse(e instanceof FileNotFoundException);
		}
	}

	@Test
	public void testReadIgnore() {
		
		try {
			ExecuteScript script = new ExecuteScript();
			script.readFile(path + "correct.script");
			assertEquals(2, script.getIgnoreList().size());
			assertEquals("     *", script.getIgnoreList().get(0));
			assertEquals(" TOTAL CPU TIME:", script.getIgnoreList().get(1));
		}
		catch (FileNotFoundException e) {
			assertFalse(e instanceof FileNotFoundException);
		}
	}

	
	@Test
	public void testReadCompare() {
		
		try {
			ExecuteScript script = new ExecuteScript();
			script.readFile(path + "correct.script");
			
			assertEquals(2, script.getExampleList().size());
			assertEquals("f1.txt", script.getExampleList().get(0));
			assertEquals("f3.txt", script.getExampleList().get(1));
			
			assertEquals(2, script.getResultList().size());
			assertEquals("f2.txt", script.getResultList().get(0));
			assertEquals("f4.txt", script.getResultList().get(1));
		}
		catch (FileNotFoundException e) {
			assertFalse(e instanceof FileNotFoundException);
		}
	}
	

	@Test
	public void testInvalidCommand() {
		
		try {
			ExecuteScript script = new ExecuteScript();
			script.readFile(path + "invalid_command.script");
			assertEquals(1, script.getInvalidList().size());
			assertEquals("aaa", script.getInvalidList().get(0));
		}
		catch (FileNotFoundException e) {
			assertFalse(e instanceof FileNotFoundException);
		}
	}

}
