package com.capncook.caffix.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capncook.caffix.R
import com.capncook.caffix.common.Resource
import com.capncook.caffix.feature.home.domain.model.Category
import com.capncook.caffix.feature.home.domain.model.HomeSection
import com.capncook.caffix.feature.home.domain.model.Product
import com.capncook.caffix.feature.home.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {


    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()


    init {
        loadInitialData()
    }



    fun onEvent(event: HomeScreenEvent) {

        when(event) {

            is HomeScreenEvent.OnCategorySelected -> {
                selectCategory(event.categoryId)
            }

            is HomeScreenEvent.OnProductClicked -> {

            }

            is HomeScreenEvent.OnRefresh -> {
                refreshHomeData()
            }

            else -> {

            }
        }
    }


    private fun loadInitialData() {

        viewModelScope.launch {

            _state.update { it.copy(isLoading = true, error = null) }

            when(val categoryResult = repository.getCategories()) {

                is Resource.Success -> {

                    val categories = categoryResult.data ?: emptyList()
                    val defaultSelectedId = categories.firstOrNull()?.id  //All will be selected


                    //Fetch dummy products and sections as of now
                    val dummyProducts = listOf(
                        Product(1, "Espresso", "Strong and Rich", 120.00, R.drawable.espresso),
                        Product(2, "Latte", "Smooth and Creamy", 160.00, R.drawable.caramel_latte),
                        Product(3, "Cappuccino", "With chocolate", 128.00, R.drawable.cappuccino),
                        Product(4, "Mocha", "With cocoa flavor", 140.00, R.drawable.mocha),
                        Product(5, "Macchiato", "Bold and milky", 150.00, R.drawable.macchiato_lespresso),
                        Product(6, "Flat White", "Velvety smooth", 110.00, R.drawable.iced_flat_white),
                        Product(7, "Iced Mocha", "Refreshing and rich", 220.00, R.drawable.iced_mocha)
                    )


                    val icedDummyProducts = listOf(
                        Product(1,"Iced Mocha", "Refreshing Mocha", 222.00, R.drawable.iced_mocha),
                        Product(2, "Iced Latte", "Cold Italian Latte", 180.00, R.drawable.iced_latte),
                        Product(3,"Iced Cappuccino","Creamy Iced Cappuccino",340.00, R.drawable.iced_cappuccino),
                        Product(4, "Iced Flat White", "ICE", 230.00, R.drawable.iced_flat_white),
                        Product(5, "Iced Caramel Macchiato", "Iced Macchiato", 340.00, R.drawable.iced_caramel_macchiato)
                    )

                    val dummySections = listOf(
                        HomeSection.PromoBanner(imageRes = R.drawable.banner_1),
                        HomeSection.CoffeeCarousel("Popular Brews", dummyProducts),
                        HomeSection.CoffeeCarousel("Recently Ordered", dummyProducts.reversed()),
                        HomeSection.CoffeeCarousel("Famous Iced Brews", icedDummyProducts)
                    )

                    _state.update {
                        it.copy(
                            location = "83, Bara Bazaar, Bareilly, Opposite to Reena Model Public School, 243003, U.P",
                            categories = categories,
                            selectedCategoryId = defaultSelectedId,
                            isLoading = false,
                            feedSections = dummySections,
                            error = null
                        )
                    }

                }

                is Resource.Error -> {

                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = categoryResult.message ?: "Failed to load categories"
                        )
                    }
                }


                is Resource.Loading -> Unit
            }
        }
    }


    private fun selectCategory(categoryId: String?) {

        viewModelScope.launch {

            _state.update{
                it.copy(selectedCategoryId = categoryId, isLoading = true)
            }

            delay(1500.milliseconds)

            // Here you would normally fetch filtered products based on categoryId
            val dummyProducts = listOf(
                Product(1, "Caramel Macchiato", "Category specific blend", 130.00, R.drawable.caramel_macchiato),
                Product(4, "Mocha", "With cocoa flavor", 140.00, R.drawable.mocha),
                Product(5, "Macchiato", "Bold and milky", 150.00, R.drawable.macchiato_lespresso),
                Product(6, "Flat White", "Velvety smooth", 110.00, R.drawable.iced_flat_white),
                Product(7, "Iced Mocha", "Refreshing and rich", 220.00, R.drawable.iced_mocha)
            )

            val icedDummyProducts = listOf(
                Product(1,"Iced Mocha", "Refreshing Mocha", 222.00, R.drawable.iced_mocha),
                Product(2, "Iced Latte", "Cold Italian Latte", 180.00, R.drawable.iced_latte),
                Product(3,"Iced Cappuccino","Creamy Iced Cappuccino",340.00, R.drawable.iced_cappuccino),
                Product(4, "Iced Flat White", "ICE", 230.00, R.drawable.iced_flat_white),
                Product(5, "Iced Caramel Macchiato", "Iced Macchiato", 340.00, R.drawable.iced_caramel_macchiato)
            )

            val filteredSections = listOf(
                HomeSection.CoffeeCarousel(title = "Popular Hot Brews", products = dummyProducts),
                HomeSection.CoffeeCarousel(title = "Iced Brews Options", products = icedDummyProducts)
            )

            _state.update {
                it.copy(
                    isLoading = false,
                    feedSections = filteredSections
                )
            }

        }

    }


    private fun refreshHomeData() {

        viewModelScope.launch {
            _state.update{ it.copy(isRefreshing = true) }

            delay(1500.milliseconds)

            _state.update{ it.copy(isRefreshing = false) }

        }
    }





}