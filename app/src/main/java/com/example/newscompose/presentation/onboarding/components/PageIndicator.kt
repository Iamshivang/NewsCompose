package com.example.newscompose.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import com.example.newscompose.R
import androidx.compose.ui.graphics.Color
import com.example.newscompose.presentation.onboarding.Dimens

/*
 * Author: Shivang Yadav
 * Created: 6/5/25
 * Description: [Add description here]
 */

@Composable
fun PageIndicator(
    modifier: Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = colorResource(id = R.color.blue_grey)
) {

    Row(
        modifier= modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        repeat(pageSize){ page ->
            Box(
                modifier= Modifier
                    .size(Dimens.IndicatorSize)
                    .clip(CircleShape)
                    .background(
                        color = if(page == selectedPage)
                        selectedColor
                        else
                        unselectedColor
                    )
            )
        }
    }
}