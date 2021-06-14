package com.dimas.onepiecefilm

import android.app.Application
import com.dimas.onepiecefilm.core.di.databaseModule
import com.dimas.onepiecefilm.core.di.networkModule
import com.dimas.onepiecefilm.core.di.repositoryModule
import com.dimas.onepiecefilm.di.useCaseModule
import com.dimas.onepiecefilm.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}