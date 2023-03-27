package gestion_reservation_vol;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Scanner;

public class administrateur {
	// Définir une connexion JDBC à la base de données
    private static Connection connexion;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Établir la connexion à la base de données
        try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_reservation_vol", "ophelda", "adolphe");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        while (true) {
        	 System.out.println("Gestion des vol");
             System.out.println("1. Ajouter un vol");
             System.out.println("2. Modifier un vol");
             System.out.println("3. Supprimer un vol ");
             System.out.println("4. Quitter");
             
             Scanner scanner = new Scanner(System.in);
             int choix = scanner.nextInt();

             switch (choix) {
                 case 1:
                     ajouter();
                     break;
                 case 2:
                 	modifier();
                     break;
                 case 3:
                     supprimer();
                     break;
                 case 4:
  	
 				try {
 					connexion.close();
 				} catch (SQLException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 				System.exit(0);
 				break;
 			default:
                     System.out.println("Choix invalide.");
             }
         }
        }
    
	private static void ajouter() {    	
            // Établir la connexion à la base de données
            try {
    			connexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_reservation_vol", "ophelda", "adolphe");
    		
          String choix="";
           do{
        	  

          

          // ajout d'un vol
         
          
               System.out.println("Entrez le numéro de vol:");
                String numero_vol = sc.nextLine();

                System.out.println("Entrez la compagnie aerienne:");
                String compagnie_aerienne = sc.nextLine();
          
                System.out.println("Entrez la ville de départ:");
                String ville_depart = sc.nextLine();
          
                System.out.println("Entrez la ville d'arrivée:");
                String ville_arrivee = sc.nextLine();
          
                System.out.println("Entrez la date de départ (yyyy-mm-dd):");
                String date_depart = sc.nextLine();

                System.out.println("Entrez l'heure de départ (hh-mm-ss):");
                String heure_depart = sc.nextLine();

                System.out.println("Entrez la date d'arrivee (yyyy-mm-dd):");
                String date_arrivee = sc.nextLine();

                System.out.println("Entrez l'heure d'arrivee (hh-mm-ss):");
                String heure_arrivee= sc.nextLine();

                System.out.println("Entrez le tarif du vol");
                          String tarif = sc.nextLine();

                          
          
                          
                         
                  String requete = "INSERT INTO vol (numero_vol, compagnie, depart, destination,date_depart,heure_depart,date_arrivee,heure_arrivee,prix) " +
                                  "VALUES (?, ?, ?, ?, ?,?,?,?,?)";
                            PreparedStatement preparedStatement = connexion.prepareStatement(requete);
                              preparedStatement.setString(1, numero_vol);
                              preparedStatement.setString(2, compagnie_aerienne);
                              preparedStatement.setString(3, ville_depart);
                              preparedStatement.setString(4, ville_arrivee);
                              preparedStatement.setString(5, date_depart);
                              preparedStatement.setString(6, heure_depart);
                              preparedStatement.setString(7, date_arrivee);
                              preparedStatement.setString(8, heure_arrivee);
                              preparedStatement.setString(9, tarif);
                              preparedStatement.executeUpdate();

         
                          System.out.println("vol ajoutée avec succès.");
                           // Afficher la liste mise à jour des vols
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                            requete = "SELECT * FROM vol";
                           
                            PreparedStatement  statement = connexion.prepareStatement(requete);
                             ResultSet resultat = statement.executeQuery(requete);
        
                         System.out.println("Liste des vols :");
                         while (resultat.next())  {
                         System.out.println(resultat.getString("numero_vol") + " | " + resultat.getString("compagnie") + " | " + resultat.getString("depart") + " | " + resultat.getString("destination")+ " | " + resultat.getString("date_depart")+ " | " + resultat.getString("heure_depart")+ " | " + resultat.getString("date_arrivee")+ " | " + resultat.getString("heure_arrivee")+ " | " + resultat.getInt("prix"));
                         }
          
                          System.out.println("Ajouter un autre vol ? (OUI/NON)");
               choix=sc.next();
                         
          }while(!choix.equals("NON"));
           
            } catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            
           
	}
                    

              
       
          

        
      
        
    
    private static void modifier() {
    	 try {
				connexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_reservation_vol", "ophelda", "adolphe");
		  


 // modification d'un vol
  // Afficher la liste des vols
  System.out.println("Liste des vols:");
  
  
  

  
  String query = "SELECT * FROM vol";
  PreparedStatement  statement = connexion.prepareStatement(query);

  ResultSet resultat = statement.executeQuery(query);

  while (resultat.next()) {
     System.out.println(resultat.getString("numero_vol") + " | " + resultat.getString("compagnie") + " | " + resultat.getString("depart") + " | " + resultat.getString("destination")+ " | " + resultat.getString("date_depart")+ " | " + resultat.getString("heure_depart")+ " | " + resultat.getString("date_arrivee")+ " | " + resultat.getString("heure_arrivee")+ " | " + resultat.getInt("prix"));
 }


   System.out.println("Modification d'un vol:");

   System.out.print("Entrez le numéro du vol à modifier: ");

   String numeroVol = sc.next();

   System.out.print("Que voulez-vous modifier (destination ou date)?: ");

   String colonne = sc.next();

   System.out.print("Entrez la nouvelle valeur: ");

   String nouvelleValeur = sc.next();
   query = "UPDATE vol SET " + colonne + "=? WHERE numero_vol=?";
   PreparedStatement preparedStatement = connexion.prepareStatement(query);

   preparedStatement.setString(1, nouvelleValeur);

   preparedStatement.setString(2, numeroVol);


   int rowsUpdated = preparedStatement.executeUpdate();

   System.out.println(rowsUpdated + " vol(s) modifié(s).");

   // Afficher la liste des vols avec les modifications
   System.out.println("Liste des vols modifiée:");
   resultat = statement.executeQuery("SELECT * FROM vol");
   while (resultat.next()) {
     System.out.println(resultat.getString("numero_vol") + " | " + resultat.getString("compagnie") + " | " + resultat.getString("depart") + " | " + resultat.getString("destination")+ " | " + resultat.getString("date_depart")+ " | " + resultat.getString("heure_depart")+ " | " + resultat.getString("date_arrivee")+ " | " + resultat.getString("heure_arrivee")+ " | " + resultat.getInt("prix"));
   }
    	 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   }
  

   
 

   private static void supprimer() {
	   // Établir la connexion à la base de données
       try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_reservation_vol", "ophelda", "adolphe");
		
   



    // suppression d'un vol
    System.out.println("Veuillez saisir le numéro de vol à supprimer :");
   
  
	String numero_vol = sc.nextLine();

    // Préparer la requête DELETE
    String requete = "DELETE FROM vol WHERE numero_vol = ?";
    PreparedStatement preparedStatement = connexion.prepareStatement(requete);
    preparedStatement.setString(1, numero_vol);

    // Exécuter la requête DELETE
    int lignesSupprimees = preparedStatement.executeUpdate();
    if (lignesSupprimees > 0) {
        System.out.println("Vol supprimé avec succès");
    } else {
        System.out.println("Aucun vol n'a été supprimé");
    }

    // Afficher la liste mise à jour des vols
    requete = "SELECT * FROM vol";
    PreparedStatement statement = connexion.prepareStatement(requete);
    ResultSet resultat = statement.executeQuery(requete);

    System.out.println("Liste des vols :");
    while (resultat.next()) {
        System.out.println(resultat.getString("numero_vol") + " | " + resultat.getString("compagnie") + " | " + resultat.getString("depart") + " | " + resultat.getString("destination")+ " | " + resultat.getString("date_depart")+ " | " + resultat.getString("heure_depart")+ " | " + resultat.getString("date_arrivee")+ " | " + resultat.getString("heure_arrivee")+ " | " + resultat.getInt("prix"));
    }
       } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

   }

}
