package e_commerce.epda_assignment.model.output;

import e_commerce.epda_assignment.utility.Static;
import lombok.Data;

@Data
public class LoginOutput {
    private Static.accType accType;
    private Object profile;
    private boolean status;

    public LoginOutput(boolean status){
        this.status = status;
    }

    public LoginOutput(Static.accType accType, Object profile){
        this.accType = accType;
        this.profile = profile;
        this.status = true;
    }
}
