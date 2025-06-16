package com.example.newscompose.presentation.onboarding.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.newscompose.presentation.onboarding.Page
import com.example.newscompose.presentation.onboarding.Dimens

/*
 * Author: Shivang Yadav
 * Created: 6/3/25
 * Description: [Add description here]
 */


@Composable
fun OnBoardingPage(
    page: Page
){

    Column(
        modifier = Modifier
            .fillMaxHeight(0.9f)
    ){

        Image(
            painter = painterResource(page.image),
            contentDescription = "OnBoardingImage",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        )

        Spacer(
            modifier = Modifier
                .height(Dimens.MediumPadding1)
        )

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
        ) {

            Text(
                text = page.title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(horizontal = Dimens.MediumPadding2)
            )

            Text(
                text = page.body,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(horizontal = Dimens.MediumPadding2)
            )
        }

    }
}