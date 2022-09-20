package com.kl3jvi.di

import com.kl3jvi.persistence.ToDoDao
import com.kl3jvi.persistence.ToDoDaoImpl
import org.koin.dsl.module

val persistenceModule = module {
    single<ToDoDao> { ToDoDaoImpl() }
}