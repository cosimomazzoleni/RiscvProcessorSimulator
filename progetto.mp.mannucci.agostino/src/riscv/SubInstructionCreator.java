package riscv;

public class SubInstructionCreator extends InstructionCreator {
	private final int fun3 = 0x0;
	private final int fun7 = 0x20;

	public SubInstructionCreator() {
		super(0x33);
	}

	@Override
	protected Instruction createConcreteInstruction(int instructionWord, Memory dataMemory) throws IllegalAddressException {
		int dst = (instructionWord / 0x80) % 0x20;
		int src1 = (instructionWord / 0x8000) % 0x20;
		int src2 = (instructionWord / 0x100000) % 0x20;

		SubInstruction add = new SubInstruction(dst, dataMemory.read(src1), dataMemory.read(src2));
		
		return add;
	}

	@Override
	protected boolean checkInstructionType(int instructionWord) {
			return super.checkOpcode(instructionWord) && super.getFun3(instructionWord) == fun3 && super.getFun7(instructionWord) == fun7;
	}

}
