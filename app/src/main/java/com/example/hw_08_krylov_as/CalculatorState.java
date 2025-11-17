package com.example.hw_08_krylov_as;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

class CalculatorState implements Parcelable {
    private String currentInput;
    private String history;

    private double currentNumber;

    private double previousNumber;

    private String operation;

    private int isNewInput;



    public CalculatorState() {
        this.currentInput = "0";
        this.history = "";
        this.currentNumber = 0f;
        this.previousNumber = 0f;
        this.operation = "none";
        this.isNewInput = 0;
    }

    protected CalculatorState(Parcel in) {
        currentInput = in.readString();
        history = in.readString();
        currentNumber = in.readInt();
        previousNumber = in.readInt();
        operation = in.readString();
        isNewInput = in.readInt();
    }


    public static final Creator<CalculatorState> CREATOR = new Creator<CalculatorState>() {
        @Override
        public CalculatorState createFromParcel(Parcel in) {
            return new CalculatorState(in);
        }

        @Override
        public CalculatorState[] newArray(int size) {
            return new CalculatorState[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(currentInput);
        dest.writeString(history);
        dest.writeDouble(currentNumber);
        dest.writeDouble(previousNumber);
        dest.writeString(operation);
        dest.writeInt(isNewInput);
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


    public double getPreviousNumber() {
        return previousNumber;

    }
    public void setPreviousNumber(double  previousNumber) {
        this.previousNumber = previousNumber;
    }

    public String getOperation() {
        return operation;

    }
    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getIsNewInput() {
        return isNewInput;
    }

    public void setIsNewInput(int isNewInput) {
        this.isNewInput = isNewInput;
    }
}
