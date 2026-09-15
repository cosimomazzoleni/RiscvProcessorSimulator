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

	// ha senso mettere dataMemory???
	public final void runProgram(Memory InstructionMemory, int startingPoint, Memory dataMemory) throws IllegalAddressException, UnknownOpcodeException {
		int programCounter = startingPoint;
		int instructionWord;
		Instruction currentInstruction;

		while((instructionWord = instructionFetch(InstructionMemory, programCounter)) != 0) {
			currentInstruction = instructionDecode(instructionWord);
			currentInstruction.execute();
			try {
				currentInstruction.accessMemory(dataMemory);
			} catch (StageNotRequiredException notStageException) {
				// teoricamente non e' necessario lanciare questa eccezione
				// 	potrebbe pero' aiutare nel capire cosa succede nel programma
			}
			try {				
				currentInstruction.writeBack(desk);
			} catch (StageNotRequiredException notStageException) {
			} catch (IllegalAddressException registerAccessError) {
				// qui il sistema deve crashare
			}
			programCounter++;
		}
	}

	public int instructionFetch(Memory instructionMemory, int startingPoint) throws IllegalAddressException {
		return instructionMemory.read(startingPoint);
	}
	
	public Instruction instructionDecode(int instructionWord) throws IllegalAddressException, UnknownOpcodeException {
		Instruction newInstruction = instructionCreatorChain.decodeInstructionWord(instructionWord, desk);
		return newInstruction;
	}
}
