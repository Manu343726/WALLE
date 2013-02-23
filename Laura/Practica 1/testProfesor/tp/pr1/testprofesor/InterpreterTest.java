package tp.pr1.testprofesor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr1.Action;
import tp.pr1.Instruction;
import tp.pr1.Interpreter;
import tp.pr1.Rotation;

public class InterpreterTest {
	
	Interpreter p;
	
	@Before
	public void SetUp() throws Exception {
		p = new Interpreter();		
	}

	@Test
	public void testGenerateInstructionWronginstruction() {
		Instruction c = p.generateInstruction("GO");
		assertFalse("ERROR: A wrong instruction (GO) does not return an unknown instruction", c.isValid());
	}

	@Test
	public void testGenerateInstructionHelp() {
		Instruction c = p.generateInstruction("help");
		assertEquals("ERROR: String \"help\" does not return a HELP instruction", Action.HELP, c.getAction());
		
		c = p.generateInstruction("HELP");
		assertEquals("ERROR: String \"HELP\" does not return a HELP instruction", Action.HELP, c.getAction());
		
		c = p.generateInstruction("heLP");
		assertEquals("ERROR: String \"heLP\" does not return a HELP instruction", Action.HELP, c.getAction());
	}
	
	@Test
	public void testGenerateInstructionQuit() {
		Instruction c = p.generateInstruction("quit");
		assertEquals("ERROR: String \"quit\" does not return a QUIT instruction", Action.QUIT, c.getAction());
		
		c = p.generateInstruction("QUIT");
		assertEquals("ERROR: String \"QUIT\" does not return a QUIT instruction", Action.QUIT, c.getAction());
		
		c = p.generateInstruction("QuIT");
		assertEquals("ERROR: String \"QuIT\" does not return a QUIT instruction", Action.QUIT, c.getAction());
	}
	
	@Test
	public void testGenerateInstructionTurnWithoutRotation() {
		Instruction c = p.generateInstruction("turn");
		assertFalse("ERROR: String \"turn\" returns a valid instruction but it has not any rotation",c.isValid());
	}
	
	@Test
	public void testGenerateInstructionWithWrongRotation() {
		Instruction c = p.generateInstruction("turn dcha");
		assertEquals("ERROR: String \"turn dcha\" does not return a TURN instruction", Action.TURN, c.getAction());
		assertFalse("ERROR: String \"turn dcha\" returns a valid instruction but the rotation is not correct",c.isValid());
	}
	
	@Test
	public void testGenerateInstructionTurnWithCorrectRotation() {
		Instruction c = p.generateInstruction("TurN left");
		assertTrue("ERROR: String \"TurN left\" is a valid instruction",c.isValid());
		assertEquals("ERROR: String \"TurN left\" does not return a TURN instruction", Action.TURN, c.getAction());
		assertEquals("ERROR: String \"TurN left\" has a rotation different of LEFT", Rotation.LEFT, c.getRotation());
	}
	
	@Test
	public void testHelp() {
		String help = p.interpreterHelp();
		help.toUpperCase();
		assertTrue("ERROR: interpreterHelp returns a description which does not contain information about instruction MOVE", help.contains("MOVE"));
		assertTrue("ERROR: interpreterHelp returns a description which does not contain information about instruction TURN", help.contains("TURN"));
		assertTrue("ERROR: interpreterHelp returns a description which does not contain information about instruction HELP", help.contains("HELP"));
		assertTrue("ERROR: interpreterHelp returns a description which does not contain information about instruction QUIT", help.contains("QUIT"));
	}
}
