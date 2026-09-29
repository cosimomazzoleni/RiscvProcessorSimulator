package riscv;

public class Cpu {
	RegisterDesk desk;
	InstructionCreator instructionCreatorChain;
	static final int WORD_SIZE = 4;

	public Cpu(RegisterDesk desk) {
		this.desk = desk;
		createSupportedInstructionCreators();
	}

	public int getWordSize() {
		return WORD_SIZE;
	}

	private void createSupportedInstructionCreators() {
		instructionCreatorChain = new AddInstructionCreator();
		instructionCreatorChain.addCreatorToChain(new SubInstructionCreator())
				.addCreatorToChain(new LoadWordInstructionCreator())
				.addCreatorToChain(new StoreWordInstructionCreator());
	}

	public final int runProgram(Memory InstructionMemory, int startingPoint, Memory dataMemory) {
		int programCounter = startingPoint;
		Integer instructionWord;
		Instruction currentInstruction;

		try {
			while ((instructionWord = instructionFetch(InstructionMemory, programCounter)) != 0) {
				currentInstruction = instructionDecode(instructionWord);
				currentInstruction.execute();
				currentInstruction.accessMemory(dataMemory);
				currentInstruction.writeBack(desk);
				programCounter = currentInstruction.updateProgramCounter(programCounter);
			}
		} catch (UnknownOpcodeException instructionFetchError) {
			return -1;
		} catch (IllegalAddressException addressError) {
			return -2;
		}
		return 0;
	}

	public Integer instructionFetch(Memory instructionMemory, int startingPoint) throws IllegalAddressException {
		return Integer.valueOf(instructionMemory.readWord(startingPoint));
	}

	public Instruction instructionDecode(Integer instructionWord)
			throws IllegalAddressException, UnknownOpcodeException {
		Instruction newInstruction = instructionCreatorChain.decodeInstructionWord(instructionWord, desk);
		return newInstruction;
	}
}
