package riscv;

import java.util.Map;

public class RAM implements Memory {
	Map<Integer, Integer> memoryCells;
	final int numberOfCells;
	
	public RAM(Map<Integer, Integer> memoryCells, int numberOfCells) {
		this.memoryCells = memoryCells;
		this.numberOfCells = numberOfCells;
	}

	@Override
	public void write(int address, int value) throws IllegalAddressException {
		if(address >=0 && address < numberOfCells) {
			memoryCells.put(address, value);
			return;
		}
		throw new IllegalAddressException();
	}

	@Override
	public int read(int address) throws IllegalAddressException {
		int readValue = 0;
		return readValue;
	}
}
