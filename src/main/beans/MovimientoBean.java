package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Fabian on 14/08/2016.
 */
public class MovimientoBean implements Serializable {
    private Integer id;
    private Date fecha;
    private String concepto;
    private BigDecimal monto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
