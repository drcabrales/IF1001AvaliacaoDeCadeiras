package ufpe.com.br.avaliacaodecadeiras;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseObject;

import java.util.ArrayList;

import adapter.ListaCadeiraAdapter;
import database.Database;
import objetoParse.ParseAluno;
import objetoParse.ParseCadeira;
import objetoParse.ParseCurso;
import repositorio.RepositorioCadeira;
import repositorioParse.RepositorioCadeiraParse;
import repositorioParse.RepositorioCursoParse;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaCadeiraFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaCadeiraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaCadeiraFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<ParseObject> listaCadeira = new ArrayList<ParseObject>();
    ParseCurso cursoSelecionado;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AutoCompleteTextView actv;
    private AutoCompleteTextView actv2;
    private String cursoN = "";
    private String cadeiraN = "";

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaCadeiraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaCadeiraFragment newInstance(String param1, String param2) {
        ListaCadeiraFragment fragment = new ListaCadeiraFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ListaCadeiraFragment() {
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

        View rootView = inflater.inflate(R.layout.fragment_lista_cadeira, container, false);
        final Bundle bundle = new Bundle();

        final ListView listaCadeiras = (ListView) rootView.findViewById(R.id.listCadeira);
        Database db = new Database(this.getActivity());
        final RepositorioCadeiraParse repCadeira = new RepositorioCadeiraParse(db);
        RepositorioCursoParse repCurso = new RepositorioCursoParse(db);


        
        //TODO: fazer filtro para pegar cursos pela faculdade do usuario
        final ArrayList<ParseObject> listaCurso = repCurso.getAll();

        ArrayList <String> nomeCursos = new ArrayList <String> ();


        for (int i = 0; i < listaCurso.size(); i++){
            nomeCursos.add(((ParseCurso)listaCurso.get(i)).getNome());
        }

        actv = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteTextView1);
        actv2 = (AutoCompleteTextView)rootView.findViewById(R.id.autoCompleteTextView2);

        Button btnIrCadastroCadeira = (Button) rootView.findViewById(R.id.btnIrCadastroCadeira);

        ArrayAdapter<String> adapterAutoCompleteCurso = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,nomeCursos);
        actv.setAdapter(adapterAutoCompleteCurso);

        final Activity activity = this.getActivity();
        
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursoN = (String) parent.getAdapter().getItem(position);

                cursoSelecionado = new ParseCurso();

                for (int i = 0; i < listaCurso.size(); i++) {
                    if (((ParseCurso) listaCurso.get(i)).getNome().equals(cursoN)) {
                        cursoSelecionado = (ParseCurso) listaCurso.get(i);
                    }
                }


                ArrayList<String> nomeCadeira = new ArrayList<String>();

                listaCadeira = repCadeira.getCadeiraByCurso(cursoSelecionado);

                for (int i = 0; i < listaCadeira.size(); i++) {
                    nomeCadeira.add(((ParseCadeira) listaCadeira.get(i)).getNome());
                }

                ArrayAdapter<String> adapterAutoCompleteCadeira = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, nomeCadeira);
                actv2.setAdapter(adapterAutoCompleteCadeira);

                ListaCadeiraAdapter adapter = new ListaCadeiraAdapter(activity, listaCadeira);
                listaCadeiras.setAdapter(adapter);
            }
        });

        
        

        actv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cadeiraN = (String) parent.getAdapter().getItem(position);
                
                ParseCadeira cadeiraSelecionado = new ParseCadeira();


                for (int i = 0; i < listaCadeira.size(); i++) {
                    if (((ParseCadeira) listaCadeira.get(i)).getNome().equals(cadeiraN)) {
                        cadeiraSelecionado = (ParseCadeira) listaCadeira.get(i);
                        
                    }
                }

                bundle.putSerializable("cadeira", cadeiraSelecionado);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                VisualizarCadeiraFragment fragment = new VisualizarCadeiraFragment();
                fragment.setArguments(bundle);
                transaction.replace(R.id.container, fragment)
                        .commit();

            }
        });

        listaCadeiras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParseCadeira cadeiraSelecionada = (ParseCadeira) parent.getAdapter().getItem(position);
                //passando a cadeira clicada para o próximo fragment
                bundle.putSerializable("cadeira", cadeiraSelecionada);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                VisualizarCadeiraFragment fragment = new VisualizarCadeiraFragment();
                fragment.setArguments(bundle);
                transaction.replace(R.id.container, fragment)
                        .commit();

            }
        });

        final Bundle bundle2 = new Bundle();
        final Toast toastCadastroErro = Toast.makeText(this.getActivity(), "Para cadastrar uma cadeira escolha um curso!", Toast.LENGTH_SHORT);
        btnIrCadastroCadeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cursoSelecionado != null){
                    bundle2.putSerializable("curso",cursoSelecionado);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    CadastrarCadeiraFragment fragment = new CadastrarCadeiraFragment();
                    fragment.setArguments(bundle2);
                    transaction.replace(R.id.container, fragment ).commit();
                }else{
                    toastCadastroErro.show();
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
