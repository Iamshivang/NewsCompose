package com.example.newscompose.utils

import java.text.SimpleDateFormat
import java.util.Locale

/*
 * Author: Shivang Yadav
 * Created: 6/10/25
 * Description: [Add description here]
 */
object getTime {

    fun getTime(publishedAt: String): String {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())

        return try {
            val date = dateFormat.parse(publishedAt)
            SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(date)
        } catch (e: Exception) {
            publishedAt
        }
    }
}