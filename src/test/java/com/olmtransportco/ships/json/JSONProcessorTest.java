package com.olmtransportco.ships.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olmtransportco.ships.json.JSONProcessor;
import com.olmtransportco.ships.model.Ship;

class JSONProcessorTest {

	JSONProcessor jsonProcessor = new JSONProcessor();
	
	@Test
	void parseShipsJSONTest() {
		List<Ship> shipList = null;
		try {
			shipList = jsonProcessor.parseShipsJSON("ships.json");
			assertEquals(165, shipList.size());
			assertEquals("CMA CGM Nevada", shipList.get(164).getName());
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	void getShipTest() {
	
		String jsonArray = "{\"id\":\"1\",\"built\":1980,\"name\":\"OOCL Hong Kong\",\"lengthMeters\":\"399.87\",\"beamMeters\":\"58.8\",\"maxTEU\":\"21413\",\"owner\":\"OOCL (Hong Kong)\",\"grossTonnage\":\"210890\"}";		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
		
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode shipNode = null;
		try {
			shipNode = objectMapper.readTree(jsonArray);
		} catch (IOException ioe) {
			fail();
		}	
		try {
			Ship ship = jsonProcessor.getShip(shipNode);
			assertEquals(1, ship.getId());
			assertEquals(sdf.parse("1980"), ship.getBuilt());
			assertEquals("OOCL Hong Kong", ship.getName());
			assertEquals(new BigDecimal("399.87"), ship.getLengthMeters());
			assertEquals(new BigDecimal("58.8"), ship.getBeamMeters());
			assertEquals(21413, ship.getMaxTEU());
			assertEquals("OOCL (Hong Kong)", ship.getOwner());			
			assertEquals(210890, ship.getGrossTonnage());
		} catch (Exception e) {
			fail();
		}
	}
}
