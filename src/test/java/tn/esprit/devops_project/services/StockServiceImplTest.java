package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StockServiceImplTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddStock() {
        // Arrange
        Stock expectedStock = new Stock();
        Mockito.when(stockRepository.save(expectedStock)).thenReturn(expectedStock);

        // Act
        Stock addedStock = stockService.addStock(expectedStock);

        // Assert
        assertEquals(expectedStock, addedStock);
    }

    @Test
    public void testRetrieveStock() {
        // Arrange
        Long stockId = 1L;
        Stock expectedStock = new Stock();
        Mockito.when(stockRepository.findById(stockId)).thenReturn(Optional.of(expectedStock));

        // Act
        Stock retrievedStock = stockService.retrieveStock(stockId);

        // Assert
        assertEquals(expectedStock, retrievedStock);
    }

    @Test
    public void testRetrieveStockNotFound() {
        // Arrange
        Long stockId = 1L;
        Mockito.when(stockRepository.findById(stockId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NullPointerException.class, () -> stockService.retrieveStock(stockId));
    }

    @Test
    public void testRetrieveAllStock() {
        // Arrange
        List<Stock> expectedStocks = new ArrayList<>();
        expectedStocks.add(new Stock());
        expectedStocks.add(new Stock());
        Mockito.when(stockRepository.findAll()).thenReturn(expectedStocks);

        // Act
        List<Stock> retrievedStocks = stockService.retrieveAllStock();

        // Assert
        assertEquals(expectedStocks, retrievedStocks);
    }
}
