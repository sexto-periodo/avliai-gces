package avaliai.subject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<Subject> getSubject() {
        return subjectService.getSubject();
    }

    @PostMapping
    public void registerNewSubject(@RequestBody Subject subject) {
        subjectService.addSubject(subject);
    }

    @GetMapping(path = "{subjectId}")
    public Subject findSubjectById(@PathVariable("subjectId") long id) {
        return subjectService.findSubjectById(id);
    }

    @DeleteMapping(path = "{subjectId}")
    public void deleteSubject(@PathVariable("subjectId") long id) {
        subjectService.deleteSubject(id);
    }

    @PutMapping(path = "{subjectId}")
    public void updateSubject(@PathVariable("subjectId") long subjectId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String picUrl,
            @RequestParam(required = false) String campus) {
        subjectService.updateSubject(subjectId, name, picUrl, campus);
    }

}
