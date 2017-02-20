/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDEV425_HW4;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Kalada Opuiyo
 */
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    private int user_id;
    private String cardholdername;
    private String cardType;
    private String serviceCode;
    private String cardNumber;
    private int cav_ccv2;
    private Date expiredate = null;
    private String fullTrackData;
    private String pin;
    public  int countLoginAttemnpt = 0;

    public int getCountLoginAttemnpt() {
        return countLoginAttemnpt;
    }

    public void setCountLoginAttemnpt(int countLoginAttemnpt) {
        this.countLoginAttemnpt = countLoginAttemnpt;
    }

    public static Users newInstance() {

        return new Users();

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCardholdername() {
        return cardholdername;
    }

    public void setCardholdername(String cardholdername) {
        this.cardholdername = cardholdername;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCav_ccv2() {
        return cav_ccv2;
    }

    public void setCav_ccv2(int cav_ccv2) {
        this.cav_ccv2 = cav_ccv2;
    }

    public Date getExpiredate() {
        return new Date(expiredate.getTime());
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = new Date(expiredate.getTime());
    }

    public String getFullTrackData() {
        return fullTrackData;
    }

    public void setFullTrackData(String fullTrackData) {
        this.fullTrackData = fullTrackData;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

}
