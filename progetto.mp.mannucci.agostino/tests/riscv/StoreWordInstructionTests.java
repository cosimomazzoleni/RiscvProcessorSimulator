package riscv;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class StoreWordInstructionTests {
	StoreWordInstruction testSW;
	
	int numberOfRegisters = 32;
	HashMap<Integer, Integer> registers;
	RegisterDesk testDesk;
	
	int numberOfCells = 128;
	HashMap<Integer, Byte> cells;
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
		int sourceValue = 110, baseAddress = 53, offset = -13;
		int expectedAddress = baseAddress + offset;
		testSW = new StoreWordInstruction(baseAddress, offset, sourceValue);
		
		testSW.execute();
		
		assertThat(testSW.addressToWrite).isEqualTo(expectedAddress);
	}

	@Test
	public void accessMemoryTest() throws IllegalAddressException {
		int sourceValue = -21, offset = 84, baseAddress = 18;
		int addressToWrite = baseAddress + offset;
		testSW = new StoreWordInstruction(baseAddress, offset, sourceValue);
		testSW.addressToWrite = addressToWrite;

		testSW.accessMemory(testMemory);

		assertThat(testMemory.memoryCells.get(addressToWrite)).isEqualTo((byte) (sourceValue & 0xff));
		assertThat(testMemory.memoryCells.get(addressToWrite+1)).isEqualTo((byte) ((sourceValue >> 8) & 0xff));
		assertThat(testMemory.memoryCells.get(addressToWrite+2)).isEqualTo((byte) ((sourceValue >> 16) & 0xff));
		assertThat(testMemory.memoryCells.get(addressToWrite+3)).isEqualTo((byte) ((sourceValue >> 24) & 0xff));
	}
}
