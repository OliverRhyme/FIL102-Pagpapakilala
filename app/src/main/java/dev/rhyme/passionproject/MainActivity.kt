package dev.rhyme.passionproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.pager.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.rhyme.passionproject.ui.theme.PassionProjectTheme
import kotlinx.coroutines.launch

@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {

            // Remember a SystemUiController
            val systemUiController = rememberSystemUiController()

            val pagerState = rememberPagerState(pageCount = 6)
            val bgColor by animateColorAsState(
                targetValue = when (pagerState.targetPage) {
                    0 -> Color.Cyan
                    1 -> Color.DarkGray
                    2 -> Color(0xFFFFDE03)
                    3 -> Color(0xFF0336FF)
                    5 -> Color(0xFFFF0266)
                    else -> colorResource(id = R.color.teal_200)
                }
            )
            val isDark = isDarkColor(bgColor)

            PassionProjectTheme(darkTheme = !isDark) {
                ProvideWindowInsets {
                    val isLight = MaterialTheme.colors.isLight
                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            color = Color.Transparent,
                            darkIcons = isLight,
                            isNavigationBarContrastEnforced = false
                        )
                    }

                    Scaffold(
                        backgroundColor = bgColor,
                        contentColor = MaterialTheme.colors.onSurface
                    ) {

                        Column(
                            modifier = Modifier
                                .systemBarsPadding()
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {

                            HorizontalPager(
                                modifier = Modifier.weight(1f),
                                state = pagerState
                            ) { page ->
                                when (page) {
                                    0 -> FirstPage(modifier = Modifier.fillMaxSize())
                                    1 -> SecondPage(modifier = Modifier.fillMaxSize())
                                    2 -> ThirdPage(modifier = Modifier.fillMaxSize())
                                    3 -> FourthPage(modifier = Modifier.fillMaxSize())
                                    4 -> FifthPage(modifier = Modifier.fillMaxSize())
                                    5 -> SixthPage(modifier = Modifier.fillMaxSize())
                                    else -> Box(Modifier.fillMaxSize())
                                }

                            }
                            BottomNav(pagerState = pagerState)
                        }

                    }
                }
            }
        }
    }
}

fun isDarkColor(color: Color): Boolean {
    val whiteContrast = ColorUtils.calculateContrast(Color.White.toArgb(), color.toArgb())
    val blackContrast = ColorUtils.calculateContrast(Color.Black.toArgb(), color.toArgb())

    return whiteContrast < blackContrast
}

@Composable
fun Page(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = content,
    )
}

@Composable
fun FirstPage(modifier: Modifier = Modifier) {
    Page(
        modifier = modifier,
    ) {
        Text("Hi!", style = MaterialTheme.typography.h3)
        Text("It's nice to meet you!", style = MaterialTheme.typography.h4)
        Text("I am Oliver Rhyme G. Añasco")
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.oliver),
            contentDescription = "Oliver",
        )
    }
}

@Composable
fun SecondPage(
    modifier: Modifier = Modifier
) {
    Page(modifier = modifier) {
        Text(
            "Let me introduce myself!",
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.tagoloan_1),
            contentDescription = "Oliver",
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Im 20 year old student, company co-founder, chief technological officer, future computer engineer and son. " +
                "I am currently living in Tagoloan, Misamis Oriental which is famous for our tallest standing Christmas tree in the whole Mindanao.")
    }
}

@Composable
fun ThirdPage(
    modifier: Modifier = Modifier,
) {
    Page(modifier = modifier) {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Just a bit of a background", style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "I am a 2nd year BS in Computer Engineering student at " +
                    "Mindanao State University - Iligan Institute of Technology."
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "I am also a Capitol University Senior High School alumnus of the strand STEM - Engineering."
        )
    }
}

@Composable
fun FourthPage(
    modifier: Modifier = Modifier
) {
    Page(modifier = modifier) {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "What do you love?", style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "My hobbies are reading science and technology books, programming (as you can see 😜) " +
                    "and also I love to travel to far places."
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "I also really love doing the hardware side of things, maybe that's why I pick Computer Engineering. " +
                    "I also know that I always want to feed my curiosity."
        )
    }
}

@Composable
fun FifthPage(
    modifier: Modifier = Modifier
) {
    Page(modifier = modifier) {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "So what's with this app?", style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "I created this app to showcase my greatest passion with programming and creating apps that help people." +
                    "I treat programming and technology in general as my go-to \"stress reliever\" " +
                    "and I feel I am in my safe space by doing the things I love the most."
        )
    }
}

@Composable
fun SixthPage(
    modifier: Modifier = Modifier
) {
    Page(modifier = modifier) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(16.dp)
                .offset(y = (-4).dp),
            painter = painterResource(id = R.drawable.msu_iit),
            contentDescription = "MSU-IIT",
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
//            modifier = Modifier.align(Alignment.Start),
            text = "Thank you for reading!", style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Submitted by: Añasco, Oliver Rhyme G.")
        Text(text = "Submitted to: Ligawon, Elgie T. ")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "MAT103 A4", fontStyle = FontStyle.Italic)

    }
}

@ExperimentalPagerApi
@Composable
fun BottomNav(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val shouldShowBack = pagerState.targetPage != 0
        val shouldShowForward =
            pagerState.targetPage != pagerState.pageCount - 1

        val animationScope = rememberCoroutineScope()

        val backAlpha: Float by animateFloatAsState(if (shouldShowBack) 1f else 0f)
        val forwardAlpha: Float by animateFloatAsState(if (shouldShowForward) 1f else 0f)

        IconButton(
            modifier = Modifier.alpha(backAlpha),
            enabled = shouldShowBack,
            onClick = {
                animationScope.launch {
                    pagerState.animateScrollToPage(
                        pagerState.targetPage - 1
                    )
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Backward"
            )
        }

        HorizontalPagerIndicator(
            modifier = Modifier.padding(16.dp),
            pagerState = pagerState
        )

        IconButton(
            modifier = Modifier.alpha(forwardAlpha),
            enabled = shouldShowForward,
            onClick = {
                animationScope.launch {
                    pagerState.animateScrollToPage(
                        pagerState.targetPage + 1
                    )
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Forward"
            )
        }

    }
}