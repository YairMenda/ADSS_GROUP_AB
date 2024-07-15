package DataAccessLayer;

import java.util.List;

public class ProductDTO {

    private int productId;
    private String productName;
    private String storageName;
    private String category;
    private String subCategory;
    private String supplierName;
    private double size;
    private int minimumRequired;
    private PriceToProductDTO priceToProduct;
    private List<ItemDTO> items;
    private ProductController productController = new ProductController();
    private ItemController itemController = new ItemController();



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
        this.priceToProduct = createPricesDTO(productId);
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

    public PriceToProductDTO getPriceToProductDTO(){
        return this.priceToProduct;
    }

    public List<ItemDTO> getItems()
    {
        return this.items;
    }

    public ProductController getProductController()
    {
        return this.productController;
    }

    public PriceToProductDTO createPricesDTO(int productId)
    {
        return productController.createPricesDTO(productId);
    }

    public boolean deleteProduct()
    {
        return this.productController.deleteProduct(this);
    }

    public boolean updateStoragePrice(Double newPrice)
    {
        this.priceToProduct.setStoragePrice(newPrice);
        return this.productController.updateStoragePrice(newPrice, this.productId);
    }

    public boolean updateSupPrice(Double newPrice)
    {
        this.priceToProduct.setSupplierPrice(newPrice);
        return this.productController.updateSupPrice(newPrice,this.productId);
    }

    public boolean updateDiscount(int discount, String startDate, int days)
    {
        this.priceToProduct.setDiscount(discount,startDate,days);
        return this.productController.updateDiscount(discount,startDate,days,this.productId);
    }

    
}
