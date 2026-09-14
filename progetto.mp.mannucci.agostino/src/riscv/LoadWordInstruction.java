package riscv;

public class LoadWordInstruction implements Instruction {
	final int destinationRegister;
	final int offset;
	final int baseAddress;
	int addressToRead;
	int readValue;

	LoadWordInstruction(int destinationRegister, int offset, int baseAddress) {
		this.destinationRegister = destinationRegister;
		this.offset = offset;
		this.baseAddress = baseAddress;
	}

	@Override
	public void execute() {
		addressToRead = baseAddress + offset;
	}

	@Override
	public void writeBack(Memory desk) throws IllegalAddressException, StageNotRequiredException {
		desk.write(destinationRegister, readValue);
	}

	@Override
	public void accessMemory(Memory toAccess) throws StageNotRequiredException, IllegalAddressException {
		readValue = toAccess.read(addressToRead);
	}

}
