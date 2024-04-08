package web.app.project.project.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.app.project.project.entities.University;
import web.app.project.project.repositories.UniversityRepository;

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

}

