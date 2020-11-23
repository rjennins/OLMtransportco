package com.olmtransportco.ships.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.olmtransportco.ships.exception.OLMtransportcoException;
import com.olmtransportco.ships.model.Ship;
import com.olmtransportco.ships.persistence.DAO;

class DAOTest {
	
	@Test
	public void getShipsTest() {
		try {
			DAO dao = new DAO();
			
			List<Ship> shipList = dao.getShips();
			
			assertEquals("OOCL Hong Kong", shipList.get(0).getName());
			
		} catch (OLMtransportcoException e) {
			fail();
		}
	}
	
	@Test
	void getShipByOwnerTest() {
		try {
			DAO dao = new DAO();
			
			List<Ship> shipList = dao.getShipByOwner("Hapag Lloyd (Germany)");
			
			assertEquals("Hapag Lloyd (Germany)", shipList.get(0).getOwner());
			
		} catch (OLMtransportcoException e) {
			fail();
		}
	}
		
	@Test
	void getShipByIdTest() {
		try {
			DAO dao = new DAO();
			
			Ship ship = dao.getShipById(3);
			
			assertEquals("OOCL Japan", ship.getName());
			
		} catch (OLMtransportcoException e) {
			fail();
		}
	}

	@Test
	void deleteShipByIdTest() {
		try {
			DAO dao = new DAO();
			Ship ship = dao.getShipById(3);
			assertNotEquals(null, ship);			
			dao.deleteShipById(3);
			ship = dao.getShipById(3);
			assertEquals(null, ship);			
		} catch (OLMtransportcoException e) {
			fail();
		}		
	}
}
