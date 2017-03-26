package ema.hochschule_trier.de.mycounterservice;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by Daniel on 17.07.2016.
 */
public class MyServiceConnection implements ServiceConnection {

    private MainActivity activity;

    public MyServiceConnection(MainActivity activity){
        this.activity = activity;
    }
    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        CounterImpl c = (CounterImpl)binder;
        activity.setCounter(c);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        activity.setCounter(null);

    }
}
