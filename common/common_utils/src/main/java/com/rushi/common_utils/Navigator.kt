package com.rushi.common_utils

import android.app.Activity

interface Navigator {

    fun navigate(activity: Activity)

    interface Provider{
        fun getTargetActivity(activityType: ActivityType) : Navigator
    }
}