package riscv;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class LoadWordInstructionTests {
	LoadWordInstruction testLW;
	
	int numberOfRegisters = 32;
	HashMap<Integer, Integer> registers;
	RegisterDesk testDesk;
	
	int numberOfCells = 128;
	HashMap<Integer, Integer> cells;
	RAM testMemory;

	@Before
	public void setup() {
		registers = new HashMap<>();
		testDesk = new RegisterDesk(registers, numberOfRegisters);

		cells = new HashMap<>();
		testMemory = new RAM(cells, numberOfCells);
	}

	@Test
	public void executionTest() {
		int dstRegister = 14, baseAddress = 253, offset = 23;
		int expectedAddress = baseAddress + offset;
		testLW = new LoadWordInstruction(dstRegister, offset, baseAddress);
		
		testLW.execute();
		
		assertThat(testLW.addressToRead).isEqualTo(expectedAddress);
	}

	@Test
	public void accessMemoryTest() throws StageNotRequiredException, IllegalAddressException {
		int dstRegister = 26, baseAddress = 118, offset = -43;
		int expectedValue = 173;
		testMemory.memoryCells.put(baseAddress + offset, expectedValue);
		testLW = new LoadWordInstruction(dstRegister, offset, baseAddress);
		testLW.addressToRead = baseAddress + offset;

		testLW.accessMemory(testMemory);

		assertThat(testLW.readValue).isEqualTo(expectedValue);
	}
	
	@Test
	public void writeBackTest() throws IllegalAddressException, StageNotRequiredException {
		int dstRegister = 11, baseAddress = 129, offset = -4;
		int expectedValue = 228;
		testLW = new LoadWordInstruction(dstRegister, offset, baseAddress);
		testLW.readValue = expectedValue;
		
		testLW.writeBack(testDesk);
		
		assertThat(testDesk.registers.get(dstRegister)).isEqualTo(expectedValue);
	}
}
