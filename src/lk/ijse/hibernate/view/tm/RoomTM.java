package lk.ijse.hibernate.view.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RoomTM {
        private String room_type_id;
        private String type;
        private double key_money;
        private int qty;

}
