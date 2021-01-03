package caprice.bfcpago;

import android.app.Activity;
import android.os.Bundle;

import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Context;
import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;

import android.database.Cursor;

import android.util.Log;

public class listarDestinatarios extends Activity
{
    Context c;
	@Override
	protected void onCreate(Bundle savedInstance)
	{
		super.onCreate(savedInstance);
        c = this;
		setContentView(R.layout.destinatariosagregados);

        LinearLayout ll = (LinearLayout)findViewById(R.id.listaLayout);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

        destinatariodb db = new destinatariodb(this);
        Cursor reg = db.leerLista();
        Log.v("caprice.bfcpago-listarDestinatario","Llegamos hasta despues de leer lista");
        if(reg.moveToFirst()){
            do
            {
                Log.v("caprice.bfcpago-listarDestinatario","bucle");
                final destinatario d = new destinatario(reg.getInt(6), //banco
                reg.getString(4), //telefono
                reg.getInt(3), //nacionalidad
                reg.getInt(2), //cedula
                reg.getInt(5), //tipo
                reg.getString(1)); // nombre
		d.log_data();
                Log.v("caprice.bfcpago-listarDestinatario","Llegamos hasta despues crear d");
                Button b = new Button(this);
                b.setLayoutParams(lp);
                b.setText(String.format("%s\n%s",d.nombre,d.telefono));
                b.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            
                        Intent i = new Intent();
                        i.putExtra("nombre", d.nombre);
                        i.putExtra("telefono",d.telefono);
                        i.putExtra("cedula",d.cedula);
                        i.putExtra("pais",d.pais);
                        i.putExtra("banco",d.banco);
                        i.putExtra("tipoCuenta",d.tipoCuenta);
                        setResult(1,i);
                        finish();
                        }
                    });
                
                ll.addView(b);
            }while(reg.moveToNext());
        }
        Button b = new Button(this);
            b.setLayoutParams(lp);
            b.setText("Salir");
            b.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent i = new Intent();
                        setResult(-1,i);
                        finish();
                    }
                });
            ll.addView(b);
	}
}
