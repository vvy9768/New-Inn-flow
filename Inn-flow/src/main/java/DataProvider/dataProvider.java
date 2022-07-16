
package DataProvider;

import org.testng.annotations.DataProvider;

public class dataProvider {

	
@DataProvider(name="browserConfig")
public Object[] getDataFromDataprovider(){
    return new Object[] 
    	{
            "chrome",
            "firefox",
            "ms"
        };
}
}