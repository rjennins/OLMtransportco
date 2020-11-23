package com.olmtransportco.ships.model;

import java.math.BigDecimal;
import java.util.Date;

public class Ship {

	private int id;
	private Date built;
	private String name;
	private BigDecimal lengthMeters;
	private BigDecimal beamMeters;
	private int maxTEU;
	private String owner;
	private int grossTonnage;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getBuilt() {
		return built;
	}
	public void setBuilt(Date built) {
		this.built = built;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getLengthMeters() {
		return lengthMeters;
	}
	public void setLengthMeters(BigDecimal lengthMeters) {
		this.lengthMeters = lengthMeters;
	}
	public BigDecimal getBeamMeters() {
		return beamMeters;
	}
	public void setBeamMeters(BigDecimal beamMeters) {
		this.beamMeters = beamMeters;
	}
	public int getMaxTEU() {
		return maxTEU;
	}
	public void setMaxTEU(int maxTEU) {
		this.maxTEU = maxTEU;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getGrossTonnage() {
		return grossTonnage;
	}
	public void setGrossTonnage(int grossTonnage) {
		this.grossTonnage = grossTonnage;
	}
}
