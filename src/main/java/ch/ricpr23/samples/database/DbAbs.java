package ch.ricpr23.samples.database;

import java.util.logging.Logger;

public abstract class DbAbs {
	protected Logger log = Logger.getLogger("upbias");

	protected MysqlDb db;
	
	public DbAbs(MysqlDb db) {
		this.db = db;
	}
}
