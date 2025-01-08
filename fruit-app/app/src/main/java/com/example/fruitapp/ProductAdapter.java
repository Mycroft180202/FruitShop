package com.example.fruitapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fruitapp.model.Product;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
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
        holder.productCategoryTextView.setText("Category: " + product.getCategoryName());
        // Load image using Glide or Picasso if needed
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView productNameTextView;
        public TextView productPriceTextView;
        public TextView productCategoryTextView;

        public ProductViewHolder(View view) {
            super(view);
            productNameTextView = view.findViewById(R.id.productNameTextView);
            productPriceTextView = view.findViewById(R.id.productPriceTextView);
            productCategoryTextView = view.findViewById(R.id.productCategoryTextView);
        }
    }
    public void setProducts(List<Product> products) {
        this.productList = products;
        notifyDataSetChanged();
    }
}