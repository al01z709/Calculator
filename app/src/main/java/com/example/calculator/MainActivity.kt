package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.calculator.databinding.ActivityMainBinding

    class MainActivity : AppCompatActivity() , View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    lateinit var btnPlus : Button
    lateinit var btnMinus : Button
    lateinit var btnX : Button
    lateinit var btnDil : Button
    lateinit var etA : EditText
    lateinit var etB : EditText
    lateinit var resultat : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnPlus = binding.btnPlus
        btnMinus = binding.btnMinus
        btnX = binding.btnX
        btnDil = binding.btnDil
        etA = binding.etA
        etB = binding.etB
        resultat = binding.result

        btnPlus.setOnClickListener(this)
        btnMinus.setOnClickListener(this)
        btnX.setOnClickListener(this)
        btnDil.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        var a  = etA.text.toString().toDouble()
        var b  = etB.text.toString().toDouble()
        var result = 0.0
        when(v?.id){
            R.id.btn_plus ->{
                result = a+b
                resultat.text = "$a+$b = $result"
            }
            R.id.btn_minus ->{
                result = a-b
                resultat.text = "$a-$b = $result"
            }
            R.id.btn_x->{
                result = a*b
                resultat.text = "$a*$b = $result"
            }
            R.id.btn_dil->{
                result = a/b
                resultat.text = "$a/$b = $result"
            }
        }

    }
}