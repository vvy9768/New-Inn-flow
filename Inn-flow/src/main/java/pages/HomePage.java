package pages;

import org.openqa.selenium.By;

import webUtills.WebUtills;

public class HomePage {
	
	WebUtills utill;	
public HomePage(WebUtills utill) {
	// TODO Auto-generated constructor stub
	this.utill=utill;
}

By text_HmPage=By.xpath("//div[text()='Inn-Flow']");
By Nvg_Acct=By.cssSelector("a#defaultSet-Accounting");
By Nvg_Sales=By.cssSelector("a#defaultSet-Sales");
By Nvg_Labour=By.cssSelector("a#defaultSet-Labor");
By Nvg_Procurement=By.cssSelector("a#defaultSet-Procurement");
By Nvg_Setting=By.cssSelector("a#defaultSet-Settings");




public void wait_HomePage() {
	utill.explicitlyWait(utill.getdriver(), 10);
	utill.waitUntillVisibleOfElement(text_HmPage); 
   // System.out.println("Title of the page : "+utill.getTitle());
    
}

public Accounting NavigateOnAccount() {
	
	utill.click( Nvg_Acct);
	
	return new Accounting(utill);
	
}
public Sales NavigateOnSales() {
	utill.click( Nvg_Sales);
 return new Sales(utill);
}
public Labour  NavigateOnLabour() {
	utill.click( Nvg_Labour);
	  return new Labour(utill);
}
public Procurement NavigateOnPrecurement() {
	utill.click( Nvg_Procurement);
	return new Procurement(utill);
}
public Setting NavigateOnSetting() {
	utill.click( Nvg_Setting);
	return new Setting(utill);
}

}