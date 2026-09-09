package riscv;

import java.util.Map;

public class Memory {
	Map<Integer, Integer> memoryCells;
	final int numberOfCells;
	
	public Memory(Map<Integer, Integer> memoryCells, int numberOfCells) {
		this.memoryCells = memoryCells;
		this.numberOfCells = numberOfCells;
	}
}
