package thiago.app.com.listafrutas;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Thiago on 02/08/2017.
 */

public class FrutaAdapter extends ArrayAdapter<Fruta> {
    ArrayList<Fruta> frutas;
    Context context;
    int resource;

    public FrutaAdapter(Context context, int resource, ArrayList<Fruta> frutas) {
        super(context, resource, frutas);
        this.resource = resource;
        this.context = context;
        this.frutas = frutas;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.listafrutas, null, true);
        }
        Fruta fruta = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.imageview);
        Picasso.with(context).load(fruta.getImagem()).into(imageView);

        TextView txtName = convertView.findViewById(R.id.txtnome);
        txtName.setText(fruta.getNome());

        TextView txtPrice =  convertView.findViewById(R.id.txtpreco);
        txtPrice.setText(String.valueOf(fruta.getPreco()));
        return convertView;
    }
}
