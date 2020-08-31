
package dao; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

public class StudentDao { 

    private Connection con;
    private PreparedStatement stmt1, stmt2, stmt3, stmt4;
    
    public StudentDao(Connection con){
        this.con = con;
        try {
            stmt1 = con.prepareStatement("INSERT INTO studenti VALUES(NULL, ?, ?, ?)");
            stmt2 = con.prepareStatement("DELETE FROM studenti WHERE id = ?");
            stmt3 = con.prepareStatement("SELECT * FROM studenti");
            stmt4 = con.prepareStatement("SELECT * FROM studenti WHERE Cnp = ?");
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean addStudent(Student student){
        
        try {
            stmt1.setString(1, student.getNume());
            stmt1.setString(2, student.getPrenume());
            stmt1.setInt(3, student.getCnp());
            
            return(stmt1.executeUpdate()!= 0);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean deleteStudent(int id){
        
        try {
            stmt2.setInt(1, id);
            return(stmt2.executeUpdate()!= 0);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<Student> getStudenti(){
        List<Student> studentList = new ArrayList<>();
        
        try (ResultSet rs = stmt3.executeQuery();){
            while(rs.next()){
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("Nume"),
                        rs.getString("Nume"),
                        rs.getInt("Cnp")
                );
                studentList.add(student);
            }
            
            return studentList;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Collections.emptyList();
    }
    
    public Optional<Student> findCnp(int cnp){
        
        Student student = null;
        
        try {
            stmt4.setInt(1, cnp);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try (ResultSet rs = stmt4.executeQuery()){
            
            while(rs.next()){
                student = new Student(
                
                    rs.getInt("id"),
                    rs.getString("Nume"),
                    rs.getString("Nume"),
                    rs.getInt("Cnp")    
                
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Optional.ofNullable(student);
        
    }
}