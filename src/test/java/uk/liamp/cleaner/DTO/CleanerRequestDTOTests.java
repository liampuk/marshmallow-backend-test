package uk.liamp.cleaner.DTO;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CleanerRequestDTOTests {
	private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Test
    void dtoValidation_ValidInput() {
    	
		int[] areaSize = new int[] {5,5};
		int[] startingPosition = new int[] {1,2};
		
		List<List<Integer>> oilPatches = new ArrayList<>();
		oilPatches.add(List.of(1,0));
		oilPatches.add(List.of(2,2));
		oilPatches.add(List.of(2,3));
		
		String navigationInstructions = "NNESEESWNWW";
		
		CleanerRequestDTO request = new CleanerRequestDTO();
		request.setAreaSize(areaSize);
		request.setStartingPosition(startingPosition);
		request.setOilPatches(oilPatches);
		request.setNavigationInstructions(navigationInstructions);  	

        Set<ConstraintViolation<CleanerRequestDTO>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }
    
    @Test
    void dtoValidation_InvalidAreaSize() {
    	int[] areaSize = new int[] {5,5,5};
		int[] startingPosition = new int[] {1,2};
		
		List<List<Integer>> oilPatches = new ArrayList<>();
		oilPatches.add(List.of(1,0));
		oilPatches.add(List.of(2,2));
		oilPatches.add(List.of(2,3));
		
		String navigationInstructions = "NNESEESWNWW";
		
		CleanerRequestDTO request = new CleanerRequestDTO();
		request.setAreaSize(areaSize);
		request.setStartingPosition(startingPosition);
		request.setOilPatches(oilPatches);
		request.setNavigationInstructions(navigationInstructions);
    	
        Set<ConstraintViolation<CleanerRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }
    
    @Test
    void dtoValidation_MissingStartingPosition() {
    	int[] areaSize = new int[] {5,5,5};
		
		List<List<Integer>> oilPatches = new ArrayList<>();
		oilPatches.add(List.of(1,0));
		oilPatches.add(List.of(2,2));
		oilPatches.add(List.of(2,3));
		
		String navigationInstructions = "NNESEESWNWW";
		
		CleanerRequestDTO request = new CleanerRequestDTO();
		request.setAreaSize(areaSize);
		request.setOilPatches(oilPatches);
		request.setNavigationInstructions(navigationInstructions);    	

        Set<ConstraintViolation<CleanerRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }
    
    @Test
    void dtoValidation_InvalidOilPatchSize() {
    	int[] areaSize = new int[] {5,5};
		int[] startingPosition = new int[] {1,2};
		
		List<List<Integer>> oilPatches = new ArrayList<>();
		oilPatches.add(List.of(1,0));
		oilPatches.add(List.of(2,2));
		oilPatches.add(List.of(2,3,4,5,6));
		
		String navigationInstructions = "NNESEESWNWW";
		
		CleanerRequestDTO request = new CleanerRequestDTO();
		request.setAreaSize(areaSize);
		request.setStartingPosition(startingPosition);
		request.setOilPatches(oilPatches);
		request.setNavigationInstructions(navigationInstructions);
    	
        Set<ConstraintViolation<CleanerRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }
}
