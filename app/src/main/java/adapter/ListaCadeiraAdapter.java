package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;

import database.Database;
import objetoParse.ParseAluno;
import objetoParse.ParseCadeira;
import objetoParse.ParseCadeiraFavorita;
import objetoParse.ParseCurso;
import repositorioParse.RepositorioCadeiraFavoritaParse;
import repositorioParse.RepositorioCursoParse;
import ufpe.com.br.avaliacaodecadeiras.R;

/**
 * Created by Diogo on 22/06/2015.
 */
public class ListaCadeiraAdapter extends BaseAdapter {
    private ArrayList<ParseObject> lista;
    private Context context;
    private ParseAluno alunoLogado;

    public ListaCadeiraAdapter(Context context, ArrayList<ParseObject> lista, ParseAluno alunoLogado){
        super();
        this.context = context;
        this.lista = lista;
        this.alunoLogado = alunoLogado;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.linhalistacadeira, null);
            holder = new ViewHolder();
            holder.txtNome = (TextView) convertView.findViewById(R.id.txtNome);
            holder.txtProfessor = (TextView) convertView.findViewById(R.id.txtProfessor);
            holder.txtCurso = (TextView) convertView.findViewById(R.id.txtCurso);
            holder.txtNota = (TextView) convertView.findViewById(R.id.txtNota);
            holder.favoritar = (CheckBox) convertView.findViewById(R.id.favorite);

            final ParseCadeira cadeira = (ParseCadeira) lista.get(position);
            ParseCurso curso = null;
            try {
                curso = (ParseCurso) cadeira.getParseObject("curso").fetchIfNeeded();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            holder.txtNome.setText(cadeira.getNome());
            holder.txtProfessor.setText(cadeira.getNomeProfessor());
            holder.txtCurso.setText(curso.getNome());

        //setando a estrela assim que entra na tela
        Database db = new Database(context);
        final RepositorioCadeiraFavoritaParse cadeiraFavoritaParse = new RepositorioCadeiraFavoritaParse(db);
        ParseCadeiraFavorita favorita = cadeiraFavoritaParse.get(alunoLogado, cadeira);

        if(favorita.getObjectId() != null){
            holder.favoritar.setChecked(true);
        }else{
            holder.favoritar.setChecked(false);
        }

        //logica de favoritar
        holder.favoritar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.favoritar.isChecked()){
                    ParseCadeiraFavorita aux = new ParseCadeiraFavorita(alunoLogado, cadeira);
                    cadeiraFavoritaParse.insert(aux);
                }else{
                    ParseCadeiraFavorita aux = cadeiraFavoritaParse.get(alunoLogado, cadeira);
                    cadeiraFavoritaParse.delete(aux);
                }
            }
        });

            convertView.setTag(holder);

        return convertView;
    }

    private class ViewHolder {
        TextView txtNome;
        TextView txtProfessor;
        TextView txtCurso;
        TextView txtNota;
        CheckBox favoritar;
    }
}
