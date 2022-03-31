package config.koin

import database.Database
import database.DatabaseImp
import org.koin.dsl.module

val koinModule = module {
    single { DatabaseImp() as Database }
}