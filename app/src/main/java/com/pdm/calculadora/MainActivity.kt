package com.pdm.calculadora

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pdm.calculadora.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.text.toLong
import kotlin.toString

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var resultExist = false

    private var expression = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(amb){
            setContentView(root)
            button0.setOnClickListener { addExpressionValue("0") }
            button1.setOnClickListener { addExpressionValue("1") }
            button2.setOnClickListener { addExpressionValue("2") }
            button3.setOnClickListener { addExpressionValue("3") }
            button4.setOnClickListener { addExpressionValue("4") }
            button5.setOnClickListener { addExpressionValue("5") }
            button6.setOnClickListener { addExpressionValue("6") }
            button7.setOnClickListener { addExpressionValue("7") }
            button8.setOnClickListener { addExpressionValue("8") }
            button9.setOnClickListener { addExpressionValue("9") }
            buttonAdd.setOnClickListener { addExpressionValue("+") }
            buttonSubtract.setOnClickListener { addExpressionValue("-") }
            buttonMultiply.setOnClickListener { addExpressionValue("*") }
            buttonDivide.setOnClickListener { addExpressionValue("/") }
            buttonDot.setOnClickListener { addExpressionValue(".") }
            buttonClear.setOnClickListener { clearAll() }
            buttonBack.setOnClickListener { backspace() }
            buttonEqual.setOnClickListener { calculateResult() }
        }
    }
    private fun addExpressionValue(value: String) {
        if(expression.length>=28) return
        if (resultExist) {
            if(value in listOf("+", "-", "*", "/")) {
                expression = (amb.textResult.text).toString()
                amb.textResult.text = ""
                resultExist = false
            } else {
                expression = ""
                amb.textResult.text = ""
                resultExist=false
            }
        }
        expression += value
        amb.textExpression.text = expression
    }
    @SuppressLint("SetTextI18n")
    private fun calculateResult() {
        try {
            val expression = ExpressionBuilder(this.expression).build()
            val result = expression.evaluate()
            amb.textResult.text = result.toString()
            this.resultExist = true
        } catch (e: Exception) {
            amb.textResult.text = "Error"
        }
    }

    private fun clearAll() {
        expression = ""
        amb.textExpression.text = "0"
        amb.textResult.text = ""
        this.resultExist = false
    }

    private fun backspace() {
        if (expression.isNotEmpty()) {
            expression = expression.dropLast(1)
            amb.textExpression.text = expression
        }
    }
}