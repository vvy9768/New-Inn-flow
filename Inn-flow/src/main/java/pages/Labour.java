package pages;

import webUtills.WebUtills;

public class Labour {
	WebUtills utill;	
public Labour(WebUtills utill) {
	// TODO Auto-generated constructor stub
	this.utill=utill;
}



public void windowHandle(String title) {
	utill.getWindowHandleByTitle( title);
}
}