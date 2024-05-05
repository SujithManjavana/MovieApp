package com.sujith.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.sujith.movieapp.R
import com.sujith.movieapp.model.Movie
import com.sujith.movieapp.model.getMovies

@Preview
@Composable
fun MovieItem(movie: Movie = getMovies()[0], onItemClick: (String) -> Unit = {}) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(16.dp)
            // .height(130.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .padding(12.dp), shape = RectangleShape, color = Color.Transparent
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .build(),
                    contentDescription = "Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )
            }

            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.headlineSmall)
                Text(
                    text = "Director ${movie.director}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(text = "Year ${movie.year}", style = MaterialTheme.typography.bodyMedium)

                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 13.sp)) {
                                append("Plot: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Light
                                )
                            ) {
                                append(movie.plot)
                            }
                        })
                        HorizontalDivider(modifier = Modifier.padding(3.dp))
                        Text(text = movie.actors, style = MaterialTheme.typography.bodyMedium)
                        Text(text = movie.rating, style = MaterialTheme.typography.bodyMedium)
                    }
                }

                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "View more",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { expanded = !expanded }
                )

            }

        }
    }
}
