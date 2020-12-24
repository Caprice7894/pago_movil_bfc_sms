package caprice.bfcpago;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class destinatarioHelperClass extends SQLiteOpenHelper
{
	public destinatarioHelperClass(Context context, String name,
								CursorFactory factory, int version)
	{
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase _db)
	{
		try
		{
			_db.execSQL(destinatariodb.DB_CREATE);
		}catch(Exception e)
		{
			Log.e("caprice.bfc", e.toString());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase _db,
					int _oldVers, int _newVers)
	{
		try{
			Log.w("TaskDBAdapter", "Upgrading from version " + _oldVers
			+ " to " + _newVers + ", wich will destroy all old data");

			_db.execSQL("DROP TABLE IF EXIST " + "personas");

			onCreate(_db);
		}catch(Exception e)
		{
			Log.e("caprice.bfc", e.toString());
		}
	}
}








