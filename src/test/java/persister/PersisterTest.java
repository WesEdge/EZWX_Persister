package persister;

import org.junit.Assert;
import org.junit.*;
import ucar.grib.grib2.*;
import ucar.unidata.io.RandomAccessFile;
import ucar.grib.grib2.Grib2GridDefinitionSection;

import java.io.File;
import java.net.URL;
import java.util.Map;

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

        URL url = new URL("http://weather.noaa.gov/pub/SL.us008001/ST.opnl/DF.gr2/DC.ndfd/AR.conus/VP.001-003/ds.temp.bin");
        byte[] bytes = EZWX.core.Downloader.get(url);

        java.io.File tempFile = File.createTempFile("test", null, null);
        RandomAccessFile raf = new RandomAccessFile("test", "rw");
        raf.write(bytes);
        raf.close();

        raf = new RandomAccessFile("test", "rw");
        raf.order(RandomAccessFile.BIG_ENDIAN);
        Grib2Input gribInput = new Grib2Input(raf);

        if (gribInput.scan(true, false)) {
            write("Scan Success");

            //Grib2Data data = new Grib2Data(raf);
            Map<String, Grib2GridDefinitionSection> map = gribInput.getGDSs();

            if ((map == null) || (map.size() < 1)){
                write("No GDS Found");
                Assert.assertTrue(false);
            }

            for (Map.Entry<String, Grib2GridDefinitionSection> gds : map.entrySet())
            {
                write("GDS Found");
                write("------ BEGIN GDS ------");

                Grib2GDSVariables gdsVars = gds.getValue().getGdsVars();
                //Grib2GridDefinitionSection gdsValues = gds.getValue(); // mostly deprecated, use getGdsVars instead

                writeKVP("key", gdsVars.getGdsKey());  // 801839731
                writeKVP("section", gdsVars.getSection());  // 3 = Grid Definition Section
                writeKVP("source", gdsVars.getSource());  // 0 = Latitude/Longitude (See Template 3.0)    Also called Equidistant Cylindrical or Plate Caree
                writeKVP("shape", gdsVars.getShape());  // 1 = Earth assumed spherical with radius specified (in m) by data producer
                writeKVP("scan mode", gdsVars.getScanMode());  // 80 = ?
                writeKVP("point count", gdsVars.getNumberPoints());  // 739297
                writeKVP("grid units", gdsVars.getGridUnits()); // m
                writeKVP("projection flag", gdsVars.getProjectionFlag()); //    0
                writeKVP("resolution", gdsVars.getResolution()); // 0
                writeKVP("sp lat", gdsVars.getSpLat()); //  -90
                writeKVP("sp lon", gdsVars.getSpLon()); //  0
                writeKVP("N", gdsVars.getN()); //   -9999
                writeKVP("scale value major", gdsVars.getScaleValueMajor()); // 0
                writeKVP("scale value minor", gdsVars.getScaleValueMinor()); // 0
                writeKVP("scale value radius", gdsVars.getScaleValueRadius()); //   6371200
                writeKVP("crc", gdsVars.calcCRC()); //  2.24254797E9
                writeKVP("Nx", gdsVars.calculateNx()); //   0
                writeKVP("Xo", gdsVars.getXo()); // -9999.0
                writeKVP("Yo", gdsVars.getYo()); // -9999.0
                writeKVP("Xp", gdsVars.getXp()); // -9999.0
                writeKVP("Yp", gdsVars.getYp()); // -9999.0
                writeKVP("unscaled La1", gdsVars.getUnscaledLa1()); //  20191999
                writeKVP("unscaled Lo1", gdsVars.getUnscaledLo1()); //  238445999






                write("------- END GDS -------");
                break;
            }

        }
        else{
            write("Scan Failed");
            Assert.assertTrue(false);
        }

        Assert.assertTrue(true);

    }

    private void writeKVP(String key, float value){
        writeKVP(key, String.valueOf(value));
    }

    private void writeKVP(String key, int value){
        writeKVP(key, String.valueOf(value));
    }

    private void writeKVP(String key, String value){
        write(key + ": " + value);
    }

    private void write(String text){
        System.out.println(text);
    }


}
