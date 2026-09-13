package riscv;

public class SubInstruction implements Instruction {
	final int destinationRegister;
	final int firstValue;
	final int secondValue;
	int resultValue;

	public SubInstruction(int destinationRegister, int firstValue, int secondValue) {
		this.destinationRegister = destinationRegister;
		this.firstValue = firstValue;
		this.secondValue = secondValue;
	}

	@Override
	public void execute() {
		resultValue = firstValue - secondValue;
	}

	@Override
	public void writeBack(Memory desk) throws IllegalAddressException {
		desk.write(destinationRegister, resultValue);
	}

	@Override
	public void accessMemory(Memory toAccess) throws StageNotRequiredException {
		throw new StageNotRequiredException();
	}
}
