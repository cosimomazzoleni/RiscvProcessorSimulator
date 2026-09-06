package riscv;

public class Cpu {
	RegisterDesk desk;

	public Cpu(RegisterDesk desk) {
		this.desk = desk;
	}

	public int add(int firstRegister, int secondRegister) throws IllegalRegisterAddressException {
		return desk.readRegister(firstRegister) + desk.readRegister(secondRegister);
	}
}
