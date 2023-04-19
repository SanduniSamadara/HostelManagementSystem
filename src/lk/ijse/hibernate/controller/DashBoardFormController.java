package lk.ijse.hibernate.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DashBoardFormController {
    public AnchorPane dashBoardFormContext;
    public Label lblDate;
    public Label lblTime;

    public void initialize(){

        try {
            loadDateAndTime();
            openContext();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openContext() throws IOException {
        setUI("RegistrationForm");
    }

    private void loadDateAndTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour() + ":" +
                    currentTime.getMinute() + ":" +
                    currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void setUI(String URI) throws IOException {
        dashBoardFormContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("view"+URI+".fxml"));
        dashBoardFormContext.getChildren().add(parent);
    }

    public void studentRegistrationOnAction(ActionEvent actionEvent) throws IOException {
        setUI("RegistrationForm");
    }

    public void studentManagementOnAction(ActionEvent actionEvent) throws IOException {
        setUI("StudentForm");
    }

    public void roomManagementOnAction(ActionEvent actionEvent) throws IOException {
        setUI("RoomForm");
    }

    public void userManagementOnAction(ActionEvent actionEvent) throws IOException {
        setUI("UserForm");
    }

    public void KeyMoneyPendingOnAction(ActionEvent actionEvent) throws IOException {
        setUI("PendingKeyMoneyForm");
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage1= (Stage)dashBoardFormContext.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("lk/ijse/hibernate/view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage2= new Stage();
        stage2.setTitle("Hostel Management System");
        Image image = new Image("lk/ijse/hibernate/assessts/images/ogimage1.png");
        stage2.getIcons().add(image);
        stage2.setScene(scene);
        stage2.centerOnScreen();
        stage2.show();
    }
}
