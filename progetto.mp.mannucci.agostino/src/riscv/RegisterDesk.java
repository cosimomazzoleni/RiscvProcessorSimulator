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

	public void writeWord(int registerAddress, int registerValue) throws IllegalAddressException{
		if(registerAddress <= 0 || registerAddress >= numberOfRegisters) {
			throw new IllegalAddressException();
		}
		registers.put(registerAddress, registerValue);
	}
	
	public int readWord(int registerAddress) throws IllegalAddressException{
		int defaultValue = 0;
		if(registerAddress >= 0 && registerAddress < numberOfRegisters) {
			try {
				return registers.get(registerAddress);				
			} catch (NullPointerException e) {
				return defaultValue;
			}
		}
		throw new IllegalAddressException();
	}
}
