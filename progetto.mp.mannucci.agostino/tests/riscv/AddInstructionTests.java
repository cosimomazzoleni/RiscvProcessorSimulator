package riscv;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class AddInstructionTests {
	AddInstruction testAdd;

	@Test
	public void executionTest() {
		int firstValue = 12;
		int secondValue = 73;
		int destinationRegister = 10;

		testAdd = new AddInstruction(destinationRegister, firstValue, secondValue);	// bruttrissimo, e' solo un riempitivo temporaneo

		int expectedResultValue = firstValue + secondValue;

		testAdd.execute();

		assertThat(testAdd.resultValue).isEqualTo(expectedResultValue);
	}

	@Test
	public void writeBackTest() throws IllegalAddressException {
		int numberOfRegisters = 32;
		Map<Integer, Integer> registers = new HashMap<Integer, Integer>();
		RegisterDesk testDesk = new RegisterDesk(registers, numberOfRegisters);
		int destinationRegister = 7;
		int firstValue = 150;
		int secondValue = 3;
		int valueToWrite = 153;

		testAdd = new AddInstruction(destinationRegister, firstValue, secondValue);

		testAdd.resultValue = valueToWrite;

		testAdd.writeBack(testDesk);

		assertThat(testDesk.registers.get(destinationRegister)).isEqualTo(valueToWrite);
	}

	@Test
	public void accessMemoryTest() {
		int memorySize = 16;
		TreeMap<Integer, Integer> cells = new TreeMap<>();
		RAM ram = new RAM(cells, memorySize);
		testAdd = new AddInstruction(0, 0, 0);
		assertThatThrownBy(() -> testAdd.accessMemory(ram)).isInstanceOf(StageNotRequiredException.class);
	}
}
