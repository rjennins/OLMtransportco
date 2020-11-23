package com.olmtransportco.ships.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olmtransportco.ships.exception.OLMtransportcoException;
import com.olmtransportco.ships.model.Ship;

public class JSONProcessor {
	
	final static Logger logger = LogManager.getLogger(JSONProcessor.class);

	/**
	 * Loads the in memory database from the ships json file
	 * @param shipsJsonFileName
	 * @return list of data of ships
	 * @throws OLMtransportcoException
	 */
	public List<Ship> parseShipsJSON(String shipsJsonFileName) throws OLMtransportcoException {

		List<Ship> shipList = new ArrayList<Ship>();

		byte[] jsonData = null;		
		try {
			InputStream inputStream = getClass().getResourceAsStream("/data/" + shipsJsonFileName);	
			jsonData = new byte[inputStream.available()];
			inputStream.read(jsonData);
		} catch (IOException ioe) {
			logger.error("Could not open file " + shipsJsonFileName, ioe);
			throw new OLMtransportcoException("Could not open file " + shipsJsonFileName, ioe);
		}

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode rootNode = null;
		try {
			rootNode = objectMapper.readTree(new String(jsonData, "UTF8"));
		} catch (IOException ioe) {
			logger.error("Could not open root node " + shipsJsonFileName, ioe);
			throw new OLMtransportcoException("Could not root node " + shipsJsonFileName, ioe);
		}

		JsonNode shipsNode = rootNode.get("ships");

		Iterator<JsonNode> shipNodes = shipsNode.elements();

		while (shipNodes != null && shipNodes.hasNext()) {
			JsonNode shipNode = shipNodes.next();
			Ship ship = getShip(shipNode);
			if (ship != null) {
				shipList.add(ship);
			}
		}
		return shipList;
	}

	/**
	 * The the content of the given file in bytes
	 * 
	 * @param file
	 * @return file content
	 * @throws OLMtransportcoException
	 */
	protected byte[] readFileToByteArray(File file) throws OLMtransportcoException {
		FileInputStream fis = null;
		byte[] bArray = new byte[(int) file.length()];
		try {
			fis = new FileInputStream(file);
			fis.read(bArray);
			fis.close();

		} catch (IOException ioe) {
			logger.error("Could not read file", ioe);
			throw new OLMtransportcoException("Could not read file " + file.getName(), ioe);
		}
		return bArray;
	}

	/**
	 * Gets the ship information from the given node
	 * 
	 * @param shipNode
	 * @return
	 * @throws OLMtransportcoException
	 */
	protected Ship getShip(JsonNode shipNode) throws OLMtransportcoException {
		Ship ship = new Ship();

		SimpleDateFormat sdf = new SimpleDateFormat("YYYY");

		JsonNode idNode = shipNode.get("id");
		if (idNode != null) {
			ship.setId(idNode.asInt());
		}

		JsonNode builtNode = shipNode.get("built");
		if (builtNode != null) {
			String builtDateAsString = builtNode.asText();
			if (builtDateAsString != null) {
				try {
					ship.setBuilt(sdf.parse(builtDateAsString));
				} catch (ParseException pe) {
					logger.error("Could not open parse ship node ", pe);
					throw new OLMtransportcoException("Could not parse ship node ", pe);
				}
			}
		}

		JsonNode nameNode = shipNode.get("name");
		if (nameNode != null) {
			ship.setName(nameNode.asText());
		}

		JsonNode lengthMetersNode = shipNode.get("lengthMeters");
		if (lengthMetersNode != null) {
			ship.setLengthMeters(new BigDecimal(lengthMetersNode.asText()));
		}

		JsonNode beamMetersNode = shipNode.get("beamMeters");
		if (beamMetersNode != null) {
			ship.setBeamMeters(new BigDecimal(beamMetersNode.asText()));
		}

		JsonNode maxTEUNode = shipNode.get("maxTEU");
		if (maxTEUNode != null) {
			ship.setMaxTEU(maxTEUNode.asInt());
		}

		JsonNode ownerNode = shipNode.get("owner");
		if (ownerNode != null) {
			ship.setOwner(ownerNode.asText());
		}

		JsonNode grossTonnageNode = shipNode.get("grossTonnage");
		if (grossTonnageNode != null) {
			ship.setGrossTonnage(grossTonnageNode.asInt());
		}
		return ship;
	}
}
