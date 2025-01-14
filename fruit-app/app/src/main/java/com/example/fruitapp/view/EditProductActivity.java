package com.example.fruitapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fruitapp.R;
import com.example.fruitapp.adapter.CategoryAdapter;
import com.example.fruitapp.model.Category;
import com.example.fruitapp.model.Product;
import com.example.fruitapp.network.ApiService;
import com.example.fruitapp.network.RetrofitClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProductActivity extends AppCompatActivity {

    private EditText productNameEditText, productDescriptionEditText, productPriceEditText, productQuantityEditText;
    private ImageView productImageView;
    private Button saveChangesButton;
    private Spinner categorySpinner;
    private CategoryAdapter categoryAdapter;
    private List<Category> categories = new ArrayList<>();
    private Product currentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        productNameEditText = findViewById(R.id.editProductNameEditText);
        productDescriptionEditText = findViewById(R.id.editProductDescriptionEditText);
        productPriceEditText = findViewById(R.id.editProductPriceEditText);
        productQuantityEditText = findViewById(R.id.editProductQuantityEditText);
        productImageView = findViewById(R.id.editProductImageView);
        saveChangesButton = findViewById(R.id.saveChangesProductButton);
        categorySpinner = findViewById(R.id.editProductCategorySpinner);

        // Fetch categories
        fetchCategories();

        // Get the product ID from intent
        Intent intent = getIntent();
        if (intent.hasExtra("PRODUCT_ID")) {
            Long productId = intent.getLongExtra("PRODUCT_ID", -1);
            if (productId != -1) {
                fetchProductDetails(productId);
            }
        }

        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });
    }

    private void fetchCategories() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<List<Category>> call = apiService.getCategories();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categories = response.body();
                    categoryAdapter = new CategoryAdapter(EditProductActivity.this, categories);
                    categorySpinner.setAdapter(categoryAdapter);

                    // Set the spinner selection once categories are loaded
                    if (currentProduct != null && currentProduct.getCategoryId() != null) {
                        setSpinnerSelection();
                    }
                } else {
                    Toast.makeText(EditProductActivity.this, "Failed to fetch categories", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(EditProductActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchProductDetails(Long productId) {
        ApiService apiService = RetrofitClient.getApiService();
        Call<Product> call = apiService.getProductById(productId);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    currentProduct = response.body();
                    displayProductDetails();
                } else {
                    Toast.makeText(EditProductActivity.this, "Failed to fetch product details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(EditProductActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayProductDetails() {
        if (currentProduct != null) {
            productNameEditText.setText(currentProduct.getProductName());
            productDescriptionEditText.setText(currentProduct.getDescription());
            productPriceEditText.setText(currentProduct.getPrice().toString());
            productQuantityEditText.setText(String.valueOf(currentProduct.getQuantityInStock()));

            // Set the spinner selection if categories are already loaded
            if (categoryAdapter != null) {
                setSpinnerSelection();
            }
        }
    }

    private void setSpinnerSelection() {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getCategoryId().equals(currentProduct.getCategoryId())) {
                categorySpinner.setSelection(i);
                break;
            }
        }
    }

    private void saveChanges() {
        // Validate input fields
        if (!validateInput()) {
            return;
        }

        // Get updated values from input fields
        String productName = productNameEditText.getText().toString();
        String description = productDescriptionEditText.getText().toString();
        BigDecimal price = new BigDecimal(productPriceEditText.getText().toString());
        int quantityInStock = Integer.parseInt(productQuantityEditText.getText().toString());
        Category selectedCategory = (Category) categorySpinner.getSelectedItem();

        // Update the current product object
        currentProduct.setProductName(productName);
        currentProduct.setDescription(description);
        currentProduct.setPrice(price);
        currentProduct.setQuantityInStock(quantityInStock);

        // Lấy category ID và name từ selectedCategory
        Long categoryId = selectedCategory.getCategoryId();
        String categoryName = selectedCategory.getCategoryName();

        // Set category ID và name cho currentProduct
        currentProduct.setCategoryId(categoryId);
        currentProduct.setCategoryName(categoryName);

        // Call the API to update the product
        ApiService apiService = RetrofitClient.getApiService();
        Call<Product> call = apiService.updateProduct(currentProduct.getProductId(), currentProduct);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EditProductActivity.this, "Product updated successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity
                } else {
                    Toast.makeText(EditProductActivity.this, "Failed to update product", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(EditProductActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateInput() {
        if (productNameEditText.getText().toString().trim().isEmpty()) {
            productNameEditText.setError("Product name is required");
            return false;
        }
        if (productDescriptionEditText.getText().toString().trim().isEmpty()) {
            productDescriptionEditText.setError("Description is required");
            return false;
        }
        if (productPriceEditText.getText().toString().trim().isEmpty()) {
            productPriceEditText.setError("Price is required");
            return false;
        }
        if (productQuantityEditText.getText().toString().trim().isEmpty()) {
            productQuantityEditText.setError("Quantity is required");
            return false;
        }
        // Add more validations as needed
        return true;
    }
}