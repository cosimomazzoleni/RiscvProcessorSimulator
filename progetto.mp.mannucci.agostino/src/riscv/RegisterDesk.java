package riscv;

import java.util.Map;

public class RegisterDesk implements Memory{
	Map<Integer, Integer> registers;
	final int numberOfRegisters;
	private final int registerSize;

	public RegisterDesk(Map<Integer, Integer> registers, int numberOfRegisters, int registerSize) {
		this.registers = registers;
		registers.put(0, 0);
		this.numberOfRegisters = numberOfRegisters;
		this.registerSize = registerSize;
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

	public int getWordSize() {
		return registerSize;
	}
}
