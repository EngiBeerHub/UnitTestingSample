package com.example.unittestingsample

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase


@Dao
interface RepositoryDao {
    @Insert
    fun insertAll(vararg repositories: Repository)

    @Query("SELECT * FROM repository WHERE owner = :owner")
    fun findByOwner(owner: String): List<Repository>
}

@Database(entities = [Repository::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}

class RepositoryLocalDataSource(val db: AppDatabase) {
    fun insertAll(vararg repositories: Repository) {
        db.repositoryDao().insertAll(*repositories)
    }

    fun findByOwner(owner: String): List<Repository> {
        return db.repositoryDao().findByOwner(owner)
    }
}