package com.example.restaurante;

import android.widget.Switch;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Switch selecao;
    private EditText nomeEditText;
    private Button encerrar;
    private Spinner spinner;
    private TextView quantidadeTextView, valorTextView;
    private int quantidade = 0;
    private HashMap<String, Integer> precos = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        quantidadeTextView = findViewById(R.id.quantidade);
        valorTextView = findViewById(R.id.valor);

        String[] itens = {"Hambúrguer", "Pizza", "Batata Frita","xSalada", "Bacon",};
        precos.put("Hambúrguer", 20);
        precos.put("Pizza", 35);
        precos.put("Batata Frita", 15);
        precos.put("xSalada", 20);
        precos.put("Bacon", 14);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itens);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        FloatingActionButton aumentar = findViewById(R.id.aumentar);
        aumentar.setOnClickListener(v -> {
            if (quantidade <10)
            quantidade++;
            atualizarValor();
        });

        FloatingActionButton diminuir = findViewById(R.id.diminuir);
        diminuir.setOnClickListener(v -> {
            if (quantidade > 1) {
                quantidade--;
                atualizarValor();
            }
        });

        spinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                quantidade = 1;
                atualizarValor();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });
    }

    private void atualizarValor() {
        String itemSelecionado = spinner.getSelectedItem().toString();
        int precoUnitario = precos.get(itemSelecionado);
        int total = quantidade * precoUnitario;

        quantidadeTextView.setText(String.valueOf(quantidade));
        valorTextView.setText("Total: R$ " + total);


    nomeEditText = findViewById(R.id.nome);
    encerrar = findViewById(R.id.encerrar);
    encerrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String nome = nomeEditText.getText().toString().trim();
            if (nome.isEmpty()){
                nomeEditText.setError("Preencha com nome e sobrenome ");
            }
            else {
                Toast.makeText(MainActivity.this, "Pedido Realizado Com Sucesso ", Toast.LENGTH_SHORT).show();
            }
        }
    });

    }
}



