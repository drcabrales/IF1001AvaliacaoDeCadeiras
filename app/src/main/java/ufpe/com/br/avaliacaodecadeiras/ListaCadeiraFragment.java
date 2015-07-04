package ufpe.com.br.avaliacaodecadeiras;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.parse.ParseObject;

import java.util.ArrayList;

import adapter.ListaCadeiraAdapter;
import database.Database;
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AutoCompleteTextView actv;
    private AutoCompleteTextView actv2;

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

        ListView listaCadeiras = (ListView) rootView.findViewById(R.id.listCadeira);
        Database db = new Database(this.getActivity());
        RepositorioCadeiraParse repCadeira = new RepositorioCadeiraParse(db);
        RepositorioCursoParse repCurso = new RepositorioCursoParse(db);

        //TODO: fazer filtro para pegar cadeira pelo curso escolhido
        ArrayList<ParseObject> listaCadeira = repCadeira.getAll();
        //TODO: fazer filtro para pegar cursos pela faculdade do usuario
        ArrayList<ParseObject> listaCurso = repCurso.getAll();

        ArrayList <String> nomeCursos = new ArrayList <String> ();
        ArrayList <String> nomeCadeira = new ArrayList <String> ();

        ListaCadeiraAdapter adapter = new ListaCadeiraAdapter(this.getActivity(),listaCadeira);
        listaCadeiras.setAdapter(adapter);

        for (int i = 0; i < listaCurso.size(); i++){
            nomeCursos.add(((ParseCurso)listaCurso.get(i)).getNome());
        }

        for (int i = 0; i < listaCadeira.size() ; i++) {
            nomeCadeira.add(((ParseCadeira)listaCadeira.get(i)).getNome());
        }



        actv = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteTextView1);
        actv2 = (AutoCompleteTextView)rootView.findViewById(R.id.autoCompleteTextView2);


        ArrayAdapter<String> adapterAutoCompleteCurso = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,nomeCursos);
        actv.setAdapter(adapterAutoCompleteCurso);

        ArrayAdapter<String> adapterAutoCompleteCadeira = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,nomeCadeira);
        actv2.setAdapter(adapterAutoCompleteCurso);

        return inflater.inflate(R.layout.fragment_lista_cadeira, container, false);
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
