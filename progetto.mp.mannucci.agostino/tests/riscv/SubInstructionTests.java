package riscv;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class SubInstructionTests {
	SubInstruction testSub;

	@Test
	public void addExecutionTest() {
		int firstValue = 12;
		int secondValue = 73;
		int destinationRegister = 10;

		testSub = new SubInstruction(destinationRegister, firstValue, secondValue);

		int expectedResultValue = firstValue - secondValue;

		testSub.execute();

		assertThat(testSub.resultValue).isEqualTo(expectedResultValue);
	}

	@Test
	public void addWriteBackTest() throws IllegalAddressException {
		int numberOfRegisters = 32;
		Map<Integer, Integer> registers = new HashMap<Integer, Integer>();
		RegisterDesk testDesk = new RegisterDesk(registers, numberOfRegisters);
		int destinationRegister = 7;
		int firstValue = 150;
		int secondValue = 3;
		int valueToWrite = 153;

		testSub = new SubInstruction(destinationRegister, firstValue, secondValue);

		testSub.resultValue = valueToWrite;

		testSub.writeBack(testDesk);

		assertThat(testDesk.registers.get(destinationRegister)).isEqualTo(valueToWrite);
	}

	@Test
	public void addAccessMemoryTest() {
		int memorySize = 16;
		TreeMap<Integer, Integer> cells = new TreeMap<>();
		RAM ram = new RAM(cells, memorySize);
		testSub = new SubInstruction(0, 0, 0);
		assertThatThrownBy(() -> testSub.accessMemory(ram)).isInstanceOf(StageNotRequiredException.class);
	}
}
