package ufpe.com.br.avaliacaodecadeiras;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import database.Database;
import objetoParse.ParseAluno;
import objetoParse.ParseCurso;
import objetoParse.ParseFaculdade;
import repositorio.RepositorioAluno;
import repositorioParse.RepositorioAlunoParse;
import repositorioParse.RepositorioCursoParse;
import repositorioParse.RepositorioFaculdadeParse;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CadastroUsuarioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CadastroUsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadastroUsuarioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String siglaSelecionada = "";

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
     * @return A new instance of fragment CadastroUsuarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CadastroUsuarioFragment newInstance(String param1, String param2) {
        CadastroUsuarioFragment fragment = new CadastroUsuarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CadastroUsuarioFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_cadastro_usuario, container, false);
        final Database db = new Database(this.getActivity());
        final RepositorioFaculdadeParse repFaculdade = new RepositorioFaculdadeParse(db);

        EditText nomeUsuario = (EditText) rootView.findViewById(R.id.edtNomeUsuario);
        EditText emailUsuario = (EditText) rootView.findViewById(R.id.edtEmailUsuario);
        EditText senhaUsuario = (EditText) rootView.findViewById(R.id.edtSenhaUsuario);
        AutoCompleteTextView autoCompleteSiglaFaculdade = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteSiglaFaculdadeUsuario);
        final EditText nomeFaculdade = (EditText) rootView.findViewById(R.id.edtNomeFaculdade);
        final AutoCompleteTextView autoCompleteNomeCurso = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteCursoUsuario);
        Button btnCadastrar = (Button) rootView.findViewById(R.id.btnCadastrarUsuario);

        //Setando autocomplete de siglas
        final ArrayList<ParseObject> listaFaculdades = repFaculdade.getAll();
        ArrayList<String> nomeFaculdades = new ArrayList<String>();
        for (int i = 0; i < listaFaculdades.size(); i++) {
            nomeFaculdades.add(((ParseFaculdade)listaFaculdades.get(i)).getNome());
        }

        ArrayAdapter<String> adapterAutoCompleteSiglaFaculdade = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,nomeFaculdades);
        autoCompleteSiglaFaculdade.setAdapter(adapterAutoCompleteSiglaFaculdade);

        final Activity activityPrincipal = this.getActivity();

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
                    nomeCursos.add(((ParseCurso)listaFaculdades.get(i)).getNome());
                }

                ArrayAdapter<String> adapterAutoCompleteCurso = new ArrayAdapter<String>(activityPrincipal,android.R.layout.simple_list_item_1,nomeCursos);
                autoCompleteNomeCurso.setAdapter(adapterAutoCompleteCurso);
            }
        });


        //cadastrando o usuario
        RepositorioAlunoParse repAluno = new RepositorioAlunoParse(db);
        ParseFaculdade auxF = new ParseFaculdade(siglaSelecionada, autoCompleteSiglaFaculdade.getText().toString());
        ParseCurso aux = new ParseCurso(autoCompleteNomeCurso.getText().toString(), "", auxF);
        ParseAluno alunoACadastrar = new ParseAluno(nomeUsuario.getText().toString(), emailUsuario.getText().toString(), senhaUsuario.getText().toString(), aux);

        repAluno.insert(alunoACadastrar);

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
