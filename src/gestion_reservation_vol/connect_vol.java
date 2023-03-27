package gestion_reservation_vol;

import java.sql.*;

public class connect_vol {
   static final String DB_URL = "jdbc:mysql://localhost/informations_compagnie";
   static final String USER = "ophelda";
   static final String PASS = "adolphe";

   public static void main(String[] args) {
      Connection conn = null;
      Statement stmt = null;
      try{
         Class.forName("com.mysql.jdbc.Driver");

         System.out.println("Connexion à la base de données...");
         conn = DriverManager.getConnection(DB_URL,USER,PASS);

         System.out.println("Création d'une déclaration...");
         stmt = conn.createStatement();

         String sql = "INSERT INTO compagnie (id, nom, adresse, site_web) " +
                      "VALUES (1, 'air', 'Dakar' ,'www.air.com');";
         stmt.executeUpdate(sql);
         System.out.println("Informations enregistrées dans la base de données...");

      }catch(SQLException se){
         se.printStackTrace();
      }catch(Exception e){
         e.printStackTrace();
      }finally{
         try{
            if(stmt!=null)
               conn.close();
         }catch(SQLException se){
         }
         try{
            if(conn!=null)
               conn.close();
         }catch(SQLException se){
            se.printStackTrace();
         }
      }
      System.out.println("Terminé!");
   }
}
