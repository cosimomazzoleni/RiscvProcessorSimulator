package riscv;

public class AddInstructionCreator extends InstructionCreator {
	private final static int ADD_OPCODE = 0x33;
	private final static int ADD_FUN3 = 0x0;
	private final static int ADD_FUN7 = 0x0;

	public AddInstructionCreator() {
		super(ADD_OPCODE);
	}

	@Override
	protected Instruction createConcreteInstruction(Integer instructionWord, Memory dataMemory) throws IllegalAddressException {
		// DA METTERE MAX_ADDRESS_VALUE
		int dst = getDestinationRegister(instructionWord);
		int src1 = getFirstRegister(instructionWord);
		int src2 = getSecondRegister(instructionWord);

		AddInstruction add = new AddInstruction(dst, dataMemory.read(src1), dataMemory.read(src2));
		
		return add;
	}
	
	private int getDestinationRegister(Integer instructionWord) {
		return (instructionWord >> 7) & 0x1f;
	}

	private int getFirstRegister(Integer instructionWord) {
		return (instructionWord >> 15) & 0x1f;
	}
	
	private int getSecondRegister(Integer instructionWord) {
		return (instructionWord >> 20) & 0x1f;
	}

	@Override
	protected boolean checkInstructionType(Integer instructionWord) {
		return super.checkOpcode(instructionWord) && ((Integer.compareUnsigned(super.getFun3(instructionWord), ADD_FUN3)) == 0) && ((Integer.compareUnsigned(super.getFun7(instructionWord), ADD_FUN7)) == 0);
	}
}
