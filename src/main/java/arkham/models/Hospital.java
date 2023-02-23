package arkham.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 17.02.2023
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "hospitals")
public class Hospital {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "hospital_id_gen")
    @SequenceGenerator(name = "hospital_id_gen", sequenceName = "hospital_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String address;

    private String image;

    public Hospital(String name, String address, String image) {
        this.name = name;
        this.address = address;
        this.image = image;
    }

    @OneToMany(mappedBy = "hospital",cascade = {ALL})
    private List<Doctor> doctors = new ArrayList<>();
    public void addDoctor(Doctor doctor){
        if (doctors==null){
            doctors=new ArrayList<>();
        }
        doctors.add(doctor);
    }

    @OneToMany(mappedBy = "hospital", cascade = {ALL})
    private List<Patient> patients = new ArrayList<>();
    public void addPatient(Patient patient){
        if (patients==null){
            patients=new ArrayList<>();
        }
        patients.add(patient);
    }

    @OneToMany(mappedBy = "hospital",cascade = {ALL})
    private List<Department> departments = new ArrayList<>();
    public void addDepartment(Department department){
        if (departments==null){
            departments=new ArrayList<>();
        }
        departments.add(department);
    }

    @OneToMany(cascade = {ALL})
    private List<Appointment> appointments = new ArrayList<>();
    public void addAppointment(Appointment appointment) {
        if (appointments==null){
            appointments=new ArrayList<>();
        }
        appointments.add(appointment);
    }
}
