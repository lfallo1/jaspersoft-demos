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
	
	/**
	 * Jaspersoft Studio Download
	 * http://community.jaspersoft.com/project/jaspersoft-studio
	 * @param args
	 * @throws JRException
	 * @throws DRException
	 */
	
	public static void main(String[] args) throws JRException, DRException {
		
		//create a dummy list for the datasource, and add one dummy parameter
		List<User> users = new ArrayList<>();
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("RAND", "parrot");
		users.add(new User(1,"lance","lance@gmail.com"));
		users.add(new User(2,"harry","harry@gmail.com"));
		
		//compile jrxml
		JasperReport report = JasperCompileManager.compileReport("firstReport.jrxml");
		
		//create datasource (java bean list)
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(users);
		
		//fill the compiled report with parameters and datasource
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, jrDataSource);
		
		//export the report to pdf
		JasperExportManager.exportReportToPdfFile(jasperPrint, "JasperReport.pdf");
	}

}
