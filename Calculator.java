import java.util.Scanner;

public class Calculator
{
    static String MtdRomItoa(int num)
    {
        String      dec;
        String[]    rom_st;
        int[]       rom_enum;
        int         i;

        i = 0;
        dec = "";
        rom_st = new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        rom_enum = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        while (num > 0)
        {
            if (num >= rom_enum[i])
            {
                dec = dec.concat(rom_st[i]);
                num = (num - rom_enum[i]);
                i = 0;
            }
            i++;
        }
        return (dec);
    }
    static int  MtdRomAtoi(String str) throws Exception {
        int         n;
        int         i;
        int         t;
        char[]      rom_ch;
        int[]       rom_num;

        i = 0;
        n = 0;
        t = 1;
        rom_ch = new char[] {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        rom_num = new int[] {1000, 500, 100, 50, 10, 5, 1};
        for (int count = str.length(); count > 0; --count)
        {
            while (i < 7)
            {
                if (str.charAt(count - 1) == rom_ch[i])
                {
                    if (t <= rom_num[i])
                    {
                        n += rom_num[i];
                        t = rom_num[i];
                        break;
                    }
                    else
                    {
                        n -= rom_num[i];
                        t = rom_num[i];
                        break;
                    }
                }
                i++;
            }
            i = 0;
        }
        if (n <= 0)
            throw new Exception("Римские числа не могут быть отрицательны или равны нулю!");
        return(n);
    }
    static int  GetSign(String str) throws Exception
    {
        String act;

        act = "+-/*";
        if (str.length() > 1)
            throw new Exception("Строка не является математической операцией");
        for (int i = 0; i < act.length(); i++)
        {
            if (str.charAt(0) == act.charAt(i))
                return (i);
        }
        throw new Exception("Строка не является математическим выражением");
    }
    static int  MtdAtoi(String str)
    {
        int n;
        int m;
        int i;

        m = 1;
        i = 0;
        n = 0;
        if (str.charAt(i) == '-')
        {
            m *= -1;
            i++;
        }
        while (i < str.length())
           n =  ((n * 10) + (str.charAt(i++) - '0'));
        return (n * m);
    }
    static int  MtdAnswerArab(String[] mass) throws Exception
    {
        int num1;
        int num2;
        int ans;

        ans = 0;
        num1 = MtdAtoi(mass[0]);
        num2 = MtdAtoi(mass[2]);
        switch (GetSign(mass[1]))
        {
            case 0:
                ans = (num1 + num2);
                break;
            case 1:
                ans = (num1 - num2);
                break;
            case 2:
                ans = (num1 / num2);
                break;
            case 3:
                ans = (num1 * num2);
                break;
        }
        return (ans);
    }

    static String  MtdAnswerRoman(String[] mass) throws Exception
    {
        String  dec;
        int     ans;
        int     num1;
        int     num2;

        ans = 0;
        num1 = MtdRomAtoi(mass[0]);
        num2 = MtdRomAtoi(mass[2]);
        switch (GetSign(mass[1]))
        {
            case 0:
                ans = (num1 + num2);
                break;
            case 1:
                ans = (num1 - num2);
                break;
            case 2:
                ans = (num1 / num2);
                break;
            case 3:
                ans = (num1 * num2);
                break;
        }
        if (ans <= 0)
            throw new Exception("В римской системе не моожет быть нулевых или отрицательных значений!");
        dec = MtdRomItoa(ans);
        return (dec);
    }
    static int  CheckRoman(String str)
    {
        char[]  rom;
        int     r;
        int     i;

        r = 0;
        i = 0;
        rom = new char[]{'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        for (int count = 0; count < str.length(); count++)
        {
            while ((i <= 7) && rom[i] != str.charAt(count))
                i++;
            if (str.charAt(count) != rom[i])
                return r;
            i = 0;
        }
        r = 2;
        return (r);
    }

    static int  CheckArab(String str) throws Exception
    {
        int i;
        int count;

        i = 0;
        count = 0;
        while ((count < str.length()) && (Character.isDigit(str.charAt(count))|| ((str.charAt(count) == '-')
                && (Character.isDigit(str.charAt(count + 1)) && Character.isDigit(str.charAt(count + 1))))))
            count++;
        if (count == str.length())
            i = 1;
        else if ((i = CheckRoman(str)) != 2)
            throw new Exception("В выражении могут быть использованы только римские или арабские числа!");
        return (i);
    }

    static int NumberOfWords(String exp)
    {
        int n;
        int i;

        n = 0;
        i = 0;
        while (n < exp.length() && exp.charAt(n) == ' ')
            n++;
        while (n < exp.length())
        {
         i++;
         while (n < exp.length() && exp.charAt(n) == ' ')
             n++;
         while (n < exp.length() && exp.charAt(n) != ' ')
             n++;
        }
        return(i);
    }
    static void MtdSplit(String str, String[] mass) throws Exception
    {
        int     i;
        int     n;

        i = 0;
        n = 0;
        while (i < NumberOfWords(str))
        {
            for (int count = 0; count < str.length(); count++)
            {
                while (str.charAt(count) == ' ')
                    count++;
                n = count;
                while (count < str.length() && str.charAt(count) != ' ')
                    count++;
                mass[i] = str.substring(n, count);
                i++;
            }
        }
        if (i == 3)
            return;
        throw new Exception("Введенное выражение не соответствует формату!");
    }

    static void Decision(String exp) throws Exception
    {
        int         i;
        String[]    mass;

        mass = new String[3];
        MtdSplit(exp, mass);
        if ( (i = CheckArab(mass[0])) != CheckArab(mass[2]))
            throw new Exception("Выражение может содержать только римские или только арабские числа!");
        switch (i)
        {
            case 1:
                System.out.println(MtdAnswerArab(mass));
                break;
            case 2:
                System.out.println(MtdAnswerRoman(mass));
                break;
        }
    }

    public static void main(String[] args) throws Exception 
    {
        Scanner inp;
        String  exp;
        System.out.print("Введите выражение: ");
        inp = new Scanner(System.in);
        if (!inp.hasNext())
            throw new Exception("Строка с Выражением пуста!");
        exp = inp.nextLine();
        Decision(exp);
    }
}
