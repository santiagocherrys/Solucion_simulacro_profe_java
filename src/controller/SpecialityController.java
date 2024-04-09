package controller;

import entity.Speciality;
import model.SpecialityModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class SpecialityController {

    public static void insert(){
        String name = JOptionPane.showInputDialog("Ingresa el nombre de la especialidad");
        String description = JOptionPane.showInputDialog("Ingrese la descripcion de la especialidad: ");

        instanceModel().insert(new Speciality(name,description));
    }

    public static void getAll(){
        String list = getAll(instanceModel().findAll());

        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list){
        String listString = "LISTA DE REGISTROS: \n";

        for(Object temp: list){
            Speciality objSpeciality = (Speciality) temp;
            listString += objSpeciality.toString() + "\n";
        }

        return listString;
    }

    public static SpecialityModel instanceModel(){
        return new SpecialityModel();
    }

    public static void delete(){
        Object[] options = Utils.listToArray(instanceModel().findAll());

        Speciality objSelected = (Speciality) JOptionPane.showInputDialog(null,
                "Selecciona una especialidad",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        instanceModel().delete(objSelected);
    }

    public static void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());

        Speciality objSelected = (Speciality) JOptionPane.showInputDialog(null,
                "Selecciona una especialidad para actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        objSelected.setNombre(JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre", objSelected.getNombre()));
        objSelected.setDescripcion(JOptionPane.showInputDialog(null,"ingrese la nueva descripcion", objSelected.getDescripcion()));


        instanceModel().update(objSelected);
    }
}
