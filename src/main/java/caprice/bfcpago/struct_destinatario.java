package caprice.bfcpago;

public class struct_destinatario{
    private String banco = "";
    private String telefono = "";
    private char nacionalidad = ' ';
    private String cedula = "";
    private String tipoCuenta = "";

    private String nombre = "";

    public struct_destinatario(){
        // Constructor vacio
    }

	public void setBanco(String b)
	{
		banco = b;
	}

	public void setTelefono(String t)
	{
		telefono = t;
	}

	public void setNacionalidad(String n)
	{
		nacionalidad = n.charAt(0);
	}

	public void setCedula(String ci)
	{
		cedula = ci;
	}

	public void setTipoCuenta(String tp)
	{
		tipoCuenta = tp;
	}

    public void setTipoCuenta(int tp)
    {
        tipoCuenta = (tp == 1)? "PAT": "PAC";
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

	public char getNacionalidad()
	{
		return nacionalidad;
	}

	public String getCedula()
	{
		return cedula;
	}

	public String getTipoCuenta()
	{
		return tipoCuenta;
	}

	public String getNombre()
	{
		return nombre;
	}
}