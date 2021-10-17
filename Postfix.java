import java.io.*;
import java.util.*;



public class Postfix{
	private String in_file, out_file;


	public Postfix(String[] args){
		if (args.length == 1) {
			in_file = args[0];
			out_file = null;
		} else if (args.length == 2) {
			in_file = args[0];
			out_file = args[1];
		} else {
			System.out.println("Usage Error: java Postfix input [output]");
			return;
		} 

		this.processFile();

	}

	public void processFile(){
		try (BufferedReader br = new BufferedReader(new FileReader(this.in_file))) {
		    String line, ans, fullAnswer;
		    fullAnswer = new String("");
		    while ((line = br.readLine()) != null) {
		    	ans = this.convert(line);
		    	if (this.out_file == null){
		    		System.out.println(ans);
				} else {
					fullAnswer += ans + "\n";
				}
		    }
    		if (this.out_file != null){
				// File myObj = new File(out_file);
				// myObj.close()

				try {
					FileWriter myWriter = new FileWriter(out_file);
					myWriter.write(fullAnswer);
					myWriter.close();
				}
				catch (IOException e) {
					System.out.println("Unable to open output file");
				}
				
			}
		} catch (IOException e){
			System.out.println("Unable to open input file");
		}

	}

	public String convert(String line){
		Stack<String> stack = new Stack<String>();
		String right, left, oper;
		String[] split_line = line.split(" ");
		for (String token : split_line){
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