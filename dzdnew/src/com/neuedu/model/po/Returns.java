package com.neuedu.model.po;

import java.sql.Date;

public class Returns {

    private int id;
    private int order_id;
    private int amount;
    private int prod_id;
    private java.sql.Date finish_date;
    private java.sql.Date create_date;
    private java.sql.Date return_time;
    private String reason;
    private int operator;   //操作员id
    private java.sql.Date operatorDate;

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(Date finish_date) {
        this.finish_date = finish_date;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getReturn_time() {
        return return_time;
    }

    public void setReturn_time(Date refund_time) {
        this.return_time = refund_time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public Date getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
    }

    @Override
    public String toString() {
        return "Returns{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", amount=" + amount +
                ", finish_date=" + finish_date +
                ", create_date=" + create_date +
                ", refund_time=" + return_time +
                ", reason='" + reason + '\'' +
                ", operator=" + operator +
                ", operatorDate=" + operatorDate +
                '}';
    }
}
