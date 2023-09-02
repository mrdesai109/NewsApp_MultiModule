package com.rushi.newsapp_mm

import android.os.Build.VERSION_CODES.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.rushi.common_utils.ActivityType
import com.rushi.common_utils.Navigator
import com.rushi.newsapp_mm.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _activityMainBinding: ActivityMainBinding? = null
    private val activityMainBinding: ActivityMainBinding
        get() = _activityMainBinding!!

    @Inject
    lateinit var provider : Navigator.Provider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        CoroutineScope(Dispatchers.Main).launch{
            delay(1500L)
            provider.getTargetActivity(ActivityType.NewsActivity).navigate(this@MainActivity)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }
}