package com.example.exameninterfaces;

import com.example.exameninterfaces.models.Alumno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    static Alert alert = new Alert(Alert.AlertType.NONE);

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
                new Alumno("Luis", "Lopez", 10f, 10f, 10f, 10f, 10f, 10f, 0f),
                new Alumno("YE", "YE", 10f, 10f, 10f, 10f, 10f, 10f, 10f)
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
                                    } else {
                                        alert.setAlertType(Alert.AlertType.WARNING);
                                        alert.setContentText("La nota de HLC introducida no es valida");
                                        alert.show();
                                    }
                                } else {
                                    alert.setAlertType(Alert.AlertType.WARNING);
                                    alert.setContentText("La nota de EIE introducida no es valida");
                                    alert.show();
                                }
                            } else {
                                alert.setAlertType(Alert.AlertType.WARNING);
                                alert.setContentText("La nota de PSP introducida no es valida");
                                alert.show();
                            }
                        } else {
                            alert.setAlertType(Alert.AlertType.WARNING);
                            alert.setContentText("La nota de PMDM introducida no es valida");
                            alert.show();
                        }
                    } else {
                        alert.setAlertType(Alert.AlertType.WARNING);
                        alert.setContentText("La nota de DI introducida no es valida");
                        alert.show();
                    }
                } else {
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setContentText("La nota de SGE introducida no es valida");
                    alert.show();
                }
            } else {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("La nota de AD introducida no es valida");
                alert.show();
            }
        }

    }

    @FXML
    void jasperPDF(ActionEvent event) {

    }

    @FXML
    void verInfoAlumno(MouseEvent event) {
        Alumno alumno = tablaAlumnos.getSelectionModel().getSelectedItem();
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alumno: " + alumno.getNombre());
        alert.setGraphic(null);
        alert.setHeaderText(null);
        alert.setHeight(150);
        Map<String, Object> resultado =suspenso(alumno);
        if ((boolean) resultado.get("suspenso")) {
            alert.setContentText("El alumno "+alumno.getNombre()+" "+alumno.getApellido()+" ha suspendido la " +
                    "asignatura "+resultado.get("asignatura")+" con una nota de "+resultado.get("nota"));
        }else{
            alert.setContentText("El alumno "+alumno.getNombre()+" "+alumno.getApellido()+" tiene una media de "+
                    alumno.getMedia());
        }
        alert.show();
    }

    private Map<String, Object> suspenso(Alumno alumno) {
        Map<String, Object> result = new HashMap<>();
        if (alumno.getAD() > 5f) {
            if (alumno.getSGE() > 5f) {
                if (alumno.getDI() > 5f) {
                    if (alumno.getPMDM() > 5f) {
                        if (alumno.getPSP() > 5f) {
                            if (alumno.getEIE() > 5f) {
                                if (alumno.getHLC() > 5f) {
                                    result.put("suspenso", false);
                                } else {
                                    result.put("suspenso", true);
                                    result.put("nota",alumno.getHLC());
                                    result.put("asignatura","HLC");
                                }
                            } else {
                                result.put("suspenso", true);
                                result.put("nota",alumno.getEIE());
                                result.put("asignatura","EIE");
                            }
                        } else {
                            result.put("suspenso", true);
                            result.put("nota",alumno.getPSP());
                            result.put("asignatura","PSP");
                        }
                    } else {
                        result.put("suspenso", true);
                        result.put("nota",alumno.getPMDM());
                        result.put("asignatura","PMDM");
                    }
                } else {
                    result.put("suspenso", true);
                    result.put("nota",alumno.getDI());
                    result.put("asignatura","DI");
                }
            } else {
                result.put("suspenso", true);
                result.put("nota",alumno.getSGE());
                result.put("asignatura","SGE");
            }
        } else {
            result.put("suspenso", true);
            result.put("nota",alumno.getAD());
            result.put("asignatura","AD");
        }
        return result;
    }

    private boolean hasSuspended(Alumno alumno) {
        if (alumno.getAD() > 5f) {
            if (alumno.getSGE() > 5f) {
                if (alumno.getDI() > 5f) {
                    if (alumno.getPMDM() > 5f) {
                        if (alumno.getPSP() > 5f) {
                            if (alumno.getEIE() > 5f) {
                                if (alumno.getHLC() > 5f) {
                                    return true;
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
