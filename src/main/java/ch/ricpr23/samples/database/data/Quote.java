package ch.ricpr23.samples.database.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Quote {
	
	private long id;
	private String symbol;
	private LocalDate date;
	private double open;
	private double high;
	private double low;
	private double close;
	private Double closeAdj;
	private Long volume;
	private Double dividend = null;
	private Double split = null;

	private DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	private DateTimeFormatter dfDb = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public Quote(String symbol, LocalDate date, double open, double high, double low, double close, Double closeAdj, Long volume, Double dividend, Double split) {
		this.symbol = symbol;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.closeAdj = closeAdj;
		this.volume = volume;
		this.dividend = dividend;
		this.split = split;
	}

	public Quote(long id, String symbol, LocalDate date, double open, double high, double low, double close, Double closeAdj, Long volume, Double dividend, Double split) {
		this.id = id;
		this.symbol = symbol;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.closeAdj = closeAdj;
		this.volume = volume;
		this.dividend = dividend;
		this.split = split;
	}

	public long getId() {
		return id;
	}

	public String getSymbol() {
		return symbol;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getDateStr() {
		return df.format(date);
	}
	
	public String getDateDbStr() {
		return dfDb.format(date);
	}

	public double getOpen() {
		return open + getAdjustValue();
	}

	public double getHigh() {
		return high + getAdjustValue();
	}

	public double getLow() {
		return low + getAdjustValue();
	}

	public double getClose() {
		return close + getAdjustValue();
	}

	public Double getCloseAdj() {
		return closeAdj;
	}

	public Long getVolume() {
		return volume;
	}

	public Double getDividend() {
		return dividend;
	}

	public Double getSplit() {
		return split;
	}

	public boolean equals(Quote q) {
		if (!date.equals(q.getDate())) {
			return false;
		}
		if (Math.abs(getOpen() - q.getOpen()) > 0.00000001) {
			return false;
		}
		if (Math.abs(getHigh() - q.getHigh()) > 0.00000001) {
			return false;
		}
		if (Math.abs(getLow() - q.getLow()) > 0.00000001) {
			return false;
		}
		if (Math.abs(getClose() - q.getClose()) > 0.00000001) {
			return false;
		}
//		if (Math.abs(getVolume() - q.getVolume()) > 1) {
//			return false;
//		}
		return true;
	}
	
	private double getAdjustValue() {
		return ((closeAdj != null) ? (closeAdj - close) : 0);
	}

	public void adjust(double value) {
		open += value;
		high += value;
		low += value;
		close += value;
	}
	
	private String format(Long number) {
		return (number != null ? String.format("%.0f", number.doubleValue()) : "-");
	}
	
	private String format(Double number) {
		return (number != null ? String.format("%.2f", number) : "-");
	}
	
	public String toDbString() {
		return "('"+symbol+"', '"+dfDb.format(date)+"', "+String.format("%.2f",open)+", "+String.format("%.2f",high)+", "+String.format("%.2f",low)+", "+String.format("%.2f",close)+", "+String.format("%.2f",closeAdj)+", "+format(volume)+", "+format(dividend)+", "+format(split)+")";
	}
	
	@Override
	public String toString() {
		return "Quote(symbol="+symbol+", date="+dfDb.format(date)+", open="+String.format("%.2f",open)+", high="+String.format("%.2f",high)+", low="+String.format("%.2f",low)+", close="+String.format("%.2f",close)+", closeAdj="+String.format("%.2f",closeAdj)+", volume="+format(volume)+", dividend="+format(dividend)+", split="+format(split)+")";
	}
}
