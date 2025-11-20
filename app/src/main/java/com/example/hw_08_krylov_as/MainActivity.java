package com.example.hw_08_krylov_as;

import android.content.Intent;
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
import androidx.core.text.HtmlCompat;

import com.example.hw_08_krylov_as.databinding.ActivityMainBinding;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private EditText display;
    private ScrollView scrollView;
    private TextView displayHistory;
    private CalculatorState state;
    private static final String STATE_CALCULATOR = "calculator_state";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
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
                state.getOperation() + secondNumber + " =" + "\n" +  "<br>" + "= <b>" +
                state.getCurrentInput() + "</b>" + "<br><br>" + "\n" + "\n";
        state.setHistory(tempHistory);
        displayHistory.setText(HtmlCompat.fromHtml(state.getHistory(), HtmlCompat.FROM_HTML_MODE_LEGACY));
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

    private void initButtonHamburgerClickListener() {
        binding.buttonHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent runCongratulations = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(runCongratulations);
            }
        });
    }


    private void initButtonClearClickListener() {
        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
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
                /*Intent runCongratulations = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(runCongratulations);*/
            }
        });
    }


    private void initButtonBackSpaceClickListener() {
        binding.buttonBackSpace.setOnClickListener(new View.OnClickListener() {
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

    private void initButtonSqrtClickListener() {
        binding.buttonPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = Double.parseDouble(state.getCurrentInput());
                result = Math.sqrt(result);

                double roundedResult = Math.round(result * 100000000d) / 100000000d;

                if (roundedResult == (int) roundedResult) {
                    String tempHistory = state.getHistory();
                    tempHistory = tempHistory + "√" + state.getCurrentInput() +
                             " =" + "\n" +  "<br>" + "= <b>" +
                            String.valueOf((int)roundedResult) + "</b>" + "<br><br>" + "\n" + "\n";
                    state.setHistory(tempHistory);
                    displayHistory.setText(HtmlCompat.fromHtml(state.getHistory(), HtmlCompat.FROM_HTML_MODE_LEGACY));
                    scrollToBottom();
                    state.setCurrentInput(String.valueOf((int)roundedResult));
                }
                else {
                    String tempHistory = state.getHistory();
                    tempHistory = tempHistory + "√" + state.getCurrentInput() +
                            " =" + "\n" +  "<br>" + "= <b>" +
                            String.valueOf(roundedResult) + "</b>" + "<br><br>" + "\n" + "\n";
                    state.setHistory(tempHistory);
                    displayHistory.setText(HtmlCompat.fromHtml(state.getHistory(), HtmlCompat.FROM_HTML_MODE_LEGACY));
                    scrollToBottom();
                    state.setCurrentInput(String.valueOf(roundedResult));
                }
                display.setText(state.getCurrentInput());
                state.setIsNewInput(1);
            }
        });
    }


    private void initButtonDivideClickListener() {
        binding.buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onOperationButtonClick(" / ");
            }
        });
    }

    private void initButton1ClickListener() {
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("1");
            }
        });
    }

    private void initButton2ClickListener() {
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("2");
            }
        });
    }

    private void initButton3ClickListener() {
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("3");
            }
        });
    }

    private void initButtonMultiplyClickListener() {
        binding.buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onOperationButtonClick(" x ");
            }
        });
    }

    private void initButton4ClickListener() {
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("4");
            }
        });
    }

    private void initButton5ClickListener() {
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("5");
            }
        });
    }

    private void initButton6ClickListener() {
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("6");
            }
        });
    }

    private void initButtonMinusClickListener() {
        binding.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onOperationButtonClick(" - ");


            }
        });
    }

    private void initButton7ClickListener() {
        binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("7");
            }
        });
    }

    private void initButton8ClickListener() {
        binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("8");
            }
        });
    }

    private void initButton9ClickListener() {
        binding.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("9");
            }
        });
    }

    private void initButtonPlusClickListener() {
        binding.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onOperationButtonClick(" + ");
            }
        });
    }

    private void initButton0ClickListener() {
        binding.button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick("0");
            }
        });
    }

    private void initButtonPointClickListener() {
        binding.buttonPoint.setOnClickListener(new View.OnClickListener() {
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
        binding.buttonEquals.setOnClickListener(new View.OnClickListener() {
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
        displayHistory.setText(HtmlCompat.fromHtml(state.getHistory(), HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private void initView() {
        display = binding.editTextNumberDecimal;
        displayHistory = binding.textViewHistory;
        scrollView = binding.scrollView;

        initButtonClearClickListener();
        initButtonBackSpaceClickListener();
        initButtonSqrtClickListener();
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
        initButtonHamburgerClickListener();

    }
}




