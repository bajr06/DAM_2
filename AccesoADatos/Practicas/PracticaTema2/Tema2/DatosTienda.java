package Tema2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatosTienda {
    private static List<String> datosStand() {
        List<String> datos = new ArrayList<>();

        IO.print("Introduzca el ID del Stand: ");
        datos.add(Principal.sc.nextLine());

        IO.print("Introduzca el ID de la Zona: ");
        datos.add(Principal.sc.nextLine());

        return datos;
    }

    public static void juguetesStand(Connection conexion) {
        List<String> datos = datosStand();

        String juguetesStand = "select T1.Nombre, T2.Cantidad from (select ID_Juguete, Nombre from Juguete) T1 inner join (select Juguete_ID_Juguete as ID_J, Cantidad from Stock WHERE Stand_ID_Stand = ? and Stand_Zona_ID_Zona = ?) T2 on T1.ID_Juguete = T2.ID_J;";
        
            try {
            PreparedStatement ps = conexion.prepareStatement(juguetesStand);
            ps.setString(1, datos.get(0));
            ps.setString(2, datos.get(1));
            
            ResultSet rs = ps.executeQuery();
            
            System.out.println("Juguetes disponibles en el stand " + datos.get(0) + ":");
            while (rs.next()) {
                System.out.println(rs.getString("Nombre") + " - Cantidad: " + rs.getInt("Cantidad"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void verCambios(Connection conexion) {
        String verCambios = "select ID_Cambio, Motivo, Empleado_ID_Empleado from Cambio";

        try {
            PreparedStatement ps = conexion.prepareStatement(verCambios);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                System.out.println("ID del cambio: " + rs.getInt("ID_Cambio") + ", Motivo: " + rs.getString("Motivo") + ", ID del Empleado: " + rs.getString("Empleado_ID_Empleado"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void productosPorPrecio(Connection conexion) {
        String productosPorPrecio = "select T1.Nombre, T1.Precio from (select Nombre, Precio from Juguete order by Precio asc) T1;";
        try {
            PreparedStatement ps = conexion.prepareStatement(productosPorPrecio);
            ResultSet rs = ps.executeQuery();

            System.out.println("Productos ordenados por precio:");

            while (rs.next()) {
                System.out.println(rs.getString("Nombre") + " - Precio: " + rs.getDouble("Precio") + "€");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
