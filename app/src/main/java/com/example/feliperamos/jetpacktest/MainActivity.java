package com.example.feliperamos.jetpacktest;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.feliperamos.jetpacktest.adapters.ItensAdapter;
import com.example.feliperamos.jetpacktest.model.Item;
import com.example.feliperamos.jetpacktest.viewModels.ItemViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;


public class MainActivity extends AppCompatActivity {
    private ItemViewModel itemViewModel;
    private TextView qtdRe;
    private Button bt_add;
    public static int CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ItensAdapter adapter = new ItensAdapter(this);

        recyclerView.setAdapter(adapter);
        qtdRe = findViewById(R.id.count_reg);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        itemViewModel.getAllItens().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                adapter.setItens(items);
            }
        });
        itemViewModel.getCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer qtd) {
                System.out.println(qtd);
                qtdRe.setText("Qunatidade de registros: " + qtd.toString());
            }
        });
        bt_add = findViewById(R.id.bt_add);
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createItem();
            }
        });
        adapter.setOnItemClickListener(new ItensAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                showDialogEdit(item);
            }
        });

    }

    private void showDialogEdit(Item item) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_detail);
        final int id = item.getId();
        final TextView text_id = dialog.findViewById(R.id.text_id_item);
        final EditText editText = (EditText) dialog.findViewById(R.id.input_nome);
        text_id.setText("Id do Item: " + item.getId().toString());
        editText.setText(item.getNome());
        Button button1 = (Button) dialog.findViewById(R.id.bt_dismiss);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Button button2 = (Button) dialog.findViewById(R.id.btn_edt);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeAtualizado = editText.getText().toString();
                updateItem(id, nomeAtualizado);
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private void updateItem(int id, String nome) {
        Item i = new Item();
        i.setId(id);
        i.setNome(nome);
        itemViewModel.update(i);
    }

    private void createItem() {
        Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
        startActivityForResult(intent, CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE && resultCode == RESULT_OK) {
            Item newItem = new Item();
            newItem.setNome(data.getStringExtra(AddItemActivity.EXTRA));
            itemViewModel.insert(newItem);
        }
    }


}
