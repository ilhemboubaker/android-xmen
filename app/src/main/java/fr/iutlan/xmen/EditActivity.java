package fr.iutlan.xmen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import fr.iutlan.xmen.databinding.ActivityEditBinding;

public class EditActivity extends AppCompatActivity {
    private ActivityEditBinding ui;
    private List<XMen> liste;
    public static final String EXTRA_POSITION = "position";
    private XMen xmen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        // obtenir la liste
        XMenApplication application = (XMenApplication) getApplication();
        liste = application.getListe();
        Intent intent = getIntent();
        int position = intent.getIntExtra(EXTRA_POSITION, -1);
        if (position != -1) {
            xmen = liste.get(position);
            setXMen(xmen);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // ajouter mes items de menu
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        // ajouter les items du système s'il y en a
        return super.onCreateOptionsMenu(menu);
    }

    /*
     * créer une nouvelle instance de XMen et la remplir avec les EditText de
     * l'interface
     */

    public void onAccept(MenuItem item) {
        if (xmen == null ) {
            xmen = new XMen();
            liste.add(xmen);
        }
        xmen.setNom(ui.nom.getText().toString());
        xmen.setAlias(ui.alias.getText().toString());
        xmen.setPouvoirs(ui.pouvoirs.getText().toString());
        xmen.setDescription(ui.description.getText().toString());
        // TODO ajouter ce xmen dans la liste
        // terminer l'activité en indiquant un succès
        setResult(RESULT_OK);
        finish();
    }

    private void setXMen(XMen xmen){
        ui.nom.setText(xmen.getNom());
        ui.alias.setText(xmen.getAlias());
        ui.pouvoirs.setText(xmen.getPouvoirs());
        ui.description.setText(xmen.getDescription());
    }

}