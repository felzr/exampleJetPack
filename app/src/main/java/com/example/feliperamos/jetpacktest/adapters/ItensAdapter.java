package com.example.feliperamos.jetpacktest.adapters;


/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.feliperamos.jetpacktest.AddItemActivity;
import com.example.feliperamos.jetpacktest.MainActivity;
import com.example.feliperamos.jetpacktest.R;
import com.example.feliperamos.jetpacktest.model.Item;

import java.util.Collections;
import java.util.List;


public class ItensAdapter extends RecyclerView.Adapter<ItensAdapter.ItemViewHolder> {
    private Context context;
    private OnItemClickListener listener;

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        private ItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.texto);
        }
    }

    private final LayoutInflater mInflater;
    private List<Item> itensList = Collections.emptyList(); // Cached copy of words

    public ItensAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.content_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final Item current = itensList.get(position);
        holder.textView.setText(current.getNome());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(current);
            }
        });
    }

    public void setItens(List<Item> itens) {
        itensList = itens;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itensList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}



