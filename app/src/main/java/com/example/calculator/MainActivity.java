package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicatio.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "MainActivity";
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        tv_result = (EditText) findViewById(R.id.tv_result);
        tv_result.setMovementMethod(new ScrollingMovementMethod() );

        findViewById(R.id.num_zero).setOnClickListener(this);
        findViewById(R.id.num_one).setOnClickListener(this);
        findViewById(R.id.num_two).setOnClickListener(this);
        findViewById(R.id.num_three).setOnClickListener(this);
        findViewById(R.id.num_four).setOnClickListener(this);
        findViewById(R.id.num_five).setOnClickListener(this);
        findViewById(R.id.num_six).setOnClickListener(this);
        findViewById(R.id.num_seven).setOnClickListener(this);
        findViewById(R.id.num_eight).setOnClickListener(this);
        findViewById(R.id.num_nine).setOnClickListener(this);

        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_mul).setOnClickListener(this);
        findViewById(R.id.btn_Div).setOnClickListener(this);
        findViewById(R.id.btn_sqrt).setOnClickListener(this);

        findViewById(R.id.btn_dot).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_equal).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    int resid = view.getId();
    String inputText;
        if (resid == R.id.btn_sqrt) {
        inputText = "√";
    } else {
        inputText = ((TextView) view).getText().toString();
    }
        Log.d(TAG, "resid="+resid+",inputText="+inputText);
        if (resid == R.id.btn_clear) {
        clear("");
    } else if (resid == R.id.btn_cancel) {
        if (operator.equals("")) {
            if (firstNum.length() == 1) {
                firstNum = "0";
            } else if (firstNum.length() > 0) {
                firstNum = firstNum.substring(0, firstNum.length() - 1);
            } else {
                Toast.makeText(this, "没有可取消的数字了", Toast.LENGTH_SHORT).show();
                return;
            }
            showText = firstNum;
            tv_result.setText(showText);
        } else {
            if (nextNum.length() == 1) {
                nextNum = "";
            } else if (nextNum.length() > 0) {
                nextNum = nextNum.substring(0, nextNum.length() - 1);
            } else {
                Toast.makeText(this, "没有可取消的数字了", Toast.LENGTH_SHORT).show();
                return;
            }
            showText = showText.substring(0, showText.length() - 1);
            tv_result.setText(showText);
        }
    } else if (resid == R.id.btn_equal) {
        if (operator.length() == 0 || operator.equals("＝")) {
            Toast.makeText(this, "请输入运算符", Toast.LENGTH_SHORT).show();
            return;
        } else if (nextNum.length() <= 0) {
            Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
            return;
        }
        if (caculate()) {
            operator = inputText;
            showText = showText + "=" + result;
            tv_result.setText(showText);
        } else {
            return;
        }
    } else if (resid == R.id.btn_plus || resid == R.id.btn_minus
                || resid == R.id.btn_mul || resid == R.id.btn_Div ) {
        if (firstNum.length() <= 0) {
            Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
            return;
        }
        if (operator.length() == 0 || operator.equals("＝")
                || operator.equals("√")) {
            operator = inputText;// 操作符
            showText = showText + operator;
            tv_result.setText(showText);
        } else {
            Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
            return;
        }
    } else if (resid == R.id.btn_sqrt) {
        if (firstNum.length() <= 0) {
            Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Double.parseDouble(firstNum) < 0) {
            Toast.makeText(this, "开根号的数值不能小于0", Toast.LENGTH_SHORT).show();
            return;
        }
        result = String.valueOf(Math.sqrt(Double.parseDouble(firstNum)));
        firstNum = result;
        nextNum = "";
        operator = inputText;
        showText = showText + "√=" + result;
        tv_result.setText(showText);
        Log.d(TAG, "result="+result+",firstNum="+firstNum+",operator="+operator);
    } else {
        if (operator.equals("＝")) {
            operator = "";
            firstNum = "";
            showText = "";
        }
        if (resid == R.id.btn_dot) {
            inputText = ".";
        }
        if (operator.equals("")) {
            firstNum = firstNum + inputText;
        } else {
            nextNum = nextNum + inputText;
        }
        showText = showText + inputText;
        tv_result.setText(showText);
    }
        return;
}

    private String operator = "";
    private String firstNum = "";
    private String nextNum = "";
    private String result = ""; // 当前计算结果
    private String showText = ""; // 显示的文本内容

    // 开始加减乘除四则运算
    private boolean caculate() {
               if (operator.equals("+")) {
            result = String.valueOf(Arith.add(firstNum, nextNum));
        } else if (operator.equals("-")) {
            result = String.valueOf(Arith.sub(firstNum, nextNum));
        } else if (operator.equals("×")) {
            result = String.valueOf(Arith.mul(firstNum, nextNum));
        } else if (operator.equals("÷")) {
            if ("0".equals(nextNum)) {
                Toast.makeText(this, "被除数不能为零", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                result = String.valueOf(Arith.div(firstNum, nextNum));
            }
        }
        firstNum = result;
        nextNum = "";
        return true;
    }

    // 清空并初始化
    private void clear(String text) {
        showText = text;
        tv_result.setText(showText);
        operator = "";
        firstNum = "";
        nextNum = "";
        result = "";
    }
}
