package com.example.calculate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //数字

    private Button bt_num1;
    private Button bt_num2;
    private Button bt_num3;
    private Button bt_num4;
    private Button bt_num5;
    private Button bt_num6;
    private Button bt_num7;
    private Button bt_num8;
    private Button bt_num9;
    private Button bt_num0;
    //运算符
    private Button bt_add;//+
    private Button bt_dec;//-
    private Button bt_mul;//×
    private Button bt_div;//÷
    private Button bt_point;// .
    private Button bt_equal;//=
    private Button bt_c;//clear按钮
    private Button bt_del;//del按钮
    private Button bt_lr;
    //+/-按钮
    private Button bt_gen;//根号
    private TextView textView;
    boolean clear_flag; //清除标识
    int lr_flag;//+/-标识

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_num0 = findViewById(R.id.bt_num0);
        bt_num1 = findViewById(R.id.bt_num1);
        bt_num2 = findViewById(R.id.bt_num2);
        bt_num3 = findViewById(R.id.bt_num3);
        bt_num4 = findViewById(R.id.bt_num4);
        bt_num5 = findViewById(R.id.bt_num5);
        bt_num6 = findViewById(R.id.bt_num6);
        bt_num7 = findViewById(R.id.bt_num7);
        bt_num8 = findViewById(R.id.bt_num8);
        bt_num9 = findViewById(R.id.bt_num9);


        bt_add = findViewById(R.id.bt_add);
        bt_dec = findViewById(R.id.bt_dec);
        bt_mul = findViewById(R.id.bt_mul);
        bt_del = (Button) findViewById(R.id.bt_del);
        bt_div = findViewById(R.id.bt_div);
        bt_point = findViewById(R.id.bt_point);
        bt_equal = findViewById(R.id.bt_equal);
        bt_c = findViewById(R.id.bt_c);
        bt_lr=findViewById(R.id.bt_lr);
        bt_gen=findViewById(R.id.bt_gen);

        textView = (TextView) findViewById(R.id.et_input);//结果集

        //添加点击事件
        bt_num0.setOnClickListener(this::onClick);
        bt_num1.setOnClickListener(this::onClick);
        bt_num2.setOnClickListener(this::onClick);
        bt_num3.setOnClickListener(this::onClick);
        bt_num4.setOnClickListener(this::onClick);
        bt_num5.setOnClickListener(this::onClick);
        bt_num6.setOnClickListener(this::onClick);
        bt_num7.setOnClickListener(this::onClick);
        bt_num8.setOnClickListener(this::onClick);
        bt_num9.setOnClickListener(this::onClick);

        bt_add.setOnClickListener(this::onClick);
        bt_dec.setOnClickListener(this::onClick);
        bt_mul.setOnClickListener(this::onClick);
        bt_div.setOnClickListener(this::onClick);
        bt_del.setOnClickListener(this::onClick);
        bt_point.setOnClickListener(this::onClick);
        bt_equal.setOnClickListener(this::onClick);
        bt_c.setOnClickListener(this::onClick);
        bt_lr.setOnClickListener(this::onClick);
        bt_gen.setOnClickListener(this::onClick);

    }

    public void onClick(View view) {
        //获取文本内容
        int count=0;
        String input = textView.getText().toString();
        switch (view.getId()) {
            case R.id.bt_num0:
            case R.id.bt_num1:
            case R.id.bt_num2:
            case R.id.bt_num3:
            case R.id.bt_num4:
            case R.id.bt_num5:
            case R.id.bt_num6:
            case R.id.bt_num7:
            case R.id.bt_num8:
            case R.id.bt_num9:
            case R.id.bt_gen:
            case R.id.bt_point:
                if (clear_flag) {
                    clear_flag = false;
                    textView.setText("");
                }
                textView.setText(input + ((Button) view).getText());
                //不清除，在输入输出框中显示按钮文本
                break;
            case R.id.bt_add:
            case R.id.bt_dec:
            case R.id.bt_mul:
            case R.id.bt_div:
                if (clear_flag) {
                    clear_flag = false;
                    input = "";
                    textView.setText("");
                }
                textView.setText(input + ((Button) view).getText());
                break;
            case R.id.bt_c:
                clear_flag = false;
                input = "";
                textView.setText("");
                break;
            case R.id.bt_del:
                if (clear_flag) {
                    clear_flag = false;
                    input = "";
                    textView.setText("");
                } else if (input != null || !input.equals("")) {
                    textView.setText(input.substring(0, input.length() - 1));
                }
                break;
            case R.id.bt_equal:
                getResult();
                break;
            case R.id.bt_lr:
                if(lr_flag==0){
                    //第一次单击触发
                    count=1;
                    textView.setText(input +"+");
                    lr_flag=1;
                }
                else{
                    textView.setText(input+"-");
                    lr_flag=0;
                    //第二次单机触发
                }

                break;
        }

    }

    private void getResult() {
        String exp = textView.getText().toString();
        char arr[] = exp.toCharArray();
        String ss;
        double a;
        int c1,c2;
        int c=0,flag=0;
        int count1=0;
        int count2=0;
        int cc = exp.length();
        int p=0;
        double[] op_num = new double[100];//操作数
        char[] op = new char[100];//符号
        for (int i = 0; i <exp.length(); i++) {
            if(i==0){
                //第一位为符号或者数字
                if((arr[i]>='0'&&arr[i]<='9')||(arr[i]=='+')){
                    //第一个数为正数
                    c1=i;
                    for(int j=i+1;j<exp.length();j++){
                        if(arr[j]== '-' ||arr[j]=='+'||arr[j]=='×'||arr[j]=='÷') {
                            //第一个正数到第一个符号直接的数
                            c2=j;
                            ss=exp.substring(c1,c2);
                            op_num[count1]=Double.valueOf(ss);
                            count1++;
                            flag=1;
                            break;
                        }
                    }
                    if(flag==0){
                        //只输入了一个数
                        ss=exp.substring(c1,cc);
                        op_num[count1]=Double.valueOf(ss);
                        textView.setText(op_num[count1]+"");
                        flag=1;
                        break;
                    }
                }
                if(arr[i]=='-') {
                    //第一个数为负数
                    c1 = i + 1;
                    for (int j = i + 1; j < exp.length(); j++) {
                        //寻找下一个操作符
                        if (arr[j] == '-' || arr[j] == '+' || arr[j] == '×' || arr[j] == '÷') {
                            c2 = j;
                            ss = exp.substring(c1, c2);
                            op_num[count1] = Double.valueOf(ss);
                            op_num[count1] = 0 - op_num[count1];
                            count1++;
                            flag=1;
                            break;
                        }
                    }
                    if(flag==0){
                        //只输入了一个数
                        ss=exp.substring(c1,cc);
                        op_num[count1]=Double.valueOf(ss);
                        op_num[count1] = 0 - op_num[count1];
                        textView.setText(op_num[count1]+"");
                        break;
                    }
                }
                if(arr[i]=='√'){
                    //如果第一个为根号
                    op[count2]=arr[i];
                    count2++;
                    if(arr[i+1]=='-'){
                        //根号下为负数
                        Toast.makeText(MainActivity.this,"根号下不能小于0,请重新输入",Toast.LENGTH_SHORT).show();
                        textView.setText("");
                        break;
                    }
                    if(arr[i+1]=='+'||(arr[i+1]>='0'&&arr[i+1]<='9')){
                        //根号下为正号或无符号
                        c1=i+1;
                        for(int j=i+1;j<exp.length();j++) {
                            if(arr[j] == '-' || arr[j] == '+' || arr[j] == '×' || arr[j] == '÷' ){
                                c2=j;
                                ss = exp.substring(c1, c2);
                                op_num[count1] = Double.valueOf(ss);
                                count1++;
                                op_num[count1] = Double.valueOf(ss);
                                count1++;
                                break;

                            }
                            if(j == exp.length() - 1){
                                //是最后一个
                                c2 = j+1;
                                ss = exp.substring(c1, c2);
                                op_num[count1] = Double.valueOf(ss);
                                count1++;
                            }
                        }
                    }
                    else{
                        //根号下不是加减号也不是数字
                        Toast.makeText(MainActivity.this,"输入错误,请重新输入",Toast.LENGTH_SHORT).show();
                        textView.setText("");
                        break;
                    }
                }
               if(arr[i]=='×'||arr[i]=='÷'){
                   textView.setText("0.0");
                   break;
               }
            }
            if ((arr[i] == '-' ||arr[i]=='+'||arr[i]=='×'||arr[i]=='÷'||arr[i]=='√')&&(i!=0)) {
                //当为中间的数时
                c1=i+1;
                for(int j=i+1;j<exp.length();j++){
                    //寻找下一个符号
                    if((j==i+1)&&(arr[j]== '-' ||arr[j]=='+'||arr[j]=='×'||arr[j]=='÷')){
                        //出现连个非法连续的符号
                        Toast.makeText(MainActivity.this,"输入不合法,请重新输入",Toast.LENGTH_SHORT).show();
                        textView.setText("");
                        return;
                    }
                    if(arr[j]== '-' ||arr[j]=='+'||arr[j]=='×'||arr[j]=='÷'||arr[j]=='√'||j==exp.length()-1){
                        if(j!=exp.length()-1){
                            //不是最后一个数
                            c2=j;
                            ss=exp.substring(c1,c2);
                            op_num[count1]=Double.valueOf(ss);
                        }
                        else{
                            //是最后一个数
                            c2=j+1;
                            ss=exp.substring(c1,c2);
                            op_num[count1]=Double.valueOf(ss);
                        }
                        count1++;
                        break;
                    }
                }
                if(i!=0){
                    //不是第一 但是是符号
                    op[count2]=arr[i];
                    count2++;
                }
            }

        }
        for(int i=0;i<count1;i++){
            if(op[c]=='-'){
                op_num[i+1]=op_num[i]-op_num[i+1];
                c++;
            }
            if(op[c]=='+'){
                op_num[i+1]=op_num[i]+op_num[i+1];
                c++;
            }
            if(op[c]=='×'){
                op_num[i+1]=op_num[i]*op_num[i+1];
                c++;
            }
            if(op[c]=='÷'){
                if(op_num[i+1]==0){
                    Toast.makeText(MainActivity.this,"除数不能为0,请重新输入",Toast.LENGTH_SHORT).show();
                    textView.setText("");
                    break;
                }
                else{
                    op_num[i+1]=op_num[i]/op_num[i+1];
                    c++;
                }
            }
            if(op[c]=='√'){
                op_num[i]=Math.sqrt(op_num[i]);
                op_num[i+1]=op_num[i];
                c++;
            }
            p=i;
        }
        textView.setText(op_num[p]+"");
    }


}