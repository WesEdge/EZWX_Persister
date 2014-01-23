package persister;

import java.io.IOException;
import EZWX.interfaces.MQActor;
import EZWX.mq.MQPersistanceConsumer;
import EZWX.mq.MQArgs;

public class Persister implements MQActor  {

    public Persister() throws Exception {
        new MQPersistanceConsumer(this);
    }

    public void execute(MQArgs args) throws IOException{

        byte[] gribBytes = args.getBytes();

        // TODO: decode the grib file and persist

    }

}
