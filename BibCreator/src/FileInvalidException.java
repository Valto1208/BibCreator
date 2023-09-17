

public class FileInvalidException extends Exception {
	
	
	public FileInvalidException(String message) {
		 System.out.println(message);
	} 
	
	public FileInvalidException(String property, String file) {
		 System.out.println("Problem detected with input file: " + file  + "\nFile is invalid: Field \""+ property 
				 + "\" is Empty. Processign stoped at this point. Other empty fields maybe present as well!");
	} 
	
	public FileInvalidException() {
		 System.out.println("Could not open input file again! Either file does not exist or could not be created.\n"
				 + "Sorry! I am unable to display your desired files! Program will exit!");
	} 
	

}
