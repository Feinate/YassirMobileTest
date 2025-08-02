package com.mhdncb.yassirtest.feature_main.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.mhdncb.yassirtest.R
import com.mhdncb.yassirtest.core.domain.model.Character
import com.mhdncb.yassirtest.core.ui.theme.GreyDark900
import com.mhdncb.yassirtest.core.ui.theme.SurfacePrimaryContainerAlt
import com.mhdncb.yassirtest.core.ui.theme.White
import com.mhdncb.yassirtest.core.ui.theme.YassirPurple
import timber.log.Timber

@Composable
fun CharacterCard(
    character: Character,
    onClick: (character: Character) -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = SurfacePrimaryContainerAlt
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        onClick = { onClick(character) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(character.image)
                    .crossfade(true)
                    .listener(
                        onError = { request, result ->
                            Timber.e("Error loading image : ${result.throwable}")
                        }
                    )
                    .build(),
                error = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "Character : ${character.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .aspectRatio(1f)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 12.dp, horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = character.name,
                    color = GreyDark900
                )
                Text(
                    text = character.species,
                    color = White,
                    fontSize = 14.sp,
                    modifier = Modifier.background(color = YassirPurple, shape = CircleShape).padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }
        }
    }
}