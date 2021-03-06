package upeu.rrhh.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import upeu.rrhh.model.Facultad;
import upeu.rrhh.util.DataBaseConexion;

public class PeriodoDAO extends DataBaseConexion{
 
    

    public List<Facultad> Report() {

        List<Facultad> facultades = new ArrayList<Facultad>();

        try {

            getConexion();
            st = Conn.createStatement();
            //ResultSet rs = statement.executeQuery("INSERT INTO  facultad ( facultadid , numtel , nombre ) VALUES ( ? , ?, ? )");
            rs = st.executeQuery(" SELECT * FROM facultad");

            while (rs.next()) {


                Facultad facultad = new Facultad();
                facultad.setFacultadid(rs.getString("facultadid"));
                facultad.setNombre(rs.getString("nombre"));
                facultad.setNumtel(rs.getString("numtel"));


                facultades.add(facultad);
            }

          
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{closeConexion();}

        return facultades;
    }

    public void Add(Facultad facultad) {
        try {
            getConexion();
            ps =
                    Conn.prepareStatement(
                    "  INSERT INTO  facultad ( facultadid , numtel , nombre ) VALUES ( ? , ?, ? )  ");

            ps.setString(1, facultad.getFacultadid());
            ps.setString(2, facultad.getNumtel());
            ps.setString(3, facultad.getNombre());

            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{closeConexion();}


    }

    public String ObtenerId() {

        java.util.Date ahora = new java.util.Date();
        SimpleDateFormat formateador = new SimpleDateFormat("ddMMyyyy");
        PeriodoDAO trabManager = new PeriodoDAO();
        int a = trabManager.Report().size();
        return formateador.format(ahora) + "periodo" + (a + 1);

    }

   /* public Facultad finById(String idDetallePeriodoAlumno) {
        Facultad facultad = new Facultad();

        try {
            connection = DataBaseConexion.getConexion();
            ps ps = connection.prepareStatement("select * from facultad where facultadid=?");
            ps.setString(1, idDetallePeriodoAlumno);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {


                facultad.setFacultadid(rs.getString("facultadid"));
                facultad.setNombre(rs.getString("nombre"));
                facultad.setNumtel(rs.getString("numtel"));

            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return facultad;
    }*/
    
}