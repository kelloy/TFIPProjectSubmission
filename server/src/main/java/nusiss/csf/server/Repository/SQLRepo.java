package nusiss.csf.server.Repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nusiss.csf.server.SQL.SQL;
import nusiss.csf.server.model.favouriteFood;
import nusiss.csf.server.model.feedback;
import nusiss.csf.server.model.user;

@Repository
public class SQLRepo {

    @Autowired
    private JdbcTemplate template;

    public boolean saveFeedBack(final feedback feedback){
        int addedfeedback = template.update(SQL.SQL_SAVE_FEEDBACK,feedback.getUsername(),feedback.getEmail(),feedback.getContactNumber(),feedback.getComment());
        return addedfeedback > 0;
        
    }

    public boolean saveUser(final user user){
        final SqlRowSet rs = template.queryForRowSet(SQL.SQL_CHECK_EMAIL,user.getEmail(),user.getUsername());
        rs.next();
        int dupCheck = Integer.parseInt(rs.getString("count"));
        if (dupCheck >=1){
            return false;
        } else{        
        int added = template.update(SQL.SQL_ADD_USER,user.getUsername(),user.getPassword(),user.getEmail(),user.getContactNumber());
        return added>0;
        }
    }

    public boolean checkUserLogin(String username, String password){
        int count = 1;
        boolean result = false;
        final SqlRowSet rs = template.queryForRowSet(SQL.SQL_CHECK_USER,username,password);
        System.out.println(rs);
        while(rs.next()){
            System.out.print(rs.getInt("count"));
            if (count == rs.getInt("count")){
                result = true;
            }
        }
        return result;
    }

    public boolean saveFood(final favouriteFood ff){
        final SqlRowSet rs = template.queryForRowSet(SQL.SQL_GET_USER_ID,ff.getUsername());
        boolean result = false;
        rs.next();
        int userId = Integer.parseInt(rs.getString("userId"));
        final SqlRowSet rsnext = template.queryForRowSet(SQL.SQL_CHECK_ENTRY,ff.getUuid());
        while(rsnext.next()){;
            if (rsnext.getInt("count") == 1){
                result = false;
            }
            else{
                int added = template.update(SQL.SQL_SAVE_FOOD, ff.getStallName(),ff.getUuid(),userId,ff.getUsername());
                result = true;
            }
        }
        return result;
    }

    public List<favouriteFood> getFavourites(String username){
        List<favouriteFood> favlist = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(SQL.SQL_GET_FAVOURITES, username);
        while(rs.next()){
            final favouriteFood ff = new favouriteFood();
            ff.setStallName(rs.getString("stallName"));
            ff.setUserId(rs.getInt("userId"));
            ff.setUsername(rs.getString("username"));
            ff.setUuid(rs.getString("uuid"));
            favlist.add(ff); 
        }
        return favlist;
        
    } 

    public Boolean deleteFood(favouriteFood ff){
        int foodDeleted = template.update(SQL.SQL_DELETE_FAV_FOOD,ff.getUuid(),ff.getUserId());
        System.out.println(foodDeleted);
        return foodDeleted > 0;
    }


    
}
