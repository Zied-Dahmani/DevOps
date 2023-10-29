package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.Iservices.IProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void canAddProduct() {
        // Arrange
        Product product = new Product();
        Long stockId = 1L;
        Stock stock = new Stock();
        Mockito.when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));
        Mockito.when(productRepository.save(product)).thenReturn(product);

        // Act
        Product addedProduct = productService.addProduct(product, stockId);

        // Assert
        assertEquals(stock, addedProduct.getStock());
    }

    @Test
    public void canRetrieveProduct() {
        // Arrange
        Long productId = 1L;
        Product expectedProduct = new Product();
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));

        // Act
        Product retrievedProduct = productService.retrieveProduct(productId);

        // Assert
        assertEquals(expectedProduct, retrievedProduct);
    }

    @Test
    public void canRetrieveProductNotFound() {
        // Arrange
        Long productId = 1L;
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NullPointerException.class, () -> productService.retrieveProduct(productId));
    }

    @Test
    public void canRetrieveAllProducts() {
        // Arrange
        List<Product> expectedProducts = new ArrayList<>();
        Mockito.when(productRepository.findAll()).thenReturn(expectedProducts);

        // Act
        List<Product> actualProducts = productService.retreiveAllProduct();

        // Assert
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void canRetrieveProductsByCategory() {
        // Arrange
        ProductCategory category = ProductCategory.ELECTRONICS;
        List<Product> expectedProducts = new ArrayList<>();
        Mockito.when(productRepository.findByCategory(category)).thenReturn(expectedProducts);

        // Act
        List<Product> actualProducts = productService.retrieveProductByCategory(category);

        // Assert
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void canDeleteProduct() {
        // Arrange
        Long productId = 1L;
        Mockito.doNothing().when(productRepository).deleteById(productId);

        // Act
        productService.deleteProduct(productId);

        // Assert
        // No exception should be thrown
    }

    @Test
    public void canRetrieveProductsInStock() {
        // Arrange
        Long stockId = 1L;
        List<Product> expectedProducts = new ArrayList<>();
        Mockito.when(productRepository.findByStockIdStock(stockId)).thenReturn(expectedProducts);

        // Act
        List<Product> actualProducts = productService.retreiveProductStock(stockId);

        // Assert
        assertEquals(expectedProducts, actualProducts);
    }
}
