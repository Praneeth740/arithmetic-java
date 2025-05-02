package arbitraryarithmetic;
import arbitraryarithmetic.AInteger; 
public class AFloat {
    public String value;

    public  AFloat(){
        this.value= "0.0";
    }

    public AFloat(String s){
        this.value=s;
    }

    public AFloat(AInteger copy){
        this.value=copy.value; 
    }

    public AFloat parse(String s){
        AFloat S = new AFloat(s);
        return S;
    }
    //A function to remove dot (1.02->102)
    public String removeDot(String str){
        int i=0;
        while(i<str.length()){
            if(str.charAt(i)=='.') break;
            i++;
        }
        str=str.substring(0,i)+str.substring(i+1);
        return str;
    }
    //A function to return the index of '.'
    public int dotAt(String str){
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') return i;
        }
        return -1;
    }
    
    //A function that adds zeros in the right to make the length of decimal part of both strings to be same.
    public String[] addEndZeros(String str1, String str2) {
        str1 = normalize(str1);
        str2 = normalize(str2);
    
        int dot1 = str1.indexOf('.');
        int dot2 = str2.indexOf('.');
    
        String s1 = str1.substring(dot1 + 1);
        String s2 = str2.substring(dot2 + 1);
    
        int l1 = s1.length();
        int l2 = s2.length();
    
        if (l1 < l2) {
            s1 += "0".repeat(l2 - l1);
        } else if (l2 < l1) {
            s2 += "0".repeat(l1 - l2);
        }
    
        str1 = str1.substring(0, dot1 + 1) + s1;
        str2 = str2.substring(0, dot2 + 1) + s2;
    
        return new String[]{str1, str2};
    }
    //A function to normalise various valid strings. (2. -> 2.0)
    private String normalize(String s) {
        if (!s.contains(".")) return s + ".0";
        if (s.endsWith(".")) return s + "0";
        return s;
    }
    //A function to check if the given string is a valid float.
    public int isFloat(String str) {
        //Handling null/empty strings.
        if (str == null || str.length() == 0) return 0;
        //Checking the repetition of '-' more than once in negative inputs.
        int start = 0;
        if (str.charAt(0) == '-') {
            if (str.length() == 1) return 0;
            start = 1;
        }
    
        int dotCount = 0;
        int digitCount = 0;
    
        for (int i = start; i < str.length(); i++) {
            char c = str.charAt(i);
    
            if (c == '.') {
                dotCount++;
                if (dotCount > 1) return 0;
                if ((i == start || !Character.isDigit(str.charAt(i - 1))) &&
                    (i == str.length() - 1 || !Character.isDigit(str.charAt(i + 1)))) {
                    return 0;
                }
    
            } else if (Character.isDigit(c)) {
                digitCount++;
            } else {
                return 0;
            }
        }
    
        return (digitCount > 0) ? 1 : 0;
    }
    //A function to check if the string is empty/null.
    public static boolean isZero(String num) {
        if (num == null || num.isEmpty()) return false;
        
        int startIndex = 0;
        if (num.charAt(0) == '-') {
            startIndex = 1;
        }
        
        String value = num.substring(startIndex);
        String[] parts = value.split("\\.");
        
        
        String integerPart = parts[0].isEmpty() ? "0" : parts[0];
        for (char c : integerPart.toCharArray()) {
            if (c != '0') return false;
        }
        
        if (parts.length > 1) {
            for (char c : parts[1].toCharArray()) {
                if (c != '0') return false;
            }
        }
        
        return true;
    }
    //A function to remove to sign. (for easy handling)
    private static String stripSign(String s) {
        return s.startsWith("-") ? s.substring(1) : s;
    }
    private static String removeDecimal(String s) {
        return s.replace(".", "");
    }
    public static int[] countDecimals(String s) {
        int dotIndex = s.indexOf('.');
        if (dotIndex == -1) {
            return new int[] { s.length(), 0 };
        } else {
            int integerPart = dotIndex;
            int decimalPart = s.length() - dotIndex - 1;
            return new int[] { integerPart, decimalPart };
        }
    
    }
    //A function to add zeros at the end.
    private static String repeatZeros(int count) {
        String result = "";
        for (int i = 0; i < count; i++) {
            result += "0";
        }
        return result;
    }
    //A method to handle adding various float strings using 'addString' as core logic.
    public String add(String str1,String str2){
        //Handling invalid inputs.
        if ((isFloat(str1) == 0 && AInteger.isInt(str1) == 0) || 
                        (isFloat(str2) == 0 && AInteger.isInt(str2) == 0)){
            throw new IllegalArgumentException("Inputs must be Floating numbers.");
        }
        //Padding decimal parts of both strings.
        String[] padded = addEndZeros(str1, str2);
        str1 = padded[0];
        str2 = padded[1];
        int idx=str1.length()-dotAt(str1)-1;
        str1=removeDot(str1);
        str2=removeDot(str2); 
        //Hadling cases in which one of the inputs is negative.
        if(str1.charAt(0)=='-' && str2.charAt(0)!='-'){
            str1=str1.substring(1);
            str1=AInteger.removeLeadingZeros(str1);
            str2=AInteger.removeLeadingZeros(str2);
            if(AInteger.grtString(str1, str2)==1){
                String result=AInteger.subString(str1,str1.length(),str2,str2.length());
                result='-'+result;
                while (result.length() <= idx) {
                    result = "0" + result;
                }
                result=result.substring(0,result.length()-idx)+"."+result.substring(result.length()-idx);
                return (AInteger.grtString(result ,"-0")==2) ? "0" :result;
            } else if(AInteger.grtString(str1, str2)==0){
                String result=AInteger.subString(str2,str2.length(),str1,str1.length());
                while (result.length() <= idx) {
                    result = "0" + result;
                }
                result=result.substring(0,result.length()-idx)+"."+result.substring(result.length()-idx);
                return (AInteger.grtString(result ,"-0")==2) ? "0" :result;
            }
            else if (AInteger.grtString(str1, str2)==2){
                return "0.0";
            }
        }
        else if (str2.charAt(0)=='-' && str1.charAt(0)!= '-'){
            str2=str2.substring(1);
            str1=AInteger.removeLeadingZeros(str1);
            str2=AInteger.removeLeadingZeros(str2);
            if(AInteger.grtString(str1, str2)==1){
                String result=AInteger.subString(str1,str1.length(),str2,str2.length());
                while (result.length() <= idx) {
                    result = "0" + result;
                }
                result=result.substring(0,result.length()-idx)+"."+result.substring(result.length()-idx);
                return (AInteger.grtString(result ,"-0")==2) ? "0" :result;
            } else if(AInteger.grtString(str1, str2)==0){
                String result=AInteger.subString(str2,str2.length(),str1,str1.length());
                
                while (result.length() <= idx) {
                    result = "0" + result;
                }
                result=result.substring(0,result.length()-idx)+"."+result.substring(result.length()-idx);
                result='-'+result;
                return (AInteger.grtString(result ,"-0")==2) ? "0" :result;
            }
            else if (AInteger.grtString(str1, str2)==2){
                return "0.0";
            }

        }
        //Handling cases in which both inputs are negative.
        else if (str2.charAt(0)=='-' && str1.charAt(0)== '-'){
            str1=str1.substring(1);
            str2=str2.substring(1);
            str1=AInteger.removeLeadingZeros(str1);
            str2=AInteger.removeLeadingZeros(str2);
            String result=AInteger.addString(str1,str1.length(),str2,str2.length());
            
            while (result.length() <= idx) {
                result = "0" + result;
            }
            result=result.substring(0,result.length()-idx)+"."+result.substring(result.length()-idx);
            result='-'+result;
            return (AInteger.grtString(result ,"-0")==2) ? "0" :result;
            
        }
        //Handling cases in which both inputs are positive.
        else{
            str1=AInteger.removeLeadingZeros(str1);
            str2=AInteger.removeLeadingZeros(str2);
            String result=AInteger.addString(str1,str1.length(),str2,str2.length());
            while (result.length() <= idx) {
                result = "0" + result;
            }
            result=result.substring(0,result.length()-idx)+"."+result.substring(result.length()-idx);
            return result;
        }

        return "";
    }
    //A method to handle subracting various float strings using 'subString' as core logic.
    public String sub(String str1, String str2){
        //Handling invalid inputs.
        if ((isFloat(str1) == 0 && AInteger.isInt(str1) == 0) || 
            (isFloat(str2) == 0 && AInteger.isInt(str2) == 0)) {
            throw new IllegalArgumentException("Inputs must be Floating numbers.");
        }
        //Padding the decimal part of both the strings.
        String[] padded = addEndZeros(str1, str2);
        str1 = padded[0];
        str2 = padded[1];
        int idx = str1.length() - dotAt(str1) - 1;
    
        str1 = removeDot(str1);
        str2 = removeDot(str2); 
        //If one of the inputs is negative.
        if(str1.charAt(0) == '-' && str2.charAt(0) != '-') {
            str1 = str1.substring(1);
            str1 = AInteger.removeLeadingZeros(str1);
            str2 = AInteger.removeLeadingZeros(str2);
            String result = AInteger.addString(str1, str1.length(), str2, str2.length());
            while (result.length() <= idx) {
                result = "0" + result;
            }
            result = result.substring(0, result.length() - idx) + "." + result.substring(result.length() - idx);
            result = '-' + result;
            return (AInteger.grtString(result, "-0") == 2) ? "0" : result;
    
        } else if(str2.charAt(0) == '-' && str1.charAt(0) != '-') {
            str2 = str2.substring(1);
            str1 = AInteger.removeLeadingZeros(str1);
            str2 = AInteger.removeLeadingZeros(str2);
            String result = AInteger.addString(str1, str1.length(), str2, str2.length());
            while (result.length() <= idx) {
                result = "0" + result;
            }
            result = result.substring(0, result.length() - idx) + "." + result.substring(result.length() - idx);
            return (AInteger.grtString(result, "-0") == 2) ? "0" : result;
    
        } 
        //if both the inputs are negative.
        else if(str2.charAt(0) == '-' && str1.charAt(0) == '-') {
            str1 = str1.substring(1);
            str2 = str2.substring(1);
            str1 = AInteger.removeLeadingZeros(str1);
            str2 = AInteger.removeLeadingZeros(str2);
            if (AInteger.grtString(str1, str2) == 1) {
                String result = AInteger.subString(str1, str1.length(), str2, str2.length());
                while (result.length() <= idx) {
                    result = "0" + result;
                }
                result = result.substring(0, result.length() - idx) + "." + result.substring(result.length() - idx);
                result = '-' + result;
                return (AInteger.grtString(result, "-0") == 2) ? "0" : result;
    
            } else if (AInteger.grtString(str1, str2) == 0) {
                String result = AInteger.subString(str2, str2.length(), str1, str1.length());
                while (result.length() <= idx) {
                    result = "0" + result;
                }
                result = result.substring(0, result.length() - idx) + "." + result.substring(result.length() - idx);
                return (AInteger.grtString(result, "-0") == 2) ? "0" : result;
    
            } else if (AInteger.grtString(str1, str2) == 2) {
                return "0.0";
            }
    
        } 
        //if both inputs are positive.
        else {
            str1 = AInteger.removeLeadingZeros(str1);
            str2 = AInteger.removeLeadingZeros(str2);
            if (AInteger.grtString(str1, str2) == 1) {
                String result = AInteger.subString(str1, str1.length(), str2, str2.length());
                while (result.length() <= idx) {
                    result = "0" + result;
                }
                result = result.substring(0, result.length() - idx) + "." + result.substring(result.length() - idx);
                return result;
    
            } else if (AInteger.grtString(str1, str2) == 0) {
                String result = AInteger.subString(str2, str2.length(), str1, str1.length());
                while (result.length() <= idx) {
                    result = "0" + result;
                }
                result = result.substring(0, result.length() - idx) + "." + result.substring(result.length() - idx);
                result = '-' + result;
                return result;
    
            } else if (AInteger.grtString(str1, str2) == 2) {
                return "0.0";
            }
        }
    
        return "";
    }
    //A method to handle multipling various float strings using 'mulString' as core logic.
    public String mul(String str1, String str2){
        //Handling invalid inputs.
        if ((isFloat(str1) == 0 && AInteger.isInt(str1) == 0) || 
            (isFloat(str2) == 0 && AInteger.isInt(str2) == 0)) {
            throw new IllegalArgumentException("Inputs must be Floating numbers.");
        }
    
        str1 = normalize(str1);
        str2 = normalize(str2);
        //Handling inputs based on their signs.
        boolean neg = false;
        if (str1.charAt(0) == '-' && str2.charAt(0) != '-') {
            neg = true;
            str1 = str1.substring(1);
        } else if (str2.charAt(0) == '-' && str1.charAt(0) != '-') {
            neg = true;
            str2 = str2.substring(1);
        } else if (str2.charAt(0) == '-' && str1.charAt(0) == '-') {
            str1 = str1.substring(1);
            str2 = str2.substring(1);
        }
    
        str1 = normalize(str1);
        str2 = normalize(str2);
    
        int dec1 = str1.length() - dotAt(str1) - 1;
        int dec2 = str2.length() - dotAt(str2) - 1;
        int totalDecimals = dec1 + dec2;
        //Removing dot and multiplying them treating as integers.
        str1 = removeDot(str1);
        str2 = removeDot(str2);
    
        str1 = AInteger.removeLeadingZeros(str1);
        str2 = AInteger.removeLeadingZeros(str2);
    
        String result = AInteger.mulString(str1, str1.length(), str2, str2.length());
    
        while (result.length() <= totalDecimals) {
            result = "0" + result;
        }
        //Inserting the '.' in correct position of product.
        result = result.substring(0, result.length() - totalDecimals) + "." + result.substring(result.length() - totalDecimals);
        //Adding '-' if neccessary.
        if (neg && !result.equals("0.0")) result = "-" + result;
    
        return result;
    }
    //A method to handle dividing various float strings using 'divString' as core logic.
    public static String div(String str1, String str2) {
        //Handling invalid inputs.
        if (isZero(str2)){
            throw new ArithmeticException("Division by zero");
        }
        //Handling inputs with various signs.
        boolean negative = (str1.startsWith("-") != str2.startsWith("-")) 
            && !isZero(str1);
        str1 = stripSign(str1);
        str2 = stripSign(str2);
        int[] decDividend = countDecimals(str1);
        int[] decDivisor = countDecimals(str2);
        String num1 = removeDecimal(str1);
        String num2 = removeDecimal(str2);
        int precision = 30;    //Precision.
        int maxDecimals = Math.max(decDividend[1], decDivisor[1]);
        num1 = removeDecimal(str1) + repeatZeros(maxDecimals - decDividend[1] + precision);
        num2 = removeDecimal(str2) + repeatZeros(maxDecimals - decDivisor[1]);
        String quotient = AInteger.div(num1, num2);
        int decimalShift = decDividend[1] - decDivisor[1];
        int dotPos = quotient.length() + decimalShift - (precision+maxDecimals)+4; //calculating where '.' should sit in quotient.
        
        StringBuilder result = new StringBuilder();
        if (dotPos <= 0) {                                 //If the quotient is purely fractional
            result.append("0.");
            result.append(repeatZeros(-dotPos));
            result.append(quotient);
        } else {
            result.append(quotient.substring(0, dotPos));
            result.append(".");
            result.append(quotient.substring(dotPos));
        }

        String finalResult = result.toString()
            .replaceAll("0+$", "")
            .replaceAll("\\.$", ".0");

        return (negative ? "-" : "") + finalResult;
    }

}