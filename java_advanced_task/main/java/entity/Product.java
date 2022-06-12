package entity;

public class Product {
	private Integer id;
	private String name;
	private Integer price;
	private String category;
	private Integer categoryId;
	private String description;
	private String file;
	
	
	public Product() {}
	
	public Product(Integer id, String name, Integer price, String category) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
	}
	
	public Product(Integer id, String name, Integer price, String category, String description, String file, Integer categoryId) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.description = description;
		this.file = file;
		this.categoryId = categoryId;
	}
	
	public Product(Integer id, String name, Integer price, Integer categoryId, String description, String file) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.categoryId = categoryId;
		this.description = description;
		this.file = file;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}