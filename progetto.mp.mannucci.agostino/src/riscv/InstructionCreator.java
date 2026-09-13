package riscv;

public abstract class InstructionCreator {
	public static final int OPCODE_MAX_VALUE = 0x80;
	public static final int MAX_REGISTER_VALUE = 0x20;
	public static final int MAX_FUN3_VALUE = 0x8;
	public static final int MAX_FUN7_VALUE = 0x80;
	private InstructionCreator nextChainElement;
	protected final int requiredOpcode;
	protected final int requiredFun3;
	protected final int requiredFun7;

	public InstructionCreator(int opcode, int fun3, int fun7) {
		this.requiredOpcode = opcode;
		this.requiredFun3 = fun3;
		this.requiredFun7 = fun7;
		this.nextChainElement = null;
	}

	public final InstructionCreator addCreatorToChain(InstructionCreator toAdd) {
		InstructionCreator currentElement = this;
		while (currentElement.nextChainElement != null) {
			currentElement = currentElement.nextChainElement;
		}
		currentElement.nextChainElement = toAdd;
		return this;
	}

	public final Instruction decodeInstructionWord(int instructionWord, Memory dataMemory)
			throws UnknownOpcodeException, IllegalAddressException {
		if (this.getOpcode(instructionWord) == requiredOpcode && getFun3(instructionWord) == requiredFun3
				&& getFun7(instructionWord) == requiredFun7) {
			return this.createConcreteInstruction(instructionWord, dataMemory);
		} else if (nextChainElement != null) {
			return nextChainElement.decodeInstructionWord(instructionWord, dataMemory);
		} else {
			throw new UnknownOpcodeException();
		}
	}

	protected abstract Instruction createConcreteInstruction(int instructionWord, Memory dataMemory)
			throws IllegalAddressException;

	private int getOpcode(int instructionWord) {
		int temp = instructionWord % OPCODE_MAX_VALUE;
		return temp;
	}

	private int getFun3(int instructionWord) {
		int temp = (instructionWord / (OPCODE_MAX_VALUE * MAX_REGISTER_VALUE)) % 0x8;
		return temp;
	}

	private int getFun7(int instructionWord) {
		int temp = (instructionWord / (OPCODE_MAX_VALUE * MAX_REGISTER_VALUE * MAX_REGISTER_VALUE * MAX_REGISTER_VALUE * MAX_FUN3_VALUE) % MAX_FUN7_VALUE);
		return temp;
	}
}
