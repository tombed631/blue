package pl.project.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Tom on 18.01.2017.
 */


@Entity
@Table(name = "system_contract", schema = "public")
public class SystemContract {

    @Id
    @GeneratedValue
    @Column(name="system_contract_id",nullable = false,unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "systems_id_system",nullable = false)
    private System system;

    @Column(name = "active",nullable = false)
    private Boolean active;

    @Column(name = "amount",nullable = false)
    private Double amount;

    @Column(name = "amount_period",nullable = false)
    private String amountPeriod;

    @Column(name = "amount_type",nullable = false)
    private String amountType;


    @Column(name = "authorization_percent",nullable = false)
    private Double authorizationPercent;

    @Column(name = "from_date",nullable = false)
    private Date fromDate;

    @Column(name = "to_date",nullable = false)
    private Date toDate;

    @Column(name = "request",nullable = false,unique = true)
    private String request;

    @Column(name ="order_number",nullable = false)
    private String orderNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAmountPeriod() {
        return amountPeriod;
    }

    public void setAmountPeriod(String amountPeriod) {
        this.amountPeriod = amountPeriod;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public Double getAuthorizationPercent() {
        return authorizationPercent;
    }

    public void setAuthorizationPercent(Double authorizationPercent) {
        this.authorizationPercent = authorizationPercent;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemContract that = (SystemContract) o;

        if (!id.equals(that.id)) return false;
        if (!system.equals(that.system)) return false;
        if (!active.equals(that.active)) return false;
        if (!amount.equals(that.amount)) return false;
        if (!amountPeriod.equals(that.amountPeriod)) return false;
        if (!amountType.equals(that.amountType)) return false;
        if (!authorizationPercent.equals(that.authorizationPercent)) return false;
        if (!fromDate.equals(that.fromDate)) return false;
        if (!toDate.equals(that.toDate)) return false;
        if (!request.equals(that.request)) return false;
        return orderNumber.equals(that.orderNumber);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + system.hashCode();
        result = 31 * result + active.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + amountPeriod.hashCode();
        result = 31 * result + amountType.hashCode();
        result = 31 * result + authorizationPercent.hashCode();
        result = 31 * result + fromDate.hashCode();
        result = 31 * result + toDate.hashCode();
        result = 31 * result + request.hashCode();
        result = 31 * result + orderNumber.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SystemContract{" +
                "id=" + id +
                ", system=" + system +
                ", active=" + active +
                ", amount=" + amount +
                ", amountPeriod='" + amountPeriod + '\'' +
                ", amountType='" + amountType + '\'' +
                ", authorizationPercent=" + authorizationPercent +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", request='" + request + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                '}';
    }
}
