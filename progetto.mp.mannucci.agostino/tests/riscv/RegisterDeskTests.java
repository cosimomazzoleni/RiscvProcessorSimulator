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
	public void writeRegisterTest() throws IllegalRegisterAddressException {
		int testAddress = 12;
		int testValue = 105;

		testDesk.writeRegister(testAddress, testValue);

		assertThat(testDesk.registers.get(testAddress)).isEqualTo(testValue);
	}

	@Test
	public void illegalAddressWriteTest() {
		int testValue = 63;

		assertThatThrownBy(() -> testDesk.writeRegister(0, testValue))
				.isInstanceOf(IllegalRegisterAddressException.class);
		assertThat(testDesk.registers.get(0)).isEqualTo(0);

		int testOverflowAddress = 46;
		assertThatThrownBy(() -> testDesk.writeRegister(testOverflowAddress, testValue))
				.isInstanceOf(IllegalRegisterAddressException.class);
	}

	@Test
	public void readTest() throws IllegalRegisterAddressException {
		int testAddress = 14;
		int expectedValue = 93;

		testRegisters.put(testAddress, expectedValue);

		int actualValue = testDesk.readRegister(testAddress);

		assertThat(actualValue).isEqualTo(expectedValue);
	}

	@Test
	public void illegalAddressReadTest() {
		int testOverflowAddress = 54;

		assertThatThrownBy(() -> testDesk.readRegister(testOverflowAddress)).isInstanceOf(IllegalRegisterAddressException.class);
		
		int testUnderflowAddress = -17;
		
		assertThatThrownBy(() -> testDesk.readRegister(testUnderflowAddress)).isInstanceOf(IllegalRegisterAddressException.class);
	}
}
