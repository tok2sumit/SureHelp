package com.tok2sumit.surehelp.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.tok2sumit.surehelp.Database.EntityClass;
import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void insertAll(EntityClass entityClass);

    @Query("SELECT * FROM myTable")
    List<EntityClass> getAllData();

//    Delete
    @Query("delete from myTable where eventname = :eventname")
    void delete(String eventname);

}
