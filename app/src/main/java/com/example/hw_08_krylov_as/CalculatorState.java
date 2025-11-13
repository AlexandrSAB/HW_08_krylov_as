package com.example.hw_08_krylov_as;

import java.io.Serializable;

class CalculatorState implements Serializable {
    private String currentInput;

    public CalculatorState() {
        this.currentInput = "0";
    }

    public String getCurrentInput() {
        return currentInput;
    }

    public void setCurrentInput(String currentInput) {
        this.currentInput = currentInput;
    }
}
