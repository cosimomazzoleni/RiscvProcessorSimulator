package riscv;

import java.util.Map;

public class RegisterDesk implements Memory{
	Map<Integer, Integer> registers;
	final int numberOfRegisters;
	final int registerSize = 32;	// per ora lo definisco cosi', ma quando avro' piu' chiaro come montare il tutto lo mettero' nel costruttore

	public RegisterDesk(Map<Integer, Integer> registers, int numberOfRegisters) {
		this.registers = registers;
		registers.put(0, 0);
		this.numberOfRegisters = numberOfRegisters;
	}

	public void write(int registerAddress, int registerValue) throws IllegalAddressException{
		if(registerAddress <= 0 || registerAddress >= numberOfRegisters) {
			throw new IllegalAddressException();
		}
		registers.put(registerAddress, registerValue);
	}
	
	public int read(int registerAddress) throws IllegalAddressException{
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
