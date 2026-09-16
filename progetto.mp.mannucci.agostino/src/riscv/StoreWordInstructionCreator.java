package riscv;

public class StoreWordInstructionCreator extends InstructionCreator {
	private final static int STORE_OPCODE = 0x23;
	private final static int STORE_FUN3 = 0x2;

	public StoreWordInstructionCreator() {
		super(STORE_OPCODE);
	}

	@Override
	protected boolean checkInstructionType(int instructionWord) {
		return super.checkOpcode(instructionWord) && (super.getFun3(instructionWord) == STORE_FUN3);
	}

	@Override
	protected Instruction createConcreteInstruction(int instructionWord, Memory dataMemory)
			throws IllegalAddressException {
		int addressRegister = (instructionWord / 0x8000) % 0x20;
		int offset = ((instructionWord / 0x2000000) % 0x80) * 0x20 + (instructionWord / 0x80) % 0x20;
		int valueRegister = (instructionWord / 0x100000) % 0x20;
		return new StoreWordInstruction(dataMemory.read(addressRegister), offset, dataMemory.read(valueRegister));
	}

}
