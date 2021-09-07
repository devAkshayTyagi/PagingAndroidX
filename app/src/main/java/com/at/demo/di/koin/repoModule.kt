package com.at.demo.di.koin

import com.at.demo.model.repo.BaseRepository
import com.at.demo.model.repo.PhotoRepository
import org.koin.dsl.module


val repoModule = module {

    /**Provide VideoRepository class Singleton object
     * you can use it any KoinComponent class  below is declaration
     *  private val videoRepo: VideoRepository by inject() */

    single { BaseRepository() }
    single { PhotoRepository(get()) }

}