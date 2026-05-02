package com.example.lab19_roommvvmdemo.display;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab19_roommvvmdemo.R;
import com.example.lab19_roommvvmdemo.logic.MemoViewModel;
import com.example.lab19_roommvvmdemo.storage.local.Memo;

public class MainActivity extends AppCompatActivity {

    private MemoViewModel vm;
    private EditText inputHeading, inputBody;
    private MemoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputHeading = findViewById(R.id.inputHeading);
        inputBody = findViewById(R.id.inputBody);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnWipe = findViewById(R.id.btnWipe);
        RecyclerView rv = findViewById(R.id.rvMemos);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        adapter = new MemoAdapter();
        rv.setAdapter(adapter);

        vm = new ViewModelProvider(this).get(MemoViewModel.class);
        vm.getMemoList().observe(this, list -> adapter.refreshList(list));

        btnSave.setOnClickListener(v -> pushMemo());

        btnWipe.setOnClickListener(v -> {
            vm.eraseAll();
            Toast.makeText(this, "Tout effacé", Toast.LENGTH_SHORT).show();
        });

        adapter.setHoldListener(memo -> {
            vm.eraseMemo(memo);
            Toast.makeText(this, "Supprimé", Toast.LENGTH_SHORT).show();
        });

        adapter.setTapListener(memo ->
                Toast.makeText(this, memo.getHeading(), Toast.LENGTH_SHORT).show()
        );
    }

    private void pushMemo() {
        String h = inputHeading.getText().toString().trim();
        String b = inputBody.getText().toString().trim();
        if (h.isEmpty() || b.isEmpty()) {
            Toast.makeText(this, "Remplis les deux champs", Toast.LENGTH_SHORT).show();
            return;
        }
        vm.saveMemo(new Memo(h, b));
        inputHeading.setText("");
        inputBody.setText("");
        Toast.makeText(this, "Mémo sauvegardé ✓", Toast.LENGTH_SHORT).show();
    }
}