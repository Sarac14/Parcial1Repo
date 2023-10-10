package com.example.parcial1.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial1.ProductDetailActivity;
import com.example.parcial1.R;
import com.example.parcial1.dto.Producto;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class RetrofitAdapter extends ListAdapter<Producto, RetrofitAdapter.WordViewHolder> {

    private static Context context;
    private Consumer<Producto> userConsumer;


    public RetrofitAdapter(@NonNull DiffUtil.ItemCallback<Producto> diffCallback, Context context) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return WordViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WordViewHolder holder, int position) {
        Producto current = getItem(position);
        holder.bind(current);

        holder.ver.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("PRODUCT_ID", current.id);
            context.startActivity(intent);
        });


    }

    public static class Diff extends DiffUtil.ItemCallback<Producto> {

        @Override
        public boolean areItemsTheSame(@NonNull Producto oldItem, @NonNull Producto newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Producto oldItem, @NonNull Producto newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }

    static class WordViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView description;
        private final Button ver;


        private WordViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.descripcion);
            ver = itemView.findViewById(R.id.ver);


        }

        public void bind(Producto producto) {

            title.setText(producto.title);
            description.setText(producto.description);

        }

        static WordViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product, parent, false);
            return new WordViewHolder(view);
        }

    }


    public void setUserConsumer(Consumer<Producto> userConsumer) {
        this.userConsumer = userConsumer;
    }

}
