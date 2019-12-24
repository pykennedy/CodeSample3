package pyk.codesample3.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import pyk.codesample3.R
import pyk.codesample3.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    
    private lateinit var drawerLayout: DrawerLayout
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        
        drawerLayout = b.drawerLayout
        
        val nav = this.findNavController(R.id.fragmentNavigator)
        NavigationUI.setupActionBarWithNavController(this, nav, drawerLayout)
        NavigationUI.setupWithNavController(b.nvDrawer, nav)
    }
    
    override fun onSupportNavigateUp(): Boolean {
        val nav = this.findNavController(R.id.fragmentNavigator)
        return return NavigationUI.navigateUp(nav, drawerLayout)
    }
}
