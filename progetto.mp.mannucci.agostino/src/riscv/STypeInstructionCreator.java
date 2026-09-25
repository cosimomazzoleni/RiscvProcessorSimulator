package riscv;

public abstract class STypeInstructionCreator extends InstructionCreator {

	protected STypeInstructionCreator(int opcode) {
		super(opcode);
	}

	protected final int getFun3(int instructionWord) {
		return Integer.remainderUnsigned(
				Integer.divideUnsigned(instructionWord, (OPCODE_MAX_VALUE * ADDRESS_REGISTER_MAX_VALUE)),
				MAX_FUN3_VALUE);
	}

	protected int getFirstRegister(Integer instructionWord) {
		return (instructionWord >> 15) & 0x1f;
	}
	
	protected int getSecondRegister(Integer instructionWord) {
		return (instructionWord >> 20) & 0x1f;
	}

	protected int getOffset(Integer instructionWord) {
		return ((instructionWord >> 25) << 5) + ((instructionWord >> 7) & 0x1f);
	}
}
