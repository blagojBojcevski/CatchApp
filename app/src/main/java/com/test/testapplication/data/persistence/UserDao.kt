package com.test.testapplication.data.persistence

import androidx.room.*
import com.test.testapplication.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertUser(user: User)

  @Query("SELECT * FROM User WHERE id = :id_")
  suspend fun getMovieById(id_: Long): User

  @Query("SELECT * FROM User")
  suspend fun getUser(): User

//  @Query("UPDATE movie SET favorite=:favorite WHERE id = :id")
//  suspend fun updateMovie(favorite: Boolean, id: Long)
}
