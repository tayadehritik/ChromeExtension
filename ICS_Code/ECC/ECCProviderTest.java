import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;
import sun.security.ec.SunEC;

/**
 * ECC Provider Test.
 * @author  Christopher Meyer - christopher.meyer@rub.de
 * @version 0.1

 * Oct 23, 2013
 */
public class ECCProviderTest {

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        Provider sunEC = new SunEC();
        Security.addProvider(sunEC);
        for(Service service : sunEC.getServices()) {
            System.out.println(service.getType() + ": " 
                    + service.getAlgorithm());
        }
    }

}