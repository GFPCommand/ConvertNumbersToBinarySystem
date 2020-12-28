package com.example.converternum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.math.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView result;
    private EditText input;
    private Button clearButton, convertButton;
    private RadioButton octalSystem, decimalSystem, hexadecimalSystem;
    private String inputData;
    private String outputResult = "";
    private boolean octal, decimal, hexadecimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        input = findViewById(R.id.inputText);
        clearButton = findViewById(R.id.clear);
        convertButton = findViewById(R.id.convert);

        octalSystem = findViewById(R.id.octal);
        decimalSystem = findViewById(R.id.decimal);
        hexadecimalSystem = findViewById(R.id.hexadecimal);

        clearButton.setOnClickListener(v -> {
            input.setText("");
        });

        convertButton.setOnClickListener(this);

        octalSystem.setOnClickListener(v -> {
            octal = true;
            decimal = false;
            hexadecimal = false;
        });
        decimalSystem.setOnClickListener(v -> {
            octal = false;
            decimal = true;
            hexadecimal = false;
        });
        hexadecimalSystem.setOnClickListener(v -> {
            octal = false;
            decimal = false;
            hexadecimal = true;
        });
    }

    @Override
    public void onClick(View view){
        try {
            inputData = input.getText().toString();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }

        if (input.length() == 0) {
            Toast.makeText(getApplicationContext(), "Input field can't be empty", Toast.LENGTH_LONG).show();
            result.setText("");
        } else {
            if (octal){
                convertOctalToBinary(inputData);
            } else if (decimal){
                convertDecimalToBinary(Integer.parseInt(inputData));
            } else if (hexadecimal){
                convertHexadecimalToBinary(inputData);
            }
        }
    }

    private void convertOctalToBinary(String num){
        BigInteger temp;
        temp = new BigInteger(num, 8);
        convertDecimalToBinary(temp.intValue());
        convertDecimalToBinary(temp.intValue());
    }

    private void convertDecimalToBinary(int num){
        int temp;
        String convertResult = "";
        while (num != 0){
            temp = num % 2;
            convertResult = temp + convertResult;
            num /= 2;
        }
        result.setText(convertResult);
    }

    private void convertHexadecimalToBinary(String num){
        BigInteger temp;
        temp = new BigInteger(num, 16);
        convertDecimalToBinary(temp.intValue());
    }
}