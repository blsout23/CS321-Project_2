import java.io.*;
import java.util.*;

public class Assembler {

	private Postfix converter;
	private int n;
	private String assembly;
	private String[] assemblyList, postfixExpr, infixExpr;

	public Assembler(String[] args){
		if (args.length == 1 || args.length == 2) {
			this.converter = new Postfix(new String[]{args[0], "throwaway"});
		} else {
			System.out.println("Usage Error: java Assembler input [output]");
			return;
		} 

		this.assembly = "";
		this.postfixExpr = this.converter.postfix.split("\n");
		this.infixExpr = this.converter.infix.split("\n");

		this.getAssembly();
		this.assemblyList = this.assembly.split("\n\n");

		if (args.length == 1){
			for (int i = 0; i < this.postfixExpr.length; i++){
				System.out.println("Infix Expression: "+this.infixExpr[i]);
				System.out.println("Postfix Expression: "+this.postfixExpr[i]);
				System.out.println(this.assemblyList[i]);
				System.out.println("\n\n");
			}
		} else {
			this.savetTo(args[1]);
		}
	}

	public void getAssembly(){

		for (int i = 0; i < this.postfixExpr.length; i++){

			Stack<String> stack = new Stack();
			String[] splitLine = this.postfixExpr[i].split(" ");
			this.n = 1;

			String left, right;

			for (String token : splitLine){
				if (!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/")){
					stack.push(token);
				} else {
					right = stack.pop();
					left = stack.pop();	
					stack.push(this.Evaluate(left, token, right));
				}
			}
			this.assembly += "\n\n";
		}

	}

	private String Evaluate(String left, String op, String right){

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