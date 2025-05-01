import arbitraryarithmetic.AInteger;
import arbitraryarithmetic.AFloat;

public class MyInfArith {
    public static void main(String[] args){
        String type=args[0];
        String op=args[1];
        String operand1=args[2];
        String operand2=args[3];
        String result=null;
        if(type.equalsIgnoreCase("int")){
            AInteger a=new AInteger(operand1);
            AInteger b=new AInteger(operand2);

            if(op.equals("add")){
                result=a.add(a.value,b.value);
            }else if(op.equals("sub")){
                result=AInteger.sub(a.value,b.value);
            }else if(op.equals("mul")){
                result=a.mul(a.value,b.value);
            }else if(op.equals("div")){
                result=AInteger.div(a.value,b.value);
            }else {
                System.out.println("Unknown operation: " + op);
                return;
            }
        }else if(type.equalsIgnoreCase("float")){
            AFloat a=new AFloat(operand1);
            AFloat b=new AFloat(operand2);

            if(op.equals("add")){
                result=a.add(a.value,b.value);
            }else if(op.equals("sub")){
                result=a.sub(a.value,b.value);
            }else if(op.equals("mul")){
                result=a.mul(a.value,b.value);
            }else if(op.equals("div")){
                result=AFloat.div(a.value,b.value);
            }else {
                System.out.println("Unknown operation: " + op);
                return;
            }
        }else {
            System.out.println("Unknown data type: "+type);
        }
        System.out.println(result);
    }
    
}
