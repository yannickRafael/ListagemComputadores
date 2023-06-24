package mz.ac.isutc.lecc31.yannick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import mz.ac.isutc.lecc31.yannick.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String[] p = {"celeron", "core i3", "core i5", "core i7", "core i9"};
    private static final String DB_NAME = "LISTA_DE_COMPUTADORES.DB";
    private static final int DB_VERSION = 1;

    public BaseDeDados db = new BaseDeDados(MainActivity.this, DB_NAME, null, DB_VERSION);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String >(this, android.R.layout.simple_spinner_item, p);
        binding.processador.setAdapter(adapterSpinner);

        Adapter adapterList = new Adapter(db.ler(), MainActivity.this);
        binding.list.setAdapter(adapterList);

        binding.list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Computador> computadores = db.ler();
                String s = computadores.get(position).getNrSerie();
                db.apagar(s);
                Adapter adapterList = new Adapter(db.ler(), MainActivity.this);
                binding.list.setAdapter(adapterList);
                return true;
            }
        });

        binding.gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    db.salvar(
                            binding.marca.getText()+"",
                            binding.modelo.getText()+"",
                            binding.nrSerie.getText()+"",
                            binding.processador.getSelectedItem()+"",
                            Integer.parseInt(binding.ram.getText()+""),
                            Integer.parseInt(binding.hd.getText()+"")
                    );
                    Adapter adapterList = new Adapter(db.ler(), MainActivity.this);
                    binding.list.setAdapter(adapterList);
                    binding.marca.setText(null);
                    binding.modelo.setText(null);
                    binding.nrSerie.setText(null);
                    binding.ram.setText(null);
                    binding.hd.setText(null);

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Preencha todos campos", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


}