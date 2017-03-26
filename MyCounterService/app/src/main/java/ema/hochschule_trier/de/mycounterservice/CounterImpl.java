package ema.hochschule_trier.de.mycounterservice;

import android.os.Binder;

/**
 * Created by Daniel on 17.07.2016.
 */
public class CounterImpl extends Binder {

    private int counter;

    public int increment()
    {
        counter++;
        return counter;
    }

    public int reset(){
        counter = 0;
        return counter;
    }

}
