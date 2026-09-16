package riscv;

public interface Instruction {
	public void execute();
	public void writeBack(Memory desk) throws IllegalAddressException;
	public void accessMemory(Memory toAccess) throws IllegalAddressException;
	public int updateProgramCounter();
}