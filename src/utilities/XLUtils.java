package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	Workbook wb;
	Sheet ws;
	Row row;
	Cell cell;
	CellStyle style;
	public XLUtils() throws Throwable{
		FileInputStream fi=new FileInputStream("G:\\Practice\\OHRM\\TestInput\\TestInput.xlsx");
		wb=new XSSFWorkbook(fi);
	}
	public int rowCount(String sheetname){
		return wb.getSheet(sheetname).getLastRowNum();	
	}
	public int colCount(String sheetname,int rownum){
		return wb.getSheet(sheetname).getRow(rownum).getLastCellNum();
	}
	@SuppressWarnings("deprecation")
	public String getCellData(String sheetname,int rownum,int columnnum){
		String data="";
		if((wb.getSheet(sheetname).getRow(rownum).getCell(columnnum)).getCellType()==Cell.CELL_TYPE_NUMERIC){
			int celldata=(int) wb.getSheet(sheetname).getRow(rownum).getCell(columnnum).getNumericCellValue();
			data=String.valueOf(celldata);
		}
		else
		{
			data=wb.getSheet(sheetname).getRow(rownum).getCell(columnnum).getStringCellValue();
		}
		return data;
	}
	public void setCellData(String sheetname,int rownum,int column,String status) throws Throwable{
		ws=wb.getSheet(sheetname);
		row=ws.getRow(rownum);
		cell=row.createCell(column);
		cell=row.getCell(column);
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass")){
			style=wb.createCellStyle();
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		Font font=wb.createFont();
		font.setBold(true);
		style.setFont(font);
		cell.setCellStyle(style);
		}else if(status.equalsIgnoreCase("Fail")){
			style=wb.createCellStyle();
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		Font font=wb.createFont();
		font.setBold(true);
		style.setFont(font);
		cell.setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("blocked")){
			style=wb.createCellStyle();
			style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		Font font=wb.createFont();
		font.setBold(true);
		style.setFont(font);
		cell.setCellStyle(style);
		}
		FileOutputStream fo=new FileOutputStream("G:\\Practice\\OHRM\\TestOutput\\DataDriven.xlsx");
		wb.write(fo);
		fo.close();
	    }
		
	}

