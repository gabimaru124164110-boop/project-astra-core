package com.projectastra.database

import android.content.Context
import androidx.room.Room

class CatalogRepository private constructor(
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

    suspend fun searchObjects(query: String): List<StarCatalogEntity> =
        database.starCatalogDao().searchByDesignation("%$query%")

    suspend fun brightestObjects(limit: Int = 100): List<StarCatalogEntity> =
        database.starCatalogDao().brightestStars(limit)

    companion object {
        @Volatile
        private var instance: CatalogRepository? = null

        fun getInstance(context: Context): CatalogRepository {
            return instance ?: synchronized(this) {
                instance ?: CatalogRepository(context).also { instance = it }
            }
        }
    }
}
