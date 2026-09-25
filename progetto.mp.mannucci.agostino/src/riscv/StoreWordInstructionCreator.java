package riscv;

public class StoreWordInstructionCreator extends STypeInstructionCreator {
	private final static int STORE_OPCODE = 0x23;
	private final static int STORE_FUN3 = 0x2;

	public StoreWordInstructionCreator() {
		super(STORE_OPCODE);
	}

	@Override
	protected boolean checkInstructionType(Integer instructionWord) {
		return super.checkOpcode(instructionWord) && ((Integer.compareUnsigned(super.getFun3(instructionWord), STORE_FUN3)) == 0);
	}

	@Override
	protected Instruction createConcreteInstruction(Integer instructionWord, Memory dataMemory)
			throws IllegalAddressException {
		int addressRegister = super.getFirstRegister(instructionWord);
		int offset = super.getOffset(instructionWord);
		int valueRegister = super.getSecondRegister(instructionWord);

		return new StoreWordInstruction(dataMemory.read(addressRegister), offset, dataMemory.read(valueRegister));
	}
}
