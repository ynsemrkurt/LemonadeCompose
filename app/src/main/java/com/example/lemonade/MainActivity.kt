package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadePreview()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    val currentStep = remember { mutableIntStateOf(1) }
    val (image, text, contentDescription) = fetchImageAndText(currentStep.value)
    Text(
        text = stringResource(id = R.string.app_name),
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier
            .background(Color(0xFFF8E34C))
            .statusBarsPadding()
            .fillMaxWidth()
            .height(60.dp)
            .wrapContentSize(Alignment.Center))
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(onClick = {
            currentStep.value++
            if (currentStep.value == 5) {
                currentStep.value = 1
            }
        },
            shape = MaterialTheme.shapes.extraLarge) {
            Image(
                painter = painterResource(id = image),
                contentDescription = stringResource(contentDescription),
                modifier = Modifier
                    .background(Color(0xFFCAEAD3), shape = MaterialTheme.shapes.extraLarge)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun fetchImageAndText(currentStep: Int): Triple<Int, Int, Int> {
    return when (currentStep) {
        1 -> Triple(R.drawable.lemon_tree, R.string.text_first, R.string.tree)
        2 -> Triple(R.drawable.lemon_squeeze, R.string.text_second, R.string.lemon)
        3 -> Triple(R.drawable.lemon_drink, R.string.text_third, R.string.glass)
        else -> Triple(R.drawable.lemon_restart, R.string.text_fourth, R.string.empty)
    }
}