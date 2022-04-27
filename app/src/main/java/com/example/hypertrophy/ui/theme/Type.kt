package com.example.hypertrophy.ui.theme


import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

//Replace with your font locations
val Roboto = FontFamily.Default

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    h1 = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.W600,
        fontSize = 45.sp
    ),
    h2 = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.W600,
        fontSize = 35.sp
    ),
    h3 = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),
    h4 = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.W500,
        fontSize = 24.sp
    ),
    h5 = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    h6 = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.W400,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp
    ),
//    subtitle2 = TextStyle(
//        fontFamily = FontFamily.Serif,
//        fontWeight = FontWeight.W300,
//        fontSize = 16.sp
//    ),
//    body2 = TextStyle(
//        fontFamily = FontFamily.Serif,
//        fontSize = 14.sp
//    ),
//    button = TextStyle(
//        fontFamily = FontFamily.Serif,
//        fontWeight = FontWeight.W500,
//        fontSize = 20.sp
//    ),
//    caption = TextStyle(
//        fontFamily = FontFamily.Serif,
//        fontWeight = FontWeight.Bold,
//        fontSize = 14.sp
//    ),
//    overline = TextStyle(
//        fontFamily = FontFamily.Serif,
//        fontWeight = FontWeight.W500,
//        fontSize = 12.sp
//    )
)

/* Other default text styles to override
button = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp
),
caption = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)
*/

