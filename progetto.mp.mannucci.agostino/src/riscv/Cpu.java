package riscv;

public class Cpu {
	RegisterDesk desk;
	InstructionCreator instructionCreatorChain;

	public Cpu(RegisterDesk desk) {
		this.desk = desk;
		createSupportedInstructionCreators();
	}

	private void createSupportedInstructionCreators() {
		instructionCreatorChain = new AddInstructionCreator();
		instructionCreatorChain.addCreatorToChain(new SubInstructionCreator());
		instructionCreatorChain.addCreatorToChain(new LoadWordInstructionCreator());
		instructionCreatorChain.addCreatorToChain(new StoreWordInstructionCreator());
	}

	// ha molto piu' senso fargli restituire int (come processi in Linux) con EXIT_FAILURE o EXIT_SUCCESS
	public final int runProgram(Memory InstructionMemory, int startingPoint, Memory dataMemory) {
		int programCounter = startingPoint;
		int instructionWord;
		Instruction currentInstruction;

		try {
			while((instructionWord = instructionFetch(InstructionMemory, programCounter)) != 0) {
				currentInstruction = instructionDecode(instructionWord);
				currentInstruction.execute();
				currentInstruction.accessMemory(dataMemory);
				currentInstruction.writeBack(desk);
				programCounter += currentInstruction.updateProgramCounter();
			}
		} catch (UnknownOpcodeException instructionFetchError) {
			return -1;
		} catch (IllegalAddressException addressError) {
			return -2;
		}
		return 0;
	}

	public int instructionFetch(Memory instructionMemory, int startingPoint) throws IllegalAddressException {
		return instructionMemory.read(startingPoint);
	}
	
	public Instruction instructionDecode(int instructionWord) throws IllegalAddressException, UnknownOpcodeException {
		Instruction newInstruction = instructionCreatorChain.decodeInstructionWord(instructionWord, desk);
		return newInstruction;
	}
}
