package services;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.google.gson.Gson;

import models.Product;

public class ProductService {
	private final String filePath;
	
	public ProductService(ServletContext context) {
		filePath = context.getRealPath("/WEB-INF/products.json");
	}
	
	public List<Product> getProducts() throws IOException{
		Gson gson = new Gson();
		
		List<Product> result = new ArrayList<Product>();
		try(var reader = new FileReader(filePath)) {
			var products = gson.fromJson(reader, Product[].class);
			
			for(var product : products) {
				result.add(product);
			}
			return result;
		}finally {
			
		}
	}
}
