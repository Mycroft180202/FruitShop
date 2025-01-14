package com.example.fruitapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fruitapp.R;
import com.example.fruitapp.model.Product;
import com.example.fruitapp.view.EditProductActivity;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private Context context;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productNameTextView.setText(product.getProductName());
        holder.productPriceTextView.setText("Price: $" + product.getPrice());
        if (product.getCategoryName() != null) {
            holder.productCategoryTextView.setText("Category: " + product.getCategoryName());
        } else {
            holder.productCategoryTextView.setText("Category: N/A");
        }

        // Set click listener for edit button
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditProductActivity.class);
                intent.putExtra("PRODUCT_ID", product.getProductId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView productNameTextView;
        public TextView productPriceTextView;
        public TextView productCategoryTextView;
        public Button editButton;

        public ProductViewHolder(View view) {
            super(view);
            productNameTextView = view.findViewById(R.id.productNameTextView);
            productPriceTextView = view.findViewById(R.id.productPriceTextView);
            productCategoryTextView = view.findViewById(R.id.productCategoryTextView);
            editButton = view.findViewById(R.id.editButton);
        }
    }
    public void setProducts(List<Product> products) {
        this.productList = products;
        notifyDataSetChanged();
    }
}