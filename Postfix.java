import java.io.*;
import java.util.*;



public class Postfix{
	private String inFile, outFile;
	public String postfix, infix;


	public Postfix(String[] args){
		if (args.length == 1) {
			inFile = args[0];
			outFile = null;
		} else if (args.length == 2) {
			inFile = args[0];
			outFile = args[1];
		} else {
			System.out.println("Usage Error: java Postfix input [output]");
			return;
		} 

		this.processFile();

	}

	public void processFile(){
		try (BufferedReader br = new BufferedReader(new FileReader(this.inFile))) {
		    String line, ans, fullAnswer, fullInfix;
		    fullInfix = new String("");
		    fullAnswer = new String("");
		    while ((line = br.readLine()) != null) {
		    	ans = this.convert(line);
				fullAnswer += ans + "\n";
				fullInfix += line + "\n";
		    }
		    this.infix = fullInfix;
		    this.postfix = fullAnswer;
    		if (this.outFile != null){
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
			} else {
				System.out.println(fullAnswer);
			}
		} catch (IOException e){
			System.out.println("Unable to open input file");
		}

	}

	public String convert(String line){
		Stack<String> stack = new Stack<String>();
		String right, left, oper;
		String[] splitLine = line.split(" ");
		for (String token : splitLine){
			if (token.equals(")")){
				right = stack.pop();
				oper = stack.pop();
				left = stack.pop();
				
				stack.push((left + " " + right + " " + oper));
			} else if (token.equals(";")) {
				break;
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