package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commonFunctionLibrary.OhrmLogin;
import utilities.XLUtils;

public class OhrmDataDriven {
@BeforeTest	
public void Launch()
{
	String launch=OhrmLogin.launchApp("http://orangehrm.qedgetech.com");
	Reporter.log(launch, true);
}
@Test
public void login() throws Throwable
{
	XLUtils xl=new XLUtils();
	int rc=xl.rowCount("Input");
	for(int i=1;i<=rc;i++)
	{
		String uid=xl.getCellData("Input", i, 7);
		String pword=xl.getCellData("Input", i,8);
		String login=OhrmLogin.verifyLogin(uid, pword);
		Reporter.log(login, true);
	}
}
@Test(priority=1)
public void addemployee() throws Throwable{
	XLUtils xl=new XLUtils();
	int rc=xl.rowCount("Input");
	for(int i=1;i<=rc;i++)
	{
		String fname=xl.getCellData("Input", i, 0);
		String lname=xl.getCellData("Input", i, 1);
		String nation=xl.getCellData("Input", i, 2);
		String marital=xl.getCellData("Input", i, 3);
		String month=xl.getCellData("Input", i, 4);
		String year=xl.getCellData("Input", i, 5);
		String date=xl.getCellData("Input", i, 6);
		String results=OhrmLogin.addEmployee(fname, lname, nation, marital, month, year, date);
		Reporter.log(results, true);
		xl.setCellData("Input",i,9,results);
	}
}
@AfterTest
public void logout(){
	String logout=OhrmLogin.verifyLogout();
	Reporter.log(logout, true);
}
}
