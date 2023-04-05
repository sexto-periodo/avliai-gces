package avaliai.subject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;

    }

    public List<Subject> getSubject() {
        return subjectRepository.findAll();
    }

    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    public Subject findSubjectById(long id) {
        return subjectRepository.findSubjectById(id);
    }

    public void deleteSubject(long id) {
        if (!subjectRepository.existsById(id))
            throw new IllegalStateException("Não conseguimos encontrar a disciplina");// criar uma exceção própria
        subjectRepository.deleteById(id);
    }

    @Transactional
    public void updateSubject(long subjectId, String name, String picUrl, String campus) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new IllegalStateException(
                "Não conseguimos encontra a disciplina"));// exceção própria
        subject.setName(name);
        subject.setPicUrl(picUrl);
        subject.setCampus(campus);
    }

}
