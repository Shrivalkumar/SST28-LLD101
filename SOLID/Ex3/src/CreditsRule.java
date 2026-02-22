public class CreditsRule implements EligibilityRule{
    @Override
    public String validate(StudentProfile profile ){
        if(profile.earnedCredits < 20){
            return "credits below 20";
        }
        return null;
    }
}
