package com.rushi.newsapp_mm.navigation

import com.rushi.common_utils.ActivityType
import com.rushi.common_utils.Navigator
import com.rushi.news_presentation.GoToNewsActivity
import com.rushi.search_presentation.GoToSearchActivity

class DefaultNavigatorProvider : Navigator.Provider {

    override fun getTargetActivity(activityType: ActivityType): Navigator {
        return when(activityType){
            ActivityType.NewsActivity -> GoToNewsActivity
            ActivityType.SearchActivity -> GoToSearchActivity
        }
    }
}