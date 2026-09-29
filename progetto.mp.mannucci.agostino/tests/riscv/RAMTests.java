package riscv;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class RAMTests {
	HashMap<Integer, Byte> memoryCells;
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
		int cellToRead = 233;
		int expectedValue = -5263;
		byte firstByte = (byte) (expectedValue & 0xff);
		byte secondByte = (byte) ((expectedValue >> 8) & 0xff);
		byte thirdByte = (byte) ((expectedValue >> 16) & 0xff);
		byte fourthByte = (byte) ((expectedValue >> 24) & 0xff);
		memoryCells.put(cellToRead, firstByte);
		memoryCells.put(cellToRead+1, secondByte);
		memoryCells.put(cellToRead+2, thirdByte);
		memoryCells.put(cellToRead+3, fourthByte);

		int actualValue = ramMemory.readWord(cellToRead);

		assertThat(actualValue).isEqualTo(expectedValue);
	}

	@Test
	public void illegalReadTest() {
		int underflowAddress = -32;
		int overflowAddress = 2847;

		assertThatThrownBy(() -> ramMemory.readWord(underflowAddress)).isInstanceOf(IllegalAddressException.class);
		assertThatThrownBy(() -> ramMemory.readWord(overflowAddress)).isInstanceOf(IllegalAddressException.class);
	}

	@Test
	public void writeTest() throws IllegalAddressException {
		int expectedValue = 45;
		int cellToWrite = 74;

		ramMemory.writeWord(cellToWrite, expectedValue);

		assertThat(memoryCells.get(cellToWrite)).isEqualTo((byte) (expectedValue & 0xff));
		assertThat(memoryCells.get(cellToWrite+1)).isEqualTo((byte) ((expectedValue >> 8) & 0xff));
		assertThat(memoryCells.get(cellToWrite+2)).isEqualTo((byte) ((expectedValue >> 16) & 0xff));
		assertThat(memoryCells.get(cellToWrite+3)).isEqualTo((byte) ((expectedValue >> 24) & 0xff));
	}

	@Test
	public void illegalWriteTest() {
		int underflowAddress = -632;
		int overflowAddress = 256;
		int valueToWrite = 64;

		assertThatThrownBy(() -> ramMemory.writeWord(underflowAddress, valueToWrite)).isInstanceOf(IllegalAddressException.class);
		assertThatThrownBy(() -> ramMemory.writeWord(overflowAddress, valueToWrite)).isInstanceOf(IllegalAddressException.class);
	}
}
