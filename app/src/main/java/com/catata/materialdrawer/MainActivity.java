package com.catata.materialdrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {
    private Drawer mDrawer;
    Toolbar tbMiToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Muestra la barra back en el ActionBar
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tbMiToolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(tbMiToolBar); //Hace de actionBar

        /*Con esto sólo ya se debe ver el menú, vacío... */
        new DrawerBuilder().withActivity(this).build();

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.mipmap.ic_launcher)
                .addProfiles(
                        new ProfileDrawerItem()
                            .withName("Ejemplo MaterialDrawer")
                            .withEmail("micorreo@iesserraperenxisa.com")
                            .withIcon(getResources().getDrawable(R.mipmap.ic_launcher_round, getTheme()))
                )
                .build();

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withActionBarDrawerToggle(true) //Muestra o no el icono de hamburguesa
                .withToolbar(tbMiToolBar) //Lo asocia a nuestra toolbar
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.END) //Lo pone a la derecha (La hamburguesa sigue a la izquierda)
                .withAccountHeader(headerResult)
                .withSelectedItem(2)
                .withSliderBackgroundColor(getResources().getColor(R.color.accent)) //Color de fondo
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withIdentifier(1)
                                .withName("Opción 1")
                                .withIcon(android.R.drawable.btn_star_big_on),
                        new PrimaryDrawerItem()
                                .withIdentifier(2)
                                .withName("Opción 2"),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem()
                                .withIdentifier(3)
                                .withName("Cerrar menú"),
                        new SecondaryDrawerItem()
                                .withIdentifier(4)
                                .withName("Salir App")
                )
                .withOnDrawerItemClickListener(
                        new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                switch ((int)drawerItem.getIdentifier()) {
                                    case 1: {
                                        Toast.makeText(MainActivity.this, "Opción 1 pulsada", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    case 2: {
                                        Toast.makeText(MainActivity.this, "Opción 2 pulsada", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    case 3: break;
                                    case 4: {
                                        finish();
                                        break;
                                    }
                                }

                                mDrawer.closeDrawer();

                                return true;
                            }
                        })
                .build();



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(MainActivity.this, "BackPressed", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}