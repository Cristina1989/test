package test;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestExpo.class, TestMore.class })
public class AllTests {
	@AfterClass
	public static void tearDownAfter() {
		System.out.println("3. All Tests have finished! :D");
	}
}
