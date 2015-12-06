package com.it2107.assignment.assignment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button one, two, three, four, five, six, seven, eight, nine, zero, plus, minus, multiply, divide, clear, eq, decimal;
    EditText calculation;
    TextView result;
    HorizontalScrollView calculationScroll;
    boolean decimalSwitch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
        calculation = (EditText) findViewById(R.id.calculation);
        result = (TextView) findViewById(R.id.result);
        calculationScroll = (HorizontalScrollView) findViewById(R.id.calculationScroll);
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);
        decimal = (Button) findViewById(R.id.decimal);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        multiply = (Button) findViewById(R.id.multiply);
        divide = (Button) findViewById(R.id.divide);
        clear = (Button) findViewById(R.id.clear);
        eq = (Button) findViewById(R.id.eq);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        decimal.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        clear.setOnClickListener(this);
        eq.setOnClickListener(this);


    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.one:
                calculation.append("1");
                scrollFromRight();
                break;

            case R.id.two:
                calculation.append("2");
                scrollFromRight();
                break;

            case R.id.three:
                calculation.append("3");
                scrollFromRight();
                break;

            case R.id.four:
                calculation.append("4");
                scrollFromRight();
                break;

            case R.id.five:
                calculation.append("5");
                scrollFromRight();
                break;

            case R.id.six:
                calculation.append("6");
                scrollFromRight();
                break;

            case R.id.seven:
                calculation.append("7");
                scrollFromRight();
                break;

            case R.id.eight:
                calculation.append("8");
                scrollFromRight();
                break;

            case R.id.nine:
                calculation.append("9");
                scrollFromRight();
                break;

            case R.id.zero:
                calculation.append("0");
                scrollFromRight();
                break;

            case R.id.plus:
                calculation.append(getUnicode(0x002B));
                scrollFromRight();
                break;

            case R.id.minus:
                calculation.append(getUnicode(0x002D));
                scrollFromRight();
                break;

            case R.id.multiply:
                if (!calculation.getText().toString().isEmpty()){
                    calculation.append(getUnicode(0x00D7));
                    scrollFromRight();
                }

                break;

            case R.id.divide:
                if (!calculation.getText().toString().isEmpty()) {
                    calculation.append(getUnicode(0x00F7));
                    scrollFromRight();
                }
                break;

            case R.id.decimal:
                if (!calculation.getText().toString().isEmpty()) {
                    if (!calculation.getText().toString().endsWith(".") && decimalSwitch) {
                        calculation.append(".");
                        scrollFromRight();
                        decimalSwitch = false;
                    }
                }
                break;

            case R.id.clear:

                int length = calculation.getText().length();
                if (length > 0) {
                    calculation.getText().delete(length - 1, length);
                }
                break;

        }

    }

    public void scrollFromRight() {
    //        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                calculationScroll.scrollTo(calculationScroll.getRight(), calculationScroll.getTop());
//            }
//
//        }, 100L);
        calculationScroll.postDelayed(new Runnable() {
            public void run() {
                calculationScroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 100L);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }

}
