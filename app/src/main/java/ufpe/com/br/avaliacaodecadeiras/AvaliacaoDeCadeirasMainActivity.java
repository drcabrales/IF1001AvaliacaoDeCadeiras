package ufpe.com.br.avaliacaodecadeiras;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.TextView;

import database.Database;
import objetoParse.ParseAluno;
import repositorioParse.RepositorioAlunoParse;


public class AvaliacaoDeCadeirasMainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, CadeirasFavoritasFragment.OnFragmentInteractionListener, VisualizarCadeiraFragment.OnFragmentInteractionListener, AvaliarCadeirasFragment.OnFragmentInteractionListener, ListaCadeiraFragment.OnFragmentInteractionListener, CadastrarCadeiraFragment.OnFragmentInteractionListener{

    /**
     *  Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ParseAluno alunoLogado;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cadeiras_favoritas);

            Bundle bundle = getIntent().getExtras();
            alunoLogado = (ParseAluno) bundle.getSerializable("alunoLogado");

        /*
            Intent intent = getIntent();
            Bundle params = intent.getExtras();
            this.alunoLogado = (ParseAluno) params.getSerializable("alunoLogado");
            int f = 5;
          */
        /*
            Bundle extras = getIntent().getExtras();
            alunoLogado = (ParseAluno) extras.getSerializable("alunoLogado");
            alunoLogado = (ParseAluno) getIntent().getSerializableExtra("aluno");
          */
        //ParseAluno aluno2 = (ParseAluno) getIntent().getExtras().getSerializable("alunoLogado");
            mNavigationDrawerFragment = (NavigationDrawerFragment)
                    getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
            mTitle = getTitle();

            // Set up the drawer.
            mNavigationDrawerFragment.setUp(
                    R.id.navigation_drawer,
                    (DrawerLayout) findViewById(R.id.drawer_layout));

        //setando para abrir com o listar
        String idAlunoLogado = getIntent().getStringExtra("chaveAlunoLogado");
        Database db = new Database(this);
        RepositorioAlunoParse repAluno = new RepositorioAlunoParse(db);
        alunoLogado = repAluno.getById(idAlunoLogado);

        final Bundle bundle2 = new Bundle();
        bundle2.putSerializable("aluno", alunoLogado);

        mTitle = "Cadeiras";
        FragmentManager fragmentManager = getSupportFragmentManager();
        ListaCadeiraFragment fragment = new ListaCadeiraFragment();
        fragment.setArguments(bundle2);
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();



    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
            if(position == 1){
                mTitle = "Favoritas";
                FragmentManager fragmentManager = getSupportFragmentManager();

                String idAlunoLogado = getIntent().getStringExtra("chaveAlunoLogado");

                Database db = new Database(this);
                RepositorioAlunoParse repAluno = new RepositorioAlunoParse(db);
                alunoLogado = repAluno.getById(idAlunoLogado);

                final Bundle bundle = new Bundle();
                bundle.putSerializable("aluno", alunoLogado);


                CadeirasFavoritasFragment fragment = new CadeirasFavoritasFragment();
                fragment.setArguments(bundle);

                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
            } else if(position == 0){

                String idAlunoLogado = getIntent().getStringExtra("chaveAlunoLogado");
                Database db = new Database(this);
                RepositorioAlunoParse repAluno = new RepositorioAlunoParse(db);
                alunoLogado = repAluno.getById(idAlunoLogado);

                final Bundle bundle = new Bundle();
                bundle.putSerializable("aluno", alunoLogado);

                mTitle = "Cadeiras";
                FragmentManager fragmentManager = getSupportFragmentManager();
                ListaCadeiraFragment fragment = new ListaCadeiraFragment();
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
            }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section2);
                break;
            case 2:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.cadeiras_favoritas, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
