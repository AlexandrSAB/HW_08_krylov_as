package com.example.hw_08_krylov_as;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import androidx.annotation.NonNull;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private EditText display;
    private String currentInput = "0";
    private static final String STATE_CALCULATOR = "calculator_state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        CalculatorState state = new CalculatorState(currentInput);
        instanceState.putSerializable(STATE_CALCULATOR, state);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle InstanceState) {
        super.onRestoreInstanceState(InstanceState);
        CalculatorState state = (CalculatorState) InstanceState.getSerializable(STATE_CALCULATOR);
        if (state != null) {
            currentInput = state.getCurrentInput();
            display.setText(currentInput);
        }
    }


    private void onNumberButtonClick(String number) {
        if (currentInput.equals("0")) {
            currentInput = number;
        }
        else {
            currentInput += number;
        }
        display.setText(currentInput);
    }


    private void initButtonClearClickListener() {
        Button ButtonClear = findViewById(R.id.buttonClear);
        ButtonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput = "0";
                display.setText(currentInput);
            }
        });
    }

    private void initButtonBackSpaceClickListener() {
        Button buttonBackSpace = findViewById(R.id.buttonBackSpace);
        buttonBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty()){
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                    if (currentInput.isEmpty()) {
                        currentInput = "0";
                    }
                }
                display.setText(currentInput);
            }
        });
    }

    private void initButtonPercentClickListener() {
        Button buttonPercent = findViewById(R.id.buttonPercent);
        buttonPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    private void initButtonDivideClickListener() {
        Button buttonDivide = findViewById(R.id.buttonDivide);
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    private void initButton1ClickListener() {
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("1");
            }
        });
    }

    private void initButton2ClickListener() {
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("2");
            }
        });
    }

    private void initButton3ClickListener() {
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("3");
            }
        });
    }

    private void initButton4ClickListener() {
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("4");
            }
        });
    }

    private void initButton5ClickListener() {
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("5");
            }
        });
    }

    private void initButton6ClickListener() {
        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("6");
            }
        });
    }

    private void initButton7ClickListener() {
        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("7");
            }
        });
    }

    private void initButton8ClickListener() {
        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("8");
            }
        });
    }

    private void initButton9ClickListener() {
        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("9");
            }
        });
    }

    private void initButton0ClickListener() {
        Button button0 = findViewById(R.id.button0);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("0");
            }
        });
    }

    private void initButtonPointClickListener() {
        Button buttonPoint = findViewById(R.id.buttonPoint);
        buttonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentInput.equals("0")) {
                    onNumberButtonClick("0.");
                }
                if (!currentInput.contains(".")) {
                    onNumberButtonClick(".");
                }
            }
        });
    }


    private void initView() {
        display = findViewById(R.id.editTextNumberDecimal);

        initButtonClearClickListener();
        initButtonBackSpaceClickListener();
        initButtonPercentClickListener();
        initButtonDivideClickListener();
        initButton1ClickListener();
        initButton2ClickListener();
        initButton3ClickListener();
        initButton4ClickListener();
        initButton5ClickListener();
        initButton6ClickListener();
        initButton7ClickListener();
        initButton8ClickListener();
        initButton9ClickListener();
        initButton0ClickListener();
        initButtonPointClickListener();
/*        initButtonMultiplyClickListener();
        initButtonMinusClickListener();
        initButtonPlusClickListener();
        initButtonEqualsClickListener();*/

    }
}




