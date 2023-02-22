package rs.ac.singidunum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.StudijskiProgram;
import rs.ac.singidunum.app.repository.StudijskiProgramRepository;

import java.util.Optional;

@Service
public class StudijskiProgramService {
    @Autowired
    private StudijskiProgramRepository studijskiProgramRepository;

    public StudijskiProgramService() {super();
    }

    public StudijskiProgramService(StudijskiProgramRepository studijskiProgramRepository) {
        this.studijskiProgramRepository = studijskiProgramRepository;
    }

    public StudijskiProgramRepository getStudijskiProgramRepository() {
        return studijskiProgramRepository;
    }

    public void setStudijskiProgramRepository(StudijskiProgramRepository studijskiProgramRepository) {
        this.studijskiProgramRepository = studijskiProgramRepository;
    }

    public Iterable<StudijskiProgram> findAll() {
        return studijskiProgramRepository.findAll();
    }

    public Page<StudijskiProgram> findAll(Pageable pageable) {
        return studijskiProgramRepository.findAll(pageable);
    }

    public Optional<StudijskiProgram> findOne(Long id) {
        return studijskiProgramRepository.findById(id);
    }

    public StudijskiProgram save(StudijskiProgram studijskiProgram) {
        return studijskiProgramRepository.save(studijskiProgram);
    }

    public void delete(Long id) {
        studijskiProgramRepository.deleteById(id);
    }

    public void delete(StudijskiProgram studijskiProgram) {
        studijskiProgramRepository.delete(studijskiProgram);
    }

    //Metoda za pronalazenje svih Fakulteta studijskih programa
    public Iterable<StudijskiProgram> findFakultetStudijskogProgrmama(String naziv) {
        return studijskiProgramRepository.findFakultetStudijskogProgrmama(naziv);
    }

    //Metoda za pronalazenje svih Nastavnika studijskih programa
    public Iterable<StudijskiProgram> findNastavnikStudijskogProgrmama(String ime) {
        return studijskiProgramRepository.findNastavnikStudijskogProgrmama(ime);
    }

}
