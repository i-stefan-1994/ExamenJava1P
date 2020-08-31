
package model; 

public class Student { 

    private int id;
    private String nume;
    private String prenume;
    private int cnp;

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setCnp(int cnp) {
        this.cnp = cnp;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public int getCnp() {
        return cnp;
    }

    public Student(int id, String nume, String prenume, int cnp) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", nume=" + nume + ", prenume=" + prenume + ", cnp=" + cnp + '}';
    }
    
}