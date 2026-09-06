package riscv;

import java.util.Map;

public class RegisterDesk {
	Map<Integer, Integer> registers;
	final int numberOfRegisters;

	public RegisterDesk(Map<Integer, Integer> registers, int numberOfRegisters) {
		this.registers = registers;
		registers.put(0, 0);
		this.numberOfRegisters = numberOfRegisters;
	}

	public boolean writeRegister(int registerAddress, int registerValue) {
		boolean success = false;
		if(registerAddress <= 0 || registerAddress >= numberOfRegisters) {
			return success;
		}
		registers.put(registerAddress, registerValue);
		success = true;
		return success;
	}

}
