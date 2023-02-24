package arkham.repositories.repoImpl;

import arkham.models.Appointment;
import arkham.models.Department;
import arkham.models.Hospital;
import arkham.repositories.AppointmentRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 17.02.2023
 */
@Repository

@Transactional
public class AppointmentRepoImpl implements AppointmentRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    public AppointmentRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Appointment> findAll(Long hospitalId) {
        return entityManager.createQuery("select a from Hospital h join h.appointments a where h.id=:id", Appointment.class)
                .setParameter("id", hospitalId)
                .getResultList();
    }



    @Transactional
    @Override
    public Appointment findById(Long appointmentId) {
        return entityManager.find(Appointment.class, appointmentId);
    }

    @Override
    public void update(Long appointmentId, Appointment appointment) {
        Appointment appointment1 = entityManager.find(Appointment.class, appointmentId);
        appointment1.setDate(appointment.getDate());
        entityManager.merge(appointment1);
    }


    @Override
    public Appointment save(Appointment appointment) {
        entityManager.persist(appointment);
        return appointment;
    }

    @Override
    public void deleteAppointment(Long appointmentId) {
//        try {
//            List<Hospital> hospitals = entityManager.createQuery("from Hospital h where h.id=:id", Hospital.class).setParameter("id", appointmentId).getResultList();
//            hospitals.forEach(s -> s.getAppointments().removeIf(a -> a.getId().equals(appointmentId)));
//            entityManager.remove(entityManager.find(Appointment.class, appointmentId));
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }
        entityManager.createQuery("delete from Appointment where id=:id", Department.class).setParameter("id", appointmentId).executeUpdate();
    }
}
