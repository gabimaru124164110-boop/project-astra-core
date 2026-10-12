package com.projectastra.database

import android.content.Context
import androidx.room.Room

class AstraCatalogRepository private constructor(
    context: Context,
) {
    private val database: AstraDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            AstraDatabase::class.java,
            "astra_catalog.db",
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    suspend fun brightestStars(limit: Int = 50) = database.starCatalogDao().brightestStars(limit)

    suspend fun searchByDesignation(query: String) =
        database.starCatalogDao().searchByDesignation("%$query%")

    companion object {
        @Volatile
        private var instance: AstraCatalogRepository? = null

        fun getInstance(context: Context): AstraCatalogRepository {
            return instance ?: synchronized(this) {
                instance ?: AstraCatalogRepository(context).also { instance = it }
            }
        }
    }
}
