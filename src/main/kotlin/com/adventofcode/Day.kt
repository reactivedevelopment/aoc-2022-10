package com.adventofcode

import com.adventofcode.Processor.signalsSum


object Processor {
  private var cycleCounter = 0L
  private var xRegister = 1L
  private var signalCycles = setOf(20L, 60L, 100L, 140L, 180L, 220L)
  var signalsSum = 0L; private set

  private fun cycle() {
    cycleCounter += 1
    if(cycleCounter in signalCycles) {
      signalsSum += (cycleCounter * xRegister)
    }
  }

  private fun addX(v: Long) {
    cycle()
    cycle()
    xRegister += v
  }

  private fun noop() {
    cycle()
  }

  fun process(command: String) {
    if(command == "noop") {
      noop()
      return
    }
    val value = command.removePrefix("addx ").toLong()
    addX(value)
  }
}

fun main() {
  ::main.javaClass
    .getResourceAsStream("/input")!!
    .bufferedReader()
    .forEachLine(Processor::process)
  println(signalsSum)
}
