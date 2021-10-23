/*Ben Southwick and Dhruv Joshi
CS321 Project 2
Postfix Converter*/

import java.io.*;
import java.util.*;



//declare postfix class
public class Postfix{
	//input and output file names
	private String inFile, outFile;
	//postfix and infix expressions
	public String postfix, infix;
	
	//init
	public Postfix(String[] args){
		//no output file specified
		if (args.length == 1) {
			inFile = args[0];
			outFile = null;
		//output file specified
		} else if (args.length == 2) {
			inFile = args[0];
			outFile = args[1];
		//error with args
		} else {
			System.out.println("Usage Error: java Postfix input [output]");
			return;
		} 
		this.processFile();
	}

/*  Reads through input file, converting each line to postfix.
    Answer is saved to specified output file, printed, or disregarded
    depending on command line input. */
	   
	public void processFile(){
		//create reader from input file
		try (BufferedReader br = new BufferedReader(new FileReader(this.inFile))) {
		    String line, ans, fullAnswer, fullInfix;
		    fullInfix = new String("");
		    fullAnswer = new String("");
			//while we're not at the end of the file...
		    while ((line = br.readLine()) != null) {
				//convert the line to postfix, add that to the answer and the original line to the infix
		    	ans = this.convert(line);
				fullAnswer += ans + "\n";
				fullInfix += line + "\n";
		    }
			//update global variables
		    this.infix = fullInfix;
		    this.postfix = fullAnswer;
			//if there is a specified output file...
    		if (this.outFile != null){
				//if the keyword was not used, store answer in output file
				//"throwaway" causes output not to be stored or printed (for use in Assembler)
    			if (this.outFile != "throwaway") { 
					try {
						FileWriter myWriter = new FileWriter(outFile);
						myWriter.write(fullAnswer);
						myWriter.close();
					}
					catch (IOException e) {
						System.out.println("Unable to open output file");
					}
    			}
			//if no output file, print the postfix
			} else {
				System.out.println(fullAnswer);
			}
		} catch (IOException e){
			System.out.println("Unable to open input file");
		}
	}

	//Converts given string formatted as an infix expression to a string as a postfix expression.
	public String convert(String line){
		Stack<String> stack = new Stack<String>();
		String right, left, oper;
		//split input line by spaces
		String[] splitLine = line.split(" ");
		for (String token : splitLine){
			//at closed parentheses, pop last three items (right term, operator, and left term)
			if (token.equals(")")){
				right = stack.pop();
				oper = stack.pop();
				left = stack.pop();
				//push the complete segment to the stack in postfix notation
				stack.push((left + " " + right + " " + oper));
			//break at the end of expression
			} else if (token.equals(";")) {
				break;
			//when token is not open parentheses, push to stack
			} else if ( !token.equals("(") ) {
				stack.push(token);
			} 
		}
		return stack.pop();
	}

	public static void main(String[] args){
		Postfix converter = new Postfix(args);
	}
}