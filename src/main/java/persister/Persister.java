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
        String todo = "todo";
        // get the memcached key from the args
        // get the cached grib file
        // decode & persist data
    }

}
