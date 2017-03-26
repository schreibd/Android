package ema.hochschule_trier.de.mycounterservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CounterImpl counter;
    private MyServiceConnection serConn;
    private String boundedStr = "Dienst gebunden";
    private String unboundedStr = "Kein Dienst gebunden";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void setCounter(CounterImpl counter){
        this.counter = counter;
        TextView tv = (TextView)findViewById(R.id.bindText);
        if(counter == null)
            tv.setText(unboundedStr);
        else
            tv.setText(boundedStr);
    }

    public void onDestroy(){
        super.onDestroy();
        if(counter != null)
            handleUnbindService(null);
    }

    public void handleBindService(View view)
    {
        if(serConn == null)
        {
            Intent newIntent = new Intent(this, BoundService.class);
            serConn = new MyServiceConnection(this);
            bindService(newIntent, serConn, Context.BIND_AUTO_CREATE);
        }
    }

    public void handleUnbindService(View view){
        if(serConn != null){
            unbindService(serConn);
            serConn = null;
            setCounter(null);
        }
    }

    public void handleIncrement(View view){
        handle(1);
    }

    public void handleReset(View view){
        handle(2);
    }

    private void handle(int value){
        int newValue;
        TextView txtCounter = (TextView)findViewById(R.id.counterText);
        if(counter != null)
        {
            switch(value) {
                case 1:
                    newValue = counter.increment();
                    break;
                case 2:
                    newValue = counter.reset();
                    break;
                default:
                    return;
            }
            txtCounter.setText("" + newValue);
        }
        else
            txtCounter.setText("Operation impossible");
    }
}
