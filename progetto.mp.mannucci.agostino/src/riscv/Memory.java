package riscv;

public interface Memory {
	public void writeWord(int address, int value) throws IllegalAddressException;
	public int readWord(int address) throws IllegalAddressException;
}
