package com.example.lemonadeapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@Composable
fun LemonApp() {
    var currentStep by remember{ mutableStateOf(1)}

    var squeezeCount by remember { mutableStateOf(0) }

        // A surface container using the 'background' color from the theme
    Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,

    ) {
        when (currentStep) {
            1 -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LemonTextAndImage(
                        text = stringResource(id = R.string.step_1),
                        imageResource = R.drawable.lemon_tree,
                        contentDescription = stringResource(id = R.string.lemon_tree),
                        onImageClick = {
                            currentStep = 2
                            squeezeCount = (2..4).random()
                        }
                    )
                }
            }

            2 -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LemonTextAndImage(
                        text = stringResource(id = R.string.step_2),
                        imageResource = R.drawable.lemon_squeeze,
                        contentDescription = stringResource(id = R.string.lemon),
                        onImageClick = {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                currentStep = 3
                            }
                        }
                    )
                }
            }

            3 -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LemonTextAndImage(
                        text = stringResource(id = R.string.step_3),
                        imageResource = R.drawable.lemon_drink,
                        contentDescription = stringResource(id = R.string.glass_of_lemonade),
                        onImageClick = { currentStep = 4 }
                    )
                }
            }

            4 -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LemonTextAndImage(
                        text = stringResource(id = R.string.step_4),
                        imageResource = R.drawable.lemon_restart,
                        contentDescription = stringResource(id = R.string.empty_glass),
                        onImageClick = { currentStep = 1 }

                    )
                }
            }
        }
    }
}


@Composable
fun LemonTextAndImage(text: String, imageResource: Int, contentDescription: String, onImageClick:() -> Unit) {
    Box (modifier = Modifier){
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = contentDescription,
                modifier = Modifier
                    .wrapContentSize()
                    .clip(shape)
                    .border(
                        width = 2.dp,
                        color = Color.Cyan,
                        shape = RoundedCornerShape(size = 15.dp)
                    )
                    .background(color = Color.Cyan)
                    .clickable { onImageClick },
                contentScale = ContentScale.Crop

            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = text,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = onImageClick) {
                Text(text = stringResource(id = R.string.Tap))
            }


        }
    }

}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeAppTheme {
        LemonApp()
    }
}