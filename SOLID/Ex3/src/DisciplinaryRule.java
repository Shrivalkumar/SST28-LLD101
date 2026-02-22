public class DisciplinaryRule implements EligibilityRule{
    @Override
    public String validate(StudentProfile profile){
        if(profile.disciplinaryFlag != LegacyFlags.NONE){
            return "disciplinary flag present";
        }
        return null;
    }
}
