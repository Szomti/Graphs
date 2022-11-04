package com.example.graphs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.graphs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var firstNum = 0
        var firstNodeNum = 0
        var secondNum = 0
        var secondNodeNum = 0
        val listSize = 10
        val actualMaxListSize = listSize-1
        val myArray: MutableList<MutableList<Int>> = MutableList(listSize) {MutableList(listSize) {0}}

        fun graphContainedText(){
            binding.currentFirst.text = firstNum.toString()
            binding.currentFirstNode.text = firstNodeNum.toString()
            binding.currentSecond.text = secondNum.toString()
            binding.currentSecondNode.text = secondNodeNum.toString()
            binding.graphContains.setText(myArray[firstNum][secondNum].toString())
        }

        fun pathWeightChangeable() {
            binding.graphContains.isEnabled = firstNum != secondNum
        }

        graphContainedText()

        binding.increaseFirst.setOnClickListener {
            if(firstNum < actualMaxListSize){
                firstNum += 1
                graphContainedText()
                pathWeightChangeable()
            }
        }

        binding.decreaseFirst.setOnClickListener {
            if(firstNum > 0){
                firstNum -= 1
                graphContainedText()
                pathWeightChangeable()
            }
        }

        binding.increaseSecond.setOnClickListener {
            if(secondNum < actualMaxListSize){
                secondNum += 1
                graphContainedText()
                pathWeightChangeable()
            }
        }

        binding.decreaseSecond.setOnClickListener {
            if(secondNum > 0){
                secondNum -= 1
                graphContainedText()
                pathWeightChangeable()
            }
        }

        binding.increaseFirstNode.setOnClickListener {
            if(firstNodeNum < actualMaxListSize){
                firstNodeNum += 1
                graphContainedText()
                pathWeightChangeable()
            }
        }

        binding.decreaseFirstNode.setOnClickListener {
            if(firstNodeNum > 0){
                firstNodeNum -= 1
                graphContainedText()
                pathWeightChangeable()
            }
        }

        binding.increaseSecondNode.setOnClickListener {
            if(secondNodeNum < actualMaxListSize){
                secondNodeNum += 1
                graphContainedText()
                pathWeightChangeable()
            }
        }

        binding.decreaseSecondNode.setOnClickListener {
            if(secondNodeNum > 0){
                secondNodeNum -= 1
                graphContainedText()
                pathWeightChangeable()
            }
        }

        binding.setBtn.setOnClickListener {
            myArray[firstNum][secondNum] = binding.graphContains.text.toString().toInt()
        }

        binding.searchBtn.setOnClickListener {
            dijkstra(myArray, firstNodeNum, secondNodeNum)
        }
    }

    private fun dijkstra(graph: MutableList<MutableList<Int>>, source: Int, end: Int) {
        val count = graph.size
        val visitedVertex = BooleanArray(count)
        val distance = IntArray(count)
        for (i in 0 until count) {
            visitedVertex[i] = false
            distance[i] = Int.MAX_VALUE
        }

        distance[source] = 0
        for (i in 0 until count) {

            val u = findMinDistance(distance, visitedVertex)
            visitedVertex[u] = true

            for (v in 0 until count) {
                if (!visitedVertex[v] && graph[u][v] != 0 && distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v]
                }
            }
        }
        for (i in distance.indices) {
            if(i==end) {
                binding.distanceInfo.text = "Distance from $source to $i is ${distance[i]}"
            }
        }
    }

    private fun findMinDistance(distance: IntArray, visitedVertex: BooleanArray): Int {
        var minDistance = Int.MAX_VALUE
        var minDistanceVertex = 0
        for (i in distance.indices) {
            if (!visitedVertex[i] && distance[i] < minDistance) {
                minDistance = distance[i]
                minDistanceVertex = i
            }
        }
        return minDistanceVertex
    }
}