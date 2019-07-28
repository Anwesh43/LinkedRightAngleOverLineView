package com.anwesh.uiprojects.rightangleoverlineview

/**
 * Created by anweshmishra on 28/07/19.
 */

import android.view.View
import android.view.MotionEvent
import android.content.Context
import android.app.Activity
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color

val nodes : Int = 5
val lines : Int = 2
val parts : Int = 2
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#3949AB")
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float = scaleFactor()
    return (1 - k) * a.inverse() + k * b.inverse()
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawRightAngleLine(i : Int, sc : Float, size : Float, paint : Paint) {
    val sci : Float = sc.divideScale(i, lines)
    val sf : Float = 1f - 2 * i
    for (j in 0..parts) {
        val scj : Float = sci.divideScale(j, parts)
        val x1j : Float = size / 2 * j
        val x2j : Float = size / 2 * j + (1 - j) * (size / 2) * scj
        val y2j : Float = size / 2 * j * scj
        save()
        translate(0f, (size / 2) * sf)
        drawLine(x1j, 0f, x2j, y2j, paint)
        restore()
    }
}

fun Canvas.drawRAOLNode(i : Int, scale : Float, paint : Paint) {
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = Math.min(w, h) / strokeFactor
    val size : Float = gap / sizeFactor
    paint.color = foreColor
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor 
    save()
    translate(gap * (i + 1), h / 2)
    rotate(90f * sc2)
    drawLine(0f, -size / 2, 0f, size / 2)
    for (j in 0..(lines - 1)) {
        drawRightAngleLine(j, sc1, size, paint)
    }
    restore()
}
