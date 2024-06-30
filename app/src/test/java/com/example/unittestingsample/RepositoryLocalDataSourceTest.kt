package com.example.unittestingsample

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.assertj.core.api.Assertions.*
import org.junit.After
import org.junit.experimental.runners.Enclosed

@RunWith(Enclosed::class)
class EnclosedTest {
    abstract class DBTest {
        lateinit var repositoryLocalDataSource: RepositoryLocalDataSource

        @Before
        fun setUpParent() {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            val db = Room
                .databaseBuilder(context, AppDatabase::class.java, "DB")
                .allowMainThreadQueries()
                .build()
            repositoryLocalDataSource = RepositoryLocalDataSource(db)
        }

        @After
        fun tearDownParent() {
        }
    }

    @RunWith(RobolectricTestRunner::class)
    class BlankRecord : DBTest() {
        @Test
        fun insertAll_successfully_persist_record() {
            repositoryLocalDataSource.insertAll(
                Repository(0, "hello", "hello", "shiroyama")
            )
            val shiroyamaOwners = repositoryLocalDataSource.findByOwner("shiroyama")
            assertThat(shiroyamaOwners).hasSize(1)
        }
    }

    @RunWith(RobolectricTestRunner::class)
    class RecordPrepared : DBTest() {
        @Before
        fun setUp() {
            repositoryLocalDataSource.insertAll(
                Repository(1, "hello", "hello", "shiroyama"),
                Repository(2, "world", "world", "shiroyama"),
                Repository(3, "yay!", "yay!", "yamazaki"),
            )
        }

        @Test
        fun findByOwner_givenShiroyama_returnsSizeCount2() {
            val shiroyamaOwners = repositoryLocalDataSource.findByOwner("shiroyama")
            assertThat(shiroyamaOwners).hasSize(2)
        }

        @Test
        fun findByOwner_givenYamazaki_returnsSizeCount1() {
            val yamazakiOwners = repositoryLocalDataSource.findByOwner("yamazaki")
            assertThat(yamazakiOwners).hasSize(1)
        }
    }
}