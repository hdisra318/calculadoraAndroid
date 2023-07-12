package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /** Botones */
    MaterialButton buttonC, buttonParenthesisOpen, buttonParenthesisClose;
    MaterialButton buttonMult, buttonDiv, buttonSum, buttonEq, buttonRest;
    MaterialButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, buttonDot;

    /** TextViews */
    TextView tvResult, tvSolution;

    /** Bandera sobre el punto decimal **/
    boolean decimalPuesto = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asignId(buttonC, R.id.btn_C);
        asignId(buttonParenthesisOpen, R.id.btn_parenthesis_open);
        asignId(buttonParenthesisClose, R.id.btn_parenthesis_close);
        asignId(buttonMult, R.id.btn_mult);
        asignId(buttonDiv, R.id.btn_div);
        asignId(buttonSum, R.id.btn_sum);
        asignId(buttonEq, R.id.btn_eq);
        asignId(buttonRest, R.id.btn_rest);
        asignId(button1, R.id.btn_1);
        asignId(button2, R.id.btn_2);
        asignId(button3, R.id.btn_3);
        asignId(button4, R.id.btn_4);
        asignId(button5, R.id.btn_5);
        asignId(button6, R.id.btn_6);
        asignId(button7, R.id.btn_7);
        asignId(button8, R.id.btn_8);
        asignId(button9, R.id.btn_9);
        asignId(button0, R.id.btn_0);
        asignId(buttonDot, R.id.btn_dot);

        tvResult = findViewById(R.id.tv_result);
        tvSolution = findViewById(R.id.tv_solution);

    }

    /**
     * Asigna los id's
     */
    private void asignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;

        String buttonText = button.getText().toString();
        String dataToCalculate = tvSolution.getText().toString();
        String result;


        if(buttonText.equals("C")) {

            tvSolution.setText("");
            tvResult.setText("0");
            decimalPuesto = false;
            return;

        }

        if(buttonText.equals("=")) {

            result = calculateResult(dataToCalculate);

            if (result.length() == 0)
                result = dataToCalculate;

            tvSolution.setText(result);
            tvResult.setText(result);
            decimalPuesto = false;
            return;
        }


        if(dataToCalculate.equals("0")) {

            dataToCalculate = buttonText;

        } else {

            if(buttonText.equals(".") && !decimalPuesto) {

                dataToCalculate = dataToCalculate + buttonText;
                decimalPuesto = true;

            }else if(buttonText.equals(".") && decimalPuesto) {

                dataToCalculate = dataToCalculate + "";

            } else {

                dataToCalculate = dataToCalculate + buttonText;

                if(!isNumber(buttonText.charAt(0)))
                    decimalPuesto = false;

            }


        }

        if(!dataToCalculate.toString().endsWith("="))
            tvSolution.setText(dataToCalculate);

        
    }


    private String calculateResult(String data) {

        // Dividiendo los numeros
        ArrayList<String> numStr = new ArrayList<>();
        ArrayList<Double> numDoub = new ArrayList<>();
        String num = "";
        String result = "";
        double resD = 0;

        for(int i = 0; i<data.length(); ++i){

            if(!isNumber(data.charAt(i))){
                numDoub.add(Double.parseDouble(num));
                num = "";
            }else {
                num += data.charAt(i);
            }

        }

        numDoub.add(Double.parseDouble(num));

        for(int i = 0; i<data.length(); ++i) {

            if(data.charAt(i) == '+'){

                resD = numDoub.get(0) + numDoub.get(1);
                result = ""+resD;

            } else if(data.charAt(i) == '-'){

                resD = numDoub.get(0) - numDoub.get(1);
                result = ""+resD;

            } else if(data.charAt(i) == 'x'){

                resD = numDoub.get(0) * numDoub.get(1);
                result = ""+resD;

            } else if(data.charAt(i) == '/') {

                resD = numDoub.get(0) / numDoub.get(1);
                result = "" + resD;

            }

        }

        return result;
    }


    private boolean isNumber(char c) {

        return c != '+' && c != '-' && c != 'x' && c != '/' && c != '(' && c != ')';
    }


}