package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class OperatorServiceTest {

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Mock
    private OperatorRepository operatorRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllOperators() {
        List<Operator> operators = new ArrayList<>();

        Operator operator1 = new Operator();
        operator1.setIdOperateur(1L);
        operator1.setFname("Operator 1");
        operator1.setLname("Operator 1");

        Supplier supplier1 = new Supplier();
        supplier1.setIdSupplier(1L);

        Invoice invoice1 = new Invoice();
        invoice1.setArchived(false);
        invoice1.setAmountInvoice(100);
        invoice1.setAmountDiscount(0);
        invoice1.setSupplier(supplier1);

        List<Invoice> invoices1 = new ArrayList<>();
        Set<Invoice> invoiceSet = new HashSet<>(invoices1);
        operator1.setInvoices(invoiceSet);

        operators.add(operator1);

        Mockito.when(operatorRepository.findAll()).thenReturn(operators);

        List<Operator> retrievedOperators = operatorService.retrieveAllOperators();

        assertEquals(1, retrievedOperators.size());
    }


    @Test
    public void testAddOperator() {
        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        operator.setFname("Operator 1");
        operator.setLname("Operator 1");

        Mockito.when(operatorRepository.save(operator)).thenReturn(operator);

        Operator addedOperator = operatorService.addOperator(operator);

        assertEquals("Operator 1", addedOperator.getFname());
    }
    @Test
    public void testDeleteOperator() {
        Long operatorId = 1L;

        Mockito.doNothing().when(operatorRepository).deleteById(operatorId);

        operatorService.deleteOperator(operatorId);

        Mockito.verify(operatorRepository, Mockito.times(1)).deleteById(operatorId);
    }
    @Test
    public void testUpdateOperator() {
        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        operator.setFname("Operator 1");
        operator.setLname("Operator 1");

        Mockito.when(operatorRepository.save(operator)).thenReturn(operator);

        Operator updatedOperator = operatorService.updateOperator(operator);

        assertEquals("Operator 1", updatedOperator.getFname());
    }
    @Test
    public void testRetrieveOperator() {
        Long operatorId = 1L;

        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        operator.setFname("Test Operator");
        operator.setLname("Test Operator");


        Mockito.when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(operator));

        Operator retrievedOperator = operatorService.retrieveOperator(operatorId);

        assertEquals("Test Operator", retrievedOperator.getFname());
    }
    @Test
    public void testRetrieveOperatorNotFound() {
        Long operatorId = 1L;
        Mockito.when(operatorRepository.findById(operatorId)).thenReturn(Optional.empty());
        assertThrows(NullPointerException.class, () -> operatorService.retrieveOperator(operatorId));
    }
}
