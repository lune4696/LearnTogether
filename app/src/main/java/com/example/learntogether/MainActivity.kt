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
import androidx.compose.foundation.layout.Row
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
import kotlin.io.encoding.Base64

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
                    //TaskCompletion()
                    QuadGrid()
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

@Composable
@Preview(showBackground = true)
fun QuadGrid(modifier: Modifier = Modifier) {
    // Row -> Column なので、分割順序は「左右 -> 上下」となる
    // - weight は"親に対する"分割比率なので、この順序に従っていることに注意
    Row (
        modifier = modifier.fillMaxSize(), // 親がないので分割比率もない
        horizontalArrangement = Arrangement.Center,
    ) {
        Column (
            modifier = modifier.weight(weight = 0.6F), // Row に対する Column の 水平分割比率
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            // 左上
            GridCell(
                color = colorRGB(0xEA, 0xDD, 0xFF)(0xFF),
                title = stringResource(R.string.grid_left_top_title),
                body = stringResource(R.string.grid_left_top_body),
                modifier = modifier.weight(weight = 0.3F), // Column に対する Cell の垂直分割比率
            )
            // 左下
            GridCell(
                color = colorRGB(0xB6, 0x9D, 0xF8)(0xFF),
                title = stringResource(R.string.grid_left_bottom_title),
                body = stringResource(R.string.grid_left_bottom_body),
                modifier = modifier.weight(weight = 0.5F),
            )
        }
        Column (
            modifier = modifier.weight(weight = 0.4F),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            // 右上
            GridCell(
                color = colorRGB(0xD0, 0xBC, 0xFF)(0xFF),
                title = stringResource(R.string.grid_right_top_title),
                body = stringResource(R.string.grid_right_top_body),
                modifier = modifier.weight(weight = 0.7F),
            )
            // 右下
            GridCell(
                color = colorRGB(0xF6, 0xED, 0xFF)(0xFF),
                title = stringResource(R.string.grid_right_bottom_title),
                body = stringResource(R.string.grid_right_bottom_body),
                modifier = modifier.weight(weight = 0.5F),
            )
        }
    }
}

@Composable
fun GridCell(color: Color, title: String, body: String, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .background(color)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            color = Color.Black,
            fontWeight = Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        Text(
            text = body,
            color = Color.Black,
            textAlign = TextAlign.Justify,
        )
    }
}