package com.codekul.grapprapplication.domain

/**
 * Created by sonal on 23/1/18.
 */
data class AppStack (
        val user: User,
        val app: App,
        val installed : Boolean = false
)