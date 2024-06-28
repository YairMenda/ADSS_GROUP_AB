package dev.DataAccessLayer;

public class ProductDTO {

    private int productId;
    private String productName;
    private String storageName;
    private String category;
    private String subCategory;
    private String supplierName;
    private double size;
    private int minimumRequired;
    private List<ItemDTO> items;
    private ProductController productController;
    private ItemController itemController;

    public ProductDTO(String storageName, String category, String subCategory,int productId, String productName, String supplierName,
     double size, int minimumRequired)
    {
        this.storageName = storageName;
        this.category = category;
        this.subCategory = subCategory;
        this.productId = productId;
        this.productName = productName;
        this.supplierName = supplierName;
        this.size = size;
        this.minimumRequired = minimumRequired;
        this.items = this.itemController.getItems(productId);
        this.productController = new ProductController();
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getStorageName() {
        return storageName;
    }

    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public double getSize() {
        return size;
    }

    public int getMinimumRequired() {
        return minimumRequired;
    }
    
}
