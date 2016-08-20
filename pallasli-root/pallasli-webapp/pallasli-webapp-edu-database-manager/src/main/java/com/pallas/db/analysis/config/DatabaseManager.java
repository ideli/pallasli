package com.pallas.db.analysis.config;

public interface DatabaseManager {

	public void saveDatabase(Database db);

	public void addDatabase(Database db);

	public void alterDatabase(Database db);

	public void deleteDatabase(Database db);
}
