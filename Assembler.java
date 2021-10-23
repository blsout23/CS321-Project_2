/*Ben Southwick and Dhruv Joshi
CS321 Project 2
Postfix to Assembly*/

import java.io.*;
import java.util.*;



//declare assembler class
public class Assembler {
	//postfix converter 
	private Postfix converter;
	//counter for intermediate results
	private int n;
	//string for assembly results
	private String assembly;
	//lists to store assembly results, postfix expressions, and infix expressions
	private String[] assemblyList, postfixExpr, infixExpr;
	
	//init
	public Assembler(String[] args){
		//check args length, create a new postfix converter with specified input file and keyword to disregard output file
		if (args.length == 1 || args.length == 2) {
			this.converter = new Postfix(new String[]{args[0], "throwaway"});
		//error in input
		} else {
			System.out.println("Usage Error: java Assembler input [output]");
			return;
		} 

		this.assembly = "";
		//split postfix and infix expressions by line
		this.postfixExpr = this.converter.postfix.split("\n");
		this.infixExpr = this.converter.infix.split("\n");
		
		//process input and split the results by line
		this.getAssembly();
		this.assemblyList = this.assembly.split("\n\n");
		
		//if no output file, print results
		if (args.length == 1){
			for (int i = 0; i < this.postfixExpr.length; i++){
				System.out.println("Infix Expression: "+this.infixExpr[i]);
				System.out.println("Postfix Expression: "+this.postfixExpr[i]);
				System.out.println(this.assemblyList[i]);
				System.out.println("\n\n");
			}
		//else save results to specified location
		} else {
			this.savetTo(args[1]);
		}
	}
	
/*	Converts postfix expression to assembly.
	Result is stored in assembly string for printing or saving to output. */
	
	public void getAssembly(){
		
		for (int i = 0; i < this.postfixExpr.length; i++){

			Stack<String> stack = new Stack();
			String[] splitLine = this.postfixExpr[i].split(" ");
			this.n = 1;

			String left, right;
			
			for (String token : splitLine){
				//push each token in postfix that isn't an operator onto the stack
				if (!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/")){
					stack.push(token);
				//if token is an operator, pop the last two values on the stack 
				//(right and left values) and run evaluate to convert to assembly
				} else {
					right = stack.pop();
					left = stack.pop();	
					stack.push(this.Evaluate(left, token, right));
				}
			}
			this.assembly += "\n\n";
		}
	}
	
/*	Converts from postfix to assembly given the inputs for the left variable,
	operator, and right variable. Results are added to the assembly string and
	the tempn (intermediate result) variable is returned as a string.

*/
	private String Evaluate(String left, String op, String right){
		//intermediate result variable
		String tempn = "TMP"+Integer.toString(this.n);
		this.n++;
		if (op.equals("+")){
			this.assembly += "LD "+left+"\n";
			this.assembly += "AD "+right+"\n";
			this.assembly += "ST "+tempn+"\n";
		} else if (op.equals("-")){
			this.assembly += "LD "+left+"\n";
			this.assembly += "SB "+right+"\n";
			this.assembly += "ST "+tempn+"\n";
		} else if (op.equals("*")){
			this.assembly += "LD "+left+"\n";
			this.assembly += "ML "+right+"\n";
			this.assembly += "ST "+tempn+"\n";
		} else if (op.equals("/")){
			this.assembly += "LD "+left+"\n";
			this.assembly += "DV "+right+"\n";
			this.assembly += "ST "+tempn+"\n";
		}

		return tempn;
	}
	//attempts to write outputs to specified output file
	private void savetTo(String outFile){
		try {
			FileWriter myWriter = new FileWriter(outFile);
			for (int i = 0; i < this.postfixExpr.length; i++){
				myWriter.write("Infix Expression: "+this.infixExpr[i]);
				myWriter.write("Postfix Expression: "+this.postfixExpr[i]+"\n");
				myWriter.write(this.assemblyList[i]);
				myWriter.write("\n\n");
			}
			myWriter.close();
		}
		catch (IOException e) {
			System.out.println("Unable to open output file");
		}
	}

	public static void main(String[] args){
		Assembler a = new Assembler(args);
	}
}