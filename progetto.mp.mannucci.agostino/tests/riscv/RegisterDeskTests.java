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
	public void writeRegisterTest() {
		int testAddress = 12;
		int testOverflowAddress = 37;
		int testValue = 105;

		boolean writeSuccess = testDesk.writeRegister(testAddress, testValue);
		
		assertThat(writeSuccess).isTrue();
		assertThat(testDesk.registers.get(testAddress)).isEqualTo(testValue);
		
		writeSuccess = testDesk.writeRegister(0, testValue);
		
		assertThat(writeSuccess).isFalse();
		assertThat(testDesk.registers.get(0)).isEqualTo(0);

		writeSuccess = testDesk.writeRegister(testOverflowAddress, testValue);
		assertThat(writeSuccess).isFalse();
	}
}
