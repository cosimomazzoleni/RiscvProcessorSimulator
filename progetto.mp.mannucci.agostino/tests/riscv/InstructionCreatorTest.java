package riscv;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class InstructionCreatorTest {
	InstructionCreator testCreator;
	HashMap<Integer, Integer> registers;
	Memory registerDesk;

	@Before
	public void setup() {
		registers = new HashMap<>();
		int deskSize = 32;
		registerDesk = new RegisterDesk(registers, deskSize);
		
		
	}

	@Test
	public void addCreationTest() throws UnknownOpcodeException, IllegalAddressException {
		testCreator = new AddInstructionCreator();
		int expectedDestinationRegister = 29;
		int firstRegister = 29, secondRegister = 28;
		int expectedFirstValue = 45, expectedSecondValue = -75;
		int instructionWord = 0x1ce8eb3;

		registers.put(firstRegister, expectedFirstValue);
		registers.put(secondRegister, expectedSecondValue);

		AddInstruction addInstruction = (AddInstruction) testCreator.decodeInstructionWord(instructionWord, registerDesk);

		assertThat(addInstruction.firstValue).isEqualTo(expectedFirstValue);
		assertThat(addInstruction.secondValue).isEqualTo(expectedSecondValue);
		assertThat(addInstruction.destinationRegister).isEqualTo(expectedDestinationRegister);
	}

	@Test
	public void subCreationTest() throws UnknownOpcodeException, IllegalAddressException {
		testCreator = new SubInstructionCreator();
		int expectedDestinationRegister = 15;
		int firstRegister = 16, secondRegister = 29;
		int expectedFirstValue = 84, expectedSecondValue = 21;
		int instructionWord = 0x41d807b3;

		registers.put(firstRegister, expectedFirstValue);
		registers.put(secondRegister, expectedSecondValue);

		SubInstruction subInstruction = (SubInstruction) testCreator.decodeInstructionWord(instructionWord, registerDesk);

		assertThat(subInstruction.firstValue).isEqualTo(expectedFirstValue);
		assertThat(subInstruction.secondValue).isEqualTo(expectedSecondValue);
		assertThat(subInstruction.destinationRegister).isEqualTo(expectedDestinationRegister);
	}

	@Test
	public void loadCreationTest() throws UnknownOpcodeException, IllegalAddressException {
		testCreator = new LoadWordInstructionCreator();
		int expectedDestinationRegister = 11;
		int sourceRegister = 12, offset = 13;
		int addressValue = 102;
		int instructionWord = 0xd62583;
		registers.put(sourceRegister, addressValue);

		LoadWordInstruction loadWordInstruction = (LoadWordInstruction) testCreator.decodeInstructionWord(instructionWord, registerDesk);

		assertThat(loadWordInstruction.baseAddress).isEqualTo(addressValue);
		assertThat(loadWordInstruction.offset).isEqualTo(offset);
		assertThat(loadWordInstruction.destinationRegister).isEqualTo(expectedDestinationRegister);
	}
}