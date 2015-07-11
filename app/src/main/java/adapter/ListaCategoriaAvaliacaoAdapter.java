package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;

import database.Database;
import objetoParse.ParseCadeira;
import objetoParse.ParseCategoriaAvaliacaoCadeira;
import objetoParse.ParseCurso;
import repositorioParse.RepositorioAvaliacaoCategoriaParse;
import repositorioParse.RepositorioAvaliacaoParse;
import ufpe.com.br.avaliacaodecadeiras.R;

/**
 * Created by Diogo on 08/07/2015.
 */
public class ListaCategoriaAvaliacaoAdapter extends BaseAdapter {
    private ArrayList<ParseObject> lista;
    private Context context;
    private Database db;
    private ParseCadeira cadeiraAtual;

    public ListaCategoriaAvaliacaoAdapter(Context context, ArrayList<ParseObject> lista, Database db, ParseCadeira cadeiraAtual){
        super();
        this.context = context;
        this.lista = lista;
        this.db = db;
        this.cadeiraAtual = cadeiraAtual;
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
            convertView = inflater.inflate(R.layout.linhalistacategoriaavaliacao, null);
            holder = new ViewHolder();
            holder.txtNome = (TextView) convertView.findViewById(R.id.txtNomeCategoriaAv);
            holder.txtNotaMedia = (TextView) convertView.findViewById(R.id.txtNotaCategoriaAv);

            ParseCategoriaAvaliacaoCadeira categoria = (ParseCategoriaAvaliacaoCadeira) lista.get(position);

            if(categoria != null){
                String nome = categoria.getNome();
                holder.txtNome.setText(nome);

                RepositorioAvaliacaoParse repAvCategoria = new RepositorioAvaliacaoParse(db);
                double media = repAvCategoria.getMediaCategoriaAvaliacaoByCadeira(categoria, cadeiraAtual);

                if(media == 99){
                    holder.txtNotaMedia.setText("Não avaliado");
                }else{
                    holder.txtNotaMedia.setText("Nota: "+  media);
                }

            }


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    private class ViewHolder {
        TextView txtNome;
        TextView txtNotaMedia;
    }
}
