package com.example.calculatorandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

enum class Operator {
    MINUS, MULTIPLY, DIVIDE, PLUS, NONE
}

class MainActivity : AppCompatActivity() {
    private lateinit var tvInput: TextView
    private lateinit var btnEqual: Button
    private lateinit var btnMinus: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivide: Button
    private lateinit var btnPlus: Button
    private lateinit var btnARC: Button
    private lateinit var btnDot: Button
    private lateinit var btnZero: Button
    private lateinit var btnOne: Button
    private lateinit var btnTwo: Button
    private lateinit var btnThree: Button
    private lateinit var btnFour: Button
    private lateinit var btnFive: Button
    private lateinit var btnSix: Button
    private lateinit var btnSeven: Button
    private lateinit var btnEight: Button
    private lateinit var btnNine: Button

    private var result: Double = 0.0
    private var operateNumber1: Double = 0.0
    private var operateNumber2: Double = 0.0
    private var isFirstNumberInputed = false
    private var currentOperator: Operator = Operator.NONE
    private var isNeedInputSecondOperateNumber = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getUIComponents()
        setupUIComponents()
    }

    private fun getUIComponents() {
        tvInput = findViewById(R.id.textViewInput)
        btnEqual = findViewById(R.id.buttonEqual)
        btnMinus = findViewById(R.id.buttonMinus)
        btnMultiply = findViewById(R.id.buttonMultiply)
        btnDivide = findViewById(R.id.buttonDivide)
        btnPlus = findViewById(R.id.buttonPlus)
        btnARC = findViewById(R.id.buttonARC)
        btnDot = findViewById(R.id.buttonDot)
        btnZero = findViewById(R.id.buttonZero)
        btnOne = findViewById(R.id.buttonOne)
        btnTwo = findViewById(R.id.buttonTwo)
        btnThree = findViewById(R.id.buttonThree)
        btnFour = findViewById(R.id.buttonFour)
        btnFive = findViewById(R.id.buttonFive)
        btnSix = findViewById(R.id.buttonSix)
        btnSeven = findViewById(R.id.buttonSeven)
        btnEight = findViewById(R.id.buttonEight)
        btnNine = findViewById(R.id.buttonNine)
    }

    private fun setupUIComponents() {
        tvInput.text = "0"
        btnEqual.text = resources.getText(R.string.button_equal)
        btnMinus.text = resources.getText(R.string.button_minus)
        btnMultiply.text = resources.getText(R.string.button_multiply)
        btnDivide.text = resources.getText(R.string.button_divide)
        btnDot.text = resources.getText(R.string.button_dot)
        val btnNumbers = arrayListOf<Button>(btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine)
        for (i in 0 until btnNumbers.size) {
            btnNumbers[i].text = i.toString()
            btnNumbers[i].setOnClickListener {
                onClickNumber(num = i)
            }
        }
        btnARC.setOnClickListener {
            onClickARC()
        }
        btnDot.setOnClickListener {
            onClickDot()
        }
        btnMinus.setOnClickListener {
            onClickOperator(Operator.MINUS)
        }
        btnMultiply.setOnClickListener {
            onClickOperator(Operator.MULTIPLY)
        }
        btnDivide.setOnClickListener {
            onClickOperator(Operator.DIVIDE)
        }
        btnPlus.setOnClickListener {
            onClickOperator(Operator.PLUS)
        }
        btnEqual.setOnClickListener {
            onClickEqual()
        }
    }

    private fun onClickDot() {
        onClickNumber(-1)
    }

    private fun onClickNumber(num: Int) {
        val newCharacter = if (num == -1) { "." } else { num.toString() }
        val text = tvInput.text.toString() + newCharacter
        try {
            val number = text.toDouble()
            if (!isFirstNumberInputed && number > 0) {
                tvInput.text = number.toInt().toString()
            } else {
                tvInput.append(newCharacter)
            }
            isFirstNumberInputed = true
            if (isNeedInputSecondOperateNumber) {
                operateNumber2 = number
            } else {
                operateNumber1 = number
            }
        } catch (e: java.lang.NumberFormatException) {
            println(e.localizedMessage)
        }
    }

    private fun operateResult(): Boolean {
        try {
            when (currentOperator) {
                Operator.PLUS -> {
                    result = operateNumber1 + operateNumber2
                }
                Operator.DIVIDE -> {
                    result = operateNumber1 / operateNumber2
                }
                Operator.MULTIPLY -> {
                    result = operateNumber1 * operateNumber2
                }
                Operator.MINUS -> {
                    result = operateNumber1 - operateNumber2
                }
                else -> {}
            }
            return true
        } catch (e: java.lang.Error) {
            return false
        }
    }

    private fun onClickOperator(operator: Operator) {
        if (operateNumber1 == 0.0) { return }
        if (isNeedInputSecondOperateNumber) {
            val isSuccess = operateResult()
            if (isSuccess) {
                tvInput.text = result.toString()
                operateNumber1 = result
                operateNumber2 = 0.0
                currentOperator = operator
            } else {
                reset()
                Toast.makeText(this, "Something error", Toast.LENGTH_LONG)
            }
        } else {
            currentOperator = operator
            isNeedInputSecondOperateNumber = true
            isFirstNumberInputed = false
        }
    }

    private fun onClickEqual() {
        tvInput.text = result.toString()
    }

    private fun onClickARC() {
        reset()
    }

    private fun reset() {
        tvInput.text = "0"
        isFirstNumberInputed = false
        currentOperator = Operator.NONE
        isNeedInputSecondOperateNumber = false
        operateNumber1 = 0.0
        operateNumber2 = 0.0
    }
}