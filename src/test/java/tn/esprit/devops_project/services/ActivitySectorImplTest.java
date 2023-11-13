package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;
import tn.esprit.devops_project.services.Iservices.IActivitySector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActivitySectorImplTest {

    @InjectMocks
    private ActivitySectorImpl activitySectorService;

    @Mock
    private ActivitySectorRepository activitySectorRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void canRetrieveAllActivitySectors() {
        // Arrange
        List<ActivitySector> expectedActivitySectors = new ArrayList<>();
        Mockito.when(activitySectorRepository.findAll()).thenReturn(expectedActivitySectors);

        // Act
        List<ActivitySector> actualActivitySectors = activitySectorService.retrieveAllActivitySectors();

        // Assert
        assertEquals(expectedActivitySectors, actualActivitySectors);
    }

    @Test
    public void canAddActivitySector() {
        // Arrange
        ActivitySector activitySectorToAdd = new ActivitySector();
        Mockito.when(activitySectorRepository.save(activitySectorToAdd)).thenReturn(activitySectorToAdd);

        // Act
        ActivitySector addedActivitySector = activitySectorService.addActivitySector(activitySectorToAdd);

        // Assert
        assertEquals(activitySectorToAdd, addedActivitySector);
    }

    @Test
    public void canDeleteActivitySector() {
        // Arrange
        Long activitySectorIdToDelete = 1L;

        // Act
        activitySectorService.deleteActivitySector(activitySectorIdToDelete);

        // Assert
        Mockito.verify(activitySectorRepository, Mockito.times(1)).deleteById(activitySectorIdToDelete);
    }

    @Test
    public void canUpdateActivitySector() {
        // Arrange
        ActivitySector activitySectorToUpdate = new ActivitySector();
        Mockito.when(activitySectorRepository.save(activitySectorToUpdate)).thenReturn(activitySectorToUpdate);

        // Act
        ActivitySector updatedActivitySector = activitySectorService.updateActivitySector(activitySectorToUpdate);

        // Assert
        assertEquals(activitySectorToUpdate, updatedActivitySector);
    }

    @Test
    public void testRetrieveActivitySector() {
        // Arrange
        Long activitySectorId = 1L;
        ActivitySector expectedActivitySector = new ActivitySector();
        Mockito.when(activitySectorRepository.findById(activitySectorId)).thenReturn(Optional.of(expectedActivitySector));

        // Act
        ActivitySector retrievedActivitySector = activitySectorService.retrieveActivitySector(activitySectorId);

        // Assert
        assertEquals(expectedActivitySector, retrievedActivitySector);
    }

    @Test
    public void testRetrieveActivitySectorNotFound() {
        // Arrange
        Long activitySectorId = 1L;
        Mockito.when(activitySectorRepository.findById(activitySectorId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> activitySectorService.retrieveActivitySector(activitySectorId));
    }
}
