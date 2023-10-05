
package com.users;

public class getRatio {

    int gcd(int p, int q) {
        if (q == 0) {
            return p;
        } else {
            return gcd(q, p % q);
        }
    }

   public String ratio(int a, int b) {
        final int gcd = gcd(a, b);
        String res = a / gcd + ":" + b / gcd+" ";
        return res;
    }

}
