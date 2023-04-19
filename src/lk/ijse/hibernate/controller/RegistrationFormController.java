package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custom.RegistrationBO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.view.tm.CartTM;

import java.util.List;
import java.util.Optional;

public class RegistrationFormController {
    public AnchorPane registrationFormContext;
    public JFXComboBox<String> cmbStudentId;
    public JFXComboBox <String>cmbRoomTypeId;
    public JFXComboBox<String> cmbPaymentStatus;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public TableColumn colRoomTypeId;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colPaymentStatus;
    public TableColumn colDelete;
    public JFXButton btnAddToCart;
    public JFXButton btnRegister;
    public TableView<CartTM> tblCart;

    private final RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.REGISTRATION);
    public Label lblReservation;

    public void initialize(){
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        TableColumn<CartTM, Button> lastCol = (TableColumn<CartTM, Button>) tblCart.getColumns().get(4);
        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");

            btnDelete.setOnAction(event -> {
                tblCart.getItems().remove(param.getValue());
                tblCart.getSelectionModel().clearSelection();
                enableOrDisableRegisterButton();
            });

            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        setStudentId();
        setRoomId();
        generateNewId();

        txtName.setEditable(false);
        txtAddress.setEditable(false);
        txtContact.setEditable(false);
        txtType.setEditable(false);
        txtKeyMoney.setEditable(false);
        txtQty.setEditable(false);
        btnAddToCart.setDisable(true);
        btnRegister.setDisable(true);

        btnAddToCart.setDisable(true);


        cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            enableOrDisableRegisterButton();
            if (newValue != null) {
                try {
                    List<StudentDTO> list = registrationBO.getStudentDetailUsingId(newValue);
                    for (StudentDTO dto : list
                    ) {
                        txtName.setText(dto.getName());
                        txtAddress.setText(dto.getAddress());
                        txtContact.setText(dto.getContact_no());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                txtName.clear();
                txtAddress.clear();
                txtContact.clear();
            }
        });

        cmbRoomTypeId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnAddToCart.setDisable(newValue==null);
            if (newValue != null){
                try {
                    List<RoomDTO> list = registrationBO.getRoomDetailUsingId(newValue);
                    for (RoomDTO dto:list
                    ) {
                        txtType.setText(dto.getType());
                        Optional<CartTM> tm = tblCart.getItems().stream().filter(detail -> detail.getRoom_type_id().equals(newValue)).findFirst();

                        txtQty.setText(String.valueOf(tm.isPresent() ? dto.getQty()- 1 : dto.getQty()));
                        txtKeyMoney.setText(String.valueOf(dto.getKey_money()));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                txtType.clear();
                txtKeyMoney.clear();
                txtQty.clear();

            }

        });

        cmbPaymentStatus.getItems().addAll("Paid","Paid Later");

    }

    private void generateNewId() {
        try {
            String lastRegistrationId = registrationBO.generateRegistrationId();
            System.out.println(lastRegistrationId);
            int newId = Integer.parseInt(lastRegistrationId.substring(2, 5))+1;
            System.out.println(newId);
            if (newId < 10) {
                lblReservation.setText("RS00"+newId);
            }else if (newId < 100) {
                lblReservation.setText("RS0"+newId);
            }else {
                lblReservation.setText("RS"+newId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enableOrDisableRegisterButton() {
        btnRegister.setDisable(!(cmbStudentId.getSelectionModel().getSelectedItem() != null && !tblCart.getItems().isEmpty()));

    }

    private void setRoomId() {
        List<RoomDTO> allRooms = registrationBO.getAllRooms();
        for (RoomDTO dto:allRooms
        ) {
            cmbRoomTypeId.getItems().add(dto.getRoom_type_id());
        }

    }

    private void setStudentId() {
        List<StudentDTO> allStudents = registrationBO.getAllStudents();
        for (StudentDTO dto:allStudents
        ) {
            cmbStudentId.getItems().add(dto.getStudent_id());
        }
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        String roomTypeId = cmbRoomTypeId.getValue();
        String type = txtType.getText();
        Double keyMoney = Double.valueOf(txtKeyMoney.getText());
        String status = cmbPaymentStatus.getValue();

        if(btnAddToCart.getText().equals("Save")){

        }else {
            tblCart.getItems().add(new CartTM(roomTypeId,type,keyMoney,status));
        }
        cmbPaymentStatus.getSelectionModel().clearSelection();
        cmbRoomTypeId.getSelectionModel().clearSelection();
        btnRegister.requestFocus();
        enableOrDisableRegisterButton();
    }


    public void btnRegisterOnAction(ActionEvent actionEvent) {
        try {
            registrationBO.Register(tblCart.getItems(),cmbStudentId.getValue(),lblReservation.getText());
            new Alert(Alert.AlertType.CONFIRMATION,"Register Complete").show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        generateNewId();
        cmbStudentId.getSelectionModel().clearSelection();
        cmbRoomTypeId.getSelectionModel().clearSelection();
        cmbPaymentStatus.getSelectionModel().clearSelection();
        tblCart.getItems().clear();
    }
}