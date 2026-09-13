package riscv;

public class SubInstructionCreator extends InstructionCreator {
	public SubInstructionCreator() {
		super(0x33, 0x0, 0x20);
	}

	@Override
	protected Instruction createConcreteInstruction(int instructionWord, Memory dataMemory) throws IllegalAddressException {
		int dst = (instructionWord / 0x80) % 0x20;
		int src1 = (instructionWord / 0x8000) % 0x20;
		int src2 = (instructionWord / 0x100000) % 0x20;

		SubInstruction add = new SubInstruction(dst, dataMemory.read(src1), dataMemory.read(src2));
		
		return add;
	}

}
