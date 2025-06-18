package com.example.newscompose.presentation.navgraph

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.newscompose.R
import kotlinx.coroutines.launch

/*
 * Author: Shivang Yadav
 * Created: 6/17/25
 * Description: [Add description here]
 */


@Composable
fun DrawerContent(
    menus: Array<DrawerMenu>,
    onMenuClick: (Route) -> Unit,
    navHostController: NavHostController,
    drawerState: DrawerState
) {

    val viewModel: NavDrawerViewModel= hiltViewModel()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painterResource(R.drawable.account),
                contentDescription = "Article Image",
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .size(180.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Shivang Yadav",
                style = MaterialTheme.typography.titleLarge.copy(MaterialTheme.colorScheme.secondary),
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Divider(Modifier.height(2.dp))

        Spacer(modifier = Modifier.height(12.dp))
        menus.forEach {
            NavigationDrawerItem(
                label = { Text(text = it.title) },
                icon = { Icon(painter = painterResource(it.icon), contentDescription = null) },
                selected = false,
                onClick = {
                    onMenuClick(it.route)
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                viewModel.logout()
                navHostController.navigate(Route.LoginScreen)
                scope.launch {
                    drawerState.close()
                    snackbarHostState.showSnackbar("Logged out")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .height(45.dp),
            shape = RoundedCornerShape(20.dp),
            content = {
                Text(text = "Logout",
                        style = MaterialTheme.typography.titleMedium.copy(MaterialTheme.colorScheme.background)) }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painterResource(R.drawable.ic_logo),
                contentDescription = "Article Image",
                modifier = Modifier
                    .size(80.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = "1.1",
                style = MaterialTheme.typography.titleSmall.copy(MaterialTheme.colorScheme.secondary),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun DrawerContentPreviewDark(){
//
//    DrawerContent(
//        menus,
//        {}
//    ) { }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun DrawerContentPreview(){
//
//    DrawerContent(
//        menus,
//        {}
//    ) { }
//}