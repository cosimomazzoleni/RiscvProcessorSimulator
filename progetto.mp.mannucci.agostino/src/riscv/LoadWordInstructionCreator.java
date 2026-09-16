package riscv;

public class LoadWordInstructionCreator extends InstructionCreator {
	private final static int LOAD_OPCODE = 0x3;
	private final static int LOAD_FUN3 = 0x2;

	public LoadWordInstructionCreator() {
		super(LOAD_OPCODE);
	}

	@Override
	protected Instruction createConcreteInstruction(int instructionWord, Memory dataMemory)
			throws IllegalAddressException {
		int dstRegister = (instructionWord / 0x80) % 0x20;
		int offset = (instructionWord / 0x100000) % 0x800;
		int sourceRegister = (instructionWord / 0x8000) % 0x20;
		return new LoadWordInstruction(dstRegister, offset, dataMemory.read(sourceRegister));
	}

	@Override
	protected boolean checkInstructionType(int instructionWord) {
		return super.checkOpcode(instructionWord) && (super.getFun3(instructionWord) == LOAD_FUN3);
	}

}
