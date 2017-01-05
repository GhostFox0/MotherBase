package inc.sam.farmalista;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import inc.sam.farmalista.model.Medicine;

/**
 * Created by sam on 05/01/17.
 */

public class MedicineAdapter extends BaseAdapter {

    Context mContext;

    ArrayList<Medicine> medicines;


    public MedicineAdapter(Context mContext, ArrayList<Medicine> medicines) {
        this.mContext = mContext;
        this.medicines = medicines;
    }

    @Override
    public int getCount() {
        return medicines.size();
    }

    @Override
    public Object getItem(int position) {
        return medicines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return medicines.get(position).getId_medicine();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView== null){
            LayoutInflater vInflater = LayoutInflater.from(mContext);
            convertView = vInflater.inflate(R.layout.cell_layout,null);
            ViewHolder vViewHolder = new ViewHolder();
            vViewHolder.mNameView = (TextView) convertView.findViewById(R.id.textViewMedicineNameCell);
            vViewHolder.mQuantitaView = (TextView) convertView.findViewById(R.id.textViewQiantitaMedicineCell);
            convertView.setTag(vViewHolder);
        }

        Medicine vMedicine = (Medicine) getItem(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.mNameView.setText(vMedicine.getName());
        viewHolder.mQuantitaView.setText(vMedicine.getQuantita());

        return convertView;
    }

    class ViewHolder{
        TextView mNameView;
        TextView mQuantitaView;
    }

}
