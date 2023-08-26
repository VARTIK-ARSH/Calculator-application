package com.google.calculator1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result,solution;
    MaterialButton buttonc,buttondot,buttondel,buttondiv,buttonmul,buttonmin,buttonplus,buttonequals,buttonopenbracket,buttonclosebracket;
    MaterialButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=findViewById(R.id.result_t);
        solution=findViewById(R.id.solution_t);

        assignId(buttonc,R.id.button_C);
        assignId(buttonopenbracket,R.id.button_openbracket);
        assignId(buttonclosebracket,R.id.button_closebracket);
        assignId(buttondiv,R.id.button_divide);
        assignId(buttonmul,R.id.button_multiply);
        assignId(buttonplus,R.id.button_plus);
        assignId(buttonmin,R.id.button_minus);
        assignId(buttonequals,R.id.button_equalsto);
        assignId(btn0,R.id.button_zero);
        assignId(btn1,R.id.button_one);
        assignId(btn2,R.id.button_two);
        assignId(btn3,R.id.button_three);
        assignId(btn4,R.id.button_four);
        assignId(btn5,R.id.button_five);
        assignId(btn6,R.id.button_six);
        assignId(btn7,R.id.button_seven);
        assignId(btn8,R.id.button_eight);
        assignId(btn9,R.id.button_nine);
        assignId(btn0,R.id.button_zero);
        assignId(buttondel,R.id.button_X);
        assignId(buttondot,R.id.button_dot);

    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }







    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution.getText().toString();

        if(buttonText.equals("C")){
            solution.setText("");
            result.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution.setText(result.getText());
            return;
        }
        if(buttonText.equals("X")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solution.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            result.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}

