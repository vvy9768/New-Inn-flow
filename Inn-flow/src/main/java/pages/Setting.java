package pages;

import webUtills.WebUtills;

public class Setting {
	WebUtills utill;	
public Setting(WebUtills utill) {
	// TODO Auto-generated constructor stub
	this.utill=utill;
}



public void windowHandle(String title) {
	utill.getWindowHandleByTitle( title);
}
}
