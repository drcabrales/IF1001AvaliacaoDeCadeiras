package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;

import objetoParse.ParseCadeira;
import objetoParse.ParseCurso;
import repositorioParse.RepositorioCursoParse;
import ufpe.com.br.avaliacaodecadeiras.R;

/**
 * Created by Diogo on 22/06/2015.
 */
public class ListaCadeiraAdapter extends BaseAdapter {
    private ArrayList<ParseObject> lista;
    private Context context;

    public ListaCadeiraAdapter(Context context, ArrayList<ParseObject> lista){
        super();
        this.context = context;
        this.lista = lista;
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

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.linhalistacadeira, null);
            holder = new ViewHolder();
            holder.txtNome = (TextView) convertView.findViewById(R.id.txtNome);
            holder.txtProfessor = (TextView) convertView.findViewById(R.id.txtProfessor);
            holder.txtCurso = (TextView) convertView.findViewById(R.id.txtCurso);
            holder.txtNota = (TextView) convertView.findViewById(R.id.txtNota);

            //acho que não precisa fazer nada com isso
          //  holder.favorite = (ImageView) convertView.findViewById(R.id.favorite);

            //TODO: fazer calculo da nota geral, puxando a avaliação da cadeira aqui

            ParseCadeira cadeira = (ParseCadeira) lista.get(position);
            ParseCurso curso = null;
            try {
                curso = (ParseCurso) cadeira.getParseObject("curso").fetchIfNeeded();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            holder.txtNome.setText(cadeira.getNome());
            holder.txtProfessor.setText(cadeira.getNomeProfessor());
            holder.txtCurso.setText(curso.getNome());

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    private class ViewHolder {
        TextView txtNome;
        TextView txtProfessor;
        TextView txtCurso;
        TextView txtNota;
        ImageView favorite;
    }
}
