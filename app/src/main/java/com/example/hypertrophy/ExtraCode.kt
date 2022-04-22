package com.example.hypertrophy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Programs(

    val id: Int,
    val name: String,
    val description: String,
)

@Composable
fun AllPrograms(programList: List<Programs>) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 40.dp)
        ) {

            Text(text = "Beginner", fontSize = 22.sp)
        }

        LazyRow(

            Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(top = 15.dp)
            // this padding pushes the Beginner text up from the card
        )
        {

            item {

            }

            items(programList) { programs ->
                CreateNewProgram(programs.name, programs.description)
            }
        }
        AllPrograms1(programs1)
        AllPrograms2(programs2)
        }
        //CreateNewProgramButton()
    }


@Composable
fun CreateNewProgram(name: String, description: String) {

    var isOpen by rememberSaveable{ mutableStateOf(false) }
    //var size by rememberSaveable { mutableStateOf(Size(700, 700))}


        Card(

            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                //.animateContentSize { initialValue: IntSize, targetValue: IntSize ->  }
                //.size(150.dp)
                //.onSizeChanged {  300.dp }
                //.fillMaxHeight()
                //.wrapContentHeight()
                //.wrapContentSize()
                .clickable { isOpen = !isOpen },
            //.clickable { (name + description) },
            //shape = MaterialTheme.shapes.medium,
            elevation = 8.dp,
            //backgroundColor = MaterialTheme.colors.surface

        ) {

            Row(

                verticalAlignment = Alignment.CenterVertically
            ) {

                //this column padding makes the card bigger or smaller due to the text padding in it
                Column(Modifier.padding(start = 8.dp, end = 8.dp, top = 24.dp, bottom = 24.dp)) {

                    Text(
                        text = name,
                        style = MaterialTheme.typography.h6,
                        //color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

//                Text(
//                    text = description,
//                    style = MaterialTheme.typography.body1,
//                    color = MaterialTheme.colors.onSurface,
//                    modifier = Modifier.padding(bottom = 4.dp)
//                )

                    if (isOpen) { Text(text = description) }

                    }
                }
            }
        }



val programs = listOf(

    Programs(

        1,
        "Full Body Workout",
        "1min rest in between sets.\n" +
                "2 x 10-15 Squats\n" +
                "2 x 10-15 Bench Press\n" +
                "2 x 10-15 DB Shoulder Press\n" +
                "2 x 10-15 DB Row\n" +
                "2 x 15-20 Calf Raises\n" +
                "3 x 15-20 Ab Crunch"
    ),

    Programs(

        2,
        "Upper Body Strength",
        "1min rest in between sets.\n" +
                "2 x 10-15 DB Bench Press\n" +
                "2 x 10-15 DB Incline Fly\n" +
                "2 x 10-15 DB Shoulder Press\n" +
                "2 x 10-15 DB Lateral Raise\n" +
                "2 x 10-15 DB Row\n" +
                "2 x 10-15 Lower Back Ext"
    ),

    Programs(

        3,
        "Lower Body Strength",
        "1min rest in between sets.\n" +
                "2 x 10-15 Squats\n" +
                "2 x 10-15 DB Lunge\n" +
                "2 x 10-15  Leg Extension Machine\n" +
                "2 x 10-15 Lying Leg Curl Machine\n" +
                "2 x 15-20 Calf Raises\n"
    ),

    Programs(

        4,
        "Beginner AMRAP",
        "10min to get as many rounds as possible\n" +
                "10 Air Squats\n" +
                "10 Push-Ups or Modified Push-Ups\n" +
                "10 Mountain Climbers\n" +
                "10 Lunges\n" +
                "10 Sit Ups"
    ),
)

data class Programs1(

    val id: Int,
    val name: String,
    val description: String,
)

@Composable
fun AllPrograms1(programList1: List<Programs1>) {

    Column(modifier = Modifier.fillMaxWidth()) {

        Row(modifier = Modifier
            .align(CenterHorizontally)
            .padding(top = 40.dp)) {

            Text(text = "Intermediate", fontSize = 22.sp)
        }
    }

    LazyRow(

        Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(top = 15.dp)
    )
    {

        item {

//            Row(
//
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .wrapContentHeight()
//                    .padding(vertical = 25.dp),
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//
//            }
        }

        items(programList1) { programs1 ->
            CreateNewProgram1(programs1.name, programs1.description)
        }
    }
}

@Composable
fun CreateNewProgram1(name: String, description: String) {

    var isOpen by rememberSaveable{ mutableStateOf(false) }

    Card(

        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            //.size(150.dp)
          //  .onSizeChanged { 300.dp }
            //.fillMaxWidth()
            //.wrapContentHeight()
            .clickable(onClick = { isOpen = !isOpen }),

        //shape = MaterialTheme.shapes.medium,
        elevation = 8.dp,
        //backgroundColor = MaterialTheme.colors.surface


    ) {

        Row(

            verticalAlignment = Alignment.CenterVertically
        ) {

            Column( modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 24.dp, bottom = 24.dp)) {

                Text(
                    text = name,
                    style = MaterialTheme.typography.h6,
                    //color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

//                Text(
//                    text = description,
//                    style = MaterialTheme.typography.body1,
//                    color = MaterialTheme.colors.onSurface,
//                    modifier = Modifier.padding(bottom = 4.dp)
//                )

                if (isOpen) {
                    Text(text = description)
                }
            }
        }
    }
}

val programs1 = listOf(

    Programs1(

        1,
        "Chest and Triceps Split",
        "1.5min rest in between sets.\n" +
                "3 x 10 Bench Press\n" +
                "3 x 10 Incline DB Bench Press\n" +
                "3 x 10 Machine Fly\n" +
                "3 x 5-10 Chest Dips\n" +
                "3 x 10 DB Triceps Ext\n" +
                "3 x 10 Cable Triceps Pushdown\n" +
                "3 x 10 Rope Cable Pushdown"
    ),

    Programs1(

        2,
        "Back and Biceps Split",
        "1.5min rest in between sets.\n" +
                "3 x 8-10 Deadlift\n" +
                "3 x 10 Barbell Row\n" +
                "3 x 10 DB Row\n" +
                "3 x 10 Pulldowns\n" +
                "3 x 10 EZ Bar Curls\n" +
                "3 x 10 DB Hammer Curls\n" +
                "3 x 10 Cable Curls"
    ),

    Programs1(

        3,
        "Legs and Shoulders Split",
        "1.5min rest in between sets.\n" +
                "3 x 10 Barbell Squats\n" +
                "3 x 10 Barbell Glute Bridge\n" +
                "3 x 10 Leg Extension\n" +
                "3 x 5-10 Lying Leg Curl\n" +
                "3 x 10 Barbell Should Press\n" +
                "3 x 10 Arnold Press\n" +
                "3 x 10 DB Lateral Raise"
    ),

    Programs1(

        4,
        "Intermediate AMRAP",
        "15min to get as many rounds as possible\n" +
                "15 DB Squats\n" +
                "15 DB Step-Ups\n" +
                "10 Burpees\n" +
                "10 Push-Ups\n" +
                "15 V-Ups"
    ),
)

data class Programs2(

    val id: Int,
    val name: String,
    val description: String,
)

@Composable
fun AllPrograms2(programList2: List<Programs2>) {

    Column(modifier = Modifier.fillMaxWidth()) {

        Row(modifier = Modifier
            .align(CenterHorizontally)
            .padding(top = 40.dp)) {

            Text(text = "Advanced", fontSize = 22.sp)
        }
    }

    LazyRow(

        Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(top = 15.dp)
    )
    {

        item {

//            Row(modifier = Modifier
//                .fillMaxWidth()
//                .wrapContentHeight()
//                .padding(vertical = 25.dp),
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//
//            }
        }

        items(programList2) { programs2 ->
            CreateNewProgram2(programs2.name, programs2.description)
        }
    }
}

@Composable
fun CreateNewProgram2(name: String, description: String) {

    var isOpen by rememberSaveable{ mutableStateOf(false) }

    Card(

        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
           // .onSizeChanged { 300.dp }
//            .fillMaxWidth()
//            .wrapContentHeight()
            .clickable { isOpen = !isOpen },
            //.clickable { (name + description) },
        //shape = MaterialTheme.shapes.medium,
        elevation = 8.dp,
        //backgroundColor = MaterialTheme.colors.surface

    ) {

        Row(

            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(Modifier.padding(start = 20.dp, end = 20.dp, top = 24.dp, bottom = 24.dp)) {

                Text(
                    text = name,
                    style = MaterialTheme.typography.h6,
                    //color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

//                Text(
//                    text = description,
//                    style = MaterialTheme.typography.body1,
//                    color = MaterialTheme.colors.onSurface,
//                    modifier = Modifier.padding(bottom = 4.dp)
//                )

                if (isOpen) {
                    Text(text = description)
                }
            }
        }
    }
}

val programs2 = listOf(

    Programs2(

        1,
        "Chest",
        "1.5 to 2min rest in between sets.\n" +
                "5 x 5 Bench Press\n" +
                "3-5 x 6-8 Incline Plate Loaded Bench Press\n" +
                "3-5 x 6-8 Incline DB Bench Press\n" +
                "3-5 x 6-8 Cable Flys\n" +
                "3 x Failure Chest Dips\n"
    ),

    Programs2(

        2,
        "Back",
        "1.5 to 2min rest in between sets.\n" +
                "5 x 5 Deadlift\n" +
                "3-5 x 6-8 Barbell Row\n" +
                "3-5 x 6-8 DB Row\n" +
                "3-5 x 8-10 Pulldowns\n" +
                "3-5 x 8-10 DB Shrugs" +
                "3 x 8-10 Weighted Lower Back Extension\n"
    ),

    Programs2(

        3,
        "Shoulders",
        "1.5 to 2min rest in between sets.\n" +
                "5 x 5 Barbell Shoulder Press\n" +
                "3-5 x 6-8 DB Shoulder Press\n" +
                "3-5 x 6-8 DB Lateral Raise\n" +
                "3-5 x 8-10 Machine Reverse Flys\n"

    ),

    Programs2(

        4,
        "Arms",
        "1.5 to 2min rest in between sets.\n" +
                "3-5 x 6-8 EZ Bar Curl\n" +
                "3-5 x 8-10 DB Curl\n" +
                "3-5 x 8-10 DB Hammer Curl\n" +
                "3-5 x 8-10 Bar Reverse Curl\n" +
                "3-5 x 6-8 DB Tri Ext\n" +
                "3-5 x 8-10 Cable Tri Ext\n" +
                "3-5 x 8-10 Reverse Cable Tri Ext\n" +
                "3-5 x 8-10 DB Kickbacks\n"
    ),

    Programs2(

        5,
        "Legs",
        "1.5 to 2min rest in between sets.\n" +
                "5 x 5 Squat\n" +
                "3-5 x 6-8 Glute Bridge\n" +
                "3-5 x 6-8 DB Lunges\n" +
                "3-5 x 8-10 Leg Extension\n" +
                "3-5 x 8-10 Lying Leg Curl\n" +
                "3-5 x 10-15 Machine Calf Raises\n"

    ),

    Programs2(

        6,
        "Advanced AMRAP",
        "25min to get as many rounds as possible\n" +
                "20 DB Squats\n" +
                "20 DB Lunges\n" +
                "10 Pull-ups\n" +
                "10 Burpees\n" +
                "20 Push-Ups\n" +
                "20 Weighted Sit-Ups\n"
    ),
)

//@Composable
//fun CreateNewProgramButton() {
//
//    var context = LocalContext.current
//
//
//    Text(text = "Don't like these programs?")
//
//    Button(
//        shape = RoundedCornerShape(10.dp),
//        onClick = {
//            //context.startActivity(Intent(context, ExerciseCard::class.java))
//        },
//        modifier = Modifier
//            .padding(start = 10.dp, end = 10.dp)
//            .fillMaxWidth()
//
//    ) {
//
//        Text(
//            "Create Custom Program",
//            color = Color.White,
//            textAlign = TextAlign.Center,
//            fontFamily = FontFamily.Serif,
//            fontWeight = FontWeight.W600,
//            fontSize = 20.sp,
//            modifier = Modifier.padding(1.dp)
//        )
//    }
//}