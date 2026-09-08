package riscv;

public class AddInstruction {
	int firstValue;
	int secondValue;
	int resultValue;

	void execute() {
		resultValue = firstValue + secondValue;
	}
}
