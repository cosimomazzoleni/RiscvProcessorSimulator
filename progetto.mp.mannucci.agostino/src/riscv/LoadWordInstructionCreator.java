package riscv;

public class LoadWordInstructionCreator extends InstructionCreator {
	private final static int LOAD_OPCODE = 0x3;
	private final static Integer LOAD_FUN3 = Integer.valueOf(0x2);

	public LoadWordInstructionCreator() {
		super(LOAD_OPCODE);
	}

	@Override
	protected Instruction createConcreteInstruction(Integer instructionWord, Memory dataMemory)
			throws IllegalAddressException {
		int dstRegister = (instructionWord >> 7) & 0x1f;
		int offset = (instructionWord >> 20);
		int sourceRegister = (instructionWord >> 15) & 0x1f;
		/*
		int dstRegister = Integer.remainderUnsigned(Integer.divideUnsigned(instructionWord, 0x80), 0x20);
		int offset = (int) Integer.remainderUnsigned(Integer.divideUnsigned(instructionWord, 0x100000), 0x800);
		int sourceRegister = Integer.remainderUnsigned(Integer.divideUnsigned(instructionWord, 0x8000), 0x20);
		*/
		return new LoadWordInstruction(dstRegister, offset, dataMemory.read(sourceRegister));
	}

	@Override
	protected boolean checkInstructionType(Integer instructionWord) {
		return super.checkOpcode(instructionWord) && ((Integer.compareUnsigned(super.getFun3(instructionWord), LOAD_FUN3)) == 0);
	}

}
