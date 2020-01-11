package commonFunctionLibrary;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class OhrmLogin {
	public static WebDriver driver;
	public static String launchApp(String url)
	{
		String res="";
	System.setProperty("webdriver.chrome.driver","G:\\Practice\\OHRM\\CommonDrivers\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get(url);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	if(driver.findElement(By.id("btnLogin")).isDisplayed())
	{
		res="Application Launched Successfully";
	}
	else
	{
		res="Application Launch Failed";
	}
	return res;
	}
public static String verifyLogin(String uid,String pwd)
{
	String res="";
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.findElement(By.id("txtUsername")).clear();
    driver.findElement(By.id("txtUsername")).sendKeys(uid);
    driver.findElement(By.id("txtPassword")).clear();
    driver.findElement(By.id("txtPassword")).sendKeys(pwd);
    driver.findElement(By.id("btnLogin")).click();
    if(driver.findElement(By.linkText("Admin")).isDisplayed())
    {
    	res="Login Successfull";
    }
    else
    {
    	res="Login Failed";
    }
    return res;
}
public static String verifyLogout()
{
	String res="";
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.findElement(By.linkText("Welcome Suresh")).click();
	driver.findElement(By.linkText("Logout")).click();
	if(driver.findElement(By.id("btnLogin")).isDisplayed())
	{
		res="Logout Successfull";
	}
	else
	{
		res="Logout Failed";
	}
	return res;
}
public static String addEmployee(String fname,String lname,String nation,String marital,String month,String year,String date)
{
	String res="";
	String expdata,actdata;
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']/b")).click();
	driver.findElement(By.xpath("//a[@id='menu_pim_addEmployee']")).click();
	expdata=driver.findElement(By.id("employeeId")).getAttribute("value");
	driver.findElement(By.id("firstName")).sendKeys(fname);
	driver.findElement(By.id("lastName")).sendKeys(lname);
	driver.findElement(By.id("btnSave")).click();
	driver.findElement(By.xpath("//input[@id='btnSave']")).click();
	if(!driver.findElement(By.id("personal_optGender_1")).isSelected())
	{
	    driver.findElement(By.id("personal_optGender_1")).click();
	}
	Select cselect=new Select(driver.findElement(By.id("personal_cmbNation")));
	cselect.selectByVisibleText(nation);
	Select mselect=new Select(driver.findElement(By.id("personal_cmbMarital")));
	mselect.selectByVisibleText(marital);
	driver.findElement(By.id("personal_DOB")).click();
	Select monselect=new Select(driver.findElement(By.xpath("//select[@class='ui-datepicker-month']")));
	monselect.selectByVisibleText(month);
	Select yselect=new Select(driver.findElement(By.xpath("//select[@class='ui-datepicker-year']")));
	yselect.selectByVisibleText(year);
	WebElement table=driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']"));
	List<WebElement> trows=table.findElements(By.tagName("tr"));
	for(int i=1;i<trows.size();i++)
	{
		List<WebElement> tcols=trows.get(i).findElements(By.tagName("td"));
		for(int j=0;j<tcols.size();j++)
		{
		 String dt=tcols.get(j).getText();
		 if(dt.equals(date))
		 {
			 tcols.get(j).click();
		 }
	}
	}
	driver.findElement(By.xpath("//input[@id='btnSave']")).click();
	driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
	driver.findElement(By.id("empsearch_id")).sendKeys(expdata);
	driver.findElement(By.id("searchBtn")).click();
	actdata=driver.findElement(By.xpath("//table/tbody/tr/td[2]")).getText();
	if(expdata.equalsIgnoreCase(actdata))
	{
		res="Pass";
	}
	else
	{
		res="Fail";
	}
	return res;
}
}