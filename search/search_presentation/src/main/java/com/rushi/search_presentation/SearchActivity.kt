package com.rushi.search_presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.rushi.common_utils.API_KEY
import com.rushi.common_utils.ActivityType
import com.rushi.common_utils.Navigator
import com.rushi.search_presentation.databinding.ActivitySearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    var _activitySearchBinding: ActivitySearchBinding? = null
    val activitySearchBinding: ActivitySearchBinding
        get() = _activitySearchBinding!!

    val searchViewModel: SearchViewModel by viewModels()
    val searchAdapter = NewsAdapter()

    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, SearchActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activitySearchBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(activitySearchBinding.root)
        initViews()
        collectFlows()
    }

    fun initViews() {
        activitySearchBinding.searchTitle.doAfterTextChanged {
            val map = mutableMapOf<String, String>()
            map["apiKey"] = API_KEY
            map["q"] = it.toString()
            searchViewModel.searchNews(map)
        }
        activitySearchBinding.rvSearch.apply {
            adapter = searchAdapter
            setHasFixedSize(true)
        }
    }

    fun collectFlows() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.searchState.collect {
                    if (it.isLoading) {
                        activitySearchBinding.progressBar.visibility = View.VISIBLE
                    } else {
                        activitySearchBinding.progressBar.visibility = View.GONE
                    }
                    if (it.errMsg.isNotEmpty()) {
                        Toast.makeText(applicationContext, it.errMsg, Toast.LENGTH_LONG).show()
                    }
                    if (it.data.isNotEmpty()) {
                        //hideKeyboard(activitySearchBinding.root.rootView)
                        searchAdapter.setData(
                            it.data
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activitySearchBinding = null
    }

    fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

object GoToSearchActivity : Navigator {
    override fun navigate(activity: Activity) {
        SearchActivity.launchActivity(activity)
    }

}