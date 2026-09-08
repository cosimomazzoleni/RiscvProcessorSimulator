package riscv;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({RegisterDeskTests.class, CpuTests.class, AddInstructionTests.class})
public class AllTests {

}
