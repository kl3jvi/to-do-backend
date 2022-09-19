package com.kl3jvi.repository

import com.kl3jvi.persistence.ToDoDaoImpl
import org.koin.dsl.module

val appModule = module {
    single { ToDoRepositoryImpl() }
    single { ToDoDaoImpl() }
}

