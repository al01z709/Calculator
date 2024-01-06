package com.example.calculator

import android.icu.math.BigDecimal
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity()  {
        var currentInput = StringBuilder()
        var currentOperator = Operator.NONE
        var operand1: BigDecimal? = null



        enum class Operator {
            NONE, ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    private lateinit var binding: ActivityMainBinding

    //Дії
    lateinit var btnClear : Button
    lateinit var btnAC : Button
    lateinit var btnBackspace : ImageButton
    lateinit var btnPlus : Button
    lateinit var btnMinus : Button
    lateinit var btnX : Button
    lateinit var btnDil : Button
    //Цифри
    lateinit var btn1 : Button
    lateinit var btn2 : Button
    lateinit var btn3 : Button
    lateinit var btn4 : Button
    lateinit var btn5 : Button
    lateinit var btn6 : Button
    lateinit var btn7 : Button
    lateinit var btn8 : Button
    lateinit var btn9 : Button
    lateinit var btn0 : Button
    lateinit var btnKrap : Button
    lateinit var btnRes : Button

    lateinit var Input : TextView
    lateinit var CurrentOperand : TextView
    lateinit var OldInput : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnAC = binding.btnAc
        btnBackspace = binding.btnBackspace
        btnClear = binding.btnClear
        btnPlus = binding.btnPlus
        btnMinus = binding.btnMinus
        btnX = binding.btnX
        btnDil = binding.btnDil
        btnRes = binding.btnRes

        btn1 = binding.btn1
        btn2 = binding.btn2
        btn3 = binding.btn3
        btn4 = binding.btn4
        btn5 = binding.btn5
        btn6 = binding.btn6
        btn7 = binding.btn7
        btn8 = binding.btn8
        btn9 = binding.btn9
        btn0 = binding.btn0
        btnKrap = binding.btnKrap

        Input = binding.Input
        CurrentOperand = binding.CurrentOperand
        OldInput = binding.OldInput

        btn1.setOnClickListener { appendNumber("1") }
        btn2.setOnClickListener { appendNumber("2") }
        btn3.setOnClickListener { appendNumber("3") }
        btn4.setOnClickListener { appendNumber("4") }
        btn5.setOnClickListener { appendNumber("5") }
        btn6.setOnClickListener { appendNumber("6") }
        btn7.setOnClickListener { appendNumber("7") }
        btn8.setOnClickListener { appendNumber("8") }
        btn9.setOnClickListener { appendNumber("9") }
        btn0.setOnClickListener { appendNumber("0") }
        btnAC.setOnClickListener { appendNumber(".") }
        btnBackspace.setOnClickListener { handleBackspace() }
        btnClear.setOnClickListener { clearInput() }
        btnAC.setOnClickListener { allClearInput() }
        btnRes.setOnClickListener { calculateResult() }


        btnPlus.setOnClickListener { setOperator(Operator.ADD) }
        btnMinus.setOnClickListener { setOperator(Operator.SUBTRACT) }
        btnX.setOnClickListener { setOperator(Operator.MULTIPLY) }
        btnDil.setOnClickListener {
            setOperator(Operator.DIVIDE)



        }

    }
    private fun appendNumber(number: String) {
        currentInput.append(number)
        updateDisplay()
    }
    private fun handleBackspace() {
        if (currentInput.isNotEmpty()) {
            currentInput.deleteCharAt(currentInput.length - 1)
            updateDisplay()
        }
    }

    private fun setOperator(operator: Operator) {
        if(currentInput.toString()=="")return
        if (operand1 == null) {
            operand1 = BigDecimal(currentInput.toString())
            currentInput.clear()
        }
        OldInput.text = operand1.toString()
        Input.text = ""
        currentOperator = operator
        CurrentOperand.text = operatorToString(operator)
    }
    private fun operatorToString(operator: Operator): String {
        return when (operator) {
            Operator.ADD -> "+"
            Operator.SUBTRACT -> "-"
            Operator.MULTIPLY -> "×"
            Operator.DIVIDE -> "÷"
            Operator.NONE -> ""
        }
    }
    private fun calculateResult() {
        if(currentInput.toString()=="")return
        val operand2 = BigDecimal(currentInput.toString())
        var result: BigDecimal? = null
        Log.d("CalculatorApp", "operand1= ${operand1}")
        Log.d("CalculatorApp", "operand2= ${operand2}")
        Log.d("CalculatorApp", "currentInput= ${currentInput.toString()}")
        Log.d("CalculatorApp", "currentOperator= ${currentOperator}")

        when (currentOperator) {
            Operator.ADD -> result = operand1?.add(operand2)
            Operator.SUBTRACT -> result = operand1?.subtract(operand2)
            Operator.MULTIPLY -> result = operand1?.multiply(operand2)
            Operator.DIVIDE -> {
                if (operand2 != BigDecimal.ZERO) {
                    result = operand1?.divide(operand2, 10, BigDecimal.ROUND_HALF_UP)
                }else
                {
                    allClearInput()
                    binding.Input.text = "На нуль не ділять"
                }
            }
            Operator.NONE -> result = operand2
        }


        if (result != null) {
            OldInput.text = ("${operand1}${operatorToString(currentOperator)}${operand2}").toString()
            Input.text = result.toString()
            operand1 = result
        }

        currentInput.clear()
        CurrentOperand.text =""

    }


    private fun allClearInput() {
        currentInput.clear()
        operand1 = null
        currentOperator = Operator.NONE
        OldInput.text = ""
        Input.text = "0"
        CurrentOperand.text = ""
    }
    private fun clearInput() {
        currentInput.clear()
        currentOperator = Operator.NONE
        Input.text = "0"
        operand1 = null
    }

    private fun updateDisplay() {
        Input.text = currentInput.toString()
    }
}