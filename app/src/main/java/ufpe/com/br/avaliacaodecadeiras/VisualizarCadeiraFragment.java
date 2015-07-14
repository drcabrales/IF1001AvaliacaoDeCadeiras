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
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;

import adapter.ListaCategoriaAvaliacaoAdapter;
import adapter.ListaComentarioAdapter;
import database.Database;
import objetoParse.ParseAluno;
import objetoParse.ParseAvaliacao;
import objetoParse.ParseCadeira;
import objetoParse.ParseCadeiraFavorita;
import objetoParse.ParseCategoriaAvaliacaoCadeira;
import objetoParse.ParseComentario;
import objetoParse.ParseMetodoAvaliacaoCadeira;
import repositorioParse.RepositorioAvaliacaoMetodoParse;
import repositorioParse.RepositorioAvaliacaoParse;
import repositorioParse.RepositorioCadeiraFavoritaParse;
import repositorioParse.RepositorioCategoriaAvaliacaoCadeiraParse;
import repositorioParse.RepositorioComentarioParse;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VisualizarCadeiraFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VisualizarCadeiraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VisualizarCadeiraFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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
     * @return A new instance of fragment VisualizarCadeiraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VisualizarCadeiraFragment newInstance(String param1, String param2) {
        VisualizarCadeiraFragment fragment = new VisualizarCadeiraFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public VisualizarCadeiraFragment() {
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

        View rootView = inflater.inflate(R.layout.fragment_visualizar_cadeira, container, false);
        Bundle bundle = this.getArguments();
        final ParseCadeira cadeiraSelecionada = (ParseCadeira) bundle.getSerializable("cadeira");
        final ParseAluno alunoLogado = (ParseAluno) bundle.getSerializable("aluno");

        TextView nomeCadeira = (TextView) rootView.findViewById(R.id.txtNomeCadeira);
        TextView nomeProfessor = (TextView) rootView.findViewById(R.id.txtProfessor);
        ListView categoriaAvaliacao = (ListView) rootView.findViewById(R.id.listCategoriaavaliacao);
        TextView qtdeAvaliacoes = (TextView) rootView.findViewById(R.id.txtQtdeAvaliacoes);
        ListView comentarios = (ListView) rootView.findViewById(R.id.listcomentarios);

        final CheckBox chkFavoritar = (CheckBox) rootView.findViewById(R.id.favorite);

        nomeCadeira.setText(cadeiraSelecionada.getString("nome"));
        nomeProfessor.setText(cadeiraSelecionada.getString("nomeProfessor"));

        Button avaliar1 = (Button) rootView.findViewById(R.id.btnavaliar);
        Button avaliar2 = (Button) rootView.findViewById(R.id.btnavaliar2);

        //setando adapter da lista de categoria avaliação, passando a cadeira corrente
        Database db = new Database(this.getActivity());
        RepositorioAvaliacaoParse repAvaliacao = new RepositorioAvaliacaoParse(db);
        RepositorioCategoriaAvaliacaoCadeiraParse repCategorias = new RepositorioCategoriaAvaliacaoCadeiraParse(db);
        ArrayList<ParseObject> listaCategorias = repCategorias.getAll();
        ListaCategoriaAvaliacaoAdapter adapter = new ListaCategoriaAvaliacaoAdapter(this.getActivity(), listaCategorias, db, cadeiraSelecionada);
        categoriaAvaliacao.setAdapter(adapter);

        //Setando a quantidade de avaliações totais na tela
        ArrayList<ParseObject> avaliacoesTotais = repAvaliacao.getAllByCadeira(cadeiraSelecionada);
        qtdeAvaliacoes.setText("Avaliações: " + avaliacoesTotais.size());

        //com cadeira, pega avaliações
        ArrayList<ParseObject> listaAvaliacoes =  repAvaliacao.getAllByCadeira(cadeiraSelecionada);
        //com as avaliações, pega os comentários
        ArrayList<ParseComentario> listaComentarios = new ArrayList<ParseComentario>();
        RepositorioComentarioParse repComentario = new RepositorioComentarioParse(db);
        for (int i = 0; i < listaAvaliacoes.size(); i++) {
            ParseAvaliacao avaliacaoAux = (ParseAvaliacao) listaAvaliacoes.get(i);
            ParseComentario comentarioAux = (ParseComentario) repComentario.get(avaliacaoAux);

            if(comentarioAux.getTexto() != null && comentarioAux.getAno() != null && comentarioAux.getSemestre() != null){
                listaComentarios.add(comentarioAux);
            }
        }

        //Setando adapter da lista de comentários (passar lista de metodos de avaliação?)
        RepositorioAvaliacaoMetodoParse repAvMetodo = new RepositorioAvaliacaoMetodoParse(db);
        ArrayList<ArrayList<ParseMetodoAvaliacaoCadeira>> listaMetodos = new  ArrayList<ArrayList<ParseMetodoAvaliacaoCadeira>>();

        for (int i = 0; i < listaAvaliacoes.size(); i++) {

            ParseAvaliacao avaliacaoAux = (ParseAvaliacao) listaAvaliacoes.get(i);

            ArrayList<ParseMetodoAvaliacaoCadeira> avaliacaoMetodoAux = repAvMetodo.getByAvaliacao(avaliacaoAux);
            listaMetodos.add(avaliacaoMetodoAux);
        }

        ListaComentarioAdapter adapterComentarios = new ListaComentarioAdapter(this.getActivity(), listaComentarios, listaMetodos);
        comentarios.setAdapter(adapterComentarios);

        final Bundle bundleEnvio = new Bundle();
        bundleEnvio.putSerializable("cadeira", cadeiraSelecionada);
        bundleEnvio.putSerializable("aluno", alunoLogado);

        avaliar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                AvaliarCadeirasFragment fragment = new AvaliarCadeirasFragment();
                fragment.setArguments(bundleEnvio);
                transaction.replace(R.id.container, fragment)
                        .commit();
            }
        });

        avaliar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                AvaliarCadeirasFragment fragment = new AvaliarCadeirasFragment();
                fragment.setArguments(bundleEnvio);
                transaction.replace(R.id.container, fragment)
                        .commit();
            }
        });

        //setando a estrela assim que entra na tela
        final RepositorioCadeiraFavoritaParse cadeiraFavoritaParse = new RepositorioCadeiraFavoritaParse(db);
        ParseCadeiraFavorita favorita = cadeiraFavoritaParse.get(alunoLogado, cadeiraSelecionada);

        if(favorita.getObjectId() != null){
            chkFavoritar.setChecked(true);
        }else{
            chkFavoritar.setChecked(false);
        }

        //logica de favoritar
        chkFavoritar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkFavoritar.isChecked()){
                    ParseCadeiraFavorita aux = new ParseCadeiraFavorita(alunoLogado, cadeiraSelecionada);
                    cadeiraFavoritaParse.insert(aux);
                }else{
                    ParseCadeiraFavorita aux = cadeiraFavoritaParse.get(alunoLogado, cadeiraSelecionada);
                    cadeiraFavoritaParse.delete(aux);
                }
            }
        });

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
