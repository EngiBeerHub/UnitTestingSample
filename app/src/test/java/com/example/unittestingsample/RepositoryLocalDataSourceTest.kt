package com.example.unittestingsample

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.assertj.core.api.Assertions.*

@RunWith(RobolectricTestRunner::class)
class RepositoryLocalDataSourceTest {
    lateinit var repositoryLocalDataSource: RepositoryLocalDataSource

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val db = Room
            .databaseBuilder(context, AppDatabase::class.java, "DB")
            .allowMainThreadQueries()
            .build()
        repositoryLocalDataSource = RepositoryLocalDataSource(db)
    }

    @Test
    fun findByOwner_expectsEmpty() {
        val list = repositoryLocalDataSource.findByOwner("shiroyama")
        assertThat(list).isEmpty()
    }

    @Test
    fun insertAll_finishesSuccessfully() {
        val owner = "shiroyama"
        repositoryLocalDataSource.insertAll(
            Repository(1, "hello", "hello", owner),
            Repository(2, "world", "world", owner)
        )
        val list = repositoryLocalDataSource.findByOwner("shiroyama")
        assertThat(list).hasSize(2)
    }
}