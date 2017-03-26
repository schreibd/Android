package ema.hochschule_trier.de.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class StartActivity extends AppCompatActivity {

    TextView display;
    String term;
    Button clear;
    boolean isNegative = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        display = (TextView)findViewById(R.id.display);
        clear = (Button)findViewById(R.id.delete);
        term = "";
        display.setText(term);

        clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                display.setText("");
                term = "";
                return false;
            }
        });
    }

    public void NumberButton(View v)
    {
        Button btn = (Button)v;
        display.append(btn.getText());
        term = display.getText().toString();
        String btnText = (String)btn.getText();
    }

    public void calculateResult(View v)
    {
        try {
            Expression e = new ExpressionBuilder(term).build();
            double result = e.evaluate();
            display.setText(String.valueOf(result));
        }
        catch(Exception inception)
        {
            display.setText("Term ist ungültig");
        }
    }

    public void clearSymbol(View v)
    {
        if(!term.isEmpty())
        {
            term = term.substring(0, term.length() -1);
            display.setText(term);
        }
    }

    public void changeSuffix(View v)
    {
        if(!isNegative)
        {
            term = "-".concat(term);
            display.setText(term.toString());
            isNegative = true;
        }
        else
        {
            term = term.substring(1, term.length());
            display.setText(term.toString());
            isNegative = false;
        }
    }

    public void makeFloat(View v)
    {
        if(!term.isEmpty())
        {
            term = term.concat(".");
            display.setText(term);
        }
    }

    public void calculateEuro(View v)
    {
        float result = Float.valueOf(term) / 1.958334f;
        term = String.valueOf(result);
        display.setText(term);
    }

    public void wurzelZiehen(View v)
    {
        term = "sqrt("+term+")";
        display.setText(term);

    }

    public void durchEins(View v)
    {
        term = "1 / ".concat(term);
        display.setText(term);
    }

    public void malZwei(View v)
    {
        term = "(" + term + ") * (" + term + ")";
        display.setText(term);
    }

    public void winkel(View v)
    {
        Button temp = (Button) v;
        switch(temp.getText().toString()){
            case("COS"): term = "cos("+term+")";
                break;
            case("SIN"): term = "sin("+term+")";
                break;
            case("TAN"): term = "tan("+term+")";
                break;
        }
        display.setText(term);
    }

    public void calculatePi(View v)
    {
        term = "pi * ("+term+")";
        display.setText(term);
    }

    public void calculateLog(View v)
    {
        if(term != "") {
            term = "log(" + term + ")";
            display.setText(term);
        }
    }

    public void calculateEuler(View v)
    {
        term = "exp("+term+")";
        display.setText(term);
    }

    public void factorial(View v)
    {
        term = "!("+term+")";
        display.setText(term);
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putString("display", display.getText().toString());
    }

    public void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        String display_val = bundle.getString("display");
        if(display_val != null)
            display.setText(display_val);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.item1:
                changeFontSize(10);
                return true;
            case R.id.item2:
                changeFontSize(16);
                return true;
            case R.id.item3:
                changeFontSize(50);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void changeFontSize(int font)
    {
        for (int i = 0; i < 10; i++) {
            int id = getResources().getIdentifier("button"+i, "id", getPackageName());
            Button button = (Button) findViewById(id);
            button.setTextSize(font);

        }
    }


}
