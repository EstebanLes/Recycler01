package com.estebanles.proyecto14recycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        ArrayList<Persona> personas;
        RecyclerView rv1;
        EditText et1, et2;
        AdaptadorPersona ap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv1 = findViewById(R.id.rv1);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);

        personas = new ArrayList<Persona>();
        personas.add(new Persona("Esteban","3512275843"));
        personas.add(new Persona("Seba","3512275252"));
        personas.add(new Persona("Tomy","3512273333"));
        personas.add(new Persona("Mati","3512278888"));
        personas.add(new Persona("Alma","3512275959"));
        personas.add(new Persona("Mari","3512272424"));
        personas.add(new Persona("Bianca","3512271212"));

        LinearLayoutManager linear = new LinearLayoutManager(this);
        rv1.setLayoutManager(linear);
        ap = new AdaptadorPersona();
        rv1.setAdapter(ap);

    }

    public void ingresar (View v){
        Persona persona1 = new Persona(et1.getText().toString(), et2.getText().toString());
        personas.add(persona1);
        et1.setText("");
        et2.setText("");
        ap.notifyDataSetChanged(); //aca aviso que fue modificada la estructura de dato
        rv1.scrollToPosition(personas.size()-1); // el scrollposition hace que se desplase al ultimo y lo agrege ahi a la persona
    }

    public void eliminar (View v)
    {
        int pos = -1; // creo una variable auxilar para guardar el array
        for (int f = 0; f < personas.size(); f++) {
            if (personas.get(f).getNombre().equals(et1.getText().toString()))
                pos = f;
        }
            if (pos != -1) {  //si es distinto de -1 quiere decir que encontro lo que recorrio en el for y lo hago eliminar
                personas.remove(pos);
                et1.setText("");
                et2.setText("");
                ap.notifyDataSetChanged();
                Toast.makeText(this, "Se elimino la persona", Toast.LENGTH_SHORT).show();
        }
            else {
                Toast.makeText(this, "La persona no existe", Toast.LENGTH_SHORT).show();
            }
    }

    private void mostrar(int layoutPosition) {
        et1.setText(personas.get(layoutPosition).getNombre());
        et2.setText(personas.get(layoutPosition).getTelefono());
    }

    private class AdaptadorPersona extends RecyclerView.Adapter <AdaptadorPersona.AdaptadorLugaresHolder> {

        @NonNull
        @Override
        public AdaptadorLugaresHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdaptadorLugaresHolder(getLayoutInflater().inflate(R.layout.itempersona, parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainActivity.AdaptadorPersona.AdaptadorLugaresHolder holder, int position) {
            holder.print(position);
        }

        @Override
        public int getItemCount() {
            return personas.size();
        }

        public class AdaptadorLugaresHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView tv1,tv2;
            public AdaptadorLugaresHolder(@NonNull View itemView) {
                super(itemView);
                tv1=itemView.findViewById(R.id.tvnombre);
                tv2=itemView.findViewById(R.id.tvtelefono);
                itemView.setOnClickListener(this);
            }

            public void print(int position) {

                tv1.setText("Nombre: " +personas.get(position).getNombre());
                tv2.setText("Telefono: " +personas.get(position).getTelefono());
            }

            @Override
            public void onClick(View v) {
                mostrar(getLayoutPosition());
            }
        }

    }
}