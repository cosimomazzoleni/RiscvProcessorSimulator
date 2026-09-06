package riscv;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class CpuTests {
	Cpu testCpu;
	RegisterDesk desk;
	Map<Integer, Integer> registers;
	int deskSize;

	@Before
	public void setup() {
		registers = new TreeMap<Integer, Integer>();
		deskSize = 32;
		desk = new RegisterDesk(registers, deskSize);
		testCpu = new Cpu(desk);
	}

	@Test
	public void addExecutionTest() throws IllegalRegisterAddressException {
		int firstAddress = 12, firstValue = 113;
		int secondAddress = 3, secondValue = 9;
		int expectedSum = firstValue + secondValue;
		registers.put(firstAddress, firstValue);
		registers.put(secondAddress, secondValue);

		int actualSum = testCpu.add(firstAddress, secondAddress);

		assertThat(actualSum).isEqualTo(expectedSum);
	}

	@Test
	public void incorrectAddExecutionTest() {
		int firstAddress = 73;
		int secondAddress = -4;
		
		assertThatThrownBy(() -> testCpu.add(firstAddress, secondAddress)).isInstanceOf(IllegalRegisterAddressException.class);
	}
}
