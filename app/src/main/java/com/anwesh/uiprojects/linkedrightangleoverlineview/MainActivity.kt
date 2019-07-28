package com.anwesh.uiprojects.linkedrightangleoverlineview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.rightangleoverlineview.RightAngleOverLineView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RightAngleOverLineView.create(this)
    }
}
