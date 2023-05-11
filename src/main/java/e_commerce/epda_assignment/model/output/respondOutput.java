package e_commerce.epda_assignment.model.output;

import lombok.Data;

@Data
public class respondOutput {
    private boolean status;
    private Object updatedObject;
    public respondOutput(boolean status){
        this.status = status;
        this.updatedObject = null;
    }
    public respondOutput(Object object){
        this.status = true;
        this.updatedObject = object;
    }
}
