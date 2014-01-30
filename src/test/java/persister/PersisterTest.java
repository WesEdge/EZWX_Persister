package persister;

import org.junit.Assert;
import org.junit.*;
import ucar.grib.grib2.Grib2Input;
import ucar.grib.grib2.Grib2Product;
import ucar.unidata.io.InMemoryRandomAccessFile;
import ucar.unidata.io.RandomAccessFile;
import ucar.grib.grib2.Grib2Input;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: wedge
 * Date: 1/29/14
 * Time: 2:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersisterTest {

    @Test
    public void decodeGrib2() throws Exception {

        // get the grib2 file
        URL url = new URL("http://weather.noaa.gov/pub/SL.us008001/ST.opnl/DF.gr2/DC.ndfd/AR.conus/VP.001-003/ds.temp.bin");
        byte[] bytes = EZWX.core.Downloader.get(url);
        InMemoryRandomAccessFile raf = new InMemoryRandomAccessFile("raf", bytes);
        Grib2Input gribInput = new Grib2Input(raf);


        Assert.assertTrue(true);
    }


}
