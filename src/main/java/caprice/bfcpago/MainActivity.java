package caprice.bfcpago;

//#region Imports
    import android.app.Activity;
    import android.os.Bundle;

    import android.content.Intent;
    import android.content.Context;

    import android.view.View;
    import android.view.View.OnClickListener;

    import android.widget.EditText;
    import android.widget.Spinner;
    import android.widget.Button;
    import android.widget.RadioGroup;
    import android.widget.Toast;
    import android.widget.ArrayAdapter;

    import android.widget.AdapterView;
    import android.widget.AdapterView.OnItemSelectedListener;

    import android.util.Log;
//#endregion

public class MainActivity extends Activity
        implements OnClickListener{

//#region Variables y Constantes a nivel de clase
    private final String BFC = "88232";
    private final String[] dts = {"V", "E", "J"};

    private struct_destinatario d;

    /* Codigos de para identificar los Intent */
    private final int REQUEST_CODE_AGG = 10;
    private final int REQUEST_CODE_LIST = 11;

    // Elementos del layout
    private EditText nombre;
    private EditText numero;
    private EditText monto;
    private EditText ci;

    private Spinner spci;
    private Spinner spBanco;

    private RadioGroup rg;
//#endregion

    /* Called when the activity is first created.*/
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        d = new struct_destinatario();

        Inicializar_componentes();

    }

    private void Inicializar_componentes(){

        // Asignamos los componentes del menu a las variables para poder
        // utilizar los mismos
        nombre = (EditText) findViewById(R.id.editNombre);
        numero = (EditText) findViewById(R.id.editNumero);
        monto = (EditText) findViewById(R.id.editMonto);
        ci = (EditText) findViewById(R.id.editCi);

        spci = (Spinner) findViewById(R.id.spci);
        ArrayAdapter<String> aaCi = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dts);
        spci.setAdapter(aaCi);


        spBanco = (Spinner) findViewById(R.id.spbanco);
        ArrayAdapter<CharSequence> aaBanco = ArrayAdapter.createFromResource(this, R.array.bancos, android.R.layout.simple_spinner_item);
        spBanco.setAdapter(aaBanco);

        rg = (RadioGroup) findViewById(R.id.RGb);

        rg.clearCheck();
        rg.check(R.id.RbPersona);

        Button enviar = (Button) findViewById(R.id.enviar);
        enviar.setOnClickListener(this);

        Button cancelar = (Button)findViewById(R.id.cancelar);
        cancelar.setOnClickListener(this);

        Button agregar = (Button)findViewById(R.id.agregar);
        agregar.setOnClickListener(this);

        Button botonLista = (Button) findViewById(R.id.lista);
        botonLista.setOnClickListener(this);

    }

    private void enviar(){
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);

        sendIntent.setType("vnd.android-dir/mms-sms");

        sendIntent.putExtra("address", BFC);

        sendIntent.putExtra("sms_body", msg());
        startActivity(sendIntent);
    }

    private String msg()
    {
        final String fmt = "%s %s %s %s %s%s";
        
        return String.format(fmt, d.getTipoCuenta(), d.getBanco(), d.getTelefono(), monto.getText(), d.getNacionalidad(), d.getCedula());
    }

    private void leer_input(){

        String tipoCuenta = "";
        switch(rg.getCheckedRadioButtonId()){
            case R.id.RbPersona:
                tipoCuenta = "PAT";
            break;

            case R.id.RbEmpresa:
                tipoCuenta = "PAC";
            break;
        }
        d.setNombre(nombre.getText().toString());
        d.setTelefono(numero.getText().toString());
        d.setCedula(ci.getText().toString());
        d.setBanco(
                ((String)spBanco.getItemAtPosition(
                    spBanco.getSelectedItemPosition()
                )).substring(0,4)
            );
        d.setNacionalidad((
                (String) spci.getItemAtPosition(
                    spci.getSelectedItemPosition()
                ))
            );
        d.setTipoCuenta(tipoCuenta);
    }

    @Override
    public void onClick(View v){

        switch(v.getId()){
            case R.id.enviar:
                leer_input();
                enviar();
            break;

            case R.id.cancelar:
                finish();
            break;

            case R.id.agregar:
                Intent agg = new Intent(this, agregarDestinatario.class);
                
                agg.putExtra("nombre", nombre.getText().toString());
                agg.putExtra("telefono", numero.getText().toString());
                agg.putExtra("cedula", ci.getText().toString());

                agg.putExtra("nacionalidad", spci.getSelectedItemPosition());

                agg.putExtra("tipoCuenta", rg.getCheckedRadioButtonId());
                
                agg.putExtra("banco", spBanco.getSelectedItemPosition());
                
                Log.v("caprice.bfcpago-mainActivity","Antes de iniciar AggActivity");

                startActivityForResult(agg, REQUEST_CODE_AGG);

            break;
        }

    }

}
//getSelectedItemPosition();