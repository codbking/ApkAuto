package codbking.bean;

public class FirGetUrlData {
	
	public String id;
	public String type;
	public Cert cert;

	public static class Cert {
		public Bean icon;
		public Bean binary;
		public Mqc mqc;
		public String support;
		public String prefix;
	}

	public static class Bean {
		public String key;
		public String token;
		public String upload_url;
	}
	
	public static class Mqc{
		public int total;
		public int used;
		public String is_mqc_availabled;
	}

}
