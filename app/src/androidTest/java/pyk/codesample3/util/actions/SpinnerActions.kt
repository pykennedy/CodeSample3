package pyk.codesample3.util.actions

import android.view.InputDevice
import android.view.MotionEvent
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.CoordinatesProvider
import androidx.test.espresso.action.GeneralClickAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Tap

class SpinnerActions {
    fun clickXY(percentX: Float, percentY: Float): ViewAction {
        return GeneralClickAction(Tap.SINGLE, CoordinatesProvider { view ->
            val coords = IntArray(2)
            view.getLocationOnScreen(coords)
            val w = view.width
            val h = view.height
            val x = w * percentX
            val y = h * percentY
            val finalX = coords[0] + x
            val finalY = coords[1] + y
            floatArrayOf(finalX, finalY)
        }, Press.FINGER, InputDevice.SOURCE_MOUSE, MotionEvent.BUTTON_PRIMARY)
    }
}