package com.olmtransportco.ships.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.olmtransportco.ships.exception.OLMtransportcoException;
import com.olmtransportco.ships.json.JSONProcessor;
import com.olmtransportco.ships.model.Ship;

@Service
@Scope("singleton")
public class DAO {
	
	final static Logger logger = LogManager.getLogger(DAO.class);

	Map<Integer, Ship> shipsWithIdKeyMap = new HashMap<Integer, Ship>(); 

    private JSONProcessor jsonProcessor = new JSONProcessor();

	public DAO() throws OLMtransportcoException {
		List<Ship> ships = jsonProcessor.parseShipsJSON("ships.json");
		for (Ship ship : ships) {
			shipsWithIdKeyMap.put(ship.getId(), ship);
		}
	}
	
	/**
	 * Gets the full set of ships
	 * @return list of available ships
	 */
	public List<Ship> getShips() {
		Collection<Ship> c = shipsWithIdKeyMap.values();
		ArrayList<Ship> shipList = new ArrayList<>(c);
		return shipList;
	}	
	
	/**
	 * Gets the ship details for a given ship id
	 * @param ship id
	 * @return ship details
	 */
	public Ship getShipById(int id) {
		Ship retValue = null;
		retValue = shipsWithIdKeyMap.get(id);
		return retValue;
	}

	/**
	 * Deletes the ship for the given id from the ship in memory database
	 * @param ship id
	 */
	public void deleteShipById(int id) {
		shipsWithIdKeyMap.remove(id);
	}
	
	/**
	 * Gets the list of ships for the given owner
	 * @param owner
	 * @return list of ships for given owner
	 */
	public List<Ship> getShipByOwner(String owner) {
		List<Ship> retValue = new ArrayList<Ship>();
		
		Collection<Ship> c = shipsWithIdKeyMap.values();
		Iterator<Ship> it = c.iterator();
		while(it.hasNext()) {
			Ship ship = it.next();
			if (ship.getOwner().equals(owner)) {
				retValue.add(ship);
			}
		}
		return retValue;
	}
}
