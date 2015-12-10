package com.it2107.assignment.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
                break;

            case R.id.two:
                newInput();
                result.append("2");
                break;

            case R.id.three:
                newInput();
                result.append("3");
                break;

            case R.id.four:
                newInput();
                result.append("4");
                break;

            case R.id.five:
                newInput();
                result.append("5");
                break;

            case R.id.six:
                newInput();
                result.append("6");
                break;

            case R.id.seven:
                newInput();
                result.append("7");
                break;

            case R.id.eight:
                newInput();
                result.append("8");
                break;

            case R.id.nine:
                newInput();
                result.append("9");
                break;

            case R.id.zero:
                newInput();
                result.append("0");
                break;

            case R.id.plus:
                if (!result.getText().toString().equalsIgnoreCase("")) {
                    input = Double.parseDouble(result.getText().toString());
                } else {
                    input = 0;
                }

                calculation.append(result.getText());
                calculation.append(getUnicode(0x002B));

                if (isFirstInput) {
                    total = input;
                    clicked = 1;
                    result(clicked);
                    isFirstInput = false;
                    input = 0.0;
                    result.setText("");
                } else {
                    result(clicked);
                    result.setText(nf.format(total));
                    input = 0.0;
                    clicked = 1;
                }

                lockResultPanel();

                break;

            case R.id.minus:
                if (!result.getText().toString().equalsIgnoreCase("")) {
                    input = Double.parseDouble(result.getText().toString());
                } else {
                    input = 0;
                }

                calculation.append(result.getText());
                calculation.append(getUnicode(0x002D));

                if (isFirstInput) {
                    total = input;
                    clicked = 2;
                    result(clicked);
                    isFirstInput = false;
                    input = 0.0;
                    result.setText("");
                } else {
                    result(clicked);
                    result.setText(nf.format(total));
                    input = 0.0;
                    clicked = 2;
                }

                lockResultPanel();

                break;

            case R.id.multiply:
//                if (!calculation.getText().toString().isEmpty()){
//                    if (!calculation.getText().toString().endsWith(getUnicode(0x00D7)) &&
//                            !calculation.getText().toString().endsWith(getUnicode(0x00F7))) {
                if (!result.getText().toString().equalsIgnoreCase("")) {
                    input = Double.parseDouble(result.getText().toString());
                } else {
                    input = 0;
                }
                calculation.append(result.getText());
                calculation.append(getUnicode(0x00D7));

                if (isFirstInput) {
                    total = input;
                    clicked = 3;
                    result(clicked);
                    isFirstInput = false;
                    input = 0.0;
                    result.setText("");
                } else {
                    result(clicked);
                    result.setText(nf.format(total));
                    input = 0.0;
                    clicked = 3;
                }
                lockResultPanel();
//
//                    }
//                }

                break;

            case R.id.divide:
                if (!result.getText().toString().equalsIgnoreCase("")) {
                    input = Double.parseDouble(result.getText().toString());
                } else {
                    input = 0;
                }
                calculation.append(result.getText());
                calculation.append(getUnicode(0x00F7));

                if (isFirstInput) {
                    total = input;
                    clicked = 4;
                    result(clicked);
                    isFirstInput = false;
                    input = 0.0;
                    result.setText("");
                } else {
                    result(clicked);
                    result.setText(nf.format(total));
                    input = 0.0;
                    clicked = 4;
                }
                lockResultPanel();
                break;

            case R.id.decimal:
                if (!result.getText().toString().isEmpty() && !isTotal) {
                    if (!result.getText().toString().endsWith(".") && decimalSwitch) {
                        result.append(".");
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
                decimalSwitch = true;
                break;
        }
        scrollFromRight();
    }

    private double result(int clicked) {

        switch (clicked) {
            case 1:
                if (!isFirstInput) {
                    total += input;
                }
                break;
            case 2:
                if (!isFirstInput) {
                    total -= input;
                }
                break;
            case 3:
                if (!isFirstInput) {
                    total *= input;
                }
                break;
            case 4:
                if (!isFirstInput) {
                    total /= input;
                }
                break;
            default:
                break;
        }
        decimalSwitch = true;
        return total;
    }

    private void lockResultPanel() {
        isTotal = true;

    }

    private void newInput() {
        if (isTotal) {
            result.setText("");
        }
        isTotal = false;
    }

    private void scrollFromRight() {
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
