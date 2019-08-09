package testNG;

import org.testng.annotations.Test;

public class Topic_02_Group {

	@Test(groups ="a")
	 public void TC_01() {
		  System.out.println("Run TC 01");
	  }
	@Test(groups ="c")
	 public void TC_02() {
		  System.out.println("Run TC 02");
	  }
	@Test(groups ="a")
	 public void TC_03() {
		  System.out.println("Run TC 03");
	  }
	@Test(groups ="b")
	 public void TC_04() {
		  System.out.println("Run TC 04");
	  }
	@Test(groups ="b")
	 public void TC_05() {
		  System.out.println("Run TC 05");
	  }
	@Test(groups ="c")
	 public void TC_06() {
		  System.out.println("Run TC 06");
	  }

	
}
