package pyk.codesample3

import android.app.Application

class App: Application() {
    init {
        instance = this
    }
    
    companion object {
        private var instance: App? = null
        fun getAppContext(): App {
            return instance as App
        }
    }
}