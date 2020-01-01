package pyk.codesample3.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Rule
import org.junit.runner.RunWith
import pyk.codesample3.model.repo.MovieList
import pyk.codesample3.view.activity.MainActivity

@RunWith(AndroidJUnit4::class)
class ListFragmentTest {
    @get:Rule
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    
    @After
    fun cleanup() { MovieList().getMovies().clear() }
    
    
    
    
}