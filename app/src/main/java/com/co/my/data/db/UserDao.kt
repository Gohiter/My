package com.co.my.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.co.my.data.db.entities.CURRENT_USER_ID
import com.co.my.data.db.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User) : Long

    @Query("SELECT * FROM user Where uid = $CURRENT_USER_ID")
    fun getuser() : LiveData<User>

}