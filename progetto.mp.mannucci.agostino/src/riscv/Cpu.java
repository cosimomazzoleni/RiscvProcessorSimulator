package riscv;

public class Cpu {
	RegisterDesk desk;

	public Cpu(RegisterDesk desk) {
		this.desk = desk;
	}

	public AddInstruction instructionDecode(int instructionWord) throws IllegalRegisterAddressException {
		int opcode = instructionWord % 0x80;
		if (opcode == 0x33) {
			int dst = (instructionWord / 0x80) % 0x20;
			int src1 = (instructionWord / 0x8000) % 0x20;
			int src2 = (instructionWord / 0x100000) % 0x20;

			return new AddInstruction(dst, desk.readRegister(src1), desk.readRegister(src2));
		}
		return null;
	}
}
