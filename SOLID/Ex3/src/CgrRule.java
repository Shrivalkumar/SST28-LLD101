public class CgrRule implements EligibilityRule{
    @Override
    public String validate(StudentProfile profile){
        if(profile.cgr < 8.0){
            return "cgr below 8.0";
        }
        return null;
    }
}
