package com.example.mycal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.example.mycal.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        onclickListeners()


    }


    /** function for all click listeners **/
    fun onclickListeners() {

        //Numbers
        activityMainBinding.num0.setOnClickListener { appendVal("0") }
        activityMainBinding.num1.setOnClickListener { appendVal("1") }
        activityMainBinding.num2.setOnClickListener { appendVal("2") }
        activityMainBinding.num3.setOnClickListener { appendVal("3") }
        activityMainBinding.num4.setOnClickListener { appendVal("4") }
        activityMainBinding.num5.setOnClickListener { appendVal("5") }
        activityMainBinding.num6.setOnClickListener { appendVal("6") }
        activityMainBinding.num7.setOnClickListener { appendVal("7") }
        activityMainBinding.num8.setOnClickListener { appendVal("8") }
        activityMainBinding.num9.setOnClickListener { appendVal("9") }
        activityMainBinding.numDot.setOnClickListener { appendVal(".") }
        //Operators

        activityMainBinding.actionDivide.setOnClickListener { appendVal(" / ") }
        activityMainBinding.actionMultiply.setOnClickListener { appendVal(" * ") }
        activityMainBinding.actionMinus.setOnClickListener { appendVal(" - ") }
        activityMainBinding.actionAdd.setOnClickListener { appendVal(" + ") }


      // on back click
        activityMainBinding.actionBack.setOnClickListener {
            val expression = activityMainBinding.placeholder.text.toString()
            if (expression.isNotEmpty()) {
                activityMainBinding.placeholder.text =
                    expression.substring(0, expression.length - 1)
            }


        }

       //  on clear click
        activityMainBinding.clear.setOnClickListener {
            activityMainBinding.placeholder.text = ""
            activityMainBinding.answer.text = ""
        }

        //        on equal click
        activityMainBinding.actionEqual.setOnClickListener {
            activityMainBinding.placeholder.text = activityMainBinding.answer.text
            activityMainBinding.answer.text = ""
        }


        //on text change
        activityMainBinding.placeholder.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                if (activityMainBinding.placeholder.text.length == 0)
                    activityMainBinding.answer.text = ""

                try {
                    val expression =
                        ExpressionBuilder(activityMainBinding.placeholder.text.toString()).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()
                    if (result == longResult.toDouble()) {
//                        Toast.makeText(this@MainActivity, "Double", Toast.LENGTH_SHORT).show()
                        activityMainBinding.answer.text = longResult.toString()
                    } else
                        activityMainBinding.answer.text = result.toString()

                } catch (e: Exception) {
//                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show();

                    Log.d("EXCEPTION", "Message: ${e.message}")
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


    }


    /** appending value of input text  **/
    fun appendVal(string: String) {

        activityMainBinding.placeholder.append(string)

    }
}