package riscv;

public class AddInstruction implements Instruction {
	final int destinationRegister;
	final int firstValue;
	final int secondValue;
	int resultValue;

	public AddInstruction(int destinationRegister, int firstValue, int secondValue) {
		this.firstValue = firstValue;
		this.secondValue = secondValue;
		this.destinationRegister = destinationRegister;
	}

	@Override
	public void execute() {
		resultValue = firstValue + secondValue;
	}

	@Override
	public void writeBack(Memory testDesk) throws IllegalAddressException {
		testDesk.write(destinationRegister, resultValue);
	}

	@Override
	public void accessMemory(Memory toAccess) {
		return;
	}

	@Override
	public int updateProgramCounter() {
		return 1;
	}
}
