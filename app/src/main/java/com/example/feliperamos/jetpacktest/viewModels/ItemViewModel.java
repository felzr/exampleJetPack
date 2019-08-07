package com.example.feliperamos.jetpacktest.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.feliperamos.jetpacktest.model.Item;
import com.example.feliperamos.jetpacktest.repository.ItemRepository;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {
    private ItemRepository itemRepository;
    private LiveData<List<Item>> itens;
    private LiveData<Integer> count;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        itemRepository = new ItemRepository(application);
        itens = itemRepository.getItens();
        count = itemRepository.getCount();
    }

    public LiveData<List<Item>> getAllItens() {
        return itens;
    }

    public void insert(Item item) {
        itemRepository.insert(item);
    }

    public void update(Item item) {
        itemRepository.update(item);
    }

    public LiveData<Integer> getCount() {
        return itemRepository.getCount();
    }
}
