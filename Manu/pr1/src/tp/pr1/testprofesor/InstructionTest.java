package tp.pr1.testprofesor;

import static org.junit.Assert.*;

import org.junit.Test;

import tp.pr1.Action;
import tp.pr1.Instruction;
import tp.pr1.Rotation;


public class InstructionTest {

	@Test
	public void testIsValid() {
		Instruction c = new Instruction();
		assertFalse("ERROR: An instruction created with default constructor is not a valid instruction",
				c.isValid());
		
		c = new Instruction(Action.TURN);
		assertFalse("ERROR: A TURN instruction without rotation is not a valid instruction",
				c.isValid());
		
		c = new Instruction(Action.MOVE);
		assertTrue("ERROR: A instruction created with a action (MOVE) is a valid instruction",
				c.isValid());
		
		c = new Instruction(Action.HELP);
		assertTrue("ERROR: A instruction created with a action (QUIT) is a valid instruction",
				c.isValid());
		
		c = new Instruction(Action.QUIT);
		assertTrue("ERROR: A instruction created with a action (QUIT) is a valid instruction",
				c.isValid());
	}

	@Test
	public void testGetAction() {
		Instruction c = new Instruction(Action.MOVE);
		assertEquals("ERROR: A instruction created with the action MOVE does not return the correct action (MOVE)",
				c.getAction(), Action.MOVE);
	}

	@Test
	public void testGetDirection(){
		Instruction c = new Instruction(Action.TURN, Rotation.LEFT);
		assertEquals("ERROR: A instruction configured with a direction should return the correct direction when executing getDirection method",Rotation.LEFT, c.getRotation());
	}

}
