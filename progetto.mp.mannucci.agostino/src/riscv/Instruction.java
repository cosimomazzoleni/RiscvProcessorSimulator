package riscv;

public interface Instruction {
	public void execute();
	public void writeBack(Memory desk) throws IllegalAddressException, StageNotRequiredException;
	public void accessMemory(Memory toAccess) throws StageNotRequiredException, IllegalAddressException;
	public int updateProgramCounter();
}