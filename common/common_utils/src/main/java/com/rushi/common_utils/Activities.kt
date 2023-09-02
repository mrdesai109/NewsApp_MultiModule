package com.rushi.common_utils

sealed class ActivityType{
    object NewsActivity:ActivityType()
    object SearchActivity:ActivityType()
}
