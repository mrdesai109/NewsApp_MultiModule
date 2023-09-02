package com.rushi.news_presentation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.withStarted
import com.rushi.common_utils.ActivityType
import com.rushi.common_utils.Navigator
import com.rushi.news_presentation.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    var _activityNewsBinding : ActivityNewsBinding? = null
    val activityNewsBinding : ActivityNewsBinding
        get() = _activityNewsBinding!!

    val newsViewModel : NewsViewModel by viewModels()
    val newsAdapter = NewsAdapter()

    @Inject
    lateinit var provider : Navigator.Provider

    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, NewsActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityNewsBinding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(activityNewsBinding.root)
        initViews()
        collectFlows()
    }

    fun initViews(){
        activityNewsBinding.rvArticles.apply {
            adapter = newsAdapter
            setHasFixedSize(true)
        }
        activityNewsBinding.searchiv.setOnClickListener {
            provider.getTargetActivity(ActivityType.SearchActivity).navigate(this@NewsActivity)
        }
    }

    fun collectFlows(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                newsViewModel.newsState.collect{
                    if(it.isLoading){
                        activityNewsBinding.progressBar.visibility = View.VISIBLE
                    }else{
                        activityNewsBinding.progressBar.visibility = View.GONE
                    }
                    if(it.errorMsg.isNotBlank()){
                        Toast.makeText(applicationContext,it.errorMsg,Toast.LENGTH_LONG).show()
                        activityNewsBinding.progressBar.visibility = View.GONE
                    }
                    if(it.data.isNotEmpty()){
                        activityNewsBinding.progressBar.visibility = View.GONE
                        newsAdapter.setData(it.data)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityNewsBinding = null
    }
}

object GoToNewsActivity : Navigator {

    override fun navigate(activity: Activity) {
        NewsActivity.launchActivity(activity)
        activity.finish()
    }

}