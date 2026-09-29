package riscv;

import java.util.Map;

public class RAM implements Memory {
	Map<Integer, Byte> memoryCells;
	private int wordSize;	// devo capire come introdurla, tipo tramite createComputer()
	final int numberOfCells;
	
	public RAM(Map<Integer, Byte> memoryCells, int numberOfCells) {
		this.memoryCells = memoryCells;
		this.numberOfCells = numberOfCells;
	}

	@Override
	public void writeWord(int address, int value) throws IllegalAddressException {
		if(address >=0 && address < numberOfCells) {
			memoryCells.put(address, (byte) (value & 0xff));
			memoryCells.put(address+1, (byte) ((value >> 8) & 0xff));
			memoryCells.put(address+2, (byte) ((value >> 16) & 0xff));
			memoryCells.put(address+3, (byte) ((value >> 24) & 0xff));
			return;
		}
		throw new IllegalAddressException();
	}

	@Override
	public int readWord(int address) throws IllegalAddressException {
		int defaultValue = 0;
		if(address >=0 && address < numberOfCells) {
			try {
				int outputWord = memoryCells.get(address) & 0xff;
				outputWord = outputWord | ((memoryCells.get(address+1) & 0xff) << 8);
				outputWord = outputWord | ((memoryCells.get(address+2) & 0xff) << 16);
				outputWord = outputWord | ((memoryCells.get(address+3) & 0xff) << 24);
				return outputWord;
			} catch (NullPointerException e) {
				return defaultValue;
			}	
		}
		throw new IllegalAddressException();	
	}

	@Override
	public int getWordSize() {
		return wordSize;
	}
}
