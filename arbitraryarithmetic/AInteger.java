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
    public static String addString(String str1,int l1, String str2,int l2){
        String result="";
        int carry=0;
        if (l2 > l1) {
            String tempStr = str1;
            str1 = str2;
            str2 = tempStr;

            int tempLen = l1;
            l1 = l2;
            l2 = tempLen;
        }
        for( int i=l1-1,j=l2-1; i>=l1-l2 && j>=0; i--,j--){
            int v1=str1.charAt(i)-'0';
            int v2=str2.charAt(j)-'0';
            int sum = v1 + v2 + carry;
            result = (sum % 10)+ result;
            carry = sum / 10;

        }
        for( int i=l1-l2-1 ; i>=0; i--){
            int v1=str1.charAt(i)-'0';
            int sum=v1+carry;
            result=(sum)%10+result;
            carry=sum/10;
        }
        while (carry > 0) {
            result = (carry % 10)+result;
            carry /= 10;
        }
                
        return result;
    }

    public static String subString(String str1, int l1, String str2, int l2) {
        String result = "";
        if (l2 > l1) {
            String tempStr = str1;
            str1 = str2;
            str2 = tempStr;
    
            int tempLen = l1;
            l1 = l2;
            l2 = tempLen;
        }
    
        int carry = 0;
        for (int i = l1 - 1, j = l2 - 1; i >= l1 - l2 && j >= 0; i--, j--) {
            int v1 = (i>=0) ? str1.charAt(i) - '0': 0;
            int v2 = (j>=0) ? str2.charAt(j) - '0': 0;
    
            v1 -= carry;
            
            if (v1 >= v2) {
                result += (v1 - v2);
                carry = 0;
            } else { 
                v1 += 10;
                result += (v1 - v2);
                carry = 1;
            }
        }
        for (int i = l1 - l2 - 1; i >= 0; i--) {
            int v1 = str1.charAt(i) - '0';
            v1 -= carry;
            
            if (v1 >= 0) {
                result += v1;
                carry = 0;
            } else {
                v1 += 10;
                result += v1;
                carry = 1;
            }
        }
    
        return removeLeadingZeros(reverseString(result));
    }


    public static String mulString(String str1, int l1, String str2, int l2){
        String result = "0";
        if (l2 > l1) {
            String tempStr = str1;
            str1 = str2;
            str2 = tempStr;
    
            int tempLen = l1;
            l1 = l2;
            l2 = tempLen;
        }
        for( int i=l2-1;i>=0; i--){
            String tempString="";
            int carry=0;
            for(int j=l1-1;j>=0;j--){
                int v1=str1.charAt(j)-'0';
                int v2=str2.charAt(i)-'0';
                if (v1*v2+carry<10){
                    tempString=(char)(v1*v2+carry+'0')+tempString;
                    carry=0;
                } else if(v1*v2+carry>=10){
                    tempString=(char)((v1*v2+carry)%10+'0')+tempString;
                    carry=(v1*v2+carry)/10;
                }
            }
            while(carry>0){
                tempString=(char)(carry%10+'0')+tempString;
                carry=carry/10; 
            }
            for(int j=0;j<l2-i-1;j++){
                tempString+="0";
            }
            result=addString(result, result.length(), tempString, tempString.length());
            
        }
        return result;
    }

    public static String divString(String str1, int l1, String str2, int l2) {
        str1 = removeLeadingZeros(str1);
        str2 = removeLeadingZeros(str2);
        if (grtString(str1, str2) == 0) return "0";
        if (grtString(str1, str2) == 2) return "1";
    
        String result = "";
        String strip = "";
    
        int idx = 0;
        while (idx < str1.length()) {
            strip += str1.charAt(idx);
            strip = removeLeadingZeros(strip);
            int count = 0;
            while (grtString(strip, str2) == 1 || grtString(strip, str2) == 2) {
                strip = subString(strip, strip.length(), str2, l2);
                strip = removeLeadingZeros(strip);
                count++;
            }
            result += (char)(count + '0');
            idx++;
        }
    
        return removeLeadingZeros(result);
    }

    public String add(String str1,String str2){
        if(isInt(str1)==0 || isInt(str2)==0){
            throw new IllegalArgumentException("Inputs must be Integers.");
        }
        if(str1.charAt(0)=='-' && str2.charAt(0)!='-'){
            str1=str1.substring(1);
            str1=removeLeadingZeros(str1);
            str2=removeLeadingZeros(str2);
            if(grtString(str1, str2)==1){
                String result=subString(str1,str1.length(),str2,str2.length());
                result='-'+result;
                return (grtString(result ,"-0")==2) ? "0" :result;
            } else if(grtString(str1, str2)==0){
                String result=subString(str2,str2.length(),str1,str1.length());
                return (grtString(result ,"-0")==2) ? "0" :result;
            }
            else if (grtString(str1, str2)==2){
                return "0";
            }
        }
        else if (str2.charAt(0)=='-' && str1.charAt(0)!= '-'){
            str2=str2.substring(1);
            str1=removeLeadingZeros(str1);
            str2=removeLeadingZeros(str2);
            if(grtString(str1, str2)==1){
                String result=subString(str1,str1.length(),str2,str2.length());
                return (grtString(result ,"-0")==2) ? "0" :result;
            } else if(grtString(str1, str2)==0){
                String result=subString(str2,str2.length(),str1,str1.length());
                result='-'+result;
                return (grtString(result ,"-0")==2) ? "0" :result;
            }
            else if (grtString(str1, str2)==2){
                return "0";
            }

        }else if (str2.charAt(0)=='-' && str1.charAt(0)== '-'){
            str1=str1.substring(1);
            str2=str2.substring(1);
            str1=removeLeadingZeros(str1);
            str2=removeLeadingZeros(str2);
            String result=addString(str1,str1.length(),str2,str2.length());
            result='-'+result;
            return (grtString(result ,"-0")==2) ? "0" :result;
            
        }else{
            str1=removeLeadingZeros(str1);
            str2=removeLeadingZeros(str2);
            String result=addString(str1,str1.length(),str2,str2.length());
            return result;
        }
        return "";
    }
    public static String sub(String str1,String str2){
        if(isInt(str1)==0 || isInt(str2)==0){
            throw new IllegalArgumentException("Inputs must be Integers.");
        }
        if(str1.charAt(0)=='-' && str2.charAt(0)!='-'){
            str1=str1.substring(1);
            str1=removeLeadingZeros(str1);
            str2=removeLeadingZeros(str2);
            String result=addString(str1,str1.length(),str2,str2.length());
            result='-'+result;
            return (grtString(result ,"-0")==2) ? "0" :result; 
        }else if(str2.charAt(0)=='-' && str1.charAt(0)!='-'){
            str2=str2.substring(1);
            str1=removeLeadingZeros(str1);
            str2=removeLeadingZeros(str2);
            String result=addString(str1,str1.length(),str2,str2.length());
            return (grtString(result ,"-0")==2) ? "0" :result; 
        }else if (str2.charAt(0)=='-' && str1.charAt(0)== '-'){
            str1=str1.substring(1);
            str2=str2.substring(1);
            str1=removeLeadingZeros(str1);
            str2=removeLeadingZeros(str2);
            if(grtString(str1, str2)==1){
                String result=subString(str1,str1.length(),str2,str2.length());
                result='-'+result;
                return (grtString(result ,"-0")==2) ? "0" :result;
            }else if(grtString(str1,str2)==0){
                String result=subString(str2,str2.length(),str1,str1.length());
                return (grtString(result ,"-0")==2) ? "0" :result;
            }else if(grtString(str1, str2)==2){
                return "0";
            }
            
            
        }else{
            str1=removeLeadingZeros(str1);
            str2=removeLeadingZeros(str2);
            if(grtString(str1, str2)==1){
                String result=subString(str1,str1.length(),str2,str2.length());
                return result;
            }else if(grtString(str1,str2)==0){
                String result=subString(str1,str1.length(),str2,str2.length());
                result='-'+result;
                return result;
            }else if(grtString(str1,str2)==2){
                return "0";
            }
        }
        return "";

    }
    
    public String mul(String str1,String str2){
        if(isInt(str1)==0 || isInt(str2)==0){
            throw new IllegalArgumentException("Inputs must be Integers.");
        }
        if(str1.charAt(0)=='-' && str2.charAt(0)!='-'){
            str1=str1.substring(1);
            str1=removeLeadingZeros(str1);
            str2=removeLeadingZeros(str2);
            String result=mulString(str1,str1.length(), str2, str2.length());
            result='-'+result;
            return result;
        }else if(str2.charAt(0)=='-' && str1.charAt(0)!='-'){
            str2=str2.substring(1);
            str1=removeLeadingZeros(str1);
            str2=removeLeadingZeros(str2);
            String result=mulString(str1,str1.length(), str2, str2.length());
            result='-'+result;
            return result;
        }else if(str2.charAt(0)=='-' && str1.charAt(0)=='-'){
            str1=str1.substring(1);
            str2=str2.substring(1);
            str1=removeLeadingZeros(str1);
            str2=removeLeadingZeros(str2);
            String result=mulString(str1,str1.length(), str2, str2.length());
            return result;
        }else{
            str1=removeLeadingZeros(str1);
            str2=removeLeadingZeros(str2);
            String result=mulString(str1,str1.length(), str2, str2.length());
            return result;
        } 
    }

    public  static String div(String str1, String str2) {
        if (isInt(str1) == 0 || isInt(str2) == 0) {
            throw new IllegalArgumentException("Inputs must be valid integers.");
        }
        if (removeLeadingZeros(str2).equals("0")) {
            throw new ArithmeticException("Division by zero is undefined.");
        }
    
        boolean negative = false;
        if (str1.charAt(0) == '-' && str2.charAt(0) != '-') {
            negative = true;
            str1 = str1.substring(1);
        } else if (str2.charAt(0) == '-' && str1.charAt(0) != '-') {
            negative = true;
            str2 = str2.substring(1);
        } else if (str2.charAt(0) == '-' && str1.charAt(0) == '-') {
            str1 = str1.substring(1);
            str2 = str2.substring(1);
        }
        str1 = removeLeadingZeros(str1);
        str2 = removeLeadingZeros(str2);
        String result = divString(str1, str1.length(), str2, str2.length());
        if (negative && !result.equals("0")) {
            result = "-" + result;
        }
    
        return result;
    }
}   
     

 