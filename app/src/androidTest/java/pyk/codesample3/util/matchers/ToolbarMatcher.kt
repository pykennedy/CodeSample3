package pyk.codesample3.util.matchers

import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`

object ToolbarMatcher {
    private fun matchToolbarTitle(textMatcher: Matcher<CharSequence?>): Matcher<View?>? {
        return object: BoundedMatcher<View?, Toolbar>(Toolbar::class.java) {
            override fun matchesSafely(toolbar: Toolbar): Boolean {
                return textMatcher.matches(toolbar.title)
            }
            
            override fun describeTo(description: Description) {
                description.appendText("with toolbar title: ")
                textMatcher.describeTo(description)
            }
        }
    }
    
    fun withToolbarTitle(title: CharSequence?): Matcher<View?>? {
        return matchToolbarTitle(`is`(title))
    }
    
    // not a real matcher but this code is going to be reused and the dream was for a proper matcher
    // to function like this, but oh well dreams are for crushing anyways
    fun expectedTitle(actionBar: ActionBar?, title: String): Boolean {
        return actionBar?.title.toString() == title
    }
}