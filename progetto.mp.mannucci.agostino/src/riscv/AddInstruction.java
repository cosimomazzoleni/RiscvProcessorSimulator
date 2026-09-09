package riscv;

public class AddInstruction {
	int firstValue;
	int secondValue;
	int resultValue;
	int destinationRegister;

	public AddInstruction(int destinationRegister, int firstValue, int secondValue) {
		this.firstValue = firstValue;
		this.secondValue = secondValue;
		this.destinationRegister = destinationRegister;
	}

	public void execute() {
		resultValue = firstValue + secondValue;
	}

	public void writeBack(RegisterDesk testDesk) throws IllegalRegisterAddressException {
		testDesk.writeRegister(destinationRegister, resultValue);
	}

	// qui aggiungi memory argument
	public void accessMemory(Memory toAccess) throws StageNotRequiredException {
		throw new StageNotRequiredException();
	}
}
