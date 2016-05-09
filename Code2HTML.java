// --------------------------------- JgilleCh112 ----------------------------------
// 	Programmer's Name: Jake Gillenwater
//	Course:  		CISP1020		Section Number: A01
//	Creation Date:	4/30/16			Date of Last Modification:	5/1/16
//	e-mail address: JakeGill70@Gmail.com
//  phone number: (1-423)-438-6828
//	address: 1122 Qualls Road, Kingsport, TN 37660
// --------------------------------------------------------------------------------
//	Purpose - Replace special characters in code with HTML escape characters
//
// --------------------------------------------------------------------------------
//	Input  - kb - Keyboard Scanner
//			inputFile - dataFile Scanner
//
//  Process- Read the file, check each character, replace as needed, write to a new file
//
//	Output - 
//			Command Prompt:
//				"Please enter the file name and extension of the file you would like to convert: "
//				<Input File Name> + "_asHTML.html"
//				"Done."
//				"Saved as: 	" + <Output File Name>
//			File Output:
//				Same as code input, except: 
//					replace All "\t" with "    "
//					replace All "<" with "&lt;"
//					replace All ">" with "&gt;"
//					replace All "\\Q[\\E" with "&#91;"
//					replace All "\\Q]\\E" with "&#93;"
//					replace All "\\s" with "&nbsp;"
//		
//
// ----------------------------------------------------------------------------------
//	Identifier dictionary - name, description, type of each variable and object
//		resultsFileName 	- 	Default file name of results file 		- final String 
//		SENTIAL_VALUE 		-	Sential value to represent the end 
//								of a student's grade - final double		- final double
//		
//		dataFile 			-	Opens the data file which allows		
//								This allows us to pass it to a 
//								scanner for reading data				- File
//		
//		kb 					- 	Used for Reading keyboard input			- Scanner 
//		inputFile			- 	Allows us to read from the dataFile 	- Scanner
//      currentLine 		-	The last line of code from the datafile
//								that was read by the scanner			- String
//		
//		outputFile			-	Allows us to write to a results file 	- PrintWriter
//	
// ----------------------------------------------------------------------------------
//	Notes on specifications, special algorithms, and assumptions. - none
//
// ----------------------------------------------------------------------------------
//  Amount of time spent on UML/Documentation (mins) - 25
//	Amount of time spent coding and debugging (mins) - 60
// ----------------------------------------------------------------------------------

// Test Characters:

// [ ]
// [
// ]

// >

// <

import java.util.Scanner;
import java.io.*;

public class Code2HTML{
	public static void main(String[] args) throws IOException, FileNotFoundException{
	
		String resultsFileName = "results.html";		// Default file name of results file
		
		File dataFile = new File("Data.file");		// Opens the data file which allows
														//		This allows us to pass it to a scanner for reading data
		
		Scanner kb = new Scanner(System.in); 		// Reading keyboard input
		Scanner inputFile = null;					// Allows us to read from the dataFile
		PrintWriter outputFile = null;				// Allows us to write to a results file
		
		String currentLine = "";					// The last line of code read by the scanner
		
		do {
			System.out.println("Please enter the file name and extension of the file you would like to convert: ");
			// Attempt to open entered file name
			dataFile = new File(	formatUserInput(kb.nextLine())	); 
		} while(!dataFile.exists());
		
		
		// Create scanner to read through the data file
		inputFile = new Scanner(dataFile);
		// Create a print writer to write/save to a results file
		outputFile = new PrintWriter(formatOutputFileName(dataFile.getName()) + "_asHTML.html");
			
		do{
			currentLine = inputFile.nextLine();
			
			currentLine = formatFileInput(currentLine);
			
			outputFile.println(currentLine);
			
		}while(inputFile.hasNext());
		
		// Close the output file to save changes
		outputFile.close();
		System.out.println("Done.\nSaved as: \t" + formatOutputFileName(dataFile.getName()) + "_asHTML.html");
	}

	public static String formatUserInput(String str){
		str = str.trim();
		if(str.contains(".")){
			return str;
		}
		else{
			str = str + ".txt";
			return str;
		}
	}	
	
	public static String formatFileInput(String str){
		str = str.replaceAll("\t", "    ");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\\Q[\\E", "&#91;");
		str = str.replaceAll("\\Q]\\E", "&#93;");
		str = str.replaceAll("\\s", "&nbsp;");
		
		return str + "<br>";
	}	
	
	public static String formatOutputFileName(String str){
		return str.split("\\Q.\\E")[0];
	}
}