import java.sql.*;

public class ReservationVol {
    
    public static void main(String[] args) {
        // 1. Se connecter à la base de données
        String url = "jdbc:mysql://localhost:3306/gestion_reservation_vol";
        String user = "ophelda";
        String password = "adolphe";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            return;
        }
        
        // 2. Demander les informations de réservation
        System.out.println("Veuillez entrer les informations de réservation :");
        System.out.print("préférence siege : ");
        String preference_siege = System.console().readLine();
        System.out.print("Date de reservation (format : AAAA-MM-JJ) : ");
        String date_reservation = System.console().readLine();
        System.out.print("Nombre de passagers : ");
        int nombre_passager = Integer.parseInt(System.console().readLine());
        
        // 4. Réserver les sièges pour les passagers
        String updateReservationSql = "INSERT INTO reservations (preference_siege, date_reservation, nombre_passager VALUES (?, ?, ?)";
        try {
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(updateReservationSql);
            stmt.setString(1, preference_siege);
            stmt.setString(2, date_reservation);
            stmt.setInt(3, nombre_passager);
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
    
  
    }
