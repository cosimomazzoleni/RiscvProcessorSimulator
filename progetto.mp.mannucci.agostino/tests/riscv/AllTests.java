package riscv;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RegisterDeskTests.class, CpuTests.class, AddInstructionTests.class, InstructionCreatorTest.class, SubInstructionTests.class, RAMTests.class, LoadWordInstructionTests.class, StoreWordInstructionTests.class})
public class AllTests {

}
