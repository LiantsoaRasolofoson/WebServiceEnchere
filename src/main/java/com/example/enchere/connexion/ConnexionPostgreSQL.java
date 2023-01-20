package com.example.enchere.connexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionPostgreSQL {
    
    Connection Connect;

    public ConnexionPostgreSQL(){}

    public Connection getConnect(){
        try{
            Class.forName("org.postgresql.Driver");
            this.Connect = DriverManager.getConnection("jdbc:postgresql://containers-us-west-172.railway.app:7613/railway", "postgres", "5EtPU13yu4JLzOQKuIy5");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return this.Connect;
    }
}
