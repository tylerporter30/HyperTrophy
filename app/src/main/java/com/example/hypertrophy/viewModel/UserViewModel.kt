package com.example.hypertrophy.viewModel

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import kotlinx.coroutines.*
import kotlinx.coroutines.internal.synchronized

class UserViewModel(application: Application) {

    val allUsers: LiveData<List<User>>

    private val repository: UserRepository
    val searchResults: MutableLiveData<List<User>>

    init {
        val userDb = UserRoomDatabase.getInstance(application)
        val userDao = userDb.userDao()
        repository = UserRepository(userDao = userDao)

        allUsers = repository.allUsers
        searchResults = repository.searchResults
    }

    fun addUser(user: User) {
        repository.addUser(newuser = user)
    }
    fun findUser(username: String) {
        repository.findUser(username = username)
    }
}

@Entity(tableName = "users")
class User {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "userId")
    var id: Int = 0

    @ColumnInfo(name = "username")
    var username: String = ""

    @ColumnInfo(name = "password")
    var password: String = ""

    constructor() {}

    constructor(username: String, password: String) {
        this.username = username
        this.password = password
    }
}

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE username = :inputUsername")
    fun findUser(inputUsername: String) : List<User>

    @Insert
    fun addUser(user: User)

    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>
}

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAllUsers()
    val searchResults = MutableLiveData<List<User>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addUser(newuser: User) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.addUser(newuser)
        }
    }

    fun findUser(username: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(username).await()
        }
    }

    private fun asyncFind(username: String) : Deferred<List<User>?> = coroutineScope.async(Dispatchers.IO) {
        return@async userDao.findUser(username)
    }
}

@Database(entities = [(User::class)], version = 2)
abstract class UserRoomDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: UserRoomDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): UserRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserRoomDatabase::class.java,
                        "User_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

