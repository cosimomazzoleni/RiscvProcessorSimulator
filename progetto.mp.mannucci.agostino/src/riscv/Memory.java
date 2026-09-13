package riscv;

public interface Memory {
	public void write(int address, int value) throws IllegalAddressException;
	public int read(int address) throws IllegalAddressException;
}
