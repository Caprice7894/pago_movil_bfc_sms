package caprice.bfcpago;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import android.content.Intent;
import android.content.Context;

import android.util.Log;

public class agregarDestinatario extends Activity implements OnClickListener
{
	@Override
	public void onCreate(Bundle savedInstance)
	{
		super.onCreate(savedInstance);
		setContentView(R.layout.agregardestinatario);

		Button cancelar = (Button)findViewById(R.id.salirdest);
			cancelar.setOnClickListener(this);

		TextView text = (TextView)findViewById(R.id.agg);

		Bundle rp = getIntent().getExtras();
        
		if(agregar(rp))
		{
            
                Log.v("caprice.bfcpago-aggDest","Luego de aggreagar");
			text.setText("Ha sido agregado correctamente\n" + rp.getString("nombre") + " " + rp.getString("telefono"));
		}else
		{
			text.setText("Algo ha fallado");
		}
	}

	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.salirdest:
				Intent i = new Intent();

				i.putExtra("result", "Finalizado");
				setResult(1, i);
				finish();
			break;
		}
	}

	private boolean agregar(Bundle b)
	{
		destinatariodb db = new destinatariodb(this);
        
                Log.v("caprice.bfcpago-mainActivity","Antes de ir a db");
		return db.agregarDatos(b.getString("nombre"), b.getInt("cedula"), b.getInt("nacionalidad"), b.getString("telefono"), b.getInt("tipoCuenta"), b.getInt("banco"));
	}
}
