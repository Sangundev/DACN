
package com.example.shopeeappconnections.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopeeappconnections.DonHangActiviy;
import com.example.shopeeappconnections.Model.Tinhtang;
import com.example.shopeeappconnections.R;

import java.util.List;

public class TinhTrangDHAdapter extends RecyclerView.Adapter<TinhTrangDHAdapter.MyViewHolder> {

    private List<Tinhtang> tinhtangList;
    private Context context;

    public TinhTrangDHAdapter(Context context, List<Tinhtang> tinhtangList) {
        this.context = context;
        this.tinhtangList = tinhtangList;
    }

    @NonNull
    @Override
    public TinhTrangDHAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tinhtrangdonhang, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TinhTrangDHAdapter.MyViewHolder holder, int position) {
        Tinhtang currentTinhtrangdonhang = tinhtangList.get(position);

        holder.Name.setText(currentTinhtrangdonhang.getName());
        String imageResId = currentTinhtrangdonhang.getImage();
        holder.image.setImageResource(getImageResource(holder.itemView.getContext(), currentTinhtrangdonhang.getImage()));

        holder.itemView.setOnClickListener(view -> handleItemClick(currentTinhtrangdonhang));

    }
    private void handleItemClick(Tinhtang selectedTinhtang) {
        Intent intent = new Intent(context, DonHangActiviy.class);

        Bundle bundle = new Bundle();
        switch (selectedTinhtang.getName()) {
            case "Đã Đặt":
                bundle.putString("SELECTED_FRAGMENT", "Order_Fragment");
                bundle.putInt("SELECTED_TAB_INDEX", 0);
                break;
            case "Đơn Hàng Đã Thanh Toán":
                bundle.putString("SELECTED_FRAGMENT", "paymentorderFragment");
                bundle.putInt("SELECTED_TAB_INDEX", 1);
                break;
            case "Đơn Hàng Nhận Hàng rồi thanh toán":
                bundle.putString("SELECTED_FRAGMENT", "NhanRoiThanhToanFragment");
                bundle.putInt("SELECTED_TAB_INDEX", 2);
                break;
            case "Đã Hủy":
                bundle.putString("SELECTED_FRAGMENT", "CancelOrderFragment");
                bundle.putInt("SELECTED_TAB_INDEX", 3);
                break;
            default:
                // Default fragment if no match
                break;
        }

        intent.putExtras(bundle);
        context.startActivity(intent);
    }





    @Override
    public int getItemCount() {
        return tinhtangList.size();
    }
    private int getImageResource(Context context, String imageName) {
        // Convert image name to resource ID
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView Name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.tentinhtrang);
            image = itemView.findViewById(R.id.Anhtinhtrang);
        }


    }
}
