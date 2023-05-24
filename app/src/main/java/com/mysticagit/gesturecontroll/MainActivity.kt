package com.mysticagit.gesturecontroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var viewTouchArea: TouchTestView

    interface TouchResultListener {
        fun onResult(log: String?)
    }

    companion object {
        lateinit var gDetector: GestureDetector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI 구성
        initUI()

        // 제스처 감지 구성
        gDetector = GestureDetector(this, gestureListener)
    }

    private fun initUI() {
        viewTouchArea = TouchTestView(this)
        viewTouchArea.findViewById<TouchTestView>(R.id.view_touch_area)
        var scrollTouchArea: ScrollView = findViewById<ScrollView>(R.id.scroll_result_area)

        scrollTouchArea.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            scrollTouchArea.fullScroll(View.FOCUS_DOWN)
        }
    }

    private val gestureListener = object : GestureDetector.OnGestureListener {
        override fun onDown(e: MotionEvent): Boolean {
            trListener.onResult("onDown()")
            return true
        }

        override fun onShowPress(e: MotionEvent) {
            trListener.onResult("onShowPress()")
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            trListener.onResult("onSingleTapUp()")
            return true
        }

        override fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            trListener.onResult("onScroll() : distanceX = $distanceX, distanceY = $distanceY")
            return false
        }

        override fun onLongPress(e: MotionEvent) {
            trListener.onResult("onLongPress()")
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            trListener.onResult("onFling() : velocityX = $velocityX, velocityY = $velocityY")
            return true
        }
    }

    private val trListener = object : TouchResultListener {
        override fun onResult(log: String?) {
            log?.let {
                var tvTouchResult: TextView = findViewById<TextView>(R.id.tv_touch_result)

                var history = tvTouchResult.text.toString()
                history += ("\n" + log)

                tvTouchResult.text = history
            }
        }
    }
}