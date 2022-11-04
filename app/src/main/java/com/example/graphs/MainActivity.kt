package com.example.graphs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.example.graphs.databinding.ActivityMainBinding
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var firstNum = 0
        var secondNum = 0
        val listSize = 100
        val myArray: MutableList<MutableList<Int>> = MutableList(listSize) {MutableList(listSize) {0}}

        fun graphContainedText(){
            binding.currentFirst.text = firstNum.toString()
            binding.currentSecond.text = secondNum.toString()
            binding.graphContains.setText(myArray[firstNum][secondNum].toString())
        }

        graphContainedText()

        binding.increaseFirst.setOnClickListener {
            if(firstNum < listSize){
                firstNum += 1
                graphContainedText()
            }
        }

        binding.decreaseFirst.setOnClickListener {
            if(firstNum > 0){
                firstNum -= 1
                graphContainedText()
            }
        }

        binding.increaseSecond.setOnClickListener {
            if(secondNum < listSize){
                secondNum += 1
                graphContainedText()
            }
        }

        binding.decreaseSecond.setOnClickListener {
            if(secondNum > 0){
                secondNum -= 1
                graphContainedText()
            }
        }

        binding.setBtn.setOnClickListener {
            myArray[firstNum][secondNum] = binding.graphContains.text.toString().toInt()
            println(myArray[firstNum][secondNum])
            println(myArray[firstNum+1][secondNum+1])
        }
    }
}