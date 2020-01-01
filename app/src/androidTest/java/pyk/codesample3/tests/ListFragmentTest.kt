package pyk.codesample3.tests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pyk.codesample3.R
import pyk.codesample3.model.repo.MovieList
import pyk.codesample3.model.support.StaticValues
import pyk.codesample3.view.activity.MainActivity
import pyk.codesample3.view.adapter.MovieListAdapter

@RunWith(AndroidJUnit4::class) class ListFragmentTest {
    @get:Rule val activityRule: ActivityTestRule<MainActivity> =
            ActivityTestRule(MainActivity::class.java)
    
    @After fun cleanup() {
        MovieList().getMovies().clear()
    }
    
    @Test fun firstPagePopulated() {
        // first page is populated
        onView(withId(R.id.rv_list)).check(
                matches(hasDescendant(withText(StaticValues.movies1[0].title))))
    }
    
    @Test fun pullForSecondPage() {
        onView(withId(R.id.rv_list)).check(
                matches(not(hasDescendant(withText(StaticValues.movies2[0].title)))))
        onView(withId(R.id.rv_list)).perform(swipeUp())
        // once more for good measure
        onView(withId(R.id.rv_list)).perform(swipeUp())
        // SUPER scuffed way of "waiting" for my infinite scroll to load since espresso
        // quits before submitList() can get called
        onView(withId(R.id.rv_list)).perform(click())
        pressBack()
        onView(withId(R.id.rv_list)).perform(
                RecyclerViewActions.scrollToPosition<MovieListAdapter.ViewHolder>(30))
        onView(withId(R.id.rv_list)).check(
                matches(hasDescendant(withText(StaticValues.movies2[10].title))))
    }
}