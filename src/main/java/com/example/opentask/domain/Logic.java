package com.example.opentask.domain;

import java.util.Arrays;
import java.util.Random;

public class Logic {
    private int[] numb = new int[4];

    public int[] getNumb() {
        return numb;
    }

    public void setNumb(int[] numb) {
        this.numb = numb;
    }

    /**
     * создание рандомного числа,в случае если цифра повторяется цикл начинается заного до создания уникального числа.
     *
     */
    public int[] generator(){
        boolean z =true;
        Random random = new Random();
        int k=0;
        while(k!=4){
            numb[k] = random.nextInt(10);
            for(int i = 0; i< k; i++){
                if (k==0)break;
                else if(numb[k] == numb[i]) {
                    z=false;
                    break;
                }

            }
            if(z)k++;else z=true;
        }
        return numb;
    }
    /**
    * Проверка числа которое вводит пользователь
    */
    public int[] parser(String answer){
        int[] arr = new int[4];
        for(int i = 0; i < 4; i++){
            arr[i] = Integer.parseInt(answer.substring(i,i+1));
        }
        return arr;
    }

    /**
     * Основная логика игры
     */
    public String game(int[] a) {
        int b = 0, k = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == numb[i]) b++;
            for (int j = 0; j < a.length; j++) {
                if (a[i] == numb[j]) {
                    if (i == j) ;
                    else k++;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if(b==4){
            stringBuilder.append(b).append("Б").append(k).append("К");
            stringBuilder.append("  ").append("Верное число").append("  ").append(Arrays.toString(numb).replace("[","").replace("]","").replace(" ,",""));
        }else stringBuilder.append(b).append("Б").append(k).append("К").append("  ").append("Некорректное число");
        return String.valueOf(stringBuilder);
    }
}
