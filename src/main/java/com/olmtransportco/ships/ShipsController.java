package com.olmtransportco.ships;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olmtransportco.ships.model.Ship;
import com.olmtransportco.ships.persistence.DAO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "findShipDetails")
@RestController
public class ShipsController {

	@Autowired
	private DAO dao;

	/**
	 * Get all the ship data the URL is http:localhost:8080/ships
	 * 
	 * @return full set of ship data
	 */
	@ApiOperation(value = "Get details for all ships")
	@RequestMapping(value = "/ships", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Ship>> ships() {

		try {
			List<Ship> shipList = dao.getShips();
			return ResponseEntity.status(HttpStatus.OK).body(shipList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	/**
	 * Gets the ship data for the given ship id. An example URL is
	 * http:localhost:8080/ship/1
	 * 
	 * @param shipId
	 * @return ship data
	 */
	@RequestMapping(value = "/ship/{shipId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ship> getShipData(@PathVariable Integer shipId) {

		try {
			Ship ship = null;
			ship = dao.getShipById(shipId);
			if (ship != null) {
				return ResponseEntity.status(HttpStatus.OK).body(ship);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);				
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	/**
	 * Get the data for the ships for the given owner. An example URL is
	 * http:localhost:8080/ship?owner=Maersk Line
	 * 
	 * @param owner
	 * @return list of ships for given owner
	 */
	@RequestMapping(value = "/ship", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Ship>> getShipsForOwner(@RequestParam("owner") String owner) {

		try {
			List<Ship> shipList = dao.getShipByOwner(owner);
			return ResponseEntity.status(HttpStatus.OK).body(shipList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	/**
	 * Delete the ship data from the in memory database
	 * 
	 * @param shipId
	 * @return success or failure message
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "ship/{shipId}")
	ResponseEntity<?> deleteShip(@PathVariable int shipId) {
		try {
			dao.deleteShipById(shipId);

			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Data deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
		}
	}
}