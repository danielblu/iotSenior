package br.com.theisges.iot.readFile;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


public class ReadFile {
	
	private static ReadFile instance;
	
	private File[] files;
	
	private int idxFile = 0;
	
	private Scanner input;
	
	private ReadFile() {
		String filePath = new File("").getAbsolutePath() + "/senior-data-sample";
		
		files = new File(filePath).listFiles();
		
		hasNextInput();
	}

	public static ReadFile getInstance() {
		if (instance == null) {
			instance = new ReadFile();
		}
		
		return instance;
	}
	
	private boolean hasNextInput() {
		if (idxFile < files.length) {
			
			try {
				input = new Scanner(new FileReader(files[idxFile]));
				input.next();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			idxFile++;
			return true;
		}
			
		return false;
	}
	
	public boolean hasNextData() {
		if (!input.hasNext()) {
			if (!hasNextInput()) {
				return false;
			}
		}
		
		return true;
	}
	
	public String nextData() {
		if (hasNextData()) {
			return input.next();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		while (ReadFile.getInstance().hasNextData()) {
			System.out.println(ReadFile.getInstance().nextData());
		}
	}
}
