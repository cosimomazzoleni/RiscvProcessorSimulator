package riscv;

public class LoadWordInstructionCreator extends ITypeInstructionCreator {
	private final static int LOAD_OPCODE = 0x3;
	private final static Integer LOAD_FUN3 = Integer.valueOf(0x2);

	public LoadWordInstructionCreator() {
		super(LOAD_OPCODE);
	}

	@Override
	protected Instruction createConcreteInstruction(Integer instructionWord, Memory dataMemory)
			throws IllegalAddressException {
		int dstRegister = super.getDestinationRegister(instructionWord);
		int offset = super.getOffset(instructionWord);
		int sourceRegister = super.getFirstRegister(instructionWord);

		return new LoadWordInstruction(dstRegister, offset, dataMemory.read(sourceRegister));
	}

	@Override
	protected boolean checkInstructionType(Integer instructionWord) {
		return super.checkOpcode(instructionWord)
				&& ((Integer.compareUnsigned(super.getFun3(instructionWord), LOAD_FUN3)) == 0);
	}

}
