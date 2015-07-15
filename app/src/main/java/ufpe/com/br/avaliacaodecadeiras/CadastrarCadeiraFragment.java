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
import android.widget.EditText;
import android.widget.Toast;

import database.Database;
import objetoParse.ParseAluno;
import objetoParse.ParseCadeira;
import objetoParse.ParseCurso;
import repositorioParse.RepositorioCadeiraParse;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CadastrarCadeiraFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CadastrarCadeiraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadastrarCadeiraFragment extends Fragment {
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
     * @return A new instance of fragment CadastrarCadeiraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CadastrarCadeiraFragment newInstance(String param1, String param2) {
        CadastrarCadeiraFragment fragment = new CadastrarCadeiraFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CadastrarCadeiraFragment() {
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

        View rootView = inflater.inflate(R.layout.fragment_cadastrar_cadeira, container, false);

        Bundle bundle = this.getArguments();
        final Bundle bundle2 = new Bundle();
        final ParseAluno alunoLogado = (ParseAluno) bundle.getSerializable("aluno");
        //TODO: EM LISTAR CADEIRA, PASSAR PRO BUNDLE DO FRAGMENT O CURSO QUE TÁ SENDO VISUALIZADO NO MOMENTO
        final ParseCurso cursoSelecionado = (ParseCurso) bundle.getSerializable("curso");

        final EditText edtNomeCadeira = (EditText) rootView.findViewById(R.id.edtNomeCadeira);
        final EditText edtNomeProfessor = (EditText) rootView.findViewById(R.id.edtNomeProfessor);
        Button btnCadastrarCadeira = (Button) rootView.findViewById(R.id.btnCadastrarCadeira);
        Button btnVoltarCadastrarCadeira = (Button) rootView.findViewById(R.id.btnVoltarCadastrarCadeira);



        final Toast toastCadastroOk = Toast.makeText(this.getActivity(), "Cadeira cadastrada com sucesso!", Toast.LENGTH_SHORT);

        Database db = new Database(this.getActivity());
        final RepositorioCadeiraParse repCadeira = new RepositorioCadeiraParse(db);

        btnCadastrarCadeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseCadeira cadeiraACadastrar = new ParseCadeira(edtNomeCadeira.getText().toString(), edtNomeProfessor.getText().toString(), cursoSelecionado);
                repCadeira.insert(cadeiraACadastrar);

                //limpando os campos do cadastro
                edtNomeCadeira.setText("");
                edtNomeProfessor.setText("");

                //toast informando o cadastro da cadeira
                toastCadastroOk.show();
            }
        });


        btnVoltarCadastrarCadeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle2.putSerializable("aluno",alunoLogado);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ListaCadeiraFragment fragment = new ListaCadeiraFragment();
                fragment.setArguments(bundle2);
                transaction
                        .replace(R.id.container, fragment)
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
