package arkham.repositories;

import arkham.models.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 17.02.2023
 */
@Repository
public interface PatientRepo {
    List<Patient> findAll(Long id);

    Patient findById(Long patientId);

    void update(Patient patient);

    void save(Patient patient);

}
