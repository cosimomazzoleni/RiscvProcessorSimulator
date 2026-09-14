package riscv;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class RAMTests {
	HashMap<Integer, Integer> memoryCells;
	int memorySize;
	RAM ramMemory;

	@Before
	public void setup() {
		memorySize = 256;
		memoryCells = new HashMap<>();
		ramMemory = new RAM(memoryCells, memorySize);
	}

	@Test
	public void readTest() throws IllegalAddressException {
		int expectedValue = -53;
		int cellToRead = 233;
		memoryCells.put(cellToRead, expectedValue);

		int actualValue = ramMemory.read(cellToRead);

		assertThat(actualValue).isEqualTo(expectedValue);
	}

	@Test
	public void illegalReadTest() {
		int underflowAddress = -32;
		int overflowAddress = 2847;

		assertThatThrownBy(() -> ramMemory.read(underflowAddress)).isInstanceOf(IllegalAddressException.class);
		assertThatThrownBy(() -> ramMemory.read(overflowAddress)).isInstanceOf(IllegalAddressException.class);
	}

	@Test
	public void writeTest() throws IllegalAddressException {
		int expectedValue = 45;
		int cellToWrite = 74;

		ramMemory.write(cellToWrite, expectedValue);

		assertThat(memoryCells.get(cellToWrite)).isEqualTo(expectedValue);
	}

	@Test
	public void illegalWriteTest() {
		int underflowAddress = -632;
		int overflowAddress = 256;
		int valueToWrite = 64;

		assertThatThrownBy(() -> ramMemory.write(underflowAddress, valueToWrite)).isInstanceOf(IllegalAddressException.class);
		assertThatThrownBy(() -> ramMemory.write(overflowAddress, valueToWrite)).isInstanceOf(IllegalAddressException.class);
	}
}
