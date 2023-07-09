package com.zseni.harrypotter.features_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.zseni.harrypotter.core.domain.model.CharactersItem
import com.zseni.harrypotter.core.presentation.components.ApiImage
import com.zseni.harrypotter.core.presentation.components.CharacterImage
import com.zseni.harrypotter.core.presentation.components.Lottie
import com.zseni.harrypotter.core.presentation.components.PillBtn
import com.zseni.harrypotter.features_home.model.DetailModel
import com.zseni.hogwarts_school.R

@Composable
fun DetailBottomSheet(
    character:CharactersItem,
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current
    val horizontalListState = rememberLazyStaggeredGridState()
    val staggeredListState = rememberLazyStaggeredGridState()

    // Details for each character
    val characterDetails = remember{
        mutableListOf(
            DetailModel("EyeColor",character.eyeColour,Icons.Outlined.Visibility),
            DetailModel("D.O.B",character.dateOfBirth,Icons.Outlined.CalendarMonth),
            DetailModel("Ancestry",character.ancestry,Icons.Outlined.Timeline),
            DetailModel("Gender",character.gender,Icons.Outlined.Male),
            DetailModel("HairColour",character.hairColour,Icons.Outlined.Palette),
            DetailModel("Patronus",character.patronus,Icons.Filled.SupportAgent),
            DetailModel("Species",character.species,Icons.Outlined.Animation),
            DetailModel("Year of Birth",character.yearOfBirth.toString(),Icons.Outlined.CalendarToday),
            DetailModel("Actor",character.actor,Icons.Outlined.Movie),
            DetailModel("ALive",character.alive.toString(),Icons.Outlined.HealthAndSafety),
            DetailModel("Stunt Doubles",character.alternate_actors.toString(),Icons.Outlined.Diamond),
            DetailModel("Alternate Names",character.alternate_names.toString(),Icons.Outlined.AppSettingsAlt)
        )
    }
    Scaffold(
        topBar = {

        }
    ) {contentPadding ->

        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(contentPadding)
        ){
            Column(modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                        // Show an Icon if a wizard character appears
                    ) {

                        if (character.wizard) {
                            CharacterImage(
                                context = context,
                                uri = character.image.toUri()
                            )
                        }else{
                            ApiImage(
                                context =context,
                                imageUri = character.image.toUri(),
                                imgHolder = R.drawable.profile_img,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(120.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        // Wizard Name
                        Text(
                            text = character.name,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.secondary
                        )

                    }
                    //House Section
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PillBtn(
                            title = character.house,
                            verticalPadding = 8.dp,
                            onClick = {}
                        )
                    }
                }
                //Show nicknames if it contains anyValue
                if(character.alternate_names.isNotEmpty()){

                    Spacer(modifier = Modifier.height(8.dp))

                    //LazyGrid to display the names
                    LazyHorizontalStaggeredGrid(

                        rows = StaggeredGridCells.Fixed(1),
                        content = {
                            items(character.alternate_names){ names->
                                PillBtn(
                                    title = names,
                                    textColour = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f),
                                    containerColour = MaterialTheme.colorScheme.onSecondary,
                                    horizontalPadding = 16.dp,
                                    verticalPadding = 16.dp,
                                    onClick = {}
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        },

                        state = horizontalListState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        horizontalItemSpacing = 16.dp,
                       // verticalArrangement = Arrangement.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                // Wand Details
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(MaterialTheme.colorScheme.onSecondary),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Lottie(
                        rawFile = R.raw.wand,
                        isPlaying = true,
                        iterations = Int.MAX_VALUE,
                        modifier = Modifier
                            .size(80.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    //Wand Name
                    Text(
                        text = stringResource(id = R.string.wand),
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    //Wand Details
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Core
                        WandCard(
                            title = stringResource(id = R.string.core),
                            icon = Icons.Outlined.DashboardCustomize,
                            description = character.wand.core,
                            modifier = Modifier
                                .size(80.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        //Length
                        WandCard(
                            title = stringResource(id = R.string.length),
                            icon =Icons.Outlined.Timelapse,
                            description = character.wand.length.toString(),
                            modifier = Modifier
                                .size(80.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        //Wood
                        WandCard(
                            title = stringResource(id = R.string.wood),
                            icon = Icons.Outlined.Handyman,
                            description = character.wand.wood,
                            modifier = Modifier
                                .size(80.dp)
                        )
                }

            }
                //Further Details Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ){
                    Text(
                        text = stringResource(id = R.string.extra_info),
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
                    )

                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2),
                        content ={
                            items(characterDetails){ detail->
                                if (detail.description.isNotEmpty()){
                                    DetailCard(detail)
                                }
                            }
                        },
                        state = staggeredListState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                       verticalItemSpacing = 10.dp
                    )

                }

            }
        }

    }
}