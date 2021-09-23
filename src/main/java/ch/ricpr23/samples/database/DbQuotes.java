package ch.ricpr23.samples.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import ch.ricpr23.samples.database.data.Quote;
import ch.ricpr23.samples.database.data.Security;
import ch.ricpr23.samples.database.util.DateUtil;

public class DbQuotes extends DbAbs {

	private String TABLE = "quotesAdj";

	public DbQuotes(MysqlDb db) {
		super(db);
	}
	
	public Quote getFirstQuoteFromDb(Security security) throws SQLException {
	    return doGetFirstQuoteFromDb(security.getSymbol());
	}
	
	public Quote doGetFirstQuoteFromDb(String symbol) throws SQLException {
		Quote lastQuote = null;
		ResultSet rs = null;
	    try {
	    	String sql = "select symbol, date, open, high, low, close, closeAdj, volume, dividend, split from "+TABLE+" where symbol = '"+symbol+"' order by date limit 1";
	        rs = db.query(sql);
	        if (rs.next()) {
            	LocalDate date = DateUtil.getLocalDate(rs.getDate("date"));
				double open = rs.getDouble("open");
				double high = rs.getDouble("high");
				double low = rs.getDouble("low");
				double close = rs.getDouble("close");
				double closeAdj = rs.getDouble("closeAdj");
				long volume = rs.getLong("volume");
				Double dividend = rs.getDouble("dividend");
				Double split = rs.getDouble("split");
				lastQuote = new Quote(symbol, date, open, high, low, close, closeAdj, volume, dividend, split);
	        }
	    } finally {
	    	if (rs != null) {
	    		rs.close();
	    	}
	    }
	    return lastQuote;
	}
	
	public Quote getLastQuoteFromDb(Security security) throws SQLException {
	    return doGetLastQuoteFromDb(security.getSymbol());
	}

	public Quote doGetLastQuoteFromDb(String symbol) throws SQLException {
		Quote lastQuote = null;
		ResultSet rs = null;
	    try {
	    	String sql = "select symbol, date, open, high, low, close, closeAdj, volume, dividend, split from "+TABLE+" where symbol = '"+symbol+"' order by date desc limit 1";
	        rs = db.query(sql);
	        if (rs.next()) {
            	LocalDate date = DateUtil.getLocalDate(rs.getDate("date"));
				Double open = rs.getDouble("open");
				Double high = rs.getDouble("high");
				Double low = rs.getDouble("low");
				double close = rs.getDouble("close");
				double closeAdj = rs.getDouble("closeAdj");
				Long volume = rs.getLong("volume");
				Double dividend = rs.getDouble("dividend");
				Double split = rs.getDouble("split");
				lastQuote = new Quote(symbol, date, open, high, low, close, closeAdj, volume, dividend, split);
	        }
	    } finally {
	    	if (rs != null) {
	    		rs.close();
	    	}
	    }
	    return lastQuote;
	}

	public void storeQuote(Quote quote) throws SQLException {
		log.info(quote.toString());
    	String sql = "insert into quotes(symbol, date, open, high, low, close, volume) values ('"+
			quote.getSymbol()+"', '"+quote.getDateDbStr()+"', "+quote.getOpen()+", "+quote.getHigh()+", "+
			quote.getLow()+", "+quote.getClose()+", "+quote.getVolume()+")";
		db.update(sql);
	}	

	public void storeQuoteRaw(Quote quote) throws SQLException {
    	String sql = "insert into quotes(symbol, date, open, high, low, close, volume, exDividend, splitRatio) values ('"+
			quote.getSymbol()+"', '"+quote.getDateDbStr()+"', "+quote.getOpen()+", "+quote.getHigh()+", "+
			quote.getLow()+", "+quote.getClose()+", "+quote.getVolume()+", "+quote.getDividend()+", "+quote.getSplit()+")";
		db.update(sql);
	}

	public void storeQuotes(String data) throws SQLException {
    	String sql = "insert into quotes(symbol, date, open, high, low, close, volume) values "+data+";";
		db.update(sql);
	}	
	
	public void storeQuotesAdj(String symbol, String adjData) throws SQLException {
    	String sql = "delete from quotes_adj where symbol='"+symbol+"'";
		db.update(sql);
		sql = "insert into quotes_adj(symbol, date, open, high, low, close, volume) values "+adjData+";";
		db.update(sql);
	}

	public void storeQuotesExt(String data) throws SQLException {
    	String sql = "insert into "+TABLE+"(symbol, date, open, high, low, close, closeAdj, volume, dividend, split) values "+data+";";
    	db.update(sql);
	}	
	
	public void storeQuoteAdj(Quote quote) throws SQLException {
		log.info(quote.toString());
    	String sql = "insert into quotesAdj(symbol, date, open, high, low, close, volume) values ('"+
			quote.getSymbol()+"', '"+quote.getDateDbStr()+"', "+quote.getOpen()+", "+quote.getHigh()+", "+
			quote.getLow()+", "+quote.getClose()+", "+quote.getVolume()+")";
		db.update(sql);
	}	
}

