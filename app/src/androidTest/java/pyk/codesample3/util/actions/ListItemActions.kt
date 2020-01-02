package pyk.codesample3.util.actions

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.Matcher

object ListItemActions {
    fun clickChildViewWithId(id: Int): ViewAction {
        return object: ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }
            
            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }
            
            override fun perform(uiController: UiController?, view: View) {
                val v: View = view.findViewById(id)
                v.performClick()
            }
        }
    }
}