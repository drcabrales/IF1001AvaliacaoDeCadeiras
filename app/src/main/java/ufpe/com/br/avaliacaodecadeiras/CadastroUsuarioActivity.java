package ufpe.com.br.avaliacaodecadeiras;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;

import java.util.ArrayList;

import database.Database;
import objetoParse.ParseAluno;
import objetoParse.ParseCurso;
import objetoParse.ParseFaculdade;
import repositorio.RepositorioCurso;
import repositorio.RepositorioFaculdade;
import repositorioParse.RepositorioAlunoParse;
import repositorioParse.RepositorioCursoParse;
import repositorioParse.RepositorioFaculdadeParse;


public class CadastroUsuarioActivity extends Activity {
    private String siglaSelecionada = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        final Database db = new Database(this);
        final RepositorioFaculdadeParse repFaculdade = new RepositorioFaculdadeParse(db);

        final EditText nomeUsuario = (EditText) findViewById(R.id.edtNomeUsuario);
        final EditText emailUsuario = (EditText) findViewById(R.id.edtEmailUsuario);
        final EditText senhaUsuario = (EditText) findViewById(R.id.edtSenhaUsuario);
        final AutoCompleteTextView autoCompleteSiglaFaculdade = (AutoCompleteTextView) findViewById(R.id.autoCompleteSiglaFaculdadeUsuario);
        final EditText nomeFaculdade = (EditText) findViewById(R.id.edtNomeFaculdade);
        final AutoCompleteTextView autoCompleteNomeCurso = (AutoCompleteTextView) findViewById(R.id.autoCompleteCursoUsuario);
        Button btnCadastrar = (Button) findViewById(R.id.btnCadastrarUsuario);

        //Setando autocomplete de siglas
        final ArrayList<ParseObject> listaFaculdades = repFaculdade.getAll();
        ArrayList<String> nomeFaculdades = new ArrayList<String>();
        for (int i = 0; i < listaFaculdades.size(); i++) {
            nomeFaculdades.add(((ParseFaculdade)listaFaculdades.get(i)).getSigla());
        }

        ArrayAdapter<String> adapterAutoCompleteSiglaFaculdade = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nomeFaculdades);
        autoCompleteSiglaFaculdade.setAdapter(adapterAutoCompleteSiglaFaculdade);

        final Activity activityPrincipal = this;

        autoCompleteSiglaFaculdade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                siglaSelecionada = (String) parent.getAdapter().getItem(position);

                ParseFaculdade faculdadeSelecionada = new ParseFaculdade();
                if(!siglaSelecionada.equals("")){
                    faculdadeSelecionada = repFaculdade.get(siglaSelecionada);
                    nomeFaculdade.setText(faculdadeSelecionada.getNome());
                }

                //setando o autocomplete de cursos
                RepositorioCursoParse repCurso = new RepositorioCursoParse(db);
                ArrayList<ParseObject> listaCursos = repCurso.getByFaculdade(faculdadeSelecionada);
                ArrayList<String> nomeCursos = new ArrayList<String>();
                for (int i = 0; i < listaCursos.size(); i++) {
                    nomeCursos.add(((ParseCurso)listaCursos.get(i)).getNome());
                }

                ArrayAdapter<String> adapterAutoCompleteCurso = new ArrayAdapter<String>(activityPrincipal,android.R.layout.simple_list_item_1,nomeCursos);
                autoCompleteNomeCurso.setAdapter(adapterAutoCompleteCurso);
            }
        });


        //cadastrando o usuario
        final RepositorioAlunoParse repAluno = new RepositorioAlunoParse(db);
        final RepositorioCursoParse repCurso = new RepositorioCursoParse(db);
        final RepositorioFaculdadeParse repFaculdadeP = new RepositorioFaculdadeParse(db);


        final Intent intent = new Intent(this, LoginActivity.class);
        final Toast toastCadastroOk = Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastCadastroOk.show();
                ParseAluno alunoACadastrar;
                ParseFaculdade auxF = repFaculdadeP.get(siglaSelecionada);
                if(!auxF.getNome().equals("")){ //se existe essa faculdade
                    ParseCurso aux = repCurso.get(autoCompleteNomeCurso.getText().toString(), auxF);
                    alunoACadastrar = new ParseAluno(nomeUsuario.getText().toString(), emailUsuario.getText().toString(), senhaUsuario.getText().toString(), aux);
                }else{ //senao
                    auxF = new ParseFaculdade(nomeFaculdade.getText().toString(),autoCompleteSiglaFaculdade.getText().toString());
                    auxF.saveInBackground();
                    auxF = repFaculdadeP.get(autoCompleteSiglaFaculdade.getText().toString());
                    ParseCurso aux = new ParseCurso(autoCompleteNomeCurso.getText().toString(), "", auxF);
                    aux.saveInBackground();
                    aux = repCurso.get(autoCompleteNomeCurso.getText().toString(), auxF);
                    alunoACadastrar = new ParseAluno(nomeUsuario.getText().toString(), emailUsuario.getText().toString(), senhaUsuario.getText().toString(), aux);
                }


                repAluno.insert(alunoACadastrar);
                startActivity(intent);
            }
        });

    }
}
