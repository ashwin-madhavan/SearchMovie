package com.plcoding.stockmarketapp.presentation.company_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.stockmarketapp.domain.model.MovieReview
import com.plcoding.stockmarketapp.ui.theme.DarkBlue
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun MovieInfoScreen(
    movie_id: Long,
    viewModel: MovieInfoViewModel = hiltViewModel()
) {
    val state = viewModel.state
    if (state.error == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue)
                .padding(16.dp)
        ) {
            state.movie?.let { movie ->
                Text(
                    text = movie.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = movie.overview,
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            val movieReview = MovieReview(
                2, "test", "test",
                "test", "test", "test", 4
            )
            Button(onClick = { viewModel.addMovieReview(movieReview) }) {
                Text(text = "Add Review")
            }

            Button(onClick = { viewModel.getAllMovieReviews() }) {
                Text(text = "Get all Reviews")
            }
        }
    }
}