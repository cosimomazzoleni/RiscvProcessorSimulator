package riscv;

public abstract class ImmediateInstructionCreator extends InstructionCreator{

	public ImmediateInstructionCreator(int opcode) {
		super(opcode);
	}

	protected final int getDestinationRegister(Integer instructionWord) {
		return (instructionWord >> 7) & 0x1f;
	}

	protected final int getFirstRegister(Integer instructionWord) {
		return (instructionWord >> 15) & 0x1f;
	}


	protected final int getFun3(int instructionWord) {
		return Integer.remainderUnsigned(
				Integer.divideUnsigned(instructionWord, (OPCODE_MAX_VALUE * ADDRESS_REGISTER_MAX_VALUE)),
				MAX_FUN3_VALUE);
	}

	protected final int getOffset(int instructionWord) {
		return instructionWord >> 20;
	}
}
