package com.dimas.onepiecefilm.di
import com.dimas.onepiecefilm.core.domain.usecase.OnePieceInteractor
import com.dimas.onepiecefilm.core.domain.usecase.OnePieceUseCase
import com.dimas.onepiecefilm.detail.DetailViewModel
import com.dimas.onepiecefilm.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<OnePieceUseCase> { OnePieceInteractor(get()) }
}
val viewModelModule = module {
    viewModel { HomeViewModel(get())}
    viewModel { DetailViewModel(get())}
}