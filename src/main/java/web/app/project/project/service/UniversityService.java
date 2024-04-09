package web.app.project.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.app.project.project.entities.University;
import web.app.project.project.repositories.UniversityRepository;

import java.util.List;

@Service
public class UniversityService {

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public University saveUniversity(University university) {
        return universityRepository.save(university);
    }

    public University findUniversityById(Long id) {
        return universityRepository.findById(id).orElse(null);
    }

    public List<University> getAllUniversities() {
        return (List<University>) universityRepository.findAll();
    }

    public boolean deleteUniversityById(Long id) {
        if (universityRepository.existsById(id)) {
            universityRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
