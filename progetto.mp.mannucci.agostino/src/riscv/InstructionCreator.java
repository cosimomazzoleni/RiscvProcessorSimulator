package riscv;

public abstract class InstructionCreator {
	protected static final int OPCODE_MAX_VALUE = 0x80;
	protected static final int ADDRESS_REGISTER_MAX_VALUE = 0x20;
	protected static final int MAX_FUN3_VALUE = 0x8;
	protected static final int MAX_FUN7_VALUE = 0x80;

	private InstructionCreator nextChainElement;
	protected final int requiredOpcode;

	public InstructionCreator(int opcode) {
		this.requiredOpcode = opcode;
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
		if (this.checkInstructionType(instructionWord)) {
			return this.createConcreteInstruction(instructionWord, dataMemory);
		} else if (nextChainElement != null) {
			return nextChainElement.decodeInstructionWord(instructionWord, dataMemory);
		} else {
			throw new UnknownOpcodeException();
		}
	}

	protected abstract boolean checkInstructionType(int instructionWord);

	protected abstract Instruction createConcreteInstruction(int instructionWord, Memory dataMemory)
			throws IllegalAddressException;

	protected final boolean checkOpcode(int instructionWord) {
		return (instructionWord % OPCODE_MAX_VALUE) == requiredOpcode;
	}

	protected final int getFun3(int instructionWord) {
		return instructionWord / (OPCODE_MAX_VALUE * ADDRESS_REGISTER_MAX_VALUE) % MAX_FUN3_VALUE;
	}

	protected final int getFun7(int instructionWord) {
		return instructionWord / (OPCODE_MAX_VALUE * ADDRESS_REGISTER_MAX_VALUE * ADDRESS_REGISTER_MAX_VALUE * ADDRESS_REGISTER_MAX_VALUE * MAX_FUN3_VALUE) % MAX_FUN7_VALUE;
	}
}
