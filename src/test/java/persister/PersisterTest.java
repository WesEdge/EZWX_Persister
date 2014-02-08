package persister;

import EZWX.core.ConsoleWriter;
import EZWX.grib.Grib2;
import org.junit.Assert;
import org.junit.*;
import java.net.URL;

public class PersisterTest {

    @Test
    public void decodeGrib2() throws Exception {

        URL url = new URL("http://weather.noaa.gov/pub/SL.us008001/ST.opnl/DF.gr2/DC.ndfd/AR.conus/VP.001-003/ds.temp.bin");

        Grib2 grib = Grib2.load(url);

        if ((grib != null) && (grib.isLoaded())){

            String gdsSummary = grib.getGDSSummary();
            ConsoleWriter.write(gdsSummary);

            String pdsSummary = grib.getProductSummary();
            ConsoleWriter.write(pdsSummary);

            Assert.assertTrue(true);

        }
        else{
            Assert.assertTrue(false);
        }

    }

}
