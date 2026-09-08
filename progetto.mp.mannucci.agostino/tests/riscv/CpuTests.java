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
	public void addExecutionTest() {
		
	}
}
