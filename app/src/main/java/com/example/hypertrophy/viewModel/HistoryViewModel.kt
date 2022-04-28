package com.example.hypertrophy.viewModel

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hypertrophy.history.ExerciseData
import com.example.hypertrophy.history.HistoryCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) {

    val allHistory: LiveData<List<History>>

    private val repository: HistoryRepository
    //val searchResults: MutableLiveData<List<User>>

    init {
        val historyDb = HistoryRoomDatabase.getInstance(application)
        val historyDao = historyDb.historyDao()
        repository = HistoryRepository(historyDao = historyDao)

        allHistory = repository.allHistory
        //searchResults = repository.searchResults
    }

    fun addHistory(history: History) {
        repository.addHistory(newHistory = history)
    }
    /*fun findHistory(username: String) {
        repository.findUser(username = username)
    }*/
}

@Entity(tableName = "history")
class History {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "userId")
    var id: Int = 0

    @ColumnInfo(name = "history")
    var history: String = ""
    //var history: HistoryCard = HistoryCard(workoutTemplate = "", date = "", exercises = arrayListOf(ExerciseData(exercisename = "", setsAndReps = arrayListOf(""))))



    //constructor() {}

    constructor(history:
                String//HistoryCard
    ) {
        this.history = history
    }
}

@Dao
interface HistoryDao {
    /*@Query("SELECT * FROM users WHERE history = :history")
    fun findUser(inputUsername: String) : List<User>*/

    @Insert
    fun addHistory(history: History)

    @Query("SELECT * FROM history")
    fun getAllHistory(): LiveData<List<History>>
}

class HistoryRepository(private val historyDao: HistoryDao) {

    val allHistory: LiveData<List<History>> = historyDao.getAllHistory()
    //val searchResults = MutableLiveData<List<User>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addHistory(newHistory: History) {
        coroutineScope.launch(Dispatchers.IO) {
            historyDao.addHistory(newHistory)
        }
    }

    /*fun findUser(username: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(username).await()
        }
    }*/

    /*private fun asyncFind(history: String) : Deferred<List<History>?> = coroutineScope.async(
        Dispatchers.IO) {
        return@async historyDao.findUser(username)
    }*/
}

@Database(entities = [(History::class)], version = 2)
abstract class HistoryRoomDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        private var INSTANCE: HistoryRoomDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): HistoryRoomDatabase {
            kotlinx.coroutines.internal.synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryRoomDatabase::class.java,
                        "History_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}