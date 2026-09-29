package riscv;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class RegisterDeskTests {
	RegisterDesk testDesk;
	Map<Integer, Integer> testRegisters;
	int deskSize;

	@Before
	public void setup() {
		testRegisters = new HashMap<Integer, Integer>();
		deskSize = 32;
		testDesk = new RegisterDesk(testRegisters, deskSize);
	}

	@Test
	public void writeRegisterTest() throws IllegalAddressException {
		int testAddress = 12;
		int testValue = 105;

		testDesk.writeWord(testAddress, testValue);

		assertThat(testDesk.registers.get(testAddress)).isEqualTo(testValue);
	}

	@Test
	public void illegalAddressWriteTest() {
		int testValue = 63;

		assertThatThrownBy(() -> testDesk.writeWord(0, testValue))
				.isInstanceOf(IllegalAddressException.class);
		assertThat(testDesk.registers.get(0)).isEqualTo(0);

		int testOverflowAddress = 46;
		assertThatThrownBy(() -> testDesk.writeWord(testOverflowAddress, testValue))
				.isInstanceOf(IllegalAddressException.class);
	}

	@Test
	public void readTest() throws IllegalAddressException {
		int testAddress = 14;
		int expectedValue = 93;

		testRegisters.put(testAddress, expectedValue);

		int actualValue = testDesk.readWord(testAddress);

		assertThat(actualValue).isEqualTo(expectedValue);
	}

	@Test
	public void illegalAddressReadTest() {
		int testOverflowAddress = 54;

		assertThatThrownBy(() -> testDesk.readWord(testOverflowAddress)).isInstanceOf(IllegalAddressException.class);
		
		int testUnderflowAddress = -17;
		
		assertThatThrownBy(() -> testDesk.readWord(testUnderflowAddress)).isInstanceOf(IllegalAddressException.class);
	}
}
