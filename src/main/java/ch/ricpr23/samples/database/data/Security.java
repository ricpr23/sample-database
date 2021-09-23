package ch.ricpr23.samples.database.data;

public class Security implements Comparable<Security> {

	private long id;
	private String symbol;
	private String ticker;
	private String description;
	private String currency;
	private String assetClass;
	private String ext;
	private int autoDownload;
	
	public Security(long id, String symbol, String ticker, String description, String currency, String assetClass, String ext, int autoDownload) {
		this.id = id;
		this.symbol = symbol;
		this.ticker = ticker;
		this.description = description;
		this.currency = currency;
		this.assetClass = assetClass;
		this.ext = ext;
		this.autoDownload = autoDownload;
	}

	public long getId() {
		return id;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getTicker() {
		return ticker;
	}

	public String getDescription() {
		return description;
	}

	public String getCurrency() {
		return currency;
	}

	public String getAssetClass() {
		return assetClass;
	}
	
	public String getExt() {
		return ext;
	}

	public int getAutoDownload() {
		return autoDownload;
	}

	@Override
	public int compareTo(Security o) {
		return symbol.compareTo(o.getSymbol());
	}
	
	@Override
	public String toString() {
		return "Security(id="+id+", symbol="+symbol+", ticker="+ticker+", description="+description+", currency="+currency+", assetClass="+assetClass+", ext="+ext+", autoDownload="+autoDownload+")";
	}
}
