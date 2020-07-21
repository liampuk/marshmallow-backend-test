package uk.liamp.cleaner.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import uk.liamp.cleaner.DTO.CleanerRequestDTO;
import uk.liamp.cleaner.DTO.CleanerResponseDTO;
import uk.liamp.cleaner.Exception.InvalidNavigationInstructionsException;
import uk.liamp.cleaner.Helper.Coordinate;

/**
 * Service class that provides the majority of the application logic. The initial location
 * of the ship is set from the request object, and the boat is then moved based on the 
 * navigation instructions. If it moves out of bounds, or an invalid character is present
 * an error is returned.
 * 
 * @author liampuk
 */
@Service
public class CleanerService {
	
	public CleanerResponseDTO clean(CleanerRequestDTO request) {
		CleanerResponseDTO response = new CleanerResponseDTO();
		
		String path = request.getNavigationInstructions().toUpperCase();
		Coordinate location = new Coordinate(request.getStartingPosition());
		List<Coordinate> oilPatches = request.getOilPatchesCoordinates();
		int oilPatchesCleaned = 0;
		
		for(int i=0; i<path.length(); i++){
			// Making use of the Coordinate class to move the boat
			if(path.charAt(i) == 'N') {
				location.goNorth();
			}else if(path.charAt(i) == 'E') {
				location.goEast();
			}else if(path.charAt(i) == 'S') {
				location.goSouth();
			}else if(path.charAt(i) == 'W') {
				location.goWest();
			// If not a cardinal direction, return an error.
			}else {
				throw new InvalidNavigationInstructionsException("Invalid navigation instructions - must only contain cardinal directions (N,S,E,W)");
			}
			// If the boat has moved out of bounds, return an error.
			if(!location.isInbound(request.getAreaSizeCoordinate())) {
				throw new InvalidNavigationInstructionsException("Invalid navigation instructions - cleaner is out of bounds");
			}
			// If the boat has encountered an oil patch, clean it up!
			if(oilPatches.contains(location)) {
				oilPatchesCleaned++;
				oilPatches.remove(oilPatches.indexOf(location));
			}
		}
		
		// Build the response object and return to the Controller.
		response.setFinalPosition(location.getXYArray());
		response.setOilPatchesCleaned(oilPatchesCleaned);
		return response;
	}

}
