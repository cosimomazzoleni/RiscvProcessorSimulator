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

		testDesk.write(testAddress, testValue);

		assertThat(testDesk.registers.get(testAddress)).isEqualTo(testValue);
	}

	@Test
	public void illegalAddressWriteTest() {
		int testValue = 63;

		assertThatThrownBy(() -> testDesk.write(0, testValue))
				.isInstanceOf(IllegalAddressException.class);
		assertThat(testDesk.registers.get(0)).isEqualTo(0);

		int testOverflowAddress = 46;
		assertThatThrownBy(() -> testDesk.write(testOverflowAddress, testValue))
				.isInstanceOf(IllegalAddressException.class);
	}

	@Test
	public void readTest() throws IllegalAddressException {
		int testAddress = 14;
		int expectedValue = 93;

		testRegisters.put(testAddress, expectedValue);

		int actualValue = testDesk.read(testAddress);

		assertThat(actualValue).isEqualTo(expectedValue);
	}

	@Test
	public void illegalAddressReadTest() {
		int testOverflowAddress = 54;

		assertThatThrownBy(() -> testDesk.read(testOverflowAddress)).isInstanceOf(IllegalAddressException.class);
		
		int testUnderflowAddress = -17;
		
		assertThatThrownBy(() -> testDesk.read(testUnderflowAddress)).isInstanceOf(IllegalAddressException.class);
	}
}
