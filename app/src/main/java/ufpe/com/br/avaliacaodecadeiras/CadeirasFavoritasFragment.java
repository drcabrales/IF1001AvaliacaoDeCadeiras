package ufpe.com.br.avaliacaodecadeiras;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;

import adapter.ListaCadeiraAdapter;
import database.Database;
import objetoParse.ParseAluno;
import objetoParse.ParseCadeira;
import objetoParse.ParseCadeiraFavorita;
import repositorioParse.RepositorioAvaliacaoParse;
import repositorioParse.RepositorioCadeiraFavoritaParse;
import repositorioParse.RepositorioCadeiraParse;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CadeirasFavoritasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CadeirasFavoritasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadeirasFavoritasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<ParseObject> listaFavorito = new ArrayList<ParseObject>();
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CadeirasFavoritasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CadeirasFavoritasFragment newInstance(String param1, String param2) {
        CadeirasFavoritasFragment fragment = new CadeirasFavoritasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CadeirasFavoritasFragment() {
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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cadeiras_favoritas, container, false);
        //TODO: trocar para apenas as cadeiras marcadas como favoritas

        final Bundle bundle = new Bundle();

        Bundle mBundle = new Bundle();
        mBundle = getArguments();
        final ParseAluno aluno = (ParseAluno) mBundle.getSerializable("aluno");
        ArrayList<String> nomeCadeira = new ArrayList<String>();

        //pegando a listview de cadeiras
        ListView listaCadeiras = (ListView) rootView.findViewById(R.id.listViewCadeirasFavoritas);

        //criando o adapter responsável por setar os dados e a lista de dados
        Database db = new Database(this.getActivity());
        //RepositorioCadeiraParse repCadeira = new RepositorioCadeiraParse(db);
        //ArrayList<ParseObject> lista = repCadeira.getAll();
        RepositorioCadeiraFavoritaParse repCadeiraFavorita = new RepositorioCadeiraFavoritaParse(db);

        listaFavorito = repCadeiraFavorita.getCadeiraByAluno(aluno);
        ArrayList<ParseObject> listaCadeirasF = new ArrayList<ParseObject>();
        for (int i = 0; i < listaFavorito.size(); i++) {
            ParseCadeiraFavorita cadeiraFavorita = (ParseCadeiraFavorita) listaFavorito.get(i);
            ParseObject cadeiraF = null;
            try {
                cadeiraF = cadeiraFavorita.getCadeira().fetchIfNeeded();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            listaCadeirasF.add(cadeiraF);
        }


        ListaCadeiraAdapter adapter = new ListaCadeiraAdapter(this.getActivity(),listaCadeirasF, aluno);

        //setando o adapter da listview
        listaCadeiras.setAdapter(adapter);

        //setando onClick para a cadeira clicada
        listaCadeiras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParseCadeira cadeiraSelecionada = (ParseCadeira) parent.getAdapter().getItem(position);
                //passando a cadeira clicada para o próximo fragment
                bundle.putSerializable("cadeira", cadeiraSelecionada);
                bundle.putSerializable("aluno", aluno);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                VisualizarCadeiraFragment fragment = new VisualizarCadeiraFragment();
                fragment.setArguments(bundle);
                transaction.replace(R.id.container, fragment)
                        .commit();

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
