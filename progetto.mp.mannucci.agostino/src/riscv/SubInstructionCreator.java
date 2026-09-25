package riscv;

public class SubInstructionCreator extends RTypeInstructionCreator {
	private final static int SUB_OPCODE = 0x33;
	private final static int SUB_FUN3 = 0x0;
	private final static int SUB_FUN7 = 0x20;

	public SubInstructionCreator() {
		super(SUB_OPCODE);
	}

	@Override
	protected Instruction createConcreteInstruction(Integer instructionWord, Memory dataMemory)
			throws IllegalAddressException {
		int dst = super.getDestinationRegister(instructionWord);
		int src1 = super.getFirstRegister(instructionWord);
		int src2 = super.getSecondRegister(instructionWord);

		SubInstruction add = new SubInstruction(dst, dataMemory.read(src1), dataMemory.read(src2));

		return add;
	}

	@Override
	protected boolean checkInstructionType(Integer instructionWord) {
		return super.checkOpcode(instructionWord)
				&& ((Integer.compareUnsigned(super.getFun3(instructionWord), SUB_FUN3)) == 0)
				&& ((Integer.compareUnsigned(super.getFun7(instructionWord), SUB_FUN7)) == 0);
	}
}
