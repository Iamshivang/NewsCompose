package com.example.newscompose.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.newscompose.presentation.common.NewsButton
import com.example.newscompose.presentation.common.NewsTextButton
import com.example.newscompose.presentation.onboarding.components.OnBoardingPage
import com.example.newscompose.presentation.onboarding.components.PageIndicator
import kotlinx.coroutines.launch

/*
 * Author: Shivang Yadav
 * Created: 6/5/25
 * Description:
 */

@Composable
fun OnBoardingScreen(onClick: () -> Unit) {


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

//        val pagerState = rememberPagerState(initialPage = 0, pageCount = {pages.size})
        val pagerState = rememberPagerState(initialPage = 0){
            pages.size
        }

        val buttonState = remember {
            derivedStateOf {
                when(pagerState.currentPage){

                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(pages[index])
        }

        Spacer(
            modifier = Modifier
                .weight(.5f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){

            PageIndicator(
                modifier = Modifier
                    .width(Dimens.PageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){

                val scope = rememberCoroutineScope()

                if(buttonState.value[0].isNotEmpty()){

                    NewsTextButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage- 1)
                            }
                        }
                    )
                }

                NewsButton(
                    text = buttonState.value[1],
                    onClick = {
                        scope.launch {

                            if(pagerState.currentPage == 2){

                                onClick()
                            }else{

                                pagerState.animateScrollToPage(pagerState.currentPage+ 1)
                            }
                        }
                    }
                )
            }
        }

        Spacer(
            modifier = Modifier
                .weight(0.5f)
        )
    }
}