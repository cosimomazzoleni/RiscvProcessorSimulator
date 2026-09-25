package riscv;

public class AddInstructionCreator extends RTypeInstructionCreator {
	private final static int ADD_OPCODE = 0x33;
	private final static int ADD_FUN3 = 0x0;
	private final static int ADD_FUN7 = 0x0;

	public AddInstructionCreator() {
		super(ADD_OPCODE);
	}

	@Override
	protected Instruction createConcreteInstruction(Integer instructionWord, Memory dataMemory)
			throws IllegalAddressException {
		// DA METTERE MAX_ADDRESS_VALUE
		int dst = super.getDestinationRegister(instructionWord);
		int src1 = super.getFirstRegister(instructionWord);
		int src2 = super.getSecondRegister(instructionWord);

		AddInstruction add = new AddInstruction(dst, dataMemory.read(src1), dataMemory.read(src2));

		return add;
	}

	@Override
	protected boolean checkInstructionType(Integer instructionWord) {
		return super.checkOpcode(instructionWord)
				&& ((Integer.compareUnsigned(super.getFun3(instructionWord), ADD_FUN3)) == 0)
				&& ((Integer.compareUnsigned(super.getFun7(instructionWord), ADD_FUN7)) == 0);
	}
}
