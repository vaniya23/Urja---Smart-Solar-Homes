package model;

public class Order extends Product
{
    private int orderId;
    private String uname;
    private String uphone;
    private String uemail;
    private int qunatity;
    private String date;

    public Order() 
    {
    }

    public Order(int orderId, String uname, String uphone, String uemail, int qunatity, String date) 
    {
        super();
        this.orderId = orderId;
        this.uname = uname;
        this.uphone = uphone;
        this.uemail = uemail;
        this.qunatity = qunatity;
        this.date = date;
    }

    public Order(String uname, String uphone, String uemail, int qunatity, String date) {
        super();
        this.uname = uname;
        this.uphone = uphone;
        this.uemail = uemail;
        this.qunatity = qunatity;
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUname() {
        return uname;
    }
    
    public void setUname(String uname) {
        this.uname = uname;
    }
    
    public String getUphone() {
        return uphone;
    }
    
    public void setUphone(String uphone) {
        this.uphone = uphone;
    }
    
    public String getUemail() {
        return uemail;
    }
    
    public void setUemail(String uemail) {
        this.uemail = uemail;
    }
    
    public int getQunatity() {
        return qunatity;
    }
    
    public void setQunatity(int qunatity) {
        this.qunatity = qunatity;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
}
