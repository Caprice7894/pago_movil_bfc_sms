package caprice.bfcpago;

	import android.app.AlertDialog;
	import android.content.ContentValues;
	import android.content.Context;
	import android.content.DialogInterface;
	import android.database.Cursor;
	import android.database.SQLException;
	import android.database.sqlite.SQLiteDatabase;
	import android.util.Log;
	import android.widget.Toast;
	import java.util.ArrayList;
	import java.util.List;

public class destinatariodb
{
	static final String DB_NAME = "pagomovilbfc.db";
	static final int DB_VERS = 2;

	static final String DB_CREATE = "CREATE TABLE personas(ID integer primary key autoincrement, NOMBRE text, CI integer, NACIONALIDAD integer, TELEFONO text,  TIPO integer, BANCO integer);";

	public static SQLiteDatabase db;

	private final Context context;

	private static destinatarioHelperClass dbH;

	public destinatariodb(Context cntxt)
	{
		context = cntxt;
		dbH = new destinatarioHelperClass(context, DB_NAME, null, DB_VERS);
	}

	public destinatariodb open() throws SQLException
	{
		db = dbH.getWritableDatabase();
		return this;
	}

	public void close()
	{
		db.close();
	}

	public SQLiteDatabase getDb()
	{
		return db;
	}

	public boolean agregarDatos(String n, int ci, int nac, String tel, int tipo, int banco)
	{
        
                Log.v("caprice.bfcpago-destinatarioDB","Antes de guardar los datos");
		try{
			ContentValues newValues = new ContentValues();
			newValues.put("NOMBRE", n);
			newValues.put("CI", ci);
			newValues.put("NACIONALIDAD", nac);
			newValues.put("TELEFONO", tel);
			newValues.put("TIPO", tipo);
			newValues.put("BANCO", banco);

                Log.v("caprice.bfcpago-destinatarioDB","Antes de Escribir");
			db = dbH.getWritableDatabase();
			long res = db.insert("personas", null, newValues);

			
                Log.v("caprice.bfcpago-destinatarioDB","Luego de insertar");
			Toast.makeText(context, "Guardado: " + res, Toast.LENGTH_LONG).show();
			close();
			return true;
		}catch(Exception e)
		{
			Log.e("bfcpago.DB.agregarDatos", e.toString());
			return false;
		}
	}

    public Cursor leerLista()
    {
        Log.v("caprice.bfcpago-Leer","Llegamos hasta leer lista");
        db = dbH.getReadableDatabase();
        return db.query("personas",
                            null,
                            null,
                            null,
                            null,
                            null,
                            null);
    }
}











