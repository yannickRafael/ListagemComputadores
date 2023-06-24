package mz.ac.isutc.lecc31.yannick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Adapter extends BaseAdapter {
    private ArrayList<Computador> computadores;
    private Context context;
    private LayoutInflater inflater;

    public Adapter(ArrayList<Computador> computadores, Context context) {
        this.computadores = computadores;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return computadores.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item, null);
        Computador c = computadores.get(position);
        TextView marca_modelo, id, nr, processador, ram_hd;

        marca_modelo = convertView.findViewById(R.id.item_marca_modelo);
        id= convertView.findViewById(R.id.item_id);
        nr = convertView.findViewById(R.id.item_serie);
        processador = convertView.findViewById(R.id.item_processador);
        ram_hd = convertView.findViewById(R.id.item_ram_hd);

        marca_modelo.setText(c.getMarca()+": "+c.getModelo());
        id.setText("id: "+c.getId());
        nr.setText("Nr.Serie: "+c.getNrSerie());
        processador.setText(c.getProcessador()+"");
        ram_hd.setText(c.getRam()+"RAM - "+c.getHd()+"HD");

        return convertView;
    }
}
