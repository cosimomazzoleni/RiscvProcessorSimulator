package riscv;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
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
	
	RAM ram;
	Map<Integer, Integer> ramCells;
	int memorySize;

	@Before
	public void setup() {
		deskSize = 32;
		registers = new HashMap<Integer, Integer>();
		desk = new RegisterDesk(registers, deskSize);
		testCpu = new Cpu(desk);

		ramCells = new TreeMap<Integer, Integer>();
		memorySize = 256;
		ram = new RAM(ramCells, memorySize);
	}

	@Test
	public void fetchInstructionTest() throws IllegalAddressException {
		int expectedWord = 0x8e2e83;
		int programCounter = 63;
		ramCells.put(programCounter, expectedWord);

		int actualWord = testCpu.instructionFetch(ram, programCounter);

		assertThat(actualWord).isEqualTo(expectedWord);
	}

	@Test
	public void decodeAddInstructionTest() throws IllegalAddressException, UnknownOpcodeException {
		int instructionWord = 0x730533; // add x10 x6 x7
		int firstRegister = 6, secondRegister = 7, destinationRegister = 10;
		registers.put(firstRegister, 54);
		registers.put(secondRegister, 33);
		
		AddInstruction testAdd = (AddInstruction) testCpu.instructionDecode(instructionWord);

		assertThat(testAdd.firstValue).isEqualTo(registers.get(firstRegister));
		assertThat(testAdd.secondValue).isEqualTo(registers.get(secondRegister));
		assertThat(testAdd.destinationRegister).isEqualTo(destinationRegister);
	}

	@Test
	public void decodeSubInstructionTest() throws IllegalAddressException, UnknownOpcodeException {
		int instructionWord = 0x40638633; // sub x12 x7 x6
		int firstRegister = 7, secondRegister = 6, destinationRegister = 12;
		registers.put(firstRegister, 54);
		registers.put(secondRegister, 33);
		
		SubInstruction testSub = (SubInstruction) testCpu.instructionDecode(instructionWord);

		assertThat(testSub.firstValue).isEqualTo(registers.get(firstRegister));
		assertThat(testSub.secondValue).isEqualTo(registers.get(secondRegister));
		assertThat(testSub.destinationRegister).isEqualTo(destinationRegister);
	}

	@Test
	public void decodeLoadWordInstructionTest() throws IllegalAddressException, UnknownOpcodeException {
		// volevo fare questo test ma JAVA VA IN OVERFLOW
		//		non esattamente, semplicemente considera iw negativa e quindi il modulo torna diversamente
		// int instructionWord = 0xffc42603;
		Integer instructionWord = Integer.valueOf(0xfd62a503); // lw x10 -42 x5
		int dstRegister = 10, offset = -42, sourceRegister = 5;
		int baseAddress = 112;
		registers.put(sourceRegister, baseAddress);
		// NOTA: posso usare unsigned arithmetic tramite la classe Integer
		int bllb = 0xfd62a503;
		long superbllb = 0xfd62a503L;
		System.out.println("Int: " + bllb + "\n" + "Long: " + superbllb);
		
		LoadWordInstruction testLoadWord = (LoadWordInstruction) testCpu.instructionDecode(instructionWord);
		
		assertThat(testLoadWord.destinationRegister).isEqualTo(dstRegister);
		assertThat(testLoadWord.offset).isEqualTo(offset);
		assertThat(testLoadWord.baseAddress).isEqualTo(baseAddress);
	}

	@Test
	public void decodeStoreWordInstructionTest() throws IllegalAddressException, UnknownOpcodeException {
		int valueSrcRegister = 15, offset = 3, addressSrcRegister = 30;
		int instructionWord = 0x00ff21a3;
		int expectedValue = 34, expectedAddress = 12;
		registers.put(valueSrcRegister, expectedValue);
		registers.put(addressSrcRegister, expectedAddress);

		StoreWordInstruction testStoreWord = (StoreWordInstruction) testCpu.instructionDecode(instructionWord);

		assertThat(testStoreWord.valueToWrite).isEqualTo(expectedValue);
		assertThat(testStoreWord.offset).isEqualTo(offset);
		assertThat(testStoreWord.baseAddress).isEqualTo(expectedAddress);
	}

	@Test
	public void runProgramTest() throws IllegalAddressException, UnknownOpcodeException {
		int exit_success = 0;
		int startingPoint = 136;
		ramCells.put(startingPoint, 0x42283);
		ramCells.put(startingPoint+1, 0xc4a303);
	    ramCells.put(startingPoint+2, 0x628533);
	    ramCells.put(startingPoint+3, 0xa92023);
	    int firstValue = 61;
	    int firstBaseAddressRegister = 8;
	    int firstBaseAddress = 127;
	    int firstOffset = 0;
	    ramCells.put(firstBaseAddress+firstOffset, firstValue);
	    int secondValue = -34;
	    int secondBaseAddressRegister = 9;
	    int secondBaseAddress = 221;
	    int secondOffset = 12;
	    ramCells.put(secondBaseAddress+secondOffset, secondValue);
	    int storeAddressRegister = 18;
	    int storeAddress = 14;
	    registers.put(firstBaseAddressRegister, firstBaseAddress);
	    registers.put(secondBaseAddressRegister, secondBaseAddress);
	    registers.put(storeAddressRegister, storeAddress);

	    int exitValue = testCpu.runProgram(ram, startingPoint, ram);

	    assertThat(exitValue).isEqualTo(exit_success);
	    assertThat(ramCells.get(storeAddress)).isEqualTo(firstValue+secondValue);
	}
}
