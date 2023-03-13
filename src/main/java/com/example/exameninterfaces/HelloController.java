package com.example.exameninterfaces;

import com.example.exameninterfaces.models.Alumno;
import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    static Alert alert = new Alert(Alert.AlertType.NONE);
    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cesur", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
        //nos traemos los alumnos de la BBDD
        var resulSQL = new ArrayList<Alumno>();
        try (var pst = connection.prepareStatement("select * from alumnos")) {
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                var alumno = new Alumno();
                alumno.setNombre(resultSet.getString("nombre"));
                alumno.setApellido(resultSet.getString("apellido"));
                alumno.setAD(resultSet.getFloat("AD"));
                alumno.setSGE(resultSet.getFloat("SGE"));
                alumno.setDI(resultSet.getFloat("DI"));
                alumno.setPMDM(resultSet.getFloat("PMDM"));
                alumno.setPSP(resultSet.getFloat("PSP"));
                alumno.setEIE(resultSet.getFloat("EIE"));
                alumno.setHLC(resultSet.getFloat("HLC"));
                resulSQL.add(alumno);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //Creamos la observablelist y añadimos los alumnos de la bbdd
        ObservableList<Alumno> alumnos = FXCollections.observableArrayList();
        resulSQL.forEach(alumno -> {
            alumnos.add(alumno);
        });
        tablaAlumnos.getItems().clear();
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

                                        try (var pst = connection.prepareStatement("INSERT INTO `alumnos` (`id`, " +
                                                "`nombre`, `apellido`, `AD`, `SGE`, `DI`, `PMDM`, `PSP`, `EIE`, `HLC`) " +
                                                "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
                                            pst.setString(1,alumno.getNombre());
                                            pst.setString(2,alumno.getApellido());
                                            pst.setFloat(3,alumno.getAD());
                                            pst.setFloat(4,alumno.getSGE());
                                            pst.setFloat(5,alumno.getDI());
                                            pst.setFloat(6,alumno.getPMDM());
                                            pst.setFloat(7,alumno.getPSP());
                                            pst.setFloat(8,alumno.getEIE());
                                            pst.setFloat(9,alumno.getHLC());
                                            if(pst.executeUpdate() == 1){
                                                var keys = pst.getGeneratedKeys();
                                                keys.next();
                                                update();
                                            }
                                            txtNombre.setText("");
                                            txtApellido.setText("");
                                            txtAD.setText("");
                                            txtSGE.setText("");
                                            txtDI.setText("");
                                            txtPMDM.setText("");
                                            txtPSP.setText("");
                                            txtEIE.setText("");
                                            txtHLC.setText("");
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }
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
    void jasperPDF(ActionEvent event) throws IOException, JRException {
        //Creamos el Report
        HashMap hashMap = new HashMap();
        BufferedImage logo = ImageIO.read(HelloApplication.class.getResource("cesur.png"));
        hashMap.put("Logo", logo);
        String report = "Alumnos.jasper";

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report, hashMap, connection
        );

        JRViewer viewer = new JRViewer(jasperPrint);
        JFrame frame = new JFrame("Report Alumnos");
        frame.getContentPane().add(viewer);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("Alumnos.pdf"));
        SimplePdfExporterConfiguration config = new SimplePdfExporterConfiguration();
        exporter.exportReport();
    }

    @FXML
    void verInfoAlumno(MouseEvent event) {
        Alumno alumno = tablaAlumnos.getSelectionModel().getSelectedItem();
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alumno: " + alumno.getNombre());
        alert.setGraphic(null);
        alert.setHeaderText(null);
        alert.setHeight(150);
        Map<String, Object> resultado = suspenso(alumno);
        if ((boolean) resultado.get("suspenso")) {
            alert.setContentText("El alumno " + alumno.getNombre() + " " + alumno.getApellido() + " ha suspendido la " +
                    "asignatura " + resultado.get("asignatura") + " con una nota de " + resultado.get("nota"));
        } else {
            alert.setContentText("El alumno " + alumno.getNombre() + " " + alumno.getApellido() + " tiene una media de " +
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
                                    result.put("nota", alumno.getHLC());
                                    result.put("asignatura", "HLC");
                                }
                            } else {
                                result.put("suspenso", true);
                                result.put("nota", alumno.getEIE());
                                result.put("asignatura", "EIE");
                            }
                        } else {
                            result.put("suspenso", true);
                            result.put("nota", alumno.getPSP());
                            result.put("asignatura", "PSP");
                        }
                    } else {
                        result.put("suspenso", true);
                        result.put("nota", alumno.getPMDM());
                        result.put("asignatura", "PMDM");
                    }
                } else {
                    result.put("suspenso", true);
                    result.put("nota", alumno.getDI());
                    result.put("asignatura", "DI");
                }
            } else {
                result.put("suspenso", true);
                result.put("nota", alumno.getSGE());
                result.put("asignatura", "SGE");
            }
        } else {
            result.put("suspenso", true);
            result.put("nota", alumno.getAD());
            result.put("asignatura", "AD");
        }
        return result;
    }
}
