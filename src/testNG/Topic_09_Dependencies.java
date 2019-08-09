package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_09_Dependencies {

	@Test(groups ="payment", enabled = true,  priority = 1, description = "Tao customer")
	 public void CreateCustomer() {
		  System.out.println("Run TC 01 CreateCustomer");
		  Assert.assertTrue(false);
	  }
	@Test(groups ="product", priority = 2, enabled = false, dependsOnMethods = "CreateCustomer")
	 public void EditCustomer() {
		  System.out.println("Run TC 02 EditCustomer" );
	  }
	@Test(groups ="product", priority = 3,dependsOnMethods = "EditCustomer")
	 public void CreateAccount() {
		  System.out.println("Run TC 03 CreateAccount");
	  }
	@Test(groups ="product", priority = 4, dependsOnMethods = "CreateAccount")
	 public void EditAccount() {
		  System.out.println("Run TC 04 EditAccount");
	  }
	@Test(groups ="users", priority = 5, dependsOnMethods = "EditAccount")
	 public void Deposit() {
		  System.out.println("Run TC 05 Deposit");
	  }
	@Test(groups ="payment", priority = 6, dependsOnMethods = "Deposit")
	 public void TransferMoney() {
		  System.out.println("Run TC 06 TransferMoney");
	  }

	
}
