package com.kl3jvi.repository

import org.koin.dsl.module

val appModule = module {
    single { ToDoRepositoryImpl() }
}