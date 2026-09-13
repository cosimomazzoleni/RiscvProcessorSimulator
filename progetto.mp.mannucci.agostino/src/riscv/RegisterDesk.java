package riscv;

import java.util.Map;

public class RegisterDesk implements Memory{
	Map<Integer, Integer> registers;
	final int numberOfRegisters;

	public RegisterDesk(Map<Integer, Integer> registers, int numberOfRegisters) {
		this.registers = registers;
		registers.put(0, 0);
		this.numberOfRegisters = numberOfRegisters;
	}

	public void write(int registerAddress, int registerValue) throws IllegalAddressException{
		boolean success = false;
		if(registerAddress <= 0 || registerAddress >= numberOfRegisters) {
			throw new IllegalAddressException();
		}
		registers.put(registerAddress, registerValue);
		success = true;
	}
	
	public int read(int registerAddress) throws IllegalAddressException{
		if(registerAddress < 0 || registerAddress >= numberOfRegisters) {
			throw new IllegalAddressException();
		}
		return registers.get(registerAddress);
	}

}
