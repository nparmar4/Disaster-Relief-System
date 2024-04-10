package edu.ucalgary.oop;
public class Inquirer extends Person{
    private String info;
    private String servicesPhoneNum;

    public Inquirer(String firstName, String lastName, String servicesPhoneNum) {
        super(firstName, lastName);
        this.servicesPhoneNum = servicesPhoneNum;
    }

    public String getInfo() {
        return info;
    }

    public String getServicesPhoneNum() {
        return servicesPhoneNum;
    }
}