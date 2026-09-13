package riscv;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;

import org.junit.Test;

public class InstructionCreatorTest {
	InstructionCreator testCreator;

	@Test
	public void addCreationTest() throws UnknownOpcodeException, IllegalAddressException {
		testCreator = new AddInstructionCreator();
		int expectedDestinationRegister = 29;
		int firstRegister = 29, secondRegister = 28;
		int expectedFirstValue = 45, expectedSecondValue = -75;
		int instructionWord = 0x01ce8eb3;

		HashMap<Integer, Integer> registers = new HashMap<>();
		registers.put(firstRegister, expectedFirstValue);
		registers.put(secondRegister, expectedSecondValue);
		int deskSize = 32;
		Memory registerDesk = new RegisterDesk(registers, deskSize);

		AddInstruction addInstruction = (AddInstruction) testCreator.decodeInstructionWord(instructionWord, registerDesk);

		assertThat(addInstruction.firstValue).isEqualTo(expectedFirstValue);
		assertThat(addInstruction.secondValue).isEqualTo(expectedSecondValue);
		assertThat(addInstruction.destinationRegister).isEqualTo(expectedDestinationRegister);
	}

}