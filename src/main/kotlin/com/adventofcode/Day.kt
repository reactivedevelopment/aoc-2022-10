package com.adventofcode

import com.adventofcode.CPU.process
import com.adventofcode.CRT.display
import com.adventofcode.CRT.drawPixel
import com.adventofcode.CRT.sprite
import com.google.common.collect.Iterators.cycle

object CRT {
  private val display = CharArray(240) { '?' }
  private val realPixel = display.indices.iterator()
  private val spritePixel = cycle(0 until 40)
  var sprite = 1

  private fun isVisible(): Boolean {
    val p = spritePixel.next()
    return p in setOf(sprite - 1, sprite, sprite + 1)
  }

  fun drawPixel() {
    val p = realPixel.next()
    if (isVisible()) {
      display[p] = '#'
    } else {
      display[p] = '.'
    }
  }

  fun display() {
    val lines = display.asList().chunked(40)
    for (line in lines) {
      val processedLine = line.joinToString("")
      println(processedLine)
    }
  }
}

object CPU {
  private var xRegister = 1

  private fun cycle() {
    drawPixel()
  }

  private fun addX(v: Int) {
    cycle()
    cycle()
    xRegister += v
    sprite = xRegister
  }

  private fun noop() {
    cycle()
  }

  fun process(command: String) {
    if (command == "noop") {
      noop()
      return
    }
    val value = command.removePrefix("addx ").toInt()
    addX(value)
  }
}

fun main() {
  ::main.javaClass
    .getResourceAsStream("/input")!!
    .bufferedReader()
    .forEachLine(::process)
  display()
}
