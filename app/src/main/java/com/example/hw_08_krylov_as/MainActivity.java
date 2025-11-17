package com.example.hw_08_krylov_as;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
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

    private ScrollView scrollView;

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
        if (state.getIsNewInput() == 1) {
            state.setCurrentInput("0");
            state.setIsNewInput(0);
        }

        if (state.getCurrentInput().equals("0")) {
            state.setCurrentInput(number);
        }
        else {
            state.setCurrentInput(state.getCurrentInput() + number);
        }
        display.setText(state.getCurrentInput());
    }

    private void onOperationButtonClick(String operation) {

        if (!state.getOperation().equals("none") && state.getIsNewInput() == 1) {
            state.setOperation(operation);
            return;
        }

        if (!state.getOperation().equals("none")) {
            calculateResult();
        }
        else {
            state.setPreviousNumber(Double.parseDouble(state.getCurrentInput()));
            display.setText(state.getCurrentInput());
        }
        state.setOperation(operation);
        state.setIsNewInput(1);
    }

    private void calculateResult() {

        double currentNum = Double.parseDouble(state.getCurrentInput());
        double result = 0;

        switch (state.getOperation()) {
            case " + ":
                result = state.getPreviousNumber() + currentNum;
                break;
            case " - ":
                result = state.getPreviousNumber() - currentNum;
                break;
            case " x ":
                result = state.getPreviousNumber() * currentNum;
                break;
            case " / ":
                if (currentNum != 0) {
                    result = state.getPreviousNumber() / currentNum;
                } else {
                    state.setCurrentInput("Error");
                    display.setText(state.getCurrentInput());
                    state.setOperation("none");
                    return;
                }
                break;
        }


        double roundedResult = Math.round(result * 100000000d) / 100000000d;

        if (roundedResult == (int) roundedResult) {
            state.setCurrentInput(String.valueOf((int)roundedResult));
        }
        else {
            state.setCurrentInput(String.valueOf(roundedResult));
        }

        String tempHistory = state.getHistory();


        String firstNumber = "";
        String secondNumber = "";

        if (state.getPreviousNumber() == (int) state.getPreviousNumber()) {
            firstNumber = String.valueOf((int)state.getPreviousNumber());
        }
        else {
            firstNumber = String.valueOf(state.getPreviousNumber());
        }

        if (currentNum == (int) currentNum) {
           secondNumber = String.valueOf((int)currentNum);
        }
        else {
           secondNumber = String.valueOf(currentNum);
        }


        tempHistory = tempHistory + firstNumber +
                state.getOperation() + secondNumber + " =" + "\n" + "= " +
                state.getCurrentInput() + "\n" + "\n";
        state.setHistory(tempHistory);
        displayHistory.setText(state.getHistory());
        scrollToBottom();

        state.setPreviousNumber(roundedResult);
        display.setText(state.getCurrentInput());
    }

    private void scrollToBottom() {
        if (scrollView != null) {
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });
        }
    }


    private void initButtonClearClickListener() {
        Button ButtonClear = findViewById(R.id.buttonClear);
        ButtonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state.setCurrentInput("0");
                state.setHistory("");
                state.setPreviousNumber(0);
                state.setOperation("none");
                state.setIsNewInput(0);
                state.setHistory("");
                display.setText(state.getCurrentInput());
                displayHistory.setText(state.getHistory());
            }
        });
    }


    private void initButtonBackSpaceClickListener() {
        Button buttonBackSpace = findViewById(R.id.buttonBackSpace);
        buttonBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state.getIsNewInput() == 1) {
                    state.setCurrentInput("0");
                    state.setIsNewInput(0);
                }
                if (!state.getCurrentInput().isEmpty()){
                    state.setCurrentInput(state.getCurrentInput().substring(0,
                            state.getCurrentInput().length() - 1));
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
                double result = Double.parseDouble(state.getCurrentInput());
                result = result / 100;
                state.setCurrentInput(String.valueOf(result));
                display.setText(state.getCurrentInput());
                state.setIsNewInput(1);
            }
        });
    }

    private void initButtonDivideClickListener() {
        Button buttonDivide = findViewById(R.id.buttonDivide);
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onOperationButtonClick(" / ");
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

    private void initButtonMultiplyClickListener() {
        Button buttonDivide = findViewById(R.id.buttonMultiply);
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onOperationButtonClick(" x ");
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

    private void initButtonMinusClickListener() {
        Button buttonDivide = findViewById(R.id.buttonMinus);
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onOperationButtonClick(" - ");


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

    private void initButtonPlusClickListener() {
        Button buttonDivide = findViewById(R.id.buttonPlus);
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onOperationButtonClick(" + ");
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

    private void initButtonEqualsClickListener() {
        Button buttonDivide = findViewById(R.id.buttonEquals);
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!state.getOperation().equals("none")) {
                    calculateResult();
                }
                state.setOperation("none");
                state.setIsNewInput(1);
            }
        });
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(STATE_CALCULATOR, state);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle InstanceState) {
        super.onRestoreInstanceState(InstanceState);
        state = InstanceState.getParcelable(STATE_CALCULATOR);
        setDisplays();
    }


    private void setDisplays() {
        display.setText(state.getCurrentInput());
        displayHistory.setText(state.getHistory());
    }

    private void initView() {
        display = findViewById(R.id.editTextNumberDecimal);
        displayHistory = findViewById(R.id.textViewHistory);
        scrollView = findViewById(R.id.scrollView);

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
        initButtonMultiplyClickListener();
        initButtonMinusClickListener();
        initButtonPlusClickListener();
        initButtonEqualsClickListener();

    }
}




