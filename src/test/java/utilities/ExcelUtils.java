package utilities;
 
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ExcelUtils {

	public String getCellData(String file,String sheet,int rownum,int cellnum) throws IOException

	{

		String data;

		FileInputStream fin=new FileInputStream(file);

		XSSFWorkbook wb=new XSSFWorkbook(fin);

		XSSFSheet sh=wb.getSheet(sheet);

		XSSFRow row=sh.getRow(rownum);

		XSSFCell cell=row.getCell(cellnum);

		DataFormatter dat=new DataFormatter();

		data=dat.formatCellValue(cell);

		return data;

	}

	public int getCellCount(String file,String sheet,int rownum) throws IOException

	{

		FileInputStream fin=new FileInputStream(file);

		XSSFWorkbook wb=new XSSFWorkbook(fin);

		XSSFSheet sh=wb.getSheet(sheet);

		XSSFRow row=sh.getRow(rownum);

		int cell=row.getLastCellNum();


		return cell;

	}
 
}

 