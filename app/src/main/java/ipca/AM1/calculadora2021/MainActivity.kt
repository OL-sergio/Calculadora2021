package ipca.AM1.calculadora2021

import android.graphics.Insets.add
import android.graphics.Insets.subtract
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.OneShotPreDrawListener.add

enum class Operator {
    add,
    divid,
    subtract,
    multiply,
    percentage,
    positnegativ
}

class MainActivity : AppCompatActivity() {

    lateinit var textViewDisplay: TextView
    var operand : Double = 0.0
    var operator : Operator? = null

    var userIsInTheMiddleIntroduction = false

    var onClickNum: (view: View) -> Unit = {
        val buttonPressed = it as Button
        if (userIsInTheMiddleIntroduction ){

            if (buttonPressed.text == "."){
                if (!textViewDisplay.text.contains("."))  {
                    textViewDisplay.text = textViewDisplay.text.toString().plus(buttonPressed.text)
                }
            }else{
                if (textViewDisplay.text.equals("0")){
                    textViewDisplay.text = buttonPressed.text
                }else {
                    textViewDisplay.text = textViewDisplay.text.toString().plus(buttonPressed.text)
                }
            }
        }else{
            textViewDisplay.text = buttonPressed.text.toString()
            userIsInTheMiddleIntroduction = true
        }
    }
   var onClickOperation : (view : View) -> Unit = {
        if (doOperation()) {
            val buttonProcess = it as Button
            operand = textViewDisplay.text.toString().toDouble()
            operator = when (buttonProcess.text.toString()){
                "+" -> Operator.add
                "-" -> Operator.subtract
                "/" -> Operator.divid
                "x" -> Operator.multiply
                "%" -> Operator.percentage
                else -> null
            }
            userIsInTheMiddleIntroduction = false
        }

    }



    fun doOperation () : Boolean {
        operator?. let {
            when(it){
                Operator.add -> operand += textViewDisplay.text.toString().toDouble()
                Operator.subtract -> operand -= textViewDisplay.text.toString().toDouble()
                Operator.multiply -> operand *= textViewDisplay.text.toString().toDouble()
                Operator.percentage -> operand = (textViewDisplay.text.toString().toDouble())/100
                Operator.divid -> {
                    if (textViewDisplay.text.equals("0")) {
                        Toast.makeText(this,"Cannot divide by zero!", Toast.LENGTH_LONG).show()
                        return false
                    }else {
                        operand /= textViewDisplay.text.toString().toDouble()

                    }

                }
            }
            if (operand % 1 == 0.0 ){
                val result : Int = operand.toInt()
                textViewDisplay.text = result.toString()
            }else {
                textViewDisplay.text = operand.toString()
            }

        }
        return true
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewDisplay = findViewById<TextView>(R.id.textViewNumb)

        findViewById<Button>(R.id.button_0).setOnClickListener(onClickNum)
        findViewById<Button>(R.id.button_1).setOnClickListener(onClickNum)
        findViewById<Button>(R.id.button_2).setOnClickListener(onClickNum)
        findViewById<Button>(R.id.button_3).setOnClickListener(onClickNum)
        findViewById<Button>(R.id.button_4).setOnClickListener(onClickNum)
        findViewById<Button>(R.id.button_5).setOnClickListener(onClickNum)
        findViewById<Button>(R.id.button_6).setOnClickListener(onClickNum)
        findViewById<Button>(R.id.button_7).setOnClickListener(onClickNum)
        findViewById<Button>(R.id.button_8).setOnClickListener(onClickNum)
        findViewById<Button>(R.id.button_9).setOnClickListener(onClickNum)



        findViewById<Button>(R.id.button_mais).setOnClickListener(onClickOperation)
        findViewById<Button>(R.id.button_menos).setOnClickListener(onClickOperation)
        findViewById<Button>(R.id.button_div).setOnClickListener(onClickOperation)
        findViewById<Button>(R.id.button_x).setOnClickListener(onClickOperation)
        findViewById<Button>(R.id.button_prc).setOnClickListener(onClickOperation)

        findViewById<Button>(R.id.button_virbula).setOnClickListener(onClickNum)


        findViewById<Button>(R.id.button_mn).setOnClickListener {
            val value = textViewDisplay.text.toString()
            if (value.isEmpty()) {
                textViewDisplay.text = "-"
            } else {
                try {
                    var doubleValue = value.toDouble()
                    doubleValue *= -1
                    textViewDisplay.text = doubleValue.toString()
                } catch (e: NumberFormatException) {

                    textViewDisplay.text = ""
                }
            }
        }

        findViewById<Button>(R.id.button_ac).setOnClickListener {
            textViewDisplay.text = "0"
            userIsInTheMiddleIntroduction = false
            operand = 0.0
            operator = null
        }



        findViewById<Button>(R.id.button_igual).setOnClickListener {
            doOperation()
            userIsInTheMiddleIntroduction = false

        }
    }

}


