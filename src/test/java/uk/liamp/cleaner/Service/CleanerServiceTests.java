package uk.liamp.cleaner.Service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import uk.liamp.cleaner.DTO.CleanerRequestDTO;
import uk.liamp.cleaner.DTO.CleanerResponseDTO;

@SpringBootTest
class CleanerServiceTests {
	
	@Autowired
	private CleanerService cleanerService;
	
	@Test
	void clean_ValidInput() {
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
		
		CleanerResponseDTO response = cleanerService.clean(request);
		assertArrayEquals(response.getFinalPosition(), new int[] {1,3});
		
	}
	
	@Test
	void clean_InvalidCharacter() {
		int[] areaSize = new int[] {5,5};
		int[] startingPosition = new int[] {1,2};
		
		List<List<Integer>> oilPatches = new ArrayList<>();
		oilPatches.add(List.of(1,0));
		oilPatches.add(List.of(2,2));
		oilPatches.add(List.of(2,3));
		
		String navigationInstructions = "fail";
		
		CleanerRequestDTO request = new CleanerRequestDTO();
		request.setAreaSize(areaSize);
		request.setStartingPosition(startingPosition);
		request.setOilPatches(oilPatches);
		request.setNavigationInstructions(navigationInstructions);
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			cleanerService.clean(request);
	    });
	 
	    String expectedMessage = "Invalid navigation instructions - must only contain cardinal directions (N,S,E,W)";
	    String actualMessage = exception.getMessage();
	 
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void clean_OutOfBounds() {
		int[] areaSize = new int[] {5,5};
		int[] startingPosition = new int[] {1,2};
		
		List<List<Integer>> oilPatches = new ArrayList<>();
		oilPatches.add(List.of(1,0));
		oilPatches.add(List.of(2,2));
		oilPatches.add(List.of(2,3));
		
		String navigationInstructions = "SSSSSSSSSS";
		
		CleanerRequestDTO request = new CleanerRequestDTO();
		request.setAreaSize(areaSize);
		request.setStartingPosition(startingPosition);
		request.setOilPatches(oilPatches);
		request.setNavigationInstructions(navigationInstructions);
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			cleanerService.clean(request);
	    });
	 
	    String expectedMessage = "Invalid navigation instructions - cleaner is out of bounds";
	    String actualMessage = exception.getMessage();
	 
	    assertTrue(actualMessage.contains(expectedMessage));
	}

}
