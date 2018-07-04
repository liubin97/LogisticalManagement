package com.neuedu.model.po.user;

import java.util.Date;

public class Order {

    //共20个属性
    private int order_id;
    private int client_id;
    private int status;  //1-缺货 2-已到货 3-可分配 4-已调度 5-已完成 6-失败
    private int prod_id;
    private String prod_name;
    private int amount;
    private String unit;  //计量单位
    private double price;
    private double discount;
    private double sum_monney;
    private java.sql.Date finish_date;  //要求完成日期
    private java.sql.Date create_date;  //创建日期
    private int type;          //1-新订 2-退订 3-退货
    private String deliver_addr;
    private String consignee;   //收件人
    private String consignee_tel;   //收件人电话
    private String consignee_zip_cod;  //收件人邮编
    private int invoice;     //是否要发票 1-要 0-不要
    private int operator;   //操作员id
    private java.sql.Date operatorDate;

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", client_id=" + client_id +
                ", status=" + status +
                ", prod_id=" + prod_id +
                ", prod_name='" + prod_name + '\'' +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", sum_monney=" + sum_monney +
                ", finish_date=" + finish_date +
                ", create_date=" + create_date +
                ", type=" + type +
                ", deliver_addr='" + deliver_addr + '\'' +
                ", consignee='" + consignee + '\'' +
                ", consignee_tel='" + consignee_tel + '\'' +
                ", consignee_zip_cod='" + consignee_zip_cod + '\'' +
                ", invoice=" + invoice +
                ", operator=" + operator +
                ", operatorDate=" + operatorDate +
                '}';
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getSum_monney() {
        return sum_monney;
    }

    public void setSum_monney(double sum_monney) {
        this.sum_monney = sum_monney;
    }

    public java.sql.Date getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(java.sql.Date finish_date) {
        this.finish_date = finish_date;
    }

    public java.sql.Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(java.sql.Date create_date) {
        this.create_date = create_date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDeliver_addr() {
        return deliver_addr;
    }

    public void setDeliver_addr(String deliver_addr) {
        this.deliver_addr = deliver_addr;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsignee_tel() {
        return consignee_tel;
    }

    public void setConsignee_tel(String consignee_tel) {
        this.consignee_tel = consignee_tel;
    }

    public String getConsignee_zip_cod() {
        return consignee_zip_cod;
    }

    public void setConsignee_zip_cod(String consignee_zip_cod) {
        this.consignee_zip_cod = consignee_zip_cod;
    }

    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public java.sql.Date getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(java.sql.Date operatorDate) {
        this.operatorDate = operatorDate;
    }
}
