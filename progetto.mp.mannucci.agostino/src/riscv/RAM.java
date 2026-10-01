package riscv;

import java.util.Map;

public class RAM implements Memory {
	private final int wordSize;
	Map<Integer, Byte> memoryCells;
	final int numberOfCells;

	public RAM(Map<Integer, Byte> memoryCells, int numberOfCells, int wordSize) {
		this.memoryCells = memoryCells;
		this.numberOfCells = numberOfCells;
		this.wordSize = wordSize;
	}

	@Override
	public void writeWord(int address, int value) throws IllegalAddressException {
		if (address >= 0 && address < numberOfCells - wordSize) {
			for (int j = 0; j < wordSize; j++) {
				memoryCells.put(address + j, (byte) ((value >> (8 * j)) & 0xff));
			}
			return;
		}
		throw new IllegalAddressException();
	}

	@Override
	public int readWord(int address) throws IllegalAddressException {
		int defaultValue = 0;
		if (address >= 0 && address < numberOfCells - wordSize) {
			try {
				int outputWord = 0;
				for(int j = 0; j < wordSize; j++) {
					outputWord = outputWord | ((memoryCells.get(address + j) & 0xff) << (8*j));
				}
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
