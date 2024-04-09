import controller.SpecialityController;
import database.ConfigDB;
import entity.Speciality;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int option = 0, option2= 0;

        do{
            option = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Administrar Especialidades
                    2. Administrar Medico
                    3. Administrar Pacientes
                    4. Administrar Citas
                    5. Salir
                    
                    Ingrese una opcion:
                    """));

            switch (option){
                case 1:
                    do{
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar Especialidad
                                2. Crear Especialidad
                                3. Eliminar Especialidad
                                4. Actualizar Especialidad
                                5. Salir
                                
                                Ingrese una opcion:
                                """));

                        switch (option2){
                            case 1:
                                SpecialityController.getAll();
                                break;
                            case 2:
                                SpecialityController.insert();
                                break;
                            case 3:
                                SpecialityController.delete();
                                break;
                            case 4:
                                SpecialityController.update();
                                break;
                        }

                    }while(option2 !=0);
                    break;
            }
        }while(option != 5);
    }
}