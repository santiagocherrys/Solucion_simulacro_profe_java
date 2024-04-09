package model;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import database.CRUD;
import database.ConfigDB;
import entity.Speciality;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialityModel implements CRUD{

    @Override
    public Object insert(Object obj) {
        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        Speciality objSpeciality = (Speciality) obj;

        try{
            String sql = "INSERT INTO especialidad(nombre, descripcion) VALUES (?,?)";

            PreparedStatement objPrepare =objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objSpeciality.getNombre());
            objPrepare.setString(2, objSpeciality.getDescripcion());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objSpeciality.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"La especialidad fue agregada correctamente");

        }catch (SQLException e){
            System.out.println("ERROR " + e.getMessage());
        }
        ConfigDB.closeConnection();

        return objSpeciality;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listEspecialities = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try{
            String  sql = "SELECT * FROM especialidad;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Speciality objSpeciality = new Speciality();

                objSpeciality.setId(objResult.getInt("id"));
                objSpeciality.setNombre(objResult.getString("nombre"));
                objSpeciality.setDescripcion(objResult.getString("descripcion"));

                listEspecialities.add(objSpeciality);
            }
        }catch (SQLException e){
            System.out.println("Error >" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listEspecialities;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConnection();
        Speciality objSpeciality = (Speciality) obj;

        boolean isUpdated = false;

        try{
            String sql = "UPDATE especialidad SET nombre = ?, descripcion = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objSpeciality.getNombre());
            objPrepare.setString(2,objSpeciality.getDescripcion());

            int totalRowAffected = objPrepare.executeUpdate();
            if(totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "El registro fue actualizado correctamente");
            }
        }catch (SQLException e){

        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Speciality objSpeciality = (Speciality) obj;

        boolean isDeleted = false;

        try{
            String sql = "DELETE FROM especialidad WHERE id=?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objSpeciality.getId());

            //devuelve un numero de filas afectadas

            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows >0 ){
                isDeleted = true;

                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
            }
        }catch(SQLException e){
            System.out.println("Error " + e.getMessage());

        }

        return isDeleted;
    }
}
