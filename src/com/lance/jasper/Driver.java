package com.lance.jasper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lance.jasper.model.ReportParameters;
import com.lance.jasper.model.Superhero;
import com.lance.jasper.service.SuperheroLoader;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Driver {

	public static void main(String[] args) throws JRException, UnsupportedEncodingException, FileNotFoundException, IOException {

		List<Superhero> superheroes = SuperheroLoader.load();

//		JSONObject json = new JSONObject(superheroes.get(0));
//		try (Writer writer = new BufferedWriter(
//				new OutputStreamWriter(new FileOutputStream("superhero.json"), "utf-8"))) {
//			writer.write(json.toString());
//		}

		ReportParameters reportParameters = new ReportParameters();
		reportParameters.setReportDate(new Date());
		reportParameters.setName("Super Report");
		reportParameters.setStuff(Arrays.asList("Dog", "Cat", "Hippo"));
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("ReportParam", reportParameters);
		// creates a pdf report. unlike below, this saves the jasper print obj
		// to memory. the in-memory object is used during the actual export
		// step.
//		String reportName = "secondReport";
//		JasperReport report = JasperCompileManager.compileReport("superheroes.jrxml");
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(superheroes);
		JasperPrint jasperPrint = JasperFillManager.fillReport("reports/sh/super.jasper", parameters, jrDataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint, "superhero.pdf");

	}

}
