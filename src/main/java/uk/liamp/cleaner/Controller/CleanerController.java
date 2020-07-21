package uk.liamp.cleaner.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uk.liamp.cleaner.DTO.CleanerRequestDTO;
import uk.liamp.cleaner.DTO.CleanerResponseDTO;
import uk.liamp.cleaner.Service.CleanerService;

/**
 * Controller class that handles post requests to the api.
 * Requests are validated using the CleanerRequestDTO, and responses are built
 * in the CleanerService class and returned using the CleanerResponseDTO.
 * @author liampuk
 */
@RestController
public class CleanerController {

	@Autowired
	CleanerService cleanerService;

	@PostMapping(value = "/cleaner", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CleanerResponseDTO> cleaner(@Valid @RequestBody CleanerRequestDTO request) {
		CleanerResponseDTO response = cleanerService.clean(request);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
