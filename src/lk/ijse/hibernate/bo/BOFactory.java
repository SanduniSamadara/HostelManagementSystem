package lk.ijse.hibernate.bo;

import lk.ijse.hibernate.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBOFactory(){
        if (boFactory==null){
            return new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes {
        LOG,PENDINGKEYMONEY,REGISTRATION,ROOM,STUDENT,USER
    }


    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case LOG:
                return (SuperBO) new LogBOImpl();
            case PENDINGKEYMONEY:
                return (SuperBO) new PendingKeyMoneyBOImpl();
            case REGISTRATION:
                return (SuperBO) new RegistrationBOImpl();
            case ROOM:
                return (SuperBO) new RoomBOImpl();
            case STUDENT:
                return (SuperBO) new StudentBOImpl();
            case USER:
                return (SuperBO) new UserBOImpl();
            default:
                return null;
        }
    }
}
