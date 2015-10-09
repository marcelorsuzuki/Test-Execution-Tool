package br.org.suzuki.tet;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.org.suzuki.tet.CheckFiles;

public class CompareFilesTest {
	
	private CheckFiles checkFiles;
	private String path;
	
	
	@Before
	public void init() {
		checkFiles = new CheckFiles();
		path = ClassLoader.getSystemResource(".").getPath();
	}

	@Test
	public void testFile1Less() {

		try {
			assertFalse(checkFiles.compare(path + "incomplete.txt", path + "full.txt"));
			assertEquals(checkFiles.getMessage(), "First file is less than second file.");
		}
		catch (FileNotFoundException e) {
			assertFalse(e instanceof FileNotFoundException);
		}
			
	}

	@Test
	public void testFile2Less() {
		try {
			assertFalse(checkFiles.compare(path + "full.txt", path + "incomplete.txt"));
			assertEquals(checkFiles.getMessage(), "Second file is less than first file.");
		}
		catch (FileNotFoundException e) {
			assertFalse(e instanceof FileNotFoundException);
		}
	}

	
	@Test
	public void testFilesEquals() {
		try {
			assertTrue(checkFiles.compare(path + "full.txt", path + "full2.txt"));
			assertEquals(checkFiles.getMessage(), "The files are equals.");
		}
		catch (FileNotFoundException e) {
			assertFalse(e instanceof FileNotFoundException);
		}

	}

	
	@Test
	public void testFilesDifferents() {
		try {
			assertFalse(checkFiles.compare(path + "full.txt", path + "changed.txt"));
			assertEquals(checkFiles.getMessage(), "The files are different.");
		}
		catch (FileNotFoundException e) {
			assertFalse(e instanceof FileNotFoundException);
		}
	}

	@Test
	public void testFile1NotFound() {
		try {
			checkFiles.compare(path + "aaa.txt", path + "full.txt");
			fail("First file is invalid.");
		}
		catch (FileNotFoundException e) {
			assertTrue(e instanceof FileNotFoundException);
		}
	}

	@Test
	public void testFile2NotFound() {
		try {
			checkFiles.compare(path + "full.txt", path + "aaa.txt");
			fail("Second file is invalid.");
		}
		catch (FileNotFoundException e) {
			assertTrue(e instanceof FileNotFoundException);
			
		}
	}

	@Test
	public void testFilesWithIgnoreList() {
		try {
			List<String> param = new ArrayList<String>();
			param.add("tmpfs");
			assertTrue(checkFiles.compare(path + "full.txt", path + "changed.txt", param));
			assertEquals(checkFiles.getMessage(), "The files are equals.");
		}
		catch (FileNotFoundException e) {
			assertFalse(e instanceof FileNotFoundException);
		}
	}
	
	
}
