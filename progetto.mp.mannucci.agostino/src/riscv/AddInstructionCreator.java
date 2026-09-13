package riscv;

public class AddInstructionCreator extends InstructionCreator {

	public AddInstructionCreator() {
		super(0x33, 0x0, 0x0);
	}

	@Override
	protected Instruction createConcreteInstruction(int instructionWord, Memory dataMemory) throws IllegalAddressException {
		int dst = (instructionWord / 0x80) % 0x20;
		int src1 = (instructionWord / 0x8000) % 0x20;
		int src2 = (instructionWord / 0x100000) % 0x20;

		AddInstruction add = new AddInstruction(dst, dataMemory.read(src1), dataMemory.read(src2));
		
		return add;
	}
}
