package pages;

import webUtills.WebUtills;

public class Procurement {
 WebUtills utill;
public  Procurement(WebUtills utill) {
	this.utill=utill;
}




public void windowHandle(String title) {
	utill.getWindowHandleByTitle( title);
}
}
