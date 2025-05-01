package arbitraryarithmetic;
public class AInteger{
    public String value;

    public  AInteger(){
        this.value= "0";
    }

    public AInteger(String s){
        this.value=s;
    }

    public AInteger(AInteger copy){
        this.value=copy.value; 
    }

    public AInteger parse(String s){
        AInteger S = new AInteger(s);
        return S;
    }

    public static String reverseString(String s){
        String result ="";
        for( int i =0; i<s.length(); i++){
            result+=s.charAt(s.length()-1-i);
        }
        return result;
    }

    public static int grtString(String str1, String str2) {
        str2 = removeLeadingZeros(str2);
        str1 = removeLeadingZeros(str1);
        if (str1.length() > str2.length()) {
            return 1;
        } else if (str1.length() < str2.length()) {
            return 0;
        } else {
            int i = 0;
            while (i < str1.length()) {
                if (str1.charAt(i) > str2.charAt(i)) {
                    return 1;
                } else if (str1.charAt(i) < str2.charAt(i)) {
                    return 0;
                }
                i++;
            }
            return 2;
        }
    }
    
    public static int isMultiple(int x,int y){
        int a=0;
        if(y%x==0){
            return a+1;
        }else{
            return a;
        }
    }
    public static String removeLeadingZeros(String str){
        int i = 0;
        if(str.charAt(0)=='-'){
            i=i+1;
        }
        while (i < str.length() && str.charAt(i) == '0') {
            i++;
        }
        if (i == str.length()) {
            return "0";
        }
        if (str.charAt(0)=='-'){
            return '-' + str.substring(i);
        }
        return str.substring(i);
    }

    public static int isInt(String str1){
        if(str1.length() == 0) return 0;
        int start = 0;
        if (str1.charAt(0) == '-') {
            if (str1.length() == 1) return 0;
            start = 1;
            str1=str1.substring(1);
        }
        for (int i = 0; i < str1.length(); i++) {
            if (!(Character.isDigit(str1.charAt(i)))) {
                return 0;
            }
        }
        return 1;
    }
    
     

 