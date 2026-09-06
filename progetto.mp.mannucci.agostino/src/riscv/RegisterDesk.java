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

	public void writeRegister(int registerAddress, int registerValue) throws IllegalRegisterAddressException{
		boolean success = false;
		if(registerAddress <= 0 || registerAddress >= numberOfRegisters) {
			throw new IllegalRegisterAddressException();
		}
		registers.put(registerAddress, registerValue);
		success = true;
	}
	
	public int readRegister(int registerAddress) throws IllegalRegisterAddressException{
		if(registerAddress < 0 || registerAddress >= numberOfRegisters) {
			throw new IllegalRegisterAddressException();
		}
		return registers.get(registerAddress);
	}

}
