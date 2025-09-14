package com.example.learntogether

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learntogether.ui.theme.LearnTogetherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnTogetherTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //ComposeArticle()
                    TaskCompletion()
                }
            }
        }
    }
}

// Original

val toColor: (Int) -> (Int) -> (Int) -> (Int) -> Color = {
        r -> {
        g -> {
        b -> {
        a -> Color(red = r, green = g, blue = b, alpha = a) } } }
}

val colorRGB: (Int, Int, Int) -> (Int) -> Color = {
        r, g, b -> {
        a -> toColor(r)(g)(b)(a) }
}

// http://developer.android.com/codelabs/basic-android-kotlin-compose-composables-practice-problems page.2

/*
* Jetpack Compose は、 Android プロジェクトで定義されたリソースにアクセスできる
* リソースのアクセスには、自動生成された `R` クラスが生成するリソース ID を使用する
* - R クラスはプロジェクト内の全てのリソース ID を含むクラスである
* - 例えば、 `R.drawable.graphic` は、 ./app/res/drawable にある ID = graphic のリソースを示す
* Composable 関数は必ずオプション引数として modifier: Modifier を持つ必要がある
* */
@Composable
@Preview(showBackground = true)
fun ComposeArticle(modifier: Modifier = Modifier) {
    Surface (
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (
            modifier = modifier,
        ){
            Header(modifier)
            Title(modifier)
            Abstract(modifier)
            MainText(modifier)
        }
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    val painter = painterResource(R.drawable.article_background)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Red),
    ) {
        Image(
            painter = painter,
            contentDescription = null, // TalkBack スクリーンリーダーが要求
            contentScale = ContentScale.FillWidth,
            alpha = 0.8F,
        )
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    val text = stringResource(R.string.article_text_title)
    Text(
        text = text,
        fontSize = 24.sp,
        modifier = modifier.padding(16.dp),
    )
}

@Composable
fun Abstract(modifier: Modifier = Modifier) {
    val text = stringResource(R.string.article_text_abstract)
    Text(
        text = text,
        fontSize = 20.sp,
        // Text の 段落中での行の Alignment
        // https://developer.android.com/reference/kotlin/androidx/compose/ui/text/style/TextAlign#Justify()
        // Stretch lines of text that end with a soft line break to fill the width of the container.
        // Lines that end with hard line breaks are aligned towards the Start edge.
        textAlign = TextAlign.Justify,
        modifier = modifier.padding(start = 16.dp, end = 16.dp),
    )
}

@Composable
fun MainText(modifier: Modifier = Modifier) {
    val text = stringResource(R.string.article_text_main)
    Text(
        text = text,
        fontSize = 16.sp,
        //lineHeight = 32.sp,
        textAlign = TextAlign.Justify,
        modifier = modifier.padding(16.dp),
    )
}

// http://developer.android.com/codelabs/basic-android-kotlin-compose-composables-practice-problems page.3

@Composable
@Preview(showBackground = true)
fun TaskCompletion(modifier: Modifier = Modifier) {
    Surface (
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            TaskCompletionLogo(modifier)
            TaskCompletionMessage(modifier)
            TaskCompletionComment(modifier)
        }
    }
}

@Composable
fun TaskCompletionLogo(modifier: Modifier = Modifier) {
    val painter = painterResource(R.drawable.task_completion_logo)
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painter,
            contentDescription = null, // TalkBack スクリーンリーダーが要求
            contentScale = ContentScale.None,
            alignment = Alignment.Center,
            alpha = 0.8F,
        )
    }
}

@Composable
fun TaskCompletionMessage(modifier: Modifier = Modifier) {
    val text = stringResource(R.string.task_completion_message)
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = Bold,
        textAlign = TextAlign.Center,
        modifier = modifier.padding(top=24.dp, bottom = 8.dp),
    )
}

@Composable
fun TaskCompletionComment(modifier: Modifier = Modifier) {
    val text = stringResource(R.string.task_completion_comment)
    Text(
        text = text,
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        modifier = modifier,
    )
}

// http://developer.android.com/codelabs/basic-android-kotlin-compose-composables-practice-problems page.4
