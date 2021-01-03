package caprice.bfcpago;

import android.util.Log;

public class destinatario
{

	public int banco = 0;
    public String telefono = "";
    public int pais = 0;
    public int cedula = 0;
    public int tipoCuenta = 0;
    public String nombre = "";

	//Constructor de la clase destinatario
	public destinatario(int b, String t, int p, int ci, int tp, String name)
	{
		banco = b;
		telefono = t;
		pais = p;
		cedula = ci;
		tipoCuenta = tp;
		nombre = name;
	}

	public void log_data(){
		Log.v("caprice.bfcpago","".format("banco= %d, pais= %d, cedula= %d, telefono= %s, tipoCuenta= %d, nombre= %s", banco, pais, cedula, telefono, tipoCuenta, nombre));
	}
}
