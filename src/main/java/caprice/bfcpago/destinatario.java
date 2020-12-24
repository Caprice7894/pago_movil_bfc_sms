package caprice.bfcpago;

public class destinatario
{

	public String banco = "";
    public String telefono = "";
    public String pais = "";
    public String cedula = "";
    public boolean tipoCuenta = false;
    public String nombre = "";

	//Constructor de la clase destinatario
	public destinatario(String b, String t, String p, String ci, boolean tp, String name)
	{
		banco = b;
		telefono = t;
		pais = p;
		cedula = ci;
		tipoCuenta = tp;
		nombre = name;
	}

	public void setBanco(String b)
	{
		banco = b;
	}

	public void setTelefono(String t)
	{
		telefono = t;
	}

	public void setPais(String p)
	{
		pais = p;
	}

	public void setCedula(String ci)
	{
		cedula = ci;
	}

	public void setTipoCuenta(boolean tp)
	{
		tipoCuenta = tp;
	}

    public void setTipoCuenta(int tp)
    {
        tipoCuenta = (tp == 1)? true: false;
    }

	public void setNombre(String name)
	{
		nombre = name;
	}

	public String getBanco()
	{
		return banco;
	}

	public String getTelefono()
	{
		return telefono;
	}

	public String getPais()
	{
		return pais;
	}

	public String getCedula()
	{
		return cedula;
	}

	public String getTipoCuenta()
	{
		return (!tipoCuenta)?"PAT":"PAC";
	}

	public String getNombre()
	{
		return nombre;
	}

}
