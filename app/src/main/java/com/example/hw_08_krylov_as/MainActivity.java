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

    private TextView displayHistory;

    private CalculatorState state;

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
        state = new CalculatorState();
        initView();
    }




    private void onNumberButtonClick(String number) {
        if (state.getCurrentInput().equals("0")) {
            state.setCurrentInput(number);
        }
        else {
            state.setCurrentInput(state.getCurrentInput() + number);
        }
        display.setText(state.getCurrentInput());
    }


    private void initButtonClearClickListener() {
        Button ButtonClear = findViewById(R.id.buttonClear);
        ButtonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state.setCurrentInput("0");
                display.setText(state.getCurrentInput());
            }
        });
    }

    private void initButtonBackSpaceClickListener() {
        Button buttonBackSpace = findViewById(R.id.buttonBackSpace);
        buttonBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!state.getCurrentInput().isEmpty()){
                    state.setCurrentInput(state.getCurrentInput().substring(0, state.getCurrentInput().length() - 1));
                    if (state.getCurrentInput().isEmpty()) {
                        state.setCurrentInput("0");
                    }
                }
                display.setText(state.getCurrentInput());
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
                if (state.getCurrentInput().equals("0")) {
                    onNumberButtonClick("0.");
                }
                if (!state.getCurrentInput().contains(".")) {
                    onNumberButtonClick(".");
                }
            }
        });
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putSerializable(STATE_CALCULATOR, state);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle InstanceState) {
        super.onRestoreInstanceState(InstanceState);
        state = (CalculatorState) InstanceState.getSerializable(STATE_CALCULATOR);
        setDisplays();
    }


    private void setDisplays() {
        display.setText(state.getCurrentInput());
        displayHistory.setText(state.getHistory());
    }

    private void initView() {
        display = findViewById(R.id.editTextNumberDecimal);
        displayHistory = findViewById(R.id.textViewHistory);

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




