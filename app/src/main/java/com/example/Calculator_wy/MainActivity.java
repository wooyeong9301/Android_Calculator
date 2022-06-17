package com.example.Calculator_wy;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.textView);
        result = findViewById(R.id.textView2);
//        display.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (getString(R.string.display).equals(display.getText().toString()))
//                    display.setText((""));
//            }
//        });
        display.setShowSoftInputOnFocus(false);

    }

    private void updateText(String strToAdd) {
        List<String> symbols = Arrays.asList("÷", "×", "+", "-");
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (oldStr.length() + strToAdd.length() > 50) {
            System.out.println("입력 제한 초과");
        }
        if (getString(R.string.display).equals(display.getText().toString())) {
            if (strToAdd == "0" || strToAdd == "00" || strToAdd == "000") {
                display.setText("0");
                display.setSelection(cursorPos);
            } else if (symbols.contains(strToAdd)) {

            } else {
                display.setText(strToAdd);
                display.setSelection(cursorPos + 1);
            }
        }
        else if (symbols.contains((strToAdd))) {
            if (display.getSelectionStart() == 0) {

            }
            else if (symbols.contains(leftStr.substring(leftStr.length()-1))) {
                String new_left = leftStr.substring(0, leftStr.length()-1);
                display.setText(String.format("%s%s%s", new_left, strToAdd, rightStr));
                display.setSelection(cursorPos);
            } else if (rightStr.length() >= 1 && symbols.contains(rightStr.substring(0, 1))) {
                String new_right = rightStr.substring(1);
                display.setText(String.format("%s%s%s", leftStr, strToAdd, new_right));
                display.setSelection(cursorPos + 1);
            }
            else {
                display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
                display.setSelection(cursorPos + 1);
            }
        }

        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + strToAdd.length());
        }
    }

    private void updateSymbol(String symbolToAdd) {
        List<String> symbols = Arrays.asList("÷", "×", "+", "-");
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (symbols.contains(symbolToAdd)) {

        }
    }


    public void zeroBTN(View view) {
        updateText("0");
    }

    public void oneBTN(View view) {
        updateText("1");
    }

    public void twoBTN(View view) {
        updateText("2");
    }

    public void threeBTN(View view) {
        updateText("3");
    }

    public void fourBTN(View view) {
        updateText("4");
    }

    public void fiveBTN(View view) {
        updateText("5");
    }

    public void sixBTN(View view) {
        updateText("6");
    }

    public void sevenBTN(View view) {
        updateText("7");
    }

    public void eightBTN(View view) {
        updateText("8");
    }

    public void nineBTN(View view) {
        updateText("9");
    }

    public void doubleZeroBTN(View view) {
        updateText("00");
    }

    public void tripleZeroBTN(View view) {
        updateText("000");
    }

    public void divideBTN(View view) {
        updateText("÷");
    }

    public void multipleBTN(View view) {
        updateText("×");
    }

    public void addBTN(View view) {
        updateText("+");
    }

    public void subtractBTN(View view) {
        updateText("-");
    }

    public void clearBTN(View view) {
//        updateText("0");
        display.setText("0");
    }

    public void percentBTN(View view) {
//        updateText("0");
        display.setText("%");
    }

    public void equalsBTN(View view) {

        String userExp = display.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        Expression exp = new Expression(userExp);
//        int ii;
//        ii = (int) exp.calculate();
        String answer = String.valueOf(exp.calculate());
//        display.setText(answer.substring(0, answer.length()-2));
//        display.setSelection(answer.substring(0, answer.length()-2).length());
        if (answer.length() >= 3 && answer.substring(answer.length()-2).equals(".0")) {
            String short_answer = answer.substring(0, answer.length()-2);
            BigDecimal bigDeci = new BigDecimal(short_answer);
            display.setText(bigDeci.toPlainString());
            display.setSelection(bigDeci.toPlainString().length());
        } else {
            BigDecimal bigDeci = new BigDecimal(answer);
            display.setText(bigDeci.toPlainString());
            display.setSelection(bigDeci.toString().length());
        }
    }

    public void backBTN(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }
}