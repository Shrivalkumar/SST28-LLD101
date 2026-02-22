import java.util.*;

public class OnboardingService {
    private final StudentRepository repository;
    private final InputParser parser;
    private final RegistrationValidator validator;
    private final OnboardingUI ui;

    public OnboardingService(StudentRepository repository, InputParser parser, RegistrationValidator validator, OnboardingUI ui) {
        this.repository = repository;
        this.parser = parser;
        this.validator = validator;
        this.ui = ui;
    }

    public void registerFromRawInput(String raw) {
        ui.printInput(raw);

        Map<String, String> data = parser.parse(raw);

        List<String> errors = validator.validate(data);
        if (!errors.isEmpty()) {
            ui.printErrors(errors);
            return;
        }
        String id = IdUtil.nextStudentId(repository.count());
        StudentRecord rec = new StudentRecord(
            id, 
            data.get("name"), 
            data.get("email"), 
            data.get("phone"), 
            data.get("program")
        );

        repository.save(rec);
        ui.printSuccess(rec, repository.count());
    }
}