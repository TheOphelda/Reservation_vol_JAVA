
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

public class gestionReservationVol {

    // Définir une connexion JDBC à la base de données
    private static Connection connexion;

    public static void main(String[] args) {
        // Établir la connexion à la base de données
        try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_reservation_vol", "ophelda", "adolphe");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Boucle pour permettre à l'utilisateur de sélectionner une option
        while (true) {
            System.out.println("Gestion de réservation de vol");
            System.out.println("1. Recherche de vols disponibles");
            System.out.println("2. S'authentifier");
            System.out.println("3. creer un compte");
            System.out.println("4. Quitter");

            // Lire l'entrée de l'utilisateur
            Scanner scanner = new Scanner(System.in);
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    rechercherVols();
                    break;
                case 2:
                	authentifier();
                    break;
                case 3:
                    creer_compte();
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
    
    private static void authentifier() {
    	    Scanner sc = new Scanner(System.in);
    	    Connection conn = null;
    	    PreparedStatement pstmt = null;
    	    ResultSet rs = null;
    	    
    	    int nbTentatives = 0;
    	    boolean authentifie = false;
    	    
    	    try {
    	        // Se connecter à la base de données
    	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_reservation_vol", "ophelda", "adolphe");
    	        
    	        while (!authentifie && nbTentatives < 2) {
    	            // Demander les informations d'identification à l'utilisateur
    	            System.out.println("Veuillez saisir votre adresse email :");
    	            String email = sc.nextLine();
    	            System.out.println("Veuillez saisir votre mot de passe :");
    	            String motDePasse = sc.nextLine();
    	            
    	            // Vérifier si l'utilisateur existe dans la base de données
    	            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM utilisateur WHERE email = ? AND mot_de_passe = ?");
    	            pstmt.setString(1, email);
    	            pstmt.setString(2, motDePasse);
    	            rs = pstmt.executeQuery();
    	            rs.next();
    	            int nbUtilisateurs = rs.getInt(1);
    	            
    	            if (nbUtilisateurs == 1) {
    	                // L'utilisateur a été authentifié avec succès
    	                authentifie = true;
    	                System.out.println("Authentification réussie !");
    	                try {
    	        			connexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_reservation_vol", "ophelda", "adolphe");
    	        		} catch (SQLException e) {
    	        			// TODO Auto-generated catch block
    	        			e.printStackTrace();
    	        		}

    	                // Boucle pour permettre à l'utilisateur de sélectionner une option
    	                while (true) {
    	                    System.out.println("Gestion de réservation de vol");
    	                    System.out.println("1. Recherche de vols disponibles");
    	                    System.out.println("2. reserver vol");
    	                    System.out.println("3. consulter les reservations");
    	                    System.out.println("4. Annuler reservation");
    	                    System.out.println("5. Se deconnecter ");

    	                    // Lire l'entrée de l'utilisateur
    	                    Scanner scanner = new Scanner(System.in);
    	                    int choix = scanner.nextInt();

    	                    switch (choix) {
    	                        case 1:
    	                            rechercherVols();
    	                            break;
    	                        case 2:
    	                        	reserverVol();
    	                            break;
    	                        case 3:
    	                        	afficherReservations();
    	                            break;
    	                        case 4:
    	                        	annulerReservation();
    	                            break;
    	                        case 5:
    	         	
    	        				try {
    	        					connexion.close();
    	        				} catch (SQLException e) {
    	        					// TODO Auto-generated catch block
    	        					e.printStackTrace();
    	        				}
    	        				System.out.println("Vous etes deconnecté . voulez vous vous reconnecter ?(oui/non)");
    	        				String Choix = sc.nextLine();
    	        				Object oui = null;
								Object non=null;
								if(Choix.equals(oui)) {
    	        					authentifier();
    	        					
    	        				}
    	        				else if(Choix.equals(non)) {
    	        					break;
    	        					
    	        				}
    	        			
    	        				
    	        				
    	                        default:
    	                            System.out.println("Choix invalide.");
    	                    }
    	                }
    	            } else {
    	                // L'utilisateur n'a pas été authentifié, on incrémente le nombre de tentatives
    	                nbTentatives++;
    	                System.out.println("Email ou mot de passe incorrect !");
    	            }
    	        }
    	        
    	        if (!authentifie) {
    	            // L'utilisateur n'a pas réussi à s'authentifier, on lui propose de créer un compte
    	            System.out.println("Vous n'avez pas de compte ? Voulez-vous en créer un ? (oui/non)");
    	            String reponse = sc.nextLine();
    	            
    	            if (reponse.equalsIgnoreCase("oui")) {
    	                // Appeler la méthode creerCompte pour créer un compte pour l'utilisateur
    	            	creer_compte();
    	            } else {
    	                System.out.println("Au revoir !");
    	            }
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    } finally {
    	        // Fermer la connexion à la base de données
    	        try {
    	            if (rs != null) {
    	                rs.close();
    	            }
    	            if (pstmt != null) {
    	                pstmt.close();
    	            }
    	            if (conn != null) {
    	                conn.close();
    	            }
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	    }
    	    
    	}


		
	private static void creer_compte() {
		    Scanner sc = new Scanner(System.in);
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    
		    try {
		        // Se connecter à la base de données
		        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_reservation_vol", "ophelda", "adolphe");
		        
		        // Demander les informations nécessaires pour créer le compte
		        System.out.println("Veuillez saisir votre nom :");
		        String nom = sc.nextLine();
		        System.out.println("Veuillez saisir votre prénom :");
		        String prenom = sc.nextLine();
		        System.out.println("Veuillez saisir votre adresse email :");
		        String email = sc.nextLine();
		        System.out.println("Veuillez saisir votre mot de passe :");
		        String motDePasse = sc.nextLine();
		        
		        // Insérer les informations dans la base de données
		        pstmt = conn.prepareStatement("INSERT INTO utilisateur (nom, prenom, email, mot_de_passe) VALUES (?, ?, ?, ?)");
		        pstmt.setString(1, nom);
		        pstmt.setString(2, prenom);
		        pstmt.setString(3, email);
		        pstmt.setString(4, motDePasse);
		        pstmt.executeUpdate();
		        
		        System.out.println("Compte créé avec succès !");
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // Fermer la connexion à la base de données
		        try {
		            if (pstmt != null) {
		                pstmt.close();
		            }
		            if (conn != null) {
		                conn.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}


	private static void afficherReservations() {
		
		 	Scanner sc = new Scanner(System.in);
		 	System.out.println("Veuillez saisir votre id utilisateur :");
	        int id_utilisateur = sc.nextInt();
	        System.out.println("Veuillez saisir votre id de vol :");
	        int id_vol = sc.nextInt();
	       

		    // Rechercher les réservations de l'utilisateur dans la base de données
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet rs = null;
		    try {
		        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_reservation_vol", "ophelda", "adolphe");
		        String sql = "SELECT * FROM reservation WHERE id_utilisateur = ?";
		        stmt = conn.prepareStatement(sql);
		        stmt.setInt(1,id_utilisateur );
		        
		        rs = stmt.executeQuery();
		        boolean hasReservations = false;
		        while (rs.next()) {
		            hasReservations = true;
		            System.out.println("Réservation #" + rs.getInt("id_reservation"));
		            System.out.println("Vol: " + rs.getString("id_vol"));
		            System.out.println("Passager: " + rs.getString("id_utilisateur"));
		            System.out.println("Date de reservation: " + rs.getString("date_reservation"));
		            System.out.println("Siège: " + rs.getString("preference_siege"));
		            System.out.println(" nombre de passagers : " + rs.getInt("nombre_passager"));
		            System.out.println();
		        }
		        if (!hasReservations) {
		            System.out.println("Aucune réservation trouvée pour cet utilisateur.");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (rs != null) rs.close();
		            if (stmt != null) stmt.close();
		            if (conn != null) conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}

		

	private static void annulerReservation() {
			Scanner sc = new Scanner(System.in);
		 	System.out.println("saisir l'id de la reservation ");
		 	int id_reservation  = sc.nextInt();
		 	
		    try {
		        // se connecter à la base de données
		        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_reservation_vol", "ophelda", "adolphe");

		        // préparer la requête SQL pour supprimer la réservation
		        String sql = "DELETE FROM reservation WHERE id_reservation = ?";
		        PreparedStatement statement = connection.prepareStatement(sql);
		        statement.setInt(1, id_reservation);

		        // exécuter la requête SQL
		        int rowsDeleted = statement.executeUpdate();

		        if (rowsDeleted > 0) {
		            System.out.println("La réservation a été annulée avec succès.");
		        } else {
		            System.out.println("Le numéro de réservation n'est pas valide.");
		        }

		        // fermer la connexion à la base de données
		        connection.close();
		    } catch (SQLException e) {
		        System.out.println("Une erreur s'est produite : " + e.getMessage());
		    }
		}

		


	private static void reserverVol() {
		
		
					
				
		
				rechercherVols();
				
				   String url = "jdbc:mysql://localhost:3306/gestion_reservation_vol";
			        String user = "ophelda";
			        String password = "adolphe";
			        Connection conn = null;
			        Scanner sc = new Scanner (System.in);
			        try {
			            conn = DriverManager.getConnection(url, user, password);
			        } catch (SQLException e) {
			            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
			            return;
			        }
			        
			        // 2. Demander les informations de réservation
			        System.out.println("Veuillez entrer les informations de réservation :");
			        System.out.print("id reservation : ");
			        int id_reservation = sc.nextInt();
			        
			        System.out.print("id vol : ");
			        int id_vol = sc.nextInt();
			        
			        System.out.print("id utilisateur: ");
			        int id_utilisateur = sc.nextInt();
			        
			        System.out.print("préférence siege : ");
			        String preference_siege = sc.next();
			        
			        System.out.print("Date de reservation (format : AAAA-MM-JJ) : ");
			        String date_reservation = sc.nextLine();
			        
			        System.out.print("Nombre de passagers : ");
			        int nombre_passager = sc.nextInt();
			        
			        // 4. Réserver les sièges pour les passagers
			        String updateReservationSql = "INSERT INTO reservation (id_reservation , id_vol, id_utilisateur,preference_siege, date_reservation, nombre_passager VALUES (?, ?, ?, ?, ?, ?)";
			        try {
			            conn.setAutoCommit(false);
			            PreparedStatement stmt = conn.prepareStatement(updateReservationSql);
			            stmt.setInt(1, id_reservation);
			            stmt.setInt(2, id_vol);
			            stmt.setInt(3, id_utilisateur);
			            stmt.setString(4, preference_siege);
			            stmt.setString(5, date_reservation);
			            stmt.setInt(6, nombre_passager);
			            int rowsUpdated = stmt.executeUpdate();
			            if (rowsUpdated > 0) {
			                conn.commit();
			                System.out.println("Réservation réussie pour " + nombre_passager + " passager(s) pour le vol " + preference_siege + " le " + date_reservation + ".");
			            } else {
			                conn.rollback();
			                System.out.println("La réservation a échoué. Veuillez réessayer.");
			            }
			        } catch (SQLException e) {
			            System.out.println("Erreur de requête : " + e.getMessage());
			            return;
			        }
			    }
			    
		                   

	

	public static void rechercherVols() {
		
		    Scanner sc = new Scanner(System.in);
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    
		    try {
		        // Se connecter à la base de données
		        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_reservation_vol", "ophelda", "adolphe");
		        
		        // Demander les informations nécessaires pour la recherche de vols
		        System.out.println("Veuillez saisir la ville de départ :");
		        String depart = sc.nextLine();
		        System.out.println("Veuillez saisir la ville d'arrivée :");
		        String destination = sc.nextLine();
		        System.out.println("Veuillez saisir la date de départ (au format YYYY-MM-DD) :");
		        String dateDepart = sc.nextLine();
		        
		        // Envoyer une requête à la base de données pour récupérer les vols disponibles correspondant aux critères de recherche
		        pstmt = conn.prepareStatement("SELECT * FROM vol WHERE depart = ? AND destination = ? AND date_depart = ?");
		        pstmt.setString(1, depart);
		        pstmt.setString(2, destination);
		        pstmt.setString(3, dateDepart);
		        rs = pstmt.executeQuery();
		        
		        // Afficher les vols disponibles
		        if (rs.next()) {
		            System.out.println("Vols disponibles :");
		            do {
		                System.out.println(rs.getString("numero_vol") + " - " + rs.getString("depart") + " -> " + rs.getString("destination") + " - " + rs.getString("date_depart") + " - " + rs.getString("heure_depart"));
		            } while (rs.next());
		        } else {
		            System.out.println("Aucun vol disponible pour les critères de recherche spécifiés.");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // Fermer la connexion à la base de données
		        try {
		            if (rs != null) {
		                rs.close();
		            }
		            if (pstmt != null) {
		                pstmt.close();
		            }
		            if (conn != null) {
		                conn.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

	}
	    
	}

