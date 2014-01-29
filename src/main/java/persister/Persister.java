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

        String memCachedKey = args.getMessage();

        // TODO: get grib file from memcached, decode the grib file and persist (look at Persister.java execute function)

    }

}
