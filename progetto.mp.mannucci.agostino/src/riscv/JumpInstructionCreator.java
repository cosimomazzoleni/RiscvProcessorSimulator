package riscv;

public abstract class JumpInstructionCreator extends InstructionCreator {

	public JumpInstructionCreator(int instructionOpcode) {
		super(instructionOpcode);
	}

	protected final int getDestinationRegister(Integer instructionWord) {
		return (instructionWord >> 7) & 0x1f;
	}

	protected final int getConstant(int instructionWord) {
//		int temp010 = (instructionWord >> 20) & 0x7fe;
//		int temp11 = (instructionWord & 0x100000) >> 10;
//		int temp1219 = instructionWord & 0xff000;
//		int temp20 = (instructionWord & 0x80000000) >> 9;
		return ((instructionWord & 0x80000000) >> 9) & (instructionWord & 0xff000)
				& ((instructionWord & 0x100000) >> 10) & ((instructionWord >> 20) & 0x7fe);
	}
}
