package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.dto.RoomDTO;

import java.io.IOException;
import java.util.List;

public interface RoomBO {
    boolean saveRoom(RoomDTO dto) throws Exception;

    boolean updateRoom(RoomDTO dto) throws Exception;

    boolean deleteRoom(String id) throws Exception;

    List<RoomDTO> getAllRooms() throws IOException;
}
