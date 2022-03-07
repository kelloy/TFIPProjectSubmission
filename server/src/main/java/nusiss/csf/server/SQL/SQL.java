package nusiss.csf.server.SQL;

public class SQL {

    public static final String SQL_ADD_USER = "insert into user(username,password,email,contactNumber) values (?,sha1(?),?,?)";
    public static final String SQL_LAST_INSERT_ID = "select last_insert_id() as userId";
    public static final String SQL_CHECK_USER = "select count(*) as count from user where username = ? and password = sha1(?)";
    public static final String SQL_GET_USER_ID = "select userId from user where username = ?";
    public static final String SQL_GET_FAVOURITES = "select * from favourite where username = ?";
    public static final String SQL_CHECK_EMAIL = "select count(*) as count from user where email = ? or username = ?";
    public static final String SQL_SAVE_FOOD = "insert into favourite(stallName,uuid,userId,username) values (?,?,?,?)";
    public static final String SQL_CHECK_ENTRY = "select count(*) as count from favourite where uuid = ?";
    public static final String SQL_SAVE_FEEDBACK = "insert into feedbacks(username,email,contactNumber,comment) values (?,?,?,?)";
    public static final String SQL_DELETE_FAV_FOOD = "delete from favourite where uuid = ? and userId = ?";
    
}
