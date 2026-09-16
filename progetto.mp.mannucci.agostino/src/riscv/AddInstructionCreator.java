package riscv;

public class AddInstructionCreator extends InstructionCreator {
	private final static int ADD_OPCODE = 0x33;
	private final static int ADD_FUN3 = 0x0;
	private final static int ADD_FUN7 = 0x0;

	public AddInstructionCreator() {
		super(ADD_OPCODE);
	}

	@Override
	protected Instruction createConcreteInstruction(int instructionWord, Memory dataMemory) throws IllegalAddressException {
		// DA METTERE MAX_ADDRESS_VALUE
		int dst = (instructionWord / 0x80) % 0x20;
		int src1 = (instructionWord / 0x8000) % 0x20;
		int src2 = (instructionWord / 0x100000) % 0x20;

		AddInstruction add = new AddInstruction(dst, dataMemory.read(src1), dataMemory.read(src2));
		
		return add;
	}

	@Override
	protected boolean checkInstructionType(int instructionWord) {
		return super.checkOpcode(instructionWord) && super.getFun3(instructionWord) == ADD_FUN3 && super.getFun7(instructionWord) == ADD_FUN7;
	}
}
