package riscv;

public class StoreWordInstructionCreator extends InstructionCreator {
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
		int addressRegister = getFirstRegister(instructionWord);
		int offset = getOffset(instructionWord);
		int valueRegister = getSecondRegister(instructionWord);

		return new StoreWordInstruction(dataMemory.read(addressRegister), offset, dataMemory.read(valueRegister));
	}
	
	private int getFirstRegister(Integer instructionWord) {
		return (instructionWord >> 15) & 0x1f;
	}

	private int getOffset(Integer instructionWord) {
		return ((instructionWord >> 25) << 5) + ((instructionWord >> 7) & 0x1f);
	}
	
	private int getSecondRegister(Integer instructionWord) {
		return (instructionWord >> 20) & 0x1f;
	}
}
