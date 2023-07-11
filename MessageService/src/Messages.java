import java.util.Scanner;
import java.util.regex.*;
class OptionNotvalid  extends Exception
{
    public OptionNotvalid (String str)
    {
        // calling the constructor of parent Exception
        super(str);
    }
}

interface MessagingService{
    void sendMessage(String mobno,String msg);
    void sendMessage(String email,String subject,String msg);

}
 abstract class SMSmessagingService implements MessagingService{
    @Override
    public void sendMessage(String mobno,String msg) {
        System.out.println("To:"+mobno);
        System.out.println("Message:"+msg);
    }
}
 abstract class EmailMessagingService  extends SMSmessagingService {
    public void sendMessage(String email,String subject,String msg) {
        System.out.println("Email:"+email);
        System.out.println("Subject:"+subject);
        System.out.println("Message:"+msg);
    }

}
 class WhatsAppMessagingService extends EmailMessagingService{
    public void sendMessage(String sender,String reciever,String msg) {
        System.out.println("From:"+sender);
        System.out.println("To:"+reciever);
        System.out.println("Message:"+msg);
    }

}
public class Messages{
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter\n 1 for SMS\n 2 for email\n 3 for whatsapp");
        int option=scan.nextInt();
        if(option>3 ||option<1) {
            try {
                throw new OptionNotvalid("invalid Option");
            } catch (OptionNotvalid e) {
                System.out.println(e);
            }
        }
        switch (option){
            case 1:{
                MessagingService sms=new WhatsAppMessagingService();
                System.out.println("Enter mobile number:");
                String mobno=scan.next();
                Pattern p = Pattern.compile("(6|7|8|9|)?[0-9]{9}");
                Matcher m = p.matcher(mobno);
                boolean isvalid=(m.find() && m.group().equals(mobno));
                if(!isvalid){
                    System.out.println("Enter valid Mobile number");
                    break;

                }

                System.out.println("Enter your Message");
                String msg=scan.next();
                sms.sendMessage(mobno,msg);
                break;
            }
            case 2:{
                MessagingService email=new WhatsAppMessagingService();
                System.out.println("Enter Email Address:");
                String emailadd=scan.next();
                String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

                Pattern pattern = Pattern.compile(regex);
                Matcher m2 = pattern.matcher(emailadd);
                if(!m2.matches()){
                    System.out.println("Enter valid Email id");
                    break;
                }
                System.out.println("Subject:");
                String subject=scan.next();
                System.out.println("Enter your Message");
                String msg=scan.next();


                email.sendMessage(emailadd,subject,msg);
                break;

            }
            case 3:{
                MessagingService waweb=new WhatsAppMessagingService();
                System.out.println("Enter Sender Whatsapp Number");
                String sendwhats=scan.next();
                Pattern p = Pattern.compile("(6|7|8|9|)?[0-9]{9}");
                Matcher m = p.matcher(sendwhats);
                boolean isvalid=(m.find() && m.group().equals(sendwhats));
                if(!isvalid){
                    System.out.println("Enter valid sender whatsapp Mobile number");
                    break;

                }
                System.out.println("Enter Reciever Whatsapp Number");
                String recieverwhats=scan.next();
                Pattern p1 = Pattern.compile("(6|7|8|9|)?[0-9]{9}");
                Matcher m1 = p1.matcher(recieverwhats);
                boolean isvalid1=(m1.find() && m1.group().equals(recieverwhats));
                if(!isvalid1){
                    System.out.println("Enter valid Mobile number");
                    break;

                }
                System.out.println("Enter your Message");
                String msg=scan.next();



                waweb.sendMessage(sendwhats,recieverwhats,msg);
                break;

            }
        }



    }
}