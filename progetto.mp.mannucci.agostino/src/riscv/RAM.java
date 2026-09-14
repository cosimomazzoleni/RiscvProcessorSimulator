package riscv;

import java.util.Map;

public class RAM implements Memory {
	Map<Integer, Integer> memoryCells;
	private int wordSize;	// devo capire come introdurla, tipo tramite createComputer()
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
		if(address >=0 && address < numberOfCells) {
			return memoryCells.get(address);
		}
		throw new IllegalAddressException();	
	}

	@Override
	public int getWordSize() {
		return wordSize;
	}
}
