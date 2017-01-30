import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Driver {
	
	private static final String REPORTS_FOLDER = "reports/";
	private static final String OUTPUT_FOLDER = "C:\\Users\\lfallon\\Desktop\\";
	
	/**
	 * Install the Jaspersoft Studio Eclipse plugin
	 * 
	 * Alternatively, could install Jaspersoft Studio as a standalone application from
	 * http://community.jaspersoft.com/project/jaspersoft-studio
	 * @param args
	 * @throws JRException
	 * @throws DRException
	 */
	
	public static void main(String[] args) throws JRException, DRException {
		
//		JRDataSource jrDataSource = new JRBeanCollectionDataSource(list);
//		String jasperPrint = JasperFillManager.fillReportToFile(jasperMasterReportFile, parameters, jrDataSource);
//		StringBuilder filePath = new StringBuilder(appConfig.getReportFileSavePath());
//		if (PDF.equalsIgnoreCase(reportType)) {
//			fileName.append(DOT).append(PDF);
//			JasperExportManager.exportReportToPdfFile(jasperPrint,
//					filePath.toString() + fileName.toString());
//			return fileName.toString();
		
		//create a dummy list for the datasource, and add one dummy parameter
		List<User> users = new ArrayList<>();
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("RAND", "parrot");
		users.add(new User(1,"lance","lance@gmail.com"));
		users.add(new User(2,"harry","harry@gmail.com"));
		
		//creates a pdf report. unlike below, this saves the jasper print obj to memory. the in-memory object is used during the actual export step.
		String reportName = "secondReport";		
		JasperReport report = JasperCompileManager.compileReport(REPORTS_FOLDER + reportName + ".jrxml");
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(users);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, jrDataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint, OUTPUT_FOLDER + reportName + ".pdf");
		
		//create a report and export to pdf.
		//this method saves the jasper print obj to disk, instead of in-memory.
		//like above the exported file itself is saved to disk.
		List<Department> dpts = new ArrayList<>();
		dpts.add(new Department(1,"Sales"));
		dpts.add(new Department(2,"HR"));
		dpts.add(new Department(3,"Production"));
		dpts.add(new Department(4,"Development"));
		dpts.add(new Department(5,"IT"));
		dpts.add(new Department(6,"Marketing"));
		JRDataSource jrDataSource2 = new JRBeanCollectionDataSource(dpts);
		String jasperLocation = REPORTS_FOLDER + "foodmart.jasper";
		String jasperPrint2 = JasperFillManager.fillReportToFile(jasperLocation, parameters, jrDataSource2);
		JasperExportManager.exportReportToPdfFile(jasperPrint2, OUTPUT_FOLDER + "departments.pdf");		
	}

}
