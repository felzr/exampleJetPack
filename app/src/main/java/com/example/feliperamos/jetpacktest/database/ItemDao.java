package com.example.feliperamos.jetpacktest.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.feliperamos.jetpacktest.model.Item;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * from itens")
    LiveData<List<Item>> getItens();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Item item);

    @Query("DELETE FROM itens WHERE id = :id")
    void deleteItem(Integer id);

    @Query("SELECT * FROM itens WHERE id = :id")
    List<Item> findById(Integer id);

    @Update
    void update(Item item);

    @Query("DELETE FROM itens")
    void deleteAll();

    @Query("SELECT COUNT(*) FROM itens")
    LiveData<Integer> getCount();
}
