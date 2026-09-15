package riscv;

public class StoreWordInstruction implements Instruction{
	final int valueToWrite;
	final int baseAddress;
	final int offset;
	int addressToWrite;

	public StoreWordInstruction(int baseAddress, int offset, int valueToWrite) {
		this.baseAddress = baseAddress;
		this.offset = offset;
		this.valueToWrite = valueToWrite;
	}

	@Override
	public void execute() {
		addressToWrite = baseAddress + offset;
	}
	
	@Override
	public void accessMemory(Memory toAccess) throws StageNotRequiredException, IllegalAddressException {
		toAccess.write(addressToWrite, valueToWrite);
	}

	@Override
	public void writeBack(Memory desk) throws IllegalAddressException, StageNotRequiredException {
		throw new StageNotRequiredException();
	}

}
