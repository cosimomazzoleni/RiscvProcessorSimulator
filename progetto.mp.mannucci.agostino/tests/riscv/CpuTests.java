package riscv;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class CpuTests {
	Cpu testCpu;
	
	RegisterDesk desk;
	Map<Integer, Integer> registers;
	int deskSize;
	
	Memory ram;
	Map<Integer, Integer> ramCells;
	int memorySize;

	@Before
	public void setup() {
		deskSize = 32;
		registers = new HashMap<Integer, Integer>();
		desk = new RegisterDesk(registers, deskSize);
		testCpu = new Cpu(desk);

		ramCells = new TreeMap<Integer, Integer>();
		memorySize = 128;
	}

	@Test
	public void decodeAddInstructionTest() throws IllegalRegisterAddressException {
		int instructionWord = 0x730533; // add x10 x6 x7
		int firstRegister = 6, secondRegister = 7, destinationRegister = 10;
		registers.put(firstRegister, 54);
		registers.put(secondRegister, 33);
		
		AddInstruction testAdd = testCpu.instructionDecode(instructionWord);

		assertThat(testAdd.firstValue).isEqualTo(registers.get(firstRegister));
		assertThat(testAdd.secondValue).isEqualTo(registers.get(secondRegister));
		assertThat(testAdd.destinationRegister).isEqualTo(destinationRegister);
	}
}
