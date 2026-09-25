package riscv;

public abstract class RTypeInstructionCreator extends InstructionCreator {

	protected RTypeInstructionCreator(int instructionOpcode) {
		super(instructionOpcode);
	}
	
	protected final int getDestinationRegister(Integer instructionWord) {
		return (instructionWord >> 7) & 0x1f;
	}

	protected final int getFirstRegister(Integer instructionWord) {
		return (instructionWord >> 15) & 0x1f;
	}
	
	protected final int getSecondRegister(Integer instructionWord) {
		return (instructionWord >> 20) & 0x1f;
	}

	protected final int getFun3(int instructionWord) {
		return Integer.remainderUnsigned(
				Integer.divideUnsigned(instructionWord, (OPCODE_MAX_VALUE * ADDRESS_REGISTER_MAX_VALUE)),
				MAX_FUN3_VALUE);
	}

	protected final int getFun7(int instructionWord) {
		return Integer.remainderUnsigned(Integer.divideUnsigned(instructionWord, (OPCODE_MAX_VALUE * ADDRESS_REGISTER_MAX_VALUE * ADDRESS_REGISTER_MAX_VALUE
				* ADDRESS_REGISTER_MAX_VALUE * MAX_FUN3_VALUE)), MAX_FUN7_VALUE);
	}
}
