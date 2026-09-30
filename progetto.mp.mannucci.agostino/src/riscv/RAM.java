package riscv;

import java.util.Map;

public class RAM implements Memory {
	Map<Integer, Byte> memoryCells;
	final int numberOfCells;

	public RAM(Map<Integer, Byte> memoryCells, int numberOfCells) {
		this.memoryCells = memoryCells;
		this.numberOfCells = numberOfCells;
	}

	@Override
	public void writeWord(int address, int value) throws IllegalAddressException {
		if (address >= 0 && address < numberOfCells - Cpu.WORD_SIZE) {
			for (int j = 0; j < Cpu.WORD_SIZE; j++) {
				memoryCells.put(address + j, (byte) ((value >> (8 * j)) & 0xff));
			}
			return;
		}
		throw new IllegalAddressException();
	}

	@Override
	public int readWord(int address) throws IllegalAddressException {
		int defaultValue = 0;
		if (address >= 0 && address < numberOfCells - Cpu.WORD_SIZE) {
			try {
				int outputWord = 0;
				for(int j = 0; j < Cpu.WORD_SIZE; j++) {
					outputWord = outputWord | ((memoryCells.get(address + j) & 0xff) << (8*j));
				}
				return outputWord;
			} catch (NullPointerException e) {
				return defaultValue;
			}
		}
		throw new IllegalAddressException();
	}

}
