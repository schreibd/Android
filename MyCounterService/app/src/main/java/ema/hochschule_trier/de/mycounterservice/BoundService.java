package ema.hochschule_trier.de.mycounterservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Daniel on 17.07.2016.
 */
public class BoundService extends Service {
    private static int instanceCounter = 0;
    private int number;


    public BoundService(){
        instanceCounter++;
        number = instanceCounter;
    }

    public void onCreate(){
        //Insert log
    }

    public void onDestroy(){
        //Insert Log
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new CounterImpl();
        //Insert Log
    }
}
