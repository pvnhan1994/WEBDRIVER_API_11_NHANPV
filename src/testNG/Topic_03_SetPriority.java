package testNG;

import org.testng.annotations.Test;

public class Topic_03_SetPriority {

	@Test(groups ="payment", priority = 1, description = "Tao customer")
	 public void CreateCustomer() {
		  System.out.println("Run TC 01 CreateCustomer");
	  }
	@Test(groups ="product", priority = 2, enabled = false)
	 public void EditCustomer() {
		  System.out.println("Run TC 02 EditCustomer");
	  }
	@Test(groups ="product", priority = 3)
	 public void CreateAccount() {
		  System.out.println("Run TC 03 CreateAccount");
	  }
	@Test(groups ="product", priority = 4)
	 public void EditAccount() {
		  System.out.println("Run TC 04 EditAccount");
	  }
	@Test(groups ="users", priority = 5)
	 public void Deposit() {
		  System.out.println("Run TC 05 Deposit");
	  }
	@Test(groups ="payment", priority = 6)
	 public void TransferMoney() {
		  System.out.println("Run TC 06 TransferMoney");
	  }

	
}
