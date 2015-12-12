package com.it2107.assignment.assignment;

import android.app.Activity;
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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button one, two, three, four, five, six, seven, eight, nine, zero, plus, minus, multiply, divide, clear, eq, decimal;
    Button history;
    EditText calculationET;
    EditText resultET;
    HorizontalScrollView calculationPanel, resultPanel;
    boolean decimalSwitch = true; //enables/disables decimals
    double total = 0.0;
    double input = 0.0;
    boolean isTotal = false; //check is final result or not (after eq is invoked)
    boolean isFirstInput = true; //check if is first time input
    boolean isAlternateInput = true;
    boolean isFromHistory = false;
    int clicked; //operand selector
    ArrayList<String> calculationsArr = new ArrayList<String>();
    ArrayList<String> resultsArr = new ArrayList<String>();
    NumberFormat nf = new DecimalFormat("###,###.###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        calculationET = (EditText) findViewById(R.id.calculation);
        resultET = (EditText) findViewById(R.id.result);
        calculationET.setTextColor(0xff757575);
        resultET.setTextColor(0xff333333);
        calculationPanel = (HorizontalScrollView) findViewById(R.id.calculationPanel);
        resultPanel = (HorizontalScrollView) findViewById(R.id.resultsPanel);
        addListenerOnButton();
        history = (Button) findViewById(R.id.history);
        history.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MemoryListActivity.class);
                intent.putStringArrayListExtra("calculation", calculationsArr);
                intent.putStringArrayListExtra("total", resultsArr);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (1): {
                if (resultCode == Activity.RESULT_OK) {
                    if (data.getStringExtra("total") != null && data.getStringExtra("calculation") != null) {
                        calculationET.setText(data.getStringExtra("calculation"));
                        resultET.setText(data.getStringExtra("total"));
                        isFromHistory = true;
                        input = 0.0;
                        total = 0.0;
                        isFirstInput = false;
                    }
                    break;
                }
            }
        }
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
                calculationET.setText("");
                resultET.setText("");
                total = 0.0;
                input = 0.0;
                isFirstInput = true;
                decimalSwitch = true;
                isTotal = false;
                isAlternateInput = true;
                return true;
            }
        });
    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.one:
                newInput();
                resultET.append("1");
                break;

            case R.id.two:
                newInput();
                resultET.append("2");
                break;

            case R.id.three:
                newInput();
                resultET.append("3");
                break;

            case R.id.four:
                newInput();
                resultET.append("4");
                break;

            case R.id.five:
                newInput();
                resultET.append("5");
                break;

            case R.id.six:
                newInput();
                resultET.append("6");
                break;

            case R.id.seven:
                newInput();
                resultET.append("7");
                break;

            case R.id.eight:
                newInput();
                resultET.append("8");
                break;

            case R.id.nine:
                newInput();
                resultET.append("9");
                break;

            case R.id.zero:
                newInput();
                resultET.append("0");
                break;

            case R.id.plus:
                calculate(0x002B, 1);
                break;

            case R.id.minus:
                calculate(0x002D, 2);
                break;

            case R.id.multiply:
                calculate(0x00D7, 3);
                break;

            case R.id.divide:
                calculate(0x00F7, 4);
                break;

            case R.id.decimal:
                if (!resultET.getText().toString().isEmpty() && !isTotal) {
                    if (!resultET.getText().toString().endsWith(".") && decimalSwitch) {
                        resultET.append(".");
                        decimalSwitch = false;
                    }
                }
                break;

            case R.id.clear:

                if (!isTotal) {
                    int length = resultET.getText().length();
                    if (length > 0) {
                        resultET.getText().delete(length - 1, length);
                    }
                }
                break;

            case R.id.eq:
                input = Double.parseDouble(resultET.getText().toString().replaceAll(",", ""));
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
                calculationsArr.add(calculationET.getText().toString() + resultET.getText().toString());
                calculationET.setText("");
                resultsArr.add(nf.format(total));
                resultET.setText(nf.format(total));
                decimalSwitch = true;
                break;
        }
        scrollFromRight();
    }

    private void calculate(int unicode, int operand) {
        if (!resultET.getText().toString().equalsIgnoreCase("")) {
            input = Double.parseDouble(resultET.getText().toString().replaceAll(",", ""));
        } else {
            input = 0;
        }

        if (!isFromHistory) {
            calculationET.append(resultET.getText());
        }

        calculationET.append(getUnicode(unicode));

        if (isFirstInput) {
            total = input;
            clicked = operand;
            result(clicked);
            isFirstInput = false;
            resultET.setText("");
        } else {
            result(clicked);
            resultET.setText(nf.format(total));
            clicked = operand;
        }
        input = 0.0;
        lockResultPanel();
        isFromHistory = false;
    }

    private double result(int clicked) {
        if (!isFirstInput) {

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
                default:
                    break;
            }
        }
        decimalSwitch = true;
        return total;
    }

    private void lockResultPanel() {
        isTotal = true;
    }

    private void newInput() {
        if (isTotal) {
            resultET.setText("");
        }
        isTotal = false;
    }

    private void scrollFromRight() {
        calculationPanel.postDelayed(new Runnable() {
            public void run() {
                calculationPanel.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
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
