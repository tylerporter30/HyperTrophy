package com.example.hypertrophy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Programs(

    val id: Int,
    val name: String,
    val description: String,
)

@Composable
fun AllPrograms(programList: List<Programs>) {

    Column(modifier = Modifier.fillMaxSize()) {

        Text(text = "Beginner")

        LazyRow(

            Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        )
        {

            item {

            }

            items(programList) { programs ->
                CreateNewProgram(programs.name, programs.description)
            }
        }

        Text(text = "Intermediate")
        AllPrograms1(programs1)

        Text(text = "Advanced")
        AllPrograms2(programs2)

        CreateNewProgramButton()
    }
}

@Composable
fun CreateNewProgram(name: String, description: String) {

    Card(

        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { (name + description) },
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface

    ) {

        Row(

            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(Modifier.padding(8.dp)) {

                Text(
                    text = name,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

val programs = listOf(

    Programs(

        1,
        "Workout A",
        "5 x Squat (Barbell)\n" +
                "5 x Bench Press (Barbell)\n" +
                "5 x Bent Over Row (Barbell)"
    ),

    Programs(

        2,
        "Workout A",
        "5 x Squat (Barbell)\n" +
                "5 x Bench Press (Barbell)\n" +
                "5 x Bent Over Row (Barbell)"
    ),

    Programs(

        3,
        "Workout A",
        "5 x Squat (Barbell)\n" +
                "5 x Bench Press (Barbell)\n" +
                "5 x Bent Over Row (Barbell)"
    ),

    Programs(

        4,
        "Workout A",
        "5 x Squat (Barbell)\n" +
                "5 x Bench Press (Barbell)\n" +
                "5 x Bent Over Row (Barbell)"
    ),
)

data class Programs1(

    val id: Int,
    val name: String,
    val description: String,
)

@Composable
fun AllPrograms1(programList1: List<Programs1>) {

    LazyRow(

        Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    )
    {

        item {

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

            }
        }

        items(programList1) { programs1 ->
            CreateNewProgram1(programs1.name, programs1.description)
        }
    }
}

@Composable
fun CreateNewProgram1(name: String, description: String) {

    Card(

        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { (name + description) },
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface

    ) {

        Row(

            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(Modifier.padding(8.dp)) {

                Text(
                    text = name,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

val programs1 = listOf(

    Programs1(

        1,
        "Workout A",
        "5 x Squat (Barbell)\n" +
                "5 x Bench Press (Barbell)\n" +
                "5 x Bent Over Row (Barbell)"
    ),

    Programs1(

        2,
        "Workout A",
        "5 x Squat (Barbell)\n" +
                "5 x Bench Press (Barbell)\n" +
                "5 x Bent Over Row (Barbell)"
    ),

    Programs1(

        3,
        "Workout A",
        "5 x Squat (Barbell)\n" +
                "5 x Bench Press (Barbell)\n" +
                "5 x Bent Over Row (Barbell)"
    ),

    Programs1(

        4,
        "Workout A",
        "5 x Squat (Barbell)\n" +
                "5 x Bench Press (Barbell)\n" +
                "5 x Bent Over Row (Barbell)"
    ),
)

data class Programs2(

    val id: Int,
    val name: String,
    val description: String,
)

@Composable
fun AllPrograms2(programList2: List<Programs2>) {

    LazyRow(

        Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    )
    {

        item {

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

            }
        }

        items(programList2) { programs2 ->
            CreateNewProgram2(programs2.name, programs2.description)
        }
    }
}

@Composable
fun CreateNewProgram2(name: String, description: String) {

    Card(

        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { (name + description) },
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface

    ) {

        Row(

            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(Modifier.padding(8.dp)) {

                Text(
                    text = name,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

val programs2 = listOf(

    Programs2(

        1,
        "Workout A",
        "5 x Squat (Barbell)\n" +
                "5 x Bench Press (Barbell)\n" +
                "5 x Bent Over Row (Barbell)"
    ),

    Programs2(

        2,
        "Workout A",
        "5 x Squat (Barbell)\n" +
                "5 x Bench Press (Barbell)\n" +
                "5 x Bent Over Row (Barbell)"
    ),

    Programs2(

        3,
        "Workout A",
        "5 x Squat (Barbell)\n" +
                "5 x Bench Press (Barbell)\n" +
                "5 x Bent Over Row (Barbell)"
    ),

    Programs2(

        4,
        "Workout A",
        "5 x Squat (Barbell)\n" +
                "5 x Bench Press (Barbell)\n" +
                "5 x Bent Over Row (Barbell)"
    ),
)

@Composable
fun CreateNewProgramButton() {

    var context = LocalContext.current


    Text(text = "Don't like these programs?")

    Button(
        shape = RoundedCornerShape(10.dp),
        onClick = {
            //context.startActivity(Intent(context, ExerciseCard::class.java))
        },
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth()

    ) {

        Text(
            "Create Custom Program",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.W600,
            fontSize = 20.sp,
            modifier = Modifier.padding(1.dp)
        )
    }
}