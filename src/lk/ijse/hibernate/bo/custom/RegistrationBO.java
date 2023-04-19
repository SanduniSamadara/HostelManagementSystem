package lk.ijse.hibernate.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.view.tm.CartTM;

import java.util.List;

public interface RegistrationBO {
    List<RoomDTO> getAllRooms();

    List<StudentDTO> getAllStudents();

    List<RoomDTO> getRoomDetailUsingId(String newValue);

    List<StudentDTO> getStudentDetailUsingId(String newValue);

    String generateRegistrationId();

    void Register(ObservableList<CartTM> items, String value, String text);
}
