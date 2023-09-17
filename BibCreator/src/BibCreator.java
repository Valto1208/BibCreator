import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class BibCreator {
	static Scanner sc = new Scanner(System.in);
	Scanner readFile = null;
	static PrintWriter writeFile = null;
	static int counter = -1;	
	static int invalidCounter = 0;	
	static String directoryPath = "C:./bibfiles";
	static String directoryPath2 = ".";
    static File directory = new File(directoryPath);
    static File directory2 = new File(directoryPath2);
	static File[] files = directory.listFiles();
	static File[] WrittenFiles = directory2.listFiles();
	static String nameOfFile = null;

	
	 public static void main(String[] args) throws Exception{
		 

		 System.out.println("Welcome to BibCreator!");
		 
	        if (!directory.isDirectory()) {
	        	throw new FileInvalidException(directoryPath + " is not a directory.");
	        }

	        for (int i = 0; i < files.length; i++) {
	        	counter++;
            	try (Scanner fileScanner = new Scanner(files[i])){

            	}catch (Exception e) {
            		
            		throw new FileInvalidException("This file could not be opened/created: " + nameOfFile);
            	}

            	 try {
             		 processFilesForValidation(files[i]);
             	}
             	catch (Exception e) {
             		
             	}
	        }
	       int validCounter = (counter - invalidCounter) + 1;
	        System.out.println("A total of " + invalidCounter + " files were invalid, and could not be processed. All other " + validCounter 
        			+ " \"Valid\" files have been created." );
	        
	        int attemp = 0;
	        Boolean fl = false;
	        while(attemp < 2){
	        	if (fl == true)
	        	{
	        		break;
	        	}
	        	System.out.println("Please enter the name of one of the files that you need to review: ");
		        String UserFileName = sc.next();
	        	for(File file: WrittenFiles) {
	        		String nmf = file.getName();
		        	if (UserFileName.equals(nmf)) {
		        		try{
		        			BufferedReader readFile = new BufferedReader(new FileReader(UserFileName));
		        			displayFileContent(readFile); 
		        			fl = true;
		        			break;
		        		
		        		}
		        		catch (Exception e){
		        			throw new FileInvalidException("Could not open input "
			        		 		+ "file " + UserFileName + " for reading. Please check if file exists! "
			        		 		+ "Program will terminate after closing any opened files.");
		        		}
		        	}
		        }
	        	if (fl == false)
	        	{
        		System.out.println("Could not open input "
        		 		+ "file " + UserFileName + " for reading. Please check if file exists! "
        		 		+ "Program will terminate after closing any opened files.");
        		attemp++;
	        	}
	        }
	        if (attemp == 2 )
	        {
	        	System.out.println("Maximum number of attemps reached! Exciting program");
	        	System.exit(1);
	        }
	        
	    }
	 
	 private static void displayFileContent(BufferedReader readFile) throws IOException {
			// TODO Auto-generated method stub
			
			int charr = readFile.read();
			
			while(charr != -1) {
				
				System.out.print((char)charr);
				charr = readFile.read();
			}	
		}
	 
	 
	 public static void processFilesForValidation(File file) throws Exception {   
		 Article art = null;
		 ArrayList<Article> lsa = new ArrayList<Article>();
		 try (Scanner fileScanner = new Scanner(file)) {
			 nameOfFile = file.getName();
			 while (fileScanner.hasNext()) {
				 String line = fileScanner.nextLine().trim();
				 
				 if (line.replaceAll("\\s+","").startsWith("@ARTICLE{")) {
					 art = new Article();
				 continue; 
				}
				 if (line.contains("{}")) {
					 invalidCounter++;
					 System.out.println("Error: Detected Empty Field! \n "
					 + "==============================");
					 }
				 
				 if (line.startsWith("author={")) {
					 art.setAuthor(line.substring(8, line.length() - 2));
					 if(art.getAuthor().isEmpty()) {
						 fileScanner.close();
						 throw new FileInvalidException("author", nameOfFile);
						 }
					 } else if (line.startsWith("journal={")) {
						 art.setJournal(line.substring(9, line.length() - 2));
						 if(art.getJournal().isEmpty()) {
						 fileScanner.close();
						 throw new FileInvalidException("journal", nameOfFile);
						 } 
					 }else if (line.startsWith("title={")) {
							 art.setTitle(line.substring(7, line.length() - 2));
							 if(art.getTitle().isEmpty()) {
							 fileScanner.close();
							 throw new FileInvalidException( "title", nameOfFile );
							 }
					 }else if (line.startsWith("year={")) {
								 art.setYear(Integer.parseInt(line.substring(6, line.length() - 2)));
								 if(Integer.toString(art.getYear()).isEmpty()) {// checar y isEmpty() tmb 
								fileScanner.close();
								 throw new FileInvalidException("year", nameOfFile);
								 }
					 } else if (line.startsWith("volume={")) {
						 art.setVolume(line.substring(8, line.length() - 2));
						 if(art.getVolume().isEmpty()) {
						 fileScanner.close();
						 throw new FileInvalidException("volume",nameOfFile);
						 }
					 } else if (line.startsWith("number={")) {
						 art.setNumber(Integer.parseInt(line.substring(8, line.length() - 2)));
						 if(Integer.toString(art.getNumber()).isBlank()) {
						 fileScanner.close();
						 throw new FileInvalidException("number", nameOfFile);
						 }
					 } else if (line.startsWith("pages={")) {
						 art.setPages(line.substring(7, line.length() - 2));
						 if(art.getPages().isEmpty()) {
						 fileScanner.close();
						 throw new FileInvalidException( "pages", nameOfFile);
						 }
					 } else if (line.startsWith("keywords={")) {
						 art.setKeywords(line.substring(10, line.length() - 2));
						 if(art.getKeywords().isEmpty()) {
						 fileScanner.close();
						 throw new FileInvalidException( "keywords",nameOfFile);
						 }
					 } else if (line.startsWith("doi={")) {
							 art.setDoi(line.substring(5, line.length() - 2));
							 if(art.getDoi().isEmpty()) {
							 fileScanner.close();
							 throw new FileInvalidException( "doi",nameOfFile);
							 }
					 } else if (line.startsWith("ISSN={")) {
						 art.setISSN(line.substring(6, line.length() - 2));
						 if(art.getISSN().isEmpty()) {
						 fileScanner.close();
						 throw new FileInvalidException("ISSN", nameOfFile);
						 }
					 } else if (line.startsWith("month={")) {
						 art.setMonth(line.substring(7, line.length() - 2));
						 if(art.getMonth().isEmpty()) {
						 fileScanner.close();
						 throw new FileInvalidException( "month", nameOfFile);
						 } 
				     } 
				 if(line.replaceAll("\\s+","").endsWith("}")) {
					 lsa.add(art);
				 }
				 
			 }
			 fileScanner.close();
		 }catch (FileNotFoundException e)
		 {
			 System.err.println("Could not open file: " + nameOfFile);
		 }
		 boolean flag1 = false;
		 try {
			 
			 for (Article artc: lsa)
			 {
				if (flag1 ==false)
				{
				 writeFile = new PrintWriter("IEEE" + counter + ".json", "UTF-8");
				 flag1 = true;
				 }
				 String one = artc.IEEE_Format(); 
				 writeFile.print(one); 
			 }
			 writeFile.close(); 

			}catch(Exception e1) {
				 System.out.println(e1.toString());
			 throw new FileInvalidException("IEEE" + counter + " could not be created");
			 }
		 
		 try {
			 flag1 = false;
			 int i = 0;
			 for (Article artc: lsa) {
				 i++;
				 if (flag1 ==false)
					{
					 writeFile = new PrintWriter("ACM" + counter + ".json", "UTF-8");
					 flag1 = true;
					 }
			 
			 String two = "[" + i + "] " +artc.ACM_Format();
			 writeFile.print(two); 
			}
			 writeFile.close();
			 }
			 catch(Exception e2){
			 System.out.println(e2.toString());
			 throw new FileInvalidException("ACM" + counter + " could not be created");
			 }
		 

		 try {
			 flag1 = false;
			 for (Article artc: lsa) {
				 if (flag1 ==false)
					{
						 writeFile = new PrintWriter("NJ" + counter + ".json", "UTF-8");
						 flag1 = true;
					}
				 String three = artc.NJ_Format();
				 writeFile.print(three); 
			 }
			 writeFile.close();
		 }
		 catch(Exception e3){
			 throw new FileInvalidException("NJ" + counter + " cuold not be created");
		 }
              
	
	 }
} 

