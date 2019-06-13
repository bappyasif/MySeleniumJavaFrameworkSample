package tutorial.edureka.example.com;

import org.testng.annotations.Test;

public class GroupingTestNG {
	
	@Test(groups = { "B" })
	public void Function1() {
	}

	@Test(groups = { "A" })
	public void Function2() {
	}

	@Test(groups = { "B" })
	public void Function3() {
	}

	@Test(groups = { "C" })
	public void Function4() {
	}
}
