package com.at.demo

import android.app.Application
import android.content.Context
import com.at.demo.di.koin.appModule
import com.at.demo.di.koin.repoModule
import com.at.demo.di.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class MyApplication : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        /*** start Koin DI  */
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(getModule())
        }
    }

    /*** function to get all di modules array*/
    private fun getModule(): List<Module> {
        return listOf(
            appModule,
            viewModelModule,
            repoModule
        )
    }

}