package riscv;

public class SubInstructionCreator extends InstructionCreator {
	private final static int SUB_OPCODE = 0x33;
	private final static int SUB_FUN3 = 0x0;
	private final static int SUB_FUN7 = 0x20;

	public SubInstructionCreator() {
		super(SUB_OPCODE);
	}

	@Override
	protected Instruction createConcreteInstruction(Integer instructionWord, Memory dataMemory) throws IllegalAddressException {
		int dst = (instructionWord >> 7) & 0x1f;
		int src1 = (instructionWord >> 15) & 0x1f;
		int src2 = (instructionWord >> 20) & 0x1f;

		SubInstruction add = new SubInstruction(dst, dataMemory.read(src1), dataMemory.read(src2));
		
		return add;
	}

	@Override
	protected boolean checkInstructionType(Integer instructionWord) {
			return super.checkOpcode(instructionWord) && ((Integer.compareUnsigned(super.getFun3(instructionWord), SUB_FUN3)) == 0) && ((Integer.compareUnsigned(super.getFun7(instructionWord), SUB_FUN7)) == 0);
	}

}
