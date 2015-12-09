package com.it2107.assignment.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button one, two, three, four, five, six, seven, eight, nine, zero, plus, minus, multiply, divide, clear, eq, decimal;
    EditText calculation;
    EditText result;
    HorizontalScrollView calculationScroll, resultPanel;
    boolean decimalSwitch = true;
    double total = 0.0;
    double input = 0.0;
    boolean isTotal = false;
    boolean isFirstInput = true;
    int clicked;
    NumberFormat nf = new DecimalFormat("##.###");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        calculation = (EditText) findViewById(R.id.calculation);
        result = (EditText) findViewById(R.id.result);
        calculation.setTextColor(0xff757575);
        result.setTextColor(0xff333333);
        calculationScroll = (HorizontalScrollView) findViewById(R.id.calculationScroll);
        resultPanel = (HorizontalScrollView) findViewById(R.id.resultsPanel);
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
        clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                calculation.setText("");
                result.setText("");
                total = 0.0;
                input = 0.0;
                isFirstInput = true;
                decimalSwitch = true;
                isTotal = false;
                return true;
            }
        });
    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.one:
                newInput();
                result.append("1");
                scrollFromRight();
                break;

            case R.id.two:
                newInput();
                result.append("2");
                scrollFromRight();
                break;

            case R.id.three:
                newInput();
                result.append("3");
                scrollFromRight();
                break;

            case R.id.four:
                newInput();
                result.append("4");
                scrollFromRight();
                break;

            case R.id.five:
                newInput();
                result.append("5");
                scrollFromRight();
                break;

            case R.id.six:
                newInput();
                result.append("6");
                scrollFromRight();
                break;

            case R.id.seven:
                newInput();
                result.append("7");
                scrollFromRight();
                break;

            case R.id.eight:
                newInput();
                result.append("8");
                scrollFromRight();
                break;

            case R.id.nine:
                newInput();
                result.append("9");
                scrollFromRight();
                break;

            case R.id.zero:
                newInput();
                result.append("0");
                scrollFromRight();
                break;

            case R.id.plus:
                if (!result.getText().toString().equalsIgnoreCase("")) {
                    input = Double.parseDouble(result.getText().toString());
                } else {
                    input = 0;
                }

                calculation.append(result.getText());
                calculation.append(getUnicode(0x002B));

                lockResultPanel();

                result(1);

                result.setText(nf.format(total));

                scrollFromRight();

                input = 0.0;

                break;

            case R.id.minus:
                if (!result.getText().toString().equalsIgnoreCase("")) {
                    input = Double.parseDouble(result.getText().toString());
                } else {
                    input = 0;
                }

                if (isFirstInput) {
                    total = input * 2;
                }

                calculation.append(result.getText());
                calculation.append(getUnicode(0x002D));

                result(2);

                lockResultPanel();

                result.setText(nf.format(total));

                scrollFromRight();

                input = 0.0;

                isFirstInput = false;

                break;

            case R.id.multiply:
//                if (!calculation.getText().toString().isEmpty()){
//                    if (!calculation.getText().toString().endsWith(getUnicode(0x00D7)) &&
//                            !calculation.getText().toString().endsWith(getUnicode(0x00F7))) {
                input = Double.parseDouble(result.getText().toString());
                if (isFirstInput) {
                    Log.d("lol", "first input");
                    total = input;
                    calculation.append(result.getText());
                    calculation.append(getUnicode(0x00D7));
                    result(3);
                    isFirstInput = false;
                    input = 0.0;
                    result.setText("");
                } else {
                    Log.d("lol", "second input");
                    calculation.append(result.getText());
                    calculation.append(getUnicode(0x00D7));
                    result(3);
                    lockResultPanel();
                    result.setText(nf.format(total));
                    scrollFromRight();
                    input = 0.0;
                }
//
//                    }
//                }

                break;

            case R.id.divide:
                input = Double.parseDouble(result.getText().toString());
                if (isFirstInput) {
                    total = input;
                    calculation.append(result.getText());
                    calculation.append(getUnicode(0x00F7));
                    result(4);
                    isFirstInput = false;
                    input = 0.0;
                    result.setText("");
                } else {
                    calculation.append(result.getText());
                    calculation.append(getUnicode(0x00F7));
                    result(4);
                    lockResultPanel();
                    result.setText(nf.format(total));
                    scrollFromRight();
                    input = 0.0;
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

                if (!isTotal) {
                    int length = result.getText().length();
                    if (length > 0) {
                        result.getText().delete(length - 1, length);
                    }
                }
                break;

            case R.id.eq:
                input = Double.parseDouble(result.getText().toString());
                switch (clicked) {
                    case 1:
                        total += input;
                        break;
                    case 2:
                        total -= input;
                        break;
                    case 3:
                        total *= input;
                        break;
                    case 4:
                        total /= input;
                        break;
                }
                lockResultPanel();
                isFirstInput = true;
                calculation.setText("");
                result.setText(nf.format(total));
                break;
        }

    }

    private double result(int operand) {

        switch (operand) {
            case 1:
                clicked = 1;
                total += input;

                break;
            case 2:
                clicked = 2;
                total -= input;
                break;
            case 3:
                clicked = 3;
                if (!isFirstInput) {
                    total *= input;
                }

                break;
            case 4:
                clicked = 4;
                if (!isFirstInput) {
                    total /= input;
                }
                break;
            default:
                break;
        }
//        Log.d("input1", nf.format(input1));
        Log.d("input", nf.format(input));
        Log.d("total", nf.format(total));

        return total;
    }

    private void lockResultPanel() {
        isTotal = true;
        result.setText("");
    }

    private void newInput() {
        if (isTotal) {
            result.setText("");
        }
        isTotal = false;
    }

    private void scrollFromRight() {
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
        resultPanel.postDelayed(new Runnable() {
            public void run() {
                resultPanel.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
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
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private String getUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

}
