package com.example.newscompose.presentation.onboarding

import com.example.newscompose.R;
import androidx.annotation.DrawableRes

/*
 * Author: Shivang Yadav
 * Created: 6/3/25
 * Description: [Add description here]
 */
class Page(
    val title: String,
    val body: String,
    @DrawableRes val image: Int
)


val pages = listOf<Page>(

    Page(
        "Stay Informed Instantly",
        "Get breaking news alerts as they happen, right at your fingertips.",
        R.drawable.onboarding1
    ),
    Page(
        "Curated Just for You",
        "Discover stories that match your interests and reading habits.",
        R.drawable.onboarding2
    ),
    Page(
        "Read Anywhere, Anytime",
        "Access news offline and stay updated on the go.",
        R.drawable.onboarding3
    )
)