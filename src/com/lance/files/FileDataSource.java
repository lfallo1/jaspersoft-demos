package com.lance.files;

import java.io.File;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class FileDataSource implements JRDataSource{

	private File[] files;
	int index = -1;
	
	public FileDataSource (String path){
		File directory = new File(path);
		if(directory.exists() && directory.isDirectory()){
			files = directory.listFiles();
		}
	}

	@Override
	public boolean next() throws JRException {
		index++;
		if(files != null && index < files.length){
			return true;
		}
		return false;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		if(files != null && files[index] != null){
			File file = files[index];
			if(jrField.getName().equals("name")){
				return  file.getName();
			} else if(jrField.getName().equals("IS_DIRECTORY")){
				return file.isDirectory();
			} else if(jrField.getName().equals("size")){
				return file.length();
			}
		}
		return null;
	}
	
}
