package riscv;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class AddInstructionTests {
	AddInstruction testAdd;

	@Before
	public void setup() {
		testAdd = new AddInstruction();
	}

	@Test
	public void addExecutionTest() {
		int firstValue = 12;
		int secondValue = 73;

		testAdd.firstValue = firstValue;
		testAdd.secondValue = secondValue;
		int expectedResultValue = firstValue + secondValue;
		
		testAdd.execute();

		assertThat(testAdd.resultValue).isEqualTo(expectedResultValue);
	}

}
