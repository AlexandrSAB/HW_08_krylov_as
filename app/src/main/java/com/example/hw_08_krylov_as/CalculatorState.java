package com.example.hw_08_krylov_as;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

class CalculatorState implements Parcelable {
    private String currentInput;
    private String history;

    public CalculatorState() {
        this.currentInput = "0";
        this.history = "";
    }

    protected CalculatorState(Parcel in) {
        currentInput = in.readString();
        history = in.readString();
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
