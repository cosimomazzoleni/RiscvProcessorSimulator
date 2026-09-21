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

	public final int runProgram(Memory InstructionMemory, int startingPoint, Memory dataMemory) {
		int programCounter = startingPoint;
		Integer instructionWord;
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

	public Integer instructionFetch(Memory instructionMemory, int startingPoint) throws IllegalAddressException {
		return Integer.valueOf(instructionMemory.read(startingPoint));
	}
	
	public Instruction instructionDecode(Integer instructionWord) throws IllegalAddressException, UnknownOpcodeException {
		Instruction newInstruction = instructionCreatorChain.decodeInstructionWord(instructionWord, desk);
		return newInstruction;
	}
}
