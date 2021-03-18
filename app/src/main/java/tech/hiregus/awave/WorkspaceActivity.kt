package tech.hiregus.awave

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.hiregus.awave.home.HomeFragment

class WorkspaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.workspace_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }
    }
}