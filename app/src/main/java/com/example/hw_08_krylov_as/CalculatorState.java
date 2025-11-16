package com.example.hw_08_krylov_as;

import java.io.Serializable;

class CalculatorState implements Serializable {
    private String currentInput;
    private String history;

    public CalculatorState() {
        this.currentInput = "0";
        this.history = "";
    }

    public String getCurrentInput() {
        return currentInput;
    }

    public void setCurrentInput(String currentInput) {
        this.currentInput = currentInput;
    }

    public String getHistory() {
        return history;
    }
    public void setHistory(String history) {
        this.history = history;
    }
}
