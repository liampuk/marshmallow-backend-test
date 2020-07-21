package uk.liamp.cleaner.DTO;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import uk.liamp.cleaner.Helper.Coordinate;

/**
 * This class validates the JSON request, and converts the data into a Coordinate
 * class where applicable.
 * @author liampuk
 */
@Component
public class CleanerRequestDTO {
	
	@NotEmpty(message = "Please provide areaSize")
	@Size(min = 2, max = 2, message = "areaSize must be 2 values")
	private int[] areaSize;
	
	@NotEmpty(message = "Please provide startingPosition")
	@Size(min = 2, max = 2, message = "startingPosition must be 2 values")
	private int[] startingPosition;
	
	/**
	 * Nested lists are used to enable validation to function properly (not possible with arrays)
	 */
	@NotNull(message = "Please provide oilPatches")
	List<@Size(min = 2, max = 2, message = "oilPatches must contain arrays of 2 values") List<Integer>> oilPatches;
	
	@NotNull(message = "Please provide navigationInstructions")
	String navigationInstructions;
	
	/**
	 * Coordinate versions of data received from the request. This makes it easier to
	 * perform logic in the service class.
	 */
	
	private Coordinate areaSizeCoordinate;
	private Coordinate startingPositionCoordinate;
	private List<Coordinate> oilPatchesCoordinates = new ArrayList<>();

	public int[] getAreaSize() {
		return areaSize;
	}

	/**
	 * Coordinate variables are updated whenever data is received.
	 */
	public void setAreaSize(int[] areaSize) {
		this.areaSize = areaSize;
		areaSizeCoordinate = new Coordinate(areaSize);
	}

	public int[] getStartingPosition() {
		return startingPosition;
	}

	public void setStartingPosition(int[] startingPosition) {
		this.startingPosition = startingPosition;
		startingPositionCoordinate = new Coordinate(startingPosition);
	}

	public List<List<Integer>> getOilPatches() {
		return oilPatches;
	}

	public void setOilPatches(List<List<Integer>> oilPatches) {
		this.oilPatches = oilPatches;
		for(List<Integer> oilPatch : this.oilPatches){
			oilPatchesCoordinates.add(new Coordinate(oilPatch.get(0), oilPatch.get(1)));
		}
	}

	public String getNavigationInstructions() {
		return navigationInstructions;
	}

	public void setNavigationInstructions(String navigationInstructions) {
		this.navigationInstructions = navigationInstructions;
	}

	public Coordinate getAreaSizeCoordinate() {
		return areaSizeCoordinate;
	}

	public Coordinate getStartingPositionCoordinate() {
		return startingPositionCoordinate;
	}

	public List<Coordinate> getOilPatchesCoordinates() {
		return oilPatchesCoordinates;
	}
}
