/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpjm.micro.service;

/**
 *
 * @author bianza
 */
public class ServiceResponse {
     private Boolean status;
    private Object data;

    public ServiceResponse(Object dt){
        this.data = dt;
        if((dt.toString().compareTo("[]") == 0)){
        //if (dt == null) {
            this.status = false;
        } else {
            this.status = true;
        }
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
}
