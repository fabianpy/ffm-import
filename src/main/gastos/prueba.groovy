package gastos

import util.ExcelBuilder

/**
 * Created by Fabian on 14/08/2016.
 */


//try {
//
//    new ExcelBuilder("Balance FFM 2016.xls").eachLine(labels: true) {
//
//        if (it.rowNum > 5 && it.rowNum < 109 )  {
//            println("primera columna de la fila ${it.rowNum} = ${it.getCell(1)} ")
//        }
//
//    }
//} catch (Exception e) {
//    e.printStackTrace()
//}


//workbook.getSheets().each {sheet->
//    //???
//    sheet.eachWithIndex {row, index->
//        if (index>0) {//?????????
//            def cells = row.physicalNumberOfCells;//????
//
//            String resId = "";
//            def date = "";
//            String name = "";
//
//            resId = getCellVal(row.getCell(0)).toString();
//
//            name = getCellVal(row.getCell(1)).toString();
//            date = getDateCellVal(row.getCell(5)).toString();
//
//            println resId + "," + name + "," + date;
//        }
//    }
//}

//

import org.apache.poi.hssf.usermodel.*

HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream('Balance FFM 2016.xls'))
(0..<wb.numberOfSheets).each{
    HSSFSheet sheet = wb.getSheetAt(it)
    int rows = sheet.physicalNumberOfRows
    println "Sheet " + it + " \"" + wb.getSheetName(it) + "\" has " + rows + " row(s)."
    (7..<rows).each{r->
        HSSFRow row = sheet.getRow(r)
        if (row != null) {
            int cells = row.physicalNumberOfCells
            println "\nROW " + row.rowNum + " has " + cells + " cell(s)."
//            (0..<cells).each{c->
//                HSSFCell cell = row.getCell(c)
//                println("Cell col= ${cell.columnIndex}  value = ${cell.numericCellValue} ")
//                //println ("CELL col= ${cell.columnIndex} VALUE=".concat(new String(cell.stringCellValue)))
//            }
        }
    }
}