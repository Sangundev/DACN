package com.example.shopeeappconnections.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.AllproductActivity;
import com.example.shopeeappconnections.DealsActivity;
import com.example.shopeeappconnections.HotActivity;
import com.example.shopeeappconnections.Model.danhmuc;
import com.example.shopeeappconnections.R;
import com.example.shopeeappconnections.SaleActivity;
import com.example.shopeeappconnections.ShowstoreActivity;
import com.example.shopeeappconnections.TopActivity;
import com.example.shopeeappconnections.VoucherActivity;

import java.util.ArrayList;

public class DanhmucAdapter extends RecyclerView.Adapter<DanhmucAdapter.MyViewHolder> {

    private ArrayList<danhmuc> danhmuclist;
    private Context context;

    public DanhmucAdapter(Context context, ArrayList<danhmuc> danhmuclist) {
        this.context = context;
        this.danhmuclist = danhmuclist;
    }

    @NonNull
    @Override
    public DanhmucAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.danhmuc_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhmucAdapter.MyViewHolder holder, int position) {
        danhmuc currentDanhmuc = danhmuclist.get(position);

        holder.tendanhmuc.setText(currentDanhmuc.getTitle());
        holder.anhdanhmuc.setImageResource(getImageResource(holder.itemView.getContext(), currentDanhmuc.getImage()));

        holder.itemView.setOnClickListener(view -> handleItemClick(currentDanhmuc));
    }
    private void handleItemClick(danhmuc selectedDanhmuc) {
        Class<?> targetActivity = null;
        switch (selectedDanhmuc.getImage()) {
            case "hotdeal":
                targetActivity = DealsActivity.class;
                break;
            case "giftvoucher":
                targetActivity = VoucherActivity.class;
                break;
            case "hot":
                 targetActivity = HotActivity.class;
                break;
            case "salehhh":
                 targetActivity = SaleActivity.class;
                break;
            case "newhehe":
//                 targetActivity = AllproductAdapter.class;
                break;
            case "Search":
                 targetActivity = AllproductAdapter.class;
                break;
            case "store":
                 targetActivity = ShowstoreActivity.class;
                break;
            case "newproduct":
                targetActivity = AllproductActivity.class;
                break;
            case "thebest":
                 targetActivity = TopActivity.class;
                break;
            default:
                // targetActivity = DefaultActivity.class; // Default activity if no match
                break;
        }

        if (targetActivity != null && context != null) {
            Intent intent = new Intent(context, targetActivity);
            intent.putExtra("category", selectedDanhmuc.getTitle());
            context.startActivity(intent);
        }

    }


    @Override
    public int getItemCount() {
        return danhmuclist.size();
    }

    private int getImageResource(Context context, String imageName) {
        // Convert image name to resource ID
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tendanhmuc;
        ImageView anhdanhmuc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tendanhmuc = itemView.findViewById(R.id.tendanhmuc);
            anhdanhmuc = itemView.findViewById(R.id.Anhdanhmuc);
        }
    }
}
