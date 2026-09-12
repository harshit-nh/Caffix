package com.capncook.caffix.feature.home.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.capncook.caffix.R
import com.capncook.caffix.common.Constants
import com.capncook.caffix.common.ui_components.skeleton.shimmerEffect
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily
import com.capncook.caffix.common.utils.toComposeColor
import com.capncook.caffix.feature.home.domain.model.HomeSection
import com.capncook.caffix.feature.home.domain.model.Product
import com.capncook.caffix.feature.home.presentation.HomeScreenEvent
import com.capncook.caffix.feature.home.presentation.HomeScreenViewModel
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onProductClick: (Int) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()

    val showFloatingHeader by remember {
        derivedStateOf { listState.firstVisibleItemIndex >= 2 }
    }


    val gradientColors = if(state.headerGradientColors.isNotEmpty()) {
        state.headerGradientColors.map { it.toComposeColor() }
    } else {
        listOf(Color(0xFF303030), Color(0xFF1F1F1F), Color(0xFF121212))
    }

    val headerGradient = Brush.verticalGradient(colors = gradientColors)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.account_bg))  // Main bg color
    ) {


        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 120.dp)
        ) {


            //THE LOCATION HEADER
            item {

                Box(modifier = Modifier.fillMaxWidth()) {

                    Spacer(
                        modifier = Modifier
                            .matchParentSize()
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = 16.dp, bottomEnd = 16.dp
                                )
                            )
                            .background(headerGradient)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = WindowInsets.statusBars.asPaddingValues()
                                    .calculateTopPadding()
                            )
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 24.dp)
                    ) {

                        Text(
                            text = "Location",
                            color = Color.LightGray,
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal
                        )


                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Text(
                                modifier = Modifier.fillMaxWidth(2f / 3f),
                                text = state.location,
                                color = Color.White,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = Color.White
                            )

                        }

                        Spacer(modifier = Modifier.height(32.dp))


                        SearchBar(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            isDarkTheme = true
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        if(state.isLoading && state.heroBanner == null) {

                            //Banner skeleton
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(140.dp)
                                    .shimmerEffect()
                            )

                        } else{

                            state.heroBanner?.let { banner ->

                                val imageUrl = "${Constants.BASE_URL}${banner.imageUrl}"

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(160.dp)
                                        .clip(RoundedCornerShape(16.dp))
                                ) {

                                    AsyncImage(
                                        model = imageUrl,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            }

                        }


                        Spacer(modifier = Modifier.height(10.dp))

                    }

                }

            }


            item {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))

                    if(state.isLoading && state.categories.isEmpty()) {

                        CategorySkeletonRow()

                    } else {

                        HomeScreenCategories(
                            categories = state.categories,
                            selectedCategoryId = state.selectedCategoryId,
                            onCategoryClick = { id ->
                                viewModel.onEvent(HomeScreenEvent.OnCategorySelected(id))
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                }
            }


            // THE DYNAMIC FEED (Server-Driven UI Pattern)
            if(state.isLoading) {

                repeat(3) {
                    item {
                        Column {

                            //Title skeleton
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                                    .width(180.dp)
                                    .height(24.dp)
                                    .shimmerEffect()
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            //Product skeleton
                            ProductCarouselSkeleton()

                            Spacer(modifier = Modifier.height(32.dp))
                        }
                    }
                }
            }else {

                state.feedSections.forEach { section ->

                    when(section) {

                        is HomeSection.CoffeeCarousel -> {
                            item {
                                Column {

                                    Text(
                                        text = section.title,
                                        fontFamily = poppinsFontFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp,
                                        color = Color.Black,
                                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    LazyRow(
                                        contentPadding = PaddingValues(horizontal = 16.dp),
                                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                                    ) {

                                        items(section.products) { product ->

                                            ProductCardNew(
                                                modifier = Modifier.fillParentMaxWidth(
                                                    fraction = 0.44f
                                                ),
                                                product = product,
                                                onProductClick = {
                                                    viewModel.onEvent(HomeScreenEvent.OnProductClicked(product.id))
                                                    onProductClick(product.id)
                                                },
                                                isFavorite = false
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(32.dp))

                                }
                            }
                        }

                        is HomeSection.PromoBanner -> {

                        }
                    }
                }
            }
        }




        // This is hidden initially. It only animates in when the user scrolls past the banner!
        AnimatedVisibility(
            visible = showFloatingHeader,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut(),
            modifier = Modifier.align(Alignment.TopCenter)
        ) {

            // Surface automatically adds a nice subtle drop shadow (elevation)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
                color = Color.White,
                shadowElevation = 14.dp
            ) {

                Column(
                    modifier = Modifier
                        // We must add status bar padding here so it clears the clock/battery!
                        .padding(
                            top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
                        )
                        .padding(vertical = 12.dp)
                ) {

                    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                        SearchBar(isDarkTheme = false) // The Cloned Search Bar
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    if(state.categories.isNotEmpty()) {

                        HomeScreenCategories(
                            categories = state.categories,
                            selectedCategoryId = state.selectedCategoryId,
                            onCategoryClick = {
                                viewModel.onEvent(
                                    HomeScreenEvent.OnCategorySelected(
                                        it
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }




    }
}