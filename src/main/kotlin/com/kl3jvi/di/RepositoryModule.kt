package com.kl3jvi.di

import com.kl3jvi.persistence.ToDoDao
import com.kl3jvi.repository.ToDoRepository
import com.kl3jvi.repository.ToDoRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ToDoRepository> {
        val dao = get<ToDoDao>()
        ToDoRepositoryImpl(dao)
    }
}

