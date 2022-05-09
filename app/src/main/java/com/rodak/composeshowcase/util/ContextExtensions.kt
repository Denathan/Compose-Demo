package com.rodak.composeshowcase.util

import android.content.Context
import androidx.activity.ComponentActivity

fun Context.activitySafeCast() = this as? ComponentActivity
