package adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.redha.frent.MainActivity;
import com.redha.frent.R;
import com.redha.frent.RincianGedungActivity;

import java.util.List;

import model.GedungModel;

public class GedungAdapter extends RecyclerView.Adapter<UserViewHolder>{
    private Context context;
    private List<GedungModel> gedungModels;

    public GedungAdapter(Context context, List<GedungModel> gedungModels) {
        this.context = context;
        this.gedungModels = gedungModels;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_gedung, parent,false);
        UserViewHolder uvh = new UserViewHolder(view);
        return uvh;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        GedungModel gedungModel = gedungModels.get(position);

        holder.namaGedung.setText(gedungModel.getNama());
        holder.alamat.setText(gedungModel.getAlamat());
        holder.harga.setText("Rp"+gedungModel.getHarga());
        Glide.with(context).load(gedungModel.getGambar()).into(holder.image);

        // Set onclicklistener pada view namagedung (TextView)
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RincianGedungActivity.class);
                intent.putExtra("gambar",gedungModel.getGambar());
                intent.putExtra("namagedung",gedungModel.getNama());
                intent.putExtra("alamat",gedungModel.getAlamat());
                intent.putExtra("notelp",gedungModel.getNotelp());
                intent.putExtra("keterangan",gedungModel.getKeterangan());
                intent.putExtra("harga",gedungModel.getHarga());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return gedungModels.size();
    }



}

class UserViewHolder extends RecyclerView.ViewHolder {
    TextView id, namaGedung, alamat, harga;
    ImageView image;
    CardView cardView;
    LinearLayout ll;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.txtId);
        namaGedung = itemView.findViewById(R.id.txtNamaGedung);
        alamat = itemView.findViewById(R.id.txtAlamat);
        harga = itemView.findViewById(R.id.txtHarga);
        image = itemView.findViewById(R.id.image);
        cardView = itemView.findViewById(R.id.cardview);
        ll = itemView.findViewById(R.id.linearlayout);
    }
}
