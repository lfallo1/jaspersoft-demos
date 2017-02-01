package com.lance.files;

import net.sf.jasperreports.engine.JRDataSource;

public class GetFileDataSource {

	public static JRDataSource getCustomFileDS(){
		return new FileDataSource("C://Users/lfallon");
	}
	
}
