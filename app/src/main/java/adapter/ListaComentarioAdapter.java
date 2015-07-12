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
import objetoParse.ParseComentario;
import objetoParse.ParseCurso;
import objetoParse.ParseMetodoAvaliacaoCadeira;
import ufpe.com.br.avaliacaodecadeiras.R;

/**
 * Created by Diogo on 11/07/2015.
 */
public class ListaComentarioAdapter extends BaseAdapter{
    private ArrayList<ParseComentario> lista;
    private ArrayList<ArrayList<ParseMetodoAvaliacaoCadeira>> listaMetodos;
    private Context context;

    public ListaComentarioAdapter(Context context, ArrayList<ParseComentario> lista, ArrayList<ArrayList<ParseMetodoAvaliacaoCadeira>> listaMetodos){
        super();
        this.context = context;
        this.lista = lista;
        this.listaMetodos = listaMetodos;
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
            convertView = inflater.inflate(R.layout.linhalistacomentarios, null);
            holder = new ViewHolder();
            holder.txtComentario = (TextView) convertView.findViewById(R.id.txtComentario);
            holder.txtAno = (TextView) convertView.findViewById(R.id.txtAno);
            holder.txtSemestre = (TextView) convertView.findViewById(R.id.txtSemestre);
            holder.txtMetodos = (TextView) convertView.findViewById(R.id.txtMetodosAvaliacao);


            ParseComentario comentario = lista.get(position);

            String metodosAv = "Métodos de avaliação: ";
            for (int i = 0; i < listaMetodos.get(position).size(); i++) {
                if(i == listaMetodos.get(position).size()-1){
                    metodosAv = metodosAv + listaMetodos.get(position).get(i).getNome() + ".";
                }else{
                    metodosAv = metodosAv + listaMetodos.get(position).get(i).getNome() + ", ";
                }

            }



                holder.txtComentario.setText(comentario.getTexto());
                holder.txtAno.setText("Ano: " + comentario.getAno());
                holder.txtSemestre.setText("Semestre: " + comentario.getSemestre());
                holder.txtMetodos.setText(metodosAv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    private class ViewHolder {
        TextView txtComentario;
        TextView txtAno;
        TextView txtSemestre;
        TextView txtMetodos;
    }
}
