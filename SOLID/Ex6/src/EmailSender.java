public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    public int getMaxLen(){
        return 40;
    }

    @Override
    public void giveNotification(Notification n, String finalBody) {
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + finalBody);
        audit.add("email sent");
    }
}
