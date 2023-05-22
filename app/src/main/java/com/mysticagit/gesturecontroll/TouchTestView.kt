package com.mysticagit.gesturecontroll

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TouchTestView: View {

    private var posX: Float = 0.0f
    private var posY: Float = 0.0f
    private var paint: Paint = Paint()
    private var beginTouch = false

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attr: AttributeSet): super(context, attr) {

    }

    init {
        paint.color = Color.BLUE
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        beginTouch = true
        event?.let {
            posX = it.x
            posY = it.y

            MainActivity.gDetector.onTouchEvent(it)
            invalidate()
        }
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if(beginTouch) {
            canvas?.let {
                it.drawRect(posX - 60, posY - 60, posX + 60, posY + 60, paint)
            }
        }
    }
}