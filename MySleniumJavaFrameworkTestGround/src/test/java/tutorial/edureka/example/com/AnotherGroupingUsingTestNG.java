package tutorial.edureka.example.com;

import org.testng.annotations.Test;

public class AnotherGroupingUsingTestNG {

	@Test(groups = {"A"})
	public void Function1() {
	}

	@Test(groups = {"B"})
	public void Function2() {
	}

	@Test(groups = {"C"})
	public void Function3() {
	}

	@Test(groups = {"B"})
	public void Function4() {
	}

	@Test(groups = {"C"})
	public void Function5() {
	}

	@Test(groups = {"A"})
	public void Function6() {
	}
}
