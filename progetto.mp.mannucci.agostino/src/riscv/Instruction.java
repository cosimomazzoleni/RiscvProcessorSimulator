package riscv;

public interface Instruction {
	public void execute();
	void writeBack(Memory desk) throws IllegalAddressException, StageNotRequiredException;
	void accessMemory(Memory toAccess) throws StageNotRequiredException;
}