package com.example.feliperamos.jetpacktest.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.feliperamos.jetpacktest.database.ItemDao;
import com.example.feliperamos.jetpacktest.database.ItemDatabase;
import com.example.feliperamos.jetpacktest.model.Item;

import java.util.List;

public class ItemRepository {
    private ItemDao dao;
    private LiveData<List<Item>> itensList;
    private String time;

    public ItemRepository(Application application) {
        ItemDatabase db = ItemDatabase.getDatabase(application);
        dao = db.itemDao();
        itensList = dao.getItens();
    }

    public LiveData<List<Item>> getItens() {
        return itensList;
    }

    public void insert(Item item) {
        new InsertAsyncTaskItem(dao).execute(item);
    }

    public void update(Item item) {
        new UpdateAsyncTaskItem(dao).execute(item);
    }

    public LiveData<Integer> getCount() {
        return dao.getCount();
    }

    private static class InsertAsyncTaskItem extends AsyncTask<Item, Void, Void> {

        private ItemDao newDao;

        InsertAsyncTaskItem(ItemDao dao) {
            newDao = dao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            newDao.insert(items[0]);
            return null;
        }
    }

    private static class UpdateAsyncTaskItem extends AsyncTask<Item, Void, Void> {

        private ItemDao newDao;

        UpdateAsyncTaskItem(ItemDao dao) {
            newDao = dao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            newDao.update(items[0]);
            return null;
        }
    }


}
