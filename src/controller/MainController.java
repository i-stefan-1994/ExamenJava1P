
package controller;

import dao.StudentDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;


public class MainController {
    
    private Connection con;
    private StudentDao studentDao;
    private String url = "jdbc:mysql://localhost:3306/java1pexamen";
    private String user = "root";
    private String pass = "";
    
    private MainController() {
        try {
            con = DriverManager.getConnection(url, user, pass);
            studentDao = new StudentDao(con);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MainController getInstance() {
        return MainControllerHolder.INSTANCE;
    }
    
    private static class MainControllerHolder {

        private static final MainController INSTANCE = new MainController();
    }
    
    public boolean addStudent(Student student){
        return studentDao.addStudent(student);
    }
    
    public boolean deleteStudent(int id){
        return studentDao.deleteStudent(id);
    }
    
    public List<Student> getStudenti(){
        return studentDao.getStudenti();
    }
    
    public Optional<Student> getCnp(int cnp){
        
            Optional<Student> optionalStudent = studentDao.findCnp(cnp);
            
            if(optionalStudent.isPresent()){
                return Optional.empty();
            }else{
                return optionalStudent;
            }

    }
}
