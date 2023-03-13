package com.example.exameninterfaces;

import com.example.exameninterfaces.models.Alumno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button btnAddAlumno;

    @FXML
    private Button btnPDF;

    @FXML
    private Button btnSalir;

    @FXML
    private TableColumn<Alumno, Float> cAD;

    @FXML
    private TableColumn<Alumno, String> cApellido;

    @FXML
    private TableColumn<Alumno, Float> cDI;

    @FXML
    private TableColumn<Alumno, Float> cEIE;

    @FXML
    private TableColumn<Alumno, Float> cHLC;

    @FXML
    private TableColumn<Alumno, String> cNombre;

    @FXML
    private TableColumn<Alumno, Float> cPMDM;

    @FXML
    private TableColumn<Alumno, Float> cPSP;

    @FXML
    private TableColumn<Alumno, Float> cSGE;

    @FXML
    private TableView<Alumno> tablaAlumnos;

    @FXML
    private TextField txtAD;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtDI;

    @FXML
    private TextField txtEIE;

    @FXML
    private TextField txtHLC;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPMDM;

    @FXML
    private TextField txtPSP;

    @FXML
    private TextField txtSGE;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        cAD.setCellValueFactory(new PropertyValueFactory<>("AD"));
        cSGE.setCellValueFactory(new PropertyValueFactory<>("SGE"));
        cDI.setCellValueFactory(new PropertyValueFactory<>("DI"));
        cPMDM.setCellValueFactory(new PropertyValueFactory<>("PMDM"));
        cPSP.setCellValueFactory(new PropertyValueFactory<>("PSP"));
        cEIE.setCellValueFactory(new PropertyValueFactory<>("EIE"));
        cHLC.setCellValueFactory(new PropertyValueFactory<>("HLC"));
        update();
    }

    private void update() {
        ObservableList<Alumno> alumnos = FXCollections.observableArrayList(
                new Alumno("Pablo", "Perez", 5.7f, 5.2f, 7f, 10f, 10f, 10f, 2f),
                new Alumno("Guillermo", "Garcia", 9.5f, 10f, 4.9f, 4f, 8f, 7f, 6f),
                new Alumno("Luis", "Lopez", 10f, 10f, 10f, 10f, 10f, 10f, 0f)
        );
        tablaAlumnos.setItems(alumnos);
    }


    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void guardarAlumno(ActionEvent event) {
        if (!txtNombre.getText().isEmpty() &&
                !txtApellido.getText().isEmpty() &&
                !txtAD.getText().isEmpty() &&
                !txtSGE.getText().isEmpty() &&
                !txtDI.getText().isEmpty() &&
                !txtPMDM.getText().isEmpty() &&
                !txtPSP.getText().isEmpty() &&
                !txtEIE.getText().isEmpty() &&
                !txtHLC.getText().isEmpty()) {

            if ((Float.parseFloat(txtAD.getText()) <= 10.00) && (Float.parseFloat(txtAD.getText()) >= 0.00)) {
                if ((Float.parseFloat(txtSGE.getText()) <= 10.00) && (Float.parseFloat(txtSGE.getText()) >= 0.00)) {
                    if ((Float.parseFloat(txtDI.getText()) <= 10.00) && (Float.parseFloat(txtDI.getText()) >= 0.00)) {
                        if ((Float.parseFloat(txtPMDM.getText()) <= 10.00) && (Float.parseFloat(txtPMDM.getText()) >= 0.00)) {
                            if ((Float.parseFloat(txtPSP.getText()) <= 10.00) && (Float.parseFloat(txtPSP.getText()) >= 0.00)) {
                                if ((Float.parseFloat(txtEIE.getText()) <= 10.00) && (Float.parseFloat(txtEIE.getText()) >= 0.00)) {
                                    if ((Float.parseFloat(txtHLC.getText()) <= 10.00) && (Float.parseFloat(txtHLC.getText()) >= 0.00)) {
                                        Alumno alumno = new Alumno(txtNombre.getText(),
                                                txtApellido.getText(),
                                                Float.parseFloat(txtAD.getText()),
                                                Float.parseFloat(txtSGE.getText()),
                                                Float.parseFloat(txtDI.getText()),
                                                Float.parseFloat(txtPMDM.getText()),
                                                Float.parseFloat(txtPSP.getText()),
                                                Float.parseFloat(txtEIE.getText()),
                                                Float.parseFloat(txtHLC.getText()));
                                        tablaAlumnos.getItems().add(alumno);
                                    }else {
                                        System.out.println("Nota HLC no valida");
                                    }
                                }else {
                                    System.out.println("Nota EIE no valida");
                                }
                            }else {
                                System.out.println("Nota PSP no valida");
                            }
                        }else {
                            System.out.println("Nota PMDM no valida");
                        }
                    }else {
                        System.out.println("Nota DI no valida");
                    }
                }else {
                    System.out.println("Nota SGE no valida");
                }
            }else {
                System.out.println("Nota AD no valida");
            }
        }

    }

    @FXML
    void jasperPDF(ActionEvent event) {

    }

    @FXML
    void verInfoAlumno(MouseEvent event) {

    }

    private boolean hasSuspended(Alumno alumno){
        return false;
    }
}
