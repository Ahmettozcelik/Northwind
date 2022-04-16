package kodlamaio.northwind.business.concretes;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.ProductDao;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

@Service
public class ProductManager implements ProductService{

	private ProductDao productDao;
	
	@Autowired
	public ProductManager(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public DataResult<List<Product>> getAll() {
		return new SuccessDataResult<List<Product>>(this.productDao.findAll(),"Data founded.!");
	}
	
	@Override
	public DataResult<List<Product>> getAllSorted() {
		
		Sort sort = Sort.by(Sort.Direction.DESC,"productName");
		
		return new SuccessDataResult<List<Product>>(this.productDao.findAll(sort),"Data Sorted.!");
	}
	
	@Override
	public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		
		return new SuccessDataResult<List<Product>>(this.productDao.findAll(pageable).getContent());
	}

	@Override
	public Result add(Product product) {
		this.productDao.save(product);
		return new SuccessResult("Product added.!");
	}

	@Override
	public DataResult<Product> getByProductName(String productName) {
		return new SuccessDataResult<Product>
		(this.productDao.getByProductName(productName),"Data founded by productName.!");
	}

	@Override
	public DataResult<Product> getByProductNameAndCategoryId(String productName, int categoryId) {
		return new SuccessDataResult<Product>
		(this.productDao.getByProductNameAndCategory_CategoryId(productName, categoryId),"Data founded by productName and categoryId.!");

	}

	@Override
	public DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByProductNameOrCategory_CategoryId(productName,categoryId),"Data listed by productName or categoryId.!");
	}

	@Override
	public DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByCategory_CategoryIdIn(categories),"Data listed by categoryId.!");
	}

	@Override
	public DataResult<List<Product>> getByProductNameContains(String productName) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByProductNameContains(productName),"Data listed by productName contains.!");
	}

	@Override
	public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByProductNameStartsWith(productName),"Data listed by productName startsWith .!");
	}

	@Override
	public DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByNameAndCategory_CategoryId(productName, categoryId),"Data listed by name and category.!");
	}

	@Override
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
		return new SuccessDataResult<List<ProductWithCategoryDto>>(this.productDao.getProductWithCategoryDetails(),"Data listed.!");
	}

	
	
	
	
}