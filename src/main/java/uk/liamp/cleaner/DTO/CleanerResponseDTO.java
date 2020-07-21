package uk.liamp.cleaner.DTO;

import org.springframework.stereotype.Component;

/**
 * Response class including final position of the cleaner and the number of oil patches
 * cleaned up.
 * 
 * @author liampuk
 */
@Component
public class CleanerResponseDTO {
	
	private int[] finalPosition;
	private int oilPatchesCleaned;
	
	public int[] getFinalPosition() {
		return finalPosition;
	}
	public void setFinalPosition(int[] finalPosition) {
		this.finalPosition = finalPosition;
	}
	public int getOilPatchesCleaned() {
		return oilPatchesCleaned;
	}
	public void setOilPatchesCleaned(int oilPatchesCleaned) {
		this.oilPatchesCleaned = oilPatchesCleaned;
	}

}
