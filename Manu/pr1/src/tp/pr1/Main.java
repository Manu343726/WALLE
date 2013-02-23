package tp.pr1;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		Interpreter interpreter = new Interpreter();
		Instruction instruction = new Instruction();
		
		reader.useDelimiter("\n");
		
		System.out.println("Introduzca una instrucción: ");
		
		instruction=interpreter.generateInstruction(reader.next());
		System.out.println(instruction);
		
		reader.close();
	}

}
