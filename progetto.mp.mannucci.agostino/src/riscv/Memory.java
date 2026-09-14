package riscv;

public interface Memory {
	public int getWordSize();
	public void write(int address, int value) throws IllegalAddressException;
	public int read(int address) throws IllegalAddressException;
}
