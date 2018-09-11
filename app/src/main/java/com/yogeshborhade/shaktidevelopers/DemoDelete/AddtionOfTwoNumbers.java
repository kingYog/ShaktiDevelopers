package com.yogeshborhade.shaktidevelopers.DemoDelete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yogeshborhade.shaktidevelopers.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class AddtionOfTwoNumbers extends AppCompatActivity {

    EditText editTextValueOne, editTextValueTwo, editTextResult;
    Button buttonMultiplicationValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtion_of_two_numbers);

        editTextValueOne=(EditText)findViewById(R.id.mvalueOne);
        editTextValueTwo=(EditText)findViewById(R.id.mvalueOne);
        editTextResult=(EditText)findViewById(R.id.mresult);
        buttonMultiplicationValues=(Button) findViewById(R.id.mbtnMulValus);

        NumberFormat formaterScientificToReguler= new DecimalFormat("#0.00");

        buttonMultiplicationValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double valueOne=Double.valueOf(editTextValueOne.getText().toString());
                double valueTwo=Double.valueOf(editTextValueTwo.getText().toString());

                double Result=valueOne*valueTwo;
                editTextResult.setText(String.valueOf(Result));

            }
        });

    }
}
