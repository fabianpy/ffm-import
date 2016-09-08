package gastos

import beans.MovimientoBean
import groovy.sql.Sql
import org.apache.poi.hssf.usermodel.*

import java.sql.Date


Sql salida = Sql.newInstance('jdbc:postgresql://localhost:5432/gastos_db?ApplicationName=groovyConsole', 'postgres', 'OscProdPost2014', 'org.postgresql.Driver')
salida.execute("BEGIN TRANSACTION;")

Integer ANHO = 2016
Integer[] CANTIDAD_ROWS_BY_SHEET = [111, 69, 110, 111, 112, 89, 120 ]

try {


    HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream('Balance FFM 2016.xls'))
    (0..<wb.numberOfSheets -1).each{ //HASTA JULIO
        HSSFSheet sheet = wb.getSheetAt(it)
        //int rows = sheet.physicalNumberOfRows
        int rows = CANTIDAD_ROWS_BY_SHEET[it]
        println "Sheet " + it + " \"" + wb.getSheetName(it) + "\" has " + rows + " row(s)."
        (6..<rows).each{r->
            HSSFRow row = sheet.getRow(r)
            if (row != null) {
                String fecha = ANHO.toString().concat("-").concat((it+1).toString()).concat("-").concat(new Integer(row.getCell(1).numericCellValue.toInteger()).toString())
                movimiento = new MovimientoBean()
                movimiento.setFecha(java.sql.Date.valueOf(fecha))
                movimiento.setMonto(new BigDecimal(row.getCell(4).numericCellValue))
                movimiento.setConcepto(row.getCell(3).stringCellValue) //TODO insertar conceptos aparte

//                println("inserta fecha: ${movimiento.getFecha()} (${fecha}) (${ANHO} , ${it+1} , ${row.getCell(1).numericCellValue.toInteger()}) ; monto : ${movimiento.getMonto()} ; concepto : ${movimiento.getConcepto()} ")
                salida.execute("insert into tmp_movimiento (fecha, concepto, monto) values ( ${movimiento.getFecha()}, ${movimiento.getConcepto()}, ${movimiento.getMonto()} )")


//                movimiento = new MovimientoBean()
//                movimiento.setFecha(new Date(ANHO, (it+1), row.getCell(1).numericCellValue.toInteger()))
//                movimiento.setMonto(new BigDecimal(row.getCell(4).numericCellValue))
//                movimiento.setConcepto(row.getCell(3).stringCellValue) //TODO insertar conceptos aparte
//
//                salida.execute("insert into tmp_movimiento (fecha, concepto, monto) values ( ${movimiento.getFecha()}, ${movimiento.getConcepto()}, ${movimiento.getMonto()} )")

//                int cells = row.physicalNumberOfCells
//                println "ROW " + row.rowNum + " has " + cells + " cell(s)."
//                (0..<cells).each{c->
//
//                    HSSFCell cell = row.getCell(c)
//                    switch (c){
//                        case 1: //dia numero
//                            println("Cell col= ${cell.columnIndex}  value = ${cell.numericCellValue} ")
//                            break;
//                        case 2: //dia letra
//                            println("Cell col= ${cell.columnIndex}  value = ${cell.stringCellValue} ")
//                            break;
//                        case 3: //
//                            println("Cell col= ${cell.columnIndex}  value = ${cell.stringCellValue} ")
//                            break;
//                        case 4:
//                            println("Cell col= ${cell.columnIndex}  value = ${cell.numericCellValue} ")
//                            break;
//                    }
                    //HSSFCell cell = row.getCell(c)
                    //println("Cell col= ${cell.columnIndex}  value = ${cell.numericCellValue} ")
                    //println ("CELL col= ${cell.columnIndex} VALUE=".concat(new String(cell.stringCellValue)))
//                }
            }
        }
    }

    salida.execute("commit;")
//    salida.execute("rollback;")
} catch (Exception e) {
    salida.execute("rollback;")
    e.printStackTrace()
}