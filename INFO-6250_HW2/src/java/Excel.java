import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chung-Yang Li
 */
public class Excel extends HttpServlet{
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"/store.xls";
        System.out.println(path);

        List<List<String>> excelList = new ArrayList<List<String>>();
        try
        {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream( new File(path)));
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext())
            {
                List<String> sublist = new ArrayList<String>();
                Row row = rowIterator.next();
                DataFormatter dataFormatter = new DataFormatter();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    String cellValue = dataFormatter.formatCellValue(cell);
                    sublist.add(cellValue);
                }
                excelList.add(sublist);
            }
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        request.setAttribute("table", excelList);
        System.out.println(excelList);
        request.getRequestDispatcher("ExcelResult.jsp").forward(request, response);
    }
}
