package ufpe.com.br.avaliacaodecadeiras;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;

import java.util.ArrayList;

import database.Database;
import objeto.MetodoAvaliacaoCadeira;
import objetoParse.ParseAluno;
import objetoParse.ParseAvaliacao;
import objetoParse.ParseAvaliacaoCategoria;
import objetoParse.ParseAvaliacaoMetodo;
import objetoParse.ParseCadeira;
import objetoParse.ParseCategoriaAvaliacaoCadeira;
import objetoParse.ParseComentario;
import objetoParse.ParseMetodoAvaliacaoCadeira;
import repositorio.RepositorioAvaliacaoCategoria;
import repositorio.RepositorioAvaliacaoMetodo;
import repositorio.RepositorioCategoriaAvaliacaoCadeira;
import repositorioParse.RepositorioAvaliacaoCategoriaParse;
import repositorioParse.RepositorioAvaliacaoMetodoParse;
import repositorioParse.RepositorioCategoriaAvaliacaoCadeiraParse;
import repositorioParse.RepositorioMetodoAvalicaoCadeiraParse;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AvaliarCadeirasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AvaliarCadeirasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AvaliarCadeirasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int valueDificuldade = 0;
    private int valueSatisfacao = 0;
    private int valueAprendizado = 0;
    private boolean prova = false;
    private boolean projeto = false;
    private boolean seminario = false;
    private boolean elaboracaoDocumento = false;
    private boolean presenca = false;
    private boolean participacao = false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AvaliarCadeirasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AvaliarCadeirasFragment newInstance(String param1, String param2) {
        AvaliarCadeirasFragment fragment = new AvaliarCadeirasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AvaliarCadeirasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_avaliar_cadeiras, container, false);

        Bundle bundle = this.getArguments();
        final Bundle bundle2 = new Bundle();
        final ParseCadeira cadeiraSelecionada = (ParseCadeira) bundle.getSerializable("cadeira");
        final ParseAluno alunoLogado = (ParseAluno) bundle.getSerializable("aluno");

        TextView nomeCadeira = (TextView) rootView.findViewById(R.id.txtNomeCadeiraAvaliacao);
        TextView nomeProfessor = (TextView) rootView.findViewById(R.id.txtProfessorAvaliacao);

        final SeekBar seekDificuldade = (SeekBar) rootView.findViewById(R.id.seekBar);
        final SeekBar seekSatisfacao = (SeekBar) rootView.findViewById(R.id.seekBar2);
        final SeekBar seekAprendizado = (SeekBar) rootView.findViewById(R.id.seekBar3);
        final TextView valueSeekDificuldade = (TextView) rootView.findViewById(R.id.txtprogressdificuldade);
        final TextView valueSeekAprendizado = (TextView) rootView.findViewById(R.id.txtprogressaprendizado);
        final TextView valueSeekSatisfacao = (TextView) rootView.findViewById(R.id.txtprogresssatisfacao);

        final CheckBox chkProva = (CheckBox) rootView.findViewById(R.id.checkBox);
        final CheckBox chkProjeto = (CheckBox) rootView.findViewById(R.id.checkBox2);
        final CheckBox chkSeminario = (CheckBox) rootView.findViewById(R.id.checkBox3);
        final CheckBox chkElaboracaoDocumentos = (CheckBox) rootView.findViewById(R.id.checkBox4);
        final CheckBox chkPresenca = (CheckBox) rootView.findViewById(R.id.checkBox5);
        final CheckBox chkParticipacao = (CheckBox) rootView.findViewById(R.id.checkBox6);

        final EditText edtSemestre = (EditText) rootView.findViewById(R.id.edtSemestre);
        final EditText edtAno = (EditText) rootView.findViewById(R.id.edtAno);

        final EditText editComentarios = (EditText) rootView.findViewById(R.id.edtcomentarioAvaliacao);

        Button btnAvaliar = (Button) rootView.findViewById(R.id.btnsalvarAvaliacao);
        Button btnvoltaravAliacao = (Button) rootView.findViewById(R.id.btnvoltaravAliacao);

        seekDificuldade.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueSeekDificuldade.setText(progress + "");
                valueDificuldade = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekSatisfacao.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueSeekSatisfacao.setText(progress + "");
                valueSatisfacao = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekAprendizado.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueSeekAprendizado.setText(progress + "");
                valueAprendizado = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final Database db = new Database(this.getActivity());
        final Toast msgSucesso = Toast.makeText(this.getActivity(), "Avaliação salva com sucesso!", Toast.LENGTH_SHORT);
        btnAvaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projeto = chkProjeto.isChecked();
                prova = chkProva.isChecked();
                seminario = chkSeminario.isChecked();
                elaboracaoDocumento = chkElaboracaoDocumentos.isChecked();
                participacao = chkParticipacao.isChecked();
                presenca = chkPresenca.isChecked();



                ParseAvaliacao novaAvaliacao = new ParseAvaliacao(alunoLogado, cadeiraSelecionada);
                novaAvaliacao.saveInBackground();

                RepositorioMetodoAvalicaoCadeiraParse repMetodo = new RepositorioMetodoAvalicaoCadeiraParse(db);
                ArrayList<ParseObject> metodos = repMetodo.getAll();

                RepositorioCategoriaAvaliacaoCadeiraParse repCategoria = new RepositorioCategoriaAvaliacaoCadeiraParse(db);
                ArrayList<ParseObject> categoria = repCategoria.getAll();

                RepositorioAvaliacaoMetodoParse repAvMetodo = new RepositorioAvaliacaoMetodoParse(db);
                RepositorioAvaliacaoCategoriaParse repAvCategoria = new RepositorioAvaliacaoCategoriaParse(db);

                //adicionando os metodos de avaliação
                for (int i = 0; i < metodos.size(); i++) {
                    ParseMetodoAvaliacaoCadeira metodoaux = (ParseMetodoAvaliacaoCadeira) metodos.get(i);

                    if(prova){
                       if(metodoaux.getNome().equals("Prova")){
                            ParseAvaliacaoMetodo auxMet = new ParseAvaliacaoMetodo(novaAvaliacao, metodoaux);
                            repAvMetodo.insert(auxMet);
                       }
                    }

                    if(projeto){
                        if(metodoaux.getNome().equals("Projeto")){
                            ParseAvaliacaoMetodo auxMet = new ParseAvaliacaoMetodo(novaAvaliacao, metodoaux);
                            repAvMetodo.insert(auxMet);
                        }
                    }

                    if(seminario){
                        if(metodoaux.getNome().equals("Seminário")){
                            ParseAvaliacaoMetodo auxMet = new ParseAvaliacaoMetodo(novaAvaliacao, metodoaux);
                            repAvMetodo.insert(auxMet);
                        }
                    }

                    if(elaboracaoDocumento){
                        if(metodoaux.getNome().equals("Elaboração de documentos")){
                            ParseAvaliacaoMetodo auxMet = new ParseAvaliacaoMetodo(novaAvaliacao, metodoaux);
                            repAvMetodo.insert(auxMet);
                        }
                    }

                    if(participacao){
                        if(metodoaux.getNome().equals("Participação em aulas")){
                            ParseAvaliacaoMetodo auxMet = new ParseAvaliacaoMetodo(novaAvaliacao, metodoaux);
                            repAvMetodo.insert(auxMet);
                        }
                    }

                    if(presenca){
                        if(metodoaux.getNome().equals("Presença em aulas")){
                            ParseAvaliacaoMetodo auxMet = new ParseAvaliacaoMetodo(novaAvaliacao, metodoaux);
                            repAvMetodo.insert(auxMet);
                        }
                    }
                }

                //adicionando as categorias avaliadas
                for (int i = 0; i < categoria.size(); i++) {
                    ParseCategoriaAvaliacaoCadeira auxCat = (ParseCategoriaAvaliacaoCadeira) categoria.get(i);

                    if(auxCat.getNome().equals("Dificuldade")){
                        ParseAvaliacaoCategoria avCateg = new ParseAvaliacaoCategoria(novaAvaliacao, auxCat, valueDificuldade);
                        repAvCategoria.insert(avCateg);
                    }

                    if(auxCat.getNome().equals("Satisfação")){
                        ParseAvaliacaoCategoria avCateg = new ParseAvaliacaoCategoria(novaAvaliacao, auxCat, valueSatisfacao);
                        repAvCategoria.insert(avCateg);
                    }

                    if(auxCat.getNome().equals("Aprendizado")){
                        ParseAvaliacaoCategoria avCateg = new ParseAvaliacaoCategoria(novaAvaliacao, auxCat, valueAprendizado);
                        repAvCategoria.insert(avCateg);
                    }
                }

                //adicionando comentario
                String comentarioString = editComentarios.getText().toString();
                String semestre = edtSemestre.getText().toString();
                String ano = edtAno.getText().toString();
                ParseComentario comentario = new ParseComentario(novaAvaliacao, ano, semestre, comentarioString);
                comentario.saveInBackground();

                //limpando os campos
                projeto = false;
                prova = false;
                seminario = false;
                elaboracaoDocumento = false;
                participacao = false;
                presenca = false;

                edtSemestre.setText("");
                edtAno.setText("");
                editComentarios.setText("");

                valueSeekAprendizado.setText("0");
                valueSeekDificuldade.setText("0");
                valueSeekSatisfacao.setText("0");

                seekAprendizado.setProgress(0);
                seekDificuldade.setProgress(0);
                seekSatisfacao.setProgress(0);

                //avisando que foi cadastrado com sucesso
                msgSucesso.show();


            }
        });


        btnvoltaravAliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle2.putSerializable("aluno",alunoLogado);
                bundle2.putSerializable("cadeira",cadeiraSelecionada);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                VisualizarCadeiraFragment fragment = new VisualizarCadeiraFragment();
                fragment.setArguments(bundle2);
                transaction
                        .replace(R.id.container, fragment)
                        .commit();
            }
        });


        nomeCadeira.setText(cadeiraSelecionada.getString("nome"));
        nomeProfessor.setText(cadeiraSelecionada.getString("nomeProfessor"));

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
