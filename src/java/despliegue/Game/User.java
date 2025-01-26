/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package despliegue.Game;


/**
 *
 * @author Administrator
 */
public class User {
    private String userName;
    private boolean esAdm;
    
    public User(String user, boolean adm){
        this.userName = user;
        this.esAdm = adm;
    }
    public String getUserName() {
        return userName;
    }

    public boolean isEsAdm() {
        return esAdm;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEsAdm(boolean esAdm) {
        this.esAdm = esAdm;
    }
}
