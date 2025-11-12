package com.example.hw_08_krylov_as;

import java.io.Serializable;

class CalculatorState implements Serializable {
    private String currentInput;

    public CalculatorState(String currentInput) {
        this.currentInput = currentInput;
    }

    public String getCurrentInput() {
        return currentInput;
    }
}
